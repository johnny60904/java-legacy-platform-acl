package com.dxlan.acl.premiumasset.presentation.translations;

import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.shared.log.AclLogger;
import com.dxlan.acl.features.shared.text.TextDivider;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.entities.TimedPremiumAsset;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetCommonConcept;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;

import java.util.List;

import static com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset.Invariant.*;
import static com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository.Invariant.*;
import static com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.PremiumAssetCommonIntegrityGuard.Invariant.*;
import static com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.TimedPremiumAssetIntegrityGuard.Invariant.*;
import static com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.PremiumAssetBriefIntegrityGuard.Invariant.*;
import static com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.PremiumAssetExpirationIntegrityGuard.Invariant.*;

public final class PremiumAssetDomainExceptionTranslator {

    private static final String HR =
            TextDivider.STRONG.getText();

    private PremiumAssetDomainExceptionTranslator() { throw new AssertionError(); }

    private static final List<String> MISSING_EXPIRATION_UNIT_CAUSE =
            List.of(
                    HR,
                    TranslationMessage.Text.INVALID_OPERATION + ":",
                    "Missing " +
                    TimedPremiumAsset.Concept.EXPIRATION_UNIT.displayName() +
                    " to adjustment the premium asset expiration.",
                    HR
            );

    private static List<String> unacceptableLifespanCause(
            final String lifespan
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "The premium asset " +
                PremiumAssetBrief.Concept.LIFESPAN.displayName() +
                " [" + lifespan + "] (" +
                PremiumAssetLifeCycle.getDefaultUnit().displayName() + "s)" +
                " is invalid.",
                HR
        );
    }

    private static List<String> outOfBoundsExtensionDurationCause(
            final String extensionDuration
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "Failed to extend the premium asset expiration due to " +
                TimedPremiumAsset.Concept.EXTENSION_DURATION.displayName() +
                " [" + extensionDuration + "]" +
                " out of the valid range.",
                HR
        );
    }

    private static List<String> premiumAssetTypeOnlyTimedAllowedCause(
            final String premiumAssetType
    ) {
        return List.of(
                HR,
                TranslationMessage.Text.INVALID_OPERATION + ":",
                "The " + PremiumAssetCommonConcept.PREMIUM_ASSET_TYPE.displayName() +
                " [" + premiumAssetType + "]" +
                " must be [" + PremiumAssetType.TIMED.displayName() + "],",
                " only " + PremiumAssetType.TIMED.description() +
                " can be extended / expire expiration.",
                HR
        );
    }

    public static List<String> translate(
            final InvariantRuleViolationException exception
    ) {
        return switch (exception.violationInvariant()) {
            case EXPIRATION_UNIT_MUST_BE_PRESENT -> MISSING_EXPIRATION_UNIT_CAUSE;
            case EXTENSION_DURATION_MUST_BE_IN_BOUNDS ->
                    outOfBoundsExtensionDurationCause(exception.violatedValue());
            case LIFESPAN_MUST_BE_VALID ->
                    unacceptableLifespanCause(exception.violatedValue());
            case PREMIUM_ASSET_TYPE_MUST_BE_TIMED,
                 EXTEND_EXPIRATION_IS_ONLY_ALLOWED_FOR_TIMED_ASSET,
                 EXPIRE_EXPIRATION_IS_ONLY_ALLOWED_FOR_TIMED_ASSET,
                 EXTEND_EXPIRATION_IS_ONLY_ALLOWED_FOR_RECONCILED_EXPIRATION_STATE,
                 EXPIRE_EXPIRATION_IS_ONLY_ALLOWED_FOR_RECONCILED_EXPIRATION_STATE ->
                    premiumAssetTypeOnlyTimedAllowedCause(exception.violatedValue());
            case PREMIUM_ASSET_BRIEF_MUST_BE_PRESENT,
                 PREMIUM_ASSET_EXPIRATION_MUST_BE_PRESENT,
                 PREMIUM_ASSET_TYPE_MUST_BE_PERMANENT,
                 ASSET_NAME_MUST_NOT_BE_BLANK,
                 PREMIUM_ASSET_TYPE_MUST_BE_PRESENT,
                 ASSET_ITEM_ID_MUST_BE_IN_BOUNDS,
                 SESSION_PROCESS_ID_MUST_BE_IN_BOUNDS,
                 CLUSTER_GROUP_ID_MUST_BE_VALID,
                 TIME_ANCHOR_MUST_BE_PRESENT,
                 TIME_ZONE_MUST_BE_PRESENT,
                 EXPIRATION_TIMESTAMP_MUST_BE_PRESENT,
                 TERMINATION_TIMESTAMP_MUST_BE_PRESENT,
                 EXPIRATION_STATE_MUST_BE_PRESENT,
                 EXPIRATION_TIMESTAMP_MUST_BE_RECONCILED,
                 TERMINATION_TIMESTAMP_MUST_BE_RECONCILED,
                 EXPIRATION_TIMESTAMP_MUST_BE_UNRECONCILED,
                 TERMINATION_TIMESTAMP_MUST_BE_UNRECONCILED,
                 REMAINING_HEARTBEAT_MUST_BE_RECONCILED,
                 REMAINING_HEARTBEAT_MUST_BE_UNRECONCILED,
                 EXPIRATION_TIMESTAMP_MUST_BE_CONSISTENT_WITH_TERMINATION_TIMESTAMP,
                 PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT,
                 PREMIUM_ASSET_IDENTITY_MUST_BE_VALID,
                 PREMIUM_ASSET_LIFETIME_MUST_BE_VALID,
                 PREMIUM_ASSET_EXPIRATION_MUST_BE_VALID ->
            {
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
            default -> {
                String message = "Unreachable code path in [" +
                        PremiumAssetDomainExceptionTranslator.class.getSimpleName() + "].";
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
