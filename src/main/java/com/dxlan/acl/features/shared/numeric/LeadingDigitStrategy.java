package com.dxlan.acl.features.shared.numeric;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum LeadingDigitStrategy implements NameDisplayable {

    LITERAL("Literal"),
    SIGNIFICANT("Significant");

    private final String displayName;

    private LeadingDigitStrategy(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
