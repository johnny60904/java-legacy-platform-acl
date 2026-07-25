package com.dxlan.acl.features.shared.domain.integrities.violations;

import com.dxlan.acl.features.shared.exceptions.ErrorCause;

public interface ViolationCause extends ErrorCause {

    ViolationCategory category();

    ViolationTarget target();

}
