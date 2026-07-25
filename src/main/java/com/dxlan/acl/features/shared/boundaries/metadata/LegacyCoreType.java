package com.dxlan.acl.features.shared.boundaries.metadata;

public enum LegacyCoreType {

    SOURCE("LegacyPlatform"),
    ASSET_ENTITY("AssetEntity"),
    ACTIVE_ASSET("ActiveAsset"),
    PREMIUM_ASSET_ITEM("PremiumAssetItem"),
    ASSET_METADATA("AssetMetadata"),
    HARDWARE_ASSET("HardwareAsset"),
    USER_SESSION("UserSession"),
    CLIENT_IDENTITY("ClientIdentity"),
    CLIENT_CONNECTION("ClientConnection"),
    SYSTEM_CLUSTER("SystemCluster"),
    NETWORK_PAYLOAD("NetworkPayload"),
    INBOUND_PAYLOAD("InboundPayload"),
    OUTBOUND_PAYLOAD("OutboundPayload");

    private final String displayName;

    private LegacyCoreType(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

}
