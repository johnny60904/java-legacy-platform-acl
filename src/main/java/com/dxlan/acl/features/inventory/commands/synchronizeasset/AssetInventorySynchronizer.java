package com.dxlan.acl.features.inventory.commands.synchronizeasset;

import com.dxlan.acl.features.inventory.common.enums.AssetInventoryType;
import com.dxlan.acl.features.inventory.common.metadata.SynchronizeAssetSliceMetadata;

public interface AssetInventorySynchronizer {

    void synchronizeToClientByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            AssetInventoryType assetInventoryType
    );

    void synchronizeToClientByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            AssetInventoryType assetInventoryType
    );

    public static SynchronizeAssetSliceMetadata getMetadata() {
        return AssetInventorySynchronizerMetadata.getInstance();
    }

}
