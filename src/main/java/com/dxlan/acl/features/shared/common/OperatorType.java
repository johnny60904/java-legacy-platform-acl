package com.dxlan.acl.features.shared.common;

public enum OperatorType implements NameDisplayable {

    EQUALS("Equals"),
    NOT_EQUALS("NotEquals"),
    LESS_THAN("LessThan"),
    LESS_OR_EQUAL("lessOrEqual"),
    GREATER_THAN("GreaterThan"),
    GREATER_OR_EQUAL("GreaterThanOrEqual"),
    ANY_OF("AnyOf"),
    NOT_IN("NotIn"),
    WITHIN("Within"),
    WITHIN_ANY_OF_RANGES("WithinAnyOfRanges"),
    BETWEEN("Between"), /// open
    BETWEEN_LEFT_CLOSED_RIGHT_OPEN("BetweenLeftClosedRightOpen"),
    BETWEEN_LEFT_OPEN_RIGHT_CLOSED("BetweenLeftOpenRightClosed"),
    BETWEEN_ANY_OF_RANGES("BetweenAnyOfRanges"),
    NOT_BETWEEN("NotBetween"),
    CONTAINS("Contains"),
    NOT_CONTAINS("NotContains");

    private final String displayName;

    private OperatorType(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
