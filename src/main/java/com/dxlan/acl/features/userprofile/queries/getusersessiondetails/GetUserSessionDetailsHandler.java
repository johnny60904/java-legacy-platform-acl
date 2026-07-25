package com.dxlan.acl.features.userprofile.queries.getusersessiondetails;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;

public final class GetUserSessionDetailsHandler {

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(GetUserSessionDetailsHandler.class);

    private final UserSessionGateway characterGateway;

    private GetUserSessionDetailsHandler(
            final UserSessionGateway characterGateway
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                characterGateway,
                UserSessionGateway.getMetadata()
        );
        this.characterGateway = characterGateway;
    }

    public UserSessionDetails handle(
            final GetUserSessionDetailsQuery query
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                query,
                GetUserSessionDetailsQuery.getMetadata()
        );
        return characterGateway.loadDetails(
                query.clusterGroupId(),
                query.sessionProcessId()
        );
    }

    public static GetUserSessionDetailsHandler of(
            final UserSessionGateway characterGateway
    ) {
        return new GetUserSessionDetailsHandler(
                characterGateway
        );
    }

}
