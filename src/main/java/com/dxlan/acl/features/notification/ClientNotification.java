package com.dxlan.acl.features.notification;

import com.dxlan.acl.features.notification.commands.sendnotification.SendNotificationCommand;
import com.dxlan.acl.features.notification.commands.sendnotification.SendNotificationMultiLinesArrayCommand;
import com.dxlan.acl.features.notification.commands.sendnotification.SendNotificationMultiLinesListCommand;
import com.dxlan.acl.features.notification.commands.sendnotification.SendModalNotificationCommand;
import com.dxlan.acl.features.notification.common.enums.ChatMessageType;

import java.util.List;

public final class ClientNotification {

    private ClientNotification() {}

    public static void sendToTerminalConsole(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message,
            final ChatMessageType chatMessageType
    ) {
        ClientNotificationContainer.getInstance()
                .getSendNotificationHandler()
                .handle(
                        SendNotificationCommand.of(
                                clusterGroupId,
                                sessionProcessId,
                                message,
                                chatMessageType
                        )
                );
    }

    public static void sendToTerminalConsole(
            final int clusterGroupId,
            final int sessionProcessId,
            final List<String> messageLines,
            final ChatMessageType chatMessageType
    ) {
        ClientNotificationContainer.getInstance()
                .getSendNotificationHandler()
                .handle(
                        SendNotificationMultiLinesListCommand.of(
                                clusterGroupId,
                                sessionProcessId,
                                messageLines,
                                chatMessageType
                        )
                );
    }

    public static void sendToTerminalConsole(
            final int clusterGroupId,
            final int sessionProcessId,
            final String[] messageLines,
            final ChatMessageType chatMessageType
    ) {
        ClientNotificationContainer.getInstance()
                .getSendNotificationHandler()
                .handle(
                        SendNotificationMultiLinesArrayCommand.of(
                                clusterGroupId,
                                sessionProcessId,
                                messageLines,
                                chatMessageType
                        )
                );
    }

    public static void sendModalAlert(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message
    ) {
        ClientNotificationContainer.getInstance()
                .getSendNotificationHandler()
                .handle(
                        SendModalNotificationCommand.of(
                                clusterGroupId,
                                sessionProcessId,
                                message
                        )
                );
    }

}
