package net.legacy.platform.core.tme;

import java.io.Serializable;

/**
 * @deprecated
 * TECHNICAL COMPLIANCE WARNING & FORENSIC LOG:
 * In the actual production environment, this component represents the most fragile legacy bottleneck.
 * The original implementation (originally named 'FileTime', not java.nio.file.attribute.FileTime) suffered from severe engineering failures:
 * <ol>
 *   <li><b>Flawed Temporal Arithmetic:</b> Mixed Windows FILETIME epoch offsets (116444736000000000L)
 *       with chaotic, non-deterministic bit-shifting ({@code >>> 32}), frequently resulting in arithmetic overflows.</li>
 *   <li><b>Systemic Data Instability:</b> Due to broken serialization logic and incorrect millisecond boundaries,
 *       it continuously suffered from runtime anomalies, intermittently returning corrupted values of {@code 0} or {@code null}.</li>
 *   <li><b>Severe Layer Contamination:</b> Intrinsic network encoding workflows ({@code encode(OutPacket)})
 *       were blindly hardcoded directly into this temporal object, violating the Single Responsibility Principle (SRP).</li>
 * </ol>
 * <p>
 * <b>ACL RESOLUTION:</b> Rather than pursuing a hellish ripple-effect refactoring across hundreds of legacy dependencies,
 * an Anti-Corruption Layer (ACL) defense mechanism was established. The modern system leverages
 * {@code LegacyPlatformTimestampSanitizer} to quarantine, parse, and dual-translate these volatile metrics
 * into standard Java {@code Instant} instances at the absolute boundary.
 * <p>
 * This stub maintains a thread-safe, sanitized contract solely for architectural compiler compatibility.
 */
@Deprecated(since = "v1.0.0", forRemoval = false)
public final class Timestamp implements Serializable, Comparable<Timestamp> {

    private final long rawEpochMillisValue;

    public Timestamp() {
        this.rawEpochMillisValue = 0L;
    }

    private Timestamp(
            final long rawEpochMillisValue
    ) {
        this.rawEpochMillisValue = rawEpochMillisValue;
    }

    public static Timestamp fromEpochMillis(
            final long epochMillis
    ) {
        return new Timestamp(epochMillis);
    }

    public static Timestamp boundaryMax() { return new Timestamp(Long.MAX_VALUE); }
    public static Timestamp boundaryMin() { return new Timestamp(Long.MIN_VALUE); }

    public long toLong() { return this.rawEpochMillisValue; }
    public long toEpochMillis() { return this.rawEpochMillisValue; }

    public boolean isBoundaryMax() { return this.rawEpochMillisValue == Long.MAX_VALUE; }
    public boolean isBoundaryMin() { return this.rawEpochMillisValue == Long.MIN_VALUE; }

    @Override
    public int compareTo(
            final Timestamp other
    ) {
        return Long.compare(this.rawEpochMillisValue, other.rawEpochMillisValue);
    }

}
