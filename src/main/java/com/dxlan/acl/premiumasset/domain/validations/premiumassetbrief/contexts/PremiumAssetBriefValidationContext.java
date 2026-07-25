package com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.contexts;

import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;

public record PremiumAssetBriefValidationContext(
        DomainConceptProperty<Integer> assetItemIdProperty,
        DomainConceptProperty<String> assetNameProperty,
        DomainConceptProperty<Long> lifespanProperty,
        DomainConceptProperty<Integer> clusterGroupIdProperty,
        DomainConceptProperty<Integer> sessionProcessIdProperty,
        DomainConceptProperty<PremiumAssetType> premiumAssetTypeProperty
) {

    public static PremiumAssetBriefValidationContext of(
            final DomainConceptProperty<Integer> assetItemIdProperty,
            final DomainConceptProperty<String> assetNameProperty,
            final DomainConceptProperty<Long> lifespanProperty,
            final DomainConceptProperty<Integer> clusterGroupIdProperty,
            final DomainConceptProperty<Integer> sessionProcessIdProperty,
            final DomainConceptProperty<PremiumAssetType> premiumAssetTypeProperty
    ) {
        return new PremiumAssetBriefValidationContext(
                assetItemIdProperty,
                assetNameProperty,
                lifespanProperty,
                clusterGroupIdProperty,
                sessionProcessIdProperty,
                premiumAssetTypeProperty
        );
    }

}
