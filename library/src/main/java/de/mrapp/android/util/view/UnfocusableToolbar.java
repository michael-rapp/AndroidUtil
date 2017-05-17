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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * By default, the AppCompat support library's {@link Toolbar} does consume all touch events,
 * regardless of whether the "focusable" attribute is set, or not. This class extend the class
 * {@link Toolbar} to enable touch events to be passed to other views in the event processing chain,
 * if the "focusable" attribute is set to false. If it is set to true, the toolbar behaves exactly
 * like the original one.
 *
 * @author Michael Rapp
 * @since 1.16.0
 */
public class UnfocusableToolbar extends Toolbar {

    /**
     * Creates a toolbar, which enables touch events to be passed to other views in the event
     * processing chain, if the "focusable" attribute is set to false.
     *
     * @param context
     *         The context, which should be used by the image view, as an instance of the class
     *         {@link Context}. The context may not be null
     */
    public UnfocusableToolbar(@NonNull final Context context) {
        super(context);
    }

    /**
     * Creates a toolbar, which enables touch events to be passed to other views in the event
     * processing chain, if the "focusable" attribute is set to false.
     *
     * @param context
     *         The context, which should be used by the image view, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param attributeSet
     *         The attributes of the XML tag that is inflating the view, as an instance of the type
     *         {@link AttributeSet} or null, if no attributes are available
     */
    public UnfocusableToolbar(@NonNull final Context context,
                              @Nullable final AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * Creates a toolbar, which enables touch events to be passed to other views in the event
     * processing chain, if the "focusable" attribute is set to false.
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
    public UnfocusableToolbar(@NonNull final Context context,
                              @Nullable final AttributeSet attributeSet,
                              @StyleRes final int defaultStyle) {
        super(context, attributeSet, defaultStyle);
    }

    @Override
    public final boolean onTouchEvent(final MotionEvent event) {
        boolean handled = super.onTouchEvent(event);
        return isFocusable() && handled;
    }

}