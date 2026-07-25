package com.dxlan.acl.features.shared.validations.taxonomy;

import com.dxlan.acl.features.shared.exceptions.ErrorCause;

public interface ValidationCause extends ErrorCause {

    ValidationCategory category();

    ValidationTarget target();

}
