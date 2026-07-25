package com.dxlan.acl.premiumasset.domain.enums;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum ExpirationState implements NameDisplayable {

    RECONCILED("Reconciled"),
    UNRECONCILED("Unreconciled");

    private final String displayName;

    private ExpirationState(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }
}
