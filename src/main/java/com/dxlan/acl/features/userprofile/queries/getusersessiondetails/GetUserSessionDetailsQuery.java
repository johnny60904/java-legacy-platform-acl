package com.dxlan.acl.features.userprofile.queries.getusersessiondetails;

import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.features.userprofile.common.metadata.GetUserSessionDetailsSliceMetadata;

public record GetUserSessionDetailsQuery(
        int clusterGroupId,
        int sessionProcessId
) {

    public GetUserSessionDetailsQuery {
        ClusterParameterGuard.requireClusterGroupIdValid(
                clusterGroupId,
                GetUserSessionDetailsCommonField.CLUSTER_GROUP_ID.displayName()
        );
        ClusterParameterGuard.requireSessionProcessIdValid(
                sessionProcessId,
                GetUserSessionDetailsCommonField.SESSION_PROCESS_ID.displayName()
        );
    }

    public static GetUserSessionDetailsQuery of(
            final int clusterGroupId,
            final int sessionProcessId
    ) {
        return new GetUserSessionDetailsQuery(
                clusterGroupId,
                sessionProcessId
        );
    }

    private static record Metadata() implements GetUserSessionDetailsSliceMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.SLICE_QUERY;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.NONE;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.VERTICAL_SLICE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.CQRS_QUERY;
        }

        @Override
        public ArchitecturalStereotype stereotype() {
            return ArchitecturalStereotype.QUERY;
        }

        @Override
        public LanguageElement languageElement() {
            return LanguageElement.RECORD;
        }

        @Override
        public String typeName() {
            return GetUserSessionDetailsQuery.class.getSimpleName();
        }
    }

    private static final GetUserSessionDetailsSliceMetadata METADATA = new Metadata();

    public static GetUserSessionDetailsSliceMetadata getMetadata() {
        return METADATA;
    }

}
