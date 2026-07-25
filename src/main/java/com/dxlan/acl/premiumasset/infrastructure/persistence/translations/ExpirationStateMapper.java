package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import com.dxlan.acl.premiumasset.domain.enums.ExpirationState;

public final class ExpirationStateMapper {

    private ExpirationStateMapper() {}

    public static ExpirationState map(
            final boolean reconciledFlag
    ) {
        return reconciledFlag
                ? ExpirationState.RECONCILED
                : ExpirationState.UNRECONCILED;
    }

}
