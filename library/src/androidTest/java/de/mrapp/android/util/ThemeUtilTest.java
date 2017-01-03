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

import android.content.res.Resources.NotFoundException;
import android.os.Build;
import android.test.AndroidTestCase;

import junit.framework.Assert;

/**
 * Tests the functionality of the class {@link ThemeUtil}.
 *
 * @author Michael Rapp
 */
public class ThemeUtilTest extends AndroidTestCase {

    /**
     * Tests the functionality of the method, which allows to obtain a color from a theme.
     */
    public final void testGetColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(ThemeUtil.getColor(getContext(), android.R.attr.editTextColor), 0);
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a color from a theme and
     * expects a theme's resource id as a parameter.
     */
    public final void testGetColorWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(ThemeUtil.getColor(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.editTextColor), 0);
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * color from a theme, if the given resource id is invalid.
     */
    public final void testGetColorThrowsException() {
        try {
            ThemeUtil.getColor(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a color state list from a
     * theme.
     */
    public final void testGetColorStateList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(
                    ThemeUtil.getColorStateList(getContext(), android.R.attr.textColorPrimary));
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a color state list from a theme
     * and expects a theme's resource id as a parameter.
     */
    public final void testGetColorStateListWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(ThemeUtil.getColorStateList(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.textColorPrimary));
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * color state list from a theme, if the given resource id is invalid.
     */
    public final void testGetColorStateListThrowsException() {
        try {
            ThemeUtil.getColorStateList(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a string from a theme.
     */
    public final void testGetString() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(
                    ThemeUtil.getString(getContext(), android.R.attr.candidatesTextStyleSpans));
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a string from a theme and
     * expects a theme's resource id as a parameter.
     */
    public final void testGetStringWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(ThemeUtil.getString(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.candidatesTextStyleSpans));
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * string from a theme, if the given resource id is invalid.
     */
    public final void testGetStringThrowsException() {
        try {
            ThemeUtil.getString(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a text from a theme.
     */
    public final void testGetText() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(ThemeUtil.getText(getContext(), android.R.attr.candidatesTextStyleSpans));
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a text from a theme and expects
     * a theme's resource id as a parameter.
     */
    public final void testGetTextWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(ThemeUtil.getText(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.candidatesTextStyleSpans));
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * text from a theme, if the given resource id is invalid.
     */
    public final void testGetTextThrowsException() {
        try {
            ThemeUtil.getText(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a dimension from a theme.
     */
    public final void testGetDimension() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(
                    ThemeUtil.getDimension(getContext(), android.R.attr.listPreferredItemHeight),
                    0);
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a dimension from a theme and
     * expects a theme's resource id as a parameter.
     */
    public final void testGetDimensionWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(ThemeUtil.getDimension(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.listPreferredItemHeight), 0);
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * dimension from a theme, if the given resource id is invalid.
     */
    public final void testGetDimensionThrowsException() {
        try {
            ThemeUtil.getDimension(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a dimension pixel size from a
     * theme.
     */
    public final void testGetDimensionPixelSize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(ThemeUtil.getDimensionPixelSize(getContext(),
                            android.R.attr.listPreferredItemHeight), 0);
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a dimension pixel size from a
     * theme and expects a theme's resource id as a parameter.
     */
    public final void testGetDimensionPixelSizeWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(ThemeUtil.getDimensionPixelSize(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.listPreferredItemHeight), 0);
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * dimension pixel size from a theme, if the given resource id is invalid.
     */
    public final void testGetDimensionPixelSizeThrowsException() {
        try {
            ThemeUtil.getDimensionPixelSize(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a dimension pixel offset from a
     * theme.
     */
    public final void testGetDimensionPixelOffset() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(ThemeUtil.getDimensionPixelOffset(getContext(),
                            android.R.attr.listPreferredItemHeight), 0);
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a dimension pixel offset from a
     * theme and expects a theme's resource id as a parameter.
     */
    public final void testGetDimensionPixelOffsetWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotSame(ThemeUtil
                    .getDimensionPixelOffset(getContext(), android.R.style.Theme_Holo,
                            android.R.attr.listPreferredItemHeight), 0);
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * dimension pixel offset from a theme, if the given resource id is invalid.
     */
    public final void testGetDimensionPixelOffsetThrowsException() {
        try {
            ThemeUtil.getDimensionPixelOffset(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a drawable from a theme.
     */
    public final void testGetDrawable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(
                    ThemeUtil.getDrawable(getContext(), android.R.attr.selectableItemBackground));
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a drawable from a theme and
     * expects a theme's resource id as a parameter.
     */
    public final void testGetDrawableWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertNotNull(ThemeUtil.getDrawable(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.selectableItemBackground));
        }
    }

    /**
     * Ensures, that a {@link NotFoundException} is thrown, by the method, which allows to obtain a
     * dimension pixel offset from a theme, if the given resource id is invalid.
     */
    public final void testGetDrawableThrowsException() {
        try {
            ThemeUtil.getDrawable(getContext(), android.R.attr.id);
            Assert.fail();
        } catch (NotFoundException e) {

        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a boolean value from a theme.
     */
    public final void testGetBoolean() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ThemeUtil.getBoolean(getContext(), android.R.attr.windowCloseOnTouchOutside);
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a boolean value from a theme
     * and expects a theme's resource id as a parameter.
     */
    public final void testGetBooleanWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ThemeUtil.getBoolean(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.windowCloseOnTouchOutside);
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a float value from a theme.
     */
    public final void testGetFloat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ThemeUtil.getFloat(getContext(), android.R.attr.backgroundDimAmount);
        }
    }

    /**
     * Tests the functionality of the method, which allows to obtain a float value from a theme and
     * expects a theme's resource id as a parameter.
     */
    public final void testGetFloatWithThemeResourceIdParameter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ThemeUtil.getFloat(getContext(), android.R.style.Theme_Holo,
                    android.R.attr.backgroundDimAmount);
        }
    }

}