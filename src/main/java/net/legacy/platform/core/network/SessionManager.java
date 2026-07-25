package net.legacy.platform.core.network;

import net.legacy.platform.core.enums.NotificationChannel;

public final class SessionManager {

    private SessionManager() {
        throw new AssertionError();
    }

    public static OutBoundPayload createNotificationPayload(
            final NotificationChannel channel,
            final String rawMessage
    ) {
        return new OutBoundPayload();
    }

}
