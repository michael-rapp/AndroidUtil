# AndroidUtil - RELEASE NOTES

## Version 1.17.0 (Jun. 17th 2017)

A feature release, which introducing the following changes:

- The `getBoolean`-, `getInteger`-, `getFloat`- and `getResourceId`-methods of the class `ThemeUtil` are now deprecated. They have been replaced with methods, which allow to specify default values, which are returned, if the the given resource id is invalid.

## Version 1.16.0 (May 17th 2017)

A feature release, which introduces the following changes:

- Added the class `UnfocusableToolbar`, extends the AppCompat support library's `Toolbar` to enable touch events to be passed to other views in the event processing chain.

## Version 1.15.4 (May 3rd 2017)

A bugfix release, which fixes the following issues:

- Changed return type of `getResourceId`-method of class `ThemeUtil` from `float` to `int`.

## Version 1.15.3 (May 3rd 2017)

A minor release, which introduces the following changes:

- Added `getResourceId`-methods to the class `ThemeUtil`.

## Version 1.15.2 (Apr. 27th 2017)

A bugfix release, which introduces the following changes:

- Fixed `bringToFront`-method of class `AttachedViewRecycler`.
- Added `setComparator`-method to class `AttachedViewRecycler`.

## Version 1.15.1 (Apr. 27th 2017)

A minor release, which introduces the following changes:

- Added `bringToFront`-method to class `AttachedViewRecycler`.
- Updated AppCompat v4 support library to version 25.3.1.
- Updated AppCompat annotations support library to version 25.3.1.
- Updated AppCompat RecyclerView support library to version 25.3.1.

## Version 1.15.0 (Mar. 22th 2017)

A feature release, which introduces the following changes:

- Added new methods to the class `DragHelper`, which use `float` values instead of `int` values. The old methods are now deprecated.
- Added `setMaxDragDistance`- and `setMinDragDistance`-method to the class `DragHelper`.
- Added the classes `ViewRecycler` and `AttachedViewRecycler`, which allow to cache views in order to reuse them instead of inflating new instances.
- Added the class `AbstractDataBinder`, which allows to asynchronously load data in order to display it by using recyclable views.
- The classes `ElevationShadowView` and `SquareImageView` are now extended from the class `AppCompatImageView`.
- Added AppCompat v7 support library as a dependency.
- Updated AppCompat v4 support library to version 25.3.0.
- Updated AppCompat annotations support library to version 25.3.0.
- Updated AppCompat RecyclerView support library to version 25.3.0.

## Version 1.14.0 (Feb. 12th 2017)

A feature release, which introduces the following changes:

- Added the classes `ViewHolder` and `AbstractViewHolderAdapter`. They allow to use the view holder pattern in custom adapter implementations.

## Version 1.13.0 (Feb. 4th 2017)

A feature release, which introduces the following changes:

- Added the class `Logger`, which allows to write log messages to Logcat. By applying a log level, log messages can be filtered depending on their priority.
- Updated AppCompat v4 support library to version 25.1.0.
- Updated AppCompat annotations support library to version 25.1.0.
- Updated AppCompat RecyclerView support library to version 25.1.0.

## Version 1.12.3 (Jan. 22th 2017)

A minor release, which introduces the following changes:

- Added `removeOnGlobalLayoutListener`-method to the class `ViewUtil`.
- The `getStatusBarHeight`- and `getNavigationBarHeight`-methods of the class `DisplayUtil` are now deprecated. A `View.OnApplyWindowInsetsListener` should be used instead.

## Version 1.12.2 (Jan. 4th 2017)

A minor release, which introduces the following changes:

- Added `shouldShowRequestPermissionRationale`-method to the class `PermissionUtil`.

## Version 1.12.1 (Jan. 3rd 2017)

A minor release, which introduces the following changes:

- Updated `targetSdkVersion` to API level 25 (Android 7.1)
- The methods of the class `AppUtil` do now expect instances of the class `Context`, instead of instances of the class `Activity`, as arguments, whenever possible.
- Added `showAppInfo`-methods to the class `AppUtil`. These methods allow to start the settings app in order to show information about specific apps.

## Version 1.12.0 (Nov. 1st 2016)

A feature release, which introduces the following changes:

- Added the class `ExpandableGridView`.
- Added the class `HeaderAndFooterRecyclerView`. This required to add the RecyclerView support library as a dependency.

## Version 1.11.1 (Sep. 11th 2016)

A minor release, which introduces the following changes:

