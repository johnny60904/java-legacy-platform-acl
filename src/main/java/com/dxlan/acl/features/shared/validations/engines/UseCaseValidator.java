package com.dxlan.acl.features.shared.validations.engines;

public interface UseCaseValidator<C> {

    void validate(
            final C context,
            final Class<?> useCaseClass
    );

}
