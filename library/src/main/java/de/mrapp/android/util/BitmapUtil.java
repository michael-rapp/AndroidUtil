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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import static de.mrapp.android.util.Condition.ensureAtLeast;
import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods, which allow to create and edit bitmaps.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public final class BitmapUtil {

    /**
     * The angle of a complete arc.
     */
    private static final int COMPLETE_ARC_ANGLE = 360;

    /**
     * Creates a new utility class, which provides static methods, which allow to create and edit
     * bitmaps.
     */
    private BitmapUtil() {

    }

    /**
     * Clips the corners of a bitmap in order to transform it into a round shape. Bitmaps, whose
     * width and height are not equal, will be clipped to a square beforehand.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipCircle(@NonNull final Bitmap bitmap) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        return clipCircle(bitmap,
                bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth());
    }

    /**
     * Clips the corners of a bitmap in order to transform it into a round shape. Additionally, the
     * bitmap is resized to a specific size. Bitmaps, whose width and height are not equal, will be
     * clipped to a square beforehand.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param size
     *         The size, the bitmap should be resized to, as an {@link Integer} value in pixels. The
     *         size must be at least 1
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipCircle(@NonNull final Bitmap bitmap, final int size) {
        Bitmap squareBitmap = clipSquare(bitmap, size);
        int squareSize = squareBitmap.getWidth();
        float radius = (float) squareSize / 2.0f;
        Bitmap clippedBitmap = Bitmap.createBitmap(squareSize, squareSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(clippedBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(squareBitmap, new Rect(0, 0, squareSize, squareSize),
                new Rect(0, 0, squareSize, squareSize), paint);
        return clippedBitmap;
    }

    /**
     * Clips the corners of a bitmap in order to transform it into a round shape. Additionally, a
     * border will be added. Bitmaps, whose width and height are not equal, will be clipped to a
     * square beforehand.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param borderWidth
     *         The width of the border as an {@link Integer} value in pixels. The width must be at
     *         least 0
     * @param borderColor
     *         The color of the border as an {@link Integer} value
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipCircle(@NonNull final Bitmap bitmap, final int borderWidth,
                                    @ColorInt final int borderColor) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        return clipCircle(bitmap,
                bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth(),
                borderWidth, borderColor);
    }

    /**
     * Clips the corners of a bitmap in order to transform it into a round shape. Additionally, the
     * bitmap is resized to a specific size and a border will be added. Bitmaps, whose width and
     * height are not equal, will be clipped to a square beforehand.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param size
     *         The size, the bitmap should be resized to, as an {@link Integer} value in pixels. The
     *         size must be at least 1
     * @param borderWidth
     *         The width of the border as an {@link Integer} value in pixels. The width must be at
     *         least 0
     * @param borderColor
     *         The color of the border as an {@link Integer} value
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipCircle(@NonNull final Bitmap bitmap, final int size,
                                    final int borderWidth, @ColorInt final int borderColor) {
        ensureAtLeast(borderWidth, 0, "The border width must be at least 0");
        Bitmap clippedBitmap = clipCircle(bitmap, size);
        Bitmap result = Bitmap.createBitmap(clippedBitmap.getWidth(), clippedBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        float offset = borderWidth / 2.0f;
        Rect src = new Rect(0, 0, clippedBitmap.getWidth(), clippedBitmap.getHeight());
        RectF dst =
                new RectF(offset, offset, result.getWidth() - offset, result.getHeight() - offset);
        canvas.drawBitmap(clippedBitmap, src, dst, null);

        if (borderWidth > 0 && Color.alpha(borderColor) != 0) {
            Paint paint = new Paint();
            paint.setFilterBitmap(false);
            paint.setAntiAlias(true);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderWidth);
            paint.setColor(borderColor);
            offset = borderWidth / 2.0f;
            RectF bounds = new RectF(offset, offset, result.getWidth() - offset,
                    result.getWidth() - offset);
            canvas.drawArc(bounds, 0, COMPLETE_ARC_ANGLE, false, paint);
        }

        return result;
    }

    /**
     * Clips the long edge of a bitmap, if its width and height are not equal, in order to form it
     * into a square.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipSquare(@NonNull final Bitmap bitmap) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        return clipSquare(bitmap,
                bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth());
    }

    /**
     * Clips the long edge of a bitmap, if its width and height are not equal, in order to form it
     * into a square. Additionally, the bitmap is resized to a specific size.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param size
     *         The size, the bitmap should be resized to, as an {@link Integer} value in pixels. The
     *         size must be at least 1
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipSquare(@NonNull final Bitmap bitmap, final int size) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        ensureAtLeast(size, 1, "The size must be at least 1");
        Bitmap clippedBitmap = bitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width > height) {
            clippedBitmap = Bitmap.createBitmap(bitmap, width / 2 - height / 2, 0, height, height);
        } else if (bitmap.getWidth() < bitmap.getHeight()) {
            clippedBitmap =
                    Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() / 2 - width / 2, width,
                            width);
        }

        if (clippedBitmap.getWidth() != size) {
            clippedBitmap = resize(clippedBitmap, size, size);
        }

        return clippedBitmap;
    }

    /**
     * Clips the long edge of a bitmap, if its width and height are not equal, in order to transform
     * it into a square. Additionally, a border will be added.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param borderWidth
     *         The width of the border as an {@link Integer} value in pixels. The width must be at
     *         least 0
     * @param borderColor
     *         The color of the border as an {@link Integer} value
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipSquare(@NonNull final Bitmap bitmap, final int borderWidth,
                                    @ColorInt final int borderColor) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        return clipSquare(bitmap,
                bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth(),
                borderWidth, borderColor);
    }

    /**
     * Clips the long edge of a bitmap, if its width and height are not equal, in order to transform
     * it into a square. Additionally, the bitmap is resized to a specific size and a border will be
     * added.
     *
     * @param bitmap
     *         The bitmap, which should be clipped, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param size
     *         The size, the bitmap should be resized to, as an {@link Integer} value in pixels. The
     *         size must be at least 1
     * @param borderWidth
     *         The width of the border as an {@link Integer} value in pixels. The width must be at
     *         least 0
     * @param borderColor
     *         The color of the border as an {@link Integer} value
     * @return The clipped bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap clipSquare(@NonNull final Bitmap bitmap, final int size,
                                    final int borderWidth, @ColorInt final int borderColor) {
        ensureAtLeast(borderWidth, 0, "The border width must be at least 0");
        Bitmap clippedBitmap = clipSquare(bitmap, size);
        Bitmap result = Bitmap.createBitmap(clippedBitmap.getWidth(), clippedBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        float offset = borderWidth / 2.0f;
        Rect src = new Rect(0, 0, clippedBitmap.getWidth(), clippedBitmap.getHeight());
        RectF dst =
                new RectF(offset, offset, result.getWidth() - offset, result.getHeight() - offset);
        canvas.drawBitmap(clippedBitmap, src, dst, null);

        if (borderWidth > 0 && Color.alpha(borderColor) != 0) {
            Paint paint = new Paint();
            paint.setFilterBitmap(false);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderWidth);
            paint.setColor(borderColor);
            offset = borderWidth / 2.0f;
            RectF bounds = new RectF(offset, offset, result.getWidth() - offset,
                    result.getWidth() - offset);
            canvas.drawRect(bounds, paint);
        }

        return result;
    }

    /**
     * Resizes a bitmap to a specific width and height. If the ratio between width and height
     * differs from the bitmap's original ratio, the bitmap is stretched.
     *
     * @param bitmap
     *         The bitmap, which should be resized, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param width
     *         The width, the bitmap should be resized to, as an {@link Integer} value in pixels.
     *         The width must be at least 1
     * @param height
     *         The height, the bitmap should be resized to, as an {@link Integer} value in pixels.
     *         The height must be at least 1
     * @return The resized bitmap as an instance of the class {@link Bitmap}
     */
    public static Bitmap resize(@NonNull final Bitmap bitmap, final int width, final int height) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        ensureAtLeast(width, 1, "The width must be at least 1");
        ensureAtLeast(height, 1, "The height must be at least 1");
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    /**
     * Creates and returns a bitmap with a specific width and height by tiling another bitmap.
     *
     * @param bitmap
     *         The bitmap, which should be tiled, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param width
     *         The width of the bitmap as an {@link Integer} value in pixels. The width must be at
     *         least 1
     * @param height
     *         The height of the bitmap as an {@link Integer} value in pixels. The height must be at
     *         least 1
     * @return The bitmap, which has been created as an instance of the class {@link Bitmap}
     */
    public static Bitmap tile(final Bitmap bitmap, final int width, final int height) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        ensureAtLeast(width, 1, "The width must be at least 1");
        ensureAtLeast(height, 1, "The height must be at least 1");
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();

        for (int x = 0; x < width; x += originalWidth) {
            for (int y = 0; y < width; y += originalHeight) {
                int copyWidth = (width - x >= originalWidth) ? originalWidth : width - x;
                int copyHeight = (height - y >= originalHeight) ? originalHeight : height - y;
                Rect src = new Rect(0, 0, copyWidth, copyHeight);
                Rect dest = new Rect(x, y, x + copyWidth, y + copyHeight);
                canvas.drawBitmap(bitmap, src, dest, null);
            }
        }

        return result;
    }

    /**
     * Creates and returns a bitmap by overlaying it with a specific color.
     *
     * @param bitmap
     *         The bitmap, which should be tinted, as an instance of the class {@link Bitmap}. The
     *         bitmap may not be null
     * @param color
     *         The color, which should be used for tinting, as an {@link Integer} value
     * @return The bitmap, which has been created, as an instance of the class {@link Bitmap}
     */
    public static Bitmap tint(@NonNull final Bitmap bitmap, @ColorInt final int color) {
        ensureNotNull(bitmap, "The bitmap may not be null");
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), paint);
        return bitmap;
    }

    /**
     * Creates and returns a bitmap from a specific drawable.
     *
     * @param drawable
     *         The drawable, which should be converted, as an instance of the class {@link
     *         Drawable}. The drawable may not be null
     * @return The bitmap, which has been created, as an instance of the class {@link Bitmap}
     */
    public static Bitmap drawableToBitmap(@NonNull final Drawable drawable) {
        ensureNotNull(drawable, "The drawable may not be null");

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        Bitmap bitmap;

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap =
                    Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                            Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}