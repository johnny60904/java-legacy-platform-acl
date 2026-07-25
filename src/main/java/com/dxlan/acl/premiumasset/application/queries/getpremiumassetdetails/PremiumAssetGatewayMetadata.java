package com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails;

import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.premiumasset.application.common.interfaces.PremiumAssetApplicationMetadata;

record PremiumAssetGatewayMetadata(
        boolean isInternalCall
) implements PremiumAssetApplicationMetadata {

    private static final boolean INTERNAL_PASSPORT = true;

    PremiumAssetGatewayMetadata {
        if (isInternalCall != INTERNAL_PASSPORT) {
            throw new UnsupportedOperationException(
                    "Architectural metadata is a singleton. Use the Interface's getMetadata() instead."
            );
        }
    }

    private static final PremiumAssetApplicationMetadata INSTANCE =
            new PremiumAssetGatewayMetadata(INTERNAL_PASSPORT);

    static PremiumAssetApplicationMetadata getInstance() {
        return INSTANCE;
    }

    @Override
    public ArchitecturalScope scope() {
        return ArchitecturalScope.DDD_MODULE;
    }

    @Override
    public String systemName() {
        return getSystemName();
    }

    @Override
    public ArchitecturalParadigms paradigms() {
        return ArchitecturalParadigms.DDD;
    }

    @Override
    public ArchitecturalStyle style() {
        return ArchitecturalStyle.CLEAN_ARCHITECTURE;
    }

    @Override
    public ArchitecturalPattern pattern() {
        return ArchitecturalPattern.NONE;
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
        return PremiumAssetGateway.class.getSimpleName();
    }

}
