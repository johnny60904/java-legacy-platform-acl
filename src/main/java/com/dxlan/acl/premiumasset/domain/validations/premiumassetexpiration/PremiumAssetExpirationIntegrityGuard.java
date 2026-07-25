package com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration;

import com.dxlan.acl.features.shared.domain.integrities.DomaInIntegrityGuard;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.features.shared.domain.integrities.DomainInvariant;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.InconsistentValuesCause;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.MissingRequiredCause;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.OutOfBoundsCause;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.UnacceptableValueCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.shared.domain.specifications.DomainConceptRequirementSpec;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationState;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetCommonConcept;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.conditions.ExpirationEqualityCondition;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.contexts.PremiumAssetExpirationValidationContext;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.specifications.*;

import java.time.Instant;
import java.time.ZoneId;

public enum PremiumAssetExpirationIntegrityGuard implements
        DomaInIntegrityGuard<PremiumAssetExpirationValidationContext> {

    INSTANCE;

    /// PremiumAssetModuleMetadata.CORE
    public static enum Invariant implements DomainInvariant {
        /// MissingRequiredCause
        TIME_ANCHOR_MUST_BE_PRESENT(
                "TimeAnchorMustBePresent",
                1
        ),
        /// MissingRequiredCause
        TIME_ZONE_MUST_BE_PRESENT(
                "TimeZoneMustBePresent",
                2
        ),
        /// MissingRequiredCause
        EXPIRATION_TIMESTAMP_MUST_BE_PRESENT(
                "ExpirationTimestampMustBePresent",
                3
        ),
        /// MissingRequiredCause
        TERMINATION_TIMESTAMP_MUST_BE_PRESENT(
                "TerminationTimestampMustBePresent",
                4
        ),
        /// MissingRequiredCause
        EXPIRATION_STATE_MUST_BE_PRESENT(
                "ExpirationStateMustBePresent",
                5
        ),
        /// UnacceptableValueCause
        EXPIRATION_TIMESTAMP_MUST_BE_RECONCILED(
                "ExpirationTimestampMustBeReconciled",
                1
        ),
        /// UnacceptableValueCause
        TERMINATION_TIMESTAMP_MUST_BE_RECONCILED(
                "TerminationTimestampMustBeReconciled",
                2
        ),
        /// UnacceptableValueCause
        EXPIRATION_TIMESTAMP_MUST_BE_UNRECONCILED(
                "ExpirationTimestampMustBeUnreconciled",
                3
        ),
        /// UnacceptableValueCause
        TERMINATION_TIMESTAMP_MUST_BE_UNRECONCILED(
                "TerminationTimestampMustBeUnreconciled",
                4
        ),
        /// OutOfBoundsCause
        REMAINING_HEARTBEAT_MUST_BE_RECONCILED(
                "RemainingHeartbeatMustBeReconciled",
                1
        ),
        /// OutOfBoundsCause
        REMAINING_HEARTBEAT_MUST_BE_UNRECONCILED(
                "RemainingHeartbeatMustBeUnreconciled",
                2
        ),
        /// InconsistentValuesCause
        EXPIRATION_TIMESTAMP_MUST_BE_CONSISTENT_WITH_TERMINATION_TIMESTAMP(
                "ExpirationTimestampMustBeConsistentWithTerminationTimestamp",
                1
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

    private static void guardTimeAnchorPresent(
            final Instant timeAnchor,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<Instant> specification =
                DomainConceptRequirementSpec.create(
                        timeAnchor,
                        concept
                );
        if (!specification.isSatisfiedBy(timeAnchor)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(concept)
                    ),
                    Invariant.TIME_ANCHOR_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    private static void guardTimeZonePresent(
            final ZoneId timeZone,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<ZoneId> specification =
                DomainConceptRequirementSpec.create(
                        timeZone,
                        concept
                );
        if (!specification.isSatisfiedBy(timeZone)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(concept)
                    ),
                    Invariant.TIME_ZONE_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    private static void guardExpirationTimestampPresent(
            final Instant expirationTimestamp,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<Instant> specification =
                DomainConceptRequirementSpec.create(
                        expirationTimestamp,
                        concept
                );
        if (!specification.isSatisfiedBy(expirationTimestamp)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(concept)
                    ),
                    Invariant.EXPIRATION_TIMESTAMP_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    private static void guardTerminationTimestampPresent(
            final Instant terminationTimestamp,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<Instant> specification =
                DomainConceptRequirementSpec.create(
                        terminationTimestamp,
                        concept
                );
        if (!specification.isSatisfiedBy(terminationTimestamp)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(concept)
                    ),
                    Invariant.TERMINATION_TIMESTAMP_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    private static void guardExpirationStatePresent(
            final ExpirationState expirationState,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<ExpirationState> specification =
                DomainConceptRequirementSpec.create(
                        expirationState,
                        concept
                );
        if (!specification.isSatisfiedBy(expirationState)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(
                                    concept,
                                    " must be specified to" +
                                            " determine the specific domain concept's validation range."
                            )
                    ),
                    Invariant.EXPIRATION_STATE_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    private static void guardExpirationTimestampReconciled(
            final Instant timeAnchor,
            final Instant expirationTimestamp,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        ReconciledExpirationValiditySpec.Definition definition =
                ReconciledExpirationValiditySpec.of(timeAnchor, concept);
        Specification<Instant> specification =
                definition.create(expirationTimestamp);
        if (!specification.isSatisfiedBy(expirationTimestamp)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnacceptableValueCause.of(
                                    concept,
                                    expirationTimestamp.toString(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.EXPIRATION_TIMESTAMP_MUST_BE_RECONCILED,
                    modelClass
            );
        }
    }

    private static void guardTerminationTimestampReconciled(
            final Instant timeAnchor,
            final Instant terminationTimestamp,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        ReconciledExpirationValiditySpec.Definition definition =
                ReconciledExpirationValiditySpec.of(timeAnchor, concept);
        Specification<Instant> specification =
                definition.create(terminationTimestamp);
        if (!specification.isSatisfiedBy(terminationTimestamp)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnacceptableValueCause.of(
                                    concept,
                                    terminationTimestamp.toString(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.TERMINATION_TIMESTAMP_MUST_BE_RECONCILED,
                    modelClass
            );
        }
    }

    private static void guardRemainingHeartbeatReconciled(
            final Instant timeAnchor,
            final long remainingHeartbeat,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        ReconciledRemainingHeartbeatValiditySpec.Definition definition =
                ReconciledRemainingHeartbeatValiditySpec.of(timeAnchor, concept);
        Specification<Long> specification =
                definition.create(remainingHeartbeat);
        if (!specification.isSatisfiedBy(remainingHeartbeat)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            OutOfBoundsCause.of(
                                    concept,
                                    String.valueOf(remainingHeartbeat),
                                    PremiumAssetCommonConcept.PREMIUM_ASSET_EXPIRATION.displayName(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.REMAINING_HEARTBEAT_MUST_BE_RECONCILED,
                    modelClass
            );
        }
    }

    private static void guardExpirationTimestampForUnreconciled(
            final Instant expirationTimestamp,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        SanitizedExpirationBaselineSpec.Definition definition =
                SanitizedExpirationBaselineSpec.of(concept);
        Specification<Instant> specification =
                definition.create(expirationTimestamp);
        if (!specification.isSatisfiedBy(expirationTimestamp)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnacceptableValueCause.of(
                                    concept,
                                    expirationTimestamp.toString(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.EXPIRATION_TIMESTAMP_MUST_BE_UNRECONCILED,
                    modelClass
            );
        }
    }

    private static void guardTerminationTimestampForUnreconciled(
            final Instant terminationTimestamp,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        SanitizedExpirationBaselineSpec.Definition definition =
                SanitizedExpirationBaselineSpec.of(concept);
        Specification<Instant> specification =
                definition.create(terminationTimestamp);
        if (!specification.isSatisfiedBy(terminationTimestamp)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnacceptableValueCause.of(
                                    concept,
                                    terminationTimestamp.toString(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.TERMINATION_TIMESTAMP_MUST_BE_UNRECONCILED,
                    modelClass
            );
        }
    }

    private static void guardRemainingHeartbeatForUnreconciled(
            final long remainingHeartbeat,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        UnreconciledRemainingHeartbeatRequirementSpec.Definition definition =
                UnreconciledRemainingHeartbeatRequirementSpec.of(concept);
        Specification<Long> specification =
                definition.create(remainingHeartbeat);
        if (!specification.isSatisfiedBy(remainingHeartbeat)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            OutOfBoundsCause.of(
                                    concept,
                                    String.valueOf(remainingHeartbeat),
                                    PremiumAssetCommonConcept.PREMIUM_ASSET_EXPIRATION.displayName(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.REMAINING_HEARTBEAT_MUST_BE_UNRECONCILED,
                    modelClass
            );
        }
    }

    private static void guardExpirationConsistency(
            final DomainConceptProperty<Instant> expirationTimestampProperty,
            final DomainConceptProperty<Instant> terminationTimestampProperty,
            final DomainConceptProperty<ExpirationState> expirationStateProperty,
            final Class<?> modelClass
    ) {
        ExpirationEqualityCondition condition =
                ExpirationEqualityCondition.of(
                        expirationTimestampProperty.value(),
                        terminationTimestampProperty.value()
                );
        ExpirationEqualitySpec.Definition definition =
                ExpirationEqualitySpec.of(PremiumAssetCommonConcept.EXPIRATION);
        Specification<ExpirationEqualityCondition> specification =
                definition.create(condition);
        if (!specification.isSatisfiedBy(condition)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            InconsistentValuesCause.of(
                                    PremiumAssetCommonConcept.ABSOLUTE_TIME.displayName(),
                                    PremiumAssetCommonConcept.EXPIRATION,
                                    expirationTimestampProperty.concept(),
                                    expirationTimestampProperty.value().toString(),
                                    terminationTimestampProperty.concept(),
                                    terminationTimestampProperty.value().toString(),
                                    expirationStateProperty.concept().displayName(),
                                    ExpirationState.RECONCILED.displayName()
                            )
                    ),
                    Invariant.EXPIRATION_TIMESTAMP_MUST_BE_CONSISTENT_WITH_TERMINATION_TIMESTAMP,
                    modelClass
            );
        }
    }

    @Override
    public void guardRules(
            final PremiumAssetExpirationValidationContext context,
            final Class<?> modelClass
    ) {
        guardTimeAnchorPresent(
                context.timeAnchorProperty().value(),
                context.timeAnchorProperty().concept(),
                modelClass
        );
        guardTimeZonePresent(
                context.timeZoneProperty().value(),
                context.timeZoneProperty().concept(),
                modelClass
        );
        guardExpirationTimestampPresent(
                context.expirationTimestampProperty().value(),
                context.expirationTimestampProperty().concept(),
                modelClass
        );
        guardTerminationTimestampPresent(
                context.terminationTimestampProperty().value(),
                context.terminationTimestampProperty().concept(),
                modelClass
                );
        guardExpirationStatePresent(
                context.expirationStateProperty().value(),
                context.expirationStateProperty().concept(),
                modelClass
        );

        if (context.expirationStateProperty().value() == ExpirationState.RECONCILED) {
            guardExpirationTimestampReconciled(
                    context.timeAnchorProperty().value(),
                    context.expirationTimestampProperty().value(),
                    context.expirationTimestampProperty().concept(),
                    modelClass
            );
            guardTerminationTimestampReconciled(
                    context.timeAnchorProperty().value(),
                    context.terminationTimestampProperty().value(),
                    context.expirationTimestampProperty().concept(),
                    modelClass
            );
            guardRemainingHeartbeatReconciled(
                    context.timeAnchorProperty().value(),
                    context.remainingHeartbeatProperty().value(),
                    context.remainingHeartbeatProperty().concept(),
                    modelClass
            );
            guardExpirationConsistency(
                    context.expirationTimestampProperty(),
                    context.terminationTimestampProperty(),
                    context.expirationStateProperty(),
                    modelClass
            );
        } else { /// expirationState == ExpirationState.UNRECONCILED
            guardExpirationTimestampForUnreconciled(
                    context.expirationTimestampProperty().value(),
                    context.expirationTimestampProperty().concept(),
                    modelClass
            );
            guardTerminationTimestampForUnreconciled(
                    context.terminationTimestampProperty().value(),
                    context.terminationTimestampProperty().concept(),
                    modelClass
            );
            guardRemainingHeartbeatForUnreconciled(
                    context.remainingHeartbeatProperty().value(),
                    context.remainingHeartbeatProperty().concept(),
                    modelClass
            );
        }

    }

}
