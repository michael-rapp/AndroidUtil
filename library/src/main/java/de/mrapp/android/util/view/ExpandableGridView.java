/*
 * Copyright 2014 - 2016 Michael Rapp
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
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;

import java.util.HashSet;
import java.util.Set;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * A grid view, which can contain multiple groups, which can individually be expanded to show their
 * children.
 *
 * @author Michael Rapp
 * @since 1.12.0
 */
public class ExpandableGridView extends HeaderAndFooterGridView {

    /**
     * Defines the interface, a class, which should be notified, when a group of an expandable grid
     * view has been clicked, must implement.
     */
    public interface OnGroupClickListener {

        /**
         * The method, which is invoked, when a group of an expandable grid view has been clicked.
         *
         * @param parent
         *         The expandable grid view, the group belongs to, as an instance of the class
         *         {@link ExpandableGridView}. The expandable grid view may not be null
         * @param view
         *         The view within the expandable grid view, which has been clicked, as an instance
         *         of the class {@link View}. The view may not be null
         * @param groupIndex
         *         The index of the group, which has been clicked, as an {@link Integer} value
         * @param id
         *         The id of the group, which has been clicked, as an {@link Long} value
         * @return True, if the click has been handled by the listener, false otherwise
         */
        boolean onGroupClick(@NonNull ExpandableGridView parent, @NonNull View view, int groupIndex,
                             long id);

    }

    /**
     * Defines the interface, a class, which should be notified, when a child of an expandable grid
     * view has been clicked, must implement.
     */
    public interface OnChildClickListener {

        /**
         * The method, which is invoked, when a child of an expandable grid view has been clicked.
         *
         * @param parent
         *         The expandable grid view, the child belongs to, as an instance of the class
         *         {@link ExpandableGridView}. The expandable grid view may not be null
         * @param view
         *         The view within the expandable grid view, which has been clicked, as an instance
         *         of the class {@link View}. The view may not be null
         * @param groupIndex
         *         The index of the group, the child, which has been clicked, belongs to, as an
         *         {@link Integer} value
         * @param childIndex
         *         The index of the child, which has been clicked, as an {@link Integer} value
         * @param id
         *         The id of the child, which has been clicked, as an {@link Long} value
         * @return True, if the click has been handled by the listener, false otherwise
         */
        boolean onChildClick(@NonNull ExpandableGridView parent, @NonNull View view, int groupIndex,
                             int childIndex, long id);

    }

    /**
     * An adapter, which encapsulates another adapter in order to add the ability to show multiple
     * groups, which can individually expanded to show their children.
     */
    private class AdapterWrapper extends BaseAdapter {

        /**
         * The encapsulated adapter.
         */
        private final ExpandableListAdapter encapsulatedAdapter;

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
         * show multiple groups, which can individually expanded to show their children.
         *
         * @param encapsulatedAdapter
         *         The adapter, which should be encapsulated, as an instance of the type {@link
         *         ExpandableListAdapter}. The adapter may not be null
         */
        public AdapterWrapper(@NonNull final ExpandableListAdapter encapsulatedAdapter) {
            ensureNotNull(encapsulatedAdapter, "The adapter may not be null");
            this.encapsulatedAdapter = encapsulatedAdapter;
            this.encapsulatedAdapter.registerDataSetObserver(createDataSetObserver());
        }

        /**
         * Returns the adapter, which is encapsulated by the adapter.
         *
         * @return The adapter, which is encapsulated by the adapter, as an instance of the type
         * {@link ExpandableListAdapter}
         */
        @NonNull
        public ExpandableListAdapter getEncapsulatedAdapter() {
            return encapsulatedAdapter;
        }

        @Override
        public int getCount() {
            int count = 0;
            int numColumns = getNumColumnsCompatible();

            for (int i = 0; i < encapsulatedAdapter.getGroupCount(); i++) {
                count += numColumns;

                if (isGroupExpanded(i)) {
                    int childCount = encapsulatedAdapter.getChildrenCount(i);
                    int lastLineCount = childCount % numColumns;
                    count += childCount + (lastLineCount > 0 ? numColumns - lastLineCount : 0);
                }
            }

            return count;
        }