- Updated `targetSdkVersion` to API level 24 (Android 7.0)
- Updated AppCompat v4 support library to version 24.2.0.
- Updated AppCompat annotations support library to version 24.2.0.

## Version 1.11.0 (Aug. 17th 2016)

A feature release, which introduces the following changes:

- Added the class `ArrayUtil`, which provides methods, which allow to handle arrays.

## Version 1.10.1 (Jul. 21th 2016)

A minor release, which introduces the following changes:

- Added `createNewFile`-methods to the class `FileUtil`.
- Added `getDensity`-method to the class `DisplayUtil`.
- Added `splitHorizontally`-, `splitVertically`-, `colorToBitmap`-, `textToBitmap`-, `compressToFile`- and `compressToByteArray`-methods to the class `BitmapUtil`.

## Version 1.10.0 (Jul. 21th 2016)

A feature release, which introduces the following changes:

- Added the class `ThreadUtil`, which provides methods, which allow to handle threads.

## Version 1.9.0 (Jul. 21th 2016)

A feature release, which introduces the following changes:

- Added the class `FileUtil`, which provides methods, which allow to handle files.
- Added the class `StreamUtil`, which provides methods, which allow to handle streams.

## Version 1.8.1 (Jul. 10th 2016)

A minor release, which introduces the following changes:

- Changed the visibility of the `getSuperState`-method of the class `AbstractSavedState` to `public`.

## Version 1.8.0 (Jul. 10th 2016)

A feature release, which introduces the following changes:

- Added the abstract class `AbstractSavedState`, which can be used as a base class for storing the state of views. It is identical to the Android SDK's class `android.view.AbsSavedState`, but uses a custom class loader and is therefore suited for being used in library projects.

## Version 1.7.0 (Jun. 17th 2016)

A feature release, which introduces the following changes:

- Added the `getNotGrantedPermissions`-method to the class `PermissionUtil`.
- Removed the `requestPermission`-methods as well as the interface `Callback` from the class `PermissionUtil`.

## Version 1.6.1 (Jun. 16th 2016)

A minor release, which introduces the following changes:

- Added the `areAllPermissionsGranted`-method to the class `PermissionUtil`. 

## Version 1.6.0 (Jun. 14th 2016)

A feature release, which introduces the following changes:

- Added the view `HeaderAndFooterGridView`, which extends the Android SDK's `GridView` with the functionality to show header and footer views.

## Version 1.5.0 (Jun. 14th 2016)

A feature release, which introduces the following changes:

- The `startDialer`-methods of the utility class `AppUtil` do not require the invoking app to have the permission `android.permission.CALL_PHONE` granted, anymore. These methods to not immediately start a call anymore, but only pass a phone number to the dialer. As replacement for the old behavior new `startCall`-methods have been added.
- Added additional `startMailClient`-methods, which allow to send e-mails to multiple mail addresses and allow to optionally specify a subject and text, to the utility class `AppUtil`.

## Version 1.4.11 (Apr. 21th 2016)

A minor release, which introduces the following changes:

- Added a `startCameraApp`-method, which expects an `Uri` as a parameter, to the class `AppUtil`.

## Version 1.4.10 (Apr. 21th 2016)

A bugfix release, which fixes the following issues:

- Fixed `IllegalArgumentException` when calling the `loadThumbnail`-methods of the class `BitmapUtil`.

## Version 1.4.9 (Mar. 30th 2016)

A minor release, which introduces the following changes:

- Added a `startGalleryApp`-method to the class `AppUtil`, which expects an `Uri` as a parameter.

## Version 1.4.8 (Mar. 23th 2016)

A minor release, which introduces the following changes:

- Added the class `ThemeUtil`, which allows to obtain theme attributes.
- Added `ensureEqual`- and `ensureNotEqual`-methods to the class `Condition`.

## Version 1.4.7 (Mar. 21th 2016)

A minor release, which introduces the following changes:

- Renamed styled attribute "insetForeground" of the view `ScrimInsetsLayout` to "insetDrawable", because it conflicted with the Design support library.

## Version 1.4.6 (Mar. 20th 2016)

A minor release, which introduces the following changes:

- Added the view `SquareImageView`. It implements an `ImageView` whose height is enforced to be equal to its width.
- Added the view `ScrimInsetsLayout`.It implements a layout, which allows to show a `Drawable` in the insets, which are passed to its `fitSystemWindows`-method.

## Version 1.4.5 (Mar. 17th 2016)

A minor release, which introduces the following changes:

- Added the class `ViewUtil`. It provides a `setBackground`-method, which allows to set the background of a view depending on the device's API level.

