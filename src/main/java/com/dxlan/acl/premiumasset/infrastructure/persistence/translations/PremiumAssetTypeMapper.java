package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;

public final class PremiumAssetTypeMapper {

    private PremiumAssetTypeMapper() {}

    public static PremiumAssetType map(
            final boolean PremiumAssetTypeFlag
    ) {
        return PremiumAssetTypeFlag
                ? PremiumAssetType.PERMANENT
                : PremiumAssetType.TIMED;
    }

}
