package com.dxlan.acl.features.shared.collections;

import java.util.Collection;

public final class CollectionGuard {

    private CollectionGuard() {
        throw new AssertionError();
    }

    public static <T extends Collection<?>> T requireNotNull(
            final T collection,
            final String name
    ) {
        CollectionValidator.validateNotNull(collection, name);
        return collection;
    }

    public static <T extends Collection<?>> T requireNotNull(
            final T collection
    ) {
        CollectionValidator.validateNotNull(collection);
        return collection;
    }

    public static <T extends Collection<?>> T requireNotEmpty(
            final T collection,
            final String name
    ) {
        CollectionValidator.validateNotEmpty(collection, name);
        return collection;
    }

    public static <T extends Collection<?>> T requireNotEmpty(
            final T collection
    ) {
        CollectionValidator.validateNotEmpty(collection);
        return collection;
    }

    public static <E, T extends Collection<E>> T requireNonNullElements(
            final T collection,
            final String name
    ) {
        CollectionValidator.validateNoneNull(collection, name);
        return collection;
    }

    public static <E, T extends Collection<E>> T requireNonNullElements(
            final T collection
    ) {
        CollectionValidator.validateNoneNull(collection);
        return collection;
    }

    public static <E, T extends Collection<E>> T requireHas(
            final T collection,
            final E candidate,
            final String name,
            final String candidateName
    ) {
        CollectionValidator.validateHas(collection, candidate, name, candidateName);
        return collection;
    }

    public static <E, T extends Collection<E>> T requireHas(
            final T collection,
            final E candidate,
            final String candidateName
    ) {
        CollectionValidator.validateHas(collection, candidate, candidateName);
        return collection;
    }

    public static <E, T extends Collection<E>> T requireHas(
            final T collection,
            final E candidate
    ) {
        CollectionValidator.validateHas(collection, candidate);
        return collection;
    }

}
