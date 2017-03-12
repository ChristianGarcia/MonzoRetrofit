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
 * Basic type of feed item.
 * <p>
 * Build a new instance using {@linkplain Builder the builder}. For example:
 * <pre><code>
 * FeedItemMap feedItemMap = new BasicFeedItemMap.Builder("title", "image.png")
 *     .bodyText("Some extra text")
 *     .backgroundColorHex("#FCF1EE")
 *     .titleTextColorHex("##333")
 *     .bodyTextColorHex("#333")
 *     .build();
 * </code></pre>
 *
 * @see FeedItemMap
 * @see <a href="https://monzo.com/docs/#create-feed-item">https://monzo.com/docs/#create-feed-item</a>
 */
public final class BasicFeedItemMap
        extends FeedItemMap {

    private BasicFeedItemMap(Map<String, String> map) {
        super(map, "basic");
    }

    /**
     * Builds a new {@link BasicFeedItemMap}.
     * All modifying methods are optional.
     */
    public final static class Builder {

        private final String title;

        private final String imageUrl;

        private       String bodyText;

        private       String backgroundColorHex;

        private       String titleTextColorHex;

        private       String bodyTextColorHex;

        /**
         * @see <a href="https://monzo.com/docs/#create-feed-item">https://monzo.com/docs/#create-feed-item</a>
         */
        public Builder(String title, String imageUrl) {
            this.title = title;
            this.imageUrl = imageUrl;
        }

        /**
         * @see <a href="https://monzo.com/docs/#create-feed-item">https://monzo.com/docs/#create-feed-item</a>
         */
        public Builder bodyText(String bodyText) {
            this.bodyText = bodyText;
            return this;
        }

        /**
         * @see <a href="https://monzo.com/docs/#create-feed-item">https://monzo.com/docs/#create-feed-item</a>
         */
        public Builder backgroundColorHex(String backgroundColorHex) {
            this.backgroundColorHex = backgroundColorHex;
            return this;
        }

        /**
         * @see <a href="https://monzo.com/docs/#create-feed-item">https://monzo.com/docs/#create-feed-item</a>
         */
        public Builder titleTextColorHex(String titleTextColorHex) {
            this.titleTextColorHex = titleTextColorHex;
            return this;
        }

        /**
         * @see <a href="https://monzo.com/docs/#create-feed-item">https://monzo.com/docs/#create-feed-item</a>
         */
        public Builder bodyTextColorHex(String bodyTextColorHex) {
            this.bodyTextColorHex = bodyTextColorHex;
            return this;
        }

        /**
         * Create a new {@link FeedItemMap}.
         */
        public FeedItemMap build() {
            final Map<String, String> map = new HashMap<>();
            putParamIfNotNull(map, "title", title);
            putParamIfNotNull(map, "image_url", imageUrl);
            putParamIfNotNull(map, "background_color", backgroundColorHex);
            putParamIfNotNull(map, "body_color", bodyTextColorHex);
            putParamIfNotNull(map, "title_color", titleTextColorHex);
            putParamIfNotNull(map, "body", bodyText);
            return new BasicFeedItemMap(map);
        }

        private void putParamIfNotNull(Map<String, String> map, String key, String value) {
            if (value == null) {
                return;
            }
            map.put(String.format("params[%s]", key), value);
        }
    }
}
