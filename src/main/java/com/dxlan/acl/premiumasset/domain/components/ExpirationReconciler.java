package com.dxlan.acl.premiumasset.domain.components;

import com.dxlan.acl.premiumasset.domain.enums.ExpirationType;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class ExpirationReconciler {

    private final Instant timeAnchor;
    private final PremiumAssetType premiumAssetType;

    private ExpirationReconciler(
            final Instant timeAnchor,
            final PremiumAssetType premiumAssetType
    ) {
        this.timeAnchor = timeAnchor;
        this.premiumAssetType = premiumAssetType;
    }

    private ExpirationReconciler(
            final PremiumAssetType premiumAssetType
    ) {
        this.timeAnchor = ExpirationType.BASELINE.toInstant();
        this.premiumAssetType = premiumAssetType;
    }

    public Instant reconcile() {
        return switch(premiumAssetType) {
            case TIMED -> timeAnchor.plus(
                    PremiumAssetLifeCycle.STANDARD_SUBSCRIPTION.defaultDays(),
                    ChronoUnit.DAYS
            );
            case PERMANENT -> ExpirationType.PERMANENT.toInstant();
        };
    }

    public static ExpirationReconciler of(
            final PremiumAssetType premiumAssetType
    ) {
        return new ExpirationReconciler(premiumAssetType);
    }

    public static ExpirationReconciler of(
            final Instant timeAnchor,
            final PremiumAssetType premiumAssetType
    ) {
        return new ExpirationReconciler(timeAnchor, premiumAssetType);
    }

}
