package com.dxlan.acl.premiumasset.application.common.interfaces;

import com.dxlan.acl.features.shared.boundaries.metadata.DxlanAclWorkspace;
import com.dxlan.acl.features.shared.boundaries.metadata.InternalMetadata;

public interface PremiumAssetApplicationMetadata extends InternalMetadata {

    default String getSystemName() {
        return DxlanAclWorkspace.SYSTEM_ROOT.systemName();
    }

}
