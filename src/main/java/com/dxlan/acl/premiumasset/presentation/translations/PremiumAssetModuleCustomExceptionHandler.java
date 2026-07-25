package com.dxlan.acl.premiumasset.presentation.translations;

import com.dxlan.acl.features.notification.ClientNotification;
import com.dxlan.acl.features.notification.common.enums.ChatMessageType;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.infrastructure.log.AclLogger;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidCommandException;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidQueryException;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;

import java.util.List;

public final class PremiumAssetModuleCustomExceptionHandler {

    private PremiumAssetModuleCustomExceptionHandler() { throw new AssertionError(); }

    public static void handle(
            final int clusterGroupId,
            final int sessionProcessId,
            final Throwable throwable
    ) {
        List<String> messageLines = switch (throwable) {
            case InvariantRuleViolationException violationException ->
                PremiumAssetDomainExceptionTranslator.translate(violationException);
            case InvalidCommandException invalidCommandException ->
                PremiumAssetCommandExceptionTranslator.translate(invalidCommandException);
            case InvalidQueryException invalidQueryException ->
                PremiumAssetQueryExceptionTranslator.translate(invalidQueryException);
            /// Java Built-in Exception bubbled from Infrastructure Layer (Including Arguments Validation)
            default -> {
                AclLogger.error(
                        AclPremiumAsset.class,
                        throwable.getMessage(),
                        throwable
                );
                yield List.of(
                        TranslationMessage.Text.SYSTEM_INTERNAL_ERROR,
                        TranslationMessage.Text.NOTE_FAILURE_TIME,
                        TranslationMessage.Text.CONTACT_ADMIN
                );
            }
        };
        ClientNotification.sendModalAlert(
                clusterGroupId,
                sessionProcessId,
                TranslationMessage.Text.OPERATION_FAILURE
        );
        ClientNotification.sendToTerminalConsole(
                clusterGroupId,
                sessionProcessId,
                messageLines,
                ChatMessageType.ALERT_NOTICE
        );
    }

}
