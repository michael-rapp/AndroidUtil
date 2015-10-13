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

import android.os.Build;
import android.test.AndroidTestCase;
import android.util.DisplayMetrics;

/**
 * Tests the functionality of the class {@link DisplayUtil}.
 *
 * @author Michael Rapp
 */
public class DisplayUtilTest extends AndroidTestCase {

    /**
     * Tests the functionality of the method, which allows to convert an {@link Integer} value,
     * which is measured in pixels, into a value, which is measured in dp.
     */
    public final void testPixelsToDpWithIntegerParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            int pixels = 5;
            int dp = DisplayUtil.pixelsToDp(getContext(), pixels);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(
                    Math.round(pixels / (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO)),
                    dp);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * an {@link Integer} value, which is measured in pixels, into a value, which is measured in dp,
     * if the context is null.
     */
    public final void testPixelsToDpWithIntegerParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.pixelsToDp(null, 0);
            } catch (NullPointerException e) {
                return;
            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to convert a {@link Float} value, which
     * is measured in pixels, into a value, which is measured in dp.
     */
    public final void testPixelsToDpWithFloatParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            float pixels = 5.5f;
            float dp = DisplayUtil.pixelsToDp(getContext(), pixels);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(pixels / (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO), dp);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * a {@link Float} value, which is measured in pixels, into a value, which is measured in dp, if
     * the context is null.
     */
    public final void testPixelsToDpWithFloatParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.pixelsToDp(null, 0f);
            } catch (NullPointerException e) {
                return;
            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to convert an {@link Integer} value,
     * which is measured in dp, into a value, which is measured in pixels.
     */
    public final void testDpToPixelsWithIntegerParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            int dp = 5;
            int pixels = DisplayUtil.dpToPixels(getContext(), dp);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(Math.round(dp * (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO)),
                    pixels);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * an {@link Integer} value, which is measured in dp into a value, which is measured in pixels,
     * if the context is null.
     */
    public final void testDpToPixelsWithIntegerParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.dpToPixels(null, 0);
            } catch (NullPointerException e) {
                return;
            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to convert a {@link Float} value, which
     * is measured in dp, into a value, which is measured in pixels.
     */
    public final void testDpToPixelsWithFloatParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            float dp = 5.5f;
            float pixels = DisplayUtil.dpToPixels(getContext(), dp);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(dp * (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO), pixels);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * a {@link Float} value, which is measured in dp into a value, which is measured in pixels, if
     * the context is null.
     */
    public final void testDpToPixelsWithFloatParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.dpToPixels(null, 0.0f);
            } catch (NullPointerException e) {
                return;
            }
        }
    }

}