package net.legacy.platform.core.enums;

public enum InventoryAction {

    ADD(0),
    UPDATE_QUANTITY(1),
    MOVE(2),
    REMOVE(3),
    ASSET_EXP_UPDATE(4),
    SYSTEM_RESERVED_232(5),
    UPDATE_CONTAINER_POS(6),
    UPDATE_CONTAINER_QTY(7),
    CONTAINER_REMOVE(8),
    CONTAINER_TO_CONTAINER(9),
    CONTAINER_NEW_ITEM(10),
    CONTAINER_REMOVE_SLOT(11);

    private final int code;

    private InventoryAction(final int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
