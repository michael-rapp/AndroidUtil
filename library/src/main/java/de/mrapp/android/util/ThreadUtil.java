package de.mrapp.android.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods, which allow to handle threads.
 *
 * @author Michael Rapp
 * @since 1.10.0
 */
public class ThreadUtil {

    /**
     * Creates a new utility class, which provides static methods, which allow to handle threads.
     */
    private ThreadUtil() {

    }

    /**
     * Executes a specific runnable on the UI thread.
     *
     * @param runnable
     *         The runnable, which should be executed, as an instance of the type {@link Runnable}.
     *         The runnable may not be null
     */
    public static void runOnUiThread(@NonNull final Runnable runnable) {
        ensureNotNull(runnable, "The runnable may not be null");
        new Handler(Looper.getMainLooper()).post(runnable);
    }

}