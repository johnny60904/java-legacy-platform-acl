package com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails;

import com.dxlan.acl.features.shared.boundaries.BoundaryValidator;

public final class GetPremiumAssetDetailsHandler {

    private static final BoundaryValidator VALIDATOR =
            BoundaryValidator.forClass(GetPremiumAssetDetailsHandler.class);

    private final PremiumAssetGateway premiumAssetGateway;

    private GetPremiumAssetDetailsHandler(
            final PremiumAssetGateway premiumAssetGateway
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                premiumAssetGateway,
                PremiumAssetGateway.getMetadata()
        );
        this.premiumAssetGateway = premiumAssetGateway;
    }

    public PremiumAssetDetails handle(
            final GetPremiumAssetDetailsByIndexQuery query
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                query,
                GetPremiumAssetDetailsByIndexQuery.getMetadata()
        );

        return premiumAssetGateway.loadDetailsByActiveIndex(
                query.clusterGroupId(),
                query.sessionProcessId(),
                query.activeAssetIndex(),
                query.timeAnchor(),
                query.timeZone()
        );
    }

    public PremiumAssetDetails handle(
            final GetPremiumAssetDetailsByIdQuery query
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                query,
                GetPremiumAssetDetailsByIdQuery.getMetadata()
        );

        return premiumAssetGateway.loadDetailsByItemId(
                query.clusterGroupId(),
                query.sessionProcessId(),
                query.assetItemId(),
                query.timeAnchor(),
                query.timeZone()
        );
    }

    public PremiumAssetDetails handle(
            final GetPremiumAssetDetailsBySlotQuery query
    ) {
        VALIDATOR.requireInternalTypeNotNull(
                query,
                GetPremiumAssetDetailsBySlotQuery.getMetadata()
        );

        return premiumAssetGateway.loadDetailsByStorageSlot(
                query.clusterGroupId(),
                query.sessionProcessId(),
                query.storageSlot(),
                query.timeAnchor(),
                query.timeZone()
        );
    }

    public static GetPremiumAssetDetailsHandler of(
            final PremiumAssetGateway premiumAssetGateway
    ) {
        return new GetPremiumAssetDetailsHandler(
                premiumAssetGateway
        );
    }

}
