package com.dxlan.acl.premiumasset.infrastructure.persistence;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.boundaries.LegacyBoundaryDefender;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.features.shared.numeric.NumericGuard;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.entities.TimedPremiumAsset;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;
import com.dxlan.acl.premiumasset.domain.valueobjects.PremiumAssetExpiration;
import com.dxlan.acl.premiumasset.infrastructure.persistence.translations.AclPremiumAssetMapper;
import com.dxlan.acl.premiumasset.infrastructure.persistence.translations.ExpirationTranslator;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.PremiumAssetMetadata;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

public final class LegacyPlatformPremiumAssetRepository implements PremiumAssetRepository {

    private static final Class<LegacyPlatformPremiumAssetRepository> CLAZZ =
            LegacyPlatformPremiumAssetRepository.class;

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(CLAZZ);

    private LegacyPlatformPremiumAssetRepository() { throw new AssertionError(); }

    private static final class Holder {
        private static final LegacyPlatformPremiumAssetRepository INSTANCE =
                new LegacyPlatformPremiumAssetRepository();
    }

    public static LegacyPlatformPremiumAssetRepository getInstance() {
        return Holder.INSTANCE;
    }

    private static void validateTimeAnchorAndTimeZoneNotNull(
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        Objects.requireNonNull(
                timeAnchor,
                PremiumAssetExpiration.Concept.TIME_ANCHOR.displayName() +
                        " must be specified."
        );
        Objects.requireNonNull(
                timeZone,
                PremiumAssetExpiration.Concept.TIME_ZONE.displayName() +
                        " must be specified."
        );
    }

    private static void validateInputs(
            final int clusterGroupId,
            final int sessionProcessId,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        LegacyBoundaryDefender.requireClusterGroupIdValid(VALIDATOR, clusterGroupId);
        LegacyBoundaryDefender.requireSessionProcessIdValid(VALIDATOR, sessionProcessId);
        validateTimeAnchorAndTimeZoneNotNull(timeAnchor, timeZone);
    }

    private static void validateExtensionDurationPositive(
          final long extensionDuration
    ) {
        NumericGuard.requirePositive(
                extensionDuration,
                TimedPremiumAsset.Concept.EXTENSION_DURATION.displayName()
        );
    }

