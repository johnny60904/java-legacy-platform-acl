package com.dxlan.acl.premiumasset.domain.enums;

public enum PremiumAssetType {

    TIMED("Timed", "TimedPremiumAsset"),
    PERMANENT("Permanent", "PermanentPremiumAsset");

    private final String displayName;
    private final String description;

    private PremiumAssetType(
            final String displayName,
            final String description
    ) {
        this.displayName = displayName;
        this.description = description;
    }

    public String displayName() {
        return displayName;
    }

    public String description() {
        return description;
    }
}
