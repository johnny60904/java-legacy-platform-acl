package com.dxlan.acl.features.shared.domain.specifications;

import com.dxlan.acl.features.shared.common.OperatorType;

import java.util.function.Predicate;

public sealed interface Specification<T> permits
        Specification.And,
        Specification.Or,
        Specification.Not,
        Specification.Leaf {

    boolean isSatisfiedBy(final T candidate);

    default Specification<T> and(
            final Specification<T> other
    ) {
        return new And<>(this, other);
    }
    default Specification<T> or(
            final Specification<T> other
    ) {
        return new Or<>(this, other);
    }
    default Specification<T> not() {
        return new Not<>(this);
    }

    record And<T>(
            Specification<T> left,
            Specification<T> right
    ) implements Specification<T> {
        @Override
        public boolean isSatisfiedBy(final T candidate) {
            return left.isSatisfiedBy(candidate) &&
                    right.isSatisfiedBy(candidate);
        }
    }

    record Or<T>(
            Specification<T> left,
            Specification<T> right
    ) implements Specification<T> {
        @Override
        public boolean isSatisfiedBy(final T candidate) {
            return left.isSatisfiedBy(candidate) ||
                    right.isSatisfiedBy(candidate);
        }
    }

    record Not<T>(
            Specification<T> specification
    ) implements Specification<T> {
        @Override
        public boolean isSatisfiedBy(final T candidate) {
            return !specification.isSatisfiedBy(candidate);
        }
    }

    record Leaf<T>(
            String key,
            OperatorType operator,
            Object value,
            Predicate<T> predicate
    ) implements Specification<T> {
        @Override public boolean isSatisfiedBy(final T candidate) {
            return predicate.test(candidate);
        }
    }

}
