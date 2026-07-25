package net.legacy.platform.core.cluster;

import com.dxlan.acl.features.inventory.AssetInventoryContainer;
import com.dxlan.acl.features.notification.ClientNotificationContainer;
import com.dxlan.acl.features.userprofile.UserIdentityProfileContainer;
import com.dxlan.acl.premiumasset.presentation.dependencyinjection.PremiumAssetContainer;

public final class PlatformGateway {

    public PlatformGateway() {}

    private static final PlatformGateway INSTANCE = new PlatformGateway();

    public static PlatformGateway getInstance() {
        return INSTANCE;
    }

    public Cluster getClusterByGroupId(
            final int groupId
    ) {
        return new Cluster();
    }

    private void initialize(String[] args) { }

    public static void main(String[] args) {
        getInstance().initialize(args);
        UserIdentityProfileContainer.initialize();
        ClientNotificationContainer.initialize();
        AssetInventoryContainer.initialize();
        PremiumAssetContainer.initialize();
    }

}
