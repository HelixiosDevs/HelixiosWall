# AGENTS.md

Guidance for AI coding agents working in this repository.

## Project
HelixiosWall: Android wallpaper app (Java, XML layouts, single `app` module, package `com.helixios.helixioswall`) that browses photos from Flickr (Mirage photography club of IET, in-game screenshots), lets users favourite, crop and set them as wallpaper. Per `docs/agent-context.md`: keep Java, don't migrate to Kotlin unless clearly beneficial; keep the existing Flickr API integration.

## Commands

Run from repo root (Windows: `gradlew.bat`). Gradle 9.1 needs a JDK 17+; from a shell, point `JAVA_HOME` at Android Studio's bundled `jbr`:

```bash
./gradlew assembleDebug            # build debug APK
./gradlew installDebug             # install on device/emulator
./gradlew test                     # JVM unit tests (app/src/test)
./gradlew test --tests "com.helixios.helixioswall.SomeTest"   # single test class
./gradlew connectedAndroidTest     # instrumented tests (needs device)
./gradlew lint
```

## Configuration

- Flickr API key is read from `local.properties` key `flickr.api.key` and exposed as `BuildConfig.FLICKR_API_KEY` (see `app/build.gradle`). Without it the key is an empty string and API calls fail. Never hardcode it.
- minSdk 23 (Android 6), compileSdk/targetSdk 36 (Android 16) in `app/build.gradle`. AGP 8.13.2, Gradle 9.1 (needed to run on Android Studio's JDK 25), Java 17. Room uses `annotationProcessor` (Java), not kapt.
- `gradle.properties` keeps `android.nonTransitiveRClass=false` and `android.nonFinalResIds=false` because code uses appcompat ids via `R` and `switch` on `R.id`.

## Architecture

- `FullscreenActivity` (Lottie splash) → `MainActivity`, which hosts a ViewPager of `home_frag` (feed), `category_frag`, `favour_frag`, `about_frag` (lowercase class names are intentional/legacy).
- `SelectedCategoryActivity` lists photos for a category; `PhotoActivity` shows one photo (download/set wallpaper/favourite); `CropActivity` is the in-app cropper (CanHub Android-Image-Cropper) with Portrait/Landscape/Both crop modes; it's the fallback when the system cropper rejects the image, which happens on Samsung.
- The app is designed portrait-only (`screenOrientation="portrait"`), except `CropActivity` and `SelectedCategoryActivity`. Android 16+ ignores the lock on large screens, so layouts must still work in landscape.
- Networking (`networking/`): Retrofit + Gson; `RetrofitClient` singleton, `FlickrApi` endpoints, `NetworkUtils`, `DownloadBroadcastReceiver` for DownloadManager completion. Responses map to POJOs in `model/` (`SearchPhotos`, `Photo`, `PhotoDetails`, `PhotoSizes`).
- Persistence (`database/`): Room `HelixDatabase` + `FavouriteDao`; `Photo` doubles as API model and DB entity.
- Flickr image URLs: use `live.staticflickr.com` (farm URLs are deprecated). Since May 2025 Flickr withholds `url_o` and sizes above 1024px for Free-account photos, so most photos have no original; always go through `Photo.getDisplayUrl()` / `getBestDownloadUrl()`, which fall back to the always-available 1024px `_b` size. API errors arrive as HTTP 200 with `stat:"fail"`; check `SearchPhotos.hasPhotos()`.
- UI lists use `adapters/` RecyclerView adapters with Glide for images. Logic lives mostly in Activities/Fragments; `ui/main/PageViewModel` is barely used.
- Categories are driven by Flickr search tags (see recent commits "Tags changed for multiple categories").
- Concurrency is manual `Thread`s and Retrofit `enqueue`; historical busy-wait loops (`while(thread.isAlive())`) were being removed — don't reintroduce them.

## Docs

`docs/` holds working notes (architecture, risks, modernization plan, migration status); they can be stale, so verify against code.

## Repo hygiene

- Never commit `local.properties` (holds the Flickr API key).
- Check storage/permission behaviour on both API 23-28 and API 33+.
