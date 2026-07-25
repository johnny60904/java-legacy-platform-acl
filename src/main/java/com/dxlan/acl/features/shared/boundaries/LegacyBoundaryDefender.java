package com.dxlan.acl.features.shared.boundaries;

import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreFieldMetadata;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;

public final class LegacyBoundaryDefender {

    private LegacyBoundaryDefender() { throw new AssertionError(); }

    public static int requireClusterGroupIdValid(
            final BoundaryValidator validator,
            final int clusterGroupId
    ) {
        validator
                .requireFieldPositiveOrZero(
                        clusterGroupId,
                        LegacyCoreFieldMetadata.CLUSTER_GROUP_ID
                )
                .requireFieldDigitCountWithin(
                        clusterGroupId,
                        ClusterPhysicsMetadata.SystemCluster.ID_MIN_DIGIT,
                        ClusterPhysicsMetadata.SystemCluster.ID_MAX_DIGIT,
                        LegacyCoreFieldMetadata.CLUSTER_GROUP_ID
                )
                .requireFieldInClosedRange(
                        clusterGroupId,
                        ClusterPhysicsMetadata.SystemCluster.ID_LOWER_BOUND,
                        ClusterPhysicsMetadata.SystemCluster.ID_UPPER_BOUND,
                        LegacyCoreFieldMetadata.CLUSTER_GROUP_ID
                );
        return clusterGroupId;
    }

    public static int requireSessionProcessIdValid(
            final BoundaryValidator validator,
            final int sessionProcessId
    ) {
        validator
                .requireFieldPositive(
                        sessionProcessId,
                        LegacyCoreFieldMetadata.SESSION_PROCESS_ID
                )
                .requireFieldDigitCountWithin(
                        sessionProcessId,
                        ClusterPhysicsMetadata.UserSession.ID_MIN_DIGIT,
                        ClusterPhysicsMetadata.UserSession.ID_MAX_DIGIT,
                        LegacyCoreFieldMetadata.SESSION_PROCESS_ID
                )
                .requireFieldAtMost(
                        sessionProcessId,
                        ClusterPhysicsMetadata.UserSession.ID_UPPER_BOUND,
                        LegacyCoreFieldMetadata.SESSION_PROCESS_ID
                );
        return sessionProcessId;
    }

    public static int requireIdentityProfileIdValid(
            final BoundaryValidator validator,
            final int identityProfileId
    ) {
        validator
                .requireFieldPositive(
                        identityProfileId,
                        LegacyCoreFieldMetadata.IDENTITY_PROFILE_ID
                )
                .requireFieldDigitCountWithin(
                        identityProfileId,
                        ClusterPhysicsMetadata.ClientIdentity.ID_MIN_DIGIT,
                        ClusterPhysicsMetadata.ClientIdentity.ID_MAX_DIGIT,
                        LegacyCoreFieldMetadata.IDENTITY_PROFILE_ID
                )
                .requireFieldAtMost(
                        identityProfileId,
                        ClusterPhysicsMetadata.ClientIdentity.ID_UPPER_BOUND,
                        LegacyCoreFieldMetadata.IDENTITY_PROFILE_ID
                );
        return identityProfileId;
    }

    public static int requireAssetItemIdValid(
            final BoundaryValidator validator,
            final int assetItemId
    ) {
        validator
                .requireFieldPositive(
                        assetItemId,
                        LegacyCoreFieldMetadata.ASSET_ITEM_ID
                )
                .requireFieldDigitCountWithin(
                        assetItemId,
                        ClusterPhysicsMetadata.AssetEntity.ID_MIN_DIGIT,
                        ClusterPhysicsMetadata.AssetEntity.ID_MAX_DIGIT,
                        LegacyCoreFieldMetadata.ASSET_ITEM_ID
                )
                .requireFieldFirstDigitAnyOf(
                        assetItemId,
                        ClusterPhysicsMetadata.AssetEntity.ID_ALLOWED_FIRST_DIGITS,
                        LegacyCoreFieldMetadata.ASSET_ITEM_ID
                )
                .requireFieldInAnyClosedRanges(
                        assetItemId,
                        ClusterPhysicsMetadata.AssetEntity.ID_FIRST_UNIVERSE_MIN,
                        ClusterPhysicsMetadata.AssetEntity.ID_FIRST_UNIVERSE_MAX,
                        ClusterPhysicsMetadata.AssetEntity.ID_SECOND_UNIVERSE_MIN,
                        ClusterPhysicsMetadata.AssetEntity.ID_SECOND_UNIVERSE_MAX,
                        LegacyCoreFieldMetadata.ASSET_ITEM_ID
                );
        return assetItemId;
    }

    public static int requirePremiumAssetItemIdValid(
            final BoundaryValidator validator,
            final int premiumAssetItemId
    ) {
        validator
                .requireFieldPositive(
                        premiumAssetItemId,
                        LegacyCoreFieldMetadata.PREMIUM_ASSET_ITEM_ID
                )
                .requireFieldDigitCount(
                        premiumAssetItemId,
                        ClusterPhysicsMetadata.PremiumAssetItem.ID_DIGIT_COUNT,
                        LegacyCoreFieldMetadata.PREMIUM_ASSET_ITEM_ID
                )
                .requireFieldFirstDigit(
                        premiumAssetItemId,
                        ClusterPhysicsMetadata.PremiumAssetItem.ID_FIRST_DIGIT,
                        LegacyCoreFieldMetadata.PREMIUM_ASSET_ITEM_ID
                )
                .requireFieldInClosedRange(
                        premiumAssetItemId,
                        ClusterPhysicsMetadata.PremiumAssetItem.ID_LOWER_BOUND,
                        ClusterPhysicsMetadata.PremiumAssetItem.ID_UPPER_BOUND,
                        LegacyCoreFieldMetadata.PREMIUM_ASSET_ITEM_ID
                );
        return premiumAssetItemId;
    }

}
