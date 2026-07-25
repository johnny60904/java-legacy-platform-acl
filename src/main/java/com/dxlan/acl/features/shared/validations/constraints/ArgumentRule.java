package com.dxlan.acl.features.shared.validations.constraints;

import java.util.function.Supplier;

@FunctionalInterface
public interface ArgumentRule<T> {

    void validate(
            final T value,
            final Supplier<? extends RuntimeException> exceptionSupplier
    );

}
