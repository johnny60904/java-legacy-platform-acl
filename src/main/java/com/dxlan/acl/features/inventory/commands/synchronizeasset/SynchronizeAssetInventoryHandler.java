package com.dxlan.acl.features.inventory.commands.synchronizeasset;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;

public final class SynchronizeAssetInventoryHandler {

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(SynchronizeAssetInventoryHandler.class);

    private final AssetInventorySynchronizer assetInventorySynchronizer;

    private SynchronizeAssetInventoryHandler(
            final AssetInventorySynchronizer assetInventorySynchronizer
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                assetInventorySynchronizer,
                AssetInventorySynchronizer.getMetadata()
        );
        this.assetInventorySynchronizer = assetInventorySynchronizer;
    }

    public void handle(
            final SynchronizeAssetInventoryByIdCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                SynchronizeAssetInventoryByIdCommand.getMetadata()
        );
        assetInventorySynchronizer.synchronizeToClientByItemId(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.assetItemId(),
                command.assetInventoryType()
        );
    }

    public void handle(
            final SynchronizeAssetInventoryBySlotCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                SynchronizeAssetInventoryBySlotCommand.getMetadata()
        );
        assetInventorySynchronizer.synchronizeToClientByStorageSlot(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.storageSlot(),
                command.assetInventoryType()
        );
    }

    public static SynchronizeAssetInventoryHandler of(
            final AssetInventorySynchronizer assetInventorySynchronizer
    ) {
        return new SynchronizeAssetInventoryHandler(
                assetInventorySynchronizer
        );
    }

}
