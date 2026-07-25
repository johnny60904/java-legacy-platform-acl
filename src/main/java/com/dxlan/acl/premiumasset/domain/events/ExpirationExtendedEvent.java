package com.dxlan.acl.premiumasset.domain.events;

import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

public final class ExpirationExtendedEvent extends BaseEvent {

    private final AclPremiumAsset extendedPremiumAsset;
    private final PremiumAssetExpiration originalExpiration;

    public ExpirationExtendedEvent(
            final AclPremiumAsset extendedPremiumAsset,
            final PremiumAssetExpiration originalExpiration
    ) {
        this.extendedPremiumAsset = extendedPremiumAsset;
        this.originalExpiration = originalExpiration;
    }

    public static ExpirationExtendedEvent of(
            final AclPremiumAsset extendedPremiumAsset,
            final PremiumAssetExpiration originalExpiration
    ) {
        return new ExpirationExtendedEvent(extendedPremiumAsset, originalExpiration);
    }

    public AclPremiumAsset getExtendedPremiumAsset() {
        return extendedPremiumAsset;
    }

    public PremiumAssetExpiration getOriginalExpiration() {
        return originalExpiration;
    }

}
