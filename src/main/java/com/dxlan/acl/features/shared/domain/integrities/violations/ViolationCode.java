package com.dxlan.acl.features.shared.domain.integrities.violations;

import com.dxlan.acl.features.shared.exceptions.ErrorCode;

public record ViolationCode(
        int value
) implements ErrorCode {

    public static ViolationCode of(
            final ViolationModule module,
            final ViolationCategory category,
            final ViolationInvariant invariant
    ) {
        String codeStr = String.format(
                "%02d%d%02d%02d",
                module.moduleCode(),
                category.layerCode(),
                category.categoryCode(),
                invariant.serialNumber()
        );
        return new ViolationCode(
                Integer.parseInt(codeStr)
        );
    }

}
