package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreType;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.DataCorruptedCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;
import net.legacy.platform.core.model.PremiumAssetItem;

import java.time.Instant;
import java.time.ZoneId;

public final class PremiumAssetExpirationMapper {

    private static String basePrefix(
            Class<?> callerClass
    ) {
        return "Data corruption at ACL boundary [" + callerClass + "].";
    }

    private static final String SOURCE_NAME =
            LegacyCoreType.SOURCE.displayName();

    private PremiumAssetExpirationMapper() { throw new AssertionError(); }

    private static long requireRemainingHeartbeatValid(
            final long remainingHeartbeat,
            final Class<?> callerClass
    ) {
        if (remainingHeartbeat < 0) {
            String constraint = PremiumAssetExpiration.Concept.REMAINING_HEARTBEAT.displayName() +
                    " must be >= 0";
            String context = basePrefix(callerClass) +
                    " Illegal numerical remaining life received from " + SOURCE_NAME + " context.";
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            DataCorruptedCause.of(
                                    PremiumAssetExpiration.Concept.REMAINING_HEARTBEAT,
                                    String.valueOf(remainingHeartbeat),
                                    constraint,
                                    context
                            )
                    ),
                    PremiumAssetRepository.Invariant.PREMIUM_ASSET_EXPIRATION_MUST_BE_VALID,
                    callerClass
            );
        }
        return remainingHeartbeat;
    }

    public static PremiumAssetExpiration validateAndMap(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final PremiumAssetItem legacyPremiumAsset,
            final Class<?> callerClass
    ) {
        return PremiumAssetExpiration.of(
                timeAnchor,
                timeZone,
                ExpirationTranslator.toInstant(
                        legacyPremiumAsset.getExpirationTimestamp()
                ),
                ExpirationTranslator.toInstant(
                        legacyPremiumAsset.getTerminationTimestamp()
                ),
                requireRemainingHeartbeatValid(
                        legacyPremiumAsset.getRemainingHeartbeat(),
                        callerClass
                ),
                ExpirationStateMapper.map(
                        legacyPremiumAsset.isAclReconciled()
                )
        );
    }

}
