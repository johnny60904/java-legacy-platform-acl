package com.dxlan.acl.premiumasset.application.queries.validations.contexts;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationProperty;

import java.time.Instant;
import java.time.ZoneId;

public record GetPremiumAssetDetailsValidationContext(
        ValidationProperty<Integer> clusterGroupIdProperty,
        ValidationProperty<Integer> sessionProcessIdProperty,
        ValidationProperty<Instant> timeAnchorProperty,
        ValidationProperty<ZoneId> timeZoneProperty
) {

    public static GetPremiumAssetDetailsValidationContext of(
            final ValidationProperty<Integer> clusterGroupIdProperty,
            final ValidationProperty<Integer> sessionProcessIdProperty,
            final ValidationProperty<Instant> timeAnchorProperty,
            final ValidationProperty<ZoneId> timeZoneProperty
    ) {
        return new GetPremiumAssetDetailsValidationContext(
                clusterGroupIdProperty,
                sessionProcessIdProperty,
                timeAnchorProperty,
                timeZoneProperty
        );
    }

}
