package com.christiangp.monzoapi.model.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Field map representing any feed item.
 * Don't use this directly, use any of its subclasses, which are the actual supported feed item types.
 */
public abstract class FeedItemMap
        extends ImmutableHashMap<String, String> {

    FeedItemMap(Map<String, String> map, String type) {
        super(mapWithType(map, type));
    }

    private static Map<String, String> mapWithType(Map<String, String> map, String type) {
        final HashMap<String, String> newMap = new HashMap<>(map);
        newMap.put("type", type);
        return newMap;
    }
}
