package com.dxlan.acl.premiumasset.domain.valueobjects;

import com.dxlan.acl.features.shared.domain.ValueObject;
import com.dxlan.acl.features.shared.domain.integrities.DomainConcept;
import com.dxlan.acl.features.shared.domain.integrities.DomainConceptProperty;
import com.dxlan.acl.premiumasset.domain.components.ExtensionDurationConverter;
import com.dxlan.acl.premiumasset.domain.enums.*;
import com.dxlan.acl.premiumasset.domain.components.ExpirationExtensor;
import com.dxlan.acl.premiumasset.domain.components.ExpirationReconciler;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.PremiumAssetExpirationIntegrityGuard;
import com.dxlan.acl.premiumasset.domain.validations.premiumassetexpiration.contexts.PremiumAssetExpirationValidationContext;

import java.time.Instant;
import java.time.ZoneId;
import java.util.StringJoiner;
import java.util.stream.Stream;

public final class PremiumAssetExpiration extends ValueObject {

    private final Instant timeAnchor;
    private final ZoneId timeZone;
    private final Instant expirationTimestamp;
    private final Instant terminationTimestamp;
    private final long remainingHeartbeat;
    private final ExpirationState expirationState;

    public PremiumAssetExpiration(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final Instant expirationTimestamp,
            final Instant terminationTimestamp,
            final long remainingHeartbeat,
            final ExpirationState expirationState
    ) {
        PremiumAssetExpirationIntegrityGuard.INSTANCE.guardRules(
                PremiumAssetExpirationValidationContext.of(
                        DomainConceptProperty.of(
                                timeAnchor,
                                Concept.TIME_ANCHOR
                        ),
                        DomainConceptProperty.of(
                                timeZone,
                                Concept.TIME_ZONE
                        ),
                        DomainConceptProperty.of(
                                expirationTimestamp,
                                Concept.EXPIRATION_TIMESTAMP
                        ),
                        DomainConceptProperty.of(
                                terminationTimestamp,
                                Concept.TERMINATION_TIMESTAMP
                        ),
                        DomainConceptProperty.of(
                                remainingHeartbeat,
                                Concept.REMAINING_HEARTBEAT
                        ),
                        DomainConceptProperty.of(
                                expirationState,
                                Concept.EXPIRATION_STATE
                        )
                ),
                PremiumAssetExpiration.class
        );

        this.timeAnchor = timeAnchor;
        this.timeZone = timeZone;
        this.expirationTimestamp = expirationTimestamp;
        this.terminationTimestamp = terminationTimestamp;
        this.remainingHeartbeat = remainingHeartbeat;
        this.expirationState = expirationState;
    }

    public static PremiumAssetExpiration of(
            final Instant timeAnchor,
            final ZoneId timeZone,
            final Instant expirationTimestamp,
            final Instant terminationTimestamp,
            final long remainingHeartbeat,
            final ExpirationState expirationState
    ) {
        return new PremiumAssetExpiration(
                timeAnchor,
                timeZone,
                expirationTimestamp,
                terminationTimestamp,
                remainingHeartbeat,
                expirationState
        );
    }

    public Instant getTimeAnchor() {
        return timeAnchor;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public Instant getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public Instant getTerminationTimestamp() {
        return terminationTimestamp;
    }

    public long getRemainingHeartbeat() {
        return remainingHeartbeat;
    }

    public ExpirationState getExpirationState() {
        return expirationState;
    }

    public PremiumAssetExpiration reconcile(
            final PremiumAssetType premiumAssetType
    ) {
        return switch(premiumAssetType) {
            case TIMED -> {
                Instant reconciledExpiration = ExpirationReconciler
                        .of(timeAnchor, premiumAssetType)
                        .reconcile();
                yield PremiumAssetExpiration.of(
                        timeAnchor,
                        timeZone,
                        reconciledExpiration,
                        reconciledExpiration,
                        RemainingHeartbeatType.TIMED.days(),
                        ExpirationState.RECONCILED
                );
            }
            case PERMANENT -> {
                Instant reconciledExpiration = ExpirationReconciler
                        .of(premiumAssetType)
                        .reconcile();
                yield PremiumAssetExpiration.of(
                        timeAnchor,
                        timeZone,
                        reconciledExpiration,
                        reconciledExpiration,
                        RemainingHeartbeatType.PERMANENT.days(),
                        ExpirationState.RECONCILED
                );
            }
        };
    }

    public PremiumAssetExpiration extend(
            final long duration,
            final ExpirationUnit unit
    ) {
        Instant extendedExpiration = ExpirationExtensor
                .of(unit, timeAnchor, timeZone)
                .extend(duration, unit)
                .toInstant();
        long remainingHeartbeat = ExtensionDurationConverter
                .of(duration, unit)
                .toDays();
        return PremiumAssetExpiration.of(
                timeAnchor,
                timeZone,
                extendedExpiration,
                extendedExpiration,
                remainingHeartbeat,
                expirationState
        );
    }

    public PremiumAssetExpiration expire() {
        return PremiumAssetExpiration.of(
                timeAnchor,
                timeZone,
                ExpirationType.EXPIRED.toInstant(),
                ExpirationType.EXPIRED.toInstant(),
                RemainingHeartbeatType.EXPIRED.days(),
                expirationState
        );
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "PremiumAssetExpiration[", "]")
                .add("expirationTimestamp=" + expirationTimestamp.toString())
                .add("terminationTimestamp=" + terminationTimestamp.toString())
                .add("remainingHeartbeat=" + remainingHeartbeat +
                        " (" + PremiumAssetLifeCycle.getDefaultUnit().displayName() + "s)")
                .toString();
    }

    @Override
    public Stream<Object> getEqualityComponents() {
        return Stream.of(
                timeAnchor,
                timeZone,
                expirationTimestamp,
                terminationTimestamp,
                remainingHeartbeat,
                expirationState
        );
    }

    public static enum Concept implements DomainConcept {
        TIME_ANCHOR("TimeAnchor"),
        TIME_ZONE("TimeZone"),
        EXPIRATION_TIMESTAMP("ExpirationTimestamp"),
        TERMINATION_TIMESTAMP("TerminationTimestamp"),
        REMAINING_HEARTBEAT("RemainingHeartbeat"),
        EXPIRATION_STATE("ExpirationState");

        private final String displayName;

        private Concept(final String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String displayName() { return displayName; }
    }

}
