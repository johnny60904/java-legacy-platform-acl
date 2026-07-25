package com.dxlan.acl.premiumasset.domain.entities;

import com.dxlan.acl.features.shared.domain.BaseEntity;
import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.features.shared.domain.integrities.violationcauses.UnsupportedOperationCause;
import com.dxlan.acl.features.shared.domain.integrities.violations.DomainViolation;
import com.dxlan.acl.features.shared.domain.integrities.violations.InvariantRuleViolationException;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.enums.*;
import com.dxlan.acl.premiumasset.domain.events.ExpirationExpiredEvent;
import com.dxlan.acl.premiumasset.domain.events.ExpirationExtendedEvent;
import com.dxlan.acl.premiumasset.domain.events.ExpirationReconciledEvent;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.PremiumAssetCommonIntegrityGuard;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.TimedPremiumAssetIntegrityGuard;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts.ExpirationTimeContext;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts.PremiumAssetCommonConceptValidationContext;
import com.dxlan.acl.premiumasset.domain.validations.aclpremiumasset.contexts.TimedPremiumAssetValidationContext;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

import java.util.List;

public final class TimedPremiumAsset extends BaseEntity implements AclPremiumAsset {

    private final long extensionDuration;
    private final ExpirationUnit expirationUnit;
    private final PremiumAssetBrief premiumAssetBrief;
    private PremiumAssetExpiration premiumAssetExpiration;

    private static void guardCommonInvariants(
            final PremiumAssetBrief premiumAssetBrief,
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
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
                TimedPremiumAsset.class
        );
        PremiumAssetCommonIntegrityGuard.INSTANCE
                .guardPremiumAssetTypeConsistentWithTimedPremiumAsset(
                        premiumAssetBrief.getPremiumAssetType(),
                        PremiumAssetCommonConcept.PREMIUM_ASSET_TYPE,
                        TimedPremiumAsset.class
                );
    }

    public TimedPremiumAsset(
            final PremiumAssetBrief premiumAssetBrief,
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        super();
        guardCommonInvariants(
                premiumAssetBrief,
                premiumAssetExpiration
        );
        this.extensionDuration = 0L;
        this.expirationUnit = null;
        this.premiumAssetBrief = premiumAssetBrief;
        this.premiumAssetExpiration = premiumAssetExpiration;
    }

    public TimedPremiumAsset(
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final PremiumAssetBrief premiumAssetBrief,
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        super();
        guardCommonInvariants(
                premiumAssetBrief,
                premiumAssetExpiration
        );
        TimedPremiumAssetIntegrityGuard.INSTANCE.guardRules(
                TimedPremiumAssetValidationContext.of(
                        ExpirationTimeContext.of(
                                premiumAssetExpiration.getTimeAnchor(),
                                premiumAssetExpiration.getTimeZone()
                        ),
                        DomainConceptProperty.of(
                                extensionDuration,
                                Concept.EXTENSION_DURATION
                        ),
                        DomainConceptProperty.of(
                                expirationUnit,
                                Concept.EXPIRATION_UNIT
                        )
                ),
                TimedPremiumAsset.class
        );
        this.extensionDuration = extensionDuration;
        this.expirationUnit = expirationUnit;
        this.premiumAssetBrief = premiumAssetBrief;
        this.premiumAssetExpiration = premiumAssetExpiration;
    }

    public static TimedPremiumAsset of(
            final PremiumAssetBrief premiumAssetBrief,
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        return new TimedPremiumAsset(
                premiumAssetBrief,
                premiumAssetExpiration
        );
    }

    public static TimedPremiumAsset of(
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final PremiumAssetBrief premiumAssetBrief,
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        return new TimedPremiumAsset(
                extensionDuration,
                expirationUnit,
                premiumAssetBrief,
                premiumAssetExpiration
        );
    }

    @Override
    public PremiumAssetBrief getPremiumAssetBrief() {
        return premiumAssetBrief;
    }

    @Override
    public PremiumAssetExpiration getPremiumAssetExpiration() {
        return premiumAssetExpiration;
    }

    public long getExtensionDuration() {
        return extensionDuration;
    }

    public ExpirationUnit getExpirationUnit() {
        return expirationUnit;
    }

    private void setPremiumAssetExpiration(
            final PremiumAssetExpiration premiumAssetExpiration
    ) {
        this.premiumAssetExpiration = premiumAssetExpiration;
    }

    @Override
    public void reconcileExpiration() {

        PremiumAssetExpiration originalExpiration = premiumAssetExpiration;

        setPremiumAssetExpiration(
                originalExpiration.reconcile(
                        PremiumAssetType.TIMED
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

        if (premiumAssetExpiration.getExpirationState() != ExpirationState.RECONCILED) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnsupportedOperationCause.of(
                                    PremiumAssetExpiration.Concept.EXPIRATION_STATE,
                                    premiumAssetExpiration.getExpirationState().displayName(),
                                    Method.EXTEND_EXPIRATION,
                                    PremiumAssetType.TIMED.description() +
                                    " '" + premiumAssetExpiration.getExpirationState().displayName() +
                                    "' must be [" + ExpirationState.RECONCILED.displayName() +
                                    "] for performance operation '" +
                                    Method.EXPIRE_EXPIRATION.displayName() + "'"
                            )
                    ),
                    Invariant.EXTEND_EXPIRATION_IS_ONLY_ALLOWED_FOR_RECONCILED_EXPIRATION_STATE,
                    TimedPremiumAsset.class
            );
        }

        PremiumAssetExpiration originalExpiration = premiumAssetExpiration;

        setPremiumAssetExpiration(
                originalExpiration.extend(
                        extensionDuration,
                        expirationUnit
                )
        );

        addDomainEvent(
                ExpirationExtendedEvent.of(
                        this,
                        originalExpiration
                )
        );

    }

    @Override
    public void expireExpiration() {

        if (premiumAssetExpiration.getExpirationState() != ExpirationState.RECONCILED) {
            throw new InvariantRuleViolationException(
                    PremiumAssetModuleMetadata.CORE,
                    DomainViolation.of(
                            UnsupportedOperationCause.of(
                                    PremiumAssetExpiration.Concept.EXPIRATION_STATE,
                                    premiumAssetExpiration.getExpirationState().displayName(),
                                    Method.EXPIRE_EXPIRATION,
                                    PremiumAssetType.TIMED.description() +
                                    " '" + premiumAssetExpiration.getExpirationState().displayName() +
                                    "' must be [" + ExpirationState.RECONCILED.displayName() +
                                    "] for performance operation '" +
                                    Method.EXPIRE_EXPIRATION.displayName() + "'"
                            )
                    ),
                    Invariant.EXPIRE_EXPIRATION_IS_ONLY_ALLOWED_FOR_RECONCILED_EXPIRATION_STATE,
                    TimedPremiumAsset.class
            );
        }

        PremiumAssetExpiration originalExpiration = premiumAssetExpiration;

        setPremiumAssetExpiration(
                originalExpiration.expire()
        );

        addDomainEvent(
                ExpirationExpiredEvent.of(
                        this,
                        originalExpiration
                )
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

    public static enum Concept implements DomainConcept {
        EXTENSION_DURATION("ExtensionDuration"),
        EXPIRATION_UNIT("ExpirationUnit");

        private final String displayName;

        private Concept(final String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

}
