package com.dxlan.acl.features.shared.validations.constraints;

import com.dxlan.acl.features.shared.common.OperatorType;

import java.util.function.Predicate;

public sealed interface ValidationConstraint<T> permits
        ValidationConstraint.And,
        ValidationConstraint.Or,
        ValidationConstraint.Not,
        ValidationConstraint.Leaf {

    boolean isSatisfiedBy(T candidate);

    default ValidationConstraint<T> and(
            final ValidationConstraint<T> other
    ) {
        return new And<>(this, other);
    }

    default ValidationConstraint<T> or(
            final ValidationConstraint<T> other
    ) {
        return new Or<>(this, other);
    }

    default ValidationConstraint<T> not() {
        return new Not<>(this);
    }

    record And<T>(
            ValidationConstraint<T> left,
            ValidationConstraint<T> right
    ) implements ValidationConstraint<T> {
        @Override
        public boolean isSatisfiedBy(final T candidate) {
            return left.isSatisfiedBy(candidate) &&
                    right.isSatisfiedBy(candidate);
        }
    }

    record Or<T>(
            ValidationConstraint<T> left,
            ValidationConstraint<T> right
    ) implements ValidationConstraint<T> {
        @Override
        public boolean isSatisfiedBy(final T candidate) {
            return left.isSatisfiedBy(candidate) ||
                    right.isSatisfiedBy(candidate);
        }
    }

    record Not<T>(
            ValidationConstraint<T> constraint
    ) implements ValidationConstraint<T> {
        @Override
        public boolean isSatisfiedBy(final T candidate) {
            return !constraint.isSatisfiedBy(candidate);
        }
    }

    record Leaf<T>(
            String key,
            OperatorType operator,
            Object value,
            Predicate<T> predicate
    ) implements ValidationConstraint<T> {
        @Override public boolean isSatisfiedBy(final T candidate) {
            return predicate.test(candidate);
        }
    }

}
