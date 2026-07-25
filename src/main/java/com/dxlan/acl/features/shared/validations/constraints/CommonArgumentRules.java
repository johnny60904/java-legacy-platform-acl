package com.dxlan.acl.features.shared.validations.constraints;

import com.dxlan.acl.features.shared.collections.CollectionPredicate;
import com.dxlan.acl.features.shared.numeric.NumberPredicate;

import java.util.Collection;
import java.util.function.Supplier;

public final class CommonArgumentRules {

    private CommonArgumentRules() { throw new AssertionError(); }

    private static void validateNotNull(
            final Object object,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        if (object == null) {
            throw exceptionSupplier.get();
        }
    }

    private static <T> void validateHasAnyItems(
            final Collection<T> collection,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        if (!CollectionPredicate.hasElements(collection)) {
            throw exceptionSupplier.get();
        }
    }

    private static void validateHasText(
            final String value,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        if (value == null || value.isBlank()) {
            throw exceptionSupplier.get();
        }
    }

    private static void validatePositiveOrZero(
            final Number value,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        if (NumberPredicate.isNegative(value)) {
            throw exceptionSupplier.get();
        }
    }

    private static void validatePositive(
            final Number value,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        if (NumberPredicate.isNegativeOrZero(value)) {
            throw exceptionSupplier.get();
        }
    }

    public static final ArgumentRule<Object> NOT_NULL
            = CommonArgumentRules::validateNotNull;

    public static final ArgumentRule<Collection<?>> HAS_ANY_ITEMS
            = CommonArgumentRules::validateHasAnyItems;

    public static final ArgumentRule<String> HAS_TEXT
            = CommonArgumentRules::validateHasText;

    public static final ArgumentRule<Number> POSITIVE_OR_ZERO
            = CommonArgumentRules::validatePositiveOrZero;

    public static final ArgumentRule<Number> POSITIVE
            = CommonArgumentRules::validatePositive;


}
