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

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods for handling instances of the class {@link
 * View}.
 *
 * @author Michael Rapp
 * @since 1.4.5
 */
public final class ViewUtil {

    /**
     * Creates a new utility class, which provides static methods for handling instances of the
     * class {@link View}.
     */
    private ViewUtil() {

    }

    /**
     * Sets the background of a view. Depending on the device's API level, different methods are
     * used for setting the background.
     *
     * @param view
     *         The view, whose background should be set, as an instance of the class {@link View}.
     *         The view may not be null
     * @param background
     *         The background, which should be set, as an instance of the class {@link Drawable}, or
     *         null, if no background should be set
     */
    @SuppressWarnings("deprecation")
    public static void setBackground(@NonNull final View view,
                                     @Nullable final Drawable background) {
        ensureNotNull(view, "The view may not be null");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

}