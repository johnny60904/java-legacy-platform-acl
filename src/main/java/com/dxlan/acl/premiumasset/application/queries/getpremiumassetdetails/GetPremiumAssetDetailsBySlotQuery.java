package com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails;

import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.validations.taxonomy.ValidationProperty;
import com.dxlan.acl.premiumasset.application.common.enums.PremiumAssetCommonParameter;
import com.dxlan.acl.premiumasset.application.common.interfaces.PremiumAssetApplicationMetadata;
import com.dxlan.acl.premiumasset.application.queries.validations.GetPremiumAssetDetailsQueryValidator;
import com.dxlan.acl.premiumasset.application.queries.validations.contexts.GetPremiumAssetDetailsValidationContext;

import java.time.Instant;
import java.time.ZoneId;

public record GetPremiumAssetDetailsBySlotQuery(
        int clusterGroupId,
        int sessionProcessId,
        int storageSlot,
        Instant timeAnchor,
        ZoneId timeZone
) {

    public GetPremiumAssetDetailsBySlotQuery {
        GetPremiumAssetDetailsQueryValidator.INSTANCE.validate(
                GetPremiumAssetDetailsValidationContext.of(
                        ValidationProperty.of(
                                clusterGroupId,
                                PremiumAssetCommonParameter.CLUSTER_GROUP_ID
                        ),
                        ValidationProperty.of(
                                sessionProcessId,
                                PremiumAssetCommonParameter.SESSION_PROCESS_ID
                        ),
                        ValidationProperty.of(
                                timeAnchor,
                                PremiumAssetCommonParameter.TIME_ANCHOR
                        ),
                        ValidationProperty.of(
                                timeZone,
                                PremiumAssetCommonParameter.TIME_ZONE
                        )
                ),
                GetPremiumAssetDetailsBySlotQuery.class
        );
        GetPremiumAssetDetailsQueryValidator.validateStorageSlotInRange(
                storageSlot,
                PremiumAssetCommonParameter.STORAGE_SLOT,
                GetPremiumAssetDetailsBySlotQuery.class
        );
    }

    public static GetPremiumAssetDetailsBySlotQuery of(
            final int clusterGroupId,
            final int sessionProcessId,
            final int storageSlot,
            final Instant timeAnchor,
            final ZoneId timeZone
    ) {
        return new GetPremiumAssetDetailsBySlotQuery(
                clusterGroupId,
                sessionProcessId,
                storageSlot,
                timeAnchor,
                timeZone
        );
    }

    private static record Metadata() implements PremiumAssetApplicationMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.DDD_MODULE;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.DDD;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.CLEAN_ARCHITECTURE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.CQRS_QUERY;
        }

        @Override
        public ArchitecturalStereotype stereotype() {
            return ArchitecturalStereotype.QUERY;
        }

        @Override
        public LanguageElement languageElement() {
            return LanguageElement.RECORD;
        }

        @Override
        public String typeName() {
            return GetPremiumAssetDetailsBySlotQuery.class.getSimpleName();
        }
    }

    private static final PremiumAssetApplicationMetadata METADATA = new Metadata();

    public static PremiumAssetApplicationMetadata getMetadata() {
        return METADATA;
    }

}
