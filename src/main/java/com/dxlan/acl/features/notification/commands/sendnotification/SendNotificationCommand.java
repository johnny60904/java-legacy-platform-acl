package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.notification.common.enums.ChatMessageType;
import com.dxlan.acl.features.notification.common.metadata.SendNotificationSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.text.TextGuard;

import java.util.Objects;

public record SendNotificationCommand(
        int clusterGroupId,
        int sessionProcessId,
        String message,
        ChatMessageType chatMessageType
) {

    public SendNotificationCommand {
        ClusterParameterGuard.requireClusterGroupIdValid(
                clusterGroupId,
                SendNotificationCommonField.CLUSTER_GROUP_ID.displayName()
        );
        ClusterParameterGuard.requireSessionProcessIdValid(
                sessionProcessId,
                SendNotificationCommonField.SESSION_PROCESS_ID.displayName()
        );


        TextGuard.requireHasText(
                message,
                SendNotificationCommonField.MESSAGE.displayName()
        );


        Objects.requireNonNull(
                chatMessageType,
                ChatMessageType.getMetadata().typeName() +
                " must not be null."
        );
    }

    public static SendNotificationCommand of(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message,
            final ChatMessageType chatMessageType
    ) {
        return new SendNotificationCommand(
                clusterGroupId,
                sessionProcessId,
                message,
                chatMessageType
        );
    }

    private static record Metadata() implements SendNotificationSliceMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.SLICE_COMMAND;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.TRANSACTION_SCRIPT;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.VERTICAL_SLICE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.CQRS_COMMAND;
        }

        @Override
        public ArchitecturalStereotype stereotype() {
            return ArchitecturalStereotype.COMMAND;
        }

        @Override
        public LanguageElement languageElement() {
            return LanguageElement.RECORD;
        }

        @Override
        public String typeName() {
            return SendNotificationCommand.class.getSimpleName();
        }
    }

    private static final SendNotificationSliceMetadata METADATA = new Metadata();

    public static SendNotificationSliceMetadata getMetadata() {
        return METADATA;
    }

}
