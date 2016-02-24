/*
 * Copyright 2015 - 2016 Michael Rapp
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

import android.support.annotation.NonNull;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods for handling instances of the class {@link
 * Class}.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public final class ClassUtil {

    /**
     * Creates a new utility class, which provides static methods for handling instances of the
     * class {@link Class}.
     */
    private ClassUtil() {

    }

    /**
     * Returns a truncated version of a specific class' full qualified name. For example, if the
     * full qualified name of the class is 'com.xyz.Abc', the result will be 'c.x.Abc'.
     *
     * @param clazz
     *         The class, whose full qualified name should be turned into a truncated version, as an
     *         instance of the class {@link Class}. The class may not be null
     * @return The truncated version of the given class' full qualified name as a {@link String}
     */
    public static String getTruncatedName(@NonNull final Class<?> clazz) {
        ensureNotNull(clazz, "The class may not be null");
        String fullQualifiedName = clazz.getName();
        String[] qualifiers = fullQualifiedName.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < qualifiers.length; i++) {
            if (i != qualifiers.length - 1) {
                stringBuilder.append(qualifiers[i].substring(0, 1));
                stringBuilder.append(".");
            } else {
                stringBuilder.append(qualifiers[i]);
            }
        }

        return stringBuilder.toString();
    }

}