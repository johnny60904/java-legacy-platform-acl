package com.dxlan.acl.features.shared.text;

import java.util.Objects;

public final class StringComposer {

    private StringComposer() {
        throw new AssertionError();
    }

    public static String repeat(
            final String text,
            final int count
    ) {
        TextValidator.validateHasText(text);
        if (count <= 0) return "";
        return text.repeat(count);
    }

    public static String repeatWithNewLine(
            final String text,
            final int count,
            final WrapPosition wrapPosition
    ) {
        TextValidator.validateHasText(text);
        Objects.requireNonNull(wrapPosition, "WrapPosition must be specified.");
        String result = repeat(text, count);
        return switch(wrapPosition) {
            case START -> "\n" + result;
            case END -> result + "\n";
            case BOTH -> "\n" + result + "\n";
            default -> result;
        };
    }

    public static String combine(
            final String left,
            final String right
    ) {
        TextValidator.validateHasText(left, "Left");
        if (right == null || right.isBlank()) return left;
        return left.concat(right);
    }

}
