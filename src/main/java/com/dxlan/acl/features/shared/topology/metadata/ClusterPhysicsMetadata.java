package com.dxlan.acl.features.shared.topology.metadata;

import java.util.Set;

public final class ClusterPhysicsMetadata {

    private ClusterPhysicsMetadata() {
        throw new AssertionError();
    }

    public static final class SystemCluster {
        private SystemCluster() { throw new AssertionError(); }
        public static final String ID_DEFAULT_NAME = "ClusterGroupId";
        public static final int ID_MIN_DIGIT = 1;
        public static final int ID_MAX_DIGIT = 3;
        public static final int ID_LOWER_BOUND = 0;
        public static final int ID_UPPER_BOUND = 100;
    }

    public static final class UserSession {
        private UserSession() { throw new AssertionError(); }
        public static final String ID_DEFAULT_NAME = "SessionProcessId";
        public static final int ID_MIN_DIGIT = 1;
        public static final int ID_MAX_DIGIT = 10;
        public static final int ID_LOWER_BOUND = 1;
        public static final int ID_UPPER_BOUND = Integer.MAX_VALUE;
    }

    public static final class ClientIdentity {
        private ClientIdentity() { throw new AssertionError(); }
        public static final String ID_DEFAULT_NAME = "IdentityProfileId";
        public static final int ID_MIN_DIGIT = 1;
        public static final int ID_MAX_DIGIT = 10;
        public static final int ID_UPPER_BOUND = Integer.MAX_VALUE;
    }

    public static final class AssetEntity {
        private AssetEntity() { throw new AssertionError(); }
        public static final String ID_DEFAULT_NAME = "AssetItemId";
        public static final int ID_MIN_DIGIT = 7;
        public static final int ID_MAX_DIGIT = 8;
        public static final int ID_FIRST_UNIVERSE_MIN = 1_000_000;
        public static final int ID_FIRST_UNIVERSE_MAX = 5_999_999;
        public static final int ID_SECOND_UNIVERSE_MIN = 9_000_000;
        public static final int ID_SECOND_UNIVERSE_MAX = 9_999_999;
        public static final Set<Integer> ID_ALLOWED_FIRST_DIGITS = Set.of(1, 2, 3, 4, 5, 9);
    }

    public static final class DigitalInventory {
        private DigitalInventory() { throw new AssertionError(); }
        public static final String SLOT_DEFAULT_NAME = "StorageSlot";
        public static final int SLOT_MIN_DIGIT = 1;
        public static final int SLOT_MAX_DIGIT = 3;
        public static final int SLOT_LOWER_BOUND = 1;
        public static final int SLOT_UPPER_BOUND = 128;
    }

    public static final class ActiveAsset {
        private ActiveAsset() { throw new AssertionError(); }
        public static final String NAME_DEFAULT = "ActiveAssetName";
        public static final String INDEX_DEFAULT_NAME = "ActiveAssetIndex";
        public static final int INDEX_DIGIT_COUNT = 1;
        public static final int INDEX_LOWER_BOUND = 0;
        public static final int INDEX_UPPER_BOUND = 2;
    }

    public static final class PremiumAssetItem {
        private PremiumAssetItem() { throw new AssertionError(); }
        public static final String ID_DEFAULT_NAME = "PremiumAssetItemId";
        public static final int ID_DIGIT_COUNT = 7;
        public static final int ID_FIRST_DIGIT = 5;
        public static final int ID_LOWER_BOUND = 5_000_000;
        public static final int ID_UPPER_BOUND = 5_009_999;
    }

}
