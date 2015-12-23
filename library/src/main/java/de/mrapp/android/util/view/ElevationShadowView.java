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
package de.mrapp.android.util.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import de.mrapp.android.util.ElevationUtil;
import de.mrapp.android.util.ElevationUtil.Orientation;
import de.mrapp.android.util.R;

import static de.mrapp.android.util.Condition.ensureAtLeast;
import static de.mrapp.android.util.Condition.ensureAtMaximum;
import static de.mrapp.android.util.Condition.ensureNotNull;
import static de.mrapp.android.util.DisplayUtil.pixelsToDp;
import static de.mrapp.android.util.ElevationUtil.createElevationShadow;

/**
 * A view, which can be used to visualize the shadow of an elevation on pre-Lollipop devices.
 *
 * @author Michael Rapp
 * @since 1.2.2
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class ElevationShadowView extends ImageView {

    /**
     * The elevation of the shadow, which is visualized by the view, in dp.
     */
    private int elevation;

    /**
     * The orientation of the shadow, which is visualized by the view.
     */
    private Orientation orientation;

    /**
     * True, if parallel light should be emulated, false otherwise.
     */
    private boolean emulateParallelLight;

    /**
     * Initializes the view.
     *
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet}, or null, if no attributes should be obtained
     */
    private void initialize(@Nullable final AttributeSet attributeSet) {
        obtainStyledAttributes(attributeSet);
        adaptElevationShadow();
    }

    /**
     * Obtains the view's attributes from a specific attribute set.
     *
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     */
    private void obtainStyledAttributes(@Nullable final AttributeSet attributeSet) {
        TypedArray typedArray =
                getContext().obtainStyledAttributes(attributeSet, R.styleable.ElevationShadowView);

        try {
            obtainShadowElevation(typedArray);
            obtainShadowOrientation(typedArray);
            obtainEmulateParallelLight(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Obtains the elevation of the shadow, which is visualized by the view, from a specific typed
     * array.
     *
     * @param typedArray
     *         The typed array, the elevation should be obtained from, as an instance of the class
     *         {@link TypedArray}. The typed array may not be null
     */
    private void obtainShadowElevation(@NonNull final TypedArray typedArray) {
        int defaultValue = getResources().getDimensionPixelSize(
                R.dimen.elevation_shadow_view_shadow_elevation_default_value);
        elevation = pixelsToDp(getContext(), typedArray
                .getDimensionPixelSize(R.styleable.ElevationShadowView_shadowElevation,
                        defaultValue));
    }

    /**
     * Obtains the orientation of the shadow, which is visualized by the view, from a specific typed
     * array.
     *
     * @param typedArray
     *         The typed array, the elevation should be obtained from, as an instance of the class
     *         {@link TypedArray}. The typed array may not be null
     */
    private void obtainShadowOrientation(@NonNull final TypedArray typedArray) {
        int defaultValue = getResources()
                .getInteger(R.integer.elevation_shadow_view_shadow_orientation_default_value);
        orientation = Orientation.fromValue(typedArray
                .getInteger(R.styleable.ElevationShadowView_shadowOrientation, defaultValue));
    }

    /**
     * Obtains the boolean value, which specifies whether parallel light should be emulated, from a
     * specific typed array.
     *
     * @param typedArray
     *         The typed array, the boolean value should be obtained from, as an instance of the
     *         class {@link TypedArray}
     */
    private void obtainEmulateParallelLight(@NonNull final TypedArray typedArray) {
        boolean defaultValue = getResources()
                .getBoolean(R.bool.elevation_shadow_view_emulate_parallel_light_default_value);
        emulateParallelLight = typedArray
                .getBoolean(R.styleable.ElevationShadowView_emulateParallelLight, defaultValue);
    }

    /**
     * Adapts the shadow, which is visualized by the view, depending on its current attributes.
     */
    private void adaptElevationShadow() {
        setImageBitmap(
                createElevationShadow(getContext(), elevation, orientation, emulateParallelLight));
        setScaleType(orientation == Orientation.LEFT || orientation == Orientation.TOP ||
                orientation == Orientation.RIGHT || orientation == Orientation.BOTTOM ?
                ScaleType.FIT_XY : ScaleType.FIT_CENTER);
    }

    /**
     * Creates a new view, which can be used to visualize the shadow of an elevation on pre-Lollipop
     * devices.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     */
    public ElevationShadowView(@NonNull final Context context) {
        this(context, null);
    }

    /**
     * Creates a new view, which can be used to visualize the shadow of an elevation on pre-Lollipop
     * devices.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     */
    public ElevationShadowView(@NonNull final Context context,
                               @Nullable final AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet);
    }

    /**
     * Creates a new view, which can be used to visualize the shadow of an elevation on pre-Lollipop
     * devices.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     * @param defaultStyle
     *         The default style to apply to this preference. If 0, no style will be applied (beyond
     *         what is included in the theme). This may either be an attribute resource, whose value
     *         will be retrieved from the current theme, or an explicit style resource
     */
    public ElevationShadowView(@NonNull final Context context,
                               @Nullable final AttributeSet attributeSet, final int defaultStyle) {
        super(context, attributeSet, defaultStyle);
        initialize(attributeSet);
    }

    /**
     * Creates a new view, which can be used to visualize the shadow of an elevation on pre-Lollipop
     * devices.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     * @param defaultStyle
     *         The default style to apply to this preference. If 0, no style will be applied (beyond
     *         what is included in the theme). This may either be an attribute resource, whose value
     *         will be retrieved from the current theme, or an explicit style resource
     * @param defaultStyleResource
     *         A resource identifier of a style resource that supplies default values for the
     *         preference, used only if the default style is 0 or can not be found in the theme. Can
     *         be 0 to not look for defaults
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ElevationShadowView(@NonNull final Context context,
                               @Nullable final AttributeSet attributeSet, final int defaultStyle,
                               final int defaultStyleResource) {
        super(context, attributeSet, defaultStyle, defaultStyleResource);
        initialize(attributeSet);
    }

    /**
     * Returns the elevation of the shadow, which is visualized by the view.
     *
     * @return The elevation of the shadow, which is visualized by the view, in dp as an {@link
     * Integer} value
     */
    public final int getShadowElevation() {
        return elevation;
    }

    /**
     * Sets the elevation of the shadow, which should be visualized by the view.
     *
     * @param elevation
     *         The elevation of the shadow, which should be set, in dp as an {@link Integer} value.
     *         The elevation must be at least 0 and at maximum 16
     */
    public final void setShadowElevation(final int elevation) {
        ensureAtLeast(elevation, 0, "The elevation must be at least 0");
        ensureAtMaximum(elevation, ElevationUtil.MAX_ELEVATION,
                "The elevation must be at maximum " + ElevationUtil.MAX_ELEVATION);
        this.elevation = elevation;
        adaptElevationShadow();
    }

    /**
     * Returns the orientation of the shadow, which is visualized by the view.
     *
     * @return The orientation of the shadow, which is visualized by the view, as a value of the
     * enum {@link Orientation}. The orientation may either be <code>LEFT</code>,
     * <code>RIGHT</code>, <code>TOP</code>, <code>BOTTOM</code>, <code>TOP_LEFT</code>,
     * <code>TOP_RIGHT</code>, <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     */
    public final Orientation getShadowOrientation() {
        return orientation;
    }

    /**
     * Sets the orientation of the shadow, which should be visualized by the view.
     *
     * @param orientation
     *         The orientation, which should be set, as a value of the enum {@link Orientation}. The
     *         orientation may either be <code>LEFT</code>, <code>RIGHT</code>, <code>TOP</code>,
     *         <code>BOTTOM</code>, <code>TOP_LEFT</code>, <code>TOP_RIGHT</code>,
     *         <code>BOTTOM_LEFT</code> or <code>BOTTOM_RIGHT</code>
     */
    public final void setShadowOrientation(@NonNull final Orientation orientation) {
        ensureNotNull(orientation, "The orientation may not be null");
        this.orientation = orientation;
        adaptElevationShadow();
    }

    /**
     * Returns, whether parallel light is emulated, or not.
     *
     * @return True, if parallel light is emulated, false otherwise
     */
    public final boolean isParallelLightEmulated() {
        return emulateParallelLight;
    }

    /**
     * Sets, whether parallel light should be emulated, or not.
     *
     * @param emulateParallelLight
     *         True, if parallel light should be emulated, false otherwise
     */
    public final void emulateParallelLight(final boolean emulateParallelLight) {
        this.emulateParallelLight = emulateParallelLight;
        adaptElevationShadow();
    }

}