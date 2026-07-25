package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreType;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.DataCorruptedCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.shared.topology.constraints.PremiumAssetIdValidityConstraint;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.PremiumAssetMetadata;

public final class PremiumAssetBriefMapper {

    private static String basePrefix(
            Class<?> callerClass
    ) {
        return "Data corruption at ACL boundary [" + callerClass + "].";
    }

    private static final String SOURCE_NAME =
            LegacyCoreType.SOURCE.displayName();

    private PremiumAssetBriefMapper() { throw new AssertionError(); }

    private static int requirePremiumAssetItemIdValid(
            final int assetItemId,
            final Class<?> callerClass
    ) {
        PremiumAssetIdValidityConstraint.Definition definition =
                PremiumAssetIdValidityConstraint.ofDefault();
        ValidationConstraint<Integer> constraint = definition.create(assetItemId);
        if (!constraint.isSatisfiedBy(assetItemId)) {
            String context = basePrefix(callerClass) +
                    " Illegal identifier received from " + SOURCE_NAME + " context.";
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            DataCorruptedCause.of(
                                    PremiumAssetBrief.Concept.ASSET_ITEM_ID,
                                    String.valueOf(assetItemId),
                                    definition.requirementDescription(),
                                    context
                            )
                    ),
                    PremiumAssetRepository.Invariant.PREMIUM_ASSET_IDENTITY_MUST_BE_VALID,
                    callerClass
            );
        }
        return assetItemId;
    }

    private static String requirePremiumAssetNameHasText(
            final String assetName,
            final Class<?> callerClass
    ) {
        if (assetName == null || assetName.isBlank()) {
            String constraint = PremiumAssetBrief.Concept.ASSET_NAME.displayName() +
                    " must be specified by valid name";
            String context = basePrefix(callerClass) +
                    " Extracted premium asset " +
                    PremiumAssetBrief.Concept.ASSET_NAME.displayName() +
                    " from " + SOURCE_NAME + " context" +
                    " is absent or blank";
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            DataCorruptedCause.of(
                                    PremiumAssetBrief.Concept.ASSET_NAME,
                                    assetName,
                                    constraint,
                                    context
                            )
                    ),
                    PremiumAssetRepository.Invariant.PREMIUM_ASSET_IDENTITY_MUST_BE_VALID,
                    callerClass
            );
        }
        return assetName;
    }

    private static long requirePremiumAssetLifespanValid(
            final long lifeSpan,
            final Class<?> callerClass
    ) {
        boolean valid =
                lifeSpan >= PremiumAssetLifeCycle.PREMIUM_PERMANENT.defaultDays() &&
                lifeSpan <= PremiumAssetLifeCycle.STANDARD_SUBSCRIPTION.defaultDays();
        if (!valid) {
            String constraint = PremiumAssetBrief.Concept.LIFESPAN.displayName() +
                    " must be within closed range: " +
                    "[" + PremiumAssetLifeCycle.PREMIUM_PERMANENT.defaultDays() + ", " +
                    PremiumAssetLifeCycle.STANDARD_SUBSCRIPTION.defaultDays() + ']';
            String context = basePrefix(callerClass) +
                    " Illegal numerical duration received from " + SOURCE_NAME + " context.";
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            DataCorruptedCause.of(
                                    PremiumAssetBrief.Concept.LIFESPAN,
                                    String.valueOf(lifeSpan),
                                    constraint,
                                    context
                            )
                    ),
                    PremiumAssetRepository.Invariant.PREMIUM_ASSET_LIFETIME_MUST_BE_VALID,
                    callerClass
            );
        }
        return lifeSpan;
    }

    public static PremiumAssetBrief validateAndMap(
            final int clusterGroupId,
            final int sessionProcessId,
            final PremiumAssetItem legacyPremiumAsset,
            final PremiumAssetMetadata legacyPremiumPremiumAssetMetadata,
            final Class<?> callerClass
    ) {
        return PremiumAssetBrief.of(
                requirePremiumAssetItemIdValid(
                        legacyPremiumAsset.getAssetItemId(),
                        callerClass
                ),
                requirePremiumAssetNameHasText(
                        legacyPremiumAsset.getAssetName(),
                        callerClass
                ),
                requirePremiumAssetLifespanValid(
                        legacyPremiumPremiumAssetMetadata.getDefaultLifespan(),
                        callerClass
                ),
                clusterGroupId,
                sessionProcessId,
                PremiumAssetTypeMapper.map(
                        legacyPremiumPremiumAssetMetadata.isInfiniteLifespan()
                )
        );
    }

}
