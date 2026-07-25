package com.dxlan.acl.features.shared.topology;

import com.dxlan.acl.features.shared.topology.constraints.*;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;

public final class ClusterParameterGuard {

    private ClusterParameterGuard() { throw new AssertionError(); }

    public static int requireClusterGroupIdValid(
            final int clusterGroupId,
            final String name
    ) {
        ClusterGroupIdValidityConstraint.Definition definition =
                ClusterGroupIdValidityConstraint.of(name);
        ValidationConstraint<Integer> constraint = definition.create(clusterGroupId);
        if (!constraint.isSatisfiedBy(clusterGroupId)) {
            throw new IllegalArgumentException(
                    definition.requirementDescription() +
                    ", but was: " + clusterGroupId + "."
            );
        }
        return clusterGroupId;
    }

    public static int requireClusterGroupIdValid(
            final int clusterGroupId
    ) {
        return requireClusterGroupIdValid(
                clusterGroupId,
                ClusterPhysicsMetadata.SystemCluster.ID_DEFAULT_NAME
        );
    }

    public static int requireSessionProcessIdValid(
            final int sessionProcessId,
            final String name
    ) {
        SessionProcessIdValidityConstraint.Definition definition =
                SessionProcessIdValidityConstraint.of(name);
        ValidationConstraint<Integer> constraint = definition.create(sessionProcessId);
        if (!constraint.isSatisfiedBy(sessionProcessId)) {
            throw new IllegalArgumentException(
                    definition.requirementDescription() +
                    ", but was: " + sessionProcessId + "."
            );
        }
        return sessionProcessId;
    }

    public static int requireSessionProcessIdValid(
            final int sessionProcessId
    ) {
        return requireSessionProcessIdValid(
                sessionProcessId,
                ClusterPhysicsMetadata.UserSession.ID_DEFAULT_NAME
        );
    }

    public static int requireAssetItemIdValid(
            final int assetItemId,
            final String name
    ) {
        AssetItemIdValidityConstraint.Definition definition =
                AssetItemIdValidityConstraint.of(name);
        ValidationConstraint<Integer> constraint = definition.create(assetItemId);
        if (!constraint.isSatisfiedBy(assetItemId)) {
            throw new IllegalArgumentException(
                    definition.requirementDescription() +
                    ", but was: " + assetItemId + "."
            );
        }
        return assetItemId;
    }

    public static int requireAssetItemIdValid(
            final int assetItemId
    ) {
        return requireAssetItemIdValid(
                assetItemId,
                ClusterPhysicsMetadata.AssetEntity.ID_DEFAULT_NAME
        );
    }

    public static int requirePremiumAssetItemIdValid(
            final int premiumAssetItemId,
            final String name
    ) {
        PremiumAssetIdValidityConstraint.Definition definition =
                PremiumAssetIdValidityConstraint.of(name);
        ValidationConstraint<Integer> constraint = definition.create(premiumAssetItemId);
        if (!constraint.isSatisfiedBy(premiumAssetItemId)) {
            throw new IllegalArgumentException(
                    definition.requirementDescription() +
                    ", but was: " + premiumAssetItemId + "."
            );
        }
        return premiumAssetItemId;
    }

    public static int requirePremiumAssetItemIdValid(
            final int premiumAssetItemId
    ) {
        return requirePremiumAssetItemIdValid(
                premiumAssetItemId,
                ClusterPhysicsMetadata.PremiumAssetItem.ID_DEFAULT_NAME
        );
    }

    public static int requireStorageSlotValid(
            final int storageSlot,
            final String name
    ) {
        StorageSlotValidityConstraint.Definition definition =
                StorageSlotValidityConstraint.of(name);
        ValidationConstraint<Integer> constraint = definition.create(storageSlot);
        if (!constraint.isSatisfiedBy(storageSlot)) {
            throw new IllegalArgumentException(
                    definition.requirementDescription() +
                    ", but was: " + storageSlot + "."
            );
        }
        return storageSlot;
    }

    public static int requireStorageSlotValid(
            final int storageSlot
    ) {
        return requireStorageSlotValid(
                storageSlot,
                ClusterPhysicsMetadata.DigitalInventory.SLOT_DEFAULT_NAME
        );
    }

    public static int requireActiveAssetIndexValid(
            final int activeAssetIndex,
            final String name
    ) {
        ActiveAssetIndexValidityConstraint.Definition definition =
                ActiveAssetIndexValidityConstraint.of(name);
        ValidationConstraint<Integer> constraint = definition.create(activeAssetIndex);
        if (!constraint.isSatisfiedBy(activeAssetIndex)) {
            throw new IllegalArgumentException(
                    definition.requirementDescription() +
                    ", but was: " + activeAssetIndex + "."
            );
        }
        return activeAssetIndex;
    }

    public static int requireActiveAssetIndexValid(
            final int activeAssetIndex
    ) {
        return requireActiveAssetIndexValid(
                activeAssetIndex,
                ClusterPhysicsMetadata.ActiveAsset.INDEX_DEFAULT_NAME
        );
    }

}
