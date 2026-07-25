package com.dxlan.acl.features.notification.common.enums;

import com.dxlan.acl.features.notification.common.metadata.SendNotificationSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;
import com.dxlan.acl.features.shared.lookup.EnumLookups;
import com.dxlan.acl.features.shared.lookup.ExternalKeyMappable;
import com.dxlan.acl.features.shared.lookup.Lookupable;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum ChatMessageType implements Lookupable, ExternalKeyMappable {

    GENERAL_MESSAGE(
            Set.of("General"),
            "DirectMessage"
    ),
    DESCRIPTION(
            Set.of("Desc"),
            "SystemMetricDescription"
    ),
    TIP(
            null,
            "OperationalTip"
    ),
    STANDARD_NOTICE(
            null,
            "StandardNotice"
    ),
    ALERT_NOTICE(
            null,
            "AlertNoticeLv2"
    ),
    ADMINISTRATOR_CHAT(
            Set.of("AdminChat"),
            "AdminCommandChat"
    ),
    SYSTEM_NOTICE(
            Set.of("SysNotice"),
            "CriticalSystemNotice"
    ),
    BROADCAST_CLUSTER(
            null,
            "BroadcastCluster"
    ),
    BROADCAST_GLOBAL(
            null,
            "BroadcastGlobal"
    ),
    ASSET_ALERT(
            null,
            "AssetAlertNotifier"
    ),
    ASSET_LOG(
            null,
            "AssetLogMessage"
    ),
    LOGGER_ASSET(
            null,
            "LoggerAssetAlert"
    ),
    LOGGER_GLOBAL(
            null,
            "LoggerGlobalStream"
    ),
    REWARD_LOG(
            null,
            "IntegrationRewardLog"
    ),
    TELEMETRY_ALERT(
            null,
            "TelemetryRedAlert"
    ),
    TELEMETRY_ALTER_ALERT(
            null,
            "TelemetryRedAlertAlter"
    ),
    THEME_DARK_PURPLE(
            Set.of("DarkPurple"),
            "ThemePurple"
    ),
    THEME_YELLOW_WHITE(
            Set.of("YellowWhite"),
            "ThemeYellowWhite"
    ),
    THEME_DARK_BLUE(
            Set.of("DarkBlue"),
            "AssetMetadataStreamAlter"
    ),
    THEME_WHITE_GREEN(
            Set.of("WhiteGreen"),
            "SystemTextLegacy2"
    ),
    THEME_BLACK_WHITE(
            Set.of("BlackWhite"),
            "SystemTextLegacy3"
    );

    private final Set<String> aliases;
    private final String externalKey;

    private ChatMessageType(
            final Set<String> aliases,
            final String externalKey
    ) {
        this.aliases = aliases;
        this.externalKey = externalKey;
    }

    @Override
    public Set<String> lookupKeys() {
        return this.aliases;
    }

    @Override
    public String externalKey() {
        return this.externalKey;
    }

    private static final ChatMessageType[] CONSTANTS = ChatMessageType.values();

    private static final Map<String, ChatMessageType> LOOKUP =
            EnumLookups.buildLookupMap(CONSTANTS);

    public static final Set<String> INVARIANT_PARSABLE_TOKENS =
            EnumLookups.buildLookupKeySet(LOOKUP);

    public static Optional<ChatMessageType> of(
            final String value
    ) {
        return EnumLookups.ofValue(value, LOOKUP);
    }

    public static ChatMessageType ofTrusted(
            final String validatedName
    ) {
        return EnumLookups.ofTrustedValue(
                validatedName,
                LOOKUP,
                ChatMessageType.class.getSimpleName() + "Token",
                ChatMessageType.class.getSimpleName()
        );
    }

    public static final Map<ChatMessageType, String> EXTERNAL_LOOKUP_MAP =
            EnumLookups.buildExternalLookupMap(
                    ChatMessageType.class,
                    CONSTANTS
            );

    private static record Metadata() implements SendNotificationSliceMetadata {

        @Override
        public ArchitecturalScope scope() {
            return ArchitecturalScope.VERTICAL_SLICE;
        }

        @Override
        public String systemName() {
            return getSystemName();
        }

        @Override
        public ArchitecturalParadigms paradigms() {
            return ArchitecturalParadigms.NOT_APPLICABLE;
        }

        @Override
        public ArchitecturalStyle style() {
            return ArchitecturalStyle.VERTICAL_SLICE;
        }

        @Override
        public ArchitecturalPattern pattern() {
            return ArchitecturalPattern.NOT_APPLICABLE;
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
            return ChatMessageType.class.getSimpleName();
        }
    }

    private static final SendNotificationSliceMetadata METADATA = new Metadata();

    public static SendNotificationSliceMetadata getMetadata() {
        return METADATA;
    }

}
