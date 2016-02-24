/*
 * Copyright 2015 - 2016 Michael Rapp
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
package de.mrapp.android.util.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.test.AndroidTestCase;
import android.util.AttributeSet;
import android.util.Xml;
import android.widget.ImageView.ScaleType;

import junit.framework.Assert;

import org.xmlpull.v1.XmlPullParser;

import de.mrapp.android.util.ElevationUtil;
import de.mrapp.android.util.ElevationUtil.Orientation;
import de.mrapp.android.util.R;

import static de.mrapp.android.util.DisplayUtil.pixelsToDp;

/**
 * Tests the functionality of the class {@link ElevationShadowView}.
 *
 * @author Michael Rapp
 */
public class ElevationShadowViewTest extends AndroidTestCase {

    /**
     * Tests, if all properties are set correctly by the constructor, which expects a context as a
     * parameter.
     */
    public final void testConstructorWithContextParameter() {
        Context context = getContext();
        int elevation = pixelsToDp(context, context.getResources().getDimensionPixelSize(
                R.dimen.elevation_shadow_view_shadow_elevation_default_value));
        Orientation orientation = Orientation.fromValue(context.getResources()
                .getInteger(R.integer.elevation_shadow_view_shadow_orientation_default_value));
        boolean emulateParallelLight = context.getResources()
                .getBoolean(R.bool.elevation_shadow_view_emulate_parallel_light_default_value);
        ElevationShadowView elevationShadowView = new ElevationShadowView(context);
        assertEquals(context, elevationShadowView.getContext());
        assertEquals(elevation, elevationShadowView.getShadowElevation());
        assertEquals(orientation, elevationShadowView.getShadowOrientation());
        assertEquals(emulateParallelLight, elevationShadowView.isParallelLightEmulated());
    }

    /**
     * Tests, if all properties are set correctly by the constructor, which expects a context and an
     * attribute set as parameters.
     */
    public final void testConstructorWithContextAndAttributeSetParameters() {
        Context context = getContext();
        int elevation = pixelsToDp(context, context.getResources().getDimensionPixelSize(
                R.dimen.elevation_shadow_view_shadow_elevation_default_value));
        Orientation orientation = Orientation.fromValue(context.getResources()
                .getInteger(R.integer.elevation_shadow_view_shadow_orientation_default_value));
        boolean emulateParallelLight = context.getResources()
                .getBoolean(R.bool.elevation_shadow_view_emulate_parallel_light_default_value);
        XmlPullParser xmlPullParser = context.getResources().getXml(R.xml.elevation_shadow_view);
        AttributeSet attributeSet = Xml.asAttributeSet(xmlPullParser);
        ElevationShadowView elevationShadowView = new ElevationShadowView(context, attributeSet);
        assertEquals(context, elevationShadowView.getContext());
        assertEquals(elevation, elevationShadowView.getShadowElevation());
        assertEquals(orientation, elevationShadowView.getShadowOrientation());
        assertEquals(emulateParallelLight, elevationShadowView.isParallelLightEmulated());
    }

    /**
     * Tests, if all properties are set correctly by the constructor, which expects a context, an
     * attribute set and a default style as parameters.
     */
    public final void testConstructorWithContextAttributeSetAndDefaultStyleParameters() {
        Context context = getContext();
        int defaultStyle = 0;
        int elevation = pixelsToDp(context, context.getResources().getDimensionPixelSize(
                R.dimen.elevation_shadow_view_shadow_elevation_default_value));
        Orientation orientation = Orientation.fromValue(context.getResources()
                .getInteger(R.integer.elevation_shadow_view_shadow_orientation_default_value));
        boolean emulateParallelLight = context.getResources()
                .getBoolean(R.bool.elevation_shadow_view_emulate_parallel_light_default_value);
        XmlPullParser xmlPullParser = context.getResources().getXml(R.xml.elevation_shadow_view);
        AttributeSet attributeSet = Xml.asAttributeSet(xmlPullParser);
        ElevationShadowView elevationShadowView =
                new ElevationShadowView(context, attributeSet, defaultStyle);
        assertEquals(context, elevationShadowView.getContext());
        assertEquals(elevation, elevationShadowView.getShadowElevation());
        assertEquals(orientation, elevationShadowView.getShadowOrientation());
        assertEquals(emulateParallelLight, elevationShadowView.isParallelLightEmulated());
    }

