package com.dxlan.acl.premiumasset.application.commands.reconcileexpiration;

import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationProperty;
import com.dxlan.acl.premiumasset.application.commands.validations.PremiumAssetCommandCommonArgumentValidator;
import com.dxlan.acl.premiumasset.application.commands.validations.contexts.PremiumAssetCommandValidationContext;
import com.dxlan.acl.premiumasset.application.common.enums.PremiumAssetCommonParameter;
import com.dxlan.acl.premiumasset.application.common.interfaces.PremiumAssetApplicationMetadata;

import java.time.Instant;
import java.time.ZoneId;

public record ReconcilePremiumAssetExpirationByIdCommand(
        Instant timeAnchor,
        ZoneId timeZone,
        int clusterGroupId,
        int sessionProcessId,
        int assetItemId
) {

    public ReconcilePremiumAssetExpirationByIdCommand {
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
                ReconcilePremiumAssetExpirationByIdCommand.class
        );
        PremiumAssetCommandCommonArgumentValidator.validatePremiumAssetItemIdInRange(
                assetItemId,
                PremiumAssetCommonParameter.PREMIUM_ASSET_ITEM_ID,
                ReconcilePremiumAssetExpirationByIdCommand.class
        );
    }

    public static ReconcilePremiumAssetExpirationByIdCommand of(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId
    ) {
        return new ReconcilePremiumAssetExpirationByIdCommand(
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                assetItemId
        );
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
            return ReconcilePremiumAssetExpirationByIdCommand.class.getSimpleName();
        }
    }

    private static final PremiumAssetApplicationMetadata METADATA = new Metadata();

    public static PremiumAssetApplicationMetadata getMetadata() {
        return METADATA;
    }

}