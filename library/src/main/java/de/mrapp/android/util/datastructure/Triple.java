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

import android.support.annotation.Nullable;

/**
 * A data structure, which eases to pass around a triple of three objects. This object provides a
 * sensible implementation of equals(), returning true if equals() is true on each of the contained
 * objects.
 *
 * @param <F>
 *         The type of the first object
 * @param <S>
 *         The type of the second object
 * @param <T>
 *         The type of the third object
 * @author Michael Rapp
 * @since 1.4.4
 */
public class Triple<F, S, T> {

    /**
     * The first object.
     */
    public final F first;

    /**
     * The second object.
     */
    public final S second;

    /**
     * The third object.
     */
    public final T third;

    /**
     * Creates a new triple.
     *
     * @param first
     *         The first object as an instance of the generic type F
     * @param second
     *         The second object as an instance of the generic type S
     * @param third
     *         The third object as an instance of the generic type T
     */
    public Triple(@Nullable final F first, @Nullable final S second, @Nullable final T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Creates a new triple.
     *
     * @param <F>
     *         The type of the first object
     * @param <S>
     *         The type of the second object
     * @param <T>
     *         The type of the third object
     * @param first
     *         The first object
     * @param second
     *         The second object
     * @param third
     *         The third object
     * @return The triple, which has been created, as an instance of the class {@link Triple}
     */
    public static <F, S, T> Triple<F, S, T> create(F first, S second, T third) {
        return new Triple<>(first, second, third);
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        result = prime * result + ((third == null) ? 0 : third.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
        if (first == null) {
            if (other.first != null)
                return false;
        } else if (!first.equals(other.first))
            return false;
        if (second == null) {
            if (other.second != null)
                return false;
        } else if (!second.equals(other.second))
            return false;
        if (third == null) {
            if (other.third != null)
                return false;
        } else if (!third.equals(other.third))
            return false;
        return true;
    }

}