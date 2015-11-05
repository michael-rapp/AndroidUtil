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

import android.graphics.Bitmap;
import android.test.AndroidTestCase;

import de.mrapp.android.util.ElevationUtil.Orientation;

/**
 * Tests the functionality of the class {@link ElevationUtil}.
 *
 * @author Michael Rapp
 */
public class ElevationUtilTest extends AndroidTestCase {

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the elevation is 0 and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithZeroElevationAndNonParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 0, Orientation.LEFT);
        assertNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>LEFT</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithLeftOrientationAndNonParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.LEFT);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>TOP_LEFT</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithTopLeftOrientationAndNonParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.TOP_LEFT);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>TOP</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithTopOrientationAndNonParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.TOP);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>TOP_RIGHT</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithTopRightOrientationAndNonParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.TOP_RIGHT);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>RIGHT</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithRightOrientationAndNonParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.RIGHT);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>BOTTOM_RIGHT</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithBottomRightOrientationAndNonParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.BOTTOM_RIGHT);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>BOTTOM</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithBottomOrientationAndNonParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.BOTTOM);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>BOTTOM_LEFT</code> and no parallel light is emulated.
     */
    public final void testCreateElevationShadowWithBottomLeftOrientationAndNonParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.BOTTOM_LEFT);
        assertNotNull(shadow);
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view without emulating parallel light, if the context is null.
     */
    public final void testCreateElevationShadowWithNonParallelLightThrowsExceptionWhenContextIsNull() {
        try {
            ElevationUtil.createElevationShadow(null, 1, Orientation.BOTTOM);
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link IllegalArgumentException} is thrown by the method, which allows to
     * create the shadow of an elevated view without emulating parallel light, if the elevation is
     * less than 1.
     */
    public final void testCreateElevationShadowWithNonParallelLightThrowsExceptionWhenElevationIsLessThan0() {
        try {
            ElevationUtil.createElevationShadow(getContext(), -1, Orientation.BOTTOM);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link IllegalArgumentException} is thrown by the method, which allows to
     * create the shadow of an elevated view without emulating parallel light, if the elevation is
     * greater than the constant <code>MAX_ELEVATION</code>.
     */
    public final void testCreateElevationShadowWithNonParallelLightThrowsExceptionWhenElevationIsGreaterThanMaxElevation() {
        try {
            ElevationUtil.createElevationShadow(getContext(), ElevationUtil.MAX_ELEVATION + 1,
                    Orientation.BOTTOM);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view without emulating parallel light, if the orientation is null.
     */
    public final void testCreateElevationShadowWithNonParallelLightThrowsExceptionWhenOrientationIsNull() {
        try {
            ElevationUtil.createElevationShadow(getContext(), 1, null);
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the elevation is 0 and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithZeroElevationAndParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 0, Orientation.LEFT, true);
        assertNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>LEFT</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithLeftOrientationAndParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.LEFT, true);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>TOP_LEFT</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithTopLeftOrientationAndParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.TOP_LEFT, true);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>TOP</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithTopOrientationAndParallelLight() {
        Bitmap shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.TOP, true);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>TOP_RIGHT</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithTopRightOrientationAndParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.TOP_RIGHT, true);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>RIGHT</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithRightOrientationAndParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.RIGHT, true);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>BOTTOM_RIGHT</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithBottomRightOrientationAndParallelLight() {
        Bitmap shadow = ElevationUtil
                .createElevationShadow(getContext(), 1, Orientation.BOTTOM_RIGHT, true);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>BOTTOM</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithBottomOrientationAndParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.BOTTOM, true);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>BOTTOM_LEFT</code> and parallel light is emulated.
     */
    public final void testCreateElevationShadowWithBottomLeftOrientationAndParallelLight() {
        Bitmap shadow =
                ElevationUtil.createElevationShadow(getContext(), 1, Orientation.BOTTOM_LEFT, true);
        assertNotNull(shadow);
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view with emulating parallel light, if the context is null.
     */
    public final void testCreateElevationShadowWithParallelLightThrowsExceptionWhenContextIsNull() {
        try {
            ElevationUtil.createElevationShadow(null, 1, Orientation.BOTTOM, true);
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link IllegalArgumentException} is thrown by the method, which allows to
     * create the shadow of an elevated view with emulating parallel light, if the elevation is less
     * than 1.
     */
    public final void testCreateElevationShadowWithParallelLightThrowsExceptionWhenElevationIsLessThan0() {
        try {
            ElevationUtil.createElevationShadow(getContext(), -1, Orientation.BOTTOM, true);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link IllegalArgumentException} is thrown by the method, which allows to
     * create the shadow of an elevated view with emulating parallel light, if the elevation is
     * greater than the constant <code>MAX_ELEVATION</code>.
     */
    public final void testCreateElevationShadowWithParallelLightThrowsExceptionWhenElevationIsGreaterThanMaxElevation() {
        try {
            ElevationUtil.createElevationShadow(getContext(), ElevationUtil.MAX_ELEVATION + 1,
                    Orientation.BOTTOM, true);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view with emulating parallel light, if the orientation is null.
     */
    public final void testCreateElevationShadowWithParallelLightThrowsExceptionWhenOrientationIsNull() {
        try {
            ElevationUtil.createElevationShadow(getContext(), 1, null, true);
        } catch (NullPointerException e) {
            return;
        }
    }

}