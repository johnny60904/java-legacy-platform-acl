package com.dxlan.acl.premiumasset.presentation.translations;

import com.dxlan.acl.features.infrastructure.log.AclLogger;
import com.dxlan.acl.features.shared.text.TextDivider;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidQueryException;
import com.dxlan.acl.premiumasset.application.common.enums.PremiumAssetCommonParameter;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;

import java.util.List;

import static com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetGateway.Clause.*;

import static com.dxlan.acl.premiumasset.application.queries.validations.GetPremiumAssetDetailsQueryValidator.Rule.*;

public final class PremiumAssetQueryExceptionTranslator {

    private static final String HR =
            TextDivider.STRONG.getText();

    private PremiumAssetQueryExceptionTranslator() { throw new AssertionError(); }

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

    private static List<String> outOfRangeActiveAssetIndexCause(
            final String activeAssetIndex
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to find the active asset due to invalid " +
                PremiumAssetCommonParameter.ACTIVE_ASSET_INDEX.displayName() +
                " [" + activeAssetIndex + "].",
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

    public static List<String> translate(
            final InvalidQueryException exception
    ) {
        return switch (exception.validationClause()) {
            case CLUSTER_GROUP_ID_MUST_BE_IN_RANGE ->
                    outOfRangeClusterGroupIdCause(exception.rejectedValue());
            case SESSION_PROCESS_ID_MUST_BE_IN_RANGE ->
                    outOfRangeSessionProcessIdCause(exception.rejectedValue());
            case ACTIVE_ASSET_INDEX_MUST_BE_IN_RANGE ->
                    outOfRangeActiveAssetIndexCause(exception.rejectedValue());
            case STORAGE_SLOT_MUST_BE_IN_RANGE ->
                    outOfRangeStorageSlotCause(exception.rejectedValue());
            case ASSET_ITEM_ID_MUST_BE_IN_RANGE ->
                    outOfRangePremiumAssetItemIdCause(exception.rejectedValue());
            case PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                 PREMIUM_ASSET_IDENTITY_MUST_BE_VALID,
                 PREMIUM_ASSET_LIFETIME_MUST_BE_VALID,
                 PREMIUM_ASSET_EXPIRATION_MUST_BE_VALID -> {
                AclLogger.error(
                        AclPremiumAsset.class,
                        exception.detailedMessage(),
                        exception
                );
                yield List.of(
                        TranslationMessage.Text.SYSTEM_INTERNAL_ERROR,
                        TranslationMessage.Text.NOTE_FAILURE_TIME,
                        TranslationMessage.Text.CONTACT_ADMIN
                );
            }
            /// Unreachable Code Path
            default -> {
                String message = "Unreachable code path in [" +
                        PremiumAssetQueryExceptionTranslator.class.getSimpleName() + "].";
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
