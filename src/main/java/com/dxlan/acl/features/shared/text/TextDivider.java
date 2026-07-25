package com.dxlan.acl.features.shared.text;

public enum TextDivider {

    STRONG("==========================="),
    LIGHT("---------------------"),
    STRONG_START("============= Before ============="),
    STRONG_END("============= After ============="),
    LIGHT_START("------------ Before ------------"),
    LIGHT_END("------------ After ------------");

    private final String text;

    private TextDivider(
            final String text
    ) {
        this.text = text;
    }

    public String getText() { return this.text; }

}