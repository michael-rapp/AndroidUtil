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
package de.mrapp.android.util.datastructure;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.*;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * A list, which is meant to be used for managing event listeners. The list ensures, that no
 * duplicates or null elements can be added. For comparing listeners either the {@link
 * Object#equals(Object)} method or the identity operator (==) can be used. Furthermore, this class
 * is thread-safe and no {@link ConcurrentModificationException}s are thrown when adding or removing
 * listeners while iterating the list.
 * <p>
 * This is list is neither serializable, nor does it override the {@link Object#equals(Object)} or
 * {@link Object#hashCode()} method.
 *
 * @author Michael Rapp
 * @since 1.2.0
 */
public class ListenerList<T> implements Iterable<T> {

    /**
     * Contains all possible methods for comparing listeners with each other.
     */
    public enum CompareMethod {

        /**
         * If listeners should be compared using the method {@link Object#equals(Object)}.
         */
        EQUALITY,

        /**
         * If listeners should be compared using the identity (==) operator.
         */
        IDENTITY

    }

    /**
     * The object, which is used for locking the list.
     */
    private final Object lock = new Object();

    /**
     * The compare method, which is used by the list.
     */
    private final CompareMethod compareMethod;

    /**
     * The listeners, which are currently contained by the list.
     */
    private List<T> listeners;

    /**
     * Returns, whether an iterable contains a specific listener according to the used compare
     * method, or not.
     *
     * @param iterable
     *         The iterable as an instance of the type {@link Iterable}. The iterable may not be
     *         null
     * @param listener
     *         The listener, which should be checked, as an instance of the generic type {@link T}.
     *         The listener may not be null
     * @return True, if the iterable contains the given listener, false otherwise
     */
    private boolean contains(@NonNull final Iterable<? extends T> iterable,
                             @NonNull final T listener) {
        for (T t : iterable) {
            if (equals(t, listener)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns, whether two listeners are equal according to the used compare method.
     *
     * @param listener1
     *         The first listener as an instance of the generic type {@link T} or null
     * @param listener2
     *         The second listener as an instance of the generic type {@link T} or null
     * @return True, if the given listeners are equal, false otherwise
     */
    private boolean equals(@Nullable final T listener1, @Nullable final T listener2) {
        if (listener1 == null) {
            return listener2 == null;
        } else
            return listener2 != null &&
                    (compareMethod == CompareMethod.EQUALITY ? listener1.equals(listener2) :
                            listener1 == listener2);
    }

    /**
     * Creates a new list, which is meant to be used for managing event listeners. In order to
     * prevent duplicates, listeners are compared using the {@link Object#equals(Object)} method.
     */
    public ListenerList() {
        this(CompareMethod.EQUALITY);
    }

    /**
     * Creates a new list, which is meant to be used for managing event listeners. In order to
     * prevent duplicates, a specific compare method is used.
     *
     * @param compareMethod
     *         The method, which should be used for comparing listeners to each other, as a value of
     *         the enum {@link CompareMethod}. The compare method may not be null
     */
    public ListenerList(@NonNull final CompareMethod compareMethod) {
        ensureNotNull(compareMethod, "The compare method may not be null");
        this.compareMethod = compareMethod;
        clear();
    }

    /**
     * Returns the compare method, which is used by the list.
     *
     * @return The compare method, which is used by the list, as a value of the enum {@link
     * CompareMethod}. The compare method may not be null
     */
    @NonNull
    public final CompareMethod getCompareMethod() {
        return compareMethod;
    }

    /**
     * Returns, whether the list is empty, or not.
     *
     * @return True, if the list is empty, false otherwise
     */
    public final boolean isEmpty() {
        synchronized (lock) {
            return listeners.isEmpty();
        }
    }

    /**
     * Returns the number of listeners, which are contained by the list.
     *
     * @return The number of listeners, which are contained by the list, as an {@link Integer} value
     */
    public final int size() {
        synchronized (lock) {
            return listeners.size();
        }
    }

    /**
     * Adds a new listener to the list.
     *
     * @param listener
     *         The listener, which should be added, as an instance of the generic type {@link T}.
     *         The listener may not be null
     * @return True, if the listener has been added, or false, if the listener was already contained
     * by the list
     */
    public final boolean add(@NonNull final T listener) {
        ensureNotNull(listener, "The listener may not be null");

        synchronized (lock) {
            if (!contains(this.listeners, listener)) {
                List<T> newList = new LinkedList<>(this.listeners);
                newList.add(listener);
                this.listeners = newList;
                return true;
            }

            return false;
        }
    }

    /**
     * Adds all listeners, which can be iterated using a specific iterable, to the list.
     *
     * @param iterable
     *         The iterable as an instance of the type {@link Iterable}. The iterable may not be
     *         null
     */
    public final void addAll(@NonNull final Iterable<? extends T> iterable) {
        ensureNotNull(iterable, "The iterable may not be null");

        synchronized (lock) {
            List<T> newList = null;

            for (T listener : iterable) {
                ensureNotNull(listener, "The listener may not be null");

                if (newList == null ? !contains(this.listeners, listener) :
                        !contains(newList, listener)) {
                    if (newList == null) {
                        newList = new LinkedList<>(this.listeners);
                    }

                    newList.add(listener);
                }
            }

            if (newList != null) {
                this.listeners = newList;
            }
        }
    }

    /**
     * Removes a specific listener from the list.
     *
     * @param listener
     *         The listener, which should be removed, as an instance of the generic type {@link T}.
     *         The listener may not be null
     * @return True, if the listener has been removed, or false, if the listener is not contained by
     * the list
     */
    public final boolean remove(@NonNull final T listener) {
        ensureNotNull(listener, "The listener may not be null");

        synchronized (lock) {
            if (contains(this.listeners, listener)) {
                List<T> newList = new LinkedList<>();

                for (T t : this.listeners) {
                    if (!equals(t, listener)) {
                        newList.add(t);
                    }
                }

                this.listeners = newList;
                return true;
            }

            return false;
        }
    }

    /**
     * Removes all listeners, which can be iterated using a specific iterable.
     *
     * @param iterable
     *         The iterable as an instance of the type {@link Iterable}. The iterable may not be
     *         null
     */
    public final void removeAll(@NonNull final Iterable<? extends T> iterable) {
        ensureNotNull(iterable, "The iterable may not be null");

        synchronized (lock) {
            List<T> newList = null;

            for (T listener : this.listeners) {
                if (!contains(iterable, listener)) {
                    if (newList == null) {
                        newList = new LinkedList<>();
                    }

                    newList.add(listener);
                }
            }

            if (newList != null) {
                this.listeners = newList;
            }
        }
    }

    /**
     * Removes all listeners from the list.
     */
    public final void clear() {
        synchronized (lock) {
            this.listeners = Collections.emptyList();
        }
    }

    /**
     * Returns a collection, which contains all listeners, which are currently contained by the
     * list. The returned collection is a snapshot of the current state and is unmodifiable.
     *
     * @return A collection, which contains all listeners, which are currently contained by the
     * list, as an instance of the type {@link Collection}. The collection may not be null
     */
    @NonNull
    public Collection<T> getAll() {
        synchronized (lock) {
            if (isEmpty()) {
                return Collections.emptyList();
            } else {
                Collection<T> collection = new LinkedList<>(this.listeners);
                return Collections.unmodifiableCollection(collection);
            }
        }
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return listeners.iterator();
    }

}