package com.christiangp.monzoapi.model.request;

import java.util.HashMap;
import java.util.Map;

class ImmutableHashMap<K, V>
        extends HashMap<K, V> {

    ImmutableHashMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException("This map is immutable");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public final V remove(Object o) {
        throw new UnsupportedOperationException("This map is immutable. Use MetadataMap.Builder");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("This map is immutable. Use MetadataMap.Builder");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException("This map is immutable");
    }
}
