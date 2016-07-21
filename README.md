# AndroidUtil - README

"AndroidUtil" is an Android-library, which provides various utility classes for use in Android app development. The library currently provides the following features:

- The class `Condition` provides methods, which allow to ensure that variables or objects fulfill certain conditions by throwing exceptions, if conditions are violated, e.g. when an object is null.
- The class `BitmapUtil` provides methods, which allow to create and edit bitmaps, e.g. clipping them.
- The class `ClassUtil` provides methods, which allow to handle class names.
- The class `DisplayUtil` provides methods, which are related to a device's display metrics, e.g. converting DP values to pixel values and vice versa.
- The class `ElevationUtil` provides methods, which can be used to emulate elevations of views on pre-Lollipop devices. The view `ElevationShadowView` can be used to easily visualize such an elevation within a layout.
- The class `AppUtil` provides methods, which allow to start system apps.
- The class `PermissionUtil` provides methods, which allow to handle runtime permissions.
- The class `ViewUtil` provides methods, which allow to handle views.
- The class `FileUtil` provides methods, which allow to handle files.
- The class `StreamUtil` provides methods, which allow to handle streams.
- The class `ThreadUtil` provides methods, which allow to handle threads.
- The class `Triple` is a generic data structure, which eases to pass around a triple of three objects.
- The view `SquareImageView` implements an `ImageView` whose height is enforced to be equal to its width.
- The view `ScrimInsetsLayout` implements a layout, which allows to show a `Drawable` in the insets, which are passed to its `fitSystemWindows`-method. This can for example be used to implement a navigation drawer, which appears to be located behind the device's status bar.
- The view `HeaderAndFooterGridView` extends the Android SDK's `GridView` with the functionality to add header and footer views.
- The class `AbstractSavedState` can be used as a base class for storing the state of views. It is identical to the Android SDK's class `android.view.AbsSavedState`, but uses a custom class loader and is therefore suited for being used in library projects.

## License Agreement

This project is distributed under the Apache License version 2.0. For further information about this license agreement's content please refer to its full version, which is available at http://www.apache.org/licenses/LICENSE-2.0.txt.

Prior to version 1.4.3 this library was distributed under the GNU Lesser General Public License version 3.0 (GLPLv3).

## Download

The latest release of this library can be downloaded as a zip archive from the download section of the project's Github page, which is available [here](https://github.com/michael-rapp/AndroidUtil/releases). Furthermore, the library's source code is available as a Git repository, which can be cloned using the URL https://github.com/michael-rapp/AndroidUtil.git.

Alternatively, the library can be added to your Android app as a Gradle dependency by adding the following to the module's `build.gradle` file:

```groovy
dependencies {
    compile 'com.github.michael-rapp:android-util:1.10.0'
}
```

## Contact information

For personal feedback or questions feel free to contact me via the mail address, which is mentioned on my [Github profile](https://github.com/michael-rapp). If you have found any bugs or want to post a feature request, please use the [bugtracker](https://github.com/michael-rapp/AndroidUtil/issues) to report them.