    /**
     * Tests, if all properties are set correctly by the constructor, which expects a context, an
     * attribute set, a default style and a default style attribute as parameters.
     */
    public final void testConstructorWithContextAttributeSetAndDefaultStyleAndDefaultStyleAttributeParameters() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = getContext();
            int defaultStyle = 0;
            int defaultStyleAttribute = 0;
            int elevation = pixelsToDp(context, context.getResources().getDimensionPixelSize(
                    R.dimen.elevation_shadow_view_shadow_elevation_default_value));
            Orientation orientation = Orientation.fromValue(context.getResources()
                    .getInteger(R.integer.elevation_shadow_view_shadow_orientation_default_value));
            boolean emulateParallelLight = context.getResources()
                    .getBoolean(R.bool.elevation_shadow_view_emulate_parallel_light_default_value);
            XmlPullParser xmlPullParser =
                    context.getResources().getXml(R.xml.elevation_shadow_view);
            AttributeSet attributeSet = Xml.asAttributeSet(xmlPullParser);
            ElevationShadowView elevationShadowView =
                    new ElevationShadowView(context, attributeSet, defaultStyle,
                            defaultStyleAttribute);
            assertEquals(context, elevationShadowView.getContext());
            assertEquals(elevation, elevationShadowView.getShadowElevation());
            assertEquals(orientation, elevationShadowView.getShadowOrientation());
            assertEquals(emulateParallelLight, elevationShadowView.isParallelLightEmulated());
        }
    }

    /**
     * Tests the functionality of the method, which allows to set the elevation of the shadow, which
     * should be visualized by the view.
     */
    public final void testSetShadowElevation() {
        int elevation = 2;
        ElevationShadowView elevationShadowView = new ElevationShadowView(getContext());
        elevationShadowView.setShadowElevation(elevation);
        assertEquals(elevation, elevationShadowView.getShadowElevation());
        assertEquals(ScaleType.FIT_XY, elevationShadowView.getScaleType());
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the elevation of the shadow, which should be visualized by the view, if the elevation is
     * less than 0.
     */
    public final void testSetShadowElevationThrowsExceptionWhenElevationIsLessThanZero() {
        try {
            ElevationShadowView elevationShadowView = new ElevationShadowView(getContext());
            elevationShadowView.setShadowElevation(-1);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the elevation of the shadow, which should be visualized by the view, if the elevation is
     * greater than the maximum.
     */
    public final void testSetShadowElevationThrowsExceptionWhenElevationIsGreaterThanMaximum() {
        try {
            ElevationShadowView elevationShadowView = new ElevationShadowView(getContext());
            elevationShadowView.setShadowElevation(ElevationUtil.MAX_ELEVATION + 1);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Tests the functionality of the method, which allows to set the orientation of the shadow,
     * which should be visualized by the view.
     */
    public final void testSetShadowOrientation() {
        Orientation orientation = Orientation.TOP_RIGHT;
        ElevationShadowView elevationShadowView = new ElevationShadowView(getContext());
        elevationShadowView.setShadowOrientation(orientation);
        assertEquals(orientation, elevationShadowView.getShadowOrientation());
        assertEquals(ScaleType.FIT_CENTER, elevationShadowView.getScaleType());
    }

    /**
     * Ensures, that a {@link NullPointerException} is thrown by the method, which allows to set the
     * orientation of the shadow, which should be visualized by the view, if the orientation is
     * null.
     */
    public final void testSetShadowOrientationThrowsException() {
        try {
            ElevationShadowView elevationShadowView = new ElevationShadowView(getContext());
            elevationShadowView.setShadowOrientation(null);
            Assert.fail();
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Tests the functionality of the method, which allows to set, whether parallel light should be
     * emulated, or not.
     */
    public final void testEmulateParallelLight() {
        boolean emulateParallelLight = true;
        ElevationShadowView elevationShadowView = new ElevationShadowView(getContext());
        elevationShadowView.emulateParallelLight(emulateParallelLight);
        assertEquals(emulateParallelLight, elevationShadowView.isParallelLightEmulated());
    }

}