        @Override
        public Object getItem(final int position) {
            Pair<Integer, Integer> itemPosition = getItemPosition(position);
            int groupIndex = itemPosition.first;
            int childIndex = itemPosition.second;

            if (groupIndex == -1 && childIndex == -1) {
                return null;
            } else if (childIndex != -1) {
                return encapsulatedAdapter.getChild(groupIndex, childIndex);
            } else {
                return encapsulatedAdapter.getGroup(groupIndex);
            }
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            Pair<Integer, Integer> itemPosition = getItemPosition(position);
            int groupIndex = itemPosition.first;
            int childIndex = itemPosition.second;

            if (groupIndex == -1 && childIndex == -1) {
                return inflatePlaceholderView(convertView, getViewHeight(this, position - 1));
            } else if (childIndex != -1) {
                return encapsulatedAdapter.getChildView(groupIndex, childIndex,
                        childIndex == encapsulatedAdapter.getChildrenCount(groupIndex) - 1,
                        convertView, parent);
            } else {
                return new FullWidthContainer(encapsulatedAdapter
                        .getGroupView(groupIndex, isGroupExpanded(groupIndex), null, parent));
            }
        }

        @Override
        public boolean areAllItemsEnabled() {
            return encapsulatedAdapter.areAllItemsEnabled();
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return encapsulatedAdapter.hasStableIds();
        }

    }

    /**
     * The type of a packed position, which represents a group.
     */
    public static final int PACKED_POSITION_TYPE_GROUP = 0;

    /**
     * The type of the packed position, which represents a child.
     */
    public static final int PACKED_POSITION_TYPE_CHILD = 1;

    /**
     * The type of the packed position, which represents a neither/null/no preference.
     */
    public static final int PACKED_POSITION_TYPE_NULL = 2;

    /**
     * The value for a packed position that represents neither/null/no preference. This value is not
     * otherwise possible since a group type (first bit 0) should not have a child position filled.
     */
    public static final long PACKED_POSITION_VALUE_NULL = 0x00000000FFFFFFFFL;

    /**
     * The mask (in packed position representation) for the child.
     */
    private static final long PACKED_POSITION_MASK_CHILD = 0x00000000FFFFFFFFL;

    /**
     * The mask (in packed position representation) for the group.
     */
    private static final long PACKED_POSITION_MASK_GROUP = 0x7FFFFFFF00000000L;

    /**
     * The mask (in packed position representation) for the type.
     */
    private static final long PACKED_POSITION_MASK_TYPE = 0x8000000000000000L;

    /**
     * The shift amount (in packed position representation) for the group.
     */
    private static final long PACKED_POSITION_SHIFT_GROUP = 32;

    /**
     * The shift amount (in packed position representation) for the type.
     */
    private static final long PACKED_POSITION_SHIFT_TYPE = 63;

    /**
     * The mask (in integer group position representation) for the group.
     */
    private static final long PACKED_POSITION_INT_MASK_GROUP = 0x7FFFFFFF;

    /**
     * The grid view's adapter.
     */
    private AdapterWrapper adapter;

    /**
     * A set, which contains the indices of all currently expanded groups.
     */
    private Set<Integer> expandedGroups;

    /**
     * The listener, which is notified, when a group has been clicked.
     */
    private OnGroupClickListener groupClickListener;

    /**
     * The listener, which is notified, when a child has been clicked.
     */
    private OnChildClickListener childClickListener;

    /**
     * The listener, which is notified, when any item of the grid view has been clicked.
     */
    private OnItemClickListener itemClickListener;

    /**
     * The listener, which is notified, when any item of the grid view has been long-clicked.
     */
    private OnItemLongClickListener itemLongClickListener;

    /**
     * Initializes the view.
     */
    private void initialize() {
        this.expandedGroups = new HashSet<>();
        super.setOnItemClickListener(createItemClickListener());
        super.setOnItemLongClickListener(createItemLongClickListener());
    }

