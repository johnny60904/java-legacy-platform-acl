package com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief;

import com.dxlan.acl.features.shared.domain.integrities.DomaInIntegrityGuard;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.integrities.DomainInvariant;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.MissingRequiredCause;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.OutOfBoundsCause;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.UnacceptableValueCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.shared.domain.specifications.DomainConceptRequirementSpec;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetCommonConcept;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.contexts.PremiumAssetBriefValidationContext;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.specifications.*;

public enum PremiumAssetBriefIntegrityGuard implements
        DomaInIntegrityGuard<PremiumAssetBriefValidationContext> {

    INSTANCE;

    /// PremiumAssetModuleMetadata.CORE
    public static enum Invariant implements DomainInvariant {
        /// MissingRequiredCause
        ASSET_NAME_MUST_NOT_BE_BLANK(
                "AssetNameMustNotBeBlank",
                6
        ),
        /// MissingRequiredCause
        PREMIUM_ASSET_TYPE_MUST_BE_PRESENT(
                "PremiumAssetTypeMustBePresent",
                7
        ),
        /// OutOfBoundsCause
        ASSET_ITEM_ID_MUST_BE_IN_BOUNDS(
                "AssetItemIdMustBeInBounds",
                3
        ),
        /// OutOfBoundsCause
        SESSION_PROCESS_ID_MUST_BE_IN_BOUNDS(
                "SessionProcessIdMustBeInBounds",
                4
        ),
        /// UnacceptableValueCause
        LIFESPAN_MUST_BE_VALID(
                "LifespanMustBeValid",
                5
        ),
        /// UnacceptableValueCause
        CLUSTER_GROUP_ID_MUST_BE_VALID(
                "ClusterGroupIdMustBeValid",
                6
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

    private static void guardAssetNameHasText(
            final String assetName,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        PremiumAssetNamePresenceSpec.Definition definition =
                PremiumAssetNamePresenceSpec.of(concept);
        Specification<String> specification = definition.create(assetName);
        if (!specification.isSatisfiedBy(assetName)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.of(concept, assetName)
                    ),
                    Invariant.ASSET_NAME_MUST_NOT_BE_BLANK,
                    modelClass
            );
        }
    }

    private static void guardAssetItemIdInBounds(
            final int assetItemId,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        PremiumAssetItemIdBoundsSpec.Definition definition =
                PremiumAssetItemIdBoundsSpec.of(concept);
        Specification<Integer> specification = definition.create(assetItemId);
        if (!specification.isSatisfiedBy(assetItemId)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            OutOfBoundsCause.of(
                                    concept,
                                    String.valueOf(assetItemId),
                                    PremiumAssetCommonConcept.PREMIUM_ASSET_BRIEF.displayName(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.ASSET_ITEM_ID_MUST_BE_IN_BOUNDS,
                    modelClass
            );
        }
    }

    private static void guardLifespanAccepted(
            final long lifespan,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        LifespanDiscreteRangeSpec.Definition definition =
                LifespanDiscreteRangeSpec.of(concept);
        Specification<Long> specification = definition.create(lifespan);
        if (!specification.isSatisfiedBy(lifespan)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnacceptableValueCause.of(
                                    concept,
                                    String.valueOf(lifespan),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.LIFESPAN_MUST_BE_VALID,
                    modelClass
            );
        }
    }

    private static void guardClusterGroupIdAccepted(
            final int clusterGroupId,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        ClusterGroupIdDiscreteRangeSpec.Definition definition =
                ClusterGroupIdDiscreteRangeSpec.of(concept);
        Specification<Integer> specification = definition.create(clusterGroupId);
        if (!specification.isSatisfiedBy(clusterGroupId)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnacceptableValueCause.of(
                                    concept,
                                    String.valueOf(clusterGroupId),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.CLUSTER_GROUP_ID_MUST_BE_VALID,
                    modelClass
            );
        }
    }

    private static void guardSessionProcessIdInBounds(
            final int sessionProcessId,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        SessionProcessIdBoundsSpec.Definition definition =
                SessionProcessIdBoundsSpec.of(concept);
        Specification<Integer> specification = definition.create(sessionProcessId);
        if (!specification.isSatisfiedBy(sessionProcessId)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            OutOfBoundsCause.of(
                                    concept,
                                    String.valueOf(sessionProcessId),
                                    PremiumAssetCommonConcept.PREMIUM_ASSET_BRIEF.displayName(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.SESSION_PROCESS_ID_MUST_BE_IN_BOUNDS,
                    modelClass
            );
        }
    }

    private static void guardPremiumAssetTypePresent(
            final PremiumAssetType premiumAssetType,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<PremiumAssetType> specification =
                DomainConceptRequirementSpec.create(
                        premiumAssetType,
                        concept
                );
        if (!specification.isSatisfiedBy(premiumAssetType)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(concept)
                    ),
                    Invariant.PREMIUM_ASSET_TYPE_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    @Override
    public void guardRules(
            final PremiumAssetBriefValidationContext context,
            final Class<?> modelClass
    ) {
        guardAssetNameHasText(
                context.assetNameProperty().value(),
                context.assetNameProperty().concept(),
                modelClass
        );
        guardPremiumAssetTypePresent(
                context.premiumAssetTypeProperty().value(),
                context.premiumAssetTypeProperty().concept(),
                modelClass
        );
        guardAssetItemIdInBounds(
                context.assetItemIdProperty().value(),
                context.assetItemIdProperty().concept(),
                modelClass
        );
        guardLifespanAccepted(
                context.lifespanProperty().value(),
                context.lifespanProperty().concept(),
                modelClass
        );
        guardClusterGroupIdAccepted(
                context.clusterGroupIdProperty().value(),
                context.clusterGroupIdProperty().concept(),
                modelClass
        );
        guardSessionProcessIdInBounds(
                context.sessionProcessIdProperty().value(),
                context.sessionProcessIdProperty().concept(),
                modelClass
        );
    }

}
