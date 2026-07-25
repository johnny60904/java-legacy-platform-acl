package com.dxlan.acl.features.shared.domain.integrities;

public interface DomaInIntegrityGuard<C> {

    void guardRules(
            final C context,
            final Class<?> modelClass
    );

}
