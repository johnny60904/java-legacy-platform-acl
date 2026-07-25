package com.dxlan.acl.premiumasset.domain.aggregate;

import com.dxlan.acl.features.shared.common.Operation;
import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.features.shared.domain.integrities.DomainInvariant;
import com.dxlan.acl.premiumasset.domain.common.PremiumAssetDomainMetadata;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetBrief;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;

import java.util.List;

public interface AclPremiumAsset {

    PremiumAssetBrief getPremiumAssetBrief();

    PremiumAssetExpiration getPremiumAssetExpiration();

    void reconcileExpiration();

    void extendExpiration();

    void expireExpiration();

    List<BaseEvent> getEvents();

    void clearEvents();

    /// PremiumAssetModuleMetadata.CORE
    static enum Invariant implements DomainInvariant {
        /// UnsupportedOperationCause
        EXTEND_EXPIRATION_IS_ONLY_ALLOWED_FOR_TIMED_ASSET(
                "ExtendExpirationIsOnlyAllowedForTimedASSET",
                1
        ),
        /// UnsupportedOperationCause
        EXPIRE_EXPIRATION_IS_ONLY_ALLOWED_FOR_TIMED_ASSET(
                "ExpireExpirationIsOnlyAllowedForTimedASSET",
                2
        ),
        /// UnsupportedOperationCause
        EXTEND_EXPIRATION_IS_ONLY_ALLOWED_FOR_RECONCILED_EXPIRATION_STATE(
                "ExtendExpirationIsOnlyAllowedForReconciledExpirationState",
                3
        ),
        /// UnsupportedOperationCause
        EXPIRE_EXPIRATION_IS_ONLY_ALLOWED_FOR_RECONCILED_EXPIRATION_STATE(
                "ExpireExpirationIsOnlyAllowedForReconciledExpirationState",
                4
        );

        private final String displayName;
        private final int serial;

        private Invariant(
                final String displayName,
                final int serial
        ) {
            this.displayName = displayName;
            this.serial = serial;
        }

        @Override
        public int serialNumber() {
            return serial;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

    static enum Method implements Operation {
        EXTEND_EXPIRATION("ExtendExpiration"),
        EXPIRE_EXPIRATION("ExpireExpiration");

        private final String displayName;

        private Method(
                final String displayName
        ) {
            this.displayName = displayName;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

    public static PremiumAssetDomainMetadata getMetadata() {
        return AclPremiumAssetMetadata.getInstance();
    }

}
