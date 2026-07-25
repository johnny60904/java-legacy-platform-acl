package com.dxlan.acl.features.userprofile.common.metadata;

import com.dxlan.acl.features.shared.boundaries.metadata.DxlanAclWorkspace;
import com.dxlan.acl.features.shared.boundaries.metadata.InternalMetadata;

public interface GetUserSessionDetailsSliceMetadata extends InternalMetadata {

    default String getSystemName() {
        return DxlanAclWorkspace.SYSTEM_ROOT.systemName();
    }

}
