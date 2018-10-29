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

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * A view holder, which maintains references to previously references views in order to avoid
 * <code>findViewById</code>-method calls when reusing these views.
 *
 * @author Michael Rapp
 * @since 1.14.0
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    /**
     * The view, the view holder is associated with.
     */
    private final View parentView;

    /**
     * A sparse array, which maps the resource IDs of already referenced views to the views
     * themselves.
     */
    private SparseArray<View> views;

    /**
     * Creates a new view holder.
     *
     * @param parentView
     *         The view, the view holder is associated with, as an instance of the class {@link
     *         View}. The view may not be null
     */
    public ViewHolder(@NonNull final View parentView) {
        super(parentView);
        this.parentView = parentView;
        this.views = null;
    }

    /**
     * Returns the view, the view holder is associated with.
     *
     * @return The view, the view holder is associated with, as an instance of the class {@link
     * View}. The view may not be null
     */
    @NonNull
    public final View getParentView() {
        return parentView;
    }

    /**
     * Returns the view, which belongs to a specific resource ID. If the view has already been
     * referenced, the view, which is stored by the view holder, will be returned. Otherwise the
     * method <code>findViewById(int):View</code> of the parent view, the view holder belongs to, is
     * used to reference the view.
     *
     * @param viewId
     *         The resource ID of the view, which should be returned, as an {@link Integer} value.
     *         The ID must be a valid resource ID of a view, which belongs to the given parent view
     * @return The view, which belongs to the given resource ID, as an instance of the class {@link
     * View} or null, if no view with the given ID is available
     */
    public final View findViewById(@IdRes final int viewId) {
        View view = null;

        if (views != null) {
            view = views.get(viewId);
        } else {
            views = new SparseArray<>();
        }

        if (view == null) {
            view = parentView.findViewById(viewId);
            views.put(viewId, view);
        }

        return view;
    }

    /**
     * Removes all references, which are stored by the view holder.
     */
    public final void clear() {
        if (views != null) {
            views.clear();
            views = null;
        }
    }

}