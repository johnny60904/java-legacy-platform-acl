package com.dxlan.acl.premiumasset.presentation.translations;

public final class TranslationMessage {

    private TranslationMessage() { throw new AssertionError(); }

    public static final class Text {
        private Text() { throw new AssertionError(); }
        public static final String INVALID_OPERATION =
                "Invalid premium asset expiration adjustment operation";

        public static final String OPERATION_FAILURE =
                "PremiumAssetExpirationAdjustmentFailure";

        public static final String NOTE_FAILURE_TIME =
                "Please note the exact time of the error / failure.";

        public static final String CONTACT_ADMIN =
                "Please contact system administrator.";

        public static final String SYSTEM_INTERNAL_ERROR =
                "System internal error occurred.";

        public static final String UNKNOWN_ERROR =
                "Unknown error occurred.";
    }

}
