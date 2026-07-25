package com.dxlan.acl.premiumasset.application.common.enums;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public enum PremiumAssetCommonParameter implements ValidationParameter {

    TIME_ANCHOR("TimeAnchor"),
    TIME_ZONE("TimeZone"),
    CLUSTER_GROUP_ID("ClusterGroupId"),
    SESSION_PROCESS_ID("SessionProcessId"),
    PREMIUM_ASSET_ITEM_ID("PremiumAssetItemId"),
    ACTIVE_ASSET_INDEX("ActiveAssetIndex"),
    STORAGE_SLOT("StorageSlot");

    private final String displayName;

    private PremiumAssetCommonParameter(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }
}
