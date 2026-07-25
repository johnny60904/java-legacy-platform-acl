package com.dxlan.acl.premiumasset.application.commands.extendexpiration;

import com.dxlan.acl.features.inventory.AssetInventory;
import com.dxlan.acl.features.inventory.common.enums.AssetInventoryType;
import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.events.ExpirationExtendedEvent;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;

import java.util.Locale;

public final class ExtendPremiumAssetExpirationHandler {

    private static final Class<ExtendPremiumAssetExpirationHandler> CLAZZ =
            ExtendPremiumAssetExpirationHandler.class;

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(CLAZZ);

    private static final String INVENTORY_TYPE_TOKEN =
            AssetInventoryType.PREMIUM_SERVICE.name().toUpperCase(Locale.ROOT);

    private final PremiumAssetRepository premiumAssetRepository;

    private ExtendPremiumAssetExpirationHandler(
            final PremiumAssetRepository premiumAssetRepository
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                premiumAssetRepository,
                PremiumAssetRepository.getMetadata()
        );
        this.premiumAssetRepository = premiumAssetRepository;
    }

    public void handle(
            final ExtendPremiumAssetExpirationByIndexCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ExtendPremiumAssetExpirationByIndexCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByActiveIndex(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.activeAssetIndex(),
                command.extensionDuration(),
                command.expirationUnit(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.extendExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationExtendedEvent extendedEvent) {
                AclPremiumAsset extendedPremiumAsset = extendedEvent.getExtendedPremiumAsset();
                premiumAssetRepository.saveExpiration(extendedPremiumAsset);
                AssetInventory.synchronizeByItemId(
                        extendedPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        extendedPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        extendedPremiumAsset.getPremiumAssetBrief().getAssetItemId(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public void handle(
            final ExtendPremiumAssetExpirationByIdCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ExtendPremiumAssetExpirationByIdCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByItemId(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.assetItemId(),
                command.extensionDuration(),
                command.expirationUnit(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.extendExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationExtendedEvent extendedEvent) {
                AclPremiumAsset extendedPremiumAsset = extendedEvent.getExtendedPremiumAsset();
                premiumAssetRepository.saveExpiration(extendedPremiumAsset);
                AssetInventory.synchronizeByItemId(
                        extendedPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        extendedPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        extendedPremiumAsset.getPremiumAssetBrief().getAssetItemId(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public void handle(
            final ExtendPremiumAssetExpirationBySlotCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ExtendPremiumAssetExpirationBySlotCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByStorageSlot(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.storageSlot(),
                command.extensionDuration(),
                command.expirationUnit(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.extendExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationExtendedEvent extendedEvent) {
                AclPremiumAsset extendedPremiumAsset = extendedEvent.getExtendedPremiumAsset();
                premiumAssetRepository.saveExpiration(extendedPremiumAsset);
                AssetInventory.synchronizeByStorageSlot(
                        extendedPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        extendedPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        command.storageSlot(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public static ExtendPremiumAssetExpirationHandler of(
            final PremiumAssetRepository premiumAssetRepository
    ) {
        return new ExtendPremiumAssetExpirationHandler(
                premiumAssetRepository
        );
    }

}