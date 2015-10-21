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

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;

import static de.mrapp.android.util.Condition.ensureAtLeast;
import static de.mrapp.android.util.Condition.ensureAtMaximum;
import static de.mrapp.android.util.Condition.ensureNotNull;
import static de.mrapp.android.util.DisplayUtil.dpToPixels;

/**
 * An utility class, which provides static methods, which allow to emulate elevations of views on
 * pre-Lollipop devices.
 *
 * @author Michael Rapp
 * @since 1.1.0
 */
public final class ElevationUtil {

    /**
     * Contains all possible orientations of a shadow in relation to an elevated view.
     */
    public enum Orientation {

        LEFT,

        RIGHT,

        TOP,

        BOTTOM

    }

    /**
     * Returns an array, which contains the shadow colors, which can be used to emulate the
     * elevation of a view on pre-Lollipop devices, depending on a specific orientation.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>RIGHT</code>, <code>TOP</code> or <code>BOTTOM</code>
     * @return An array, which contains the shadow colors, which can be used to emulate the
     * elevation of a view on pre-Lollipop devices, depending on the given orientation
     */
    private static int[] getShadowColors(@NonNull final Context context,
                                         @NonNull final Orientation orientation) {
        Resources resources = context.getResources();

        switch (orientation) {
            case TOP:
                return resources.getIntArray(R.array.top_elevation_shadow_colors);
            case BOTTOM:
                return resources.getIntArray(R.array.bottom_elevation_shadow_colors);
            default:
                return resources.getIntArray(R.array.horizontal_elevation_shadow_colors);
        }
    }

    /**
     * Returns an array, which contains the shadow widths, which can be used to emulate the
     * elevation of a view on pre-Lollipop devices, depending on a specific orientation.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>RIGHT</code>, <code>TOP</code> or <code>BOTTOM</code>
     * @return An array, which contains the shadow widths, which can be used to emulate the
     * elevation of a view on pre-Lollipop devices, depending on the given orientation, in dp
     */
    private static int[] getShadowWidths(@NonNull final Context context,
                                         @NonNull final Orientation orientation) {
        Resources resources = context.getResources();

        switch (orientation) {
            case TOP:
                return resources.getIntArray(R.array.top_elevation_shadow_widths);
            case BOTTOM:
                return resources.getIntArray(R.array.bottom_elevation_shadow_widths);
            default:
                return resources.getIntArray(R.array.horizontal_elevation_shadow_widths);
        }
    }

    /**
     * Returns the orientation of the gradient, which can be used to emulate the elevation of a view
     * on pre-Lollipop devices, depending on a specific orientation.
     *
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>RIGHT</code>, <code>TOP</code> or <code>BOTTOM</code>
     * @return The orientation of the gradient, which can be used to emulate the elevation of a view
     * on pre-Lollipop devices, depending on the given orientation, as a value of the enum {@link
     * GradientDrawable.Orientation}
     */
    private static GradientDrawable.Orientation getGradientOrientation(
            @NonNull final Orientation orientation) {
        switch (orientation) {
            case LEFT:
                return GradientDrawable.Orientation.LEFT_RIGHT;
            case RIGHT:
                return GradientDrawable.Orientation.RIGHT_LEFT;
            case BOTTOM:
                return GradientDrawable.Orientation.TOP_BOTTOM;
            default:
                return GradientDrawable.Orientation.BOTTOM_TOP;
        }
    }

    /**
     * Creates a new utility class, which provides static methods, which allow to emulate elevations
     * of views on pre-Lollipop devices.
     */
    private ElevationUtil() {

    }

    /**
     * Creates and returns a drawable, which can be used to emulate the shadow of an elevated view
     * on pre-Lollipop devices.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 1 and at maximum 5
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>RIGHT</code>, <code>TOP</code> or <code>BOTTOM</code>
     * @return The drawable, which has been created, as an instance of the class {@link Drawable}
     */
    public static Drawable createElevationShadow(@NonNull final Context context,
                                                 final int elevation,
                                                 @NonNull final Orientation orientation) {
        ensureNotNull(context, "The context may not be null");
        ensureAtLeast(elevation, 1, "The elevation must be at least 0");
        ensureAtMaximum(elevation, 5, "The elevation must be at least 1");
        ensureNotNull(orientation, "The orientation may not be null");
        int[] shadowColors = getShadowColors(context, orientation);
        int shadowColor = shadowColors[elevation - 1];
        return new GradientDrawable(getGradientOrientation(orientation),
                new int[]{shadowColor, Color.TRANSPARENT});
    }

    /**
     * Returns the width of the drawable, which can be used to emulate the shadow of an elevated
     * view on pre-Lollipop devices.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 1 and at maximum 5
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>RIGHT</code>, <code>TOP</code> or <code>BOTTOM</code>
     * @return The width of the drawable in pixels as an {@link Integer} value
     */
    public static int getElevationShadowWidth(@NonNull final Context context, final int elevation,
                                              final @NonNull Orientation orientation) {
        ensureNotNull(context, "The context may not be null");
        ensureAtLeast(elevation, 1, "The elevation must be at least 0");
        ensureAtMaximum(elevation, 5, "The elevation must be at least 1");
        ensureNotNull(orientation, "The orientation may not be null");
        int[] shadowWidths = getShadowWidths(context, orientation);
        return dpToPixels(context, Integer.valueOf(shadowWidths[elevation - 1]));
    }

}