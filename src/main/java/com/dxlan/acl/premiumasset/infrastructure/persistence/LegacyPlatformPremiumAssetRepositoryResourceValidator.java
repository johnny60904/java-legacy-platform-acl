package com.dxlan.acl.premiumasset.infrastructure.persistence;

import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreType;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.ResourceAbsentCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.*;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;
import net.legacy.platform.core.model.ActiveAsset;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.PremiumAssetMetadata;

public final class LegacyPlatformPremiumAssetRepositoryResourceValidator {

    private LegacyPlatformPremiumAssetRepositoryResourceValidator() { throw new AssertionError(); }

    private static enum Target implements ViolationTarget {
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
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            ResourceAbsentCause.of(
                                    Target.ACTIVE_ASSET,
                                    "Null",
                                    LegacyCoreType.SOURCE.displayName()
                            )
                    ),
                    PremiumAssetRepository.Invariant.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                    callerClass
            );
        }
    }

    public static void validateLegacyPremiumAssetItemNotNull(
            final PremiumAssetItem legacyPremiumAssetItem,
            final Class<?> callerClass
    ) {
        if (legacyPremiumAssetItem == null) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            ResourceAbsentCause.of(
                                    Target.PREMIUM_ASSET_ITEM,
                                    "Null",
                                    LegacyCoreType.SOURCE.displayName()
                            )
                    ),
                    PremiumAssetRepository.Invariant.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                    callerClass
            );
        }
    }

    public static void validateLegacyPremiumAssetMetadataNotNull(
            final PremiumAssetMetadata legacyPremiumAssetMetadata,
            final Class<?> callerClass
    ) {
        if (legacyPremiumAssetMetadata == null) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            ResourceAbsentCause.of(
                                    Target.PREMIUM_ASSET_METADATA,
                                    "Null",
                                    LegacyCoreType.SOURCE.displayName()
                            )
                    ),
                    PremiumAssetRepository.Invariant.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                    callerClass
            );
        }
    }

}