    private static void validateExpirationUnitNotNull(
          final ExpirationUnit expirationUnit
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                expirationUnit,
                ExpirationUnit.getMetadata()
        );
    }

    private static void validateInputs(
            final int clusterGroupId,
            final int sessionProcessId,
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(clusterGroupId, sessionProcessId, timeAnchor, timeZone);
        validateExtensionDurationPositive(extensionDuration);
        validateExpirationUnitNotNull(expirationUnit);
    }

    private static void validateAclPremiumAssetNotNull(
            final AclPremiumAsset aclPremiumAsset
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                aclPremiumAsset,
                AclPremiumAsset.getMetadata()
        );
    }

    private static PremiumAssetItem findPremiumAssetItemByIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex
    ) {
        return LegacyPlatformPremiumAssetBridge.findPremiumAssetItemByActiveIndex(
                clusterGroupId,
                sessionProcessId,
                activeAssetIndex,
                VALIDATOR,
                LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_REPOSITORY,
                CLAZZ
        );
    }

    private static PremiumAssetItem findPremiumAssetItemById(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId
    ) {
        return LegacyPlatformPremiumAssetBridge.findPremiumAssetItemByItemId(
                clusterGroupId,
                sessionProcessId,
                assetItemId,
                VALIDATOR,
                LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_REPOSITORY,
                CLAZZ
        );
    }

    private static PremiumAssetItem findPremiumAssetItemBySlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot
    ) {
        return LegacyPlatformPremiumAssetBridge.findPremiumAssetItemByStorageSlot(
                clusterGroupId,
                sessionProcessId,
                storageSlot,
                VALIDATOR,
                LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_REPOSITORY,
                CLAZZ
        );
    }

    private static PremiumAssetMetadata getPremiumAssetMetadata(
            final int assetItemId
    ) {
        return LegacyPlatformPremiumAssetBridge.getPremiumAssetMetadataByItemId(
                assetItemId,
                LegacyPlatformPremiumAssetBridge.BridgeTarget.PREMIUM_ASSET_REPOSITORY,
                CLAZZ
        );
    }

    private static int getAndValidatePremiumAssetItemId(
            final PremiumAssetItem legacyPremiumAssetItem
    ) {
        return LegacyBoundaryDefender.requirePremiumAssetItemIdValid(
                VALIDATOR,
                legacyPremiumAssetItem.getAssetItemId()
        );
    }

    @Override
    public AclPremiumAsset loadAclPremiumAssetByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(clusterGroupId, sessionProcessId, timeAnchor, timeZone);
        ClusterParameterGuard.requireActiveAssetIndexValid(activeAssetIndex);
        PremiumAssetItem legacyPremiumAssetItem =
                findPremiumAssetItemByIndex(clusterGroupId, sessionProcessId, activeAssetIndex);
        int assetItemId = getAndValidatePremiumAssetItemId(legacyPremiumAssetItem);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return AclPremiumAssetMapper.toAclPremiumAsset(
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                CLAZZ
        );
    }

    @Override
    public AclPremiumAsset loadAclPremiumAssetByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(clusterGroupId, sessionProcessId, timeAnchor, timeZone);
        LegacyBoundaryDefender.requirePremiumAssetItemIdValid(VALIDATOR, assetItemId);
        PremiumAssetItem legacyPremiumAssetItem =
                findPremiumAssetItemById(clusterGroupId, sessionProcessId, assetItemId);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return AclPremiumAssetMapper.toAclPremiumAsset(
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                CLAZZ
        );
    }

    @Override
    public AclPremiumAsset loadAclPremiumAssetByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(clusterGroupId, sessionProcessId, timeAnchor, timeZone);
        ClusterParameterGuard.requireStorageSlotValid(storageSlot);
        PremiumAssetItem legacyPremiumAssetItem =
                findPremiumAssetItemBySlot(clusterGroupId, sessionProcessId, storageSlot);
        int assetItemId = getAndValidatePremiumAssetItemId(legacyPremiumAssetItem);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return AclPremiumAssetMapper.toAclPremiumAsset(
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                CLAZZ
        );
    }

    @Override
    public AclPremiumAsset loadAclPremiumAssetByActiveIndex(
            final int clusterGroupId,
            final int sessionProcessId,
            final int activeAssetIndex,
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(
                clusterGroupId, sessionProcessId, extensionDuration,
                expirationUnit, timeAnchor, timeZone
        );
        ClusterParameterGuard.requireActiveAssetIndexValid(activeAssetIndex);
        PremiumAssetItem legacyPremiumAssetItem =
                findPremiumAssetItemByIndex(clusterGroupId, sessionProcessId, activeAssetIndex);
        int assetItemId = getAndValidatePremiumAssetItemId(legacyPremiumAssetItem);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return AclPremiumAssetMapper.toAclPremiumAsset(
                extensionDuration,
                expirationUnit,
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                CLAZZ
        );
    }

    @Override
    public AclPremiumAsset loadAclPremiumAssetByItemId(
            final int clusterGroupId,
            final int sessionProcessId,
            final int assetItemId,
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(
                clusterGroupId, sessionProcessId, extensionDuration,
                expirationUnit, timeAnchor, timeZone
        );
        PremiumAssetItem legacyPremiumAssetItem =
                findPremiumAssetItemById(clusterGroupId, sessionProcessId, assetItemId);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return AclPremiumAssetMapper.toAclPremiumAsset(
                extensionDuration,
                expirationUnit,
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                CLAZZ
        );
    }

    @Override
    public AclPremiumAsset loadAclPremiumAssetByStorageSlot(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        validateInputs(
                clusterGroupId, sessionProcessId, extensionDuration,
                expirationUnit, timeAnchor, timeZone
        );
        ClusterParameterGuard.requireStorageSlotValid(storageSlot);
        PremiumAssetItem legacyPremiumAssetItem =
                findPremiumAssetItemBySlot(clusterGroupId, sessionProcessId, storageSlot);
        int assetItemId = getAndValidatePremiumAssetItemId(legacyPremiumAssetItem);
        PremiumAssetMetadata legacyPremiumAssetMetadata = getPremiumAssetMetadata(assetItemId);
        return AclPremiumAssetMapper.toAclPremiumAsset(
                extensionDuration,
                expirationUnit,
                timeAnchor,
                timeZone,
                clusterGroupId,
                sessionProcessId,
                legacyPremiumAssetItem,
                legacyPremiumAssetMetadata,
                CLAZZ
        );
    }

    @Override
    public void saveExpiration(
            final AclPremiumAsset aclPremiumAsset
    ) {
        validateAclPremiumAssetNotNull(aclPremiumAsset);
        PremiumAssetItem legacyPremiumAssetItem = findPremiumAssetItemById(
                aclPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                aclPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                aclPremiumAsset.getPremiumAssetBrief().getAssetItemId()
        );
        legacyPremiumAssetItem.setExpirationTimestamp(
                ExpirationTranslator.toLegacyTimestamp(
                        aclPremiumAsset.getPremiumAssetExpiration().getExpirationTimestamp()
                )
        );
        legacyPremiumAssetItem.setTerminationTimestamp(
                ExpirationTranslator.toLegacyTimestamp(
                        aclPremiumAsset.getPremiumAssetExpiration().getTerminationTimestamp()
                )
        );
        legacyPremiumAssetItem.setRemainingHeartbeat(
                (int) aclPremiumAsset.getPremiumAssetExpiration().getRemainingHeartbeat()
        );
    }

}
