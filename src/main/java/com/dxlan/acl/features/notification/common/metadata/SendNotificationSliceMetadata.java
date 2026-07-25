package com.dxlan.acl.features.notification.common.metadata;

import com.dxlan.acl.features.shared.boundaries.metadata.DxlanAclWorkspace;
import com.dxlan.acl.features.shared.boundaries.metadata.InternalMetadata;

public interface SendNotificationSliceMetadata extends InternalMetadata {

    default String getSystemName() {
        return DxlanAclWorkspace.SYSTEM_ROOT.systemName();
    }

}
