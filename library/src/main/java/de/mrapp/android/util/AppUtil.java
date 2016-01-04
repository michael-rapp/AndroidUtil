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

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import java.io.File;

import static de.mrapp.android.util.Condition.ensureFileIsNoDirectory;
import static de.mrapp.android.util.Condition.ensureNotEmpty;
import static de.mrapp.android.util.Condition.ensureNotNull;

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
        ensureNotNull(activity, "The activity may not be null");
        ensureNotNull(file, "The file may not be null");
        ensureFileIsNoDirectory(file, "The file must exist and must not be a directory");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

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
        ensureNotNull(activity, "The activity may not be null");
        Intent intent =
                new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Starts the gallery app in order to show a specific image. If an error occurs while starting
     * the gallery app, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the gallery app should be started from, as an instance of the class
     *         {@link Activity}. The activity may not be null
     * @param file
     *         The file, which stores the image, which should be shown, as an instance of the class
     *         {@link File}. The file may not be null. The file must exist and must not be a
     *         directory.
     */
    public static void startGalleryApp(@NonNull final Activity activity, @NonNull final File file) {
        ensureNotNull(activity, "The activity may not be null");
        ensureNotNull(file, "The file may not be null");
        ensureFileIsNoDirectory(file, "The file must exist and must not be a directory");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = file.getAbsolutePath();
        intent.setDataAndType(Uri.parse("file://" + path), "image/*");
        activity.startActivity(intent);
    }

    /**
     * Starts the web browser in order to show a specific URI. If an error occurs while starting the
     * web browser an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the web browser should be started from, as an instance of the class
     *         {@link Activity}. The activity may not be null
     * @param uri
     *         The URI, which should be shown, as a {@link String}. The URI may neither be null, nor
     *         empty
     */
    public static void startWebBrowser(@NonNull final Activity activity,
                                       @NonNull final String uri) {
        ensureNotNull(uri, "The URI may not be null");
        ensureNotEmpty(uri, "The URI may not be empty");
        startWebBrowser(activity, Uri.parse(
                (uri.startsWith("http://") || uri.startsWith("https://")) ? uri :
                        ("http://" + uri)));
    }

    /**
     * Starts the web browser in order to show a specific URI. If an error occurs while starting the
     * web browser an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the web browser should be started from, as an instance of the class
     *         {@link Activity}. The activity may not be null
     * @param uri
     *         The URI, which should be shown, as an instance of the class {@link Uri}. The URI may
     *         not be null
     */
    public static void startWebBrowser(@NonNull final Activity activity, @NonNull final Uri uri) {
        ensureNotNull(activity, "The activity may not be null");
        ensureNotNull(uri, "The URI may not be null");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }

    /**
     * Starts the mail client in order to send an e-mail to a specific e-mail address. If an error
     * occurs while starting the mail client, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the mail client should be started from, as an instance of the class
     *         {@link Activity}. The activity may not be null
     * @param emailAddress
     *         The e-mail address, the e-mail should be sent to, as a {@link String}. The e-mail
     *         address may neither be null, nor empty
     */
    public static void startMailClient(@NonNull final Activity activity,
                                       @NonNull final String emailAddress) {
        ensureNotNull(activity, "The activity may not be null");
        ensureNotNull(emailAddress, "The e-mail address may not be null");
        ensureNotEmpty(emailAddress, "The e-mail address may not be empty");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        activity.startActivity(intent);
    }

    /**
     * Starts the dialer in order to call a specific phone number. If an error occurs while starting
     * the dialer, an {@link ActivityNotFoundException} will be thrown.
     *
     * @param activity
     *         The activity, the dialer should be started from, as an instance of the class {@link
     *         Activity}. The activity may not be null
     * @param phoneNumber
     *         The phone number, which should be called, as an {@link Integer} value
     */
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static void startDialer(@NonNull final Activity activity, final int phoneNumber) {
        startDialer(activity, Integer.toString(phoneNumber));
    }

    /**
     * Starts the dialer in order to call a specific phone number. If an error occurs while starting
     * the dialer, an {@link ActivityNotFoundException} will be thrown. Calling this method requires
     * the invoking app to have the permission <code>android.Manifest.permission.CALL_PHONE</code>,
     * otherwise a {@link SecurityException} will be thrown.
     *
     * @param activity
     *         The activity, the dialer should be started from, as an instance of the class {@link
     *         Activity}. The activity may not be null
     * @param phoneNumber
     *         The phone number, which should be called, as a {@link Long} value
     */
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static void startDialer(@NonNull final Activity activity, final long phoneNumber) {
        startDialer(activity, Long.toString(phoneNumber));
    }

    /**
     * Starts the dialer in order to call a specific phone number. If an error occurs while starting
     * the dialer, an {@link ActivityNotFoundException} will be thrown. Calling this method requires
     * the invoking app to have the permission <code>android.Manifest.permission.CALL_PHONE</code>,
     * otherwise a {@link SecurityException} will be thrown.
     *
     * @param activity
     *         The activity, the dialer should be started from, as an instance of the class {@link
     *         Activity}. The activity may not be null
     * @param phoneNumber
     *         The phone number, which should be called, as a {@link String}. The phone number may
     *         neither be null, nor empty
     */
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    @SuppressWarnings("ResourceType")
    public static void startDialer(@NonNull final Activity activity,
                                   @NonNull final String phoneNumber) {
        ensureNotNull(activity, "The activity may not be null");
        ensureNotNull(phoneNumber, "The phone number may not be null");
        ensureNotEmpty(phoneNumber, "The phone number may not be empty");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        activity.startActivity(intent);
    }

}