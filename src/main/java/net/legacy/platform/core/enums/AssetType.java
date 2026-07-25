package net.legacy.platform.core.enums;

public enum AssetType {

    ACTIVE_DEPLOYED(-1),
    STANDARD_ASSET(1),
    CONSUMABLE(2),
    BASE_CONFIGURATION(4),
    INSTALLATION_KIT(3),
    PREMIUM_SERVICE(5),
    COSMETIC_EXTENSION(6);

    private final byte typeCode;

    private AssetType(
            final int typeCode
    ) {
        this.typeCode = (byte) typeCode;
    }

    public byte getTypeCode() {
        return typeCode;
    }

}
