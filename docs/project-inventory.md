# Project Inventory

## Modules
- **:app**: The main Android application module containing all source code and resources.

## Packages
- `com.helixios.helixioswall`: Main activities and fragments.
- `com.helixios.helixioswall.adapters`: RecyclerView adapters.
- `com.helixios.helixioswall.database`: Room database and DAOs.
- `com.helixios.helixioswall.model`: Data models (POJOs) for API and Database.
- `com.helixios.helixioswall.networking`: Retrofit client and API interfaces.
- `com.helixios.helixioswall.ui.main`: Pager adapters and view models for the main screen.

## Important Files
- `AndroidManifest.xml`: Application configuration and permissions.
- `build.gradle` (root): Project-level build configuration.
- `app/build.gradle`: Module-level dependencies and SDK versions.
- `gradle.properties`: Build environment settings.

## External Integrations
- **Flickr API**: Used for fetching photo data. API key is currently hardcoded in `FlickrApi.java`.
- **Lottie**: Used for splash screen and loading animations.
- **Glide**: Used for image loading and caching.
- **Android-Image-Cropper**: Used for wallpaper cropping.
