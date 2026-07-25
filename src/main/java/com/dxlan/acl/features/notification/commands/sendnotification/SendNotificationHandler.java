package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;

public final class SendNotificationHandler {

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(SendNotificationHandler.class);

    private final NotificationSender notificationSender;

    private SendNotificationHandler(
            final NotificationSender notificationSender
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                notificationSender,
                NotificationSender.getMetadata()
        );
        this.notificationSender = notificationSender;
    }

    public void handle(
            final SendNotificationCommand command
    ) {
       VALIDATOR.requireInternalTypeNotNull(
               command,
               SendNotificationCommand.getMetadata()
       );
       notificationSender.sendToClient(
               command.clusterGroupId(),
               command.sessionProcessId(),
               command.message(),
               command.chatMessageType()
       );
    }

    public void handle(
            final SendNotificationMultiLinesArrayCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                SendNotificationMultiLinesArrayCommand.getMetadata()
        );
        notificationSender.sendToClient(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.messageLines(),
                command.chatMessageType()
        );
    }

    public void handle(
            final SendNotificationMultiLinesListCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                SendNotificationMultiLinesListCommand.getMetadata()
        );
        notificationSender.sendToClient(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.messageLines(),
                command.chatMessageType()
        );
    }

    public void handle(
            final SendModalNotificationCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                SendModalNotificationCommand.getMetadata()
        );
        notificationSender.sendToClient(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.message()
        );
    }

    public static SendNotificationHandler of(
            final NotificationSender notificationSender
    ) {
        return new SendNotificationHandler(
                notificationSender
        );
    }

}
