package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.notification.common.metadata.SendNotificationSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.text.TextGuard;

public record SendModalNotificationCommand(
        int clusterGroupId,
        int sessionProcessId,
        String message
) {

    public SendModalNotificationCommand {
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
    }

    public static SendModalNotificationCommand of(
            final int clusterGroupId,
            final int sessionProcessId,
            final String message
    ) {
        return new SendModalNotificationCommand(
                clusterGroupId,
                sessionProcessId,
                message
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
            return SendModalNotificationCommand.class.getSimpleName();
        }
    }

    private static final SendNotificationSliceMetadata METADATA = new Metadata();

    public static SendNotificationSliceMetadata getMetadata() {
        return METADATA;
    }

}
