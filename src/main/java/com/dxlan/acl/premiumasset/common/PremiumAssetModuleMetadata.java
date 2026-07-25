package com.dxlan.acl.premiumasset.common;

import com.dxlan.acl.features.shared.architectures.ArchitecturalScope;
import com.dxlan.acl.features.shared.boundaries.metadata.DxlanAclWorkspace;
import com.dxlan.acl.features.shared.domain.integrities.violations.ViolationModule;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationModule;

public enum PremiumAssetModuleMetadata implements ViolationModule, ValidationModule {

    CORE(
            "PremiumAsset",
            30
    );

    private final String moduleName;
    private final int moduleCode;

    private PremiumAssetModuleMetadata(
            final String moduleName,
            final int moduleCode
    ) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }

    public String moduleName() { return moduleName; }

    @Override
    public ArchitecturalScope scope() {
        return ArchitecturalScope.DDD_MODULE;
    }

    @Override
    public int moduleCode() {
        return moduleCode;
    }

    @Override
    public String systemName() {
        return DxlanAclWorkspace.SYSTEM_ROOT.systemName();
    }

}
