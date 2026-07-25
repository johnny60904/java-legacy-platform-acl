package com.dxlan.acl.features.shared.validations.taxonomy;

public record ValidationProperty<T>(
        T value,
        ValidationParameter parameter
) {

    public static <T> ValidationProperty<T> of(
            final T value,
            final ValidationParameter parameter
    ) {
        return new ValidationProperty<T>(value, parameter);
    }

}
