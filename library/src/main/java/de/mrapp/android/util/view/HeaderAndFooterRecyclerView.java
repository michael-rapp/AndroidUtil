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

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import de.mrapp.android.util.ViewUtil;
import de.mrapp.util.Condition;

/**
 * A recycler view, which can contain multiple fixed views as headers and footers.
 *
 * @author Michael Rapp
 * @since 1.12.0
 */
public class HeaderAndFooterRecyclerView extends RecyclerView
        implements HeaderAndFooterAdapterView {

    /**
     * An adapter, which encapsulates another adapter in order to add the ability to display a
     * recycler view's header and footer views.
     */
    private class AdapterWrapper extends Adapter<ViewHolder> {

        /**
         * The view holder, which is used to visualize header views.
         */
        private class HeaderViewHolder extends ViewHolder {

            /**
             * Creates a new view holder, which is used to visualize header views.
             *
             * @param itemView
             *         The view group, which should be used to visualize the header views, as an
             *         instance of the class {@link ViewGroup}. The view group may not be null
             */
            public HeaderViewHolder(@NonNull final ViewGroup itemView) {
                super(itemView);
            }
        }

        /**
         * The view holder, which is used to visualize footer views.
         */
        private class FooterViewHolder extends ViewHolder {

            /**
             * Creates a new view holder, which is used to visualize footer views.
             *
             * @param parentView
             *         The view group, which should be used to visualize the footer views, as an
             *         instance of the class {@link ViewGroup}. The view group may not be null
             */
            public FooterViewHolder(@NonNull final ViewGroup parentView) {
                super(parentView);
            }
        }

        /**
         * The view type of a header.
         */
        private static final int VIEW_TYPE_HEADER = 4319;

        /**
         * The view type of a footer.
         */
        private static final int VIEW_TYPE_FOOTER = 4320;

        /**
         * The encapsulated adapter.
         */
        private final Adapter encapsulatedAdapter;

        /**
         * Creates and returns an observer, which allows to delegate calls of the encapsulated
         * adapter's <code>notifyDataSetChanged</code>-, <code>notifyItemRangeChanged</code>-,
         * <code>notifyItemRangeChange</code>-, <code>notifyItemRangeMoved</code>,
         * <code>notifyItemRangeInserted</code>- or <code>notifyItemRangeRemoved</code>-method to
         * its wrapper.
         *
         * @return The observer, which has been created, as an instance of the class {@link
         * AdapterDataObserver}
         */
        private AdapterDataObserver createDataObserver() {
            return new AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeChanged(int positionStart, int itemCount) {
                    super.onItemRangeChanged(positionStart, itemCount);
                    notifyItemRangeChanged(positionStart + getHeaderViewsCount(), itemCount);
                }

                @Override
                public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                    super.onItemRangeChanged(positionStart, itemCount, payload);
                    notifyItemRangeChanged(positionStart + getHeaderViewsCount(), itemCount,
                            payload);
                }

                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    notifyItemRangeInserted(positionStart + getHeaderViewsCount(), itemCount);
                }

                @Override
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    super.onItemRangeRemoved(positionStart, itemCount);
                    notifyItemRangeRemoved(positionStart + getHeaderViewsCount(), itemCount);
                }

                @Override
                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                    super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                    int headerViewsCount = getHeaderViewsCount();

                    for (int i = 0; i < itemCount; i++) {
                        notifyItemMoved(fromPosition + i + headerViewsCount,
                                toPosition + i + headerViewsCount);
                    }
                }

            };
        }

        /**
         * Creates a new adapter, which encapsulates another adapter in order to add the ability to
         * display a recycler view's header and footer views.
         *
         * @param encapsulatedAdapter
         *         The adapter, which should be encapsulated, as an instance of the type {@link
         *         Adapter}. The adapter may not be null
         */
        public AdapterWrapper(@NonNull final Adapter encapsulatedAdapter) {
            Condition.INSTANCE.ensureNotNull(encapsulatedAdapter, "The adapter may not be null");
            this.encapsulatedAdapter = encapsulatedAdapter;
            this.encapsulatedAdapter.registerAdapterDataObserver(createDataObserver());
        }

        /**
         * Returns the adapter, which is encapsulated by the adapter.
         *
         * @return The adapter, which is encapsulated by the adapter, as an instance of the type
         * {@link Adapter}. The adapter may not be null
         */
        @NonNull
        public Adapter getEncapsulatedAdapter() {
            return encapsulatedAdapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            if (viewType == VIEW_TYPE_HEADER) {
                ViewGroup view = new FrameLayout(getContext());
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                return new HeaderViewHolder(view);
            } else if (viewType == VIEW_TYPE_FOOTER) {
                ViewGroup view = new FrameLayout(getContext());
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                return new FooterViewHolder(view);
            }

            return encapsulatedAdapter.onCreateViewHolder(parent, viewType);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (holder instanceof HeaderViewHolder) {
                ViewGroup parentView = (ViewGroup) holder.itemView;
                parentView.removeAllViews();
                View header = headers.get(position);
                ViewUtil.removeFromParent(header);
                parentView.addView(header);
            } else if (holder instanceof FooterViewHolder) {
                ViewGroup parentView = (ViewGroup) holder.itemView;
                parentView.removeAllViews();
                View footer = footers.get(
                        position - encapsulatedAdapter.getItemCount() - getHeaderViewsCount());
                ViewUtil.removeFromParent(footer);
                parentView.addView(footer);
            } else {
                encapsulatedAdapter.onBindViewHolder(holder, position - getHeaderViewsCount());
            }
        }

        @Override
        public int getItemViewType(final int position) {
            if (position < getHeaderViewsCount()) {
                return VIEW_TYPE_HEADER;
            } else if (position < getHeaderViewsCount() + encapsulatedAdapter.getItemCount()) {
                return encapsulatedAdapter.getItemViewType(position - getHeaderViewsCount());
            } else {
                return VIEW_TYPE_FOOTER;
            }
        }

        @Override
        public int getItemCount() {
            return encapsulatedAdapter.getItemCount() + getHeaderViewsCount() +
                    getFooterViewsCount();
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

    }

    /**
     * The recycler view's adapter.
     */
    private AdapterWrapper adapter;

    /**
     * A list, which contains the recycler view's headers.
     */
    private final List<View> headers = new ArrayList<>();

    /**
     * A list, which contains the recycler view's footers.
     */
    private final List<View> footers = new ArrayList<>();

    /**
     * Notifies, that the underlying data of the recycler view's adapter has been changed.
     */
    private void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Creates a new recycler view, which can contain multiple fixed views as headers and footers.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     */
    public HeaderAndFooterRecyclerView(@NonNull final Context context) {
        super(context);
    }

    /**
     * Creates a new recycler view, which can contain multiple fixed views as headers and footers.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     */
    public HeaderAndFooterRecyclerView(@NonNull final Context context,
                                       @Nullable final AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * Creates a new recycler view, which can contain multiple fixed views as headers and footers.
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
    public HeaderAndFooterRecyclerView(@NonNull final Context context,
                                       @Nullable final AttributeSet attributeSet,
                                       @AttrRes final int defaultStyle) {
        super(context, attributeSet, defaultStyle);
    }

    @Override
    public final void addHeaderView(@NonNull final View view) {
        Condition.INSTANCE.ensureNotNull(view, "The view may not be null");
        headers.add(view);
        notifyDataSetChanged();
    }

    @Override
    public final void removeHeaderView(@NonNull final View view) {
        Condition.INSTANCE.ensureNotNull(view, "The view may not be null");

        for (int i = getHeaderViewsCount() - 1; i >= 0; i--) {
            View header = headers.get(i);

            if (header == view) {
                headers.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    @NonNull
    @Override
    public final View removeHeaderView(final int index) {
        View header = headers.remove(index);
        notifyDataSetChanged();
        return header;
    }

    @Override
    public final void removeAllHeaderViews() {
        headers.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public final View getHeaderView(int index) {
        return headers.get(index);
    }

    @Override
    public final int getHeaderViewsCount() {
        return headers.size();
    }

    @Override
    public final void addFooterView(@NonNull final View view) {
        Condition.INSTANCE.ensureNotNull(view, "The view may not be null");
        footers.add(view);
        notifyDataSetChanged();
    }

    @Override
    public final void removeFooterView(@NonNull final View view) {
        Condition.INSTANCE.ensureNotNull(view, "The view may not be null");

        for (int i = getFooterViewsCount() - 1; i >= 0; i--) {
            View footer = footers.get(i);

            if (footer == view) {
                footers.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    @NonNull
    @Override
    public final View removeFooterView(final int index) {
        View footer = footers.remove(index);
        notifyDataSetChanged();
        return footer;
    }

    @Override
    public final void removeAllFooterViews() {
        footers.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public final View getFooterView(final int index) {
        return footers.get(index);
    }

    @Override
    public final int getFooterViewsCount() {
        return footers.size();
    }

    @Override
    public final Adapter getAdapter() {
        return adapter != null ? adapter.getEncapsulatedAdapter() : null;
    }

    @Override
    public final void setAdapter(@Nullable final Adapter adapter) {
        if (adapter != null) {
            this.adapter = new AdapterWrapper(adapter);
            super.setAdapter(this.adapter);
        } else {
            this.adapter = null;
            super.setAdapter(null);
        }
    }
}
