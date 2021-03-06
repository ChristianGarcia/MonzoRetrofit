/*
 * Copyright (c) 2017 Christian García
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.christiangp.monzoapi.model.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a metadata object that can be added to a transaction.
 * Build a new instance using {@linkplain MetadataMap.Builder the builder}. For example:
 * <pre><code>
 * MetadataMap metadata = new MetadataMap.Builder()
 *     .add("key1","value1")
 *     .add("key2","value2")
 *     .build();
 * </code></pre>
 *
 * @see <a href="https://monzo.com/docs/#annotate-transaction">https://monzo.com/docs/#annotate-transaction</a>
 */
public final class MetadataMap
        extends ImmutableHashMap<String, String> {

    public MetadataMap(Map<String, String> map) {
        super(map);
    }

    /**
     * Builds a new {@link MetadataMap}
     */
    public final static class Builder {

        private final Map<String, String> map;

        public Builder() {
            this.map = new HashMap<>();
        }

        public Builder add(String key, String value) {
            map.put(String.format("metadata[%s]", key), value);
            return this;
        }

        /**
         * Create the {@link MetadataMap} instance using the configured values.
         */
        public MetadataMap build() {
            return new MetadataMap(map);
        }
    }
}
