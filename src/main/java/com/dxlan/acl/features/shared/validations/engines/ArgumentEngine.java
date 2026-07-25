package com.dxlan.acl.features.shared.validations.engines;

import com.dxlan.acl.features.shared.validations.constraints.ArgumentRule;

import java.util.function.Supplier;

public final class ArgumentEngine {

    private ArgumentEngine() { throw new AssertionError(); }

    public static <T> void validate(
            final T value,
            final Supplier<? extends RuntimeException> exceptionSupplier,
            final ArgumentRule<T> rule
    ) {
        rule.validate(value, exceptionSupplier);
    }

}
