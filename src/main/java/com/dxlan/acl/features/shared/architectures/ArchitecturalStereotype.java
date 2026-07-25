package com.dxlan.acl.features.shared.architectures;

import com.dxlan.acl.features.shared.common.NameDisplayable;

public enum ArchitecturalStereotype implements NameDisplayable {

    GUARD("Guard"),
    VALIDATOR("Validator"),

    AGGREGATE("Aggregate"),
    ENTITY("Entity"),
    VALUE_OBJECT("ValueObject"),
    EVENT("Event"),

    INVARIANT("Invariant"),
    CLAUSE("Clause"),
    CONSTRAINT("Constraint"),
    RULE("Rule"),

    STRATEGY("Strategy"),
    POLICY("Policy"),
    STATE("State"),
    SPECIFICATION("Specification"),

    GATEWAY("Gateway"),
    REPOSITORY("Repository"),
    ACCESSOR("Accessor"),
    ADAPTER("Adapter"),
    SENDER("Sender"),
    SYNCHRONIZER("Synchronizer"),
    BRIDGE("Bridge"),

    DTO("Dto"),
    COMMAND("Command"),
    QUERY("Query"),
    HANDLER("Handler"),

    CALCULATOR("Calculator"),
    EVALUATOR("Evaluator"),
    MEASURER("Measurer"),
    EXTENDER("Extender"),
    TRIMMER("Trimmer"),
    MATCHER("Matcher"),
    NORMALIZER("Normalizer"),
    SANITIZER("Sanitizer"),
    RECONCILER("Reconciler"),
    RESOLVER("Resolver"),
    COMPOSER("Composer"),
    FORMATTER("Formatter"),
    LOGGER("Logger"),
    MAPPER("Mapper"),
    TRANSLATOR("Translator"),
    CONVERTER("Converter"),
    DIVIDER("Divider"),

    PATTERN("Pattern"),
    FORMAT("Format"),
    METADATA("Metadata"),

    PREDICATE("Predicate"),
    DETECTOR("Detector"),
    INSPECTOR("Inspector"),
    FACTORY("Factory"),

    TIME_UNIT("TimeUnit"),
    CONTRACT("Contract"),

    CONSTANT("Constant");

    private final String displayName;

    private ArchitecturalStereotype(
            final String displayName
    ) {
        this.displayName = displayName;
    }

    @Override
    public String displayName() {
        return displayName;
    }

}
