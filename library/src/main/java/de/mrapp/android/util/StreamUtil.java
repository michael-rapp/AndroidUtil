/*
 * Copyright 2015 - 2016 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mrapp.android.util;

import android.support.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;

/**
 * An utility class, which provides static methods, which allow to handle streams.
 *
 * @author Michael Rapp
 * @since 1.9.0
 */
public final class StreamUtil {

    /**
     * Creates a new utility class, which provides static methods, wihch allow to handle streams.
     */
    private StreamUtil() {

    }

    /**
     * Gracefully closes a specific stream.
     *
     * @param stream
     *         The stream, which should be closed, as an instance of the type {@link Closeable}
     */
    public static void close(@Nullable final Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                // No need to handle
            }
        }
    }

}