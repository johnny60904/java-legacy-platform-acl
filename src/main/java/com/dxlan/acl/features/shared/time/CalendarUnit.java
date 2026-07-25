package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.common.PeerMappable;
import com.dxlan.acl.features.shared.lookup.EnumLookups;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum CalendarUnit implements TemporalMeasureUnit, PeerMappable<ChronologicalScale> {

    MILLISECOND(
            "Millisecond",
            Set.of("Milli"),
            ChronologicalScale.SECOND
    ),
    SECOND(
            "Second",
            Set.of("Sec"),
            ChronologicalScale.SECOND
    ),
    MINUTE(
            "Minute",
            Set.of("Min"),
            ChronologicalScale.MINUTE
    ),
    HOUR(
            "Hour",
            null,
            ChronologicalScale.HOUR
    ),
    DAY(
            "Day",
            null,
            ChronologicalScale.DAY
    ),
    WEEK(
            "Week",
            null,
            ChronologicalScale.WEEK
    ),
    MONTH(
            "Month",
            null,
            ChronologicalScale.MONTH
    ),
    YEAR(
            "Year",
            null,
            ChronologicalScale.YEAR
    );

    private final String displayName;
    private final Set<String> aliases;
    private final ChronologicalScale peerUnit;

    private CalendarUnit(
            final String displayName,
            final Set<String> aliases,
            final ChronologicalScale peerUnit
    ) {
        this.displayName = displayName;
        this.aliases = aliases;
        this.peerUnit = peerUnit;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public Set<String> lookupKeys() {
        return aliases;
    }

    @Override
    public ChronologicalScale getPeer() {
        return peerUnit;
    }

    public ChronologicalScale toChronologicalScale() {
        return peerUnit;
    }

    private static final CalendarUnit[] CONSTANTS = CalendarUnit.values();

    private static final Map<String, CalendarUnit> LOOKUP =
            EnumLookups.buildLookupMap(CONSTANTS);

    public static final Set<String> INVARIANT_PARSABLE_TOKENS =
            EnumLookups.buildLookupKeySet(LOOKUP);

    private static final Map<ChronologicalScale, CalendarUnit> PEER_LOOKUP =
            EnumLookups.buildPeerLookupMap(ChronologicalScale.class, CONSTANTS);

    public static Optional<CalendarUnit> of(
            final String value
    ) {
        return EnumLookups.ofValue(value, LOOKUP);
    }

    public static CalendarUnit ofTrusted(
            final String validatedValue
    ) {
        return EnumLookups.ofTrustedValue(
                validatedValue,
                LOOKUP,
                CalendarUnit.class.getSimpleName() + "Token",
                CalendarUnit.class.getSimpleName()
        );
    }

    public static Optional<CalendarUnit> fromChronologicalScale(
            final ChronologicalScale chronologicalScale
    ) {
        return EnumLookups.ofPeer(chronologicalScale, PEER_LOOKUP);
    }

    public static CalendarUnit fromTrustedChronologicalScale(
            final ChronologicalScale validatedChronologicalScale
    ) {
        return EnumLookups.ofTrustedPeer(
                validatedChronologicalScale,
                PEER_LOOKUP,
                CalendarUnit.class.getSimpleName(),
                ChronologicalScale.class.getSimpleName()
        );
    }

}
