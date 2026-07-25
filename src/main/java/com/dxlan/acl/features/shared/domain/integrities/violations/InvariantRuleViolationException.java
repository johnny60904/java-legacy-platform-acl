package com.dxlan.acl.features.shared.domain.integrities.violations;

import com.dxlan.acl.features.shared.exceptions.ErrorCode;

public final class InvariantRuleViolationException extends RuntimeException {

    private static final String INDENT = "    ";

    private final ViolationModule violationModule;
    private final ViolationCause violationCause;
    private final ViolationCategory violationCategory;
    private final ViolationInvariant violationInvariant;
    private final ViolationTarget violationTarget;
    private final ErrorCode errorCode;
    private final Class<?> affectedModelClass;

    public InvariantRuleViolationException(
            final ViolationModule violationModule,
            final DomainViolation violation,
            final ViolationInvariant invariant,
            final Class<?> affectedModelClass
    ) {
        super(
                violation.toMessage()
        );
        this.violationModule = violationModule;
        this.violationCause = violation.cause();
        this.violationCategory = violation.cause().category();
        this.violationInvariant = invariant;
        this.violationTarget = violation.cause().target();
        this.errorCode = ViolationCode.of(
                violationModule,
                violation.cause().category(),
                invariant
        );
        this.affectedModelClass = affectedModelClass;
    }

    public String violatedValue() {
        return violationCause.value();
    }

    public ViolationModule violatedModule() {
        return violationModule;
    }

    public ViolationCause violationCause() {
        return violationCause;
    }

    public ViolationCategory violationCategory() {
        return violationCategory;
    }

    public ViolationInvariant violationInvariant() { return violationInvariant; }

    public ViolationTarget violationTarget() {
        return violationTarget;
    }

    public ErrorCode errorCode() {
        return errorCode;
    }

    public Class<?> affectedModelClass() {
        return affectedModelClass;
    }

    public String detailedMessage() {
        return "\n" + INDENT + "[ Violation Module ]: " +
                violationModule.getClass().getSimpleName() + "\n" +
                "\n" + INDENT + "[ Violation Cause ]: " +
                violationCause.getClass().getSimpleName() + "\n" +
                "\n" + INDENT + "[ Violation Category ]: " +
                violationCategory.codeName() +
                "\n" + INDENT + "( Category Code: " +
                violationCategory.categoryCode() + ", Layer Code: " +
                violationCategory.layerCode() + ")" + "\n" +
                "\n" + INDENT + "[ Violation Invariant ]: " +
                violationInvariant.displayName() + "\n" +
                "\n" + INDENT + "[ Violation Target ]: " +
                violationTarget.displayName() + "\n" +
                "\n" + INDENT + "[ Violated Value ]: " +
                violationCause.value() + "\n" +
                "\n" + INDENT + "[ Affected Model Class ]: " +
                affectedModelClass.getSimpleName() + "\n" +
                "\n" + INDENT + "[ Error Code ]: " +
                errorCode.value() + "\n";
    }

}
