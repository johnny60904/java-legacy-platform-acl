package com.dxlan.acl.features.inventory.commands.synchronizeasset;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public enum SynchronizeAssetSliceCommonField implements ValidationParameter {

    CLUSTER_GROUP_ID("ClusterGroupId"),
    SESSION_PROCESS_ID("SessionProcessId"),
    STORAGE_SLOT("StorageSlot"),
    ASSET_ITEM_ID("AssetItemId"),
    ASSET_INVENTORY_TYPE_TOKEN("AssetInventoryTypeToken");

    private final String displayName;

    private SynchronizeAssetSliceCommonField(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }

}
