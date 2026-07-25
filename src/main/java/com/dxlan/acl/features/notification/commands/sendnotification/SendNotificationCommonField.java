package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.shared.validations.taxonomy.ValidationParameter;

public enum SendNotificationCommonField implements ValidationParameter {

    CLUSTER_GROUP_ID("ClusterGroupId"),
    SESSION_PROCESS_ID("SessionProcessId"),
    MESSAGE("Message"),
    MESSAGE_LINES("MessageLines"),
    CHAT_MESSAGE_STYLE("ChatMessageStyle");

    private final String displayName;

    private SendNotificationCommonField(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }

}