package net.legacy.platform.core.model;

public final class ActiveAsset {

    private String name = "ActiveAsset";
    private final PremiumAssetItem premiumAssetItem = new PremiumAssetItem();

    public ActiveAsset() {}

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public PremiumAssetItem getPremiumAssetItem() { return premiumAssetItem; }
}
