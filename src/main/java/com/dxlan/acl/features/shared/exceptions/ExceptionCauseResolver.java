package com.dxlan.acl.features.shared.exceptions;

public final class ExceptionCauseResolver {

    private ExceptionCauseResolver() {
        throw new AssertionError();
    }

    public static Throwable resolveRootCause(Throwable throwable) {
        if (null == throwable) {
            return null;
        }
        Throwable baseCause = throwable;
        Throwable cause = throwable.getCause();

        while (cause != null && cause != baseCause) {
            baseCause = cause;
            cause = cause.getCause();
        }

        return baseCause;
    }

}
