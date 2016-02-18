/*
 * AndroidUtil Copyright 2015 - 2016 Michael Rapp
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package de.mrapp.android.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;

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
     * Returns the height of the status bar, which is shown at the top of the display (containing
     * the clock, battery indicator, etc.).
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @return The height of the status bar in pixels as an {@link Integer} value
     */
    public static int getStatusBarHeight(@NonNull final Context context) {
        ensureNotNull(context, "The context may not be null");
        int resourceId =
                context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
    }

}