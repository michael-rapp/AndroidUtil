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
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.test.AndroidTestCase;

/**
 * Tests the functionality of the class {@link BitmapUtilTest}.
 *
 * @author Michael Rapp
 */
public class BitmapUtilTest extends AndroidTestCase {

    /**
     * Tests the functionality of the method, which allows to clip a circle.
     */
    public final void testClipCircle() {
        int width = 2;
        int height = width * 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap circle = BitmapUtil.clipCircle(bitmap);
        assertEquals(width, circle.getWidth());
        assertEquals(width, circle.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to clip a circle and resize it.
     */
    public final void testClipCircleWithSizeParameter() {
        int width = 2;
        int height = width * 2;
        int size = 1;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap circle = BitmapUtil.clipCircle(bitmap, size);
        assertEquals(size, circle.getWidth());
        assertEquals(size, circle.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to clip a circle and add a border to it.
     */
    public final void testClipCircleWithBorder() {
        int width = 2;
        int height = width * 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap circle = BitmapUtil.clipCircle(bitmap, 1, Color.BLACK);
        assertEquals(width, circle.getWidth());
        assertEquals(width, circle.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to clip a circle, resize it and add a
     * border to it.
     */
    public final void testClipCircleWithBorderAndSizeParameter() {
        int width = 2;
        int height = width * 2;
        int size = 1;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap circle = BitmapUtil.clipCircle(bitmap, size, 1, Color.BLACK);
        assertEquals(size, circle.getWidth());
        assertEquals(size, circle.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to clip a square.
     */
    public final void testClipSquare() {
        int width = 2;
        int height = width * 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap square = BitmapUtil.clipSquare(bitmap);
        assertEquals(width, square.getWidth());
        assertEquals(width, square.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to clip a square and resize it.
     */
    public final void testClipSquareWithSizeParameter() {
        int size = 1;
        int width = 2;
        int height = width * 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap square = BitmapUtil.clipSquare(bitmap, size);
        assertEquals(size, square.getWidth());
        assertEquals(size, square.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to clip a square and add a border to it.
     */
    public final void testClipSquareWithBorder() {
        int width = 2;
        int height = width * 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap square = BitmapUtil.clipSquare(bitmap, 1, Color.BLACK);
        assertEquals(width, square.getWidth());
        assertEquals(width, square.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to clip a square, resize it and add a
     * border to it.
     */
    public final void testClipSquareWithBorderAndSizeParameter() {
        int width = 2;
        int height = width * 2;
        int size = 1;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap square = BitmapUtil.clipSquare(bitmap, size, 1, Color.BLACK);
        assertEquals(size, square.getWidth());
        assertEquals(size, square.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to resize a bitmap.
     */
    public final void testResize() {
        int width = 2;
        int height = width * 2;
        int newWidth = 1;
        int newHeight = 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, width, height), paint);
        Bitmap resizedBitmap = BitmapUtil.resize(bitmap, newWidth, newHeight);
        assertEquals(newWidth, resizedBitmap.getWidth());
        assertEquals(newHeight, resizedBitmap.getHeight());
    }

    /**
     * Tests the functionality of the method, which allows to tint a bitmap.
     */
    public final void testTint() {
        int color = Color.RED;
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), paint);
        bitmap = BitmapUtil.tint(bitmap, color);
        assertEquals(color, bitmap.getPixel(0, 0));
    }

    /**
     * Tests the functionality of the method, which allows to create a tiled bitmap.
     */
    public final void testTile() {
        Bitmap bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, 1, 2), paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(new Rect(1, 0, 2, 2), paint);
        Bitmap tiledBitmap = BitmapUtil.tile(bitmap, 3, 2);
        assertEquals(Color.WHITE, tiledBitmap.getPixel(2, 0));
        assertEquals(Color.WHITE, tiledBitmap.getPixel(2, 1));
    }

    /**
     * Tests the functionality of the method, which allows to convert a {@link Drawable} to a {@link
     * Bitmap}, if the drawable is a {@link BitmapDrawable}.
     */
    @SuppressWarnings("deprecation")
    public final void testDrawableToBitmapWithBitmapDrawable() {
        int color = Color.RED;
        Bitmap bmp = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(new Rect(0, 0, bmp.getWidth(), bmp.getHeight()), paint);
        Drawable drawable = new BitmapDrawable(bmp);
        Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
        assertEquals(bmp.getWidth(), bitmap.getWidth());
        assertEquals(bmp.getHeight(), bitmap.getHeight());
        assertEquals(color, bitmap.getPixel(0, 0));
    }

    /**
     * Tests the functionality of the method, which allows to convert a {@link Drawable} to a {@link
     * Bitmap}, if the drawable is a {@link ColorDrawable}.
     */
    public final void testDrawableToBitmapWithColorDrawable() {
        int color = Color.RED;
        Drawable drawable = new ColorDrawable(color);
        Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
        assertEquals(1, bitmap.getWidth());
        assertEquals(1, bitmap.getHeight());
        assertEquals(color, bitmap.getPixel(0, 0));
    }

    /**
     * Tests the functionality of the method, which allows to convert a {@link Drawable} to a {@link
     * Bitmap}, if the drawable is an image drawable.
     */
    @SuppressWarnings("deprecation")
    public final void testDrawableToBitmapWithImageDrawable() {
        Drawable drawable =
                getContext().getResources().getDrawable(android.R.drawable.ic_dialog_info);
        Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
        assertTrue(bitmap.getWidth() > 0);
        assertTrue(bitmap.getHeight() > 0);
    }

}