    /**
     * Creates and returns a listener, which allows to delegate, when any item of the grid view has
     * been clicked, to the appropriate listeners.
     *
     * @return The listener, which has been created, as an instance of the type {@link
     * OnItemClickListener}
     */
    private OnItemClickListener createItemClickListener() {
        return new OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    final int position, final long id) {
                Pair<Integer, Integer> itemPosition =
                        getItemPosition(position - getHeaderViewsCount());
                int groupIndex = itemPosition.first;
                int childIndex = itemPosition.second;
                long packedId;

                if (childIndex != -1) {
                    packedId = getPackedPositionForChild(groupIndex, childIndex);
                    notifyOnChildClicked(view, groupIndex, childIndex, packedId);
                } else if (groupIndex != -1) {
                    packedId = getPackedPositionForGroup(groupIndex);
                    notifyOnGroupClicked(view, groupIndex, packedId);
                } else {
                    packedId = getPackedPositionForChild(Integer.MAX_VALUE, position);
                }

                notifyOnItemClicked(view, getPackedPosition(position), packedId);
            }

        };
    }

    /**
     * Creates and returns a listener, which allows to delegate, when any item of the grid view has
     * been long-clicked, to the appropriate listeners.
     *
     * @return The listener, which has been created, as an instance of the type {@link
     * OnItemLongClickListener}
     */
    private OnItemLongClickListener createItemLongClickListener() {
        return new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
                                           long id) {
                Pair<Integer, Integer> itemPosition =
                        getItemPosition(position - getHeaderViewsCount());
                int groupIndex = itemPosition.first;
                int childIndex = itemPosition.second;
                long packedId;

                if (childIndex != -1) {
                    packedId = getPackedPositionForChild(groupIndex, childIndex);
                } else if (groupIndex != -1) {
                    packedId = getPackedPositionForGroup(groupIndex);
                } else {
                    packedId = getPackedPositionForChild(Integer.MAX_VALUE, position);
                }

                return notifyOnItemLongClicked(view, getPackedPosition(position), packedId);
            }

        };
    }

    /**
     * Returns the packed position of an item, which corresponds to a specific position.
     *
     * @param position
     *         The position of the item, whose packed position should be returned, as an {@link
     *         Integer} value
     * @return The packed position, which corresponds to the given position, as an {@link Integer}
     * value
     */
    private int getPackedPosition(final int position) {
        if (position < getHeaderViewsCount()) {
            return position;
        } else {
            Pair<Integer, Integer> pair = getItemPosition(position - getHeaderViewsCount());
            int groupIndex = pair.first;
            int childIndex = pair.second;

            if (childIndex == -1 && groupIndex == -1) {
                int childCount = 0;

                for (int i = 0; i < getExpandableListAdapter().getGroupCount(); i++) {
                    childCount += getExpandableListAdapter().getChildrenCount(i);
                }

                return getHeaderViewsCount() + getExpandableListAdapter().getGroupCount() +
                        childCount + position - (getHeaderViewsCount() + adapter.getCount());
            } else if (childIndex != -1) {
                return getPackedChildPosition(groupIndex, childIndex);
            } else {
                return getPackedGroupPosition(groupIndex);
            }
        }
    }

    /**
     * Returns the packed position of a group, which corresponds to a specific index.
     *
     * @param groupIndex
     *         The index of the group, whose packed position should be returned, as an {@link
     *         Integer} value
     * @return The packed position of the group, which corresponds to the given index, as an {@link
     * Integer} value
     */
    private int getPackedGroupPosition(final int groupIndex) {
        int packedPosition = getHeaderViewsCount();

        if (groupIndex > 0) {
            for (int i = groupIndex - 1; i >= 0; i--) {
                packedPosition += getExpandableListAdapter().getChildrenCount(i) + 1;
            }
        }

        return packedPosition;
    }

    /**
     * Returns the packed position of a child, which corresponds to a specific group and child
     * index.
     *
     * @param groupIndex
     *         The index of the group, the child, whose packed position should be returned, belongs
     *         to, as an {@link Integer} value
     * @param childIndex
     *         The index of the child, whose packed position should be returned, as an {@link
     *         Integer} value
     * @return The packed position of the child, which corresponds to the given group and child
     * index, as an {@link Integer} value
     */
    private int getPackedChildPosition(final int groupIndex, final int childIndex) {
        return getPackedGroupPosition(groupIndex) + childIndex + 1;
    }

    /**
     * Returns a pair, which contains the group and child index of the item, which corresponds to a
     * specific packed position.
     *
     * @param packedPosition
     *         The packed position of the item, whose group and child index should be returned, as
     *         an {@link Integer} value
     * @return A pair, which contains the group and child index of the item, which corresponds to
     * the given packed position, as an instance of the class {@link Pair}
     */
    private Pair<Integer, Integer> getItemPosition(final int packedPosition) {
        int currentPosition = packedPosition;
        int groupIndex = -1;
        int childIndex = -1;
        int numColumns = getNumColumnsCompatible();

        for (int i = 0; i < getExpandableListAdapter().getGroupCount(); i++) {
            if (currentPosition == 0) {
                groupIndex = i;
                break;
            } else if (currentPosition < numColumns) {
                break;
            } else {
                currentPosition -= numColumns;

                if (isGroupExpanded(i)) {
                    int childCount = getExpandableListAdapter().getChildrenCount(i);

                    if (currentPosition < childCount) {
                        groupIndex = i;
                        childIndex = currentPosition;
                        break;
                    } else {
                        int lastLineCount = childCount % numColumns;
                        currentPosition -=
                                childCount + (lastLineCount > 0 ? numColumns - lastLineCount : 0);
                    }
                }
            }
        }

        return new Pair<>(groupIndex, childIndex);
    }

    /**
     * Notifies the listener, which has been registered to be notified, when a group has been
     * clicked, about a group being clicked.
     *
     * @param view
     *         The view within the expandable grid view, which has been clicked, as an instance of
     *         the class {@link View}. The view may not be null
     * @param groupIndex
     *         The index of the group, which has been clicked, as an {@link Integer} value
     * @param id
     *         The id of the group, which has been clicked, as a {@link Long} value
     * @return True, if the click has been handled by the listener, false otherwise
     */
    private boolean notifyOnGroupClicked(@NonNull final View view, final int groupIndex,
                                         final long id) {
        return groupClickListener != null &&
                groupClickListener.onGroupClick(this, view, groupIndex, id);
    }

    /**
     * Notifies the listener, which has been registered to be notified, when a child has been
     * clicked, about a child being clicked.
     *
     * @param view
     *         The view within the expandable grid view, which has been clicked, as an instance of
     *         the class {@link View}. The view may not be null
     * @param groupIndex
     *         The index of the group, the child, which has been clicked, belongs to, as an {@link
     *         Integer} value
     * @param childIndex
     *         The index of the child, which has been clicked, as an {@link Integer} value
     * @param id
     *         The id of the child, which has been clicked, as a {@link Long} value
     * @return True, if the click has been handled by the listener, false otherwise
     */
    private boolean notifyOnChildClicked(@NonNull final View view, final int groupIndex,
                                         final int childIndex, final long id) {
        return childClickListener != null &&
                childClickListener.onChildClick(this, view, groupIndex, childIndex, id);
    }

    /**
     * Notifies, the listener, which has been registered to be notified, when any item has been
     * clicked, about an item being clicked.
     *
     * @param view
     *         The view within the expandable grid view, which has been clicked, as an instance of
     *         the class {@link View}. The view may not be null
     * @param position
     *         The position of the item, which has been clicked, as an {@link Integer} value
     * @param id
     *         The id of the item, which has been clicked, as a {@link Long} value
     */
    private void notifyOnItemClicked(@NonNull final View view, final int position, final long id) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(this, view, position, id);
        }
    }

    /**
     * Notifies the listener, which has been registered to be notified, when any item has been
     * long-clicked, about an item being long-clicked.
     *
     * @param view
     *         The view within the expandable grid view, which has been long-clicked, as an instance
     *         of the class {@link View}. The view may not be null
     * @param position
     *         The position of the item, which has been long-clicked, as an {@link Integer} value
     * @param id
     *         The id of the item, which has been long-clicked, as a {@link Long} value
     * @return True, if the long-click has been handled by the listener, false otherwise
     */
    private boolean notifyOnItemLongClicked(@NonNull final View view, final int position,
                                            final long id) {
        return itemLongClickListener != null &&
                itemLongClickListener.onItemLongClick(this, view, position, id);
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
     * Creates a new grid view, which can contain multiple groups, which can individually be
     * expanded to show their children.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     */
    public ExpandableGridView(@NonNull final Context context) {
        super(context, null);
        initialize();
    }

    /**
     * Creates a new grid view, which can contain multiple groups, which can individually be
     * expanded to show their children.
     *
     * @param context
     *         The context, which should be used by the view, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param attributeSet
     *         The attribute set, the view's attributes should be obtained from, as an instance of
     *         the type {@link AttributeSet} or null, if no attributes should be obtained
     */
    public ExpandableGridView(@NonNull final Context context,
                              @Nullable final AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    /**
     * Creates a new grid view, which can contain multiple groups, which can individually be
     * expanded to show their children.
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
    public ExpandableGridView(@NonNull final Context context,
                              @Nullable final AttributeSet attributeSet,
                              @AttrRes final int defaultStyle) {
        super(context, attributeSet, defaultStyle);
        initialize();
    }

    /**
     * Creates a new grid view, which can contain multiple groups, which can individually be
     * expanded to show their children.
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
    public ExpandableGridView(@NonNull final Context context,
                              @Nullable final AttributeSet attributeSet, final int defaultStyle,
                              @StyleRes final int defaultStyleResource) {
        super(context, attributeSet, defaultStyle, defaultStyleResource);
        initialize();
    }

    /**
     * Returns the type of the item, which corresponds to a specific packed position.
     *
     * @param packedPosition
     *         The packed position of the item, whose type should be returned, as an {@link Integer}
     *         value
     * @return The type of the item, which corresponds to the given packed position, as an {@link
     * Integer} value. The type may either be <code>PACKED_POSITION_TYPE_GROUP</code>,
     * <code>PACKED_POSITION_TYPE_CHILD</code> or <code>PACKED_POSITION_TYPE_NULL</code>
     */
    public static int getPackedPositionType(final long packedPosition) {
        if (packedPosition == PACKED_POSITION_VALUE_NULL) {
            return PACKED_POSITION_TYPE_NULL;
        }

        return (packedPosition & PACKED_POSITION_MASK_TYPE) == PACKED_POSITION_MASK_TYPE ?
                PACKED_POSITION_TYPE_CHILD : PACKED_POSITION_TYPE_GROUP;
    }

    /**
     * Returns the index of the group, which corresponds to a specific packed position.
     *
     * @param packedPosition
     *         The packed position of the group, whose index should be returned, as an {@link
     *         Integer} value
     * @return The index of the group, which corresponds to the given packed position, as an {@link
     * Integer} value or -1, if the packed position does not correspond to a group
     */
    public static int getPackedPositionGroup(final long packedPosition) {
        if (packedPosition == PACKED_POSITION_VALUE_NULL) {
            return -1;
        }

        return (int) ((packedPosition & PACKED_POSITION_MASK_GROUP) >> PACKED_POSITION_SHIFT_GROUP);
    }

    /**
     * Returns the index of the child, which corresponds to a specific packed position.
     *
     * @param packedPosition
     *         The packed position of the child, whose index should be returned, as an {@link
     *         Integer} value
     * @return The index of the child, which corresponds to the given packed position, as an {@link
     * Integer} value or -1, if the packed position does not correspond to a child
     */
    public static int getPackedPositionChild(final long packedPosition) {
        if (packedPosition == PACKED_POSITION_VALUE_NULL ||
                (packedPosition & PACKED_POSITION_MASK_TYPE) != PACKED_POSITION_MASK_TYPE) {
            return -1;
        }

        return (int) (packedPosition & PACKED_POSITION_MASK_CHILD);
    }

    /**
     * Returns the packed position of a group, which corresponds to a specific index.
     *
     * @param groupIndex
     *         The index of the group, whose packed position should be returned, as an {@link
     *         Integer} value
     * @return The packed position of the group, which corresponds to the given index, as an {@link
     * Integer} value
     */
    public static long getPackedPositionForGroup(final int groupIndex) {
        return ((((long) groupIndex) & PACKED_POSITION_INT_MASK_GROUP) <<
                PACKED_POSITION_SHIFT_GROUP);
    }

    /**
     * Returns the packed position of a child, which corresponds to a specific group and child
     * index.
     *
     * @param groupIndex
     *         The index of the group, the child, whose packed position should be returned, belongs
     *         to, as an {@link Integer} value
     * @param childIndex
     *         The index of the child, whose packed position should be returned, as an {@link
     *         Integer} value
     * @return The packed position of the child, which corresponds to the given group and child
     * index, as an {@link Integer} value
     */
    public static long getPackedPositionForChild(final int groupIndex, final int childIndex) {
        return (((long) PACKED_POSITION_TYPE_CHILD) << PACKED_POSITION_SHIFT_TYPE) |
                ((((long) groupIndex) & PACKED_POSITION_INT_MASK_GROUP) <<
                        PACKED_POSITION_SHIFT_GROUP) | childIndex;
    }

    /**
     * Sets the adapter that provides data to this view.
     *
     * @param adapter
     *         The adapter, which should be set, as an instance of the type {@link
     *         ExpandableListAdapter} or null, if no adapter should be set
     */
    public final void setAdapter(@Nullable final ExpandableListAdapter adapter) {
        expandedGroups.clear();

        if (adapter != null) {
            this.adapter = new AdapterWrapper(adapter);
            super.setAdapter(this.adapter);
        } else {
            this.adapter = null;
            super.setAdapter(null);
        }
    }

    /**
     * Returns the adapter that provides data to this view.
     *
     * @return The adapter that provides data to this view as an instance of the type {@link
     * ExpandableListAdapter} or null, if no adapter has been set.
     */
    public ExpandableListAdapter getExpandableListAdapter() {
        return adapter != null ? adapter.getEncapsulatedAdapter() : null;
    }

    /**
     * Returns, whether the group, which corresponds to a specific index, is currently expanded, or
     * not.
     *
     * @param groupIndex
     *         The index, of the group, whose expansion should be checked, as an {@link Integer}
     *         value
     * @return True, if the group is currently expanded, false otherwise
     */
    public final boolean isGroupExpanded(final int groupIndex) {
        return expandedGroups.contains(groupIndex);
    }

    /**
     * Expands the group, which corresponds to a specific index.
     *
     * @param groupIndex
     *         The index of the group, which should be expanded, as an {@link Integer} value
     * @return True, if the group has been expanded, false otherwise
     */
    public final boolean expandGroup(final int groupIndex) {
        ExpandableListAdapter adapter = getExpandableListAdapter();

        if (adapter != null && !isGroupExpanded(groupIndex)) {
            expandedGroups.add(groupIndex);
            notifyDataSetChanged();
            return true;
        }

        return false;
    }

    /**
     * Collapses the group, which corresponds to a specific index.
     *
     * @param groupIndex
     *         The index of the group, which should be collapsed, as an {@link Integer} value
     * @return True if the group has been collapsed, false otherwise
     */
    public final boolean collapseGroup(final int groupIndex) {
        ExpandableListAdapter adapter = getExpandableListAdapter();

        if (adapter != null && isGroupExpanded(groupIndex)) {
            expandedGroups.remove(groupIndex);
            notifyDataSetChanged();
            return true;
        }

        return false;
    }

    /**
     * Sets the listener, which should be notified, when a group has been clicked.
     *
     * @param listener
     *         The listener, which should be set, as an instance of the type {@link
     *         OnGroupClickListener} or null, if no listener should be notified
     */
    public final void setOnGroupClickListener(@Nullable final OnGroupClickListener listener) {
        this.groupClickListener = listener;
    }

    /**
     * Sets the listener, which should be notified, whn a child has been clicked.
     *
     * @param listener
     *         The listener, which should be set, as an instance of the type {@link
     *         OnChildClickListener} or null, if no listener should be notified
     */
    public final void setOnChildClickListener(@Nullable final OnChildClickListener listener) {
        this.childClickListener = listener;
    }

    @Override
    public final void setOnItemClickListener(@Nullable final OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    public final void setOnItemLongClickListener(@Nullable final OnItemLongClickListener listener) {
        this.itemLongClickListener = listener;
    }

    @Override
    public final void setAdapter(@Nullable final ListAdapter adapter) {
        expandedGroups.clear();
        super.setAdapter(adapter);
    }

}