package com.dxlan.acl.features.shared.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class AclLogger {

    // 1. 限流防禦設定：每 10 秒鐘計數器
    private static final int MAX_ALLOWED_LOGS = 5;
    private static final AtomicInteger logCounter = new AtomicInteger(0);

    private AclLogger() {
        throw new AssertionError();
    }

    private static String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(new Date());
    }

    // 2. 核心優化：建立一個獨立的背景執行緒池，專門處理檔案 I/O，絕對不卡遊戲主執行緒！
    private static final ExecutorService logExecutor = Executors.newSingleThreadExecutor(
            runnable -> {
                    Thread t = new Thread(runnable, "ACL-Async-File-Writer");
                    t.setDaemon(true); // 設為守護執行緒，不影響伺服器正常關閉
                    return t;
            }
    );

    static {
        // 建立計數器歸零定時器
        // 每 10 秒將錯誤計數器歸零
        Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread t = new Thread(runnable, "AclLogger-Reset-Timer");
            t.setDaemon(true);
            return t;
        }).scheduleAtFixedRate(
                () -> logCounter.set(0),
                10,
                10,
                java.util.concurrent.TimeUnit.SECONDS
        );
    }

    /**
     * 【一般資訊 Log】—— 異步印在 Console，不寫入檔案，絕不卡遊戲！
     * 適用時機：管理員輸入指令、API 呼叫成功通知、正常轉義後的流程提示。
     */
    public static void info(
            String message
    ) {
        String timeStamp = getCurrentTimeStamp();

        // 丟給背景執行緒去印，遊戲執行緒 0 秒卡頓直接放行
        logExecutor.submit(() -> {
            System.out.printf(
                    "[ INFO ] [ %s ]:%n%s%n",
                    timeStamp,
                    message
            );
        });
    }

    /**
     * 【一般資訊 Log】—— 異步印在 Console，不寫入檔案，絕不卡遊戲！
     * 適用時機：管理員輸入指令、API 呼叫成功通知、正常轉義後的流程提示。
     */
    public static void info(
            Class<?> clazz,
            String message
    ) {
        String timeStamp = getCurrentTimeStamp();
        String className = clazz.getSimpleName();

        // 丟給背景執行緒去印，遊戲執行緒 0 秒卡頓直接放行
        logExecutor.submit(() -> {
            System.out.printf(
                    "[ INFO ] [ %s ] [ %s ]:%n%s%n",
                    timeStamp,
                    className,
                    message
            );
        });
    }

    /**
     * 實體檔案寫入（內部背景私有方法）
     */
    private static void executeFileWrite(
            String module,
            String timeStamp,
            String message,
            Throwable throwable
    ) {
        // 建立目錄結構 logs/acl/pet/ 或 logs/acl/equip/
        String dirPath = "logs/acl/" + module;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs(); // 自動遞迴建立資料夾
        }

        // 檔名維持你最想要的簡潔格式
        File logFile = new File(dir, "acl_" + module + "_errors.log");

        // 採用 try-with-resources，Java 21 會自動幫我們 Flush 並關閉 FileStream 釋放鎖
        try (FileWriter fw = new FileWriter(logFile, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // 寫入標準日誌開頭
            pw.printf("[ ERROR ] [ %s ]:%n%s%n", timeStamp, message);

            // 如果有拋出異常且未被限流，將 StackTrace 完整還原印入檔案
            if (throwable != null) {
                throwable.printStackTrace(pw);
            }
            pw.println(); // 空一行方便閱讀

        } catch (IOException e) {
            // 如果萬一連寫 Log 都失敗，退化印到系統標準錯誤（不影響遊戲）
            System.err.println("Failed to write ACL log to file: " + e.getMessage());
        }
    }

    /**
     * 外部唯一對接入口
     * 【錯誤日誌 Log】—— 異步分流寫入檔案 + 同步噴在 Console（含限流防禦）
     * @param clazz 當前的服務類別 (例如 PetService.class 或 EquipService.class)
     * @param message 給你方便閱讀的提示文字
     * @param throwable 捕獲到的底層 Exception
     */
    public static void error(
            Class<?> clazz,
            String message,
            Throwable throwable
    ) {

        // 判斷這筆錯誤是屬於哪個模組 (依據 package 的關鍵字判斷)
        String packageName = clazz.getPackageName();
        String moduleName = "common";
        if (packageName.contains("acl.pet")) {
            moduleName = "pet";
        } else if (packageName.contains("acl.equip")) {
            moduleName = "equip";
        } // 未來有新模組，直接在這邊多加 else if 即可！

        // 限流過濾
        int currentCount = logCounter.incrementAndGet();
        String finalMessage;
        boolean includeStackTrace = true;

        if (currentCount <= MAX_ALLOWED_LOGS) {
            finalMessage = message;
        } else if (currentCount == MAX_ALLOWED_LOGS + 1) {
            finalMessage = String.join(
                    "",
                    message,
                    "   [ WARNING ] Error frequency is too high, ",
                    "subsequent detailed logs have been automatically rate-limited and hidden."
            );
        } else {
            finalMessage = message.concat(" (Skipped detail due to rate limiting)");
            includeStackTrace = false; // 刷屏時，不記錄冗長的 StackTrace，極大節省效能
        }

        // 4. 將寫入硬碟的苦力活，丟給背景非同步執行緒去排隊處理
        final String targetModule = moduleName;
        final String className = clazz.getSimpleName();
        final Throwable finalThrowable = includeStackTrace ? throwable : null;
        final String timeStamp = getCurrentTimeStamp();

        // 丟給背景執行緒一併處理
        logExecutor.submit(() -> {
            // 先非同步印到主控台 Console
            System.err.printf(
                    "[ ERROR ] [ %s ] [ %s ]:%n%s%n",
                    timeStamp,
                    className,
                    finalMessage
            );
            // 再非同步寫入實體檔案
            executeFileWrite(targetModule, timeStamp, finalMessage, finalThrowable);
        });
    }

    public static void error(
            Class<?> clazz,
            String message
    ) {
        error(clazz, message, null);
    }

}
