# Current Architecture

## UI Architecture
The application follows a standard Activity/Fragment structure using XML layouts.
- **FullscreenActivity**: Splash screen with a Lottie animation.
- **MainActivity**: Hosts a `ViewPager` with four fragments:
    - `home_frag`: Main photo feed.
    - `category_frag`: List of categories (CardViews).
    - `favour_frag`: Saved photos list.
    - `about_frag`: App information.
- **SelectedCategoryActivity**: Displays photos for a specific category.
- **PhotoActivity**: Detailed view of a single photo.
- **CropActivity**: Interface for cropping an image before setting as wallpaper.

## Presentation Layer
Logic is largely contained within Activities and Fragments. There is minimal use of ViewModels (`PageViewModel` exists but seems underutilized).

## Data Layer
- **Local Persistence**: Room database (`HelixDatabase`) stores `Photo` objects.
- **Models**: Simple POJOs (`Photo`, `SearchPhotos`, etc.) used for both API responses and database entities.

## Networking
- **Retrofit**: Used with GSON converter for Flickr API interaction.
- **RetrofitClient**: Singleton for Retrofit instance management.
- **FlickrApi**: Interface defining API endpoints and hardcoded API keys.

## Concurrency
- **Thread Management**: The app uses manual `Thread` creation in many places.
- **Busy Waiting**: Uses `while(thread.isAlive()) { continue; }` which is a significant anti-pattern.
- **Callbacks**: Retrofit uses asynchronous enqueuing.

## Navigation
- **ViewPager**: Standard swipe-based navigation for main sections.
- **Intents**: Used for moving between Activities with some shared element transitions.
