package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.notification.common.enums.ChatMessageType;
import com.dxlan.acl.features.notification.common.metadata.SendNotificationSliceMetadata;

import java.util.List;

public interface NotificationSender {

    void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message,
            final ChatMessageType chatMessageType
    );

    void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final List<String> messageLines,
            final ChatMessageType chatMessageType
    );

    void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final String[] messageLines,
            final ChatMessageType chatMessageType
    );

    void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message
    );

    public static SendNotificationSliceMetadata getMetadata() {
        return NotificationSenderMetadata.getInstance();
    }

}
