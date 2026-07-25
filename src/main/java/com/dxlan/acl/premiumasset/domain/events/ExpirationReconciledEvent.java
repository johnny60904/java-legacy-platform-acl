package com.dxlan.acl.premiumasset.domain.events;

import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

public final class ExpirationReconciledEvent extends BaseEvent {

    private final AclPremiumAsset reconciledPremiumAsset;
    private final PremiumAssetExpiration originalExpiration;

    public ExpirationReconciledEvent(
            final AclPremiumAsset reconciledPremiumAsset,
            final PremiumAssetExpiration originalExpiration
    ) {
        this.reconciledPremiumAsset = reconciledPremiumAsset;
        this.originalExpiration = originalExpiration;
    }

    public static ExpirationReconciledEvent of(
            final AclPremiumAsset reconciledPremiumAsset,
            final PremiumAssetExpiration originalExpiration
    ) {
        return new ExpirationReconciledEvent(reconciledPremiumAsset, originalExpiration);
    }

    public AclPremiumAsset getReconciledPremiumAsset() {
        return reconciledPremiumAsset;
    }

    public PremiumAssetExpiration getOriginalExpiration() {
        return originalExpiration;
    }

}
