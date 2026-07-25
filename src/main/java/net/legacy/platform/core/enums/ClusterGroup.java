package net.legacy.platform.core.enums;

public enum ClusterGroup {

    PRIMARY_NODE_A(0),
    REDUNDANT_NODE_B(1),
    EU_REGION_NODE(30),
    STAGING_TESTBED(100);

    private final int value;

    private ClusterGroup(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
