package com.dxlan.acl.features.inventory;

import com.dxlan.acl.features.inventory.commands.synchronizeasset.SynchronizeAssetInventoryByIdCommand;
import com.dxlan.acl.features.inventory.commands.synchronizeasset.SynchronizeAssetInventoryBySlotCommand;

public final class AssetInventory {

    private AssetInventory() {}

    public static void synchronizeByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final String assetInventoryTypeToken
    ) {
        AssetInventoryContainer.getInstance()
                .getSynchronizeAssetInventoryHandler()
                .handle(
                        SynchronizeAssetInventoryByIdCommand.of(
                                clusterGroupId,
                                sessionProcessId,
                                assetItemId,
                                assetInventoryTypeToken
                        )
                );
    }

    public static void synchronizeByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final String assetInventoryTypeToken
    ) {
        AssetInventoryContainer.getInstance()
                .getSynchronizeAssetInventoryHandler()
                .handle(
                        SynchronizeAssetInventoryBySlotCommand.of(
                                clusterGroupId,
                                sessionProcessId,
                                storageSlot,
                                assetInventoryTypeToken
                        )
                );
    }

}
