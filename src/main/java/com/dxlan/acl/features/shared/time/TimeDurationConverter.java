package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.numeric.NumericValidator;

import java.util.Objects;

public final class TimeDurationConverter {

    private final long duration;
    private final DurationUnit sourceUnit;

    private TimeDurationConverter(
            final long duration,
            final DurationUnit sourceUnit
    ) {
        NumericValidator.validatePositive(duration, "Duration");
        Objects.requireNonNull(sourceUnit, "SourceUnit must not be null.");
        this.duration = duration;
        this.sourceUnit = sourceUnit;
    }

    public static TimeDurationConverter from(
            final long duration,
            final DurationUnit sourceUnit
    ) {
        return new TimeDurationConverter(duration, sourceUnit);
    }

    public double to(
            final DurationUnit targetUnit
    ) {
        Objects.requireNonNull(targetUnit, "TargetUnit must not be null.");
        return sourceUnit.convertTo(duration, targetUnit);
    }

}
