package com.dxlan.acl.features.infrastructure.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class AclLogger {

    private static final AtomicInteger logCounter = new AtomicInteger(0);

    private AclLogger() {
        throw new AssertionError();
    }

    private static String getCurrentTimeStamp() {
        return DateTimeFormatter
                .ofPattern(AclLoggerConfiguration.CURRENT_TIMESTAMP_PATTERN)
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
    }

    private static String getLogFileTimeStamp() {
        return DateTimeFormatter
                .ofPattern(AclLoggerConfiguration.LOG_FILE_TIMESTAMP_PATTERN)
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
    }

    private static final ExecutorService logExecutor =
            Executors.newSingleThreadExecutor(
                    runnable -> {
                        Thread t = new Thread(runnable, AclLoggerConfiguration.THREAD_WRITER_NAME);
                        t.setDaemon(true);return t;
                    }
            );

    static {
        Executors.newSingleThreadScheduledExecutor(
                runnable -> {
                    Thread t = new Thread(runnable, AclLoggerConfiguration.THREAD_TIMER_NAME);
                    t.setDaemon(true);return t;
                }
        ).scheduleAtFixedRate(
                () -> logCounter.set(0),
                AclLoggerConfiguration.TIMER_INITIAL_DELAY,
                AclLoggerConfiguration.TIMER_PERIOD,
                AclLoggerConfiguration.TIMER_UNIT
        );
    }

    private static String createMessage(
            final String title,
            final String message
    ) {
        return "\n[ " + title + " ] [ " + getCurrentTimeStamp() + " ]:\n" +
                message + "\n";
    }

    private static String createMessage(
            final String title,
            final Class<?> targetClass,
            final String message
    ) {
        return "\n[ " + title + " ] [ " + targetClass.getSimpleName() + " ]" +
                " [ " + getCurrentTimeStamp() + " ]:\n" +
                message + "\n";
    }

    private static File createLogFile(
            final String moduleName,
            final String directory
    ) {
        return new File(
                directory,
                AclLoggerConfiguration.LOG_FILE_NAME_PREFIX +
                moduleName +
                AclLoggerConfiguration.LOG_FILE_NAME_SUFFIX +
                getLogFileTimeStamp() +
                AclLoggerConfiguration.LOG_FILE_EXTENSION
        );
    }

    public static void info(
            final String message
    ) {
        logExecutor.submit(() -> System.out.printf(createMessage("INFO", message)));
    }

    public static void info(
            final Class<?> targetClass,
            final String message
    ) {
        logExecutor.submit(() -> System.out.printf(createMessage("INFO", targetClass, message)));
    }

    private static void executeFileWrite(
            final String moduleName,
            final String message,
            final Throwable throwable
    ) {
        String directoryPath = AclLoggerConfiguration.DIRECTORY_PATH + moduleName;
        File directory = new File(directoryPath);
        if (!directory.exists()) directory.mkdirs();
        File logFile = createLogFile(moduleName, directoryPath);

        try (FileWriter fileWriter = new FileWriter(logFile, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf(createMessage("ERROR", message));

            if (throwable != null) throwable.printStackTrace(printWriter);
            printWriter.println();

        } catch (IOException exception) {
            System.err.println("Failed to write ACL log to file: " + exception.getMessage());
        }
    }

    public static void error(
            final Class<?> targetClass,
            final String message,
            final Throwable throwable
    ) {
        String packageName = targetClass.getPackageName();
        String moduleName = AclLoggerConfiguration.MODULE_NAME_DEFAULT;
        if (packageName.contains(AclLoggerConfiguration.PREMIUM_ASSET_MODULE)) {
            moduleName = AclLoggerConfiguration.PREMIUM_ASSET_MODULE_NAME;
        } else if (packageName.contains(AclLoggerConfiguration.HARDWARE_ASSET_MODULE)) {
            moduleName = AclLoggerConfiguration.HARDWARE_ASSET_MODULE_NAME;
        }

        int currentCount = logCounter.incrementAndGet();
        String finalMessage;
        boolean includeStackTrace = true;

        if (currentCount <= AclLoggerConfiguration.MAX_ALLOWED_LOGS) {
            finalMessage = message;
        } else if (currentCount == AclLoggerConfiguration.MAX_ALLOWED_LOGS + 1) {
            finalMessage = message +
                    "\n[ WARNING ] Error frequency is too high," +
                    " subsequent detailed logs have been automatically rate-limited and hidden.";
        } else {
            finalMessage = message + " (Skipped details due to rate limiting)";
            includeStackTrace = false;
        }

        final String targetModule = moduleName;
        final Throwable finalThrowable = includeStackTrace ? throwable : null;

        logExecutor.submit(() -> {
            System.err.printf(createMessage("ERROR", targetClass, message));
            executeFileWrite(targetModule, finalMessage, finalThrowable);
        });
    }

    public static void error(
            final Class<?> targetClass,
            final String message
    ) {
        error(targetClass, message, null);
    }

}
