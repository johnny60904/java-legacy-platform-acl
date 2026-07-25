package net.legacy.platform.core.identity;

public final class AccountEntity {

    private int credentialsId = 1;
    private String credentialsName = "Account";

    public AccountEntity() {}

    public int getCredentialsId() { return credentialsId; }

    public String getCredentialsName() { return credentialsName; }

    public void setCredentialsId(final int credentialsId) {
        this.credentialsId = credentialsId;
    }

    public void setCredentialsName(final String credentialsName) {
        this.credentialsName = credentialsName;
    }
}
