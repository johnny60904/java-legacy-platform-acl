package com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts;

import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;

public record TimedPremiumAssetValidationContext(
        ExpirationTimeContext expirationTimeContext,
        DomainConceptProperty<Long> extensionDurationProperty,
        DomainConceptProperty<ExpirationUnit> expirationUnitProperty
) {

    public static TimedPremiumAssetValidationContext of(
            final ExpirationTimeContext expirationTimeContext,
            final DomainConceptProperty<Long> extensionDurationProperty,
            final DomainConceptProperty<ExpirationUnit> expirationUnitProperty
    ) {
        return new TimedPremiumAssetValidationContext(
                expirationTimeContext,
                extensionDurationProperty,
                expirationUnitProperty
        );
    }

}
