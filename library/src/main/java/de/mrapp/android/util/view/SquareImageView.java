/*
 * Copyright 2015 - 2018 Michael Rapp
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

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import de.mrapp.android.util.R;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * A custom view, which is extended from the view {@link android.widget.ImageView} in order to
 * ensure, that its height is always equal to its width (or vice versa).
 *
 * @author Michael Rapp
 * @since 1.4.6
 */
public class SquareImageView extends AppCompatImageView {

    /**
     * Contains all possible edges of a {@link SquareImageView}.
     */
    public enum Edge {

        /**
         * The horizontal edge (on the x-axis).
         */
        HORIZONTAL(0),

        /**
         * The vertical edge (on the y-axis).
         */
        VERTICAL(1);

        /**
         * The value of the edge.
         */
        private final int value;

        /**
         * Creates a new edge.
         *
         * @param value
         *         The value of the edge
         */
        Edge(final int value) {
            this.value = value;
        }

        /**
         * Returns the value of the edge.
         *
         * @return The value of the edge as an {@link Integer} value
         */
        public final int getValue() {
            return value;
        }

        /**
         * Returns the edge, which corresponds to a specific value. If the given value is invalid,
         * an {@link IllegalArgumentException} will be thrown.
         *
         * @param value
         *         The value of the edge, which should be returned, as an {@link Integer} value
         * @return The edge, which corresponds to the given value, as a value of the enum {@link
         * Edge}
         */
        @NonNull
        public static Edge fromValue(final int value) {
            for (Edge edge : values()) {
                if (edge.getValue() == value) {
                    return edge;
                }
            }

            throw new IllegalArgumentException("Invalid enum value: " + value);
        }

    }

    /**
     * The edge, which is scaled in order to ensure, that width and height are equal.
     */
    private Edge scaledEdge;

    /**
     * Initializes the view.
     *
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet}, or null, if no attributes should be obtained
     * @param defaultStyleResource
     *         A resource identifier of a style resource that supplies default values for the view,
     *         used only if the default style is 0 or can not be found in the theme. Can be 0 to not
     *         look for defaults
     */
    private void initialize(@Nullable final AttributeSet attributeSet,
                            @StyleRes final int defaultStyleResource) {
        obtainStyledAttributes(attributeSet, defaultStyleResource);
    }

    /**
     * Obtains the view's attributes from a specific attribute set.
     *
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     * @param defaultStyleResource
     *         A resource identifier of a style resource that supplies default values for the view,
     *         used only if the default style is 0 or can not be found in the theme. Can be 0 to not
     *         look for defaults
     */
    private void obtainStyledAttributes(@Nullable final AttributeSet attributeSet,
                                        @StyleRes final int defaultStyleResource) {
        TypedArray typedArray = getContext()
                .obtainStyledAttributes(attributeSet, R.styleable.SquareImageView, 0,
                        defaultStyleResource);

        try {
            obtainScaledEdge(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Obtains the scaled edge from a specific typed array.
     *
     * @param typedArray
     *         The typed array, the scaled edge should be obtained from, as an instance of the class
     *         {@link TypedArray}. The typed array may not be null
     */
    private void obtainScaledEdge(@NonNull final TypedArray typedArray) {
        int defaultValue = Edge.VERTICAL.getValue();
        Edge scaledEdge = Edge.fromValue(
                typedArray.getInt(R.styleable.SquareImageView_scaledEdge, defaultValue));
        setScaledEdge(scaledEdge);
    }

    /**
     * Creates a new square image view.
     *
     * @param context
     *         The context, which should be used by the image view, as an instance of the class
     *         {@link Context}. The context may not be null
     */
    public SquareImageView(@NonNull final Context context) {
        this(context, null);
    }

    /**
     * Creates a new square image view.
     *
     * @param context
     *         The context, which should be used by the image view, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param attributeSet
     *         The attributes of the XML tag that is inflating the view, as an instance of the type
     *         {@link AttributeSet} or null, if no attributes are available
     */
    public SquareImageView(@NonNull final Context context,
                           @Nullable final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /**
     * Creates a new square image view.
     *
     * @param context
     *         The context, which should be used by the image view, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param attributeSet
     *         The attributes of the XML tag that is inflating the view, as an instance of the type
     *         {@link AttributeSet} or null, if no attributes are available
     * @param defaultStyle
     *         The default style to apply to this view. If 0, no style will be applied (beyond what
     *         is included in the theme). This may either be an attribute resource, whose value will
     *         be retrieved from the current theme, or an explicit style resource
     */
    public SquareImageView(@NonNull final Context context,
                           @Nullable final AttributeSet attributeSet,
                           @StyleRes final int defaultStyle) {
        super(context, attributeSet, defaultStyle);
        initialize(attributeSet, defaultStyle);
    }

    /**
     * Returns the edge of the image view, which is scaled in order to ensure, that width and height
     * are equal. E.g., if the scaled edge is {@link Edge#VERTICAL}, the height of the image view is
     * adapted to fit its width.
     *
     * @return The edge of the image view, which is scaled in order to ensure, that width and height
     * are equal, as a value of the enum {@link Edge}. The edge may either be {@link
     * Edge#HORIZONTAL} or {@link Edge#VERTICAL}
     */
    public Edge getScaledEdge() {
        return scaledEdge;
    }

    /**
     * Sets the edge of the image view, which should be scaled in order to ensure, that width and
     * height are equal. E.g., if the scaled edge is {@link Edge#VERTICAL}, the height of the image
     * view is adapted to fit its width.
     *
     * @param edge
     *         The edge, which should be set, as a value of the enum {@link Edge}. The edge may
     *         either be {@link Edge#HORIZONTAL} or {@link Edge#VERTICAL}
     */
    public final void setScaledEdge(final Edge edge) {
        ensureNotNull(edge, "The edge may not be null");
        this.scaledEdge = edge;
        requestLayout();
    }

    @Override
    protected final void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getScaledEdge() == Edge.VERTICAL) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        } else {
            setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
        }
    }

}