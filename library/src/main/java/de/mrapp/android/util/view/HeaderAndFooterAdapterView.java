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

import androidx.annotation.NonNull;
import android.view.View;

/**
 * Defines the interface an adapter view, e.g. a grid view or recycler view, which allows to display
 * fixed views as headers and footers, must implement
 *
 * @author Michael Rapp
 * @since 1.20.0
 */
public interface HeaderAndFooterAdapterView {

    /**
     * Adds a fixed view to appear at the top of the adapter view. If this method is called more
     * than once, the views will appear in the order they were added.
     *
     * @param view
     *         The header view, which should be added, as an instance of the class {@link View}. The
     *         view may not be null
     */
    void addHeaderView(@NonNull View view);

    /**
     * Removes a previously added header view.
     *
     * @param view
     *         The header view, which should be removed, as an instance of the class {@link View}.
     *         The view may not be null
     */
    void removeHeaderView(@NonNull View view);

    /**
     * Removes and returns the header view at a specific index.
     *
     * @param index
     *         The index of the header view, which should be removed, as an {@link Integer} value.
     *         The index must be at least 0 and less than {@link #getHeaderViewsCount()}
     * @return The header view, which has been removed, as an instance of the class {@link View}.
     * The view may not be null
     */
    @NonNull
    View removeHeaderView(int index);

    /**
     * Removes all header views.
     */
    void removeAllHeaderViews();

    /**
     * Returns the header view at a specific index.
     *
     * @param index
     *         The index of the header view to be returned, as an {@link Integer} value. The index
     *         must be at least 0 and less than {@link #getHeaderViewsCount()}
     * @return The header view at the given index as an instance of the class {@link View}. The view
     * may not be null
     */
    @NonNull
    View getHeaderView(int index);

    /**
     * Returns the number of header views in the adapter view.
     *
     * @return The number of header views as an {@link Integer} value
     */
    int getHeaderViewsCount();

    /**
     * Adds a fixed view to appear at the bottom of the adapter view. If this method is called more
     * than once, the views will appear in the order they were added.
     *
     * @param view
     *         The footer view, which should be added, as an instance of the class {@link View}. The
     *         view may not be null
     */
    void addFooterView(@NonNull View view);

    /**
     * Removes a previously added footer view.
     *
     * @param view
     *         The footer view, which should be removed, as an instance of the class {@link View}.
     *         The view may not be null
     */
    void removeFooterView(@NonNull View view);

    /**
     * Removes the footer view at a specific index.
     *
     * @param index
     *         The index of the footer view, which should be removed, as an {@link Integer} value.
     *         The index must be at least 0 and less than {@link #getFooterViewsCount()}
     * @return The footer view, which has been removed, as an instance of the class {@link View}.
     * The view may not be null
     */
    @NonNull
    View removeFooterView(int index);

    /**
     * Removes all footer views.
     */
    void removeAllFooterViews();

    /**
     * Returns the footer view at a specific index.
     *
     * @param index
     *         The index of the footer view to be returned as an {@link Integer} value. The index
     *         must be at least 0 and less than {@link #getFooterViewsCount()}
     * @return The footer view at the given index as an instance of the class {@link View}. The view
     * may not be null
     */
    @NonNull
    View getFooterView(int index);

    /**
     * Returns the number of footer views in the adapter view.
     *
     * @return The number of footer views as an {@link Integer} value
     */
    int getFooterViewsCount();
}