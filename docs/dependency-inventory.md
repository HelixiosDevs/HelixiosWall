# Dependency Inventory

| Dependency | Current Version | Purpose | Status |
| :--- | :--- | :--- | :--- |
| **AGP** | 7.4.2 | Android Gradle Plugin | Outdated |
| **Gradle** | 7.5 | Build System | Outdated |
| **AppCompat** | 1.2.0 | AndroidX AppCompat | Old |
| **Material** | 1.2.1 | Material Design Components | Old |
| **Room** | 2.3.0 | Local Database | Old |
| **Retrofit** | 2.9.0 | Networking | Current |
| **Glide** | 4.12.0 | Image Loading | Old |
| **Lottie** | 3.7.0 | Animations | Old |
| **Android-Image-Cropper** | 3.1.1 | Image Cropping | Old |
| **ConstraintLayout** | 2.0.4 | UI Layouts | Old |
| **Lifecycle** | 2.2.0 | Lifecycle/ViewModel | Old |
| **Navigation** | 2.2.2 | Jetpack Navigation | Old |

## Modernization Required
- **Gradle/AGP**: Upgrade to 8.x and latest Gradle for better performance and support.
- **AndroidX Libraries**: Most libraries are 2-4 years old and should be updated to stable versions.
- **Storage**: Move away from legacy storage permissions.
