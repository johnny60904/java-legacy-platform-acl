package com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.specifications;

import com.dxlan.acl.features.shared.arrays.ArrayJoiner;
import com.dxlan.acl.features.shared.arrays.ArrayPredicate;
import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.specifications.Specification;

import java.util.Arrays;

public final class ClusterGroupIdDiscreteRangeSpec {

    private static final int[] ID_TABLE = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18,
            29, 30, 31,
            43, 44, 45, 46, 47, 48, 49,
            50, 51, 52, 53,
            100
    };

    static {
        Arrays.sort(ID_TABLE);
    }

    private ClusterGroupIdDiscreteRangeSpec() {}

    public static record Definition(
            DomainConcept concept
    ) {

        public Specification<Integer> create(
                final int clusterGroupId
        ) {
            return new Specification.Leaf<>(
                    concept.displayName(),
                    OperatorType.CONTAINS,
                    clusterGroupId,
                    clusterGroup_Id -> ArrayPredicate.contains(ID_TABLE, clusterGroup_Id)
            );
        }

        public String ruleDescription() {
            return concept.displayName() +
                    " must be one of valid value: " +
                    ArrayJoiner.join(ID_TABLE);
        }

    }

    public static Definition of(
            final DomainConcept concept
    ) {
        return new Definition(concept);
    }

}
