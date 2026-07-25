package com.dxlan.acl.features.shared.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class BaseEntity {

    public UUID id;

    protected BaseEntity() {
        this.id = UUID.randomUUID();
    }

    private final List<BaseEvent> _domainEvents = new ArrayList<>();

    public final List<BaseEvent> getDomainEvents() {
        return Collections.unmodifiableList(_domainEvents);
    }

    public void addDomainEvent(final BaseEvent domainEvent) {
        _domainEvents.add(domainEvent);
    }

    public void removeDomainEvent(final BaseEvent domainEvent) {
        _domainEvents.remove(domainEvent);
    }

    public void clearDomainEvents() {
        _domainEvents.clear();
    }

}
