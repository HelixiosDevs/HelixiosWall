# Modernization Plan

## Phase 1: Critical Fixes & Security (Immediate)
1. **Remove Busy Waiting**: Refactor `favour_frag.java` and any other classes using `while(thread.isAlive())` to use Room's asynchronous capabilities or standard Java concurrency without blocking the main thread.
2. **Secure API Keys**: Move Flickr API keys from `FlickrApi.java` to `local.properties` and inject them via `BuildConfig`.

## Phase 2: Toolchain & Dependency Modernization
1. **Gradle Upgrade**: Upgrade Gradle wrapper to 8.x.
2. **AGP Upgrade**: Upgrade Android Gradle Plugin to 8.x.
3. **Dependency Refresh**: Update core libraries (AppCompat, Material, Room, Glide) to their latest stable versions.

## Phase 3: Android Platform Compatibility
1. **Scoped Storage**: Refactor wallpaper download and setting flow to use Scoped Storage or MediaStore APIs instead of `WRITE_EXTERNAL_STORAGE`.
2. **Permissions**: Implement Android 13+ notification permissions if needed (though not currently using notifications).
3. **Edge-to-Edge**: Implement modern edge-to-edge UI support.

## Phase 4: Architectural Improvements
1. **MVVM Refactor**: Properly implement `ViewModel` and `LiveData` for data flow between fragments and the database/API.
2. **Networking**: Clean up `FlickrApi` and centralize networking logic.
3. **Connectivity**: Replace ping-based `isOnline` check with `ConnectivityManager`.

## Phase 5: Testing & Quality
1. **Unit Tests**: Add tests for models and DAOs.
2. **UI Tests**: Add Espresso tests for main navigation flows.
3. **Static Analysis**: Integrate Lint and potentially Detekt (if moving to Kotlin).

---

## First Recommended Change
**Item**: Remove Busy Waiting in `favour_frag.java`.
**Reason**: This is a critical performance bug that causes the UI thread to spin while waiting for database operations, leading to poor user experience and potential ANRs.
**Impact**: High (Stability/Performance).
**Risk**: Low.
