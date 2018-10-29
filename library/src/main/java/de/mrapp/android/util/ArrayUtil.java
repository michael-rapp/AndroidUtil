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
package de.mrapp.android.util;

import androidx.annotation.NonNull;
import de.mrapp.util.Condition;

/**
 * An utility class, which provides static methods, which allow to handle arrays.
 *
 * @author Michael Rapp
 * @since 1.11.0
 */
public final class ArrayUtil {

    /**
     * Creates a new utility class, which provides static methods, which allow to handle arrays.
     */
    private ArrayUtil() {

    }

    /**
     * Returns the index of the first item of an array, which equals a specific boolean value.
     *
     * @param array
     *         The array, which should be checked, as a {@link Boolean} array. The array may not be
     *         null
     * @param value
     *         The value, which should be checked, as a {@link Boolean} value
     * @return The index of the first item, which equals the given boolean value, as an {@link
     * Integer} value or -1, if no item of the array equals the value
     */
    public static int indexOf(@NonNull final boolean[] array, final boolean value) {
        Condition.INSTANCE.ensureNotNull(array, "The array may not be null");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the index of the last item of an array, which equals a specific boolean value.
     *
     * @param array
     *         The array, which should be checked, as a {@link Boolean} array. The array may not be
     *         null
     * @param value
     *         The value, which should be checked, as a {@link Boolean} value
     * @return The index of the last item, which equals the given boolean value, as an {@link
     * Integer} value or -1, if no item of the array equals the value
     */
    public static int lastIndexOf(@NonNull final boolean[] array, final boolean value) {
        Condition.INSTANCE.ensureNotNull(array, "The array may not be null");
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == value) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns, whether an array contains a specific boolean value, or not.
     *
     * @param array
     *         The array, which should be checked, as a {@link Boolean} array. The array may not be
     *         null
     * @param value
     *         The value, which should be checked, as a {@link Boolean} value
     * @return True, if the array contains the given boolean value, false otherwise
     */
    public static boolean contains(@NonNull final boolean[] array, final boolean value) {
        return indexOf(array, value) != -1;
    }

}