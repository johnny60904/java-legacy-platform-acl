package com.dxlan.acl.features.shared.boundaries.metadata;

import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;

public enum LegacyCoreTypeMetadata implements
        ExternalMetadata, ViolationTarget, ValidationTarget {

    ASSET(LegacyCoreType.ASSET_ENTITY),
    ACTIVE_ASSET(LegacyCoreType.ACTIVE_ASSET),
    PREMIUM_ASSET_ITEM(LegacyCoreType.PREMIUM_ASSET_ITEM),
    HARDWARE_ASSET(LegacyCoreType.HARDWARE_ASSET),
    USER_SESSION(LegacyCoreType.USER_SESSION),
    CLIENT_CONNECTION(LegacyCoreType.CLIENT_CONNECTION),
    ASSET_METADATA(LegacyCoreType.ASSET_METADATA),
    SYSTEM_CLUSTER(LegacyCoreType.SYSTEM_CLUSTER),
    NETWORK_PAYLOAD(LegacyCoreType.NETWORK_PAYLOAD),
    INBOUND_PAYLOAD(LegacyCoreType.INBOUND_PAYLOAD),
    OUTBOUND_PAYLOAD(LegacyCoreType.OUTBOUND_PAYLOAD);

    private final LegacyCoreType legacyCoreType;

    private LegacyCoreTypeMetadata(
            final LegacyCoreType legacyCoreType
    ) {
        this.legacyCoreType = legacyCoreType;
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
        return legacyCoreType.displayName();
    }

}
