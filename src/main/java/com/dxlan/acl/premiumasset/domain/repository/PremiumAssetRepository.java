package com.dxlan.acl.premiumasset.domain.repository;

import com.dxlan.acl.features.shared.domain.integrities.DomainInvariant;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.common.PremiumAssetDomainMetadata;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;

import java.time.Instant;
import java.time.ZoneId;

public interface PremiumAssetRepository {

    AclPremiumAsset loadAclPremiumAssetByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    AclPremiumAsset loadAclPremiumAssetByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    AclPremiumAsset loadAclPremiumAssetByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    AclPremiumAsset loadAclPremiumAssetByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    AclPremiumAsset loadAclPremiumAssetByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    AclPremiumAsset loadAclPremiumAssetByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    );

    void saveExpiration(
            final AclPremiumAsset aclPremiumAsset
    );

    public static enum Invariant implements DomainInvariant {
        /// ResourceAbsentCause
        PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT(
                "PremiumAssetResourceMustBePresent",
                1
        ),
        /// DataCorruptedCause
        PREMIUM_ASSET_IDENTITY_MUST_BE_VALID(
                "PremiumAssetIdentityMustBeValid",
                1
        ),
        /// DataCorruptedCause
        PREMIUM_ASSET_LIFETIME_MUST_BE_VALID(
                "PremiumAssetLifetimeMustBeValid",
                2
        ),
        /// DataCorruptedCause
        PREMIUM_ASSET_EXPIRATION_MUST_BE_VALID(
                "PremiumAssetExpirationMustBeValid",
                3
        );

        private final String displayName;
        private final int serial;

        private Invariant(
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

    public static PremiumAssetDomainMetadata getMetadata() {
        return PremiumAssetRepositoryMetadata.getInstance();
    }

}
