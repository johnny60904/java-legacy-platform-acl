package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.notification.common.enums.ChatMessageType;
import com.dxlan.acl.features.notification.common.metadata.SendNotificationSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.collections.CollectionGuard;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.features.shared.languages.LanguageElement;

import java.util.List;
import java.util.Objects;

public record SendNotificationMultiLinesListCommand(
        int clusterGroupId,
        int sessionProcessId,
        List<String> messageLines,
        ChatMessageType chatMessageType
) {

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(SendNotificationMultiLinesListCommand.class);

    public SendNotificationMultiLinesListCommand {
        ClusterParameterGuard.requireClusterGroupIdValid(
                clusterGroupId,
                SendNotificationCommonField.CLUSTER_GROUP_ID.displayName()
        );
        ClusterParameterGuard.requireSessionProcessIdValid(
                sessionProcessId,
                SendNotificationCommonField.SESSION_PROCESS_ID.displayName()
        );


        CollectionGuard.requireNonNullElements(
                messageLines,
                SendNotificationCommonField.MESSAGE_LINES.displayName()
        );


        Objects.requireNonNull(
                chatMessageType,
                ChatMessageType.getMetadata().typeName() +
                " must not be null."
        );
    }

    public static SendNotificationMultiLinesListCommand of(
            final int clusterGroupId,
            final int sessionProcessId,
            final List<String> messageLines,
            final ChatMessageType chatMessageType
    ) {
        return new SendNotificationMultiLinesListCommand(
                clusterGroupId,
                sessionProcessId,
                messageLines,
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
            return SendNotificationMultiLinesListCommand.class.getSimpleName();
        }
    }

    private static final SendNotificationSliceMetadata METADATA = new Metadata();

    public static SendNotificationSliceMetadata getMetadata() {
        return METADATA;
    }

}
