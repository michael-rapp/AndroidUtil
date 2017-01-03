/*
 * Copyright 2015 - 2017 Michael Rapp
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

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;

import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods for retrieving theme attributes.
 *
 * @author Michael Rapp
 * @since 1.4.8
 */
public final class ThemeUtil {

    /**
     * Obtains the attribute, which corresponds to a specific resource id, from a theme.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return A type array, which holds the attribute, which has been obtained, as an instance of
     * the class {@link TypedArray}
     */
    private static TypedArray obtainStyledAttributes(@NonNull final Context context,
                                                     @StyleRes final int themeResourceId,
                                                     @AttrRes final int resourceId) {
        ensureNotNull(context, "The context may not be null");
        Theme theme = context.getTheme();
        int[] attrs = new int[]{resourceId};

        if (themeResourceId != -1) {
            return theme.obtainStyledAttributes(themeResourceId, attrs);
        } else {
            return theme.obtainStyledAttributes(attrs);
        }
    }

    /**
     * Creates a new utility class, which provides static methods for retrieving theme attributes.
     */
    private ThemeUtil() {

    }

    /**
     * Obtains the color, which corresponds to a specific resource id, from a context's theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The color, which has been obtained, as an {@link Integer} value
     */
    public static int getColor(@NonNull final Context context, @AttrRes final int resourceId) {
        return getColor(context, -1, resourceId);
    }

