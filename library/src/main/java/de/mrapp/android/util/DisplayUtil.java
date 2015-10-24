/*
 * AndroidUtil Copyright 2015 Michael Rapp
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

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as an {@link Integer} value
     * @return The calculated dp value as an {@link Integer} value. The value might be rounded
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as an {@link Long} value
     * @return The calculated dp value as an {@link Long} value. The value might be rounded
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as a {@link Float} value
     * @return The calculated dp value as a {@link Float} value
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param pixels
     *         The pixel value, which should be converted, as a {@link Double} value
     * @return The calculated dp value as a {@link Double} value
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param dp
     *         The dp value, which should be converted, as an {@link Integer} value
     * @return The calculated pixel value as an {@link Integer} value. The value might be rounded
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param dp
     *         The dp value, which should be converted, as an {@link Integer} value
     * @return The calculated pixel value as an {@link Integer} value. The value might be rounded
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param dp
     *         The dp value, which should be converted, as a {@link Float} value
     * @return The calculated pixel value as a {@link Float} value
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
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
     *         The context, which should be used to retrieve the device's display metrics, as an
     *         instance of the class {@link Context}. The context may not be null
     * @param dp
     *         The dp value, which should be converted, as a {@link Double} value
     * @return The calculated pixel value as a {@link Double} value
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
    public static double dpToPixels(@NonNull final Context context, final double dp) {
        ensureNotNull(context, "The context may not be null");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * (displayMetrics.densityDpi / PIXEL_DP_RATIO);
    }

}