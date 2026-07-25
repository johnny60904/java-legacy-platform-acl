package com.dxlan.acl.features.shared.validations.causes;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCategory;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationCause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public record RequestTargetAbsentCause(
        ValidationTarget target,
        String targetValue,
        String resourceSourceName
) implements ValidationCause {

    public static RequestTargetAbsentCause of(
            final ValidationTarget target,
            final String targetValue,
            final String resourceSourceName
    ) {
        return new RequestTargetAbsentCause(
                target,
                targetValue,
                resourceSourceName
        );
    }

    @Override
    public String toMessage() {
        return "Requested resource [" +
                target.displayName() + "]" +
                " (" + targetValue + ")" +
                " is absent from '" +
                resourceSourceName + "'.";
    }

    @Override
    public ValidationCategory category() {
        return ValidationCategory.REQUEST_TARGET_ABSENT;
    }

    @Override
    public ValidationTarget target() {
        return target;
    }

    @Override
    public String value() {
        return targetValue;
    }

}
