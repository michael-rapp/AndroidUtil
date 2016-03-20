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
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import de.mrapp.android.util.R;

/**
 * A layout, which allows to show a drawable in the insets, which are passed to its {@link
 * #fitSystemWindows(Rect)} method. This area usually corresponds to the device's status or
 * navigation bar.
 *
 * @author Michael Rapp
 * @since 1.4.6
 */
public class ScrimInsetsLayout extends FrameLayout {

    /**
     * Defines the interface, a class, which should be notified, when the insets, which are passed
     * to the layout's {@link #fitSystemWindows(Rect)} method, have been changed, must implement.
     */
    public interface Callback {

        /**
         * The method, which is invoked, when the insets have been changed.
         *
         * @param insets
         *         The new insets as an instance of the class {@link Rect}. The insets may not be
         *         null
         */
        void onInsetsChanged(@NonNull Rect insets);

    }

    /**
     * The drawable, which is shown in the layout's insets.
     */
    private Drawable insetForeground;

    /**
     * The layout's current insets.
     */
    private Rect insets;

    /**
     * The callback, which is notified, when the layout's insets have been changed.
     */
    private Callback callback;

    /**
     * Initializes the view.
     *
     * @param attributeSet
     *         The attribute set, the attributes should be obtained from, as an instance of the type
     *         {@link AttributeSet}, or null, if no attributes should be obtained
     */
    private void initialize(@Nullable final AttributeSet attributeSet) {
        this.insets = null;
        this.callback = null;
        setWillNotDraw(true);
        obtainStyledAttributes(attributeSet);
    }

    /**
     * Obtains the view's attributes from a specific attribute set.
     *
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     */
    private void obtainStyledAttributes(@Nullable final AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = getContext()
                    .obtainStyledAttributes(attributeSet, R.styleable.ScrimInsetsLayout);

            try {
                obtainInsetForeground(typedArray);
            } finally {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the drawable, which should be shown in the layout's insets, from a specific typed
     * array.
     *
     * @param typedArray
     *         The typed array, the drawable should be obtained from, as an instance of the class
     *         {@link TypedArray}. The typed array may not be null
     */
    private void obtainInsetForeground(@NonNull final TypedArray typedArray) {
        int color = typedArray.getColor(R.styleable.ScrimInsetsLayout_insetForeground, -1);

        if (color == -1) {
            Drawable drawable =
                    typedArray.getDrawable(R.styleable.ScrimInsetsLayout_insetForeground);

            if (drawable != null) {
                setInsetForeground(drawable);
            } else {
                setInsetForegroundColor(ContextCompat.getColor(getContext(),
                        R.color.scrim_insets_layout_insets_foreground_default_value));
            }
        } else {
            setInsetForegroundColor(color);
        }
    }

    /**
     * Notifies the callback, that the layout's insets have been changed.
     *
     * @param insets
     *         The new insets as an instance of the class {@link Rect}
     */
    private void notifyOnInsetsChanged(@NonNull final Rect insets) {
        if (callback != null) {
            callback.onInsetsChanged(insets);
        }
    }

    /**
     * Creates a new layout, which allows to show a drawable in the insets, which are passed to its
     * {@link #fitSystemWindows(Rect)} method.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     */
    public ScrimInsetsLayout(@NonNull final Context context) {
        super(context);
        initialize(null);
    }

    /**
     * Creates a new layout, which allows to show a drawable in the insets, which are passed to its
     * {@link #fitSystemWindows(Rect)} method.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     */
    public ScrimInsetsLayout(@NonNull final Context context,
                             @Nullable final AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(attributeSet);
    }

    /**
     * Creates a new layout, which allows to show a drawable in the insets, which are passed to its
     * {@link #fitSystemWindows(Rect)} method.
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
    public ScrimInsetsLayout(@NonNull final Context context,
                             @Nullable final AttributeSet attributeSet,
                             @StyleRes final int defaultStyle) {
        super(context, attributeSet, defaultStyle);
        initialize(attributeSet);
    }

    /**
     * Creates a new layout, which allows to show a drawable in the insets, which are passed to its
     * {@link #fitSystemWindows(Rect)} method.
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
    public ScrimInsetsLayout(@NonNull final Context context,
                             @Nullable final AttributeSet attributeSet,
                             @StyleRes final int defaultStyle,
                             @StyleRes final int defaultStyleResource) {
        super(context, attributeSet, defaultStyle);
        initialize(attributeSet);
    }

    /**
     * Sets the callback, which should be notified, when the layout's insets have been changed.
     *
     * @param callback
     *         The callback, which should be set, as an instance of the type {@link Callback}, or
     *         null, if no callback should be notified
     */
    public final void setCallback(@Nullable final Callback callback) {
        this.callback = callback;
    }

    /**
     * Returns the drawable, which is shown in the layout's insets.
     *
     * @return The drawable, which is shown in the layout's insets, as an instance of the class
     * {@link Drawable}, or null, if no drawable is shown
     */
    public final Drawable getInsetForeground() {
        return insetForeground;
    }

    /**
     * Sets the drawable, which should be shown in the layout's insets.
     *
     * @param insetForeground
     *         The drawable, which should be set, as an instance of the class {@link Drawable}, or
     *         null, if no drawable should be set
     */
    public final void setInsetForeground(@Nullable final Drawable insetForeground) {
        this.insetForeground = insetForeground;
        invalidate();
    }

    /**
     * Sets the color, which should be shown in the layout's insets.
     *
     * @param color
     *         The color, which should be set, as an {@link Integer} value
     */
    public final void setInsetForegroundColor(@ColorInt final int color) {
        setInsetForeground(new ColorDrawable(color));
    }

    /**
     * Returns the layout's current insets.
     *
     * @return The layout's current insets or null, if the layout has not been shown yet
     */
    public final Rect getInsets() {
        return insets;
    }

    @Override
    public final void draw(final Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();

        if (insets != null && insetForeground != null) {
            int saveCount = canvas.save();
            canvas.translate(getScrollX(), getScrollY());
            insetForeground.setBounds(0, 0, width, insets.top);
            insetForeground.draw(canvas);
            insetForeground.setBounds(0, height - insets.bottom, width, height);
            insetForeground.draw(canvas);
            insetForeground.setBounds(0, insets.top, insets.left, height - insets.bottom);
            insetForeground.draw(canvas);
            insetForeground
                    .setBounds(width - insets.right, insets.top, width, height - insets.bottom);
            insetForeground.draw(canvas);
            canvas.restoreToCount(saveCount);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected boolean fitSystemWindows(final Rect insets) {
        this.insets = new Rect(insets);
        setWillNotDraw(insetForeground == null);
        ViewCompat.postInvalidateOnAnimation(this);
        notifyOnInsetsChanged(insets);
        return true;
    }

    @Override
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (insetForeground != null) {
            insetForeground.setCallback(this);
        }
    }

    @Override
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (insetForeground != null) {
            insetForeground.setCallback(null);
        }
    }

}