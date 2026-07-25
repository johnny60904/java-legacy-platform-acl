package com.dxlan.acl.features.userprofile.queries.getusersessiondetails;

import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.userprofile.common.metadata.GetUserSessionDetailsSliceMetadata;

record UserSessionGatewayMetadata(
        boolean isInternalCall
) implements GetUserSessionDetailsSliceMetadata {

    private static final boolean INTERNAL_PASSPORT = true;

    UserSessionGatewayMetadata(
            final boolean isInternalCall
    ) {
        if (isInternalCall != INTERNAL_PASSPORT) {
            throw new UnsupportedOperationException(
                    "Architectural metadata is a singleton. Use the Interface's getMetadata() instead."
            );
        }
        this.isInternalCall = isInternalCall;
    }

    private static final GetUserSessionDetailsSliceMetadata INSTANCE =
            new UserSessionGatewayMetadata(INTERNAL_PASSPORT);

    static GetUserSessionDetailsSliceMetadata getInstance() {
        return INSTANCE;
    }

    @Override
    public ArchitecturalScope scope() {
        return ArchitecturalScope.SLICE_QUERY;
    }

    @Override
    public String systemName() {
        return getSystemName();
    }

    @Override
    public ArchitecturalParadigms paradigms() {
        return ArchitecturalParadigms.NONE;
    }

    @Override
    public ArchitecturalStyle style() {
        return ArchitecturalStyle.VERTICAL_SLICE;
    }

    @Override
    public ArchitecturalPattern pattern() {
        return ArchitecturalPattern.CQRS_QUERY;
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
        return UserSessionGateway.class.getSimpleName();
    }

}
