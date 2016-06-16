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

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import static de.mrapp.android.util.Condition.ensureNotEmpty;
import static de.mrapp.android.util.Condition.ensureNotNull;

/**
 * An utility class, which provides static methods, which allow to handle runtime permissions.
 *
 * @author Michael Rapp
 * @since 1.3.0
 */
public final class PermissionUtil {

    /**
     * Creates a new utility class, which provides static methods, which allow to handle runtime
     * permissions.
     */
    private PermissionUtil() {

    }

    /**
     * Returns, whether a specific permission is granted, or not.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param permission
     *         The permission, which should be checked, as a {@link String}, e.g.
     *         <code>android.Manifest.permission.CALL_PHONE</code>. The permission may neither be
     *         null, nor empty
     * @return True, if the given permission is granted, false otherwise
     */
    public static boolean isPermissionGranted(@NonNull final Context context,
                                              @NonNull final String permission) {
        ensureNotNull(context, "The context may not be null");
        ensureNotNull(permission, "The permission may not be null");
        ensureNotEmpty(permission, "The permission may not be empty");
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                ContextCompat.checkSelfPermission(context, permission) ==
                        PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Returns, whether all permissions, which are contained by a specific array, are granted, or
     * not.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param permissions
     *         An array, which contains the permissions, e.g. <code>android.Manifest.permission.CALL_PHONE</code>,
     *         which should be checked, as a {@link String} array. The array may not be null
     * @return True, if all given permissions are granted, false otherwise
     */
    public static boolean areAllPermissionsGranted(@NonNull final Context context,
                                                   @NonNull final String... permissions) {
        ensureNotNull(context, "The context may not be null");
        ensureNotNull(permissions, "The array may not be null");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (!isPermissionGranted(context, permission)) {
                    return false;
                }
            }
        }

        return true;
    }

}