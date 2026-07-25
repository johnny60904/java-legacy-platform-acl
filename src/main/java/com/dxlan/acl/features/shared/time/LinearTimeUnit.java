package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.common.PeerMappable;
import com.dxlan.acl.features.shared.lookup.EnumLookups;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum LinearTimeUnit implements TemporalMeasureUnit, PeerMappable<ChronologicalScale> {

    MILLISECOND(
            "Millisecond",
            Set.of("Milli"),
            ChronologicalScale.MILLISECOND
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
    );

    private final String displayName;
    private final Set<String> aliases;
    private final ChronologicalScale peerUnit;

    private LinearTimeUnit(
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

    private static final LinearTimeUnit[] CONSTANTS = LinearTimeUnit.values();

    private static final Map<String, LinearTimeUnit> LOOKUP =
            EnumLookups.buildLookupMap(CONSTANTS);

    public static final Set<String> INVARIANT_PARSABLE_TOKENS =
            EnumLookups.buildLookupKeySet(LOOKUP);

    private static final Map<ChronologicalScale, LinearTimeUnit> PEER_LOOKUP =
            EnumLookups.buildPeerLookupMap(ChronologicalScale.class, CONSTANTS);

    public static Optional<LinearTimeUnit> of(
            final String value
    ) {
        return EnumLookups.ofValue(value, LOOKUP);
    }

    public static LinearTimeUnit ofTrusted(
            final String validatedValue
    ) {
        return EnumLookups.ofTrustedValue(
                validatedValue,
                LOOKUP,
                LinearTimeUnit.class.getSimpleName() + "Token",
                LinearTimeUnit.class.getSimpleName()
        );
    }

    public static Optional<LinearTimeUnit> fromChronologicalScale(
            final ChronologicalScale chronologicalScale
    ) {
        return EnumLookups.ofPeer(chronologicalScale, PEER_LOOKUP);
    }

    public static LinearTimeUnit fromTrustedChronologicalScale(
            final ChronologicalScale validatedChronologicalScale
    ) {
        return EnumLookups.ofTrustedPeer(
                validatedChronologicalScale,
                PEER_LOOKUP,
                LinearTimeUnit.class.getSimpleName(),
                ChronologicalScale.class.getSimpleName()
        );
    }

}
