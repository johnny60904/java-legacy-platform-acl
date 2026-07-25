package com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset;

import com.dxlan.acl.features.shared.domain.integrities.DomaInIntegrityGuard;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.integrities.DomainInvariant;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.MissingRequiredCause;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.OutOfBoundsCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.shared.domain.specifications.DomainConceptRequirementSpec;
import com.dxlan.acl.features.shared.domain.specifications.Specification;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetCommonConcept;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts.TimedPremiumAssetValidationContext;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.specifications.ExtensionDurationBoundsSpec;

import java.time.Instant;
import java.time.ZoneId;

public enum TimedPremiumAssetIntegrityGuard implements
        DomaInIntegrityGuard<TimedPremiumAssetValidationContext> {

    INSTANCE;

    /// PremiumAssetModuleMetadata.CORE
    public static enum Invariant implements DomainInvariant {
        /// MissingRequiredCause
        EXPIRATION_UNIT_MUST_BE_PRESENT(
                "ExpirationUnitMustBePresent",
                10
        ),
        /// OutOfBoundsCause
        EXTENSION_DURATION_MUST_BE_IN_BOUNDS(
                "ExtensionDurationMustBeInBounds",
                5
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

    private static void guardExpirationUnitPresent(
            final ExpirationUnit expirationUnit,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        Specification<ExpirationUnit> specification =
                DomainConceptRequirementSpec.create(
                        expirationUnit,
                        concept
                );
        if (!specification.isSatisfiedBy(expirationUnit)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            MissingRequiredCause.ofNull(
                                    concept,
                                    " to adjust the pet expiration precisely"
                            )
                    ),
                    Invariant.EXPIRATION_UNIT_MUST_BE_PRESENT,
                    modelClass
            );
        }
    }

    private static void guardExtensionDurationInBounds(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final ExpirationUnit expirationUnit,
            final long extensionDuration,
            final DomainConcept concept,
            final Class<?> modelClass
    ) {
        ExtensionDurationBoundsSpec.Definition definition =
                ExtensionDurationBoundsSpec.of(
                        timeAnchor,
                        timeZone,
                        expirationUnit,
                        concept
                );
        Specification<Long> specification = definition.create(extensionDuration);
        if (!specification.isSatisfiedBy(extensionDuration)) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            OutOfBoundsCause.of(
                                    concept,
                                    extensionDuration +
                                    " (" + expirationUnit.displayName() + ")",
                                    PremiumAssetCommonConcept.TIMED_PREMIUM_ASSET.displayName(),
                                    definition.ruleDescription()
                            )
                    ),
                    Invariant.EXTENSION_DURATION_MUST_BE_IN_BOUNDS,
                    modelClass
            );
        }
    }

    @Override
    public void guardRules(
            final TimedPremiumAssetValidationContext context,
            final Class<?> modelClass
    ) {
        guardExpirationUnitPresent(
                context.expirationUnitProperty().value(),
                context.expirationUnitProperty().concept(),
                modelClass
        );
        guardExtensionDurationInBounds(
                context.expirationTimeContext().timeAnchor(),
                context.expirationTimeContext().timeZone(),
                context.expirationUnitProperty().value(),
                context.extensionDurationProperty().value(),
                context.extensionDurationProperty().concept(),
                modelClass
        );
    }

}
