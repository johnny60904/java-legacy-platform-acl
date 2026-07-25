package com.dxlan.acl.premiumasset.domain.enums;

import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.time.TemporalType;

public enum PremiumAssetCommonConcept implements DomainConcept {

    ABSOLUTE_TIME(TemporalType.INSTANT.displayName()),
    ACL_PREMIUM_ASSET("AclPremiumAsset"),
    PERMANENT_PREMIUM_ASSET("PermanentPremiumAsset"),
    TIMED_PREMIUM_ASSET("TimedPremiumAsset"),
    EXPIRATION("Expiration"),
    PREMIUM_ASSET_EXPIRATION("PremiumAssetExpiration"),
    PREMIUM_ASSET_BRIEF("PremiumAssetBrief"),
    PREMIUM_ASSET_DETAILS("PremiumAssetDetails"),
    PREMIUM_ASSET_TYPE("PremiumAssetType");

    private final String displayName;

    private PremiumAssetCommonConcept(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
