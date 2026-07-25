package com.dxlan.acl.premiumasset.application.queries.getpremiumassetdetails;

import com.dxlan.acl.features.shared.text.TextDivider;
import com.dxlan.acl.premiumasset.domain.enums.PremiumAssetLifeCycle;

import java.util.List;

public record PremiumAssetDetails(
        String assetName,
        int assetItemId,
        String expirationTimestamp,
        String terminationTimestamp,
        long remainingHeartbeat,
        long lifespan,
        String premiumAssetType
) {

    public static PremiumAssetDetails of(
            final String assetName,
            final int assetItemId,
            final String expirationTimestamp,
            final String terminationTimestamp,
            final long remainingHeartbeat,
            final long lifespan,
            final String premiumAssetType
    ) {
        return new PremiumAssetDetails(
                assetName,
                assetItemId,
                expirationTimestamp,
                terminationTimestamp,
                remainingHeartbeat,
                lifespan,
                premiumAssetType
        );
    }

    private static final String EXPIRATION_DEFAULT_UNIT =
            "(" + PremiumAssetLifeCycle.getDefaultUnit().displayName() + "s)";

    public List<String> toStringList() {
        return List.of(
                TextDivider.STRONG.getText(),
                "Premium Asset Item Id: " + assetItemId,
                "Premium Asset Name: " + assetName,
                "Premium Asset Expiration Timestamp: " + expirationTimestamp,
                "Premium Asset Termination Timestamp: " + terminationTimestamp,
                "Premium Asset Remaining Heartbeat " + EXPIRATION_DEFAULT_UNIT + ": " + remainingHeartbeat,
                "Premium Asset Lifespan " + EXPIRATION_DEFAULT_UNIT + ": " + lifespan,
                "Premium Asset Type: " + premiumAssetType,
                TextDivider.STRONG.getText()
        );
    }

    public List<String> toStringList(
            final boolean sectionStart
    ) {
        final String section = sectionStart
                ? TextDivider.STRONG_START.getText()
                : TextDivider.STRONG_END.getText();
        return List.of(
                section,
                "Premium Asset Item Id: " + assetItemId,
                "Premium Asset Name: " + assetName,
                "Premium Asset Expiration Timestamp: " + expirationTimestamp,
                "Premium Asset Termination Timestamp: " + terminationTimestamp,
                "Premium Asset Remaining Heartbeat " + EXPIRATION_DEFAULT_UNIT + ": " + remainingHeartbeat,
                "Premium Asset Lifespan " + EXPIRATION_DEFAULT_UNIT + ": " + lifespan,
                "Premium Asset Type: " + premiumAssetType,
                section
        );
    }

    public static void collectDetailsStringList(
            final List<String> list,
            final PremiumAssetDetails other
    ) {
        list.add(TextDivider.STRONG.getText());
        list.add("Premium Asset Item Id: " + other.assetItemId());
        list.add("Premium Asset Name: " + other.assetName());
        list.add("Premium Asset Expiration Timestamp: " + other.expirationTimestamp());
        list.add("Premium Asset Termination Timestamp: " + other.terminationTimestamp());
        list.add("Premium Asset Remaining Heartbeat " + EXPIRATION_DEFAULT_UNIT + ": " + other.remainingHeartbeat());
        list.add("Premium Asset Lifespan " + EXPIRATION_DEFAULT_UNIT + ": " + other.lifespan());
        list.add("Premium Asset Type: " + other.premiumAssetType());
        list.add(TextDivider.STRONG.getText());
    }

    public static void collectDetailsStringList(
            final List<String> list,
            final PremiumAssetDetails other,
            final boolean sectionStart
    ) {
        final String section = sectionStart
                ? TextDivider.STRONG_START.getText()
                : TextDivider.STRONG_END.getText();
        list.add(section);
        list.add("Premium Asset Item Id: " + other.assetItemId());
        list.add("Premium Asset Name: " + other.assetName());
        list.add("Premium Asset Expiration Timestamp: " + other.expirationTimestamp());
        list.add("Premium Asset Termination Timestamp: " + other.terminationTimestamp());
        list.add("Premium Asset Remaining Heartbeat " + EXPIRATION_DEFAULT_UNIT + ": " + other.remainingHeartbeat());
        list.add("Premium Asset Lifespan " + EXPIRATION_DEFAULT_UNIT + ": " + other.lifespan());
        list.add("Premium Asset Type: " + other.premiumAssetType());
        list.add(section);
    }

}
