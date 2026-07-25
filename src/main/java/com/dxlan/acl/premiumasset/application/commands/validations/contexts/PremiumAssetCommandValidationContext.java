package com.dxlan.acl.premiumasset.application.commands.validations.contexts;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationProperty;

import java.time.Instant;
import java.time.ZoneId;

public record PremiumAssetCommandValidationContext(
        ValidationProperty<Instant> timeAnchorProperty,
        ValidationProperty<ZoneId> timeZoneProperty,
        ValidationProperty<Integer> clusterGroupIdProperty,
        ValidationProperty<Integer> sessionProcessIdProperty
) {

    public static PremiumAssetCommandValidationContext of(
            final ValidationProperty<Instant> timeAnchorProperty,
            final ValidationProperty<ZoneId> timeZoneProperty,
            final ValidationProperty<Integer> clusterGroupIdProperty,
            final ValidationProperty<Integer> sessionProcessIdProperty
    ) {
        return new PremiumAssetCommandValidationContext(
                timeAnchorProperty,
                timeZoneProperty,
                clusterGroupIdProperty,
                sessionProcessIdProperty
        );
    }

}
