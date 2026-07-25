package com.dxlan.acl.features.shared.validations.taxonomy;

import com.dxlan.acl.features.shared.exceptions.ErrorCategory;

public enum ValidationCategory implements ErrorCategory {

    /// layerCode 3 -> application

    MISSING_FIELD(
            "MissingInput",
            1,
            3
    ),
    INVALID_FORMAT(
            "InvalidFormat",
            2,
            3
    ),
    OUT_OF_RANGE(
            "OutOfRange",
            3,
            3
    ),
    DISALLOWED_VALUE(
            "DisallowedValue",
            4,
            3
    ),
    PAYLOAD_DATA_CORRUPTED(
            "PayloadDataCorrupted",
            5,
            3
    ),
    REQUEST_TARGET_ABSENT(
            "RequestTargetAbsent",
            6,
            3
    );

    private final String codeName;
    private final int categoryCode;
    private final int layerCode;

    private ValidationCategory(
            final String codeName,
            final int categoryCode,
            final int layerCode
    ) {
        this.codeName = codeName;
        this.categoryCode = categoryCode;
        this.layerCode = layerCode;
    }

    @Override
    public String codeName() { return codeName; }

    @Override
    public int categoryCode() { return categoryCode; }

    @Override
    public int layerCode() { return layerCode; }

}