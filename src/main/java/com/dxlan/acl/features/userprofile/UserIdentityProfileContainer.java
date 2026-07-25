package com.dxlan.acl.features.userprofile;

import com.dxlan.acl.features.userprofile.queries.getusersessiondetails.GetUserSessionDetailsHandler;
import com.dxlan.acl.features.userprofile.queries.getusersessiondetails.UserSessionGateway;
import com.dxlan.acl.features.userprofile.queries.getusersessiondetails.LegacyPlatformUserSessionGateway;
import com.dxlan.acl.features.infrastructure.log.AclLogger;

public final class UserIdentityProfileContainer {

    private final GetUserSessionDetailsHandler handler;

    private UserIdentityProfileContainer() {
        UserSessionGateway userSessionGateway = LegacyPlatformUserSessionGateway.getInstance();
        this.handler = GetUserSessionDetailsHandler.of(userSessionGateway);
    }

    private static class Holder {
        private static final UserIdentityProfileContainer INSTANCE =
                new UserIdentityProfileContainer();
    }

    public static UserIdentityProfileContainer getInstance() {
        return Holder.INSTANCE;
    }

    public static void initialize() {
        AclLogger.info(
                UserIdentityProfileContainer.class,
                "Initializing UserIdentityProfileContainer..."
        );
        if (getInstance() != null) {
            AclLogger.info(
                    UserIdentityProfileContainer.class,
                    "UserIdentityProfileContainer initialized successfully."
            );
        }
    }

    public GetUserSessionDetailsHandler getGetUserSessionDetailsHandler() {
        return handler;
    }

}
