package net.legacy.platform.core.session;

import net.legacy.platform.core.network.NetworkPayload;

import java.util.List;

public final class ClientConnection {

    private int channelId = 1;
    private String channelName = "ChannelName";

    public ClientConnection() {}

    public int getChannelId() { return channelId; }

    public String getChannelName() { return channelName; }

    public void setChannelId(
            final int channelId
    ) {
        this.channelId = channelId;
    }

    public void setChannelName(
            final String channelName
    ) {
        this.channelName = channelName;
    }

    public void write(final NetworkPayload payload) { }

    public void write(final List<NetworkPayload> payloads) { }

}
