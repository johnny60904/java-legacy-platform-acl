package net.legacy.platform.core.model;

import net.legacy.platform.core.enums.AssetType;
import net.legacy.platform.core.tme.Timestamp;

public class AssetEntity {

    private int assetItemId = 1_000_000;
    private String assetName = "Asset";
    private AssetType assetType = AssetType.BASE_CONFIGURATION;
    private int inventoryPosition = 1;
    private Timestamp expirationTimestamp = new Timestamp();

    public AssetEntity() {}

    public int getAssetItemId() { return assetItemId; }

    public String getAssetName() { return assetName; }

    public AssetType getAssetType() { return assetType; }

    public int getInventoryPosition() { return inventoryPosition; }

    public Timestamp getExpirationTimestamp() { return expirationTimestamp; }

    public void setAssetItemId(
            final int assetItemId
    ) {
        this.assetItemId = assetItemId;
    }

    public void setAssetName(
            final String assetName
    ) {
        this.assetName = assetName;
    }

    public void setAssetType(
            final AssetType assetType
    ) {
        this.assetType = assetType;
    }

    public void setInventoryPosition(
            final int inventoryPosition
    ) {
        this.inventoryPosition = inventoryPosition;
    }

    public void setExpirationTimestamp(
            final Timestamp expirationTimestamp
    ) {
        this.expirationTimestamp = expirationTimestamp;
    }
}
