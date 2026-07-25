package com.dxlan.acl.features.shared.validations.engines;

import com.dxlan.acl.features.shared.validations.constraints.CommonArgumentRules;

import java.util.Collection;
import java.util.function.Supplier;

public final class ArgumentGuard {

    private ArgumentGuard() { throw new AssertionError(); }

    public static record CollectionCheck<T>(
            Collection<T> collection,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        public CollectionCheck<T> notNull() {
            ArgumentEngine.validate(
                    collection,
                    exceptionSupplier,
                    CommonArgumentRules.NOT_NULL
            );
            return this;
        }

        public CollectionCheck<T> hasAnyItems() {
            ArgumentEngine.validate(
                    collection,
                    exceptionSupplier,
                    CommonArgumentRules.HAS_ANY_ITEMS
            );
            return this;
        }
    }

    public static record TextCheck(
            String value,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        public TextCheck notNull() {
            ArgumentEngine.validate(
                    value,
                    exceptionSupplier,
                    CommonArgumentRules.NOT_NULL
            );
            return this;
        }

        public TextCheck hasText() {
            ArgumentEngine.validate(
                    value,
                    exceptionSupplier,
                    CommonArgumentRules.HAS_TEXT
            );
            return this;
        }
    }

    public static record NumberCheck<T extends Number>(
            T value,
            Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        public NumberCheck<T> notNull() {
            ArgumentEngine.validate(
                    value,
                    exceptionSupplier,
                    CommonArgumentRules.NOT_NULL
            );
            return this;
        }

        public NumberCheck<T> positiveOrZero() {
            ArgumentEngine.validate(
                    value,
                    exceptionSupplier,
                    CommonArgumentRules.POSITIVE_OR_ZERO
            );
            return this;
        }

        public NumberCheck<T> positive() {
            ArgumentEngine.validate(
                    value,
                    exceptionSupplier,
                    CommonArgumentRules.POSITIVE
            );
            return this;
        }
    }

    public static <T> CollectionCheck<T> forCollection(
            final Collection<T> collection,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        return new CollectionCheck<>(collection, exceptionSupplier);
    }

    public static TextCheck forText(
            final String value,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        return new TextCheck(value, exceptionSupplier);
    }

    public static <T extends Number> NumberCheck<T> forNumeric(
            final T value,
            final Supplier<? extends RuntimeException> exceptionSupplier
    ) {
        return new NumberCheck<>(value, exceptionSupplier);
    }

}
