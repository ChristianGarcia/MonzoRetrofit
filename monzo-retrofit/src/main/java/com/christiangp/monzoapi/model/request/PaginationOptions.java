package com.christiangp.monzoapi.model.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Endpoints which enumerate objects support time-based and cursor-based pagination.
 * Build a new instance using {@linkplain Builder the builder}. For example:
 * <pre><code>
 * PaginationOptions options = new PaginationOptions.Builder()
 *     .limit(20)
 *     .before("2009-11-10T23:00:00Z"
 *     .after("tx_00008zhJ3kE6c8kmsGUKgn")
 *     .build();
 * </code></pre>
 *
 * @see <a href="https://monzo.com/docs/#pagination">https://monzo.com/docs/#pagination</a>
 */
public final class PaginationOptions
        extends ImmutableHashMap<String, String> {

    private PaginationOptions(Map<String, String> map) {
        super(map);
    }

    /**
     * Creates a {@link PaginationOptions} object representing no pagination.
     */
    public static PaginationOptions noPagination() {
        return new PaginationOptions(Collections.<String, String>emptyMap());
    }

    /**
     * Build a new {@link PaginationOptions}.
     * <p>
     * All methods are optional.
     */
    public final static class Builder {

        private Integer limit;

        private String  after;

        private String  before;

        /**
         * @see <a href="https://monzo.com/docs/#pagination">https://monzo.com/docs/#pagination</a>
         */
        public Builder limitItemsTo(int limit) {
            this.limit = limit;
            return this;
        }

        /**
         * @see <a href="https://monzo.com/docs/#pagination">https://monzo.com/docs/#pagination</a>
         */
        public Builder before(String before) {
            this.before = before;
            return this;
        }

        /**
         * @see <a href="https://monzo.com/docs/#pagination">https://monzo.com/docs/#pagination</a>
         */
        public Builder after(String after) {
            this.after = after;
            return this;
        }

        /**
         * Create the {@link PaginationOptions} instance using the configured values.
         * <p>
         * Note: If no modifier methods are called, it will have the same effect as {@link PaginationOptions#noPagination()}
         */
        public PaginationOptions build() {
            final Map<String, String> map = new HashMap<>();
            if (limit != null) {
                map.put("limit", limit.toString());
            }
            if (after != null) {
                map.put("since", after);
            }
            if (before != null) {
                map.put("before", before);
            }
            return new PaginationOptions(map);
        }
    }
}
