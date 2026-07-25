package com.dxlan.acl.features.shared.domain.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;

import java.util.Objects;

public final class DomainConceptRequirementSpec {

    private DomainConceptRequirementSpec() {
        throw new AssertionError();
    }

    public static <T> Specification<T> create(
            final T candidate,
            final DomainConcept concept
    ) {
        return new Specification.Leaf<>(
                concept.displayName(),
                OperatorType.NOT_EQUALS,
                candidate,
                Objects::nonNull
        );
    }

}
