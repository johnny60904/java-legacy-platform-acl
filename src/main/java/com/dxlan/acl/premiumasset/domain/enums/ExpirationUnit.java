package com.dxlan.acl.premiumasset.domain.enums;

import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.common.NameDisplayable;
import com.dxlan.acl.features.shared.common.PeerMappable;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.lookup.EnumLookups;
import com.dxlan.acl.features.shared.lookup.Lookupable;
import com.dxlan.acl.features.shared.time.DurationUnit;
import com.dxlan.acl.premiumasset.domain.common.PremiumAssetDomainMetadata;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum ExpirationUnit implements
        Lookupable, NameDisplayable, PeerMappable<DurationUnit> {

    DAY(
            "Day",
            null,
            DurationUnit.DAY
    ),
    WEEK(
            "Week",
            null,
            DurationUnit.WEEK
    ),
    MONTH(
            "Month",
            null,
            DurationUnit.MONTH
    ),
    YEAR(
            "Year",
            null,
            DurationUnit.YEAR
    );

    private final String displayName;
    private final Set<String> aliases;
    private final DurationUnit peerUnit;

    private ExpirationUnit(
            final String displayName,
            final Set<String> aliases,
            final DurationUnit peerUnit
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
    public DurationUnit getPeer() {
        return peerUnit;
    }

    public DurationUnit toDurationUnit() {
        return peerUnit;
    }

    private static final ExpirationUnit[] CONSTANTS = ExpirationUnit.values();

    private static final Map<String, ExpirationUnit> LOOKUP =
            EnumLookups.buildLookupMap(CONSTANTS);

    public static final Set<String> INVARIANT_PARSABLE_TOKENS =
            EnumLookups.buildLookupKeySet(LOOKUP);

    public static Optional<ExpirationUnit> of(
            final String name
    ) {
        return EnumLookups.ofValue(name, LOOKUP);
    }

    public static ExpirationUnit ofTrusted(
            final String validatedName
    ) {
        return EnumLookups.ofTrustedValue(
                validatedName,
                LOOKUP,
                ExpirationUnit.class.getSimpleName() + "Token",
                ExpirationUnit.class.getSimpleName()
        );
    }

    private static record Metadata() implements PremiumAssetDomainMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.DDD_MODULE;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.DDD;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.CLEAN_ARCHITECTURE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.NONE;
        }

        @Override
        public ArchitecturalStereotype stereotype() {
            return ArchitecturalStereotype.STRATEGY;
        }

        @Override
        public LanguageElement languageElement() {
            return LanguageElement.ENUM;
        }

        @Override
        public String typeName() {
            return ExpirationUnit.class.getSimpleName();
        }
    }

    private static final PremiumAssetDomainMetadata METADATA = new Metadata();

    public static PremiumAssetDomainMetadata getMetadata() {
        return METADATA;
    }

}
