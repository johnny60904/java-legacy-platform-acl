package net.legacy.platform.core.model;

import net.legacy.platform.core.tme.Timestamp;

public final class PremiumAssetItem extends AssetEntity {

    private final ActiveAsset activeAsset = new ActiveAsset();
    private Timestamp terminationTimestamp = new Timestamp();
    private int remainingHeartbeat = 0;
    private boolean aclReconciledFlag = false;

    public PremiumAssetItem() {}

    public Timestamp getTerminationTimestamp() { return terminationTimestamp; }

    public int getRemainingHeartbeat() { return remainingHeartbeat; }

    public boolean isAclReconciled() { return aclReconciledFlag; }

    public void setTerminationTimestamp(
            final Timestamp terminationTimestamp
    ) {
        this.terminationTimestamp = terminationTimestamp;
    }

    public void setRemainingHeartbeat(
            final int remainingHeartbeat
    ) {
        this.remainingHeartbeat = remainingHeartbeat;
    }

    public String getActiveAssetName() {
        return activeAsset.getName();
    }

    public void setAclReconciledFlag(
            final boolean aclReconciledFlag
    ) {
        this.aclReconciledFlag = aclReconciledFlag;
    }
}
