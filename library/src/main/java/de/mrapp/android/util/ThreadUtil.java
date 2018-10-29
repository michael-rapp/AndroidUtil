/*
 * Copyright 2015 - 2018 Michael Rapp
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

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

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