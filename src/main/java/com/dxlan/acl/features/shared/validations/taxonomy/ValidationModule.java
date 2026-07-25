package com.dxlan.acl.features.shared.validations.taxonomy;

import com.dxlan.acl.features.shared.architectures.ArchitecturalScope;
import com.dxlan.acl.features.shared.architectures.WorkspaceMetadata;
import com.dxlan.acl.features.shared.exceptions.ErrorModule;

public interface ValidationModule extends ErrorModule, WorkspaceMetadata {

    ArchitecturalScope scope();

}
