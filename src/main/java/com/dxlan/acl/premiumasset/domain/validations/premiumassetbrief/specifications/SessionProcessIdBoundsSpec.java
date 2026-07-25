package com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.features.shared.numeric.NumberRange;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;

import java.util.Locale;

public final class SessionProcessIdBoundsSpec {

    private SessionProcessIdBoundsSpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<Integer> create(
                final int sessionProcessId
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.WITHIN,
                    sessionProcessId,
                    sessionProcess_Id -> NumberRange
                            .closed(
                                    ClusterPhysicsMetadata.UserSession.ID_LOWER_BOUND,
                                    ClusterPhysicsMetadata.UserSession.ID_UPPER_BOUND
                            )
                            .contains(sessionProcess_Id)
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " must be within closed range: [" +
                    ClusterPhysicsMetadata.UserSession.ID_LOWER_BOUND + ", " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.UserSession.ID_UPPER_BOUND
                    ) + "]";
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
