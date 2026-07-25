package com.dxlan.acl.features.shared.boundaries.metadata;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public enum LegacyCoreFieldMetadata implements
        ExternalFieldMetadata, ViolationTarget, ValidationTarget {

    LIFESPAN(
            "DefaultLifespan",
            LegacyCoreType.ASSET_METADATA
    ),
    INFINITE_LIFESPAN_FLAG(
            "IsInfiniteLifespan",
            LegacyCoreType.ASSET_METADATA
    ),
    EXPIRATION_TIME(
            "ExpirationTimestamp",
            LegacyCoreType.ASSET_ENTITY
    ),
    ASSET_ITEM_ID(
            "AssetItemId",
            LegacyCoreType.ASSET_ENTITY
    ),
    PREMIUM_ASSET_ITEM_ID(
            "PremiumAssetItemId",
            LegacyCoreType.PREMIUM_ASSET_ITEM
    ),
    ACTIVE_ASSET_NAME(
            "ActiveAssetName",
            LegacyCoreType.PREMIUM_ASSET_ITEM
    ),
    TERMINATION_TIME(
            "TerminationTimestamp",
            LegacyCoreType.PREMIUM_ASSET_ITEM
    ),
    REMAINING_TIME(
            "RemainingHeartbeat",
            LegacyCoreType.PREMIUM_ASSET_ITEM
    ),
    IDENTITY_PROFILE_ID(
            "IdentityProfileId",
            LegacyCoreType.CLIENT_IDENTITY
    ),
    IDENTITY_PROFILE_NAME(
            "IdentityProfileName",
            LegacyCoreType.CLIENT_IDENTITY
    ),
    SESSION_PROCESS_ID(
            "SessionProcessId",
            LegacyCoreType.USER_SESSION
    ),
    SESSION_PROCESS_NAME(
            "SessionProcessName",
            LegacyCoreType.USER_SESSION
    ),
    CLUSTER_GROUP_ID(
            "ClusterGroupId",
            LegacyCoreType.SYSTEM_CLUSTER
    );

    private final String fieldName;
    private final LegacyCoreType legacyCoreType;

    private LegacyCoreFieldMetadata(
            final String fieldName,
            final LegacyCoreType legacyCoreType
    ) {
        this.fieldName = fieldName;
        this.legacyCoreType = legacyCoreType;
    }

    @Override
    public String fieldName() {
        return fieldName;
    }

    @Override
    public String typeName() {
        return legacyCoreType.displayName();
    }

    @Override
    public String sourceName() {
        return LegacyCoreType.SOURCE.displayName();
    }

    @Override
    public String displayName() {
        return fieldName;
    }

}
