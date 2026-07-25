package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.boundaries.LegacyBoundaryDefender;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.DataCorruptedCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationInvariant;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationTarget;
import com.dxlan.acl.features.shared.validations.causes.PayloadDataCorruptedCause;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidQueryException;
import com.dxlan.acl.features.shared.validations.taxonomy.QueryValidation;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationClause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationTarget;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetDetails;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetGateway;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.PremiumAssetMetadata;

import java.time.Instant;
import java.time.ZoneId;

public final class PremiumAssetDetailsMapper {

    private PremiumAssetDetailsMapper() { throw new AssertionError(); }

    private static ValidationClause translatePremiumAssetBriefInvariant(
            final ViolationInvariant invariant
    ) {
        return switch(invariant) {
            case PremiumAssetRepository.Invariant.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT ->
                    PremiumAssetGateway.Clause.PREMIUM_ASSET_RESOURCE_MUST_BE_PRESENT;
            case PremiumAssetRepository.Invariant.PREMIUM_ASSET_IDENTITY_MUST_BE_VALID ->
                PremiumAssetGateway.Clause.PREMIUM_ASSET_IDENTITY_MUST_BE_VALID;
            case PremiumAssetRepository.Invariant.PREMIUM_ASSET_LIFETIME_MUST_BE_VALID ->
                PremiumAssetGateway.Clause.PREMIUM_ASSET_LIFETIME_MUST_BE_VALID;
            default -> throw new IllegalArgumentException(
                    "Unknown invariant: " + invariant.displayName() + "."
            );
        };
    }

    private static PayloadDataCorruptedCause translatePremiumAssetBriefCause(
            final ViolationCause cause
    ) {
        if (cause instanceof DataCorruptedCause(
                ViolationTarget target,
                String corruptedValue,
                String integrityConstraint,
                String violationContextDescription
        )) {
            return PayloadDataCorruptedCause.of(
                    (ValidationTarget) target,
                    corruptedValue,
                    integrityConstraint,
                    violationContextDescription
            );
        }
        throw new IllegalArgumentException(
                "Unknown violation cause: " + cause.getClass().getSimpleName() + "."
        );
    }

    private static InvalidQueryException translatePremiumAssetBriefMapperDomainException(
            final InvariantRuleViolationException violationException,
            final Class<?> callerClass
    ) {
        return new InvalidQueryException(
                PremiumAssetModuleMetadata.CORE,
                QueryValidation.of(
                        translatePremiumAssetBriefCause(violationException.violationCause())
                ),
                translatePremiumAssetBriefInvariant(violationException.violationInvariant()),
                callerClass
        );
    }

    private static ValidationClause translatePremiumAssetExpirationInvariant(
            final ViolationInvariant invariant
    ) {
        if (invariant == PremiumAssetRepository.Invariant.PREMIUM_ASSET_EXPIRATION_MUST_BE_VALID) {
            return PremiumAssetGateway.Clause.PREMIUM_ASSET_EXPIRATION_MUST_BE_VALID;
        }
        throw new IllegalArgumentException(
                "Unknown invariant: " + invariant.displayName() + "."
        );
    }

    private static PayloadDataCorruptedCause translatePremiumAssetExpirationCause(
            final ViolationCause cause
    ) {
        if (cause instanceof DataCorruptedCause(
                ViolationTarget target,
                String corruptedValue,
                String integrityConstraint,
                String violationContextDescription
        )) {
            return PayloadDataCorruptedCause.of(
                    (ValidationTarget) target,
                    corruptedValue,
                    integrityConstraint,
                    violationContextDescription
            );
        }
        throw new IllegalArgumentException(
                "Unknown violation cause: " + cause.getClass().getSimpleName() + "."
        );
    }

    private static InvalidQueryException translatePremiumAssetExpirationMapperDomainException(
            final InvariantRuleViolationException violationException,
            final Class<?> callerClass
    ) {
        return new InvalidQueryException(
                PremiumAssetModuleMetadata.CORE,
                QueryValidation.of(
                        translatePremiumAssetExpirationCause(violationException.violationCause())
                ),
                translatePremiumAssetExpirationInvariant(violationException.violationInvariant()),
                callerClass
        );
    }

    public static PremiumAssetDetails map(
            final int clusterGroupId,
            final int sessionProcessId,
            final PremiumAssetItem legacyPremiumAssetItem,
            final PremiumAssetMetadata legacyPremiumAssetMetadata,
            final Instant timeAnchor,
            final ZoneId timeZone,
            final Class<?> callerClass
    ) {
        PremiumAssetBrief premiumAssetBrief;
        PremiumAssetExpiration premiumAssetExpiration;
        try {
            premiumAssetBrief = PremiumAssetBriefMapper.validateAndMap(
                    clusterGroupId,
                    sessionProcessId,
                    legacyPremiumAssetItem,
                    legacyPremiumAssetMetadata,
                    callerClass
            );
        } catch (Throwable throwable) {
            if (throwable instanceof InvariantRuleViolationException violationException) {
                throw translatePremiumAssetBriefMapperDomainException(violationException, callerClass);
            }
            throw throwable;
        }
        try {
            premiumAssetExpiration = PremiumAssetExpirationMapper.validateAndMap(
                    timeAnchor,
                    timeZone,
                    legacyPremiumAssetItem,
                    callerClass
            );
        } catch (Throwable throwable) {
            if (throwable instanceof InvariantRuleViolationException violationException) {
                throw translatePremiumAssetExpirationMapperDomainException(violationException, callerClass);
            }
            throw throwable;
        }
        int assetItemId = legacyPremiumAssetItem.getAssetItemId();
        LegacyBoundaryDefender.requirePremiumAssetItemIdValid(
                BoundaryValidator.forClass(callerClass),
                assetItemId
        );
        return PremiumAssetDetails.of(
                premiumAssetBrief.getAssetName(),
                assetItemId,
                premiumAssetExpiration.getExpirationTimestamp().toString(),
                premiumAssetExpiration.getTerminationTimestamp().toString(),
                premiumAssetExpiration.getRemainingHeartbeat(),
                premiumAssetBrief.getLifespan(),
                premiumAssetBrief.getPremiumAssetType().description()
        );
    }

}
