package com.dxlan.acl.features.shared.boundaries;

import com.dxlan.acl.features.shared.boundaries.metadata.ExternalFieldMetadata;
import com.dxlan.acl.features.shared.boundaries.metadata.ExternalMetadata;
import com.dxlan.acl.features.shared.boundaries.metadata.InternalMetadata;

public final class BoundaryMetadataDetailsFactory {

    private BoundaryMetadataDetailsFactory() { throw new AssertionError(); }

    public static String createForExternalType(
            final ExternalMetadata metadata
    ) {
        return "Dependency Type (external) [" +
                metadata.typeName() +
                "] from '" +
                metadata.sourceName() +
                "' ";
    }

    public static String createForExternalField(
            final ExternalFieldMetadata metadata
    ) {
        return "Field (external) [" +
                metadata.fieldName() +
                "] of type [" +
                metadata.typeName() +
                "] from '" +
                metadata.sourceName() +
                "' ";
    }

    public static String createForInternalType(
            final InternalMetadata metadata
    ) {
        return "Dependency Type (Internal) [" +
                metadata.typeName() +
                "] defined within [" + metadata.style() + "]" +
                " adhering to [" + metadata.paradigms() + "]" +
                ", operates under [" + metadata.pattern() + "]" +
                ", acting as a / an [" + metadata.stereotype() + "]" +
                " [" + metadata.languageElement() + "]" +
                " in scope [" + metadata.scope() + "]" +
                " within system (workspace) [" + metadata.systemName() + "] ";
    }

}
