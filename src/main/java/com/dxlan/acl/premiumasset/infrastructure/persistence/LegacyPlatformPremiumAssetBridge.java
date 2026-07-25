package com.dxlan.acl.premiumasset.infrastructure.persistence;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreTypeMetadata;
import net.legacy.platform.core.cluster.PlatformGateway;
import net.legacy.platform.core.model.ActiveAsset;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.AssetRegistry;
import net.legacy.platform.core.registry.PremiumAssetMetadata;
import net.legacy.platform.core.session.UserSession;

public final class LegacyPlatformPremiumAssetBridge {

    private LegacyPlatformPremiumAssetBridge() { throw new AssertionError(); }

    static enum BridgeTarget {
        PREMIUM_ASSET_REPOSITORY,
        PREMIUM_ASSET_GATEWAY;
    }

    private static void dispatchActiveAssetValidation(
            final ActiveAsset legacyActiveAsset,
            final Class<?> callerClass,
            final BridgeTarget bridgeTarget
    ) {
        switch(bridgeTarget) {
            case PREMIUM_ASSET_REPOSITORY ->
                    LegacyPlatformPremiumAssetRepositoryResourceValidator
                            .validateLegacyActiveAssetNotNull(
                                    legacyActiveAsset,
                                    callerClass
                            );
            case PREMIUM_ASSET_GATEWAY ->
                    LegacyPlatformPremiumAssetGatewayResourceValidator
                            .validateLegacyActiveAssetNotNull(
                                    legacyActiveAsset,
                                    callerClass
                            );
        };
    }

    private static void dispatchPremiumAssetItemValidation(
            final PremiumAssetItem legacyPremiumAssetItem,
            final Class<?> callerClass,
            final BridgeTarget bridgeTarget
    ) {
        switch (bridgeTarget) {
            case PREMIUM_ASSET_REPOSITORY ->
                    LegacyPlatformPremiumAssetRepositoryResourceValidator
                            .validateLegacyPremiumAssetItemNotNull(
                                    legacyPremiumAssetItem,
                                    callerClass
                            );
            case PREMIUM_ASSET_GATEWAY ->
                    LegacyPlatformPremiumAssetGatewayResourceValidator
                            .validateLegacyPremiumAssetItemNotNull(
                                    legacyPremiumAssetItem,
                                    callerClass
                            );
        };
    }

    private static void dispatchPremiumAssetMetadataValidation(
            final PremiumAssetMetadata legacyPremiumAssetMetadata,
            final Class<?> callerClass,
            final BridgeTarget bridgeTarget
    ) {
        switch (bridgeTarget) {
            case PREMIUM_ASSET_REPOSITORY ->
                    LegacyPlatformPremiumAssetRepositoryResourceValidator
                            .validateLegacyPremiumAssetMetadataNotNull(
                                    legacyPremiumAssetMetadata,
                                    callerClass
                            );
            case PREMIUM_ASSET_GATEWAY ->
                    LegacyPlatformPremiumAssetGatewayResourceValidator
                            .validateLegacyPremiumAssetMetadataNotNull(
                                    legacyPremiumAssetMetadata,
                                    callerClass
                            );
        };
    }

    static UserSession findUserSessionById(
            final int clusterGroupId,
            final int sessionProcessId,
            final BoundaryValidator validator
    ) {
        UserSession swordieCharacter = PlatformGateway
                .getInstance()
                .getClusterByGroupId(clusterGroupId)
                .locateUserSessionById(sessionProcessId);
        validator.requireExternalTypeNotNull(
                swordieCharacter,
                LegacyCoreTypeMetadata.USER_SESSION
        );
        return swordieCharacter;
    }

    static PremiumAssetItem findPremiumAssetItemByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final BoundaryValidator validator,
            final BridgeTarget bridgeTarget,
            final Class<?> callerClass
    ) {
        UserSession legacyUserSession = findUserSessionById(clusterGroupId, sessionProcessId, validator);
        ActiveAsset legacyActiveAsset = legacyUserSession.getActiveAssetByIndex(activeAssetIndex);
        dispatchActiveAssetValidation(legacyActiveAsset, callerClass, bridgeTarget);
        PremiumAssetItem legacyPremiumAssetItem = legacyActiveAsset.getPremiumAssetItem();
        dispatchPremiumAssetItemValidation(legacyPremiumAssetItem, callerClass, bridgeTarget);
        return legacyPremiumAssetItem;
    }

    static PremiumAssetItem findPremiumAssetItemByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final BoundaryValidator validator,
            final BridgeTarget bridgeTarget,
            final Class<?> callerClass
    ) {
        UserSession legacyUserSession = findUserSessionById(clusterGroupId, sessionProcessId, validator);
        PremiumAssetItem legacyPremiumAssetItem = (PremiumAssetItem) legacyUserSession
                .getPremiumServiceRepository()
                .locateAssetById(assetItemId);
        dispatchPremiumAssetItemValidation(legacyPremiumAssetItem, callerClass, bridgeTarget);
        return legacyPremiumAssetItem;
    }

    static PremiumAssetItem findPremiumAssetItemByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final BoundaryValidator validator,
            final BridgeTarget bridgeTarget,
            final Class<?> callerClass
    ) {
        UserSession legacyUserSession = findUserSessionById(clusterGroupId, sessionProcessId, validator);
        PremiumAssetItem legacyPremiumAssetItem = (PremiumAssetItem) legacyUserSession
                .getPremiumServiceRepository()
                .locateAssetBySlot(storageSlot);
        dispatchPremiumAssetItemValidation(legacyPremiumAssetItem, callerClass, bridgeTarget);
        return legacyPremiumAssetItem;
    }

    static PremiumAssetMetadata getPremiumAssetMetadataByItemId(
            final int assetItemId,
            final BridgeTarget bridgeTarget,
            final Class<?> callerClass
    ) {
        PremiumAssetMetadata legacyPremiumAssetMetadata = AssetRegistry.getAssetMetadataById(assetItemId);
        dispatchPremiumAssetMetadataValidation(legacyPremiumAssetMetadata, callerClass, bridgeTarget);
        return legacyPremiumAssetMetadata;
    }

}
