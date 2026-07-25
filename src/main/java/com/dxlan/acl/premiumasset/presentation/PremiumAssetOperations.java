package com.dxlan.acl.premiumasset.presentation;

import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.features.shared.log.AclLogger;
import com.dxlan.acl.premiumasset.application.commands.expireexpiration.ExpirePremiumAssetExpirationByIdCommand;
import com.dxlan.acl.premiumasset.application.commands.expireexpiration.ExpirePremiumAssetExpirationByIndexCommand;
import com.dxlan.acl.premiumasset.application.commands.expireexpiration.ExpirePremiumAssetExpirationBySlotCommand;
import com.dxlan.acl.premiumasset.application.commands.extendexpiration.ExtendPremiumAssetExpirationByIdCommand;
import com.dxlan.acl.premiumasset.application.commands.extendexpiration.ExtendPremiumAssetExpirationByIndexCommand;
import com.dxlan.acl.premiumasset.application.commands.extendexpiration.ExtendPremiumAssetExpirationBySlotCommand;
import com.dxlan.acl.premiumasset.application.commands.reconcileexpiration.ReconcilePremiumAssetExpirationByIdCommand;
import com.dxlan.acl.premiumasset.application.commands.reconcileexpiration.ReconcilePremiumAssetExpirationByIndexCommand;
import com.dxlan.acl.premiumasset.application.commands.reconcileexpiration.ReconcilePremiumAssetExpirationBySlotCommand;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.GetPremiumAssetDetailsByIdQuery;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.GetPremiumAssetDetailsByIndexQuery;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.GetPremiumAssetDetailsBySlotQuery;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetDetails;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.presentation.dependencyinjection.PremiumAssetContainer;
import com.dxlan.acl.premiumasset.presentation.translations.PremiumAssetModuleCustomExceptionHandler;

import java.time.Instant;
import java.time.ZoneId;

public final class PremiumAssetOperations {

    private PremiumAssetOperations() { throw new AssertionError(); }

    private static void validateInputs(
            final int clusterGroupId,
            final int sessionProcessId
    ) {
        try {
            ClusterParameterGuard.requireClusterGroupIdValid(clusterGroupId);
            ClusterParameterGuard.requireSessionProcessIdValid(sessionProcessId);
        } catch (Throwable throwable) {
            String message = "A fatal error (critical data corruption) occurred:" +
                    " clusterGroupId (" +
                    clusterGroupId + ") or sessionProcessId (" +
                    sessionProcessId + ") is corrupted.";
            AclLogger.info(
                    AclPremiumAsset.class,
                    message
            );
            AclLogger.error(
                    AclPremiumAsset.class,
                    message,
                    throwable
            );
            throw throwable;
        }
    }

    public static void expireExpirationByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requireActiveAssetIndexValid(activeAssetIndex);

            PremiumAssetContainer.getInstance().getExpirePremiumAssetExpirationHandler().handle(
                    ExpirePremiumAssetExpirationByIndexCommand.of(
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            activeAssetIndex
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void expireExpirationByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requirePremiumAssetItemIdValid(assetItemId);

            PremiumAssetContainer.getInstance().getExpirePremiumAssetExpirationHandler().handle(
                    ExpirePremiumAssetExpirationByIdCommand.of(
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            assetItemId
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void expireExpirationByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requireStorageSlotValid(storageSlot);

            PremiumAssetContainer.getInstance().getExpirePremiumAssetExpirationHandler().handle(
                    ExpirePremiumAssetExpirationBySlotCommand.of(
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            storageSlot
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void extendExpirationByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final long extensionDuration,
            final String expirationUnitToken
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requireActiveAssetIndexValid(activeAssetIndex);

            PremiumAssetContainer.getInstance().getExtendPremiumAssetExpirationHandler().handle(
                    ExtendPremiumAssetExpirationByIndexCommand.of(
                            extensionDuration,
                            expirationUnitToken,
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            activeAssetIndex
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void extendExpirationByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final long extensionDuration,
            final String expirationUnitToken
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requirePremiumAssetItemIdValid(assetItemId);

            PremiumAssetContainer.getInstance().getExtendPremiumAssetExpirationHandler().handle(
                    ExtendPremiumAssetExpirationByIdCommand.of(
                            extensionDuration,
                            expirationUnitToken,
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            assetItemId
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void extendExpirationByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final long extensionDuration,
            final String expirationUnitToken
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requireStorageSlotValid(storageSlot);

            PremiumAssetContainer.getInstance().getExtendPremiumAssetExpirationHandler().handle(
                    ExtendPremiumAssetExpirationBySlotCommand.of(
                            extensionDuration,
                            expirationUnitToken,
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            storageSlot
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void reconcileExpirationByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requireActiveAssetIndexValid(activeAssetIndex);

            PremiumAssetContainer.getInstance().getReconcilePremiumAssetExpirationHandler().handle(
                    ReconcilePremiumAssetExpirationByIndexCommand.of(
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            activeAssetIndex
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void reconcileExpirationByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requirePremiumAssetItemIdValid(assetItemId);

            PremiumAssetContainer.getInstance().getReconcilePremiumAssetExpirationHandler().handle(
                    ReconcilePremiumAssetExpirationByIdCommand.of(
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            assetItemId
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static void reconcileExpirationByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot
    ) {
        validateInputs(clusterGroupId, sessionProcessId);
        try {
            ClusterParameterGuard.requireStorageSlotValid(storageSlot);

            PremiumAssetContainer.getInstance().getReconcilePremiumAssetExpirationHandler().handle(
                    ReconcilePremiumAssetExpirationBySlotCommand.of(
                            Instant.now(),
                            ZoneId.systemDefault(),
                            clusterGroupId,
                            sessionProcessId,
                            storageSlot
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
        }
    }

    public static PremiumAssetDetails getDetailsByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex
    ) {
        validateInputs(clusterGroupId, sessionProcessId);

        try {
            ClusterParameterGuard.requireActiveAssetIndexValid(activeAssetIndex);

            return PremiumAssetContainer.getInstance().getGetPremiumAssetDetailsHandler().handle(
                    GetPremiumAssetDetailsByIndexQuery.of(
                            clusterGroupId,
                            sessionProcessId,
                            activeAssetIndex,
                            Instant.now(),
                            ZoneId.systemDefault()
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
            return null;
        }
    }

    public static PremiumAssetDetails getDetailsByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId
    ) {
        validateInputs(clusterGroupId, sessionProcessId);

        try {
            ClusterParameterGuard.requirePremiumAssetItemIdValid(assetItemId);

            return PremiumAssetContainer.getInstance().getGetPremiumAssetDetailsHandler().handle(
                    GetPremiumAssetDetailsByIdQuery.of(
                            clusterGroupId,
                            sessionProcessId,
                            assetItemId,
                            Instant.now(),
                            ZoneId.systemDefault()
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
            return null;
        }
    }

    public static PremiumAssetDetails getDetailsByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot
    ) {
        validateInputs(clusterGroupId, sessionProcessId);

        try {
            ClusterParameterGuard.requireStorageSlotValid(storageSlot);

            return PremiumAssetContainer.getInstance().getGetPremiumAssetDetailsHandler().handle(
                    GetPremiumAssetDetailsBySlotQuery.of(
                            clusterGroupId,
                            sessionProcessId,
                            storageSlot,
                            Instant.now(),
                            ZoneId.systemDefault()
                    )
            );
        } catch (Throwable throwable) {
            PremiumAssetModuleCustomExceptionHandler.handle(clusterGroupId, sessionProcessId, throwable);
            return null;
        }
    }

}
