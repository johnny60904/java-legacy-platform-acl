package com.dxlan.acl.features.userprofile;

import com.dxlan.acl.features.userprofile.queries.getusersessiondetails.GetUserSessionDetailsQuery;
import com.dxlan.acl.features.userprofile.queries.getusersessiondetails.UserSessionDetails;

public final class UserIdentityProfile {

    private UserIdentityProfile() {}

    public static UserSessionDetails getUserSessionDetails(
            final int clusterGroupId,
            final int sessionProcessId
    ) {
        return UserIdentityProfileContainer.getInstance()
                .getGetUserSessionDetailsHandler()
                .handle(
                GetUserSessionDetailsQuery.of(
                        clusterGroupId,
                        sessionProcessId
                )
        );
    }

}
