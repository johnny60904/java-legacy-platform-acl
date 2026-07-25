package com.dxlan.acl.features.userprofile.queries.getusersessiondetails;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;
import com.dxlan.acl.features.shared.boundaries.LegacyBoundaryDefender;
import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreFieldMetadata;
import com.dxlan.acl.features.shared.boundaries.metadata.LegacyCoreTypeMetadata;
import net.legacy.platform.core.cluster.PlatformGateway;
import net.legacy.platform.core.session.UserSession;

public final class LegacyPlatformUserSessionGateway implements UserSessionGateway {

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(LegacyPlatformUserSessionGateway.class);

    private LegacyPlatformUserSessionGateway() {}

    private static class Holder {
        private static final UserSessionGateway INSTANCE =
                new LegacyPlatformUserSessionGateway();
    }

    public static UserSessionGateway getInstance() {
        return Holder.INSTANCE;
    }

    private static int getIdentityProfileId(
            final UserSession legacyUserSession
    ) {
        int profileId = legacyUserSession.getClientIdentity().getProfileId();
        LegacyBoundaryDefender.requireIdentityProfileIdValid(VALIDATOR, profileId);
        return profileId;
    }

    private static String getIdentityProfileName(
            final UserSession legacyUserSession
    ) {
        String profileName = legacyUserSession.getClientIdentity().getProfileName();
        VALIDATOR.requireFieldHasText(
                profileName,
                LegacyCoreFieldMetadata.IDENTITY_PROFILE_NAME
        );
        return profileName;
    }

    private static String getSessionProcessName(
            final UserSession legacyUserSession
    ) {
        String processName = legacyUserSession.getSessionProcessName();
        VALIDATOR.requireFieldHasText(
                processName,
                LegacyCoreFieldMetadata.SESSION_PROCESS_NAME
        );
        return processName;
    }

    @Override
    public UserSessionDetails loadDetails(
            final int clusterGroupId,
            final int sessionProcessId
    ) {
        LegacyBoundaryDefender.requireClusterGroupIdValid(VALIDATOR, clusterGroupId);
        LegacyBoundaryDefender.requireSessionProcessIdValid(VALIDATOR, sessionProcessId);
        UserSession legacyUserSession = PlatformGateway.getInstance()
                .getClusterByGroupId(clusterGroupId)
                .locateUserSessionById(sessionProcessId);
        VALIDATOR.requireExternalTypeNotNull(
                legacyUserSession,
                LegacyCoreTypeMetadata.USER_SESSION
        );
        return UserSessionDetails.of(
                getIdentityProfileId(legacyUserSession),
                getIdentityProfileName(legacyUserSession),
                sessionProcessId,
                getSessionProcessName(legacyUserSession)
        );
    }

}
