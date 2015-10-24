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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.ColorInt;
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

        /**
         * If the shadow is located besides the left edge of the elevated view.
         */
        LEFT,

        /**
         * If the shadow is located above the top edge of the elevated view.
         */
        TOP,

        /**
         * If the shadow is located besides the right edge of the elevated view.
         */
        RIGHT,

        /**
         * If the shadow is located below the bottom edge of the elevated view.
         */
        BOTTOM,

        /**
         * If the shadow is located at the top left corner of the elevated view.
         */
        TOP_LEFT,

        /**
         * If the shadow is located at the top right corner of the elevated view.
         */
        TOP_RIGHT,

        /**
         * If the shadow is located at the bottom left corner of the elevated view.
         */
        BOTTOM_LEFT,

        /**
         * If the shadow is located at the bottom right corner of the elevated view.
         */
        BOTTOM_RIGHT

    }

    /**
     * The maximum supported elevation in dp.
     */
    public static final int MAX_ELEVATION = 16;

    /**
     * The number of degrees of a quarter arc.
     */
    private static final float QUARTER_ARC_DEGRESS = 90f;

    /**
     * The number of degrees of a complete arc.
     */
    private static final float FULL_ARC_DEGRESS = 360f;

    /**
     * The minimum alpha value of the shadow, which is located besides the left edge of an elevated
     * view.
     */
    private static final int MIN_LEFT_ALPHA = 26;

    /**
     * The maximum alpha value of the shadow, which is located besides the right edge of an elevated
     * view.
     */
    private static final int MAX_LEFT_ALPHA = 49;

    /**
     * The minimum alpha value of the shadow, which is located above the top edge of an elevated
     * view.
     */
    private static final int MIN_TOP_ALPHA = 8;

    /**
     * The maximum alpha value of the shadow, which is located below the bottom edge of an elevated
     * view.
     */
    private static final int MAX_TOP_ALPHA = 15;

    /**
     * The minimum alpha value of the shadow, which is located besides the right edge of an elevated
     * view.
     */
    private static final int MIN_RIGHT_ALPHA = 26;

    /**
     * The maximum alpha value of the shadow, which is located besides the right edge of an elevated
     * view.
     */
    private static final int MAX_RIGHT_ALPHA = 49;

    /**
     * The minimum alpha value of the shadow, which is located below the bottom edge of an elevated
     * view.
     */
    private static final int MIN_BOTTOM_ALPHA = 45;

    /**
     * The maximum alpha value of the shadow, which is located below the bottom edge of an elevated
     * view.
     */
    private static final int MAX_BOTTOM_ALPHA = 51;

    /**
     * The elevation, which is used as a reference for calculating the width of shadows in dp.
     */
    private static final int REFERENCE_ELEVATION = 10;

    /**
     * The reference shadow width, which corresponds to the elevation, which is used as a reference
     * for calculating the width of shadows, in dp.
     */
    private static final float REFERENCE_SHADOW_WIDTH = 16.5f;

    /**
     * The scale factor, which is used to calculate the width of shadows, which are located besides
     * the left edge of an elevated view, in relation to the reference shadow width.
     */
    private static final float LEFT_SCALE_FACTOR = 0.5f;

    /**
     * The scale factor, which is used to calculate the width of shadows, which are located above
     * the top edge of an elevated view, in relation to the reference shadow width.
     */
    private static final float TOP_SCALE_FACTOR = 1f / 3f;

    /**
     * The scale factor, which is used to calculate the width of shadows, which are located besides
     * the right edge of an elevated view, in relation to the reference shadow width.
     */
    private static final float RIGHT_SCALE_FACTOR = 0.5f;

    /**
     * The scale factor, which is used to calculate the width of shadows, which are located below
     * the bottom edge of an elevated view, in relation to the reference shadow width.
     */
    private static final float BOTTOM_SCALE_FACTOR = 1f;

    /**
     * Creates and returns a bitmap, which can be used to emulate a shadow, which is located at a
     * corner of an elevated view on pre-Lollipop devices.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @return The bitmap, which has been created, as an instance of the class {@link Bitmap} or
     * null, if the given elevation is 0
     */
    private static Bitmap createEdgeShadow(@NonNull final Context context, final int elevation,
                                           @NonNull final Orientation orientation) {
        if (elevation == 0) {
            return null;
        } else {
            float shadowWidth = getShadowWidth(context, elevation, orientation);
            int shadowColor = getShadowColor(elevation, orientation);
            int bitmapWidth = (int) Math
                    .round((orientation == Orientation.LEFT || orientation == Orientation.RIGHT) ?
                            Math.ceil(shadowWidth) : 1);
            int bitmapHeight = (int) Math
                    .round((orientation == Orientation.TOP || orientation == Orientation.BOTTOM) ?
                            Math.ceil(shadowWidth) : 1);
            Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Shader linearGradient =
                    createLinearGradient(orientation, bitmapWidth, bitmapHeight, shadowWidth,
                            shadowColor);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setShader(linearGradient);
            canvas.drawRect(0, 0, bitmapWidth, bitmapHeight, paint);
            return bitmap;
        }
    }

    /**
     * Creates and returns a bitmap, which can be used to emulate a shadow, which is located besides
     * an edge of an elevated view on pre-Lollipop devices.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>TOP</code>, <code>RIGHT</code> or <code>BOTTOM</code>
     * @return The bitmap, which has been created, as an instance of the class {@link Bitmap} or
     * null, if the given elevation is 0
     */
    private static Bitmap createCornerShadow(@NonNull final Context context, final int elevation,
                                             @NonNull final Orientation orientation) {
        if (elevation == 0) {
            return null;
        } else {
            float horizontalShadowWidth = getHorizontalShadowWidth(context, elevation, orientation);
            float verticalShadowWidth = getVerticalShadowWidth(context, elevation, orientation);
            int horizontalShadowColor = getHorizontalShadowColor(elevation, orientation);
            int verticalShadowColor = getVerticalShadowColor(elevation, orientation);
            int bitmapWidth = (int) Math.round(Math.ceil(verticalShadowWidth));
            int bitmapHeight = (int) Math.round(Math.ceil(horizontalShadowWidth));
            int bitmapSize = Math.max(bitmapWidth, bitmapHeight);
            Bitmap bitmap = Bitmap.createBitmap(bitmapSize, bitmapSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setDither(true);
            RectF arcBounds = getCornerBounds(orientation, bitmapSize);
            float startAngle = getCornerAngle(orientation);
            int[] sweepColors =
                    getCornerColors(orientation, horizontalShadowColor, verticalShadowColor);
            SweepGradient sweepGradient = new SweepGradient(arcBounds.left + arcBounds.width() / 2f,
                    arcBounds.top + arcBounds.height() / 2f, sweepColors,
                    new float[]{startAngle / FULL_ARC_DEGRESS, startAngle / FULL_ARC_DEGRESS +
                            QUARTER_ARC_DEGRESS / FULL_ARC_DEGRESS});
            paint.setShader(sweepGradient);
            canvas.drawArc(arcBounds, startAngle, QUARTER_ARC_DEGRESS, true, paint);
            Shader radialGradient = createRadialGradient(orientation, bitmapSize,
                    Math.max(horizontalShadowWidth, verticalShadowWidth));
            paint.setShader(radialGradient);
            paint.setColor(Color.BLACK);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.drawRect(0, 0, bitmapSize, bitmapSize, paint);
            return BitmapUtil.resize(bitmap, bitmapWidth, bitmapHeight);
        }
    }

    /**
     * Returns an array, which contains the colors, which should be used to draw a shadow, which is
     * located at a corner of an elevated view.
     *
     * @param orientation
     *         TThe orientation of the shadow in relation to the elevated view as a value of the
     *         enum {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @param horizontalShadowColor
     *         The color of the shadow, which is located next to the corner in horizontal direction,
     *         as an {@link Integer} value
     * @param verticalShadowColor
     *         The color of the shadow, which is located next to the corner in vertical direction,
     *         as an {@link Integer}  value
     * @return The colors, which should be used to draw the shadow, as an {@link Integer} array
     */
    private static int[] getCornerColors(@NonNull Orientation orientation,
                                         @ColorInt final int horizontalShadowColor,
                                         @ColorInt final int verticalShadowColor) {
        switch (orientation) {
            case TOP_RIGHT:
                return new int[]{horizontalShadowColor, verticalShadowColor};
            case TOP_LEFT:
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return new int[]{verticalShadowColor, horizontalShadowColor};
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Returns the bounds, which should be used to draw a shadow, which is located at a corner of an
     * elevated view.
     *
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @param size
     *         The size of the bitmap, which is used to draw the shadow, in pixels as an {@link
     *         Integer} value
     * @return The bounds, which should be used to draw the shadow, as an instance of the class
     * {@link RectF}
     */
    private static RectF getCornerBounds(@NonNull final Orientation orientation, final int size) {
        switch (orientation) {
            case TOP_LEFT:
                return new RectF(0, 0, 2 * size, 2 * size);
            case TOP_RIGHT:
                return new RectF(-size, 0, size, 2 * size);
            case BOTTOM_LEFT:
                return new RectF(0, -size, 2 * size, size);
            case BOTTOM_RIGHT:
                return new RectF(-size, -size, size, size);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Returns the width of a shadow, which is located next to a corner of an elevated view in
     * horizontal direction.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @return The width of the shadow in pixels as a {@link Float} value
     */
    private static float getHorizontalShadowWidth(@NonNull final Context context,
                                                  final int elevation,
                                                  @NonNull final Orientation orientation) {
        switch (orientation) {
            case TOP_LEFT:
            case TOP_RIGHT:
                return getShadowWidth(context, elevation, Orientation.TOP);
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return getShadowWidth(context, elevation, Orientation.BOTTOM);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Returns the width of a shadow, which is located next to a corner of an elevated view in
     * vertical direction.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @return The width of the shadow in pixels as a {@link Float} value
     */
    private static float getVerticalShadowWidth(@NonNull Context context, final int elevation,
                                                @NonNull final Orientation orientation) {
        switch (orientation) {
            case TOP_LEFT:
            case BOTTOM_LEFT:
                return getShadowWidth(context, elevation, Orientation.LEFT);
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                return getShadowWidth(context, elevation, Orientation.RIGHT);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Returns the width of a shadow, which is located besides an edge of an elevated view.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>TOP</code>, <code>RIGHT</code> or <code>BOTTOM</code>
     * @return The width of the shadow in pixels as a {@link Float} value
     */
    private static float getShadowWidth(@NonNull final Context context, final int elevation,
                                        @NonNull final Orientation orientation) {
        float referenceElevationWidth =
                (float) elevation / (float) REFERENCE_ELEVATION * REFERENCE_SHADOW_WIDTH;
        float shadowWidth;

        switch (orientation) {
            case LEFT:
                shadowWidth = referenceElevationWidth * LEFT_SCALE_FACTOR;
                break;
            case TOP:
                shadowWidth = referenceElevationWidth * TOP_SCALE_FACTOR;
                break;
            case RIGHT:
                shadowWidth = referenceElevationWidth * RIGHT_SCALE_FACTOR;
                break;
            case BOTTOM:
                shadowWidth = referenceElevationWidth * BOTTOM_SCALE_FACTOR;
                break;
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }

        return dpToPixels(context, shadowWidth);
    }

    /**
     * Returns the color of a shadow, which is located next to a corner of an elevated view in
     * horizontal direction.
     *
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @return The color of the shadow as an {@link Integer} value
     */
    private static int getHorizontalShadowColor(final int elevation,
                                                @NonNull final Orientation orientation) {
        switch (orientation) {
            case TOP_LEFT:
            case TOP_RIGHT:
                return getShadowColor(elevation, Orientation.TOP);
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return getShadowColor(elevation, Orientation.BOTTOM);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Returns the color of a shadow, which is located next to a corner of an elevated view in
     * vertical direction.
     *
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @return The color of the shadow as an {@link Integer} value
     */
    private static int getVerticalShadowColor(final int elevation,
                                              @NonNull final Orientation orientation) {
        switch (orientation) {
            case TOP_LEFT:
            case BOTTOM_LEFT:
                return getShadowColor(elevation, Orientation.LEFT);
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                return getShadowColor(elevation, Orientation.RIGHT);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Returns the color of a shadow, which is located besides an edge of an elevated view.
     *
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>TOP</code>, <code>RIGHT</code> or <code>BOTTOM</code>
     * @return The color of the shadow as an {@link Integer} value
     */
    private static int getShadowColor(final int elevation, @NonNull final Orientation orientation) {
        int alpha;

        switch (orientation) {
            case LEFT:
                alpha = getShadowAlpha(elevation, MIN_LEFT_ALPHA, MAX_LEFT_ALPHA);
                break;
            case TOP:
                alpha = getShadowAlpha(elevation, MIN_TOP_ALPHA, MAX_TOP_ALPHA);
                break;
            case RIGHT:
                alpha = getShadowAlpha(elevation, MIN_RIGHT_ALPHA, MAX_RIGHT_ALPHA);
                break;
            case BOTTOM:
                alpha = getShadowAlpha(elevation, MIN_BOTTOM_ALPHA, MAX_BOTTOM_ALPHA);
                break;
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }

        return Color.argb(alpha, 0, 0, 0);
    }

    /**
     * Returns the alpha value of a shadow by interpolating between a minimum and maximum alpha
     * value, depending on a specific elevation.
     *
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param minTransparency
     *         The minimum alpha value, which should be used, if the given elevation is 0, as an
     *         {@link Integer} value between 0 and 255
     * @param maxTransparency
     *         The maximum alpha value, which should be used, if the given elevation is
     *         <code>MAX_ELEVATION</code>, as an {@link Integer} value between 0 and 255
     * @return The alpha value of the shadow as an {@link Integer} value between 0 and 255
     */
    private static int getShadowAlpha(final int elevation, final int minTransparency,
                                      final int maxTransparency) {
        float ratio = (float) elevation / (float) MAX_ELEVATION;
        int range = maxTransparency - minTransparency;
        return Math.round(minTransparency + ratio * range);
    }

    /**
     * Creates and returns a shader, which can be used to draw a shadow, which located besides an
     * edge of an elevated view.
     *
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>TOP</code>, <code>RIGHT</code> or <code>BOTTOM</code>
     * @param bitmapWidth
     *         The width of the bitmap, which is used to draw the shadow, in pixels as an {@link
     *         Integer} value
     * @param bitmapHeight
     *         The height of the bitmap, which is used to draw the shadow, in pixels as an {@link
     *         Integer} value
     * @param shadowWidth
     *         The width of the shadow in pixels as a {@link Float} value
     * @param shadowColor
     *         The color of the shadow as an {@link Integer} value
     * @return The shader, which has been created as an instance of the class {@link Shader}
     */
    private static Shader createLinearGradient(@NonNull final Orientation orientation,
                                               final int bitmapWidth, final int bitmapHeight,
                                               final float shadowWidth,
                                               @ColorInt final int shadowColor) {
        RectF bounds = new RectF();

        switch (orientation) {
            case LEFT:
                bounds.left = bitmapWidth;
                bounds.right = bitmapWidth - shadowWidth;
                break;
            case TOP:
                bounds.top = bitmapHeight;
                bounds.bottom = bitmapHeight - shadowWidth;
                break;
            case RIGHT:
                bounds.right = shadowWidth;
                break;
            case BOTTOM:
                bounds.bottom = shadowWidth;
                break;
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }

        return new LinearGradient(bounds.left, bounds.top, bounds.right, bounds.bottom, shadowColor,
                Color.TRANSPARENT, Shader.TileMode.CLAMP);
    }

    /**
     * Creates and returns a shader, which can be used to draw a shadow, which located at a corner
     * of an elevated view.
     *
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @param bitmapSize
     *         The size of the bitmap, which is used to draw the shadow, in pixels as an {@link
     *         Integer} value
     * @param radius
     *         The radius, which should be used to draw the shadow, in pixels as a {@link Float}
     *         value
     * @return The shader, which has been created as an instance of the class {@link Shader}
     */
    private static Shader createRadialGradient(@NonNull final Orientation orientation,
                                               final int bitmapSize, final float radius) {
        PointF center = new PointF();

        switch (orientation) {
            case TOP_LEFT:
                center.x = bitmapSize;
                center.y = bitmapSize;
                break;
            case TOP_RIGHT:
                center.y = bitmapSize;
                break;
            case BOTTOM_LEFT:
                center.x = bitmapSize;
                break;
            case BOTTOM_RIGHT:
                break;
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }

        return new RadialGradient(center.x, center.y, radius, Color.TRANSPARENT, Color.BLACK,
                Shader.TileMode.CLAMP);
    }

    /**
     * Returns the angle, which should be used to draw a shadow, which is located at a corner of an
     * elevated view.
     *
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @return The angle, which should be used to draw the shadow, as a {@link Float} value
     */
    private static float getCornerAngle(@NonNull final Orientation orientation) {
        switch (orientation) {
            case TOP_LEFT:
                return QUARTER_ARC_DEGRESS * 2;
            case TOP_RIGHT:
                return QUARTER_ARC_DEGRESS * 3;
            case BOTTOM_LEFT:
                return QUARTER_ARC_DEGRESS;
            case BOTTOM_RIGHT:
                return 0;
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Creates a new utility class, which provides static methods, which allow to emulate elevations
     * of views on pre-Lollipop devices.
     */
    private ElevationUtil() {

    }

    /**
     * Creates and returns a bitmap, which can be used to emulate a shadow of an elevated view on
     * pre-Lollipop devices.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param elevation
     *         The elevation, which should be emulated, in dp as an {@link Integer} value. The
     *         elevation must be at least 0 and at maximum the value of the constant
     *         <code>MAX_ELEVATION</code>
     * @param orientation
     *         The orientation of the shadow in relation to the elevated view as a value of the enum
     *         {@link Orientation}. The orientation may either be <code>LEFT</code>,
     *         <code>RIGHT</code>, <code>TOP</code>, <code>BOTTOM</code>, <code>TOP_LEFT</code>,
     *         <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     * @return The bitmap, which has been created, as an instance of the class {@link Bitmap} or
     * null, if the given elevation is 0
     */
    public static Bitmap createElevationShadow(@NonNull final Context context, final int elevation,
                                               @NonNull final Orientation orientation) {
        ensureNotNull(context, "The context may not be null");
        ensureAtLeast(elevation, 0, "The elevation must be at least 0");
        ensureAtMaximum(elevation, MAX_ELEVATION,
                "The elevation must be at maximum " + MAX_ELEVATION);
        ensureNotNull(orientation, "The orientation may not be null");

        switch (orientation) {
            case LEFT:
            case TOP:
            case RIGHT:
            case BOTTOM:
                return createEdgeShadow(context, elevation, orientation);
            case TOP_LEFT:
            case TOP_RIGHT:
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                return createCornerShadow(context, elevation, orientation);
            default:
                throw new IllegalArgumentException("Invalid orientation: " + orientation);
        }
    }

}