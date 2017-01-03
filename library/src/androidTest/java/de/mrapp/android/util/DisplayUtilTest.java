/*
 * Copyright 2015 - 2017 Michael Rapp
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

import android.os.Build;
import android.test.AndroidTestCase;
import android.util.DisplayMetrics;

import junit.framework.Assert;

import de.mrapp.android.util.DisplayUtil.DeviceType;

/**
 * Tests the functionality of the class {@link DisplayUtil}.
 *
 * @author Michael Rapp
 */
public class DisplayUtilTest extends AndroidTestCase {

    /**
     * Tests the functionality of the getValue-method of the enum {@link DeviceType}.
     */
    public final void testDeviceTypeGetValue() {
        assertEquals("phone", DeviceType.PHONE.getValue());
        assertEquals("phablet", DeviceType.PHABLET.getValue());
        assertEquals("tablet", DeviceType.TABLET.getValue());
    }

    /**
     * Tests the functionality of the fromValue-method of the enum {@link DeviceType}.
     */
    public final void testFromValue() {
        assertEquals(DeviceType.PHONE, DeviceType.fromValue("phone"));
        assertEquals(DeviceType.PHABLET, DeviceType.fromValue("phablet"));
        assertEquals(DeviceType.TABLET, DeviceType.fromValue("tablet"));
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the fromValue-method of the
     * enum {@link DeviceType}, if the given value is invalid.
     */
    public final void testFromValueThrowsException() {
        try {
            DeviceType.fromValue("foo");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }

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

            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to convert an {@link Long} value, which
     * is measured in pixels, into a value, which is measured in dp.
     */
    public final void testPixelsToDpWithLongParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            long pixels = 5L;
            long dp = DisplayUtil.pixelsToDp(getContext(), pixels);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(
                    Math.round(pixels / (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO)),
                    dp);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * an {@link Long} value, which is measured in pixels, into a value, which is measured in dp, if
     * the context is null.
     */
    public final void testPixelsToDpWithLongParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.pixelsToDp(null, 0L);
            } catch (NullPointerException e) {

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

            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to convert a {@link Double} value, which
     * is measured in pixels, into a value, which is measured in dp.
     */
    public final void testPixelsToDpWithDoubleParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            double pixels = 5.5d;
            double dp = DisplayUtil.pixelsToDp(getContext(), pixels);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(pixels / (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO), dp);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * a {@link Double} value, which is measured in pixels, into a value, which is measured in dp,
     * if the context is null.
     */
    public final void testPixelsToDpWithDoubleParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.pixelsToDp(null, 0d);
            } catch (NullPointerException e) {

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

            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to convert an {@link Long} value, which
     * is measured in dp, into a value, which is measured in pixels.
     */
    public final void testDpToPixelsWithLongParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            long dp = 5L;
            long pixels = DisplayUtil.dpToPixels(getContext(), dp);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(Math.round(dp * (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO)),
                    pixels);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * an {@link Long} value, which is measured in dp into a value, which is measured in pixels, if
     * the context is null.
     */
    public final void testDpToPixelsWithLongParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.dpToPixels(null, 0L);
            } catch (NullPointerException e) {

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

            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to convert a {@link Double} value, which
     * is measured in dp, into a value, which is measured in pixels.
     */
    public final void testDpToPixelsWithDoubleParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            double dp = 5.5d;
            double pixels = DisplayUtil.dpToPixels(getContext(), dp);
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            assertEquals(dp * (displayMetrics.densityDpi / DisplayUtil.PIXEL_DP_RATIO), pixels);
        }
    }

    /**
     * Ensures that a {@link NullPointerException} is thrown by the method, which allows to convert
     * a {@link Double} value, which is measured in dp into a value, which is measured in pixels, if
     * the context is null.
     */
    public final void testDpToPixelsWithDoubleParameterThrowsException() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            try {
                DisplayUtil.dpToPixels(null, 0.0d);
            } catch (NullPointerException e) {

            }
        }
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the orientation of the
     * device.
     */
    public final void testGetOrientation() {
        assertNotNull(DisplayUtil.getOrientation(getContext()));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the type of the device,
     * depending on its screen size.
     */
    public final void testGetDeviceType() {
        String value = getContext().getString(R.string.device_type);
        DeviceType deviceType = DeviceType.fromValue(value);
        assertEquals(deviceType, DisplayUtil.getDeviceType(getContext()));
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the width of the device's
     * display.
     */
    public final void testGetDisplayWidth() {
        assertTrue(DisplayUtil.getDisplayWidth(getContext()) > 0);
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the height of the device's
     * display.
     */
    public final void testGetDisplayHeight() {
        assertTrue(DisplayUtil.getDisplayWidth(getContext()) > 0);
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the height of the status
     * bar.
     */
    public final void testGetStatusBarHeight() {
        assertTrue(DisplayUtil.getStatusBarHeight(getContext()) > 0);
    }

    /**
     * Tests the functionality of the method, which allows to retrieve the height of the navigation
     * bar.
     */
    public final void testGetNavigationBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            assertTrue(DisplayUtil.getNavigationBarHeight(getContext()) >= 0);
        }
    }

}