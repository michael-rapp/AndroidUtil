/*
 * Copyright 2015 - 2019 Michael Rapp
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

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import de.mrapp.util.Condition;

/**
 * An abstract base class for all classes, which should allow to customize the appearance of adapter
 * views, while using the view holder pattern. It provides a <code>findViewById</code>-method, which
 * reuses previously referenced views by using a {@link ViewHolder}.
 *
 * @author Michael Rapp
 * @since 1.14.0
 */
public abstract class AbstractViewHolderAdapter {

    /**
     * The parent view of the view, whose appearance should currently be customized by the adapter.
     * This parent view is bound to a view holder.
     */
    private View currentParentView;

    /**
     * Sets the parent view, whose appearance should currently be customized by the decorator. This
     * method should never be called or overridden by any custom adapter implementation.
     *
     * @param currentParentView
     *         The parent view, which should be set, as an instance of the class {@link View}. The
     *         parent view may not be null
     */
    protected final void setCurrentParentView(@NonNull final View currentParentView) {
        Condition.INSTANCE.ensureNotNull(currentParentView, "The parent view may not be null");
        this.currentParentView = currentParentView;
    }

    /**
     * References the view, which belongs to a specific resource ID by using the view holder
     * pattern. The view is implicitly casted to the type of the field it is assigned to.
     *
     * @param <ViewType>
     *         The type, the view is implicitly casted to. It must be inherited from the class
     *         {@link View}
     * @param viewId
     *         The resource ID of the view, which should be referenced, as an {@link Integer} value.
     *         The ID must be a valid resource ID of the parent view of the view, whose appearance
     *         is currently customized by the adapter
     * @return The view, which belongs to the given resource ID, as an instance of the class {@link
     * View} or null, if no view with the given ID is available
     */
    @SuppressWarnings("unchecked")
    protected final <ViewType extends View> ViewType findViewById(@IdRes final int viewId) {
        Condition.INSTANCE.ensureNotNull(currentParentView, "No parent view set",
                IllegalStateException.class);
        ViewHolder viewHolder = (ViewHolder) currentParentView.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder(currentParentView);
            currentParentView.setTag(viewHolder);
        }

        return (ViewType) viewHolder.findViewById(viewId);
    }

}