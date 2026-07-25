package net.legacy.platform.core.registry;

public final class AssetRegistry {

    private AssetRegistry() {
        throw new AssertionError();
    }

    public static PremiumAssetMetadata getAssetMetadataById(
            final int assetItemid
    ) {
        return new PremiumAssetMetadata();
    }

}
