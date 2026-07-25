package com.dxlan.acl.features.shared.architectures;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum ArchitecturalStyle implements NameDisplayable {

    CLEAN_ARCHITECTURE("CleanArchitecture"),
    HEXAGONAL_ARCHITECTURE("HexagonalArchitecture"),
    ONION_ARCHITECTURE("OnionArchitecture"),
    VERTICAL_SLICE("VerticalSliceArchitecture"),
    SHARED_KERNEL("SharedKernel");

    private final String displayName;

    private ArchitecturalStyle(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
