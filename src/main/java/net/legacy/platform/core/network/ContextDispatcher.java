package net.legacy.platform.core.network;

import net.legacy.platform.core.enums.InventoryAction;
import net.legacy.platform.core.model.AssetEntity;

import java.util.List;

public final class ContextDispatcher {

    private ContextDispatcher() {
        throw new AssertionError();
    }

    public static OutBoundPayload dispatchInventoryAction(
            final boolean isExclusiveRequest,
            final boolean retainInfoMetadata,
            final InventoryAction actionType,
            final short previousPosition,
            final short targetPosition,
            final int storageBagPosition,
            final AssetEntity assetEntity
    ) {
        return new OutBoundPayload();
    }

    public static OutBoundPayload dispatchInventoryAction(
            final boolean isExclusiveResult,
            final boolean retainInfoMetadata,
            final List<AssetOperationStub> assetOperations
    ) {
        return new OutBoundPayload();
    }

    public static OutBoundPayload dispatchGlobalPayload(
            final GlobalNotifier globalNotifier
    ) {
        return new OutBoundPayload();
    }

    public static final record AssetOperationStub(
            AssetEntity assetEntity,
            short oldPosition,
            short newPosition,
            InventoryAction actionType
    ) {
        public AssetOperationStub of(
                final AssetEntity assetEntity,
                final short oldPosition,
                final short newPosition,
                final InventoryAction actionType
        ) {
            return new AssetOperationStub(
                    assetEntity,
                    oldPosition,
                    newPosition,
                    actionType
            );
        }
    }

}

