package com.dxlan.acl.features.shared.domain.integrities.violations;

public record DomainViolation(
        ViolationCause cause
) {

    public static DomainViolation of(
            final ViolationCause cause
    ) {
        return new DomainViolation(cause);
    }

    public String toMessage() {
        return cause().toMessage();
    }

}
