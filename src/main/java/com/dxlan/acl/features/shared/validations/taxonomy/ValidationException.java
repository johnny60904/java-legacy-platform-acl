package com.dxlan.acl.features.shared.validations.taxonomy;

public abstract class ValidationException extends RuntimeException {

    protected ValidationException(
            final String message
    ) {
        super(message);
    }

}
