package com.dxlan.acl.features.shared.topology.constraints;

import com.dxlan.acl.features.shared.common.OperatorType;
import com.dxlan.acl.features.shared.topology.metadata.ClusterPhysicsMetadata;
import com.dxlan.acl.features.shared.numeric.NumericDigitCountMatcher;
import com.dxlan.acl.features.shared.numeric.NumberRange;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;

import java.util.Locale;

public final class StorageSlotValidityConstraint {

    private StorageSlotValidityConstraint() { throw new AssertionError(); }

    public static record Definition(
            String name
    ) {

        public ValidationConstraint<Integer> create(
                final int storageSlot
        ) {
            ValidationConstraint<Integer> isPositive = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.GREATER_THAN,
                    storageSlot,
                    storage_Slot -> storage_Slot > 0
            );

            ValidationConstraint<Integer> isWithinThreeIntegerDigits = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    storageSlot,
                    storage_Slot -> NumericDigitCountMatcher
                            .of(storage_Slot)
                            .isDigitCountWithin(
                                    ClusterPhysicsMetadata.DigitalInventory.SLOT_MIN_DIGIT,
                                    ClusterPhysicsMetadata.DigitalInventory.SLOT_MAX_DIGIT
                            )
            );

            ValidationConstraint<Integer> isWithin1To128 = new ValidationConstraint.Leaf<>(
                    name,
                    OperatorType.WITHIN,
                    storageSlot,
                    storage_Slot -> NumberRange.closed(
                            ClusterPhysicsMetadata.DigitalInventory.SLOT_LOWER_BOUND,
                            ClusterPhysicsMetadata.DigitalInventory.SLOT_UPPER_BOUND
                    ).contains(storage_Slot)
            );

            return isPositive
                    .and(isWithinThreeIntegerDigits)
                    .and(isWithin1To128);
        }

        public String requirementDescription() {
            return name +
                    " must be > 0" +
                    " and must be an " +
                    ClusterPhysicsMetadata.DigitalInventory.SLOT_MIN_DIGIT + " to " +
                    ClusterPhysicsMetadata.DigitalInventory.SLOT_MAX_DIGIT +
                    " digit number" +
                    " and must be within closed range:" +
                    " [" +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.DigitalInventory.SLOT_LOWER_BOUND
                    ) + ", " +
                    String.format(
                            Locale.ROOT,
                            "%,d",
                            ClusterPhysicsMetadata.DigitalInventory.SLOT_UPPER_BOUND
                    ) +
                    "]";
        }

    }

    public static Definition of(
            final String name
    ) {
        return new Definition(
                name
        );
    }

    public static Definition ofDefault() {
        return new Definition(
                ClusterPhysicsMetadata.DigitalInventory.SLOT_DEFAULT_NAME
        );
    }

}
