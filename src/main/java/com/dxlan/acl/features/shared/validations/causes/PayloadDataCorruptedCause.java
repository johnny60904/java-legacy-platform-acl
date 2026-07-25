package com.dxlan.acl.features.shared.validations.causes;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCategory;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public record PayloadDataCorruptedCause(
        ValidationTarget target,
        String corruptedValue,
        String integrityConstraint,
        String validationContextDescription
) implements ValidationCause {

    public static PayloadDataCorruptedCause of(
            final ValidationTarget target,
            final String corruptedValue,
            final String integrityConstraint,
            final String validationContextDescription
    ) {
        return new PayloadDataCorruptedCause(
                target,
                corruptedValue,
                integrityConstraint,
                validationContextDescription
        );
    }

    @Override
    public String toMessage() {
        return "Critical Data Corruption detected: " +
                " The value (" + corruptedValue + ") of '" +
                target.displayName() + "'" +
                " violated the constraint [" +
                integrityConstraint + "]." +
                " Context: " + validationContextDescription + ".";
    }

    @Override
    public ValidationCategory category() {
        return ValidationCategory.PAYLOAD_DATA_CORRUPTED;
    }

    @Override
    public ValidationTarget target() {
        return target;
    }

    @Override
    public String value() {
        return corruptedValue;
    }

}
