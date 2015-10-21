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

import android.graphics.drawable.Drawable;
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
     * if the orientation is <code>LEFT</code>.
     */
    public final void testCreateElevationShadowWithLeftOrientation() {
        Drawable shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.LEFT);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>RIGHT</code>.
     */
    public final void testCreateElevationShadowWithRightOrientation() {
        Drawable shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.RIGHT);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>BOTTOM</code>.
     */
    public final void testCreateElevationShadowWithBottomOrientation() {
        Drawable shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.BOTTOM);
        assertNotNull(shadow);
    }

    /**
     * Tests the functionality of the method, which allows to create the shadow of an elevated view,
     * if the orientation is <code>TOP</code>.
     */
    public final void testCreateElevationShadowWithTopOrientation() {
        Drawable shadow = ElevationUtil.createElevationShadow(getContext(), 1, Orientation.TOP);
        assertNotNull(shadow);
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view, if the context is null.
     */
    public final void testCreateElevationShadowThrowsExceptionWhenContextIsNull() {
        try {
            ElevationUtil.createElevationShadow(null, 1, Orientation.BOTTOM);
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view, if the elevation is less than 1.
     */
    public final void testCreateElevationShadowThrowsExceptionWhenElevationIsLessThan1() {
        try {
            ElevationUtil.createElevationShadow(getContext(), 0, Orientation.BOTTOM);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view, if the elevation is greater than 5.
     */
    public final void testCreateElevationShadowThrowsExceptionWhenElevationIsGreaterThan5() {
        try {
            ElevationUtil.createElevationShadow(getContext(), 6, Orientation.BOTTOM);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to create
     * the shadow of an elevated view, if the orientation is null.
     */
    public final void testCreateElevationShadowThrowsExceptionWhenOrientationIsNull() {
        try {
            ElevationUtil.createElevationShadow(getContext(), 1, null);
        } catch (NullPointerException e) {
            return;
        }
    }

}