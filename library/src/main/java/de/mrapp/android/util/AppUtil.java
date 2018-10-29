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

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import de.mrapp.util.Condition;

/**
 * An utility class, which provides static methods, which allow to start system apps.
 *
 * @author Michael Rapp
 * @since 1.3.0
 */
public final class AppUtil {

    /**
     * Creates a new utility class, which provides static methods, which allow to start apps.
     */
    private AppUtil() {

    }

    /**
     * Starts the camera app in order to capture a picture. If an error occurs while starting the
     * camera app, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the captured picture should be passed to by calling its
     *         <code>onActivityResult</code> method, as an instance of the class {@link Activity}.
     *         The activity may not be null
     * @param requestCode
     *         The request code, which should be used to pass the captured picture to the given
     *         activity, as an {@link Integer} value
     * @param file
     *         The file, the captured picture should be saved to, as an instance of the class {@link
     *         File}. The file may not be null. The file must exist and must not be a directory.
     */
    public static void startCameraApp(@NonNull final Activity activity, final int requestCode,
                                      @NonNull final File file) {
        Condition.INSTANCE.ensureNotNull(file, "The file may not be null");
        Condition.INSTANCE
                .ensureFileIsNoDirectory(file, "The file must exist and must not be a directory");
        startCameraApp(activity, requestCode, Uri.fromFile(file));
    }

    /**
     * Starts the camera app in order to capture a picture. If an error occurs while starting the
     * camera app, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the captured picture should be passed to by calling its
     *         <code>onActivityResult</code> method, as an instance of the class {@link Activity}.
     *         The activity may not be null
     * @param requestCode
     *         The request code, which should be used to pass the captured picture to the given
     *         activity, as an {@link Integer} value
     * @param uri
     *         The URI of the file, the captured image should be saved to, as an instance of the
     *         class {@link Uri}. The URI may not be null
     */
    public static void startCameraApp(@NonNull final Activity activity, final int requestCode,
                                      @NonNull final Uri uri) {
        Condition.INSTANCE.ensureNotNull(activity, "The activity may not be null");
        Condition.INSTANCE.ensureNotNull(uri, "The URI may not be null");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            throw new ActivityNotFoundException("Camera app not available");
        }

        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Starts the gallery app in order to choose a picture. In an error occurs while starting the
     * gallery app, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the chosen image should be passed to by calling its
     *         <code>onActivityResult</code> method, as an instance of the class {@link Activity}.
     *         The activity may not be null
     * @param requestCode
     *         The request code, which should be used to pass the captured picture to the given
     *         activity, as an {@link Integer} value
     */
    public static void startGalleryApp(@NonNull final Activity activity, final int requestCode) {
        Condition.INSTANCE.ensureNotNull(activity, "The activity may not be null");
        Intent intent =
                new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            throw new ActivityNotFoundException("Gallery app not available");
        }

        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Starts the gallery app in order to show a specific image. If an error occurs while starting
     * the gallery app, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the gallery app should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param file
     *         The file, which stores the image, which should be shown, as an instance of the class
     *         {@link File}. The file may not be null. The file must exist and must not be a
     *         directory.
     */
    public static void startGalleryApp(@NonNull final Context context, @NonNull final File file) {
        Condition.INSTANCE.ensureNotNull(file, "The file may not be null");
        Condition.INSTANCE
                .ensureFileIsNoDirectory(file, "The file must exist and must not be a directory");
        startGalleryApp(context, Uri.parse("file://" + file.getAbsolutePath()));
    }

    /**
     * Starts the gallery app in order to show a specific image. If an error occurs while starting
     * the gallery app, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the gallery app should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param uri
     *         The URI of the image, which should be shown, as an instance of the class {@link Uri}.
     *         The URI may not be null and must be valid
     */
    public static void startGalleryApp(@NonNull final Context context, @NonNull final Uri uri) {
        Condition.INSTANCE.ensureNotNull(context, "The context may not be null");
        Condition.INSTANCE.ensureNotNull(uri, "The URI may not be null");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "image/*");

        if (intent.resolveActivity(context.getPackageManager()) == null) {
            throw new ActivityNotFoundException("Gallery app not available");
        }

