//
// MIT License
//
// Copyright (c) 2020 IntellectualSites
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
package com.intellectualsites.http;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * HTTP methods
 */
public enum HttpMethod {

    /**
     * Post requests are used to handle data
     */
    POST,

    /**
     * Get requests are handled for getting resources
     */
    GET,

    /**
     * Add data to an external source
     */
    PUT,

    /**
     * Patch content on an external source
     */
    PATCH,

    /**
     * Retrieve the headers for a request
     */
    HEAD(false),

    /**
     * Delete content from an external source
     */
    DELETE;

    private static Map<String, HttpMethod> METHOD_CACHE;

    private final boolean hasBody;

    HttpMethod() {
        this(true);
    }

    HttpMethod(final boolean hasBody) {
        this.hasBody = hasBody;
    }

    @NotNull public static Optional<HttpMethod> getByName(@NotNull final String name) {
        if (METHOD_CACHE == null) {
            METHOD_CACHE = new HashMap<>();
            for (final HttpMethod method : values()) {
                METHOD_CACHE.put(method.name(), method);
            }
        }
        final String fixed = name.replaceAll("\\s", "").toUpperCase(Locale.ENGLISH);
        return Optional.ofNullable(METHOD_CACHE.get(fixed));
    }

    /**
     * Whether or not the method should return an entity
     *
     * @return Whether or not a response entity should be expected
     */
    public boolean hasBody() {
        return this.hasBody;
    }

}
