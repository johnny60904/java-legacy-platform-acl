package com.dxlan.acl.features.shared.collections;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public final class CollectionPredicate {

    private CollectionPredicate() {
        throw new AssertionError();
    }

    public static <T extends Collection<?>> boolean isEmpty(
            final T collection
    ) {
        CollectionValidator.validateNotNull(collection);
        if (
                (collection instanceof ConcurrentLinkedQueue) ||
                (collection instanceof ConcurrentSkipListSet)
        ) {
            return collection.iterator().hasNext();
        }
        return collection.isEmpty();
    }

    public static <T extends Collection<?>> boolean hasElements(
            final T collection
    ) {
        return !CollectionPredicate.isEmpty(collection);
    }

    public static <E, T extends Collection<E>> boolean hasNullElement(
            final T collection
    ) {
        if (CollectionPredicate.isEmpty(collection)) return false;
        CollectionValidator.validateSizeBelowSecurityBound(collection);
        for (E element : collection) {
            if (element == null) return true;
        }
        return false;
    }

    /// or hasNoNullElements / isAllNotNull
    public static <E, T extends Collection<E>> boolean hasNoneNull(
            final T collection
    ) {
        return !hasNullElement(collection);
    }

}
