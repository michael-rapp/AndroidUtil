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

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * A grid view, which can contain multiple fixed views as headers and footers.
 *
 * @author Michael Rapp
 * @since 1.6.0
 */
public class HeaderAndFooterGridView extends GridView {

    /**
     * A layout, which is used as a container for a single child view. The layout is expanded to the
     * full width of the grid view.
     */
    protected class FullWidthContainer extends FrameLayout {

        /**
         * Creates a new layout, which is used as a container for a single child view.
         *
         * @param child
         *         The child view, which should be contained by the layout, as an instance of the
         *         class {@link View}. The view may not be null
         */
        public FullWidthContainer(@NonNull final View child) {
            super(child.getContext());
            addView(child);
        }

        @Override
        protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
            int fullWidth = HeaderAndFooterGridView.this.getMeasuredWidth() -
                    HeaderAndFooterGridView.this.getPaddingLeft() -
                    HeaderAndFooterGridView.this.getPaddingRight();
            int measuredWidth =
                    MeasureSpec.makeMeasureSpec(fullWidth, MeasureSpec.getMode(widthMeasureSpec));
            super.onMeasure(measuredWidth, heightMeasureSpec);
        }

    }

    /**
     * An invisible view, which is used as a placeholder.
     */
    protected class PlaceholderView extends View {

        /**
         * Creates a new invisible view, which is used as a placeholder.
         *
         * @param context
         *         The context, which should be used by the view, as an instance of the class {@link
         *         Context}. The context may not be null
         */
        public PlaceholderView(@NonNull final Context context) {
            super(context);
            setVisibility(View.INVISIBLE);
        }

    }

    /**
     * Represents a header or footer of the grid view.
     */
    private class FullWidthItem {

        /**
         * The view, which is used to display the header or footer.
         */
        private final FullWidthContainer view;

        /**
         * The data, which is associated with the header or footer.
         */
        private final Object data;

        /**
         * True, if the header or footer is selectable, false otherwise.
         */
        private final boolean selectable;

        /**
         * Creates a new representation of a header or footer.
         *
         * @param view
         *         The view, which should be used to display the header or footer, as an instance of
         *         the class {@link View}. The view may not be null
         * @param data
         *         The data, which should be associated with the header or footer, as an instance of
         *         the class {@link Object} or null, if no data should be associated with the header
         *         or footer
         * @param selectable
         *         True, if the header or footer should be selectable, false otherwise
         */
        public FullWidthItem(@NonNull final View view, @Nullable final Object data,
                             final boolean selectable) {
            ensureNotNull(view, "The view may not be null");
            this.view = new FullWidthContainer(view);
            this.data = data;
            this.selectable = selectable;
        }

    }

    /**
     * An adapter, which encapsulates another adapter in order to add the ability to display a grid
     * view's header and footer views.
     */
    private class AdapterWrapper extends BaseAdapter implements WrapperListAdapter, Filterable {

        /**
         * The encapsulated adapter.
         */
        private final ListAdapter encapsulatedAdapter;

        /**
         * Creates and returns an observer, which allows to delegate calls of the encapsulated
         * adapter's <code>notifyDataSetChanged</code>- or <code>notifyDataSetInvalidated</code>-method
         * to its wrapper.
         *
         * @return The observer, which has been created, as an instance of the class {@link
         * DataSetObserver}
         */
        private DataSetObserver createDataSetObserver() {
            return new DataSetObserver() {

                @Override
                public void onChanged() {
                    notifyDataSetChanged();
                }

                @Override
                public void onInvalidated() {
                    notifyDataSetInvalidated();
                }

            };
        }

        /**
         * Creates a new adapter, which encapsulates another adapter in order to add the ability to
         * display a grid view's header and footer views.
         *
         * @param encapsulatedAdapter
         *         The adapter, which should be encapsulated, as an instance of the type {@link
         *         ListAdapter}. The adapter may not be null
         */
        public AdapterWrapper(@NonNull final ListAdapter encapsulatedAdapter) {
            ensureNotNull(encapsulatedAdapter, "The adapter may not be null");
            this.encapsulatedAdapter = encapsulatedAdapter;
            this.encapsulatedAdapter.registerDataSetObserver(createDataSetObserver());
        }

        /**
         * Returns the adapter, which is encapsulated by the adapter.
         *
         * @return The adapter, which is encapsulated by the adapter, as an instance of the type
         * {@link ListAdapter}. The adapter may not be null
         */
        @NonNull
        public ListAdapter getEncapsulatedAdapter() {
            return encapsulatedAdapter;
        }

        @Override
        public int getCount() {
            int numColumns = getNumColumnsCompatible();
            int adapterCount = encapsulatedAdapter.getCount();
            return (getHeaderViewsCount() + getFooterViewsCount()) * numColumns + adapterCount +
                    getNumberOfPlaceholderViews();
        }

        @Nullable
        @Override
        public Object getItem(final int position) {
            int numColumns = getNumColumnsCompatible();
            int headerItemCount = getHeaderViewsCount() * numColumns;
            int adapterCount = encapsulatedAdapter.getCount();

            if (position < headerItemCount) {
                if (position % numColumns == 0) {
                    return headers.get(position / numColumns).data;
                } else {
                    return null;
                }
            } else if (position < getHeaderViewsCount() + adapterCount +
                    getNumberOfPlaceholderViews()) {
                if (position < headerItemCount + adapterCount) {
                    return encapsulatedAdapter.getItem(position - headerItemCount);
                } else {
                    return null;
                }
            } else {
                if (position % numColumns == 0) {
                    return footers.get((position - headerItemCount - adapterCount -
                            getNumberOfPlaceholderViews()) / numColumns).data;
                } else {
                    return null;
                }
            }
        }

        @NonNull
        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            int numColumns = getNumColumnsCompatible();
            int headerItemCount = getHeaderViewsCount() * numColumns;
            int adapterCount = encapsulatedAdapter.getCount();

            if (position < headerItemCount) {
                FullWidthContainer fullWidthContainer = headers.get(position / numColumns).view;

                if (position % numColumns == 0) {
                    return fullWidthContainer;
                } else {
                    return inflatePlaceholderView(convertView, fullWidthContainer.getHeight());
                }
            } else if (position < headerItemCount + adapterCount +
                    getNumberOfPlaceholderViews()) {
                if (position < headerItemCount + adapterCount) {
                    return encapsulatedAdapter.getView(position - headerItemCount,
                            convertView instanceof FullWidthContainer ||
                                    convertView instanceof PlaceholderView ? null : convertView,
                            parent);
                } else {
                    return inflatePlaceholderView(convertView, getViewHeight(this, position - 1));

                }
            } else {
                FullWidthContainer fullWidthContainer =
                        footers.get((position - headerItemCount - adapterCount -
                                getNumberOfPlaceholderViews()) / numColumns).view;

                if (position % numColumns == 0) {
                    return fullWidthContainer;
                } else {
                    return inflatePlaceholderView(convertView, fullWidthContainer.getHeight());
                }
            }
        }

        @Override
        public boolean isEnabled(final int position) {
            int numColumns = getNumColumnsCompatible();
            int headerItemCount = getHeaderViewsCount() * numColumns;
            int adapterCount = encapsulatedAdapter.getCount();

            if (position < headerItemCount) {
                return position % numColumns == 0 && headers.get(position / numColumns).selectable;
            } else if (position < headerItemCount + adapterCount +
                    getNumberOfPlaceholderViews()) {
                return position < headerItemCount + adapterCount &&
                        encapsulatedAdapter.isEnabled(position - headerItemCount);
            } else {
                return position % numColumns == 0 &&
                        footers.get((position - headerItemCount - adapterCount -
                                getNumberOfPlaceholderViews()) / numColumns).selectable;
            }
        }

        @Override
        public boolean areAllItemsEnabled() {
            boolean result = encapsulatedAdapter.areAllItemsEnabled();

            for (FullWidthItem header : headers) {
                result &= header.selectable;
            }

            for (FullWidthItem footer : footers) {
                result &= footer.selectable;
            }

            return result;
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return encapsulatedAdapter.hasStableIds();
        }

        @Override
        public ListAdapter getWrappedAdapter() {
            return encapsulatedAdapter;
        }

        @Override
        public Filter getFilter() {
            if (encapsulatedAdapter instanceof Filterable) {
                return ((Filterable) encapsulatedAdapter).getFilter();
            }

            return null;
        }

    }

    /**
     * The grid view's adapter.
     */
    private AdapterWrapper adapter;

    /**
     * A list, which contains the grid view's headers.
     */
    private final List<FullWidthItem> headers = new ArrayList<>();

    /**
     * A list, which contains the grid view's footers.
     */
    private final List<FullWidthItem> footers = new ArrayList<>();

    /**
     * Inflates an invisible placeholder view with a specific height.
     *
     * @param convertView
     *         The old view to reuse, if possible, as an instance of the class {@link View} or null,
     *         if no view to reuse is available.
     * @param height
     *         The height, which should be used, as an {@link Integer} value
     * @return The view, which has been inflated, as an instance of the class {@link View}
     */
    @NonNull
    protected final View inflatePlaceholderView(@Nullable final View convertView,
                                                final int height) {
        View view = convertView;

        if (!(view instanceof PlaceholderView)) {
            view = new PlaceholderView(getContext());
        }

        view.setMinimumHeight(height);
        return view;
    }

    /**
     * Returns the height of the view, which corresponds to a specific position of an adapter.
     *
     * @param adapter
     *         The adapter as an instance of the type {@link ListAdapter}. The adapter may not be
     *         null
     * @param position
     *         The position of the view, whose height should be returned, as an {@link Integer}
     *         value
     * @return The height of the view, which corresponds to the given position, in pixels as an
     * {@link Integer} value
     */
    protected final int getViewHeight(@NonNull final ListAdapter adapter, final int position) {
        View view = adapter.getView(position, null, this);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

        if (layoutParams == null) {
            layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
        }

        int widthMeasureSpec = getChildMeasureSpec(
                MeasureSpec.makeMeasureSpec(getColumnWidthCompatible(), MeasureSpec.EXACTLY), 0,
                layoutParams.width);
        int heightMeasureSpec =
                getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 0,
                        layoutParams.height);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredHeight();
    }

    /**
     * Returns the width of the grid view's columns by either using the
     * <code>getColumnWidth</code>-method on devices with API level 16 or greater, or via reflection
     * on older devices.
     *
     * @return The width of the grid view's columns in pixels as an {@link Integer} value or -1, if
     * the layout is pending
     */
    protected final int getColumnWidthCompatible() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return super.getColumnWidth();
        } else {
            try {
                Field columnWidth = GridView.class.getDeclaredField("mColumnWidth");
                columnWidth.setAccessible(true);
                return columnWidth.getInt(this);
            } catch (Exception e) {
                throw new RuntimeException("Unable to retrieve column width", e);
            }
        }
    }

    /**
     * Returns the number of the grid view's columns by either using the
     * <code>getNumColumns</code>-method on devices with API level 11 or greater, or via reflection
     * on older devices.
     *
     * @return The number of the grid view's columns as an {@link Integer} value or {@link
     * #AUTO_FIT}, if the layout is pending
     */
    protected final int getNumColumnsCompatible() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return super.getNumColumns();
        } else {
            try {
                Field numColumns = GridView.class.getDeclaredField("mNumColumns");
                numColumns.setAccessible(true);
                return numColumns.getInt(this);
            } catch (Exception e) {
                throw new RuntimeException("Unable to retrieve number of columns", e);
            }
        }
    }

    /**
     * Creates and returns a listener, which encapsulates another listener in order to be able to
     * adapt the position of the item, which has been clicked.
     *
     * @param encapsulatedListener
     *         The listener, which should be encapsulated, as an instance of the type {@link
     *         OnItemClickListener}
     * @return The listener, which has been created, as an instance of the type {@link
     * OnItemClickListener}
     */
    private OnItemClickListener createItemClickListener(
            @NonNull final OnItemClickListener encapsulatedListener) {
        return new OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    final int position, final long id) {
                encapsulatedListener.onItemClick(parent, view, getItemPosition(position), id);
            }

        };
    }

    /**
     * Creates and returns a listener, which encapsulates another listener in order to be able to
     * adapt the position of the item, which has been long-clicked.
     *
     * @param encapsulatedListener
     *         The listener, which should be encapsulated, as an instance of the type {@link
     *         OnItemLongClickListener}
     * @return The listener, which has been created, as an instance of the type {@link
     * OnItemLongClickListener}
     */
    private OnItemLongClickListener createItemLongClickListener(
            @NonNull final OnItemLongClickListener encapsulatedListener) {
        return new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view,
                                           final int position, final long id) {
                return encapsulatedListener
                        .onItemLongClick(parent, view, getItemPosition(position), id);
            }

        };
    }

    /**
     * Returns the index of the item, which corresponds to a specific flattened position.
     *
     * @param position
     *         The flattened position of the item, whose index should be returned, as an {@link
     *         Integer} value
     * @return The index of the item, which corresponds to the given flattened position, as an
     * {@link Integer} value
     */
    private int getItemPosition(final int position) {
        int numColumns = getNumColumnsCompatible();
        int headerItemCount = getHeaderViewsCount() * numColumns;
        int adapterCount = adapter.getEncapsulatedAdapter().getCount();

        if (position < headerItemCount) {
            return position / numColumns;
        } else if (position < headerItemCount + adapterCount +
                getNumberOfPlaceholderViews()) {
            return position - headerItemCount + getHeaderViewsCount();
        } else {
            return getHeaderViewsCount() + adapterCount +
                    (position - headerItemCount - adapterCount -
                            getNumberOfPlaceholderViews()) / numColumns;
        }
    }

    /**
     * Returns the number of placeholder views, which are necessary to complement the items of the
     * encapsulated adapter in order to fill up all columns..
     *
     * @return The number of placeholder views, which are necessary to complement the items of the
     * encapsulated adapter in order to fill up all columns, as an {@link Integer} value
     */
    private int getNumberOfPlaceholderViews() {
        int numColumns = getNumColumnsCompatible();
        int adapterCount = adapter.getEncapsulatedAdapter().getCount();
        int lastLineCount = adapterCount % numColumns;
        return lastLineCount > 0 ? numColumns - lastLineCount : 0;
    }

    /**
     * Notifies, that the underlying data of the grid view's adapter has been changed.
     */
    private void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Creates a new grid view, which can contain multiple fixed views as headers and footers.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     */
    public HeaderAndFooterGridView(@NonNull final Context context) {
        super(context);
    }

    /**
     * Creates a new grid view, which can contain multiple fixed views as headers and footers.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     */
    public HeaderAndFooterGridView(@NonNull final Context context,
                                   @Nullable final AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * Creates a new grid view, which can contain multiple fixed views as headers and footers.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     * @param defaultStyle
     *         The default style to apply to this view. If 0, no style will be applied (beyond what
     *         is included in the theme). This may either be an attribute resource, whose value will
     *         be retrieved from the current theme, or an explicit style resource
     */
    public HeaderAndFooterGridView(@NonNull final Context context,
                                   @Nullable final AttributeSet attributeSet,
                                   @AttrRes final int defaultStyle) {
        super(context, attributeSet, defaultStyle);
    }

    /**
     * Creates a new grid view, which can contain multiple fixed views as headers and footers.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     * @param defaultStyle
     *         The default style to apply to this view. If 0, no style will be applied (beyond what
     *         is included in the theme). This may either be an attribute resource, whose value will
     *         be retrieved from the current theme, or an explicit style resource
     * @param defaultStyleResource
     *         A resource identifier of a style resource that supplies default values for the view,
     *         used only if the default style is 0 or can not be found in the theme. Can be 0 to not
     *         look for defaults
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderAndFooterGridView(@NonNull final Context context,
                                   @Nullable final AttributeSet attributeSet,
                                   final int defaultStyle,
                                   @StyleRes final int defaultStyleResource) {
        super(context, attributeSet, defaultStyle, defaultStyleResource);
    }

    /**
     * Adds a fixed view to appear at the top of the grid view. If this method is called more than
     * once, the views will appear in the order they were added.
     *
     * @param view
     *         The header view, which should be added, as an instance of the class {@link View}. The
     *         view may not be null
     */
    public final void addHeaderView(@NonNull final View view) {
        addHeaderView(view, null, true);
    }

    /**
     * Adds a fixed view to appear at the top of the grid view. If this method is called more than
     * once, the views will appear in the order they were added.
     *
     * @param view
     *         The header view, which should be added, as an instance of the class {@link View}. The
     *         view may not be null
     * @param data
     *         The data, which should be associated with the header view, as an instance of the
     *         class {@link Object}, or null, if no data should be associated with the view
     * @param selectable
     *         True, if the header view should be selectable, false otherwise
     */
    public final void addHeaderView(@NonNull final View view, @Nullable final Object data,
                                    final boolean selectable) {
        ensureNotNull(view, "The view may not be null");
        headers.add(new FullWidthItem(view, data, selectable));
        notifyDataSetChanged();
    }

    /**
     * Removes a previously added header view.
     *
     * @param view
     *         The header view, which should be removed, as an instance of the class {@link View}.
     *         The view may not be null
     */
    public final void removeHeaderView(@NonNull final View view) {
        ensureNotNull(view, "The view may not be null");

        for (int i = getHeaderViewsCount() - 1; i >= 0; i--) {
            FullWidthItem header = headers.get(i);

            if (header.view.getChildAt(0) == view) {
                headers.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    /**
     * Returns the number of header views in the grid view.
     *
     * @return The number of header views as an {@link Integer} value
     */
    public final int getHeaderViewsCount() {
        return headers.size();
    }

    /**
     * Adds a fixed view to appear at the bottom of the grid view. If this method is called more
     * than once, the views will appear in the order they were added.
     *
     * @param view
     *         The footer view, which should be added, as an instance of the class {@link View}. The
     *         view may not be null
     */
    public final void addFooterView(@NonNull final View view) {
        addFooterView(view, null, true);
    }

    /**
     * Adds a fixed view to appear at the bottom of the grid view. If this method is called more
     * than once, the views will appear in the order they were added.
     *
     * @param view
     *         The footer view, which should be added, as an instance of the class {@link View}. The
     *         view may not be null
     * @param data
     *         The data, which should be associated with the footer view, as an instance of the
     *         class {@link Object}, or null, if no data should be associated with the view
     * @param selectable
     *         True, if the footer view should be selectable, false otherwise
     */
    public final void addFooterView(@NonNull final View view, @Nullable final Object data,
                                    final boolean selectable) {
        ensureNotNull(view, "The view may not be null");
        footers.add(new FullWidthItem(view, data, selectable));
        notifyDataSetChanged();
    }

    /**
     * Removes a previously added footer view.
     *
     * @param view
     *         The footer view, which should be removed, as an instance of the class {@link View}.
     *         The view may not be null
     */
    public final void removeFooterView(@NonNull final View view) {
        ensureNotNull(view, "The view may not be null");

        for (int i = getFooterViewsCount() - 1; i >= 0; i--) {
            FullWidthItem footer = footers.get(i);

            if (footer.view.getChildAt(0) == view) {
                footers.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    /**
     * Returns the number of footer views in the grid view.
     *
     * @return The number of footer views as an {@link Integer} value
     */
    public final int getFooterViewsCount() {
        return footers.size();
    }

    @Override
    public final void setClipChildren(final boolean clipChildren) {
        if (!clipChildren) {
            throw new IllegalArgumentException(
                    "Header and footer views require the GridView's children to not be clipped");
        } else {
            super.setClipChildren(false);
        }
    }

    @Override
    public final ListAdapter getAdapter() {
        return adapter != null ? adapter.getEncapsulatedAdapter() : null;
    }

    @Override
    public void setAdapter(@Nullable final ListAdapter adapter) {
        if (adapter != null) {
            this.adapter = new AdapterWrapper(adapter);
            super.setAdapter(this.adapter);
        } else {
            this.adapter = null;
            super.setAdapter(null);
        }
    }

    @Override
    public void setOnItemClickListener(@Nullable final OnItemClickListener listener) {
        super.setOnItemClickListener(listener != null ? createItemClickListener(listener) : null);
    }

    @Override
    public void setOnItemLongClickListener(@Nullable final OnItemLongClickListener listener) {
        super.setOnItemLongClickListener(
                listener != null ? createItemLongClickListener(listener) : null);
    }

}