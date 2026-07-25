package com.dxlan.acl.premiumasset.application.commands.extendexpiration;

import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationProperty;
import com.dxlan.acl.premiumasset.application.commands.validations.ExtendPremiumAssetExpirationCommandValidator;
import com.dxlan.acl.premiumasset.application.commands.validations.PremiumAssetCommandCommonArgumentValidator;
import com.dxlan.acl.premiumasset.application.commands.validations.contexts.ExtendPremiumAssetExpirationCommandValidationContext;
import com.dxlan.acl.premiumasset.application.commands.validations.contexts.PremiumAssetCommandValidationContext;
import com.dxlan.acl.premiumasset.application.common.enums.PremiumAssetCommonParameter;
import com.dxlan.acl.premiumasset.application.common.interfaces.PremiumAssetApplicationMetadata;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;

import java.time.Instant;
import java.time.ZoneId;

public record ExtendPremiumAssetExpirationBySlotCommand(
        long extensionDuration,
        String expirationUnitToken,
        Instant timeAnchor,
        ZoneId timeZone,
        int clusterGroupId,
        int sessionProcessId,
        int storageSlot
)  {

    public ExtendPremiumAssetExpirationBySlotCommand {
        PremiumAssetCommandCommonArgumentValidator.INSTANCE.validate(
                PremiumAssetCommandValidationContext.of(
                        ValidationProperty.of(
                                timeAnchor,
                                PremiumAssetCommonParameter.TIME_ANCHOR
                        ),
                        ValidationProperty.of(
                                timeZone,
                                PremiumAssetCommonParameter.TIME_ZONE
                        ),
                        ValidationProperty.of(
                                clusterGroupId,
                                PremiumAssetCommonParameter.CLUSTER_GROUP_ID
                        ),
                        ValidationProperty.of(
                                sessionProcessId,
                                PremiumAssetCommonParameter.SESSION_PROCESS_ID
                        )
                ),
                ExtendPremiumAssetExpirationBySlotCommand.class
        );
        ExtendPremiumAssetExpirationCommandValidator.INSTANCE.validate(
                ExtendPremiumAssetExpirationCommandValidationContext.of(
                        ValidationProperty.of(
                                extensionDuration,
                                Field.EXTENSION_DURATION
                        ),
                        ValidationProperty.of(
                                expirationUnitToken,
                                Field.EXPIRATION_UNIT_TOKEN
                        )
                ),
                ExtendPremiumAssetExpirationBySlotCommand.class
        );
        PremiumAssetCommandCommonArgumentValidator.validateStorageSlotInRange(
                storageSlot,
                PremiumAssetCommonParameter.STORAGE_SLOT,
                ExtendPremiumAssetExpirationBySlotCommand.class
        );
    }

    public ExpirationUnit expirationUnit() {
        return ExpirationUnit.ofTrusted(expirationUnitToken);
    }

    public static ExtendPremiumAssetExpirationBySlotCommand of(
            final long extensionDuration,
            final String expirationUnitToken,
            final Instant timeAnchor,
            final ZoneId timeZone,
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot
    ) {
        return new ExtendPremiumAssetExpirationBySlotCommand(
                extensionDuration,
                expirationUnitToken,
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                storageSlot
        );
    }

    public static enum Field implements ValidationParameter {
        EXTENSION_DURATION("ExtensionDuration"),
        EXPIRATION_UNIT_TOKEN("ExpirationUnitToken");

        private final String displayName;

        private Field(final String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

    private static record Metadata() implements PremiumAssetApplicationMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.DDD_MODULE;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.DDD;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.CLEAN_ARCHITECTURE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.CQRS_COMMAND;
        }

        @Override
        public ArchitecturalStereotype stereotype() {
            return ArchitecturalStereotype.COMMAND;
        }

        @Override
        public LanguageElement languageElement() {
            return LanguageElement.RECORD;
        }

        @Override
        public String typeName() {
            return ExtendPremiumAssetExpirationBySlotCommand.class.getSimpleName();
        }
    }

    private static final PremiumAssetApplicationMetadata METADATA = new Metadata();

    public static PremiumAssetApplicationMetadata getMetadata() {
        return METADATA;
    }

}
