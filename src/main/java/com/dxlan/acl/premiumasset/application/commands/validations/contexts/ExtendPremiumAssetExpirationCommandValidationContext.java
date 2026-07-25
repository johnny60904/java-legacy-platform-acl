package com.dxlan.acl.premiumasset.application.commands.validations.contexts;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationProperty;

public record ExtendPremiumAssetExpirationCommandValidationContext(
        ValidationProperty<Long> extensionDurationProperty,
        ValidationProperty<String> expirationUnitTokenProperty
) {

    public static ExtendPremiumAssetExpirationCommandValidationContext of(
            final ValidationProperty<Long> extensionDurationProperty,
            final ValidationProperty<String> expirationUnitTokenProperty
    ) {
        return new ExtendPremiumAssetExpirationCommandValidationContext(
                extensionDurationProperty,
                expirationUnitTokenProperty
        );
    }

}
