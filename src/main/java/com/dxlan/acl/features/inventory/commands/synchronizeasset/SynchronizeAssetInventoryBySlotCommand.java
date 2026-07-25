package com.dxlan.acl.features.inventory.commands.synchronizeasset;

import com.dxlan.acl.features.inventory.common.enums.AssetInventoryType;
import com.dxlan.acl.features.inventory.common.metadata.SynchronizeAssetSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.topology.ClusterParameterGuard;
import com.dxlan.acl.features.shared.text.StringConverter;
import com.dxlan.acl.features.shared.text.TextGuard;

public record SynchronizeAssetInventoryBySlotCommand(
        int clusterGroupId,
        int sessionProcessId,
        int storageSlot,
        String assetInventoryTypeToken
) {

    public SynchronizeAssetInventoryBySlotCommand {
        ClusterParameterGuard.requireClusterGroupIdValid(
                clusterGroupId,
                SynchronizeAssetSliceCommonField.CLUSTER_GROUP_ID.displayName()
        );
        ClusterParameterGuard.requireSessionProcessIdValid(
                sessionProcessId,
                SynchronizeAssetSliceCommonField.SESSION_PROCESS_ID.displayName()
        );
        ClusterParameterGuard.requireStorageSlotValid(
                storageSlot,
                SynchronizeAssetSliceCommonField.STORAGE_SLOT.displayName()
        );


        TextGuard.requireHasText(
                assetInventoryTypeToken,
                SynchronizeAssetSliceCommonField.ASSET_INVENTORY_TYPE_TOKEN.displayName()
        );
        TextGuard.requireAnyOf(
                StringConverter.toUpperInvariant(assetInventoryTypeToken),
                AssetInventoryType.INVARIANT_PARSABLE_TOKENS,
                SynchronizeAssetSliceCommonField.ASSET_INVENTORY_TYPE_TOKEN.displayName()
        );
    }

    public AssetInventoryType assetInventoryType() {
        return AssetInventoryType.ofTrusted(assetInventoryTypeToken);
    }

    public static SynchronizeAssetInventoryBySlotCommand of(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final String assetInventoryTypeToken
    ) {
        return new SynchronizeAssetInventoryBySlotCommand(
                clusterGroupId,
                sessionProcessId,
                storageSlot,
                assetInventoryTypeToken
        );
    }

    private static record Metadata() implements SynchronizeAssetSliceMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.SLICE_COMMAND;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.TRANSACTION_SCRIPT;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.VERTICAL_SLICE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.CQRS_COMMAND;
        }

        @Override
        public ArchitecturalStereotype stereotype() {
            return ArchitecturalStereotype.COMMAND;
        }

        @Override
        public LanguageElement languageElement() {
            return LanguageElement.RECORD;
        }

        @Override
        public String typeName() {
            return SynchronizeAssetInventoryBySlotCommand.class.getSimpleName();
        }
    }

    private static final SynchronizeAssetSliceMetadata METADATA = new Metadata();

    public static SynchronizeAssetSliceMetadata getMetadata() {
        return METADATA;
    }

}
