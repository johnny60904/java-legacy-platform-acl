package com.dxlan.acl.features.shared.time;

import java.util.Set;

public enum ChronologicalScale implements TemporalMeasureUnit {

    MILLISECOND("Millisecond", Set.of("Milli")),
    SECOND("Second", Set.of("Sec")),
    MINUTE("Minute", Set.of("Min")),
    HOUR("Hour", null),
    DAY("Day", null),
    WEEK("Week", null),
    MONTH("Month", null),
    YEAR("Year", null);

    private final String displayName;
    private final Set<String> aliases;

    private ChronologicalScale(
            final String displayName,
            final Set<String> aliases
    ) {
        this.displayName = displayName;
        this.aliases = aliases;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public Set<String> lookupKeys() {
        return aliases;
    }

}
