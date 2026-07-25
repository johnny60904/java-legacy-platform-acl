package com.dxlan.acl.features.userprofile.queries.getusersessiondetails;

import com.dxlan.acl.features.userprofile.common.metadata.GetUserSessionDetailsSliceMetadata;

public interface UserSessionGateway {

    UserSessionDetails loadDetails(
            final int clusterGroupId,
            final int sessionProcessId
    );

    public static GetUserSessionDetailsSliceMetadata getMetadata() {
        return UserSessionGatewayMetadata.getInstance();
    }

}
