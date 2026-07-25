package com.dxlan.acl.features.shared.languages;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum LanguageElement implements NameDisplayable {

    ENUM("Enum"),
    RECORD("Record"),
    CLASS("Class"),
    INTERFACE("Interface"),
    IMPLEMENTATION("ImplementationClass"),
    ABSTRACTION("AbstractionClass"),
    SEALED("SealedClass");

    private final String displayName;

    private LanguageElement(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
