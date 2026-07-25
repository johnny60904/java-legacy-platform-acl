package com.dxlan.acl.features.shared.exceptions;

public interface ErrorCause {

    String toMessage();

    ErrorCategory category();

    ErrorTarget target();

    String value();

}
