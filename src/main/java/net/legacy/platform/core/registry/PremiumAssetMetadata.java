package net.legacy.platform.core.registry;

public final class PremiumAssetMetadata {

    private int defaultLifespan = 0;
    private boolean isInfiniteLifespan = false;

    public PremiumAssetMetadata() {}

    public int getDefaultLifespan() { return defaultLifespan; }

    public boolean isInfiniteLifespan() { return isInfiniteLifespan; }

    public void setDefaultLifespan(
            final int defaultLifespan
    ) {
        this.defaultLifespan = defaultLifespan;
    }

    public void setInfiniteLifespan(
            final boolean infiniteLifespan
    ) {
        this.isInfiniteLifespan = infiniteLifespan;
    }
}
