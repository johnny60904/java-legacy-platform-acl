package com.dxlan.acl.features.shared.architectures;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum ArchitecturalPattern implements NameDisplayable {

    NONE("None"),
    NOT_APPLICABLE("NotApplicable"),
    CQRS_QUERY("CQRS_QueryPath"),
    CQRS_COMMAND("CQRS_CommandPath"),
    READ_MODEL_PROJECTION("ReadModelProjectionPath"),
    REPOSITORY("RepositoryPattern"),
    DATA_MAPPER("DataMapperPattern"),
    UNIT_OF_WORK("UnitOfWorkPattern"),
    EVENT_SOURCING("EventSourcingPattern"),
    DOMAIN_EVENT("DomainEventPattern"),
    OUTBOX("OutboxPattern"),
    SAGA("SagaPattern/ProcessPattern");

    private final String displayName;

    private ArchitecturalPattern(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
