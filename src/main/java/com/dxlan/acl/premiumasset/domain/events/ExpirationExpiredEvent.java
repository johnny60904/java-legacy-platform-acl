package com.dxlan.acl.premiumasset.domain.events;

import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

public final class ExpirationExpiredEvent extends BaseEvent {

    private final AclPremiumAsset expiredPremiumAsset;
    private final PremiumAssetExpiration originalExpiration;

    public ExpirationExpiredEvent(
            final AclPremiumAsset expiredPremiumAsset,
            final PremiumAssetExpiration originalExpiration
    ) {
        this.expiredPremiumAsset = expiredPremiumAsset;
        this.originalExpiration = originalExpiration;
    }

    public static ExpirationExpiredEvent of(
            final AclPremiumAsset expiredPremiumAsset,
            final PremiumAssetExpiration originalExpiration
    ) {
        return new ExpirationExpiredEvent(expiredPremiumAsset, originalExpiration);
    }

    public AclPremiumAsset getExpiredPremiumAsset() {
        return expiredPremiumAsset;
    }

    public PremiumAssetExpiration getOriginalExpiration() {
        return originalExpiration;
    }

}
