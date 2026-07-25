package com.dxlan.acl.premiumasset.infrastructure.persistence;

import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreType;
import com.dxlan.acl.features.shared.validations.causes.RequestTargetAbsentCause;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidQueryException;
import com.dxlan.acl.features.shared.validations.taxonomy.QueryValidation;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetGateway;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import net.legacy.platform.core.model.ActiveAsset;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.PremiumAssetMetadata;

public final class LegacyPlatformPremiumAssetGatewayResourceValidator {

    private LegacyPlatformPremiumAssetGatewayResourceValidator() { throw new AssertionError(); }

    private static enum Target implements ValidationTarget {
        ACTIVE_ASSET("ActiveAsset"),
        PREMIUM_ASSET_ITEM("PremiumAssetItem"),
        PREMIUM_ASSET_METADATA("PremiumAssetMetadata");

        private final String displayName;

        private Target(
                final String displayName
        ) {
            this.displayName = displayName;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

    public static void validateLegacyActiveAssetNotNull(
            final ActiveAsset legacyActiveAsset,
            final Class<?> callerClass
    ) {
        if (legacyActiveAsset == null) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            RequestTargetAbsentCause.of(
                                    Target.ACTIVE_ASSET,
                                    "Null",
                                    LegacyCoreType.SOURCE.displayName()
                            )
                    ),
                    PremiumAssetGateway.Clause.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                    callerClass
            );
        }
    }

    public static void validateLegacyPremiumAssetItemNotNull(
            final PremiumAssetItem legacyPremiumAssetItem,
            final Class<?> callerClass
    ) {
        if (legacyPremiumAssetItem == null) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            RequestTargetAbsentCause.of(
                                    Target.PREMIUM_ASSET_ITEM,
                                    "Null",
                                    LegacyCoreType.SOURCE.displayName()
                            )
                    ),
                    PremiumAssetGateway.Clause.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                    callerClass
            );
        }
    }

    public static void validateLegacyPremiumAssetMetadataNotNull(
            final PremiumAssetMetadata legacyPremiumAssetMetadata,
            final Class<?> callerClass
    ) {
        if (legacyPremiumAssetMetadata == null) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            RequestTargetAbsentCause.of(
                                    Target.PREMIUM_ASSET_METADATA,
                                    "Null",
                                    LegacyCoreType.SOURCE.displayName()
                            )
                    ),
                    PremiumAssetGateway.Clause.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                    callerClass
            );
        }
    }

}
