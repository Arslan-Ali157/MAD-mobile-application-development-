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

## 📂 Project Structure
