package com.dxlan.acl.premiumasset.domain.common;

import com.dxlan.acl.features.shared.boundaries.metadata.DxlanAclWorkspace;
import com.dxlan.acl.features.shared.boundaries.metadata.InternalMetadata;

public interface PremiumAssetDomainMetadata extends InternalMetadata {

    default String getSystemName() {
        return DxlanAclWorkspace.SYSTEM_ROOT.systemName();
    }

}
