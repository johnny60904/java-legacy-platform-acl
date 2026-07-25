package com.dxlan.acl.features.shared.domain.integrities;

public record DomainConceptProperty<T>(
        T value,
        DomainConcept concept
) {

    public static <T> DomainConceptProperty<T> of(
            final T value,
            final DomainConcept concept
    ) {
        return new DomainConceptProperty<T>(value, concept);
    }

}
