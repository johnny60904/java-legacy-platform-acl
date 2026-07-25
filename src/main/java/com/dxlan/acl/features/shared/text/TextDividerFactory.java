package com.dxlan.acl.features.shared.text;

public final class TextDividerFactory {

    private TextDividerFactory() {
        throw new AssertionError();
    }

    public static String buildStrongSection(
            final String content
    ) {
        if (content == null || content.isBlank()) return "===========================";
        return "============= " + content + " =============";
    }

    public static String buildLightSection(
            final String content
    ) {
        if (content == null || content.isBlank()) return "---------------------";
        return "------------ " + content + " ------------";
    }

}
