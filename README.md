# BookKeeper

BookKeeper is an Android application designed to save books. It utilizes the Room database for data persistence, follows the MVVM (Model-View-ViewModel) architecture, and incorporates Jetpack Compose for building the UI.

## Features

- Add the book's ID and title to the Room database
- View saved books: Access your entire collection at any time.
- Edit or delete books: Update book details or remove entries from your collection as needed.

## Screenshots

<p align="center">
  <img src="https://github.com/AlaaMaher/book-keeper/assets/16047640/60419597-f0ac-4f22-bcbc-f4c61f02f41b" width="350" title="1">
  <img src="https://github.com/AlaaMaher/book-keeper/assets/16047640/508474ad-21a4-42a0-8c11-de0bec914320" width="350" alt="2">
</p>

## Libraries Used

- **Jetpack Compose**: For building the UI with a modern and declarative approach.
- **Room Database**: For local data storage and management.
- **ViewModel**: To manage UI-related data in a lifecycle-conscious way.
- **State**: To collect data and recompose the UI.
- **Coroutine**: For managing background threads with simplified code and reducing the need for callbacks.
- **Navigation Component**: For handling navigation between different screens.
