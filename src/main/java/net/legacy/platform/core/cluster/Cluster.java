package net.legacy.platform.core.cluster;

import net.legacy.platform.core.session.UserSession;
import net.legacy.platform.core.enums.ClusterGroup;

public final class Cluster {

    private final ClusterGroup group = ClusterGroup.PRIMARY_NODE_A;

    public Cluster() {}

    public UserSession locateUserSessionById(final int processId) { return new UserSession(); }

    public ClusterGroup getGroup() {
        return group;
    }
}
