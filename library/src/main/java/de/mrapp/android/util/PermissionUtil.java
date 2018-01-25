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

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.Collection;
import java.util.LinkedList;

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

    /**
     * Returns the subset of specific permissions, which are not granted.
     *
     * @param context
     *         The context, which should be used, as an instance of the class {@link Context}. The
     *         context may not be null
     * @param permissions
     *         An array, which contains the permissions, e.g. <code>android.Manifest.CALL_PHONE</code>,
     *         which should be checked, as a {@link String} array. The array may not be null
     * @return An array, which contains the permissions, which are not granted, as a {@link String}
     * array or an empty array, if all permissions are granted
     */
    @NonNull
    public static String[] getNotGrantedPermissions(@NonNull final Context context,
                                                    @NonNull final String... permissions) {
        ensureNotNull(permissions, "The array may not be null");
        Collection<String> notGrantedPermissions = new LinkedList<>();

        for (String permission : permissions) {
            if (!isPermissionGranted(context, permission)) {
                notGrantedPermissions.add(permission);
            }
        }

        String[] result = new String[notGrantedPermissions.size()];
        notGrantedPermissions.toArray(result);
        return result;
    }

    /**
     * Returns, whether an UI with a rationale for requesting one or multiple permissions should be
     * shown, or not. A rationale should only be shown, if the permissions are not already granted
     * and if the context, in which the permissions are requested, does not clearly communicate to
     * the user what would be the benefit from granting the permissions.
     *
     * For example, if you write a camera app, requesting the camera permission would be expected by
     * the user and no rationale for why it is requested is needed. If however, the app needs
     * location for tagging photos then a non-tech savvy user may wonder how location is related to
     * taking photos. In this case you may choose to show UI with rationale of requesting this
     * permission.
     *
     * @param activity
     *         The activity, which should be used to show the rationale, as an instance of the class
     *         {@link Activity}. The activity may not be null
     * @param permissions
     *         An array, which contains the permissions, e.g. <code>android.Manifest.CALL_PHONE</code>,
     *         which should be requested, as a {@link String} array. The array may not be null
     * @return True, if a rationale should be shown, false otherwise
     */
    public static boolean shouldShowRequestPermissionRationale(@NonNull final Activity activity,
                                                               @NonNull final String... permissions) {
        ensureNotNull(activity, "The activity may not be null");
        ensureNotNull(permissions, "The array may not be null");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    return true;
                }
            }
        }

        return false;
    }

}