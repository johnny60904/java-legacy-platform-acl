package com.dxlan.acl.premiumasset.infrastructure.persistence.translations;

import com.dxlan.acl.premiumasset.domain.aggregate.AclPremiumAsset;
import com.dxlan.acl.premiumasset.domain.entities.PermanentPremiumAsset;
import com.dxlan.acl.premiumasset.domain.entities.TimedPremiumAsset;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;
import net.legacy.platform.core.model.PremiumAssetItem;
import net.legacy.platform.core.registry.PremiumAssetMetadata;

import java.time.Instant;
import java.time.ZoneId;

public final class AclPremiumAssetMapper {

    private AclPremiumAssetMapper() { throw new AssertionError(); }

    public static AclPremiumAsset toAclPremiumAsset(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final int clusterGroupId,
            final int sessionProcessId,
            final PremiumAssetItem legacyPremiumAsset,
            final PremiumAssetMetadata legacyPremiumPremiumAssetMetadata,
            final Class<?> callerClass
    ) {
        boolean premiumAssetTypeFlag = legacyPremiumPremiumAssetMetadata.isInfiniteLifespan();
        if (premiumAssetTypeFlag) {
            return PermanentPremiumAsset.of(
                    PremiumAssetBriefMapper.validateAndMap(
                            clusterGroupId,
                            sessionProcessId,
                            legacyPremiumAsset,
                            legacyPremiumPremiumAssetMetadata,
                            callerClass
                    ),
                    PremiumAssetExpirationMapper.validateAndMap(
                            timeAnchor,
                            timeZone,
                            legacyPremiumAsset,
                            callerClass
                    )
            );
        } else {
            return TimedPremiumAsset.of(
                    PremiumAssetBriefMapper.validateAndMap(
                            clusterGroupId,
                            sessionProcessId,
                            legacyPremiumAsset,
                            legacyPremiumPremiumAssetMetadata,
                            callerClass
                    ),
                    PremiumAssetExpirationMapper.validateAndMap(
                            timeAnchor,
                            timeZone,
                            legacyPremiumAsset,
                            callerClass
                    )
            );
        }
    }

    public static AclPremiumAsset toAclPremiumAsset(
            final long extensionDuration,
            final ExpirationUnit expirationUnit,
            final Instant timeAnchor,
            final ZoneId timeZone,
            final int clusterGroupId,
            final int sessionProcessId,
            final PremiumAssetItem legacyPremiumAsset,
            final PremiumAssetMetadata legacyPremiumPremiumAssetMetadata,
            final Class<?> callerClass
    ) {
        return TimedPremiumAsset.of(
                extensionDuration,
                expirationUnit,
                PremiumAssetBriefMapper.validateAndMap(
                        clusterGroupId,
                        sessionProcessId,
                        legacyPremiumAsset,
                        legacyPremiumPremiumAssetMetadata,
                        callerClass
                ),
                PremiumAssetExpirationMapper.validateAndMap(
                        timeAnchor,
                        timeZone,
                        legacyPremiumAsset,
                        callerClass
                )
        );
    }

}
