package com.dxlan.acl.premiumasset.infrastructure.persistence;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.boundaries.LegacyBoundaryDefender;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetDetails;
import com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails.PremiumAssetGateway;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;
import com.dxlan.acl.premiumasset.infrastructure.persistence.translations.PremiumAssetDetailsMapper;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.PremiumAssetMetadata;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

public final class LegacyPlatformPremiumAssetGateway implements PremiumAssetGateway {

    private static final Class<LegacyPlatformPremiumAssetGateway> CLAZZ =
            LegacyPlatformPremiumAssetGateway.class;

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(CLAZZ);

    private LegacyPlatformPremiumAssetGateway() {}

    private static final class Holder {
        private static final LegacyPlatformPremiumAssetGateway INSTANCE =
                new LegacyPlatformPremiumAssetGateway();
    }

    public static LegacyPlatformPremiumAssetGateway getInstance() {
        return Holder.INSTANCE;
    }

    private static void validateInputs(
            final int clusterGroupId,
            final int sessionProcessId,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        LegacyBoundaryDefender.requireClusterGroupIdValid(VALIDATOR, clusterGroupId);
        LegacyBoundaryDefender.requireSessionProcessIdValid(VALIDATOR, sessionProcessId);
        Objects.requireNonNull(
                timeAnchor,
                PremiumAssetExpiration.Concept.TIME_ANCHOR.displayName()
        );
        Objects.requireNonNull(
                timeZone,
                PremiumAssetExpiration.Concept.TIME_ZONE.displayName()
        );
    }

    private static PremiumAssetMetadata getPremiumAssetMetadata(
            final int assetItemId
    ) {
        return LegacyPlatformPremiumAssetBridge.getPremiumAssetMetadataByItemId(
                assetItemId,
                LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_GATEWAY,
                CLAZZ
        );
    }

    @Override
    public PremiumAssetDetails loadDetailsByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(clusterGroupId, sessionProcessId, timeAnchor, timeZone);
        ClusterParameterGuard.requireActiveAssetIndexValid(activeAssetIndex);
        PremiumAssetItem legacyPremiumAssetItem =
                LegacyPlatformPremiumAssetBridge.findPremiumAssetItemByActiveIndex(
                        clusterGroupId,
                        sessionProcessId,
                        activeAssetIndex,
                        VALIDATOR,
                        LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_GATEWAY,
                        CLAZZ
                );
        int assetItemId = legacyPremiumAssetItem.getAssetItemId();
        LegacyBoundaryDefender.requirePremiumAssetItemIdValid(VALIDATOR, assetItemId);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return PremiumAssetDetailsMapper.map(
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                timeAnchor,
                timeZone,
                CLAZZ
        );
    }

    @Override
    public PremiumAssetDetails loadDetailsByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(clusterGroupId, sessionProcessId, timeAnchor, timeZone);
        LegacyBoundaryDefender.requirePremiumAssetItemIdValid(VALIDATOR, assetItemId);
        PremiumAssetItem legacyPremiumAssetItem =
                LegacyPlatformPremiumAssetBridge.findPremiumAssetItemByItemId(
                        clusterGroupId,
                        sessionProcessId,
                        assetItemId,
                        VALIDATOR,
                        LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_GATEWAY,
                        CLAZZ
                );
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return PremiumAssetDetailsMapper.map(
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                timeAnchor,
                timeZone,
                CLAZZ
        );
    }

    @Override
    public PremiumAssetDetails loadDetailsByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(clusterGroupId, sessionProcessId, timeAnchor, timeZone);
        ClusterParameterGuard.requireStorageSlotValid(storageSlot);
        PremiumAssetItem legacyPremiumAssetItem =
                LegacyPlatformPremiumAssetBridge.findPremiumAssetItemByStorageSlot(
                        clusterGroupId,
                        sessionProcessId,
                        storageSlot,
                        VALIDATOR,
                        LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_GATEWAY,
                        CLAZZ
                );
        int assetItemId = legacyPremiumAssetItem.getAssetItemId();
        LegacyBoundaryDefender.requirePremiumAssetItemIdValid(VALIDATOR, assetItemId);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return PremiumAssetDetailsMapper.map(
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                timeAnchor,
                timeZone,
                CLAZZ
        );
    }

}