    /**
     * Obtains the color, which corresponds to a specific resource id, from a specific theme. If the
     * given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The color, which has been obtained, as an {@link Integer} value
     */
    public static int getColor(@NonNull final Context context, @StyleRes final int themeResourceId,
                               @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            int color = typedArray.getColor(0, -1);

            if (color == -1) {
                int id = typedArray.getResourceId(0, -1);
                color = ContextCompat.getColor(context, id);

                if (color == 0) {
                    throw new NotFoundException(
                            "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
                }
            }

            return color;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the color state list, which corresponds to a specific resource id, from a context's
     * theme. If the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The color state list, which has been obtained, as an instance of the class {@link
     * ColorStateList}
     */
    public static ColorStateList getColorStateList(@NonNull final Context context,
                                                   @AttrRes final int resourceId) {
        return getColorStateList(context, -1, resourceId);
    }

    /**
     * Obtains the color state list, which corresponds to a specific resource id, from a specific
     * theme. If the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The color state list, which has been obtained, as an instance of the class {@link
     * ColorStateList}
     */
    public static ColorStateList getColorStateList(@NonNull final Context context,
                                                   @StyleRes final int themeResourceId,
                                                   @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            ColorStateList colorStateList = typedArray.getColorStateList(0);

            if (colorStateList == null) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return colorStateList;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the string, which corresponds to a specific resource id, from a context's theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The string, which has been obtained, as a {@link String}
     */
    public static String getString(@NonNull final Context context, @AttrRes final int resourceId) {
        return getString(context, -1, resourceId);
    }

    /**
     * Obtains the string, which corresponds to a specific resource id, from a specific theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The string, which has been obtained, as a {@link String}
     */
    public static String getString(@NonNull final Context context,
                                   @StyleRes final int themeResourceId,
                                   @AttrRes final int resourceId) {
        ensureNotNull(context, "The context may not be null");
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            String string = typedArray.getString(0);

            if (string == null) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return string;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the text, which corresponds to a specific resource id, from a context's theme. If the
     * given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The text, which has been obtained, as an instance of the type {@link CharSequence}
     */
    public static CharSequence getText(@NonNull final Context context,
                                       @AttrRes final int resourceId) {
        return getText(context, -1, resourceId);
    }

    /**
     * Obtains the text, which corresponds to a specific resource id, from a specific theme. If the
     * given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The text, which has been obtained, as an instance of the type {@link CharSequence}
     */
    public static CharSequence getText(@NonNull final Context context,
                                       @StyleRes final int themeResourceId,
                                       @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            CharSequence text = typedArray.getText(0);

            if (text == null) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return text;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the text array, which corresponds to a specific resource id, from a context's theme.
     * If the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The text array, which has been obtained, as an instance of the type {@link
     * CharSequence}
     */
    public static CharSequence[] getTextArray(@NonNull final Context context,
                                              @AttrRes final int resourceId) {
        return getTextArray(context, -1, resourceId);
    }

    /**
     * Obtains the text array, which corresponds to a specific resource id, from a specific theme.
     * If the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The text array, which has been obtained, as an array of the type {@link CharSequence}
     */
    public static CharSequence[] getTextArray(@NonNull final Context context,
                                              @StyleRes final int themeResourceId,
                                              @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            CharSequence[] textArray = typedArray.getTextArray(0);

            if (textArray == null) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return textArray;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the dimension, which corresponds to a specific resource id, from a context's theme.
     * If the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The dimension, which has been obtained, as a {@link Float} value
     */
    public static float getDimension(@NonNull final Context context,
                                     @AttrRes final int resourceId) {
        return getDimension(context, -1, resourceId);
    }

    /**
     * Obtains the dimension, which corresponds to a specific resource id, from a specific theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The dimension, which has been obtained, as a {@link Float} value
     */
    public static float getDimension(@NonNull final Context context,
                                     @StyleRes final int themeResourceId,
                                     @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            float dimension = typedArray.getDimension(0, -1);

            if (dimension == -1) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return dimension;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the dimension pixel size, which corresponds to a specific resource id, from a
     * context's theme. If the given resource id is invalid, a {@link NotFoundException} will be
     * thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The dimension pixel size, which has been obtained, as an {@link Integer} value
     */
    public static int getDimensionPixelSize(@NonNull final Context context,
                                            @AttrRes final int resourceId) {
        return getDimensionPixelSize(context, -1, resourceId);
    }

    /**
     * Obtains the dimension pixel size, which corresponds to a specific resource id, from a
     * specific theme. If the given resource id is invalid, a {@link NotFoundException} will be
     * thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The dimension pixel size, which has been obtained, as an {@link Integer} value
     */
    public static int getDimensionPixelSize(@NonNull final Context context,
                                            @StyleRes final int themeResourceId,
                                            @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            int dimension = typedArray.getDimensionPixelSize(0, -1);

            if (dimension == -1) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return dimension;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the dimension pixel offset, which corresponds to a specific resource id, from a
     * context's theme. If the given resource id is invalid, a {@link NotFoundException} will be
     * thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The dimension pixel offset, which has been obtained, as an {@link Integer} value
     */
    public static int getDimensionPixelOffset(@NonNull final Context context,
                                              @AttrRes final int resourceId) {
        return getDimensionPixelOffset(context, -1, resourceId);
    }

    /**
     * Obtains the dimension pixel offset, which corresponds to a specific resource id, from a
     * specific theme. If the given resource id is invalid, a {@link NotFoundException} will be
     * thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The dimension pixel offset, which has been obtained, as an {@link Integer} value
     */
    public static int getDimensionPixelOffset(@NonNull final Context context,
                                              @StyleRes final int themeResourceId,
                                              @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            int dimension = typedArray.getDimensionPixelOffset(0, -1);

            if (dimension == -1) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return dimension;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the drawable, which corresponds to a specific resource id, from a context's theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The drawable, which has been obtained, as an instance of the class {@link Drawable}
     */
    public static Drawable getDrawable(@NonNull final Context context,
                                       @AttrRes final int resourceId) {
        return getDrawable(context, -1, resourceId);
    }

    /**
     * Obtains the drawable, which corresponds to a specific resource id, from a specific theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The drawable, which has been obtained, as an instance of the class {@link Drawable}
     */
    public static Drawable getDrawable(@NonNull final Context context,
                                       @StyleRes final int themeResourceId,
                                       @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            Drawable drawable = typedArray.getDrawable(0);

            if (drawable == null) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return drawable;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the fraction, which corresponds to a specific resource id, from a context's theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @param base
     *         The base value of this fraction as an {@link Integer} value. A standard fraction is
     *         multiplied by this value
     * @param pbase
     *         The parent base value of this fraction as an {@link Integer} value. A parent fraction
     *         (nn%p) is multiplied by this value
     * @return The fraction, which has been obtained, as a {@link Float} value
     */
    public static float getFraction(@NonNull final Context context, @AttrRes final int resourceId,
                                    final int base, final int pbase) {
        return getFraction(context, -1, resourceId, base, pbase);
    }

    /**
     * Obtains the fraction, which corresponds to a specific resource id, from a specific theme. If
     * the given resource id is invalid, a {@link NotFoundException} will be thrown.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @param base
     *         The base value of this fraction as an {@link Integer} value. A standard fraction is
     *         multiplied by this value
     * @param pbase
     *         The parent base value of this fraction as an {@link Integer} value. A parent fraction
     *         (nn%p) is multiplied by this value
     * @return The fraction, which has been obtained, as a {@link Float} value
     */
    public static float getFraction(@NonNull final Context context,
                                    @StyleRes final int themeResourceId,
                                    @AttrRes final int resourceId, final int base,
                                    final int pbase) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            float fraction = typedArray.getFraction(0, base, pbase, -1);

            if (fraction == -1) {
                throw new NotFoundException(
                        "Resource ID #0x" + Integer.toHexString(resourceId) + " is not valid");
            }

            return fraction;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the boolean value, which corresponds to a specific resource id, from a context's
     * theme.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The boolean value, which has been obtained, as an {@link Boolean} value or false, if
     * the given resource id is invalid
     */
    public static boolean getBoolean(@NonNull final Context context,
                                     @AttrRes final int resourceId) {
        return getBoolean(context, -1, resourceId);
    }

    /**
     * Obtains the boolean value, which corresponds to a specific resource id, from a specific
     * theme.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The boolean value, which has been obtained, as a {@link Boolean} value or false, if
     * the given resource id is invalid
     */
    public static boolean getBoolean(@NonNull final Context context,
                                     @StyleRes final int themeResourceId,
                                     @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            return typedArray.getBoolean(0, false);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the integer value, which corresponds to a specific resource id, from a context's
     * theme.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The integer value, which has been obtained, as an {@link Integer} value or 0, if the
     * given resource id is invalid
     */
    public static int getInteger(@NonNull final Context context, @AttrRes final int resourceId) {
        return getInteger(context, -1, resourceId);
    }

    /**
     * Obtains the integer value, which corresponds to a specific resource id, from a specific
     * theme.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The integer value, which has been obtained, as an {@link Integer} value or 0, if the
     * given resource id is invalid
     */
    public static int getInteger(@NonNull final Context context,
                                 @StyleRes final int themeResourceId,
                                 @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            return typedArray.getInteger(0, 0);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    /**
     * Obtains the float value, which corresponds to a specific resource id, from a context's
     * theme.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The float value, which has been obtained, as a {@link Float} value or 0, if the given
     * resource id is invalid
     */
    public static float getFloat(@NonNull final Context context, @AttrRes final int resourceId) {
        return getFloat(context, -1, resourceId);
    }

    /**
     * Obtains the float value, which corresponds to a specific resource id, from a specific theme.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param themeResourceId
     *         The resource id of the theme, the attribute should be obtained from, as an {@link
     *         Integer} value or -1, if the attribute should be obtained from the given context's
     *         theme
     * @param resourceId
     *         The resource id of the attribute, which should be obtained, as an {@link Integer}
     *         value. The resource id must corresponds to a valid theme attribute
     * @return The float value, which has been obtained, as a {@link Float} value or 0, if the given
     * resource id is invalid
     */
    public static float getFloat(@NonNull final Context context,
                                 @StyleRes final int themeResourceId,
                                 @AttrRes final int resourceId) {
        TypedArray typedArray = null;

        try {
            typedArray = obtainStyledAttributes(context, themeResourceId, resourceId);
            return typedArray.getFloat(0, 0);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

}