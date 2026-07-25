package net.legacy.platform.core.network;

public final class GlobalNotifier {

    private final String message;

    private GlobalNotifier(
            final String message
    ) {
        this.message = message;
    }

    public static GlobalNotifier createModalAlert(
            final String message
    ) {
        return new GlobalNotifier(message);
    }

}
