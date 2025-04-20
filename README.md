# ByteClub - Multi-Utility Android App

ByteClub is a modern Android application built with Kotlin that combines multiple utilities including trivia, cryptocurrency information, and weather features. The app follows Clean Architecture principles and is built using the latest Android development practices.

## Features

- **Trivia Quiz**: Integration with QuizAPI.io for engaging trivia questions
- **Cryptocurrency Tracking**: Real-time crypto information using KuCoin API
- **Weather Information**: Current weather conditions and forecasts
- **Clean Architecture**: Organized codebase following SOLID principles
- **Modern UI**: Built with Jetpack Compose Material 3

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: Clean Architecture with MVVM pattern
- **Dependency Injection**: Hilt
- **Networking**: Retrofit with Kotlin Serialization
- **Local Storage**: Room Database
- **State Management**: Kotlin Flow & StateFlow
- **Image Loading**: Coil
- **Charts**: Vico Charts
- **Firebase Services**:
  - Analytics
  - Authentication
  - Firestore
  - Crashlytics

## Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: 26 (Android 8.0)
- Target SDK: 35
- JDK 8

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Add required API keys in local.properties:
   ```
   QUIZ_API_KEY=your_quiz_api_key
   KUCOIN_API_KEY=your_kucoin_api_key
   WEATHER_API_KEY=your_weather_api_key
   ```
5. Build and run the project

## Build Variants

The app has the following build variants:
- debug: Development build with debugging enabled
- release: Optimized build with ProGuard rules, shrinking, and minification enabled

## Architecture

The project follows Clean Architecture principles with the following layers:
- **Presentation**: Compose UI components and ViewModels
- **Domain**: Use cases and business logic
- **Data**: Repositories and data sources
- **Framework**: External service implementations

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Version

Current version: 2.1.1 (Build 211)

## License

This project is licensed under the MIT License - see the LICENSE file for details

## Acknowledgments

- [QuizAPI.io](https://quizapi.io) for trivia data
- [KuCoin](https://www.kucoin.com) for cryptocurrency data
- Weather data provider
- All other open-source libraries used in this project
