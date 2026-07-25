package com.dxlan.acl.premiumasset.domain.entities;

import com.dxlan.acl.features.shared.domain.BaseEntity;
import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.UnsupportedOperationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetCommonConcept;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;
import com.dxlan.acl.premiumasset.domain.events.ExpirationReconciledEvent;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.PremiumAssetCommonIntegrityGuard;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts.PremiumAssetCommonConceptValidationContext;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.specifications.ExpirationAdjustmentEligibilitySpec;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

import java.util.List;

public final class PermanentPremiumAsset extends BaseEntity implements AclPremiumAsset {

    private final PremiumAssetBrief premiumAssetBrief;
    private PremiumAssetExpiration premiumAssetExpiration;

    public PermanentPremiumAsset(
            final PremiumAssetBrief premiumAssetBrief,
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        super();
        PremiumAssetCommonIntegrityGuard.INSTANCE.guardRules(
                PremiumAssetCommonConceptValidationContext.of(
                        DomainConceptProperty.of(
                                premiumAssetBrief,
                                PremiumAssetCommonConcept.PREMIUM_ASSET_BRIEF
                        ),
                        DomainConceptProperty.of(
                                premiumAssetExpiration,
                                PremiumAssetCommonConcept.PREMIUM_ASSET_EXPIRATION
                        )
                ),
                PermanentPremiumAsset.class
        );
        PremiumAssetCommonIntegrityGuard.INSTANCE
                .guardPremiumAssetTypeConsistentWithPermanentPremiumAsset(
                        premiumAssetBrief.getPremiumAssetType(),
                        PremiumAssetCommonConcept.PREMIUM_ASSET_TYPE,
                        PermanentPremiumAsset.class
                );
        this.premiumAssetBrief = premiumAssetBrief;
        this.premiumAssetExpiration = premiumAssetExpiration;
    }

    public static PermanentPremiumAsset of(
            final PremiumAssetBrief premiumAssetBrief,
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        return new PermanentPremiumAsset(premiumAssetBrief, premiumAssetExpiration);
    }

    private void setPremiumAssetExpiration(
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        this.premiumAssetExpiration = premiumAssetExpiration;
    }

    @Override
    public PremiumAssetBrief getPremiumAssetBrief() {
        return premiumAssetBrief;
    }

    @Override
    public PremiumAssetExpiration getPremiumAssetExpiration() {
        return premiumAssetExpiration;
    }

    @Override
    public void reconcileExpiration() {

        PremiumAssetExpiration originalExpiration = premiumAssetExpiration;

        setPremiumAssetExpiration(
                originalExpiration.reconcile(
                        PremiumAssetType.PERMANENT
                )
        );

        addDomainEvent(
                ExpirationReconciledEvent.of(
                        this,
                        originalExpiration
                )
        );

    }

    @Override
    public void extendExpiration() {
        throw new InvariantRuleViolationException(
                PremiumAssetModuleMetadata.CORE,
                DomainViolation.of(
                        UnsupportedOperationCause.of(
                                PremiumAssetCommonConcept.PREMIUM_ASSET_TYPE,
                                premiumAssetBrief.getPremiumAssetType().displayName(),
                                Method.EXTEND_EXPIRATION,
                                ExpirationAdjustmentEligibilitySpec
                                        .of(PremiumAssetCommonConcept.PREMIUM_ASSET_TYPE)
                                        .ruleDescription()
                        )
                ),
                Invariant.EXTEND_EXPIRATION_IS_ONLY_ALLOWED_FOR_TIMED_ASSET,
                PermanentPremiumAsset.class
        );
    }

    @Override
    public void expireExpiration() {
        throw new InvariantRuleViolationException(
                PremiumAssetModuleMetadata.CORE,
                DomainViolation.of(
                        UnsupportedOperationCause.of(
                                PremiumAssetCommonConcept.PREMIUM_ASSET_TYPE,
                                premiumAssetBrief.getPremiumAssetType().displayName(),
                                Method.EXPIRE_EXPIRATION,
                                ExpirationAdjustmentEligibilitySpec
                                        .of(PremiumAssetCommonConcept.PREMIUM_ASSET_TYPE)
                                        .ruleDescription()
                        )
                ),
                Invariant.EXPIRE_EXPIRATION_IS_ONLY_ALLOWED_FOR_TIMED_ASSET,
                PermanentPremiumAsset.class
        );
    }

    @Override
    public List<BaseEvent> getEvents() {
        return getDomainEvents();
    }

    @Override
    public void clearEvents() {
        clearDomainEvents();
    }

}
