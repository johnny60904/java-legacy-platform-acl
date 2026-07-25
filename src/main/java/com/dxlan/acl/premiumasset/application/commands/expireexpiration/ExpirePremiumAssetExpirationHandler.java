package com.dxlan.acl.premiumasset.application.commands.expireexpiration;

import com.dxlan.acl.features.inventory.AssetInventory;
import com.dxlan.acl.features.inventory.common.enums.AssetInventoryType;
import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.domain.BaseEvent;
import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.events.ExpirationExpiredEvent;
import com.dxlan.acl.premiumasset.domain.repository.PremiumAssetRepository;

import java.util.Locale;

public final class ExpirePremiumAssetExpirationHandler {

    private static final Class<ExpirePremiumAssetExpirationHandler> CLAZZ =
            ExpirePremiumAssetExpirationHandler.class;

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(CLAZZ);

    private static final String INVENTORY_TYPE_TOKEN =
            AssetInventoryType.PREMIUM_SERVICE.name().toUpperCase(Locale.ROOT);

    private final PremiumAssetRepository premiumAssetRepository;

    private ExpirePremiumAssetExpirationHandler(
            final PremiumAssetRepository premiumAssetRepository
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                premiumAssetRepository,
                PremiumAssetRepository.getMetadata()
        );
        this.premiumAssetRepository = premiumAssetRepository;
    }

    public void handle(
            final ExpirePremiumAssetExpirationByIndexCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ExpirePremiumAssetExpirationByIndexCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByActiveIndex(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.activeAssetIndex(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.expireExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationExpiredEvent expiredEvent) {
                AclPremiumAsset expiredPremiumAsset = expiredEvent.getExpiredPremiumAsset();
                premiumAssetRepository.saveExpiration(expiredPremiumAsset);
                AssetInventory.synchronizeByItemId(
                        expiredPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        expiredPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        expiredPremiumAsset.getPremiumAssetBrief().getAssetItemId(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public void handle(
            final ExpirePremiumAssetExpirationByIdCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ExpirePremiumAssetExpirationByIdCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByItemId(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.assetItemId(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.expireExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationExpiredEvent expiredEvent) {
                AclPremiumAsset expiredPremiumAsset = expiredEvent.getExpiredPremiumAsset();
                premiumAssetRepository.saveExpiration(expiredPremiumAsset);
                AssetInventory.synchronizeByItemId(
                        expiredPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        expiredPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        expiredPremiumAsset.getPremiumAssetBrief().getAssetItemId(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public void handle(
            final ExpirePremiumAssetExpirationBySlotCommand command
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                command,
                ExpirePremiumAssetExpirationBySlotCommand.getMetadata()
        );

        AclPremiumAsset aclPremiumAsset = premiumAssetRepository.loadAclPremiumAssetByStorageSlot(
                command.clusterGroupId(),
                command.sessionProcessId(),
                command.storageSlot(),
                command.timeAnchor(),
                command.timeZone()
        );

        aclPremiumAsset.expireExpiration();

        for (BaseEvent event : aclPremiumAsset.getEvents()) {
            if (event instanceof ExpirationExpiredEvent expiredEvent) {
                AclPremiumAsset expiredPremiumAsset = expiredEvent.getExpiredPremiumAsset();
                premiumAssetRepository.saveExpiration(expiredPremiumAsset);
                AssetInventory.synchronizeByStorageSlot(
                        expiredPremiumAsset.getPremiumAssetBrief().getClusterGroupId(),
                        expiredPremiumAsset.getPremiumAssetBrief().getSessionProcessId(),
                        command.storageSlot(),
                        INVENTORY_TYPE_TOKEN
                );
            }
        }

        aclPremiumAsset.clearEvents();
    }

    public static ExpirePremiumAssetExpirationHandler of(
            final PremiumAssetRepository premiumAssetRepository
    ) {
        return new ExpirePremiumAssetExpirationHandler(
                premiumAssetRepository
        );
    }

}
