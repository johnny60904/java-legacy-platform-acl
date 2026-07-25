package com.dxlan.acl.premiumasset.application.common.validations.rules;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public final class StorageSlotRangeRule {

    private StorageSlotRangeRule() {}

    public static record Definition(
            ValidationParameter parameter
    ) {

        public ValidationConstraint<Integer> create(
                final int storageSlot
        ) {
            return new ValidationConstraint.Leaf<>(
                    parameter.displayName(),
                    OperatorType.WITHIN,
                    storageSlot,
                    storage_Slot ->
                            storage_Slot >= ClusterPhysicsMetadata.DigitalInventory.SLOT_LOWER_BOUND &&
                            storage_Slot <= ClusterPhysicsMetadata.DigitalInventory.SLOT_UPPER_BOUND
            );
        }

        public String validationDescription() {
            return parameter.displayName() +
                    " must be within closed range: [" +
                    ClusterPhysicsMetadata.DigitalInventory.SLOT_LOWER_BOUND + ", " +
                    ClusterPhysicsMetadata.DigitalInventory.SLOT_UPPER_BOUND + "]";
        }

    }

    public static Definition of(
            final ValidationParameter parameter
    ) {
        return new Definition(parameter);
    }

}
