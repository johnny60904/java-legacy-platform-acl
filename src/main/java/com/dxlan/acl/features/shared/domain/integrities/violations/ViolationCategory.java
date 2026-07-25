package com.dxlan.acl.features.shared.domain.integrities.violations;

import com.dxlan.acl.features.shared.exceptions.ErrorCategory;

public enum ViolationCategory implements ErrorCategory {

    /// layerCode 4 -> domain

    MISSING_REQUIRED(
            "MissingRequired",
            1,
            4
    ),
    OUT_OF_BOUNDS(
            "OutOfBounds",
            2,
            4
    ),
    UNACCEPTABLE_VALUE(
            "UnacceptableValue",
            3,
            4
    ),
    INCONSISTENT_VALUES(
            "InconsistentValues",
            4,
            4
    ),
    NOT_MATCH(
            "NotMatch",
            5,
            4
    ),
    INVALID_POLICY(
            "InvalidPolicy",
            6,
            4
    ),
    UNSUPPORTED_OPERATION(
            "UnsupportedOperation",
            7,
            4
    ),
    JURISDICTION_UNSUPPORTED(
            "JurisdictionUnsupported",
            8,
            4
    ),
    LEGACY_UNSUPPORTED(
            "LegacyUnsupported",
            9,
            4
    ),
    TECHNICAL_UNSUPPORTED(
            "TechnicalUnsupported",
            10,
            4
    ),
    RESOURCE_ABSENT(
            "ResourceAbsent",
            11,
            4
    ),
    DATA_CORRUPTED(
            "DataCorrupted",
            12,
            4
    );

    private final String codeName;
    private final int categoryCode;
    private final int layerCode;

    private ViolationCategory(
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
