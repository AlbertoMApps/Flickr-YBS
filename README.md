# Flickr-YBS
- Flickr app, interactive app consuming a public Flickr API, using Jetpack Compose, MVVM architecture and Coroutines.
  
  ## Main Features
- Represented a List of photos with Jetpack compose, displaying its username and tags or list of tags.
- Detailed list searched by ID in the Room database.
- The detailed list will show a bigger picture and some other descriptive data consumed by the API like, title, description, date taken.
- Search bar to filter by user name or tags.
- Tags and date taken formatted.

	## Architecture
- MVVM Architecture (Model - ComposableView - ViewModel).
- Repository pattern.
- Hilt - dependency injection.
- Data structure for remote and local data.
- Clean architecture pattern.
  
	## Technologies used and dependencies
- Kotlin - Main language of programming for Android based on Java.
- Jetpack Compose - Jetpack Compose is Android’s modern toolkit for building native UI.
- Coroutines - Fetching async data along flow.
- Flow - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.
- ViewModel - Stores UI-related data that isn't destroyed on UI changes.
- Dependency Injection - Hilt - Easier way to incorporate Dagger DI into Android apps.
- Retrofit - A type-safe HTTP client for Android and Java.
- Room database for caching data within the app and representing some data search in the detaied view.
- Material Components for Android - Modular and customizable Material Design UI components for Android.



![Screenshot_1689783987](https://github.com/AlbertoMApps/Flickr-YBS/assets/16687009/40d455c0-dd11-447d-8dc9-9ff8e859e116)
