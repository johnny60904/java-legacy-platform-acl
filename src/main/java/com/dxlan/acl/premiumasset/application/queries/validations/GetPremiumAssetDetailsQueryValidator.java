package com.dxlan.acl.premiumasset.application.queries.validations;

import com.dxlan.acl.features.shared.validations.causes.MissingFieldCause;
import com.dxlan.acl.features.shared.validations.causes.OutOfRangeCause;
import com.dxlan.acl.features.shared.validations.constraints.ValidationConstraint;
import com.dxlan.acl.features.shared.validations.engines.UseCaseValidator;
import com.dxlan.acl.features.shared.validations.taxonomy.InvalidQueryException;
import com.dxlan.acl.features.shared.validations.taxonomy.QueryValidation;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationClause;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;
import com.dxlan.acl.premiumasset.application.common.validations.rules.*;
import com.dxlan.acl.premiumasset.application.queries.validations.contexts.GetPremiumAssetDetailsValidationContext;
import com.dxlan.acl.premiumasset.common.PremiumAssetModuleMetadata;

import java.time.Instant;
import java.time.ZoneId;

public enum GetPremiumAssetDetailsQueryValidator implements
        UseCaseValidator<GetPremiumAssetDetailsValidationContext> {

    INSTANCE;

    /// PremiumAssetModuleMetadata.CORE
    public static enum Rule implements ValidationClause {
        /// MissingFieldCause
        TIME_ANCHOR_MUST_BE_PRESENT(
                "TimeAnchorMustBePresent",
                4
        ),
        /// MissingFieldCause
        TIME_ZONE_MUST_BE_PRESENT(
                "TimeZoneMustBePresent",
                5
        ),
        /// OutOfRangeCause
        CLUSTER_GROUP_ID_MUST_BE_IN_RANGE(
                "ClusterGroupIdMustBeInRange",
                7
        ),
        /// OutOfRangeCause
        SESSION_PROCESS_ID_MUST_BE_IN_RANGE(
                "SessionProcessIdMustBeInRange",
                8
        ),
        /// OutOfRangeCause
        ACTIVE_ASSET_INDEX_MUST_BE_IN_RANGE(
                "ActiveAssetIndexMustBeInRange",
                9
        ),
        /// OutOfRangeCause
        STORAGE_SLOT_MUST_BE_IN_RANGE(
                "StorageSlotMustBeInRange",
                10
        ),
        /// OutOfRangeCause
        ASSET_ITEM_ID_MUST_BE_IN_RANGE(
                "AssetItemIdMustBeInRange",
                11
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
            final Class<?> queryClass
    ) {
        if (timeAnchor == null) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            MissingFieldCause.ofNull(parameter)
                    ),
                    Rule.TIME_ANCHOR_MUST_BE_PRESENT,
                    queryClass
            );
        }
    }

    private static void validateTimeZonePresent(
            final ZoneId timeZone,
            final ValidationParameter parameter,
            final Class<?> queryClass
    ) {
        if (timeZone == null) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            MissingFieldCause.ofNull(parameter)
                    ),
                    Rule.TIME_ZONE_MUST_BE_PRESENT,
                    queryClass
            );
        }
    }

    private static void validateClusterGroupIdInRange(
            final int clusterGroupId,
            final ValidationParameter parameter,
            final Class<?> queryClass
    ) {
        ClusterGroupIdRangeRule.Definition definition =
                ClusterGroupIdRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(clusterGroupId);
        if (!constraint.isSatisfiedBy(clusterGroupId)) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(clusterGroupId),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.CLUSTER_GROUP_ID_MUST_BE_IN_RANGE,
                    queryClass
            );
        }
    }

    private static void validateSessionProcessIdInRange(
            final int sessionProcessId,
            final ValidationParameter parameter,
            final Class<?> queryClass
    ) {
        SessionProcessIdRangeRule.Definition definition =
                SessionProcessIdRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(sessionProcessId);
        if (!constraint.isSatisfiedBy(sessionProcessId)) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(sessionProcessId),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.SESSION_PROCESS_ID_MUST_BE_IN_RANGE,
                    queryClass
            );
        }
    }

    @Override
    public void validate(
            final GetPremiumAssetDetailsValidationContext context,
            final Class<?> queryClass
    ) {
        validateTimeAnchorPresent(
                context.timeAnchorProperty().value(),
                context.timeAnchorProperty().parameter(),
                queryClass
        );
        validateTimeZonePresent(
                context.timeZoneProperty().value(),
                context.timeZoneProperty().parameter(),
                queryClass
        );
        validateClusterGroupIdInRange(
                context.clusterGroupIdProperty().value(),
                context.clusterGroupIdProperty().parameter(),
                queryClass
        );
        validateSessionProcessIdInRange(
                context.sessionProcessIdProperty().value(),
                context.sessionProcessIdProperty().parameter(),
                queryClass
        );
    }

    public static void validateActiveAssetIndexInRange(
            final int activeAssetIndex,
            final ValidationParameter parameter,
            final Class<?> queryClass
    ) {
        ActiveAssetIndexIncRangeRule.Definition definition =
                ActiveAssetIndexIncRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(activeAssetIndex);
        if (!constraint.isSatisfiedBy(activeAssetIndex)) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(activeAssetIndex),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.ACTIVE_ASSET_INDEX_MUST_BE_IN_RANGE,
                    queryClass
            );
        }
    }

    public static void validateStorageSlotInRange(
            final int storageSlot,
            final ValidationParameter parameter,
            final Class<?> queryClass
    ) {
        StorageSlotRangeRule.Definition definition =
                StorageSlotRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(storageSlot);
        if (!constraint.isSatisfiedBy(storageSlot)) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(storageSlot),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.STORAGE_SLOT_MUST_BE_IN_RANGE,
                    queryClass
            );
        }
    }

    public static void validatePremiumAssetItemIdInRange(
            final int assetItemId,
            final ValidationParameter parameter,
            final Class<?> queryClass
    ) {
        PremiumAssetItemIdRangeRule.Definition definition =
                PremiumAssetItemIdRangeRule.of(parameter);
        ValidationConstraint<Integer> constraint = definition.create(assetItemId);
        if (!constraint.isSatisfiedBy(assetItemId)) {
            throw new InvalidQueryException(
                    PremiumAssetModuleMetadata.CORE,
                    QueryValidation.of(
                            OutOfRangeCause.of(
                                    parameter,
                                    String.valueOf(assetItemId),
                                    definition.validationDescription()
                            )
                    ),
                    Rule.ASSET_ITEM_ID_MUST_BE_IN_RANGE,
                    queryClass
            );
        }
    }

}
