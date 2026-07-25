package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.lookup.EnumLookups;
import com.dxlan.acl.features.shared.numeric.BigDecimalValidator;
import com.dxlan.acl.features.shared.numeric.DoubleNumberValidator;
import com.dxlan.acl.features.shared.numeric.NumericValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public enum DurationUnit implements TemporalMeasureUnit {

    MILLISECOND(
            "Millisecond",
            Set.of("Milli"),
            1L
    ),
    SECOND(
            "Second",
            Set.of("Sec"),
            1_000L
    ),
    MINUTE(
            "Minute",
            Set.of("Min"),
            60_000L
    ),
    HOUR(
            "Hour",
            null,
            3_600_000L
    ),
    DAY(
            "Day",
            null,
            86_400_000L
    ),
    WEEK(
            "Week",
            null,
            604_800_000L
    ),
    /// 30
    MONTH(
            "Month",
            null,
            2_592_000_000L
    ),
    /// 365
    YEAR(
            "Year",
            null,
            31_536_000_000L
    );

    private final String displayName;
    private final Set<String> aliases;
    private final long milliseconds;

    private DurationUnit(
            final String displayName,
            final Set<String> aliases,
            final long milliseconds
    ) {
        this.displayName = displayName;
        this.aliases = aliases;
        this.milliseconds = milliseconds;
    }

    @Override
    public String displayName() { return displayName; }

    @Override
    public Set<String> lookupKeys() {
        return aliases;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    private static final DurationUnit[] CONSTANTS = DurationUnit.values();

    private static final Map<String, DurationUnit> LOOKUP =
            EnumLookups.buildLookupMap(CONSTANTS);

    private static final Map<Long, DurationUnit> MILLIS_LOOKUP;

    public static final Set<String> INVARIANT_PARSABLE_TOKENS =
            EnumLookups.buildLookupKeySet(LOOKUP);

    static {
        Map<Long, DurationUnit> lookup = new HashMap<>();
        for (DurationUnit unit : DurationUnit.values())
            lookup.put(unit.milliseconds, unit);
        MILLIS_LOOKUP = Map.copyOf(lookup);
    }

    public static Optional<DurationUnit> of(
            final String value
    ) {
        return EnumLookups.ofValue(value, LOOKUP);
    }

    public static Optional<DurationUnit> ofMilliseconds(
            final long milliseconds
    ) {
        DurationUnit result = MILLIS_LOOKUP.get(milliseconds);
        if (result == null) return Optional.empty();
        return Optional.of(result);
    }

    private static double normalize(final double doubleValue) {
        DoubleNumberValidator.validateFinite(doubleValue);
        BigDecimal bigDecimal = (new BigDecimal(doubleValue)).stripTrailingZeros();
        BigDecimalValidator.validateScaleBelowUpperBound(bigDecimal);
        BigDecimalValidator.validatePrecisionBelowUpperBound(bigDecimal);
        BigDecimal rounded = bigDecimal.setScale(3, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

    double convertTo(
            final long duration,
            final DurationUnit targetUnit
    ) {
        NumericValidator.validatePositiveOrZero(duration, "Duration");
        Objects.requireNonNull(targetUnit, "TargetUnit must not be null.");
        if (duration == 0) return 0.00;
        if (this == targetUnit) return normalize(duration);

        double thisDouble = this.milliseconds;
        double targetDouble = targetUnit.milliseconds;
        DoubleNumberValidator.validateFinite(thisDouble);
        DoubleNumberValidator.validateFinite(targetDouble);
        if (this.milliseconds > targetUnit.milliseconds) {
            double ratio = thisDouble / targetDouble;
            double result = duration * ratio;
            return normalize(result);
        } else {
            double ratio = targetDouble / thisDouble;
            double result = duration / ratio;
            return normalize(result);
        }
    }

}
