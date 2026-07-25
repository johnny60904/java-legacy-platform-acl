package com.dxlan.acl.premiumasset.presentation.dependencyinjection;

import com.dxlan.acl.features.infrastructure.log.AclLogger;
import com.dxlan.acl.premiumasset.application.commands.expireexpiration.ExpirePremiumAssetExpirationHandler;
import com.dxlan.acl.premiumasset.application.commands.extendexpiration.ExtendPremiumAssetExpirationHandler;
import com.dxlan.acl.premiumasset.application.commands.reconcileexpiration.ReconcilePremiumAssetExpirationHandler;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.GetPremiumAssetDetailsHandler;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetGateway;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;
import com.dxlan.acl.premiumasset.infrastructure.persistence.LegacyPlatformPremiumAssetGateway;
import com.dxlan.acl.premiumasset.infrastructure.persistence.LegacyPlatformPremiumAssetRepository;

public final class PremiumAssetContainer {

    private final ExpirePremiumAssetExpirationHandler expirePremiumAssetExpirationHandler;
    private final ExtendPremiumAssetExpirationHandler extendPremiumAssetExpirationHandler;
    private final ReconcilePremiumAssetExpirationHandler reconcilePremiumAssetExpirationHandler;

    private final GetPremiumAssetDetailsHandler getPremiumAssetDetailsHandler;

    private PremiumAssetContainer() {
        PremiumAssetRepository premiumAssetRepository = LegacyPlatformPremiumAssetRepository.getInstance();
        this.expirePremiumAssetExpirationHandler = ExpirePremiumAssetExpirationHandler.of(premiumAssetRepository);
        this.extendPremiumAssetExpirationHandler = ExtendPremiumAssetExpirationHandler.of(premiumAssetRepository);
        this.reconcilePremiumAssetExpirationHandler = ReconcilePremiumAssetExpirationHandler.of(premiumAssetRepository);

        PremiumAssetGateway premiumAssetGateway = LegacyPlatformPremiumAssetGateway.getInstance();
        this.getPremiumAssetDetailsHandler = GetPremiumAssetDetailsHandler.of(premiumAssetGateway);
    }

    private static class Hodler {
        private static final PremiumAssetContainer INSTANCE = new PremiumAssetContainer();
    }

    public static PremiumAssetContainer getInstance() {
        return Hodler.INSTANCE;
    }

    public static void initialize() {
        AclLogger.info(
                PremiumAssetContainer.class,
                "Initializing PremiumAssetContainer..."
        );
        if (getInstance() != null) {
            AclLogger.info(
                    PremiumAssetContainer.class,
                    "PremiumAssetContainer initialized successfully."
            );
        }
    }

    public ExpirePremiumAssetExpirationHandler getExpirePremiumAssetExpirationHandler() {
        return expirePremiumAssetExpirationHandler;
    }

    public ExtendPremiumAssetExpirationHandler getExtendPremiumAssetExpirationHandler() {
        return extendPremiumAssetExpirationHandler;
    }

    public ReconcilePremiumAssetExpirationHandler getReconcilePremiumAssetExpirationHandler() {
        return reconcilePremiumAssetExpirationHandler;
    }

    public GetPremiumAssetDetailsHandler getGetPremiumAssetDetailsHandler() {
        return getPremiumAssetDetailsHandler;
    }
}
