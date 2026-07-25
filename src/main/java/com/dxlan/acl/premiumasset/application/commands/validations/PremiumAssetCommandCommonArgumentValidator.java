package com.dxlan.acl.premiumasset.application.commands.validations;

import com.dxlan.acl.features.shared.validations.causes.MissingFieldCause;
import com.dxlan.acl.features.shared.validations.causes.OutOfRangeCause;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.engines.UseCaseValidator;
import com.dxlan.acl.features.shared.validations.taxonomy.CommandValidation;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidCommandException;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationClause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;
import com.dxlan.acl.premiumasset.application.commands.validations.contexts.PremiumAssetCommandValidationContext;
import com.dxlan.acl.premiumasset.application.common.validations.rules.*;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

public enum PremiumAssetCommandCommonArgumentValidator implements
        UseCaseValidator<PremiumAssetCommandValidationContext> {

    INSTANCE;

    /// PremiumAssetModuleMetadata.CORE
    public static enum Rule implements ValidationClause {
        /// MissingFieldCause
        TIME_ANCHOR_MUST_BE_PRESENT(
                "TimeAnchorMustBePresent",
                1
        ),
        /// MissingFieldCause
        TIME_ZONE_MUST_BE_PRESENT(
                "TimeZoneMustBePresent",
                2
        ),
        /// OutOfRangeCause
        CLUSTER_GROUP_ID_MUST_BE_IN_RANGE(
                "ClusterGroupIdMustBeInRange",
                1
        ),
        /// OutOfRangeCause
        SESSION_PROCESS_ID_MUST_BE_IN_RANGE(
                "SessionProcessIdMustBeInRange",
                2
        ),
        /// OutOfRangeCause
        ACTIVE_ASSET_INDEX_MUST_BE_IN_RANGE(
                "ActiveAssetIndexMustBeInRange",
                3
        ),
        /// OutOfRangeCause
        STORAGE_SLOT_MUST_BE_IN_RANGE(
                "StorageSlotMustBeInRange",
                4
        ),
        /// OutOfRangeCause
        ASSET_ITEM_ID_MUST_BE_IN_RANGE(
                "AssetItemIdMustBeInRange",
                5
        );

        private final String displayName;
        private final int serial;

        private Rule(
                final String displayName,
                final int serial
        ) {
            this.displayName = displayName;
            this.serial = serial;
        }

        @Override
        public int serialNumber() {
            return serial;
        }

        @Override
        public String displayName() {
            return displayName;
        }
    }

    private static void validateTimeAnchorPresent(
            final Instant timeAnchor,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        if (Objects.isNull(timeAnchor)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            MissingFieldCause.ofNull(parameter)
                    ),
                    Rule.TIME_ANCHOR_MUST_BE_PRESENT,
                    commandClass
            );
        }
    }

    private static void validateTimeZonePresent(
            final ZoneId timeZone,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        if (Objects.isNull(timeZone)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            MissingFieldCause.ofNull(parameter)
                    ),
                    Rule.TIME_ZONE_MUST_BE_PRESENT,
                    commandClass
            );
        }
    }

    private static void validateClusterGroupIdInRange(
            final int clusterGroupId,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        ClusterGroupIdRangeRule.Definition definition =
                ClusterGroupIdRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(clusterGroupId);
        if (!constraint.isSatisfiedBy(clusterGroupId)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(clusterGroupId),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.CLUSTER_GROUP_ID_MUST_BE_IN_RANGE,
                    commandClass
            );
        }
    }

    private static void validateSessionProcessIdInRange(
            final int sessionProcessId,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        SessionProcessIdRangeRule.Definition definition =
                SessionProcessIdRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(sessionProcessId);
        if (!constraint.isSatisfiedBy(sessionProcessId)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(sessionProcessId),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.SESSION_PROCESS_ID_MUST_BE_IN_RANGE,
                    commandClass
            );
        }
    }

    @Override
    public void validate(
            final PremiumAssetCommandValidationContext context,
            final Class<?> commandClass
    ) {
        validateTimeAnchorPresent(
                context.timeAnchorProperty().value(),
                context.timeAnchorProperty().parameter(),
                commandClass
        );
        validateTimeZonePresent(
                context.timeZoneProperty().value(),
                context.timeZoneProperty().parameter(),
                commandClass
        );
        validateClusterGroupIdInRange(
                context.clusterGroupIdProperty().value(),
                context.clusterGroupIdProperty().parameter(),
                commandClass
        );
        validateSessionProcessIdInRange(
                context.sessionProcessIdProperty().value(),
                context.sessionProcessIdProperty().parameter(),
                commandClass
        );
    }

    public static void validateActiveAssetIndexInRange(
            final int activeAssetIndex,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        ActiveAssetIndexIncRangeRule.Definition definition =
                ActiveAssetIndexIncRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(activeAssetIndex);
        if (!constraint.isSatisfiedBy(activeAssetIndex)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(activeAssetIndex),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.ACTIVE_ASSET_INDEX_MUST_BE_IN_RANGE,
                    commandClass
            );
        }
    }

    public static void validateStorageSlotInRange(
            final int storageSlot,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        StorageSlotRangeRule.Definition definition =
                StorageSlotRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(storageSlot);
        if (!constraint.isSatisfiedBy(storageSlot)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(storageSlot),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.STORAGE_SLOT_MUST_BE_IN_RANGE,
                    commandClass
            );
        }
    }

    public static void validatePremiumAssetItemIdInRange(
            final int assetItemId,
            final ValidationParameter parameter,
            final Class<?> commandClass
    ) {
        PremiumAssetItemIdRangeRule.Definition definition =
                PremiumAssetItemIdRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(assetItemId);
        if (!constraint.isSatisfiedBy(assetItemId)) {
            throw new InvalidCommandException(
                    PremiumAssetModuleMetadata.CORE,
                    CommandValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(assetItemId),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.ASSET_ITEM_ID_MUST_BE_IN_RANGE,
                    commandClass
            );
        }
    }

}
