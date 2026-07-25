package com.dxlan.acl.premiumasset.domain.components;

import com.dxlan.acl.features.shared.time.DurationUnit;
import com.dxlan.acl.features.shared.time.TimeDurationConverter;
import com.dxlan.acl.premiumasset.domain.enums.ExpirationUnit;

public final class ExtensionDurationConverter {

    private final long extensionDuration;
    private final ExpirationUnit expirationUnit;

    private ExtensionDurationConverter(
            final long extensionDuration,
            final ExpirationUnit expirationUnit
    ) {
        this.extensionDuration = extensionDuration;
        this.expirationUnit = expirationUnit;
    }

    public long toDays() {
        if (expirationUnit == ExpirationUnit.DAY) return extensionDuration;
        return (long) Math.ceil(
                TimeDurationConverter.from(
                        extensionDuration,
                        expirationUnit.toDurationUnit()
                ).to(DurationUnit.DAY)
        );
    }

    public static ExtensionDurationConverter of(
            final long extensionDuration,
            final ExpirationUnit expirationUnit
    ) {
        return new ExtensionDurationConverter(extensionDuration, expirationUnit);
    }


}
