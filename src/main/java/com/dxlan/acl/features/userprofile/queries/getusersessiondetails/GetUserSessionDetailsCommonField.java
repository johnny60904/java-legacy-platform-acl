package com.dxlan.acl.features.userprofile.queries.getusersessiondetails;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public enum GetUserSessionDetailsCommonField implements ValidationParameter {

    CLUSTER_GROUP_ID("ClusterGroupId"),
    SESSION_PROCESS_ID("SessionProcessId"),;

    private final String displayName;

    private GetUserSessionDetailsCommonField(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }

}
