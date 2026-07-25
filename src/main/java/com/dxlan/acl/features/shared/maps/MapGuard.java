package com.dxlan.acl.features.shared.maps;

import java.util.Map;

public final class MapGuard {

    private MapGuard() {}

    public static <T extends Map<?, ?>> T requireNotNull(
            final T map,
            final String name
    ) {
        MapValidator.validateNotNull(map, name);
        return map;
    }

    public static <T extends Map<?, ?>> T requireNotNull(
            final T map
    ) {
        return requireNotNull(map);
    }

    public static <T extends Map<?, ?>> T requireNotEmpty(
            final T map,
            final String name
    ) {
        MapValidator.validateNotEmpty(map, name);
        return map;
    }

    public static <T extends Map<?, ?>> T requireNotEmpty(
            final T map
    ) {
        return requireNotEmpty(map);
    }

}
