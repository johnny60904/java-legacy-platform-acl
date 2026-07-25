package com.dxlan.acl.features.shared.architectures;

import com.dxlan.acl.features.shared.languages.LanguageElement;

public interface ArchitecturalMetadata extends WorkspaceMetadata {

    /// scope (Vertical Slice)
    ArchitecturalScope scope(); /// or context (Bounded Context) (DDD)

    /// Design Methodology / ArchitecturalParadigms
    ArchitecturalParadigms paradigms();

    ArchitecturalStyle style();

    ArchitecturalPattern pattern();

    ArchitecturalStereotype stereotype();

    LanguageElement languageElement();

}
