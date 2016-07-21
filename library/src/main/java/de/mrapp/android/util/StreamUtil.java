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