## Version 1.4.4. (Mar. 3rd 2016)

A minor release, which introduces the following changes:

- Added an `ensureNotEmpty`-method, which allows to ensure that an instance of the type `Iterable` is not empty, to the class `Condition`.
- Added `ensureTrue`- and `ensureFalse`-methods to the class `Condition`.
- Added the data structure `Triple`.

## Version 1.4.3 (Feb. 24th 2016)

A minor release, which introduces the following changes:

- The library is from now on distributed under the Apache License version 2.0. 
- Minor changes of the example app.

## Version 1.4.2 (Feb. 19th 2016)

A minor release, which introduces the following changes:

- Added `getDisplayWidth` and `getDisplayHeight`-methods to the class `DisplayUtil`.
- Added `getOrientation`-method to the class `DisplayUtil`.
- Added `getNavigationBarHeight`-method to the class `DisplayUtil`.

## Version 1.4.1 (Feb. 18th 2016)

A minor release, which introduces the following changes:

- Added `getStatusBarHeight`-method to the class `DisplayUtil`.

## Version 1.4.0 (Feb. 7th 2016)

A feature release, which provides the following changes:

- The class `DragHelper`, which allows to recognize drag gestures have been added.

## Version 1.3.2 (Jan. 4th 2016)

A bugfix release, which fixes the following issues:

- Faulty `@RequiresPermission` annotations of the `startDialer`-methods of the class `AppUtil` have been fixed.

## Version 1.3.1 (Jan. 4th 2016)

A minor release, which introduces the following changes:

- Added `getImageDimension`-methods, which allow to retrieve the width and height of images, to the class `BitmapUtil`.
- Added `loadThumbnail`-methods, which allow to load downsampled thumbnail of images, to the class `BitmapUtil`.

## Version 1.3.0 (Jan. 3rd 2016)

A feature release, which introduces the following features:

- Added `ensureFileIsNoDirectory`-method to class `Condition`.
- Added `getDeviceType`-method to class `DisplayUtil`.
- Added the utility class `AppUtil`, which provides methods, which allow to start system apps.
- Added the utility class `PermissionUtil`, which provides methods, which allow to handle runtime permissions.
- Removed the annotation `@VisibleForTesting` as the annotation support library provides an identical one.
- As the library does now depend on the v4 support library, the minimum SDK version has been increased to 4.

## Version 1.2.4 (Dec. 27th 2015)

A minor release, which introduces the following changes:

- Added `ensureFileExists`-method to class `Condition`.
- Added `ensureFileIsDirectory`-method to class `Condition`.

## Version 1.2.3 (Dec. 23th 2015)

A bugfix release, which fixes the following issues:

- https://github.com/michael-rapp/AndroidUtil/issues/1

## Version 1.2.2 (Nov. 5th 2015)

A minor release, which introduces the following changes:

- The view `ElevationShadowView`, which allows to visualize elevations within layouts, has been added.

## Version 1.2.1 (Nov. 3rd 2015)

A minor release, which introduces the following changes:

- A method, which allows to specify, whether parallel illumination should be used, when emulating the shadow of an elevated view, has been added to the class `ElevationUtil`. When using parallel lightning, the shadows at all sides of the view appear identically.

## Version 1.2.0 (Oct. 24th 2015)

A feature release, which provides the following changes:

- The class `ElevationUtil` has been re-implemented. Shadows are now rendered as bitmaps and a wider range of elevations is now available. Furthermore, rendering shadows, which are located at the corners of an elevated view, is now also supported.
- An example app, which demonstrates the appearance emulated elevations, has been added.

## Version 1.1.0 (Oct. 22th 2015)

A feature release, which provides the following changes:

- The class `ElevationUtil`, which provides methods, which allow to emulate elevations of views on pre-Lollipop devices, has been added.

## Version 1.0.0 (Oct. 15th 2015)

The first stable release of the library, which provides the following utility classes:

- The class `Condition` provides methods, which allow to ensure that variables or objects fulfill certain conditions by throwing exceptions, if conditions are violated, e.g. when an object is null.
- The class `BitmapUtil` provides methods, which allow to create and edit bitmaps, e.g. clipping them.
- The class `ClassUtil` provides methods, which allow to handle class names.
- The class `DisplayUtil` provides methods, which are related to a device's display metrics, e.g. converting DP values to pixel values and vice versa.
- The annotation `VisibleForTesting` can be used to mark types, constructors, methods or fields, which are only part of a publicly exposed API for testing purposes.