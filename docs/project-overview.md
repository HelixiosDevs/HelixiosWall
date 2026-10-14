# HelixiosWall Project Overview

HelixiosWall is an Android wallpaper application that allows users to browse, download, and set high-quality wallpapers sourced from the Flickr API.

## Main Functionality
- **Browse Wallpapers**: View a feed of photos from specified Flickr groups.
- **Categories**: Browse photos by specific tags/categories (e.g., Natural, Cyberpunk, Games).
- **Favourites**: Save photos locally using a Room database.
- **Set Wallpaper**: Download and set images as system or lock screen wallpapers, with cropping support.
- **Share**: Share Flickr links to photos.

## Technical Summary
- **Application ID**: `com.helixios.helixioswall`
- **Minimum SDK**: 23 (Android 6.0)
- **Target SDK**: 34 (Android 14)
- **Primary Language**: Java
- **Architecture**: Fragment-based UI with Activity containers, Room persistence, Retrofit networking.
- **External Services**: Flickr API.

## Current Status
The project is in the **Discovery and Initialization** phase. It builds successfully but contains several legacy patterns and technical risks that require modernization.
