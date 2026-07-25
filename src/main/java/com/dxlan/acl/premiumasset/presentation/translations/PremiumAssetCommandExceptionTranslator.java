package com.dxlan.acl.premiumasset.presentation.translations;

import com.dxlan.acl.features.shared.log.AclLogger;
import com.dxlan.acl.features.shared.text.TextDivider;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidCommandException;
import com.dxlan.acl.premiumasset.application.commands.extendexpiration.ExtendPremiumAssetExpirationByIdCommand;
import com.dxlan.acl.premiumasset.application.common.enums.PremiumAssetCommonParameter;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.entities.TimedPremiumAsset;

import java.util.List;

import static com.dxlan.acl.premiumasset.application.commands.validations.ExtendPremiumAssetExpirationCommandValidator.Rule.*;
import static com.dxlan.acl.premiumasset.application.commands.validations.PremiumAssetCommandCommonArgumentValidator.Rule.*;

public final class PremiumAssetCommandExceptionTranslator {

    private static final String HR =
            TextDivider.STRONG.getText();

    private static final List<String> BLANK_EXPIRATION_UNIT_TOKEN_CAUSE =
            List.of(
                    HR,
                    TranslationMessage.Text.INVALID_OPERATION + ":",
                    "Missing " +
                    TimedPremiumAsset.Concept.EXPIRATION_UNIT.displayName() +
                    " (null or blank) to adjust the premium asset expiration",
                    HR
            );

    private PremiumAssetCommandExceptionTranslator() { throw new AssertionError(); }

    private static List<String> outOfRangeClusterGroupIdCause(
            final String clusterGroupId
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to find the exact system cluster due to invalid " +
                PremiumAssetCommonParameter.CLUSTER_GROUP_ID.displayName() +
                " [" + clusterGroupId + "].",
                HR
        );
    }

    private static List<String> outOfRangeSessionProcessIdCause(
            final String sessionProcessId
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to find the user session due to invalid " +
                PremiumAssetCommonParameter.SESSION_PROCESS_ID.displayName() +
                " [" + sessionProcessId + "].",
                HR
        );
    }

    private static List<String> outOfRangeActivePetIndexCause(
            final String activePetIndex
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to find the active asset due to invalid " +
                PremiumAssetCommonParameter.ACTIVE_ASSET_INDEX.displayName() +
                " [" + activePetIndex + "].",
                HR
        );
    }

    private static List<String> outOfRangePremiumAssetItemIdCause(
            final String assetItemId
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to find the premium asset due to invalid " +
                PremiumAssetCommonParameter.PREMIUM_ASSET_ITEM_ID.displayName() +
                " [" + assetItemId + "].",
                HR
        );
    }

    private static List<String> outOfRangeStorageSlotCause(
            final String storageSlot
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to find the premium asset due to invalid " +
                PremiumAssetCommonParameter.STORAGE_SLOT.displayName() +
                " [" + storageSlot + "].",
                HR
        );
    }

    private static List<String> disallowedExpirationUnitTokenCause(
            final String expirationUnitToken
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to extend the premium asset expiration due to invalid / unsupported " +
                TimedPremiumAsset.Concept.EXPIRATION_UNIT.displayName() +
                " [" + expirationUnitToken + "].",
                HR
        );
    }

    private static List<String> outOfRangeExtensionDurationCause(
            final String extensionDuration
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION,
                "Invalid " +
                ExtendPremiumAssetExpirationByIdCommand.Field.EXTENSION_DURATION.displayName() +
                " [" + extensionDuration + "] " +
                " to extend the premium asset expiration.",
                HR
        );
    }

    private static List<String> missingTimeAnchorOrTimeZoneCause() {
        return List.of(
                TranslationMessage.Text.SYSTEM_INTERNAL_ERROR,
                TranslationMessage.Text.NOTE_FAILURE_TIME,
                TranslationMessage.Text.CONTACT_ADMIN
        );
    }

    public static List<String> translate(
            final InvalidCommandException exception
    ) {
        return switch (exception.structuralConstraint()) {
            case CLUSTER_GROUP_ID_MUST_BE_IN_RANGE ->
                    outOfRangeClusterGroupIdCause(exception.rejectedValue());
            case SESSION_PROCESS_ID_MUST_BE_IN_RANGE ->
                    outOfRangeSessionProcessIdCause(exception.rejectedValue());
            case ACTIVE_ASSET_INDEX_MUST_BE_IN_RANGE ->
                    outOfRangeActivePetIndexCause(exception.rejectedValue());
            case STORAGE_SLOT_MUST_BE_IN_RANGE ->
                    outOfRangeStorageSlotCause(exception.rejectedValue());
            case ASSET_ITEM_ID_MUST_BE_IN_RANGE ->
                    outOfRangePremiumAssetItemIdCause(exception.rejectedValue());
            case EXPIRATION_UNIT_TOKEN_MUST_NOT_BE_BLANK -> BLANK_EXPIRATION_UNIT_TOKEN_CAUSE;
            case EXPIRATION_UNIT_TOKEN_MUST_BE_ALLOWED_VALUE ->
                    disallowedExpirationUnitTokenCause(exception.rejectedValue());
            case EXTENSION_DURATION_MUST_BE_POSITIVE ->
                    outOfRangeExtensionDurationCause(exception.rejectedValue());
            case TIME_ANCHOR_MUST_BE_PRESENT,
                 TIME_ZONE_MUST_BE_PRESENT -> {
                AclLogger.error(
                        AclPremiumAsset.class,
                        exception.detailedMessage(),
                        exception
                );
                yield missingTimeAnchorOrTimeZoneCause();
            }
            /// Unreachable Code Path
            default -> {
                String message = "Unreachable code path in [" +
                        PremiumAssetCommandExceptionTranslator.class.getSimpleName() + "].";
                AclLogger.error(
                        AclPremiumAsset.class,
                        message
                );
                yield List.of(
                        TranslationMessage.Text.UNKNOWN_ERROR,
                        TranslationMessage.Text.NOTE_FAILURE_TIME,
                        TranslationMessage.Text.CONTACT_ADMIN
                );
            }
        };
    }

}
