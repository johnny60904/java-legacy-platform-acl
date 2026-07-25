package com.dxlan.acl.features.shared.architectures;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum ArchitecturalParadigms implements NameDisplayable {

    NONE("None"),
    NOT_APPLICABLE("NotApplicable"),
    DDD("Domain-DrivenDesign"),
    TRANSACTION_SCRIPT("TransactionScript/CRUD");

    private final String displayName;

    private ArchitecturalParadigms(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