        context.startActivity(intent);
    }

    /**
     * Starts the web browser in order to show a specific URI. If an error occurs while starting the
     * web browser an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the web browser should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param uri
     *         The URI, which should be shown, as a {@link String}. The URI may neither be null, nor
     *         empty
     */
    public static void startWebBrowser(@NonNull final Context context, @NonNull final String uri) {
        Condition.INSTANCE.ensureNotNull(uri, "The URI may not be null");
        Condition.INSTANCE.ensureNotEmpty(uri, "The URI may not be empty");
        startWebBrowser(context, Uri.parse(
                (uri.startsWith("http://") || uri.startsWith("https://")) ? uri :
                        ("http://" + uri)));
    }

    /**
     * Starts the web browser in order to show a specific URI. If an error occurs while starting the
     * web browser an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the web browser should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param uri
     *         The URI, which should be shown, as an instance of the class {@link Uri}. The URI may
     *         not be null
     */
    public static void startWebBrowser(@NonNull final Context context, @NonNull final Uri uri) {
        Condition.INSTANCE.ensureNotNull(context, "The context may not be null");
        Condition.INSTANCE.ensureNotNull(uri, "The URI may not be null");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(context.getPackageManager()) == null) {
            throw new ActivityNotFoundException("Web browser not available");
        }

        context.startActivity(intent);
    }

    /**
     * Starts the mail client in order to send an e-mail to a specific e-mail address. If an error
     * occurs while starting the mail client, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the mail client should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param emailAddress
     *         The e-mail address, the e-mail should be sent to, as a {@link String}. The e-mail
     *         address may neither be null, nor empty
     */
    public static void startMailClient(@NonNull final Context context,
                                       @NonNull final String emailAddress) {
        startMailClient(context, emailAddress, null, null);
    }

    /**
     * Starts the mail client in order to send an e-mail to a specific e-mail address. If an error
     * occurs while starting the mail client, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the mail client should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param emailAddress
     *         The e-mail address, the e-mail should be sent to, as a {@link String}. The e-mail
     *         address may neither be null, nor empty
     * @param subject
     *         The subject of the e-mail, which should be sent, as a {@link String} or null, if the
     *         e-mail should not have a subject
     * @param text
     *         The text of the e-mail, which should be sent, as a {@link String}  or null, if the
     *         e-mail should not have a text
     */
    public static void startMailClient(@NonNull final Context context,
                                       @NonNull final String emailAddress,
                                       @Nullable final String subject,
                                       @Nullable final String text) {
        Condition.INSTANCE.ensureNotNull(emailAddress, "The e-mail address may not be null");
        Condition.INSTANCE.ensureNotEmpty(emailAddress, "The e-mail address may not be empty");
        startMailClient(context, new String[]{emailAddress}, subject, text);
    }

    /**
     * Starts the mail client in order to send an e-mail to specific e-mail addresses. If an error
     * occurs while starting the mail client, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the mail client should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param emailAddresses
     *         An array, which contains the e-mail addresses, the e-mail should be sent to, as a
     *         {@link String} array. The array may neither be null, nor empty
     */
    public static void startMailClient(@NonNull final Context context,
                                       @NonNull final String[] emailAddresses) {
        startMailClient(context, emailAddresses, null, null);
    }

    /**
     * Starts the mail client in order to send an e-mail to specific e-mail addresses. If an error
     * occurs while starting the mail client, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the mail client should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param emailAddresses
     *         An array, which contains the e-mail addresses, the e-mail should be sent to, as a
     *         {@link String} array. The array may neither be null, nor empty
     * @param subject
     *         The subject of the e-mail, which should be sent, as a {@link String} or null, if the
     *         e-mail should not have a subject
     * @param text
     *         The text of the e-mail, which should be sent, as a {@link String}  or null, if the
     *         e-mail should not have a text
     */
    public static void startMailClient(@NonNull final Context context,
                                       @NonNull final String[] emailAddresses,
                                       @Nullable final String subject,
                                       @Nullable final String text) {
        Condition.INSTANCE.ensureNotNull(context, "The context may not be null");
        Condition.INSTANCE.ensureNotNull(emailAddresses, "The array may not be null");
        Condition.INSTANCE.ensureTrue(emailAddresses.length > 0, "The array may not be empty");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);

        if (!TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }

        if (!TextUtils.isEmpty(text)) {
            intent.putExtra(Intent.EXTRA_TEXT, text);
        }

        if (intent.resolveActivity(context.getPackageManager()) == null) {
            throw new ActivityNotFoundException("Mail client not available");
        }

        context.startActivity(intent);
    }

    /**
     * Starts the dialer in order to call a specific phone number. The call has to be manually
     * started by the user. If an error occurs while starting the dialer, an {@link
     * ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the dialer should be started from, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param phoneNumber
     *         The phone number, which should be dialed, as an {@link Integer} value
     */
    public static void startDialer(@NonNull final Context context, final int phoneNumber) {
        startDialer(context, Integer.toString(phoneNumber));
    }

    /**
     * Starts the dialer in order to call a specific phone number. The call has to be manually
     * started by the user. If an error occurs while starting the dialer, an {@link
     * ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the dialer should be started from, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param phoneNumber
     *         The phone number, which should be dialed, as a {@link Long} value
     */
    public static void startDialer(@NonNull final Context context, final long phoneNumber) {
        startDialer(context, Long.toString(phoneNumber));
    }

    /**
     * Starts the dialer in order to call a specific phone number. The call has to be manually
     * started by the user. If an error occurs while starting the dialer, an {@link
     * ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the dialer should be started from, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param phoneNumber
     *         The phone number, which should be dialed, as a {@link String}. The phone number may
     *         neither be null, nor empty
     */
    public static void startDialer(@NonNull final Context context,
                                   @NonNull final String phoneNumber) {
        Condition.INSTANCE.ensureNotNull(context, "The context may not be null");
        Condition.INSTANCE.ensureNotNull(phoneNumber, "The phone number may not be null");
        Condition.INSTANCE.ensureNotEmpty(phoneNumber, "The phone number may not be empty");
        Uri uri = Uri.parse(phoneNumber.startsWith("tel:") ? phoneNumber : "tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        if (intent.resolveActivity(context.getPackageManager()) == null) {
            throw new ActivityNotFoundException("Dialer app not available");
        }

        context.startActivity(intent);
    }

    /**
     * Starts the dialer in order to call a specific phone number. The call is immediately started,
     * requiring the invoking app to have the permission <code>android.permission.CALL_PHONE</code>
     * granted, otherwise a {@link SecurityException} will be thrown. If an error occurs while
     * starting the dialer, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the dialer should be started from, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param phoneNumber
     *         The phone number, which should be called, as an {@link Integer} value
     */
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static void startCall(@NonNull final Context context, final int phoneNumber) {
        startDialer(context, Integer.toString(phoneNumber));
    }

    /**
     * Starts the dialer in order to call a specific phone number. The call is immediately started,
     * requiring the invoking app to have the permission <code>android.permission.CALL_PHONE</code>
     * granted, otherwise a {@link SecurityException} will be thrown. If an error occurs while
     * starting the dialer, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the dialer should be started from, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param phoneNumber
     *         The phone number, which should be called, as a {@link Long} value
     */
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static void startCall(@NonNull final Context context, final long phoneNumber) {
        startDialer(context, Long.toString(phoneNumber));
    }

    /**
     * Starts the dialer in order to call a specific phone number. The call is immediately started,
     * requiring the invoking app to have the permission <code>android.permission.CALL_PHONE</code>
     * granted, otherwise a {@link SecurityException} will be thrown. If an error occurs while
     * starting the dialer, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param context
     *         The context, the dialer should be started from, as an instance of the class {@link
     *         Context}. The context may not be null
     * @param phoneNumber
     *         The phone number, which should be called, as a {@link String}. The phone number may
     *         neither be null, nor empty
     */
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static void startCall(@NonNull final Context context,
                                 @NonNull final String phoneNumber) {
        Condition.INSTANCE.ensureNotNull(context, "The context may not be null");
        Condition.INSTANCE.ensureNotNull(phoneNumber, "The phone number may not be null");
        Condition.INSTANCE.ensureNotEmpty(phoneNumber, "The phone number may not be empty");
        Uri uri = Uri.parse(phoneNumber.startsWith("tel:") ? phoneNumber : "tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);

        if (intent.resolveActivity(context.getPackageManager()) == null) {
            throw new ActivityNotFoundException("Dialer app not available");
        }

        context.startActivity(intent);
    }

    /**
     * Starts the settings app in order to show the information about the current app.
     *
     * @param context
     *         The context, the settings app should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     */
    public static void showAppInfo(@NonNull final Context context) {
        showAppInfo(context, context.getPackageName());
    }

    /**
     * Starts the settings app in order to show the information about a specific app.
     *
     * @param context
     *         The context, the settings app should be started from, as an instance of the class
     *         {@link Context}. The context may not be null
     * @param packageName
     *         The fully qualified package name of the app, whose information should be shown, as a
     *         {@link String}. The package name may neither be null, nor empty
     */
    public static void showAppInfo(@NonNull final Context context,
                                   @NonNull final String packageName) {
        Condition.INSTANCE.ensureNotNull(context, "The context may not be null");
        Condition.INSTANCE.ensureNotNull(packageName, "The package name may not be null");
        Condition.INSTANCE.ensureNotEmpty(packageName, "The package name may not be empty");
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", packageName, null);
        intent.setData(uri);

        if (intent.resolveActivity(context.getPackageManager()) == null) {
            throw new ActivityNotFoundException(
                    "App info for package " + packageName + " not available");
        }

        context.startActivity(intent);
    }

}