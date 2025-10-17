
## Overview
This is a modularized Android project built using Jetpack Compose, MVVM, and Hilt.  
The app demonstrates a user list screen and a detail screen, fetching data from a sample API.

## Technologies Used
- Kotlin
- Jetpack Compose (UI)
- ViewModel + StateFlow (State management)
- Hilt (Dependency Injection)
- Retrofit (API calls)
- Coroutines (Asynchronous programming)
- Modularized architecture (core & feature modules)
- JUnit & MockK (Unit testing)
- Compose UI Testing

## Features Implemented
- List of users fetched from a sample API.
- Detail view accessible when clicking a user.
- Simple error and loading handling.
- Unit tests for Repository and ViewModel.

## Assumptions
- API always returns a list of users in a predictable format.
- Only basic error handling implemented (network failures).
- User detail screen shows minimal information.
- No persistent storage implemented.

## Possible Improvements
- Implement offline caching with Room or DataStore.
- Add pagination for large datasets.
- Enhance UI with Material3 theming and animations.

## Project Structure
app/
feature/ # Feature module containing UserListScreen. UserDetailScreen and ViewModel
core/ # Core module with models, repository, API service