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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods, which are related to a device's display
 * metrics.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public class DisplayUtil {

    /**
     * Contains all possible orientations of devices.
     */
    public enum Orientation {

        /**
         * When the device is in portrait mode.
         */
        PORTRAIT,

        /**
         * When the device is in landscape mode.
         */
        LANDSCAPE,

        /**
         * When the width and height of the device's display are equal.
         */
        SQUARE

    }

    /**
     * Contains all possible types of devices, depending on their display size.
     */
    public enum DeviceType {

        /**
         * When the device is a phone, e.g. 4-, 5- or 6-inch devices like Google's Nexus 4, 5 and
         * 6.
         */
        PHONE("phone"),

        /**
         * When the device is a small tablet (phablet), e.g. a 7-inch devices like Google's Nexus
         * 7.
         */
        PHABLET("phablet"),

        /**
         * When the device is a tablet, e.g. a 10-inch devices like Google's Nexus 10.
         */
        TABLET("tablet");

        /**
         * The value of the device type.
         */
        private String value;

        /**
         * Creates a new device type.
         *
         * @param value
         *         The value of the device type as a {@link String}
         */
        DeviceType(final String value) {
            this.value = value;
        }

        /**
         * Returns the value of the device type.
         *
         * @return The value of the device type as a {@link String}
         */
        public final String getValue() {
            return value;
        }

        /**
         * Returns the device type, which corresponds to a specific value.
         *
         * @param value
         *         The value of the device type, which should be returned, as a {@link String}
         * @return The device type, which corresponds to the given value, as a value of the enum
         * {@link DeviceType}
         */
        public static DeviceType fromValue(final String value) {
            for (DeviceType deviceType : values()) {
                if (deviceType.getValue().equals(value)) {
                    return deviceType;
                }
            }

            throw new IllegalArgumentException("Invalid enum value: " + value);
        }

    }

    /**
     * The ratio, which can be used to convert values, which are measured in pixels, into values,
     * which are measured in dp, and vice versa.
     */
    @VisibleForTesting
    protected static final float PIXEL_DP_RATIO = 160.0f;

    /**
     * Creates a new utility class, which provides static methods, which are related to a device's
     * display metrics.
     */
    private DisplayUtil() {

    }

    /**
     * Converts an {@link Integer} value, which is measured in pixels, into a value, which is
     * measured in dp.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as an {@link Integer} value
     * @return The calculated dp value as an {@link Integer} value. The value might be rounded
     */
    public static int pixelsToDp(@NonNull final Context context, final int pixels) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(pixels / (displayMetrics.densityDpi / PIXEL_DP_RATIO));
    }

    /**
     * Converts an {@link Long} value, which is measured in pixels, into a value, which is measured
     * in dp.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as an {@link Long} value
     * @return The calculated dp value as an {@link Long} value. The value might be rounded
     */
    public static long pixelsToDp(@NonNull final Context context, final long pixels) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(pixels / (displayMetrics.densityDpi / PIXEL_DP_RATIO));
    }

    /**
     * Converts a {@link Float} value, which is measured in pixels, into a value, which is measured
     * in dp.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as a {@link Float} value
     * @return The calculated dp value as a {@link Float} value
     */
    public static float pixelsToDp(@NonNull final Context context, final float pixels) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return pixels / (displayMetrics.densityDpi / PIXEL_DP_RATIO);
    }

    /**
     * Converts a {@link Double} value, which is measured in pixels, into a value, which is measured
     * in dp.
     *
     * @param context
     *         The context, which should be used to, as an instance of the class {@link Context}.
     *         The context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as a {@link Double} value
     * @return The calculated dp value as a {@link Double} value
     */
    public static double pixelsToDp(@NonNull final Context context, final double pixels) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return pixels / (displayMetrics.densityDpi / PIXEL_DP_RATIO);
    }

    /**
     * Converts an {@link Integer} value, which is measured in dp, into a value, which is measured
     * in pixels.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param dp
     *         The dp value, which should be converted, as an {@link Integer} value
     * @return The calculated pixel value as an {@link Integer} value. The value might be rounded
     */
    public static int dpToPixels(@NonNull final Context context, final int dp) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / PIXEL_DP_RATIO));
    }

    /**
     * Converts an {@link Integer} value, which is measured in dp, into a value, which is measured
     * in pixels.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param dp
     *         The dp value, which should be converted, as an {@link Integer} value
     * @return The calculated pixel value as an {@link Integer} value. The value might be rounded
     */
    public static long dpToPixels(@NonNull final Context context, final long dp) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / PIXEL_DP_RATIO));
    }

    /**
     * Converts a {@link Float} value, which is measured in dp, into a value, which is measured in
     * pixels.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param dp
     *         The dp value, which should be converted, as a {@link Float} value
     * @return The calculated pixel value as a {@link Float} value
     */
    public static float dpToPixels(@NonNull final Context context, final float dp) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * (displayMetrics.densityDpi / PIXEL_DP_RATIO);
    }

    /**
     * Converts a {@link Double} value, which is measured in dp, into a value, which is measured in
     * pixels.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param dp
     *         The dp value, which should be converted, as a {@link Double} value
     * @return The calculated pixel value as a {@link Double} value
     */
    public static double dpToPixels(@NonNull final Context context, final double dp) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * (displayMetrics.densityDpi / PIXEL_DP_RATIO);
    }

    /**
     * Returns the orientation of the device.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The orientation of the device as a value of the enum {@link Orientation}. The
     * orientation may either be <code>PORTRAIT</code>, <code>LANDSCAPE</code> or
     * <code>SQUARE</code>
     */
    public static Orientation getOrientation(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        int orientation = context.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_UNDEFINED) {
            int width = getDisplayWidth(context);
            int height = getDisplayHeight(context);

            if (width > height) {
                return Orientation.LANDSCAPE;
            } else if (width < height) {
                return Orientation.PORTRAIT;
            } else {
                return Orientation.SQUARE;
            }
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return Orientation.LANDSCAPE;
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return Orientation.PORTRAIT;
        } else {
            return Orientation.SQUARE;
        }
    }

    /**
     * Returns the type of the device, depending on its display size.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The type of the device as a value of the enum {@link DeviceType}. The type may either
     * be <code>PHONE</code>, <code>PHABLET</code> or <code>TABLET</code>
     */
    public static DeviceType getDeviceType(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        return DeviceType.fromValue(context.getString(R.string.device_type));
    }

    /**
     * Returns the width of the device's display.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The width of the device's display in pixels as an {@link Integer} value
     */
    public static int getDisplayWidth(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Returns the height of the device's display.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The height of the device's display in pixels as an {@link Integer} value
     */
    public static int getDisplayHeight(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * Returns the logical density of the device's display.
     *
     * This is a scaling factor for the density-independent pixel unit, where one DIP is one pixel
     * on an approximately 160 dpi screen (for example a 240x320, 1.5"x2" screen), providing the
     * baseline of the system's display. Thus on a 160dpi screen this density value will be 1; on a
     * 120 dpi screen it would be .75; etc.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The logical density of the device's display as a {@link Float} value
     */
    public static float getDensity(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * Returns the height of the status bar, which is shown at the top of the display (containing
     * the clock, battery indicator, etc.).
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The height of the status bar in pixels as an {@link Integer} value
     * @deprecated Use a {@link android.view.View.OnApplyWindowInsetsListener} instead
     */
    @Deprecated
    public static int getStatusBarHeight(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        int resourceId =
                context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }

    /**
     * Returns the height of the navigation bar, which is shown at the bottom of the display
     * (containing for example back, home and recent apps soft-keys).
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The height of the navigation bar in pixels as an {@link Integer} value or 0, if no
     * navigation bar is shown
     * @deprecated Use a {@link android.view.View.OnApplyWindowInsetsListener} instead
     */
    @Deprecated
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static int getNavigationBarHeight(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            Orientation orientation = getOrientation(context);
            int resourceId;

            if (getDeviceType(context) == DeviceType.TABLET) {
                resourceId = context.getResources().getIdentifier(
                        orientation == Orientation.PORTRAIT ? "navigation_bar_height" :
                                "navigation_bar_height_landscape", "dimen", "android");
            } else {
                resourceId = context.getResources().getIdentifier(
                        orientation == Orientation.PORTRAIT ? "navigation_bar_height" :
                                "navigation_bar_width", "dimen", "android");
            }

            if (resourceId > 0) {
                return context.getResources().getDimensionPixelSize(resourceId);
            }
        }

        return 0;
    }

}