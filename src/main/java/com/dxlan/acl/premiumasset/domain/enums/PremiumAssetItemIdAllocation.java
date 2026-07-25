package com.dxlan.acl.premiumasset.domain.enums;

import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.numeric.NumberRange;

public enum PremiumAssetItemIdAllocation {

    CORE(
            NumberRange.closed(
                    ClusterPhysicsMetadata.PremiumAssetItem.ID_LOWER_BOUND,
                    ClusterPhysicsMetadata.PremiumAssetItem.ID_UPPER_BOUND
            )
    );

    private final NumberRange<Integer> idRange;

    private PremiumAssetItemIdAllocation(
            final NumberRange<Integer> idRange
    ) {
        this.idRange = idRange;
    }

    public NumberRange<Integer> idRange() {
        return idRange;
    }

    public boolean contains(
            final int id
    ) {
        return idRange.contains(id);
    }

}
