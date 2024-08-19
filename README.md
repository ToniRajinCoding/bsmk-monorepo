<h1>Overview</h1>
Besok Masak is an Android application that utilizes AI to generate recipe ideas based on user-provided ingredients. The app aims to reduce food waste and inspire culinary creativity.

<h2>Tech Stack</h2>
Frontend: Kotlin, MVVM, Coroutines, Flow, Dagger Hilt, AdMob, Room
Backend: Firebase Auth, Firebase Datastore, Firebase Crashlytics, Google Cloud Run, Node.js

<h2>Features</h2>
AI-powered recipe generation based on user-provided ingredients
User-friendly interface for inputting ingredients and preferences
AdMob integration for monetization
Offline functionality using Room database
User authentication and data storage with Firebase Auth and Firebase Datastore
Crash reporting and analytics with Firebase Crashlytics
Scalable backend infrastructure using Google Cloud Run and Node.js

<h2>Architecture</h2>
MVVM: Separates concerns between Model, View, and ViewModel for better maintainability and testability.
Coroutines and Flow: Handles asynchronous operations efficiently and manages data flow.
Dagger Hilt: Manages dependency injection for clean code and testability.
Room: Provides local data storage for offline functionality.
Firebase: Handles user authentication, data storage, and crash reporting.
Google Cloud Run: Deploys the backend API for scalability.
Node.js: Implements the backend logic for recipe generation and other API endpoints.
