package com.dxlan.acl.features.shared.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class ValueObject {

    protected static boolean equalOperator(
            final ValueObject left,
            final ValueObject right
    ) {
        if (left == null ^ right == null) {
            return false;
        }
        return left != null && left.equals(right);
    }

    protected static boolean notEqualOperator(
            final ValueObject left,
            final ValueObject right
    ) {
        return !equalOperator(left, right);
    }

    protected abstract Stream<Object> getEqualityComponents();

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var other = (ValueObject) obj;

        Object[] thisComponents = getEqualityComponents().toArray();
        Object[] otherComponents = other.getEqualityComponents().toArray();

        return Arrays.equals(thisComponents, otherComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEqualityComponents().toArray());
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

}
