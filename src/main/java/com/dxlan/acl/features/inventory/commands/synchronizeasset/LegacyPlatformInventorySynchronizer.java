package com.dxlan.acl.features.inventory.commands.synchronizeasset;

import com.dxlan.acl.features.inventory.common.enums.AssetInventoryType;
import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.boundaries.LegacyBoundaryDefender;
import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreTypeMetadata;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import net.legacy.platform.core.cluster.PlatformGateway;
import net.legacy.platform.core.enums.AssetType;
import net.legacy.platform.core.enums.InventoryAction;
import net.legacy.platform.core.model.AssetEntity;
import net.legacy.platform.core.network.ContextDispatcher;
import net.legacy.platform.core.network.OutBoundPayload;
import net.legacy.platform.core.session.UserSession;

public final class LegacyPlatformInventorySynchronizer implements AssetInventorySynchronizer {

    private static final Class<LegacyPlatformInventorySynchronizer> CLAZZ =
            LegacyPlatformInventorySynchronizer.class;

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(CLAZZ);

    private LegacyPlatformInventorySynchronizer() {}

    private static class Holder {
        private static final LegacyPlatformInventorySynchronizer INSTANCE =
                new LegacyPlatformInventorySynchronizer();
    }

    public static LegacyPlatformInventorySynchronizer getInstance() {
        return Holder.INSTANCE;
    }

    private static void validateAssetNotNull(
          final AssetEntity legacyAsset
    ) {
        VALIDATOR.requireExternalTypeNotNull(
                legacyAsset,
                LegacyCoreTypeMetadata.ASSET
        );
    }

    private static void validateInputs(
            final int clusterGroupId,
            final int sessionProcessId,
            AssetInventoryType assetInventoryType
    ) {
        LegacyBoundaryDefender.requireClusterGroupIdValid(VALIDATOR, clusterGroupId);
        LegacyBoundaryDefender.requireSessionProcessIdValid(VALIDATOR, sessionProcessId);
        VALIDATOR.requireInternalTypeNotNull(
                assetInventoryType,
                AssetInventoryType.getMetadata()
        );
    }

    private static UserSession findUserSessionById(
            final int clusterGroupId,
            final int sessionProcessId
    ) {
        UserSession legacyUserSession = PlatformGateway.getInstance()
                .getClusterByGroupId(clusterGroupId)
                .locateUserSessionById(sessionProcessId);
        VALIDATOR.requireExternalTypeNotNull(
                legacyUserSession,
                LegacyCoreTypeMetadata.USER_SESSION
        );
        return legacyUserSession;
    }

    private static AssetEntity findHardwareAssetById(
            final UserSession legacyUserSession,
            final int hardwareId
    ) {
        AssetEntity legacyHardwareAsset = legacyUserSession
                .getHardwareRepository()
                .locateAssetById(hardwareId);
        validateAssetNotNull(legacyHardwareAsset);
        return legacyHardwareAsset;
    }

    private static AssetEntity findConsumableAssetById(
            final UserSession legacyUserSession,
            final int consumableId
    ) {
        AssetEntity legacyConsumableAsset = legacyUserSession
                .getConsumableRepository()
                .locateAssetById(consumableId);
        validateAssetNotNull(legacyConsumableAsset);
        return legacyConsumableAsset;
    }

    private static AssetEntity findMaterialAssetById(
            final UserSession legacyUserSession,
            final int materialId
    ) {
        AssetEntity legacyMaterialAsset = legacyUserSession
                .getGeneralMaterialRepository()
                .locateAssetById(materialId);
        validateAssetNotNull(legacyMaterialAsset);
        return legacyMaterialAsset;
    }

    private static AssetEntity findDeploymentKitById(
            final UserSession legacyUserSession,
            final int deploymentKitId
    ) {
        AssetEntity legacyDeploymentKit = legacyUserSession
                .getDeploymentKitRepository()
                .locateAssetById(deploymentKitId);
        validateAssetNotNull(legacyDeploymentKit);
        return legacyDeploymentKit;
    }

