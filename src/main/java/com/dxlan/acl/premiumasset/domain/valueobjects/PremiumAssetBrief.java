package com.dxlan.acl.premiumasset.domain.valueobjects;

import com.dxlan.acl.features.shared.domain.ValueObject;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetType;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.PremiumAssetBriefIntegrityGuard;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetbrief.contexts.PremiumAssetBriefValidationContext;

import java.util.StringJoiner;
import java.util.stream.Stream;

public final class PremiumAssetBrief extends ValueObject {

    private final int assetItemId;
    private final String assetName;
    private final long lifespan;
    private final int clusterGroupId;
    private final int sessionProcessId;
    private final PremiumAssetType premiumAssetType;

    public PremiumAssetBrief(
            final int assetItemId,
            final String assetName,
            final long lifespan,
            final int clusterGroupId,
            final int sessionProcessId,
            final PremiumAssetType premiumAssetType
    ) {
        PremiumAssetBriefIntegrityGuard.INSTANCE.guardRules(
                PremiumAssetBriefValidationContext.of(
                        DomainConceptProperty.of(
                                assetItemId,
                                Concept.ASSET_ITEM_ID
                        ),
                        DomainConceptProperty.of(
                                assetName,
                                Concept.ASSET_NAME
                        ),
                        DomainConceptProperty.of(
                                lifespan,
                                Concept.LIFESPAN
                        ),
                        DomainConceptProperty.of(
                                clusterGroupId,
                                Concept.CLUSTER_GROUP_ID
                        ),
                        DomainConceptProperty.of(
                                sessionProcessId,
                                Concept.SESSION_PROCESS_ID
                        ),
                        DomainConceptProperty.of(
                                premiumAssetType,
                                Concept.PREMIUM_ASSET_TYPE
                        )
                ),
                PremiumAssetBrief.class
        );
        this.assetItemId = assetItemId;
        this.assetName = assetName;
        this.lifespan = lifespan;
        this.clusterGroupId = clusterGroupId;
        this.sessionProcessId = sessionProcessId;
        this.premiumAssetType = premiumAssetType;
    }

    public static PremiumAssetBrief of(
            final int assetItemId,
            final String assetName,
            final long lifespan,
            final int clusterGroupId,
            final int sessionProcessId,
            final PremiumAssetType premiumAssetType
    ) {
        return new PremiumAssetBrief(
                assetItemId,
                assetName,
                lifespan,
                clusterGroupId,
                sessionProcessId,
                premiumAssetType
        );
    }

    public int getAssetItemId() {
        return assetItemId;
    }

    public String getAssetName() {
        return assetName;
    }

    public long getLifespan() {
        return lifespan;
    }

    public int getClusterGroupId() {
        return clusterGroupId;
    }

    public int getSessionProcessId() {
        return sessionProcessId;
    }

    public PremiumAssetType getPremiumAssetType() {
        return premiumAssetType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "PremiumAssetBrief[", "]")
                .add("assetItemId=" + assetItemId)
                .add("assetName=" + assetName)
                .add("lifespan=" + lifespan +
                        " (" + PremiumAssetLifeCycle.getDefaultUnit().displayName() + "s)")
                .add("clusterGroupId=" + clusterGroupId)
                .add("sessionProcessId=" + sessionProcessId)
                .add("premiumAssetType=" + premiumAssetType)
                .toString();
    }

    @Override
    public Stream<Object> getEqualityComponents() {
        return Stream.of(
                assetItemId,
                assetName,
                lifespan,
                clusterGroupId,
                sessionProcessId,
                premiumAssetType
        );
    }

    public static enum Concept implements DomainConcept {
        ASSET_ITEM_ID("AssetItemId"),
        ASSET_NAME("AssetName"),
        LIFESPAN("Lifespan"),
        CLUSTER_GROUP_ID("ClusterGroupId"),
        SESSION_PROCESS_ID("SessionProcessId"),
        PREMIUM_ASSET_TYPE("PremiumAssetType");

        private final String displayName;

        private Concept(final String displayName) { this.displayName = displayName; }

        @Override
        public String displayName() { return displayName; }
    }

}
