package com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset;

import com.dxlan.acl.features.shared.domain.integrities.DomaInIntegrityGuard;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.integrities.DomainInvariant;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.MissingRequiredCause;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.NotMatchCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.shared.domain.specifications.DomainConceptRequirementSpec;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetCommonConcept;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts.PremiumAssetCommonConceptValidationContext;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.specifications.PremiumAssetTypePermanentRequirementSpec;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.specifications.PremiumAssetTypeTimedRequirementSpec;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

public enum PremiumAssetCommonIntegrityGuard implements
        DomaInIntegrityGuard<PremiumAssetCommonConceptValidationContext> {

    INSTANCE;

    /// PremiumAssetModuleMetadata.CORE
    public static enum Invariant implements DomainInvariant {
        /// MissingRequiredCause
        PREMIUM_ASSET_BRIEF_MUST_BE_PRESENT(
                "PremiumAssetBriefMustBePresent",
                8
        ),
        /// MissingRequiredCause
        PREMIUM_ASSET_EXPIRATION_MUST_BE_PRESENT(
                "PremiumAssetExpirationMustBePresent",
                9
        ),
        /// NotMatchCause
        PREMIUM_ASSET_TYPE_MUST_BE_PERMANENT(
                "PremiumAssetTypeMustBePermanent",
                1
        ),
        /// NotMatchCause
        PREMIUM_ASSET_TYPE_MUST_BE_TIMED(
                "PremiumAssetTypeMustBeTimed",
                2
        );

        private final String displayName;
        private final int serial;

        private Invariant(
                final String displayName,
                final int serial
        ) {
            this.displayName = displayName;
            this.serial = serial;
        }

        @Override
        public int serialNumber() { return serial; }

        @Override
        public String displayName() { return displayName; }
    }

    private static void guardPremiumAssetBriefPresent(
            final PremiumAssetBrief premiumAssetBrief,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<PremiumAssetBrief> specification =
                DomainConceptRequirementSpec.create(
                        premiumAssetBrief,
                        concept
                );
        if (!specification.isSatisfiedBy(premiumAssetBrief)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(concept)
                    ),
                    Invariant.PREMIUM_ASSET_BRIEF_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    private static void guardPremiumAssetExpirationPresent(
            final PremiumAssetExpiration premiumAssetExpiration,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<PremiumAssetExpiration> specification =
                DomainConceptRequirementSpec.create(
                        premiumAssetExpiration,
                        concept
                );
        if (!specification.isSatisfiedBy(premiumAssetExpiration)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(
                                    concept,
                                    " to perform the specific domain calculation"
                            )
                    ),
                    Invariant.PREMIUM_ASSET_EXPIRATION_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    @Override
    public void guardRules(
            final PremiumAssetCommonConceptValidationContext context,
            final Class<?> modelClass
    ) {
        guardPremiumAssetBriefPresent(
                context.premiumAssetBriefProperty().value(),
                context.premiumAssetBriefProperty().concept(),
                modelClass
        );
        guardPremiumAssetExpirationPresent(
                context.premiumAssetExpirationProperty().value(),
                context.premiumAssetExpirationProperty().concept(),
                modelClass
        );
    }

    public void guardPremiumAssetTypeConsistentWithPermanentPremiumAsset(
            final PremiumAssetType premiumAssetType,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        PremiumAssetTypePermanentRequirementSpec.Definition definition =
                PremiumAssetTypePermanentRequirementSpec.of(concept);
        Specification<PremiumAssetType> specification = definition.create(premiumAssetType);
        if (!specification.isSatisfiedBy(premiumAssetType)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            NotMatchCause.of(
                                    concept,
                                    premiumAssetType.displayName(),
                                    PremiumAssetCommonConcept.PERMANENT_PREMIUM_ASSET.displayName(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.PREMIUM_ASSET_TYPE_MUST_BE_PERMANENT,
                    modelClass
            );
        }
    }

    public void guardPremiumAssetTypeConsistentWithTimedPremiumAsset(
            final PremiumAssetType premiumAssetType,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        PremiumAssetTypeTimedRequirementSpec.Definition definition =
                PremiumAssetTypeTimedRequirementSpec.of(concept);
        Specification<PremiumAssetType> specification = definition.create(premiumAssetType);
        if (!specification.isSatisfiedBy(premiumAssetType)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            NotMatchCause.of(
                                    concept,
                                    premiumAssetType.displayName(),
                                    PremiumAssetCommonConcept.TIMED_PREMIUM_ASSET.displayName(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.PREMIUM_ASSET_TYPE_MUST_BE_TIMED,
                    modelClass
            );
        }
    }

}
