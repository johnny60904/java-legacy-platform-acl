package com.dxlan.acl.features.notification;

import com.dxlan.acl.features.notification.commands.sendnotification.NotificationSender;
import com.dxlan.acl.features.notification.commands.sendnotification.SendNotificationHandler;
import com.dxlan.acl.features.notification.commands.sendnotification.LegacyPlatformNotificationSender;
import com.dxlan.acl.features.shared.log.AclLogger;

public final class ClientNotificationContainer {

    private final SendNotificationHandler handler;

    private ClientNotificationContainer() {
        NotificationSender notificationSender =
                LegacyPlatformNotificationSender.getInstance();
        this.handler = SendNotificationHandler.of(notificationSender);
    }

    private static class Holder {
        private static final ClientNotificationContainer INSTANCE =
                new ClientNotificationContainer();
    }

    public static ClientNotificationContainer getInstance() {
        return Holder.INSTANCE;
    }

    public static void initialize() {
        AclLogger.info(
                ClientNotificationContainer.class,
                "Initializing ClientNotificationContainer...\n"
        );
        if (getInstance() != null) {
            AclLogger.info(
                    ClientNotificationContainer.class,
                    "ClientNotificationContainer initialized successfully.\n"
            );
        }
    }

    public SendNotificationHandler getSendNotificationHandler() {
        return handler;
    }

}
