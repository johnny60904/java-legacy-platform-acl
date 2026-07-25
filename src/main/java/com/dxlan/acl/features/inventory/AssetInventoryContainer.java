package com.dxlan.acl.features.inventory;

import com.dxlan.acl.features.inventory.commands.synchronizeasset.AssetInventorySynchronizer;
import com.dxlan.acl.features.inventory.commands.synchronizeasset.LegacyPlatformInventorySynchronizer;
import com.dxlan.acl.features.inventory.commands.synchronizeasset.SynchronizeAssetInventoryHandler;
import com.dxlan.acl.features.infrastructure.log.AclLogger;

public final class AssetInventoryContainer {

    private final SynchronizeAssetInventoryHandler handler;

    private AssetInventoryContainer() {
        AssetInventorySynchronizer assetInventorySynchronizer =
                LegacyPlatformInventorySynchronizer.getInstance();
        this.handler = SynchronizeAssetInventoryHandler.of(assetInventorySynchronizer);
    }

    private static class Holder {
        private static final AssetInventoryContainer INSTANCE =
                new AssetInventoryContainer();
    }

    public static AssetInventoryContainer getInstance() {
        return Holder.INSTANCE;
    }

    public static void initialize() {
        AclLogger.info(
                AssetInventoryContainer.class,
                "Initializing AssetInventoryContainer..."
        );
        if (getInstance() != null) {
            AclLogger.info(
                    AssetInventoryContainer.class,
                    "AssetInventoryContainer initialized successfully."
            );
        }
    }

    public SynchronizeAssetInventoryHandler getSynchronizeAssetInventoryHandler() {
        return handler;
    }

}
