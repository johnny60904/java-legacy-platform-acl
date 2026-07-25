package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.notification.common.enums.ChatMessageType;
import com.dxlan.acl.features.shared.arrays.ArrayGuard;
import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.boundaries.LegacyBoundaryDefender;
import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreTypeMetadata;
import com.dxlan.acl.features.shared.collections.CollectionGuard;
import com.dxlan.acl.features.shared.text.TextGuard;
import net.legacy.platform.core.cluster.PlatformGateway;
import net.legacy.platform.core.session.ClientConnection;
import net.legacy.platform.core.network.GlobalNotifier;
import net.legacy.platform.core.session.UserSession;
import net.legacy.platform.core.network.OutBoundPayload;
import net.legacy.platform.core.network.NetworkPayload;
import net.legacy.platform.core.network.SessionManager;
import net.legacy.platform.core.network.ContextDispatcher;
import net.legacy.platform.core.enums.NotificationChannel;

import java.util.ArrayList;
import java.util.List;

public final class LegacyPlatformNotificationSender implements NotificationSender {

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(LegacyPlatformNotificationSender.class);

    private LegacyPlatformNotificationSender() {}

    private static class Holder {
        private static final LegacyPlatformNotificationSender INSTANCE =
                new LegacyPlatformNotificationSender();
    }

    public static LegacyPlatformNotificationSender getInstance() {
        return Holder.INSTANCE;
    }

    private static void validateChatMessageTypeNotNull(
            final ChatMessageType chatMessageType
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                chatMessageType,
                ChatMessageType.getMetadata()
        );
    }

    private static void validateInputs(
            final int clusterGroupId,
            final int sessionProcessId
    ) {
        LegacyBoundaryDefender.requireClusterGroupIdValid(VALIDATOR, clusterGroupId);
        LegacyBoundaryDefender.requireSessionProcessIdValid(VALIDATOR, sessionProcessId);
    }

    private static void validateInputs(
            final int clusterGroupId,
            final int sessionProcessId,
            final ChatMessageType chatMessageType
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        validateChatMessageTypeNotNull(chatMessageType);
    }

    private static ClientConnection findClientById(
            final int clusterGroupId,
            final int sessionProcessId
    ) {
        UserSession legacyUserSession = PlatformGateway.getInstance()
                .getClusterByGroupId(clusterGroupId)
                .locateUserSessionById(sessionProcessId);
        VALIDATOR.requireExternalTypeNotNull(
                legacyUserSession,
                LegacyCoreTypeMetadata.USER_SESSION
        );
        ClientConnection legacyClientConnection = legacyUserSession.getClientConnection();
        VALIDATOR.requireExternalTypeNotNull(
                legacyClientConnection,
                LegacyCoreTypeMetadata.CLIENT_CONNECTION
        );
        return legacyClientConnection;
    }

    private static void validateOutBoundPayloadNotNull(
            final OutBoundPayload outBoundPayload
    ) {
        VALIDATOR.requireExternalTypeNotNull(
                outBoundPayload,
                LegacyCoreTypeMetadata.OUTBOUND_PAYLOAD
        );
    }

    private static NotificationChannel translateChatMessageTypeToNotificationChannel(
            final ChatMessageType chatMessageType
    ) {
        return NotificationChannel.valueOf(
                ChatMessageType.EXTERNAL_LOOKUP_MAP.get(
                        chatMessageType
                )
        );
    }

    private static NetworkPayload createNotificationPayload(
            final String message,
            final ChatMessageType chatMessageType
    ) {
        OutBoundPayload outBoundPayload = SessionManager.createNotificationPayload(
                translateChatMessageTypeToNotificationChannel(chatMessageType),
                message
        );
        validateOutBoundPayloadNotNull(outBoundPayload);
        return outBoundPayload;
    }

    private static List<NetworkPayload> createNotificationMutiLinesPayload(
            final List<String> messageLines,
            final ChatMessageType chatMessageType
    ) {
        NotificationChannel notificationChannel =
                translateChatMessageTypeToNotificationChannel(chatMessageType);
        List<NetworkPayload> payloads = new ArrayList<>();
        for (String line : messageLines) {
            payloads.add(
                    SessionManager.createNotificationPayload(
                            notificationChannel,
                            line
                    )
            );
        }
        VALIDATOR.requireExternalTypeNoneNullElements(
                payloads,
                LegacyCoreTypeMetadata.NETWORK_PAYLOAD
        );
        return List.copyOf(payloads); /// unmodifiableList
    }

    private static List<NetworkPayload> createNotificationMutiLinesPayload(
            final String[] messageLines,
            final ChatMessageType chatMessageType
    ) {
        NotificationChannel notificationChannel =
                translateChatMessageTypeToNotificationChannel(chatMessageType);
        List<NetworkPayload> payloads = new ArrayList<>();
        for (String line : messageLines) {
            payloads.add(
                    SessionManager.createNotificationPayload(
                            notificationChannel,
                            line
                    )
            );
        }
        VALIDATOR.requireExternalTypeNoneNullElements(
                payloads,
                LegacyCoreTypeMetadata.NETWORK_PAYLOAD
        );
        return List.copyOf(payloads); /// unmodifiableList
    }

    private static NetworkPayload createModalNotificationPayload(
            final String message
    ) {
        OutBoundPayload outBoundPayload = ContextDispatcher.dispatchGlobalPayload(
                GlobalNotifier.createModalAlert(message)
        );
        validateOutBoundPayloadNotNull(outBoundPayload);
        return outBoundPayload;
    }

    @Override
    public void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message,
            final ChatMessageType chatMessageType
    ) {
        validateInputs(clusterGroupId, sessionProcessId, chatMessageType);
        TextGuard.requireHasText(message, "Message");
        ClientConnection legacyClientConnection = findClientById(clusterGroupId, sessionProcessId);
        legacyClientConnection.write(
                createNotificationPayload(message, chatMessageType)
        );
    }

    @Override
    public void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final List<String> messageLines,
            final ChatMessageType chatMessageType
    ) {
        validateInputs(clusterGroupId, sessionProcessId, chatMessageType);
        CollectionGuard.requireNonNullElements(
                messageLines,
                "MessageLines"
        );
        ClientConnection legacyClientConnection = findClientById(clusterGroupId, sessionProcessId);
        legacyClientConnection.write(
                createNotificationMutiLinesPayload(
                        messageLines,
                        chatMessageType
                )
        );
    }

    @Override
    public void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final String[] messageLines,
            final ChatMessageType chatMessageType
    ) {
        validateInputs(clusterGroupId, sessionProcessId, chatMessageType);
        ArrayGuard.requireNoneNull(
                messageLines,
                "MessageLines"
        );
        ClientConnection legacyClientConnection = findClientById(clusterGroupId, sessionProcessId);
        legacyClientConnection.write(
                createNotificationMutiLinesPayload(
                        messageLines,
                        chatMessageType
                )
        );
    }

    @Override
    public void sendToClient(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        TextGuard.requireHasText(message, "Message");
        ClientConnection legacyClientConnection = findClientById(clusterGroupId, sessionProcessId);
        legacyClientConnection.write(
                createModalNotificationPayload(message)
        );
    }

}
