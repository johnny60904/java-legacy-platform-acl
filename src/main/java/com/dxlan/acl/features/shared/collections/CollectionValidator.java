package com.dxlan.acl.features.shared.collections;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public final class CollectionValidator {

    private static final String DEFAULT_NAME = "Collection";

    private CollectionValidator() {
        throw new AssertionError();
    }

    private static void validateNameHasText(
            final String name
    ) {
        Objects.requireNonNull(name, "Name must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank.");
        }
    }

    public static void validateNotNull(
            final Object collection,
            final String name
    ) {
        validateNameHasText(name);
        Objects.requireNonNull(collection, name + " must not be null.");
    }

    public static void validateNotNull(
            final Object collection
    ) {
        validateNotNull(collection, DEFAULT_NAME);
    }

    public static <T extends Collection<?>> void validateNotEmpty(
            final T collection,
            final String name
    ) {
        validateNotNull(collection, name);
        boolean valid = true;
        if (
                (collection instanceof ConcurrentLinkedQueue) ||
                (collection instanceof ConcurrentSkipListSet)
        ) {
            if (!collection.iterator().hasNext()) valid = false;
        }
        if (collection.isEmpty()) valid = false;
        if (!valid) {
            throw new IllegalArgumentException(
                    name + " must not be empty."
            );
        }
    }

    public static <T extends Collection<?>> void validateNotEmpty(
            final T collection
    ) {
        validateNotEmpty(collection, DEFAULT_NAME);
    }

    public static <T extends Collection<?>> void validateSizeBelowSecurityBound(
            final T collection,
            final String name
    ) {
        validateNotNull(collection, name);
        if (collection.size() <= CollectionSizeSecurity.MAXIMUM_BOUND) return;
        throw new IllegalArgumentException(
                name + " size (" + collection.size() + ") is too large, rejected to prevent OOM."
        );
    }

    public static <T extends Collection<?>> void validateSizeBelowSecurityBound(
            final T collection
    ) {
        validateSizeBelowSecurityBound(collection, DEFAULT_NAME);
    }

    public static <E, T extends Collection<E>> void validateNoneNull(
            final T collection,
            final String name
    ) {
        validateNotEmpty(collection, name);
        for (E element : collection) {
            if (element == null) {
                throw new IllegalArgumentException(
                        name + " must not contain null elements."
                );
            }
        }
    }

    public static <E, T extends Collection<E>> void validateNoneNull(
            final T collection
    ) {
        validateNoneNull(collection, DEFAULT_NAME);
    }

    public static <E, T extends Collection<E>> void validateHas(
            final T collection,
            final E candidate,
            final String name,
            final String candidateName
    ) {
        validateNoneNull(collection, name);
        Objects.requireNonNull(candidate, "Candidate must not be null.");
        Objects.requireNonNull(candidateName, "Candidate name must not be null.");
        if (!collection.contains(candidate)) {
            throw new IllegalArgumentException(
                    name + " must contain " + candidateName + "."
            );
        }
    }

    public static <E, T extends Collection<E>> void validateHas(
            final T collection,
            final E candidate,
            final String candidateName
    ) {
        validateHas(collection, candidate, DEFAULT_NAME, candidateName);
    }

    public static <E, T extends Collection<E>> void validateHas(
            final T collection,
            final E candidate
    ) {
        validateHas(collection, candidate, DEFAULT_NAME, "required candidate");
    }

}
