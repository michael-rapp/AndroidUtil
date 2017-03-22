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
package de.mrapp.android.util.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * A custom view, which is extended from the view {@link android.widget.ImageView} in order to
 * ensure, that its height is always equal to its width.
 *
 * @author Michael Rapp
 * @since 1.4.6
 */
public class SquareImageView extends AppCompatImageView {

    /**
     * Creates a new square image view.
     *
     * @param context
     *         The context, which should be used by the image view, as an instance of the class
     *         {@link Context}. The context may not be null
     */
    public SquareImageView(@NonNull final Context context) {
        super(context);
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
        super(context, attributeSet);
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
    }

    @Override
    protected final void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

}