    private static AssetEntity findPremiumServiceById(
            final UserSession legacyUserSession,
            final int premiumServiceId
    ) {
        AssetEntity legacyPremiumService = legacyUserSession
                .getPremiumServiceRepository()
                .locateAssetById(premiumServiceId);
        validateAssetNotNull(legacyPremiumService);
        return legacyPremiumService;
    }

    private static AssetEntity findExtensionModuleById(
            final UserSession legacyUserSession,
            final int extensionModuleId
    ) {
        AssetEntity legacyExtensionModule = legacyUserSession
                .getExtensionModuleRepository()
                .locateAssetById(extensionModuleId);
        validateAssetNotNull(legacyExtensionModule);
        return legacyExtensionModule;
    }

    private static AssetEntity findHardwareAssetBySlot(
            final UserSession legacyUserSession,
            final int hardwareStorageSlot
    ) {
        AssetEntity legacyHardwareAsset = legacyUserSession
                .getHardwareRepository()
                .locateAssetBySlot(hardwareStorageSlot);
        validateAssetNotNull(legacyHardwareAsset);
        return legacyHardwareAsset;
    }

    private static AssetEntity findConsumableAssetBySlot(
            final UserSession legacyUserSession,
            final int consumableStorageSlot
    ) {
        AssetEntity legacyConsumableAsset = legacyUserSession
                .getConsumableRepository()
                .locateAssetBySlot(consumableStorageSlot);
        validateAssetNotNull(legacyConsumableAsset);
        return legacyConsumableAsset;
    }

    private static AssetEntity findMaterialAssetBySlot(
            final UserSession legacyUserSession,
            final int materialStorageSlot
    ) {
        AssetEntity legacyMaterialAsset = legacyUserSession
                .getGeneralMaterialRepository()
                .locateAssetBySlot(materialStorageSlot);
        validateAssetNotNull(legacyMaterialAsset);
        return legacyMaterialAsset;
    }

    private static AssetEntity findDeploymentKitBySlot(
            final UserSession legacyUserSession,
            final int deploymentKitStorageSlot
    ) {
        AssetEntity legacyDeploymentKit = legacyUserSession
                .getDeploymentKitRepository()
                .locateAssetBySlot(deploymentKitStorageSlot);
        validateAssetNotNull(legacyDeploymentKit);
        return legacyDeploymentKit;
    }

    private static AssetEntity findPremiumServiceBySlot(
            final UserSession legacyUserSession,
            final int premiumServiceStorageSlot
    ) {
        AssetEntity legacyPremiumService = legacyUserSession
                .getPremiumServiceRepository()
                .locateAssetBySlot(premiumServiceStorageSlot);
        validateAssetNotNull(legacyPremiumService);
        return legacyPremiumService;
    }

    private static AssetEntity findExtensionModuleBySlot(
            final UserSession legacyUserSession,
            final int extensionModuleStorageSlot
    ) {
        AssetEntity legacyExtensionModule = legacyUserSession
                .getExtensionModuleRepository()
                .locateAssetBySlot(extensionModuleStorageSlot);
        validateAssetNotNull(legacyExtensionModule);
        return legacyExtensionModule;
    }

