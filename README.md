# AndroidUtil - README

"AndroidUtil" is an Android-library, which provides various utility classes for use in Android app development. The library currently provides the following features:

- The class `Condition` provides methods, which allow to ensure that variables or objects fulfill certain conditions by throwing exceptions, if conditions are violated, e.g. when an object is null.
- The class `BitmapUtil` provides methods, which allow to create and edit bitmaps, e.g. clipping them.
- The class `ClassUtil` provides methods, which allow to handle class names.
- The class `DisplayUtil` provides methods, which are related to a device's display metrics, e.g. converting DP values to pixel values and vice versa.
- The class `ElevationUtil` provides methods, which can be used to emulate elevations of views on pre-Lollipop devices. The view `ElevationShadowView` can be used to easily visualize such an elevation within a layout.
- The class `AppUtil` provides methods, which allow to start system apps.
- The class `PermissionUtil` provides methods, which allow to handle runtime permissions.

## License Agreement

This project is distributed under the GNU Lesser Public License version 3.0 (GLPLv3). For further information about this license agreement's content please refer to its full version, which is available at http://www.gnu.org/licenses/lgpl.txt.

## Download

The latest release of this library can be downloaded as a zip archive from the download section of the project's Github page, which is available [here](https://github.com/michael-rapp/AndroidUtil/releases). Furthermore, the library's source code is available as a Git repository, which can be cloned using the URL https://github.com/michael-rapp/AndroidUtil.git.

Alternatively, the library can be added to your Android app as a Gradle dependency by adding the following to the module's `build.gradle` file:

```
dependencies {
    compile 'com.github.michael-rapp:android-util:1.4.0'
}
```

## Contact information

For personal feedback or questions feel free to contact me via the mail address, which is mentioned on my [Github profile](https://github.com/michael-rapp). If you have found any bugs or want to post a feature request, please use the [bugtracker](https://github.com/michael-rapp/AndroidUtil/issues) to report them.