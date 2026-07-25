package com.dxlan.acl.features.shared.lookup;

import com.dxlan.acl.features.shared.arrays.ArrayValidator;
import com.dxlan.acl.features.shared.common.NameDisplayable;
import com.dxlan.acl.features.shared.common.PeerMappable;
import com.dxlan.acl.features.shared.maps.MapValidator;
import com.dxlan.acl.features.shared.text.CamelCaseConverter;
import com.dxlan.acl.features.shared.text.TextValidator;

import java.util.*;
import java.util.function.Function;

public final class EnumLookups {

    private EnumLookups() {
        throw new AssertionError();
    }

    /// using Collections.unmodifiableMap() for EnumMap
    /// using Map.copyOf() for General Map (e.g. HashMap)

    private static void validateNotNull(
            final Object target,
            final String name
    ) {
        Objects.requireNonNull(target, name + " must not be null.");
    }

    public static <E extends Enum<E> & Lookupable> Map<String, E> buildLookupMap(
            final E[] enumValues
    ) {
        ArrayValidator.validateNotEmpty(enumValues, "EnumValues");
        Map<String, E> lookupMap = new HashMap<>();
        for (E enumConstant : enumValues) {
            lookupMap.put(
                    enumConstant.name().trim().toUpperCase(Locale.ROOT),
                    enumConstant
            );

            if (enumConstant.lookupKeys() != null && !enumConstant.lookupKeys().isEmpty()) {
                for (String key : enumConstant.lookupKeys()) {
                    if (key != null && !key.isBlank()) {
                        lookupMap.put(
                                key.trim().toUpperCase(Locale.ROOT),
                                enumConstant
                        );
                    }
                }
            }
        }
        return Map.copyOf(lookupMap);
    }

    public static <E extends Enum<E>> Set<String> buildLookupKeySet(
            final Map<String, E> unmodifiableLookupMap
    ) {
        MapValidator.validateNotEmpty(unmodifiableLookupMap, "UnmodifiableLookupMap");
        return Set.copyOf(
                unmodifiableLookupMap.keySet()
        );
    }

    public static <E extends Enum<E> & Lookupable> Optional<E> ofValue(
            final String value,
            final Map<String, E> lookupMap
    ) {
        /// TextValidator.validateHasText(value, "Value");
        MapValidator.validateNotEmpty(lookupMap, "LookupMap");
        if (value == null || value.isBlank()) return Optional.empty();
        return Optional.ofNullable(
                lookupMap.get(
                        value.trim().toUpperCase(Locale.ROOT)
                )
        );
    }

    public static <E extends Enum<E> & Lookupable> E ofTrustedValue(
            final String value,
            final Map<String, E> lookupMap,
            final String keyName,
            final String enumName
    ) {
        TextValidator.validateHasText(keyName, "KeyName");
        TextValidator.validateHasText(value, keyName);
        TextValidator.validateHasText(enumName, "EnumName");
        MapValidator.validateNotEmpty(lookupMap, "LookupMap");
        E result = lookupMap.get(
                value.trim().toUpperCase(Locale.ROOT)
        );
        if (result == null) throw new IllegalArgumentException(
                "Failed to resolve '" + enumName +
                "' for lookup key:" +
                " [" + keyName + "]."
        );
        return result;
    }

    public static <E extends Enum<E> & Lookupable> E ofTrustedValue(
            final String value,
            final Map<String, E> lookupMap
    ) {
        return ofTrustedValue(
                value,
                lookupMap,
                "EnumName",
                "LookUpKey"
        );
    }

    public static <E extends Enum<E> & ExternalKeyMappable> Map<E, String> buildExternalLookupMap(
            final Class<E> enumClass,
            final E[] enumValues
    ) {
        validateNotNull(enumClass, "EnumClass");
        ArrayValidator.validateNotEmpty(enumValues, "EnumValues");
        Map<E, String> registry = new EnumMap<>(enumClass);
        for (E enumConstant : enumValues) {
            String externalKey = enumConstant.externalKey();
            if (externalKey != null && !externalKey.isBlank()) {
                registry.put(
                        enumConstant,
                        CamelCaseConverter.toScreamingSnakeCase(
                                externalKey.trim()
                        )
                );
            }
        }
        return Collections.unmodifiableMap(registry);
    }

    public static <E extends Enum<E> & PeerMappable<T>, T extends Enum<T>> Map<T, E>
    buildPeerLookupMap(
            final Class<T> peerClass,
            final E[] enumValues
    ) {
        validateNotNull(peerClass, "PeerClass");
        ArrayValidator.validateNotEmpty(enumValues, "EnumValues");
        Map<T, E> lookupMap = new EnumMap<>(peerClass);
        for (E enumConstant : enumValues) {
            T peer = enumConstant.getPeer();
            if (peer != null) lookupMap.put(peer, enumConstant);
        }
        return Collections.unmodifiableMap(lookupMap);
    }

    public static <E extends Enum<E> & PeerMappable<T>, T extends Enum<T>> Optional<E>
    ofPeer(
            final T peer,
            final Map<T, E> lookupMap
    ) {
        /// validateNotNull(peer, "Peer");
        MapValidator.validateNotEmpty(lookupMap, "LookupMap");
        if (peer == null) return Optional.empty();
        return Optional.ofNullable(
                lookupMap.get(peer)
        );
    }

    public static <E extends Enum<E> & PeerMappable<T>, T extends Enum<T> & NameDisplayable> E
    ofTrustedPeer(
            final T peer,
            final Map<T, E> lookupMap,
            final String enumName,
            final String peerName
    ) {
        validateNotNull(peer, "Peer");
        MapValidator.validateNotEmpty(lookupMap, "LookupMap");
        E result = lookupMap.get(peer);
        if (result == null) throw new IllegalArgumentException(
                "Failed to resolve '" + enumName +
                "' for peer '" + peerName + "':" +
                " [" + peer.displayName() + "]."
        );
        return result;
    }

    public static <E extends Enum<E> & PeerMappable<T>, T extends Enum<T> & NameDisplayable> E
    ofTrustedPeer(
            final T peer,
            final Map<T, E> lookupMap
    ) {
        return ofTrustedPeer(
                peer, lookupMap, "EnumName", "PeerName"
        );
    }

    public static <E extends Enum<E>, V> Map<E, V> buildStrategyMap(
            final Class<E> enumClass,
            final E[] enumValues,
            final Function<E, V> strategyProvider
    ) {
        validateNotNull(enumClass, "EnumClass");
        ArrayValidator.validateNotEmpty(enumValues, "EnumValues");
        validateNotNull(strategyProvider, "StrategyProvider");
        Map<E, V> map = new EnumMap<>(enumClass);
        for (E enumConstant : enumValues) {
            V strategy = strategyProvider.apply(enumConstant);
            if (strategy != null) {
                map.put(enumConstant, strategy);
            }
        }
        return Map.copyOf(map);
    }

}
