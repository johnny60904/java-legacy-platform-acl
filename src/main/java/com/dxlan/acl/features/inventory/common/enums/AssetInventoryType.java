package com.dxlan.acl.features.inventory.common.enums;

import com.dxlan.acl.features.inventory.common.metadata.SynchronizeAssetSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.lookup.EnumLookups;
import com.dxlan.acl.features.shared.lookup.Lookupable;

import java.util.*;

public enum AssetInventoryType implements Lookupable {

    HARDWARE(0, null),
    CONSUMABLES(1, null),
    GENERAL_MATERIALS(2, Set.of("Materials")),
    DEPLOYMENT_KIT(3, Set.of("Deployment", "Kit")),
    PREMIUM_SERVICE(4, Set.of("Premium", "Service")),
    EXTENSION_MODULE(5, Set.of("Extension", "Module"));

    private final int inventoryIndex;
    private final Set<String> aliases;

    private AssetInventoryType(
            final int inventoryIndex,
            final Set<String> aliases
    ) {
        this.inventoryIndex = inventoryIndex;
        this.aliases = aliases;
    }

    public int getInventoryIndex() {
        return inventoryIndex;
    }

    @Override
    public Set<String> lookupKeys() {
        return aliases;
    }

    private static final AssetInventoryType[] CONSTANTS;
    private static final Map<String, AssetInventoryType> LOOKUP;
    private static final Map<Integer, AssetInventoryType> INVENTORY_INDEX_LOOKUP;
    public static final Set<String> INVARIANT_PARSABLE_TOKENS;

    static {
        AssetInventoryType[] constants = AssetInventoryType.values();
        CONSTANTS = constants;
        Map<String, AssetInventoryType> lookupMap = EnumLookups.buildLookupMap(constants);
        LOOKUP = lookupMap;
        INVARIANT_PARSABLE_TOKENS = EnumLookups.buildLookupKeySet(lookupMap);
        Map<Integer, AssetInventoryType> map = new HashMap<>();
        for (AssetInventoryType enumConstant : AssetInventoryType.values()) {
            map.put(enumConstant.inventoryIndex, enumConstant);
        }
        INVENTORY_INDEX_LOOKUP = Collections.unmodifiableMap(map);
    }

    public static Optional<AssetInventoryType> of(
            final String name
    ) {
        return EnumLookups.ofValue(name, LOOKUP);
    }

    public static AssetInventoryType ofTrusted(
            final String validatedName
    ) {
        return EnumLookups.ofTrustedValue(
                validatedName,
                LOOKUP,
                AssetInventoryType.class.getSimpleName() + "Token",
                AssetInventoryType.class.getSimpleName()
        );
    }

    public static Optional<AssetInventoryType> ofInventoryIndex(
            final int inventoryIndex
    ) {
        if (!INVENTORY_INDEX_LOOKUP.containsKey(inventoryIndex)) {
            return Optional.empty();
        }
        return Optional.ofNullable(
                INVENTORY_INDEX_LOOKUP.get(inventoryIndex)
        );
    }

    public static AssetInventoryType ofTrustedInventoryIndex(
            final int validatedInventoryIndex
    ) {
        return INVENTORY_INDEX_LOOKUP.get(validatedInventoryIndex);
    }

    private static record Metadata() implements SynchronizeAssetSliceMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.VERTICAL_SLICE;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.NOT_APPLICABLE;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.VERTICAL_SLICE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.NOT_APPLICABLE;
        }

        @Override
        public ArchitecturalStereotype stereotype() {
            return ArchitecturalStereotype.STRATEGY;
        }

        @Override
        public LanguageElement languageElement() {
            return LanguageElement.ENUM;
        }

        @Override
        public String typeName() {
            return AssetInventoryType.class.getSimpleName();
        }
    }

    private static final SynchronizeAssetSliceMetadata METADATA = new Metadata();

    public static SynchronizeAssetSliceMetadata getMetadata() {
        return METADATA;
    }

}
