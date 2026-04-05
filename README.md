# 🌟 Celebrity Portfolio App

A professional, high-performance, and visually stunning Android application built using **Jetpack Compose**. This app showcases a curated list of world-renowned celebrities with a modern UI/UX design, featuring real-time data persistence and smooth animations.

---

## 🚀 Features

- **Modern Grid Dashboard**: A clean 2-column layout using `LazyVerticalGrid` featuring custom cards with circular image frames, shadows, and borders.
- **Dynamic Detail View**: Comprehensive profile pages for each celebrity including full-length descriptions and high-resolution images.
- **Persistent Support System**: A functional "Like" system powered by **Room Database** that persists data across app restarts.
- **Real-time Image Loading**: Integration with **Coil** to fetch and cache professional images directly from career-related URLs.
- **Material 3 Design**: Fully compliant with Material 3 standards, featuring:
    - Custom gradients for background depth.
    - `AnimatedContent` for dynamic like-count transitions.
    - Modern TopAppBars and interactive Surfaces.
- **Clean Architecture**: Built using the **MVVM** pattern with clear separation between UI, Business Logic, and Data.

---

## 🛠️ Tech Stack & Libraries

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (100% Kotlin-based UI)
- **Architecture**: MVVM (Model-View-ViewModel) + State Hoisting
- **Database**: [Room](https://developer.android.com/training/data-storage/room) (SQLite abstraction for local persistence)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) (Coroutine-based image fetching)
- **Navigation**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
- **Design**: Material 3 (M3)

---

## 📸 Screenshots

| Home Screen (Grid) | Detail Screen (Profile) |
|---|---|
| ![Home](https://via.placeholder.com/300x600?text=Home+Grid+View) | ![Detail](https://via.placeholder.com/300x600?text=Detail+Profile+View) |

---

## 📂 Project Structure

```text
CelebrityPortfolioApp/
├── .gradle/                 # Gradle build system temporary files
├── .idea/                   # Android Studio configuration files
├── .kotlin/                 # Kotlin compiler temporary files
├── app/                     # Main application module
│   ├── src/                 # Application source code
│   │   ├── main/
│   │   │   ├── java/com/example/celebrityportfolioapp/
│   │   │   │   ├── MainActivity.kt        # Entry point & Navigation
│   │   │   │   ├── CelebrityViewModel.kt  # State Management
│   │   │   │   ├── CelebrityRepository.kt # Data Repository
│   │   │   │   ├── Celebrity.kt           # Room Entity
│   │   │   │   ├── CelebrityDao.kt        # Room DAO
│   │   │   │   └── AppDatabase.kt         # Room Database
│   │   │   └── AndroidManifest.xml        # App Permissions
│   └── build.gradle.kts     # App-level build configuration
├── gradle/                  # Gradle wrapper files
├── .gitignore               # Files to be ignored by Git
├── build.gradle.kts         # Project-level build configuration
├── gradlew                  # Gradle wrapper executable
├── settings.gradle.kts      # Project settings and module list
└── README.md                # Project documentation
```

---

## ⚙️ Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Arslan-Ali157/MAD-mobile-application-development-.git
   ```
2. **Open in Android Studio**:
   - File > Open > Select `CelebrityPortfolioApp` folder.
3. **Build & Run**:
   - Ensure you have an active internet connection (to load celebrity images).
   - Select your emulator or physical device.
   - Click the **Run** button.

---

## 👤 Celebrities Featured

The app currently includes detailed portfolios for:
- **Cristiano Ronaldo** (Football Icon)
- **Lionel Messi** (Football Legend)
- **Elon Musk** (Tech Visionary)
- **Babar Azam** (Cricket Captain)
- **Shah Rukh Khan** (Bollywood King)
- **Virat Kohli** (Cricket Legend)

---

## 📝 License

This project was developed for the **Mobile Application Development (MAD)** course. Feel free to use it as a reference for modern Jetpack Compose development.
