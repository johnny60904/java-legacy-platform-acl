package net.legacy.platform.core.identity;

/**
 * @deprecated
 * WARNING: The architectural package layering of the legacy platform has completely collapsed.
 * Deeply nested packages constantly introduce cyclic dependencies with higher-level scopes
 * (e.g., the Session layer directly referencing the core Profile/Identity layer).
 * <p>
 * This stub has been intentionally flattened into a cohesive packet to shield the modern
 * Hybrid Architecture from package contamination.
 */
@Deprecated(since = "v1.0.0", forRemoval = false)
public final class ClientIdentity {

    private int profileId = 1;
    private String profileName = "User";

    public ClientIdentity() {}

    public int getProfileId() {
        return profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileId(final int profileId) {
        this.profileId = profileId;
    }

    public void setProfileName(final String profileName) {
        this.profileName = profileName;
    }
}
