/*
 * AndroidUtil Copyright 2015 - 2016 Michael Rapp
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
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