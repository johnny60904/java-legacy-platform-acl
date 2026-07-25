package com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts;

import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

public record PremiumAssetCommonConceptValidationContext(
        DomainConceptProperty<PremiumAssetBrief> premiumAssetBriefProperty,
        DomainConceptProperty<PremiumAssetExpiration> premiumAssetExpirationProperty
) {

    public static PremiumAssetCommonConceptValidationContext of(
            final DomainConceptProperty<PremiumAssetBrief> premiumAssetBriefProperty,
            final DomainConceptProperty<PremiumAssetExpiration> premiumAssetExpirationProperty
    ) {
        return new PremiumAssetCommonConceptValidationContext(
                premiumAssetBriefProperty,
                premiumAssetExpirationProperty
        );
    }

}
