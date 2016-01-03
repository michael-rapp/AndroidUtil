/*
 * AndroidUtil Copyright 2015 Michael Rapp
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
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Constructor;

/**
 * An utility class, which provides static methods, which allow to ensure, that variables and
 * objects fulfill certain conditions. If a condition is not violated, an exception is thrown by
 * each of these methods.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public final class Condition {

    /**
     * Creates a new utility class, which provides static methods, hwich allows to ensure, that
     * variables and objects fulfill certain conditions.
     */
    private Condition() {

    }

    /**
     * Throws a specific runtime exception. The exception is instantiated using reflection and must
     * provide a constructor, which expects exactly one {@link String} parameter. If instantiating
     * the exception fails, a {@link RuntimeException} is thrown instead.
     *
     * @param exceptionMessage
     *         The message of the exception, which should be thrown, as a {@link String} or null, if
     *         the exception should not have a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, as an instance of the
     *         class {@link Class}. The class may not be null
     */
    private static void throwException(@Nullable final String exceptionMessage,
                                       @NonNull final Class<? extends RuntimeException> exceptionClass) {
        RuntimeException exception;

        try {
            Constructor<? extends RuntimeException> constructor =
                    exceptionClass.getConstructor(String.class);
            exception = constructor.newInstance(exceptionMessage);
        } catch (Exception e) {
            exception = new RuntimeException(exceptionMessage);
        }

        throw exception;
    }

    /**
     * Ensures that an object is not null. Otherwise a {@link NullPointerException} with a specific
     * message is thrown.
     *
     * @param object
     *         The object, which should be checked, as an instance of the class {@link Object}
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given object is null, as a
     *         {@link String} or null, if the exception should not have a message
     */
    public static void ensureNotNull(final Object object, @Nullable final String exceptionMessage) {
        if (object == null) {
            throw new NullPointerException(exceptionMessage);
        }
    }

    /**
     * Ensures that an object is not null. Otherwise a specific {@link RuntimeException} is thrown.
     *
     * @param object
     *         The object, which should be checked, as an instance of the class {@link Object}
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given object is null, as a
     *         {@link String} or null, if the exception should not have a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given object is
     *         null, as an instance of the class {@link Class}. The class may not be null
     */
    public static void ensureNotNull(final Object object, @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (object == null) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a text is not empty. Otherwise an {@link IllegalArgumentException} with a
     * specific message is thrown.
     *
     * @param text
     *         The text, which should be checked, as an instance of the type {@link CharSequence}
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given text is empty, as a
     *         {@link String} or null, if the exception should not have a message
     */
    public static void ensureNotEmpty(final CharSequence text,
                                      @Nullable final String exceptionMessage) {
        if (TextUtils.isEmpty(text)) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a text is not empty. Otherwise a specific {@link RuntimeException} is thrown.
     *
     * @param text
     *         The text, which should be checked, as an instance of the type {@link CharSequence}
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given text is empty, as a
     *         {@link String} or null, if the exception should not have a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given text is
     *         empty, as an instance of the class {@link Class}. The class may not be null
     */
    public static void ensureNotEmpty(final CharSequence text,
                                      @Nullable final String exceptionMessage,
                                      @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (TextUtils.isEmpty(text)) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Short} value is at least as great as a reference value. Otherwise an
     * {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link Short}
     *         value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtLeast(final short value, final short referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value < referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Short} value is at least as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link Short}
     *         value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtLeast(final short value, final short referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is at least as great as a reference value. Otherwise
     * an {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as an {@link
     *         Integer} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtLeast(final int value, final int referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value < referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is at least as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as an {@link
     *         Integer} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtLeast(final int value, final int referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Long} value is at least as great as a reference value. Otherwise an
     * {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link Long}
     *         value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtLeast(final long value, final long referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value < referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Long} value is at least as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link Long}
     *         value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtLeast(final long value, final long referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Float} value is at least as great as a reference value. Otherwise an
     * {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link Float}
     *         value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtLeast(final float value, final float referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value < referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Float} value is at least as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link Float}
     *         value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtLeast(final float value, final float referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Double} value is at least as great as a reference value. Otherwise an
     * {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link
     *         Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtLeast(final double value, final double referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value < referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Double} value is at least as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be at least as great as, as a {@link
     *         Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtLeast(final double value, final double referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Short} value is at maximum as great as a reference value. Otherwise an
     * {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Short} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtMaximum(final short value, final short referenceValue,
                                       @Nullable final String exceptionMessage) {
        if (value > referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Short} value is at maximum as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Short} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtMaximum(final short value, final short referenceValue,
                                       @Nullable final String exceptionMessage,
                                       @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is at maximum as great as a reference value. Otherwise
     * an {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as an {@link
     *         Integer} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtMaximum(final int value, final int referenceValue,
                                       @Nullable final String exceptionMessage) {
        if (value > referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is at maximum as great as a reference value. Otherwise
     * a specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Short} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtMaximum(final int value, final int referenceValue,
                                       @Nullable final String exceptionMessage,
                                       @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Long} value is at maximum as great as a reference value. Otherwise an
     * {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Long} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtMaximum(final long value, final long referenceValue,
                                       @Nullable final String exceptionMessage) {
        if (value > referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Long} value is at maximum as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Long} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtMaximum(final long value, final long referenceValue,
                                       @Nullable final String exceptionMessage,
                                       @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Float} value is at maximum as great as a reference value. Otherwise an
     * {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Float} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtMaximum(final float value, final float referenceValue,
                                       @Nullable final String exceptionMessage) {
        if (value > referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Float} value is at maximum as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Float} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtMaximum(final float value, final float referenceValue,
                                       @Nullable final String exceptionMessage,
                                       @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Double} value is at maximum as great as a reference value. Otherwise
     * an {@link IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureAtMaximum(final double value, final double referenceValue,
                                       @Nullable final String exceptionMessage) {
        if (value > referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Double} value is at maximum as great as a reference value. Otherwise a
     * specific {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be at maximum as great as, as a {@link
     *         Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater as the
     *         reference value, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater as the reference value, as an instance of the class {@link Class}. The class
     *         may not be null
     */
    public static void ensureAtMaximum(final double value, final double referenceValue,
                                       @Nullable final String exceptionMessage,
                                       @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Short} value is greater as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Short} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureGreater(final short value, final short referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value <= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Short} value is greater as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Short} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureGreater(final short value, final short referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is greater as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as an {@link Integer} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureGreater(final int value, final int referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value <= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is greater as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as an {@link Integer} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureGreater(final int value, final int referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Long} value is greater as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Long} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureGreater(final long value, final long referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value <= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Long} value is greater as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Long} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureGreater(final long value, final long referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Float} value is greater as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Float} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureGreater(final float value, final float referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value <= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Float} value is greater as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Float} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureGreater(final float value, final float referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Double} value is greater as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureGreater(final double value, final double referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value <= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Double} value is greater as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be greater as, as a {@link Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is smaller or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         smaller or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureGreater(final double value, final double referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Short} value is smaller as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Short} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureSmaller(final short value, final short referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value >= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Short} value is smaller as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Short} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Short} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureSmaller(final short value, final short referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is smaller as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as an {@link Integer} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureSmaller(final int value, final int referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value >= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that an {@link Integer} value is smaller as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as an {@link Integer} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as an {@link Integer} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureSmaller(final int value, final int referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Long} value is smaller as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Long} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureSmaller(final long value, final long referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value >= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Long} value is smaller as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Long} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Long} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureSmaller(final long value, final long referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Float} value is smaller as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Float} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureSmaller(final float value, final float referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value >= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Float} value is smaller as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Float} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Float} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureSmaller(final float value, final float referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a {@link Double} value is smaller as a reference value. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     */
    public static void ensureSmaller(final double value, final double referenceValue,
                                     @Nullable final String exceptionMessage) {
        if (value >= referenceValue) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a {@link Double} value is smaller as a reference value. Otherwise a specific
     * {@link RuntimeException} is thrown.
     *
     * @param value
     *         The value, which should be checked, as a {@link Double} value
     * @param referenceValue
     *         The reference value, the given value must be smaller as, as a {@link Double} value
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given value is greater or equal
     *         as the reference value, as a {@link String} or null, if the exception should not have
     *         a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given value is
     *         greater or equal as the reference value, as an instance of the class {@link Class}.
     *         The class may not be null
     */
    public static void ensureSmaller(final double value, final double referenceValue,
                                     @Nullable final String exceptionMessage,
                                     @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a specific file exists. Otherwise an {@link IllegalArgumentException} with a
     * specific message is thrown.
     *
     * @param file
     *         The file, which should be checked, as an instance of the class {@link File}. The file
     *         may not be null
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given file does not exist, as a
     *         {@link String} or null, if the exception should not have a message
     */
    public static void ensureFileExists(final File file, final String exceptionMessage) {
        if (!file.exists()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a specific file exists. Otherwise a specific {@link RuntimeException} is
     * thrown.
     *
     * @param file
     *         The file, which should be checked, as an instance of the class {@link File}. The file
     *         may not be null
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given file does not exist, as a
     *         {@link String} or null, if the exception should not have a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given file does
     *         not exist, as an instance of the class {@link Class}. The class may not be null
     */
    public static void ensureFileExists(@NonNull final File file,
                                        @Nullable final String exceptionMessage,
                                        @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (!file.exists()) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a specific file is a directory. Otherwise an {@link IllegalArgumentException}
     * with a specific message is thrown.
     *
     * @param file
     *         The file, which should be checked, as an instance of the class {@link File}. The file
     *         may not be null
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given file is not a directory,
     *         as a {@link String} or null, if the exception should not have a message
     */
    public static void ensureFileIsDirectory(@NonNull final File file,
                                             @Nullable final String exceptionMessage) {
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a specific file is a directory. Otherwise a specific {@link RuntimeException}
     * is thrown.
     *
     * @param file
     *         The file, which should be checked, as an instance of the class {@link File}. The file
     *         may not be null
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given file is not a directory,
     *         as a {@link String} or null, if the exception should not have a message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given file is not
     *         a directory, as an instance of the class {@link Class}. The class may not be null
     */
    public static void ensureFileIsDirectory(@NonNull final File file,
                                             @Nullable final String exceptionMessage,
                                             @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (!file.isDirectory()) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

    /**
     * Ensures, that a specific file does exist and is not a directory. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param file
     *         The file, which should be checked, as an instance of the class {@link File}. The file
     *         may not be null
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given file is a directory or
     *         does not exist, as a {@link String} or null, if the exception should not have a
     *         message
     */
    public static void ensureFileIsNoDirectory(@NonNull final File file,
                                               @Nullable final String exceptionMessage) {
        if (!file.isFile()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Ensures, that a specific file does exist and is not a directory. Otherwise an {@link
     * IllegalArgumentException} with a specific message is thrown.
     *
     * @param file
     *         The file, which should be checked, as an instance of the class {@link File}. The file
     *         may not be null
     * @param exceptionMessage
     *         The message of the exception, which is thrown, if the given file is a directory or
     *         does not exist, as a {@link String} or null, if the exception should not have a
     *         message
     * @param exceptionClass
     *         The class of the runtime exception, which should be thrown, if the given file is a
     *         directory or does not exist, as an instance of the class {@link Class}. The class may
     *         not be null
     */
    public static void ensureFileIsNoDirectory(@NonNull final File file,
                                               @Nullable final String exceptionMessage,
                                               @NonNull final Class<? extends RuntimeException> exceptionClass) {
        if (!file.isFile()) {
            throwException(exceptionMessage, exceptionClass);
        }
    }

}