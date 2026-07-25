package com.dxlan.acl.features.shared.validations.taxonomy;

import com.dxlan.acl.features.shared.exceptions.ErrorCode;

public final class InvalidCommandException extends ValidationException {

    private static final String INDENT = "    ";

    private final ValidationModule validationModule;
    private final ValidationCause validationCause;
    private final ValidationCategory validationCategory;
    private final ValidationClause validationClause;
    private final ValidationTarget validationTarget;
    private final ErrorCode errorCode;
    private final Class<?> commandClass;

    public InvalidCommandException(
            final ValidationModule validationModule,
            final CommandValidation validation,
            final ValidationClause validationClause,
            final Class<?> commandClass
    ) {
        super(
                validation.toMessage()
        );
        this.validationModule = validationModule;
        this.validationCause = validation.cause();
        this.validationCategory = validation.cause().category();
        this.validationClause = validationClause;
        this.validationTarget = validation.cause().target();
        this.errorCode = ValidationCode.of(
                validationModule,
                validation.cause().category(),
                validationClause
        );
        this.commandClass = commandClass;
    }

    public String rejectedValue() {
        return validationCause.value();
    }

    public ValidationModule validationModule() {
        return validationModule;
    }

    public ValidationCause validationCause() {
        return validationCause;
    }

    public ValidationCategory validationCategory() {
        return validationCategory;
    }

    public ValidationClause structuralConstraint() { return validationClause; }

    public ValidationTarget validationTarget() {
        return validationTarget;
    }

    public ErrorCode errorCode() {
        return errorCode;
    }

    public Class<?> commandClass() {
        return commandClass;
    }

    public String detailedMessage() {
        return "\n" + INDENT + "[ Validation Module ]: " +
                validationModule.getClass().getSimpleName() + "\n" +
                "\n" + INDENT + "[ Validation Cause ]: " +
                validationCause.getClass().getSimpleName() + "\n" +
                "\n" + INDENT + "[ Validation Category ]: " +
                validationCategory.codeName() +
                "\n" + INDENT + "( Category Code: " +
                validationCategory.categoryCode() + ", Layer Code: " +
                validationCategory.layerCode() + ")" + "\n" +
                "\n" + INDENT + "[ Structural Constraint ]: " +
                validationClause.displayName() + "\n" +
                "\n" + INDENT + "[ Validation Target ]: " +
                validationTarget.displayName() + "\n" +
                "\n" + INDENT + "[ Rejected Value ]: " +
                validationCause.value() + "\n" +
                "\n" + INDENT + "[ Validation Command Class ]: " +
                commandClass.getSimpleName() + "\n" +
                "\n" + INDENT + "[ Error Code ]: " +
                errorCode.value() + "\n" +
                "\n" + INDENT + " [ Message ]:\n" +
                getMessage() + "\n";
    }

}
