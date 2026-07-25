package com.dxlan.acl.premiumasset.domain.enums;

public enum PremiumAssetLifeCycle {

    TRANSIENT_BONUS(14, false),

    SEASONAL_REWARD(30, false),

    STANDARD_SUBSCRIPTION(90, true),

    PREMIUM_PERMANENT(0, false);

    private final long defaultDays;
    private final boolean revivable;

    private PremiumAssetLifeCycle(
            final long defaultDays,
            final boolean revivable
    ) {
        this.defaultDays = defaultDays;
        this.revivable = revivable;
    }

    public long defaultDays() {
        return defaultDays;
    }

    public boolean isRevivable() {
        return revivable;
    }

    public boolean hasExpiry() {
        return this != PREMIUM_PERMANENT;
    }

    public static final long[] ALLOWED_DAYS;

    static {
        PremiumAssetLifeCycle[] lifeCycles = PremiumAssetLifeCycle.values();
        long[] days = new long[lifeCycles.length];
        for (int i = 0; i < lifeCycles.length; i++)
            days[i] = lifeCycles[i].defaultDays;
        ALLOWED_DAYS = days;
    }

    public static boolean contains(
            final long days
    ) {
        for (long allowedDays : ALLOWED_DAYS)
            if (allowedDays == days) return true;
        return false;
    }

    public static ExpirationUnit getDefaultUnit() {
        return ExpirationUnit.DAY;
    }

}
