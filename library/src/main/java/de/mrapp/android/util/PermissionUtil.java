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

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.LinkedList;
import java.util.List;

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
     * Defines the interface, a callback, which should be notified, when an explanation should be
     * shown before requesting a permission, must implement.
     */
    public interface Callback {

        /**
         * The method, which is invoked, when an explanation should be shown to the user before
         * requesting a permission. After showing the explanation, the permission should be
         * requested again.
         *
         * @param activity
         *         The activity, which should be used to show the explanation, as an instance of the
         *         class {@link Activity}
         * @param requestCode
         *         The request code, which is used to request the permission, as an {@link Integer}
         *         value
         * @param permission
         *         The permission, e.g. <code>android.Manifest.permission.CALL_PHONE</code>, which
         *         should be explained, as a {@link String}
         */
        void onShouldShowExplanation(@NonNull Activity activity, int requestCode,
                                     @NonNull String permission);

    }

    /**
     * Returns, whether an explanation should be shown to the user, before requesting a specific
     * permission.
     *
     * @param activity
     *         The activity, which should be used to show the explanation, as an instance of the
     *         class {@link Activity}. The activity may not be null
     * @param permission
     *         The permission, which should be requested, as a {@link String}, e.g.
     *         <code>android.Manifest.permission.CALL_PHONE</code>. The permission may neither be
     *         null, nor empty
     * @return True, if an explanation should be shown to the user, when requesting the given
     * permission, false otherwise
     */
    private static boolean shouldShowExplanation(@NonNull final Activity activity,
                                                 @NonNull final String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

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
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests a specific permission, if it has not already been granted by the user or at
     * installation-time, by showing a dialog to the user. The given activity must implement the
     * interface <code>ActivityCompat.OnRequestPermissionsResultCallback</code> as, after the user
     * has accepted or rejected a permission, its <code>onRequestPermissionsResultCallback</code>-method
     * will be invoked in order to deliver the result of the permission request. If an explanation
     * should be shown to the user before requesting the permission and a callback is given, the
     * callback will be notified instead of requesting the permission.
     *
     * @param activity
     *         The activity, which should handle the result of the permission request, as an
     *         instance of the class {@link Activity}. The activity may not be null and must
     *         implement the interface <code>ActivityCompat.OnRequestPermissionsResultCallback</code>
     * @param requestCode
     *         The request code, which should be used, as an {@link Integer} value
     * @param callback
     *         The callback, which should be notified when an explanation should be shown to the
     *         user before requesting the permission, as an instance of the type {@link
     *         PermissionUtil.Callback}, or null, if no explanation should be shown
     * @param permission
     *         The permission, e.g. <code>android.Manifest.permission.CALL_PHONE</code>, which
     *         should be requested, as a {@link String}. The permission may neither be null, nor
     *         empty
     */
    public static void requestPermission(@NonNull final Activity activity, final int requestCode,
                                         @Nullable final Callback callback,
                                         @NonNull final String permission) {
        requestPermissions(activity, requestCode, callback, permission);
    }

    /**
     * Requests a specific set of permissions, if they have not already been granted by the user or
     * at installation-time, by showing a dialog to the user. The given activity must implement the
     * interface <code>ActivityCompat.OnRequestPermissionsResultCallback</code> as, after the user
     * has accepted or rejected a permission, its <code>onRequestPermissionsResultCallback</code>-method
     * will be invoked in order to deliver the result of the permission request. If an explanation
     * should be shown to the user before requesting a permission and a callback is given, the
     * callback will be notified instead of requesting the permission.
     *
     * @param activity
     *         The activity, which should handle the result of the permission request, as an
     *         instance of the class {@link Activity}. The activity may not be null and must
     *         implement the interface <code>ActivityCompat.OnRequestPermissionsResultCallback</code>
     * @param requestCode
     *         The request code, which should be used, as an {@link Integer} value
     * @param callback
     *         The callback, which should be notified when an explanation should be shown to the
     *         user before requesting a permission, as an instance of the type {@link
     *         PermissionUtil.Callback}, or null, if no explanations should be shown
     * @param permissions
     *         The permissions, e.g. <code>android.Manifest.permission.CALL_PHONE</code>, which
     *         should be requested, as a {@link String} array or an empty array, if no permissions
     *         should be requested
     */
    public static void requestPermissions(@NonNull final Activity activity, final int requestCode,
                                          @Nullable final Callback callback,
                                          @NonNull final String... permissions) {
        ensureNotNull(activity, "The activity may not be null");
        ensureNotNull(permissions, "The permissions may not be null");
        List<String> notGrantedPermissions = new LinkedList<>();

        for (String permission : permissions) {
            ensureNotNull(permission, "The permission may not be null");
            ensureNotEmpty(permission, "The permission may not be empty");

            if (!isPermissionGranted(activity, permission)) {
                notGrantedPermissions.add(permission);
            }
        }

        if (callback != null) {
            for (int i = notGrantedPermissions.size() - 1; i >= 0; i--) {
                String permission = notGrantedPermissions.get(i);

                if (shouldShowExplanation(activity, permission)) {
                    callback.onShouldShowExplanation(activity, requestCode, permission);
                    notGrantedPermissions.remove(i);
                }
            }
        }

        String[] array = new String[notGrantedPermissions.size()];
        ActivityCompat.requestPermissions(activity, array, requestCode);
    }

}