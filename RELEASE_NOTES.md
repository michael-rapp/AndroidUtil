# AndroidSidebar - RELEASE NOTES

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