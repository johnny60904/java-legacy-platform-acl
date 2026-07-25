package com.dxlan.acl.features.shared.domain.integrities.violations;

import com.dxlan.acl.features.shared.architectures.ArchitecturalScope;
import com.dxlan.acl.features.shared.architectures.WorkspaceMetadata;
import com.dxlan.acl.features.shared.exceptions.ErrorModule;

public interface ViolationModule extends ErrorModule, WorkspaceMetadata {

    ArchitecturalScope scope();

}
