package com.dxlan.acl.premiumasset.application.commands.reconcileexpiration;

import com.dxlan.acl.features.inventory.AssetInventory;
import com.dxlan.acl.features.inventory.common.enums.AssetInventoryType;
import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.events.ExpirationReconciledEvent;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;

import java.util.Locale;

public final class ReconcilePremiumAssetExpirationHandler {

    private static final Class<ReconcilePremiumAssetExpirationHandler> CLAZZ =
            ReconcilePremiumAssetExpirationHandler.class;

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(CLAZZ);

    private static final String INVENTORY_TYPE_TOKEN =
            AssetInventoryType.PREMIUM_SERVICE.name().toUpperCase(Locale.ROOT);

    private final PremiumAssetRepository premiumAssetRepository;

    private ReconcilePremiumAssetExpirationHandler(
            final PremiumAssetRepository premiumAssetRepository
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                premiumAssetRepository,
                PremiumAssetRepository.getMetadata()
        );
        this.premiumAssetRepository = premiumAssetRepository;
    }

    public void handle(
            final ReconcilePremiumAssetExpirationByIndexCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ReconcilePremiumAssetExpirationByIndexCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByActiveIndex(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.activeAssetIndex(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.reconcileExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationReconciledEvent reconciledEvent) {
                AclPremiumAsset reconciledPremiumAsset = reconciledEvent.getReconciledPremiumAsset();
                premiumAssetRepository.saveExpiration(reconciledPremiumAsset);
                AssetInventory.synchronizeByItemId(
                        reconciledPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        reconciledPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        reconciledPremiumAsset.getPremiumAssetBrief().getAssetItemId(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public void handle(
            final ReconcilePremiumAssetExpirationByIdCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ReconcilePremiumAssetExpirationByIdCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByItemId(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.assetItemId(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.reconcileExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationReconciledEvent reconciledEvent) {
                AclPremiumAsset reconciledPremiumAsset = reconciledEvent.getReconciledPremiumAsset();
                premiumAssetRepository.saveExpiration(reconciledPremiumAsset);
                AssetInventory.synchronizeByItemId(
                        reconciledPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        reconciledPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        reconciledPremiumAsset.getPremiumAssetBrief().getAssetItemId(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public void handle(
            final ReconcilePremiumAssetExpirationBySlotCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ReconcilePremiumAssetExpirationBySlotCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByStorageSlot(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.storageSlot(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.reconcileExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationReconciledEvent reconciledEvent) {
                AclPremiumAsset reconciledPremiumAsset = reconciledEvent.getReconciledPremiumAsset();
                premiumAssetRepository.saveExpiration(reconciledPremiumAsset);
                AssetInventory.synchronizeByStorageSlot(
                        reconciledPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        reconciledPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        command.storageSlot(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public static ReconcilePremiumAssetExpirationHandler of(
            final PremiumAssetRepository premiumAssetRepository
    ) {
        return new ReconcilePremiumAssetExpirationHandler(
                premiumAssetRepository
        );
    }

}
