package com.dxlan.acl.features.shared.boundaries.metadata;

import com.dxlan.acl.features.shared.architectures.WorkspaceMetadata;

public enum DxlanAclWorkspace implements WorkspaceMetadata {

    SYSTEM_ROOT("Com.DXLan.ACL");

    private final String workspaceName;

    private DxlanAclWorkspace(
            final String workspaceName
    ) {
        this.workspaceName = workspaceName;
    }

    @Override
    public String systemName() {
        return workspaceName;
    }

}
