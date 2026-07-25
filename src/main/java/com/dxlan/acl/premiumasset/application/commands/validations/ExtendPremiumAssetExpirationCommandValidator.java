package com.dxlan.acl.premiumasset.application.commands.validations;

import com.dxlan.acl.features.shared.text.StringPredicate;
import com.dxlan.acl.features.shared.validations.causes.DisallowedValueCause;
import com.dxlan.acl.features.shared.validations.causes.MissingFieldCause;
import com.dxlan.acl.features.shared.validations.causes.OutOfRangeCause;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.engines.UseCaseValidator;
import com.dxlan.acl.features.shared.validations.taxonomy.CommandValidation;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidCommandException;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationClause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;
import com.dxlan.acl.premiumasset.application.commands.validations.contexts.ExtendPremiumAssetExpirationCommandValidationContext;
import com.dxlan.acl.premiumasset.application.commands.validations.rules.ExpirationUnitResolutionRule;
import com.dxlan.acl.premiumasset.application.commands.validations.rules.ExtensionDurationPositiveRule;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;

public enum ExtendPremiumAssetExpirationCommandValidator implements
        UseCaseValidator<ExtendPremiumAssetExpirationCommandValidationContext> {

    INSTANCE;

    /// PremiumAssetModuleMetadata.CORE
    public static enum Rule implements ValidationClause {
        /// MissingFieldCause
        EXPIRATION_UNIT_TOKEN_MUST_NOT_BE_BLANK(
                "ExpirationUnitTokenMustNotBeBlank",
                3
        ),
        /// DisallowedValueCause
        EXPIRATION_UNIT_TOKEN_MUST_BE_ALLOWED_VALUE(
                "ExpirationUnitTokenMustBeAllowedValue",
                1
        ),
        /// OutOfRangeCause
        EXTENSION_DURATION_MUST_BE_POSITIVE(
                "TimeZoneMustBePositive",
                6
        );

        private final String displayName;
        private final int serial;

        private Rule(
                final String displayName,
                final int serial
        ) {
            this.displayName = displayName;
            this.serial = serial;
        }

        @Override
        public int serialNumber() {
            return serial;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

    private static void validateExpirationUnitTokenHasText(
            final String expirationUnitToken,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        if (StringPredicate.isNullOrWhiteSpace(expirationUnitToken)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            MissingFieldCause.of(
                                    parameter,
                                    expirationUnitToken
                            )
                    ),
                    Rule.EXPIRATION_UNIT_TOKEN_MUST_NOT_BE_BLANK,
                    commandClass
            );
        }
    }

    private static void validateExpirationUnitTokenParsable(
            final String expirationUnitToken,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        ExpirationUnitResolutionRule.Definition definition =
                ExpirationUnitResolutionRule.of(parameter);
        ValidationConstraint<String> constraint = definition.create(expirationUnitToken);
        if (!constraint.isSatisfiedBy(expirationUnitToken)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            DisallowedValueCause.of(
                                    parameter,
                                    expirationUnitToken,
                                    definition.validationDescription()
                            )
                    ),
                    Rule.EXPIRATION_UNIT_TOKEN_MUST_BE_ALLOWED_VALUE,
                    commandClass
            );
        }
    }

    private static void validateExtensionDurationPositive(
            final long extensionDuration,
            final String expirationUnitToken,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        ExtensionDurationPositiveRule.Definition definition =
                ExtensionDurationPositiveRule.of(parameter);
        ValidationConstraint<Long> constraint = definition.create(extensionDuration);
        if (!constraint.isSatisfiedBy(extensionDuration)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(extensionDuration) +
                                    " (" + expirationUnitToken + ")",
                                    definition.validationDescription()
                            )
                    ),
                    Rule.EXTENSION_DURATION_MUST_BE_POSITIVE,
                    commandClass
            );
        }
    }

    @Override
    public void validate(
            final ExtendPremiumAssetExpirationCommandValidationContext context,
            final Class<?> commandClass
    ) {
        validateExpirationUnitTokenHasText(
                context.expirationUnitTokenProperty().value(),
                context.expirationUnitTokenProperty().parameter(),
                commandClass
        );
        validateExpirationUnitTokenParsable(
                context.expirationUnitTokenProperty().value(),
                context.expirationUnitTokenProperty().parameter(),
                commandClass
        );
        validateExtensionDurationPositive(
                context.extensionDurationProperty().value(),
                context.expirationUnitTokenProperty().value(),
                context.extensionDurationProperty().parameter(),
                commandClass
        );
    }

}
