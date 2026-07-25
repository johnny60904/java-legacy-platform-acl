package com.dxlan.acl.features.shared.validations.taxonomy;

import com.dxlan.acl.features.shared.exceptions.ErrorCode;

public record ValidationCode(
        int value
) implements ErrorCode {

    public static ValidationCode of(
            final ValidationModule module,
            final ValidationCategory category,
            final ValidationClause constraint
    ) {
        String codeStr = String.format(
                "%02d%d%02d%02d",
                module.moduleCode(),
                category.layerCode(),
                category.categoryCode(),
                constraint.serialNumber()
        );
        return new ValidationCode(
                Integer.parseInt(codeStr)
        );
    }

}