    private static short resolveAssetInventoryPosition(
            final AssetEntity legacyAsset
    ) {
        int position = (legacyAsset.getAssetType() == AssetType.ACTIVE_DEPLOYED)
                ? - legacyAsset.getInventoryPosition() : legacyAsset.getInventoryPosition();
        if (position > Short.MAX_VALUE || position < Short.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "Inventory position out of bounds: " + position + "."
            );
        }
        return (short) position;
    }

    private static OutBoundPayload createPayload(
            final AssetEntity legacyAsset,
            final InventoryAction actionType,
            final short position
    ) {
        OutBoundPayload outBoundPayload = ContextDispatcher.dispatchInventoryAction(
                true,
                false,
                actionType,
                position,
                (short) 0,
                0,
                legacyAsset
        );
        VALIDATOR.requireExternalTypeNotNull(
                outBoundPayload,
                LegacyCoreTypeMetadata.OUTBOUND_PAYLOAD
        );
        return outBoundPayload;
    }

    private static void sendPayload(
            final UserSession legacyUserSession,
            final AssetEntity legacyAsset
    ) {
        short inventoryPosition = resolveAssetInventoryPosition(legacyAsset);
        legacyUserSession.write(
                createPayload(
                        legacyAsset,
                        InventoryAction.REMOVE,
                        inventoryPosition
                )
        );
        legacyUserSession.write(
                createPayload(
                        legacyAsset,
                        InventoryAction.ADD,
                        inventoryPosition
                )
        );
    }

    private static AssetEntity findActiveDeployedAssetById(
            final UserSession legacyUserSession,
            final int assetItemId
    ) {
        AssetEntity legacyActiveDeployedAsset = legacyUserSession
                .getActiveDeployedRepository()
                .locateAssetById(assetItemId);
        validateAssetNotNull(legacyActiveDeployedAsset);
        return legacyActiveDeployedAsset;
    }

    private static AssetEntity findAssetByItemId(
            final UserSession legacyUserSession,
            final int assetItemId,
            final AssetInventoryType assetInventoryType
    ) {
        return switch(assetInventoryType) {
            case HARDWARE -> findHardwareAssetById(legacyUserSession, assetItemId);
            case CONSUMABLES -> findConsumableAssetById(legacyUserSession, assetItemId);
            case GENERAL_MATERIALS -> findMaterialAssetById(legacyUserSession, assetItemId);
            case DEPLOYMENT_KIT -> findDeploymentKitById(legacyUserSession, assetItemId);
            case PREMIUM_SERVICE -> findPremiumServiceById(legacyUserSession, assetItemId);
            case EXTENSION_MODULE -> findExtensionModuleById(legacyUserSession, assetItemId);
        };
    }

    private static AssetEntity findAssetByStorageSlot(
            final UserSession legacyUserSession,
            final int storageSlot,
            final AssetInventoryType assetInventoryType
    ) {
        return switch(assetInventoryType) {
            case HARDWARE -> findHardwareAssetBySlot(legacyUserSession, storageSlot);
            case CONSUMABLES -> findConsumableAssetBySlot(legacyUserSession, storageSlot);
            case GENERAL_MATERIALS -> findMaterialAssetBySlot(legacyUserSession, storageSlot);
            case DEPLOYMENT_KIT -> findDeploymentKitBySlot(legacyUserSession, storageSlot);
            case PREMIUM_SERVICE -> findPremiumServiceBySlot(legacyUserSession, storageSlot);
            case EXTENSION_MODULE -> findExtensionModuleBySlot(legacyUserSession, storageSlot);
        };
    }

    @Override
    public void synchronizeToClientByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final AssetInventoryType assetInventoryType
    ) {
        validateInputs(clusterGroupId, sessionProcessId, assetInventoryType);
        LegacyBoundaryDefender.requireAssetItemIdValid(VALIDATOR, assetItemId);
        UserSession legacyUserSession = findUserSessionById(clusterGroupId, sessionProcessId);
        AssetEntity legacyAsset = findAssetByItemId(legacyUserSession, assetItemId, assetInventoryType);
        sendPayload(legacyUserSession, legacyAsset);
    }

    @Override
    public void synchronizeToClientByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final AssetInventoryType assetInventoryType
    ) {
        validateInputs(clusterGroupId, sessionProcessId, assetInventoryType);
        ClusterParameterGuard.requireStorageSlotValid(storageSlot);
        UserSession legacyUserSession = findUserSessionById(clusterGroupId, sessionProcessId);
        AssetEntity legacyAsset = findAssetByStorageSlot(legacyUserSession, storageSlot, assetInventoryType);
        sendPayload(legacyUserSession, legacyAsset);
    }

}
