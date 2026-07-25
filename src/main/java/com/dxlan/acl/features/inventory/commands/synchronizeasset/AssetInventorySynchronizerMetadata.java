package com.dxlan.acl.features.inventory.commands.synchronizeasset;

import com.dxlan.acl.features.inventory.common.metadata.SynchronizeAssetSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;

record AssetInventorySynchronizerMetadata(
        boolean isInternalCall
) implements SynchronizeAssetSliceMetadata {

    private static final boolean INTERNAL_PASSPORT = true;

    AssetInventorySynchronizerMetadata(
            final boolean isInternalCall
    ) {
        if (isInternalCall != INTERNAL_PASSPORT) {
            throw new UnsupportedOperationException(
                    "Architectural metadata is a singleton. Use the Interface's getMetadata() instead."
            );
        }
        this.isInternalCall = isInternalCall;
    }

    private static final SynchronizeAssetSliceMetadata INSTANCE =
            new AssetInventorySynchronizerMetadata(INTERNAL_PASSPORT);

    static SynchronizeAssetSliceMetadata getInstance() {
        return INSTANCE;
    }

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
        return ArchitecturalStereotype.CONTRACT;
    }

    @Override
    public LanguageElement languageElement() {
        return LanguageElement.INTERFACE;
    }

    @Override
    public String typeName() {
        return AssetInventorySynchronizer.class.getSimpleName();
    }

}
