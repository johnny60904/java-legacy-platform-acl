package com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationClause;
import com.dxlan.acl.premiumasset.application.common.interfaces.PremiumAssetApplicationMetadata;

import java.time.Instant;
import java.time.ZoneId;

public interface PremiumAssetGateway {

    PremiumAssetDetails loadDetailsByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    PremiumAssetDetails loadDetailsByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    PremiumAssetDetails loadDetailsByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    public static enum Clause implements ValidationClause {
        /// ResourceAbsentCause
        PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT(
                "PremiumAssetResourceMustBePresent",
                2
        ),
        /// DataCorruptedCause
        PREMIUM_ASSET_IDENTITY_MUST_BE_VALID(
                "PremiumAssetIdentityMustBeValid",
                4
        ),
        /// DataCorruptedCause
        PREMIUM_ASSET_LIFETIME_MUST_BE_VALID(
                "PremiumAssetLifetimeMustBeValid",
                5
        ),
        /// DataCorruptedCause
        PREMIUM_ASSET_EXPIRATION_MUST_BE_VALID(
                "PremiumAssetExpirationMustBeValid",
                6
        );

        private final String displayName;
        private final int serial;

        private Clause(
                final String displayName,
                final int serial
        ) {
            this.displayName = displayName;
            this.serial = serial;
        }

        @Override
        public int serialNumber() {
            return serial;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

    public static PremiumAssetApplicationMetadata getMetadata() {
        return PremiumAssetGatewayMetadata.getInstance();
    }

}
