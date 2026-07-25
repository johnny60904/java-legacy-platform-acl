package com.dxlan.acl.features.shared.arrays;

public final class ArrayLengthSecurity {

    public static final int MAXIMUM_BOUND = 100_000;

    public static final int THRESHOLD = 50_000;

    private ArrayLengthSecurity() {
        throw new AssertionError();
    }

}
