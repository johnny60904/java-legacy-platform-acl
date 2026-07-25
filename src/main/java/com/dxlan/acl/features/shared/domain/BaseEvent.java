package com.dxlan.acl.features.shared.domain;

import java.util.UUID;

public abstract class BaseEvent {

    private final UUID eventID;

    protected BaseEvent() {
        this.eventID = UUID.randomUUID();
    }

    public UUID getEventID() { return eventID; }

}
