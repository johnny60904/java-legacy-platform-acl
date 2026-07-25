package com.dxlan.acl.features.userprofile.queries.getusersessiondetails;

public record UserSessionDetails(
        int identityProfileId,
        String identityProfileName,
        int sessionProcessId,
        String sessionProcessName
) {

    public static UserSessionDetails of(
            final int identityProfileId,
            final String identityProfileName,
            final int sessionProcessId,
            final String sessionProcessName
    ) {
        return new UserSessionDetails(
                identityProfileId,
                identityProfileName,
                sessionProcessId,
                sessionProcessName
        );
    }

}
