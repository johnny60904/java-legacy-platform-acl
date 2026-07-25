package com.dxlan.acl.features.shared.architectures;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum ArchitecturalScope implements NameDisplayable {

    VERTICAL_SLICE("VerticalSlice"),
    SLICE_COMMAND("SliceCommand"),
    SLICE_QUERY("SliceQuery"),
    DDD_MODULE("Domain-DrivenDesignModule"),
    SHARED_KERNEL("SharedKernel");

    private final String displayName;

    private ArchitecturalScope(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
