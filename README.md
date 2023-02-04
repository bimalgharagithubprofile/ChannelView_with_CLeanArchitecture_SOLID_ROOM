# What parts of the test did you find challenging and why?
 1. Failed to run androidTest because mockk uses MethodHandle.invokeExact which is supported from Android O, And our app supports from Android Kitkat
 2. NoClassDefFoundError: PlatformTestStorageRegistry because default app tried to use androidx.test:monitor:1.4.0 (extensive framework), But should androidx.test:monitor:1.6.0
 3. Hilt Fragments must be attached to an @AndroidEntryPoint Activity because Hilt does not currently support FragmentScenario
 4. A common issue for all develop - blocking UI when Coroutines in-progress. as usual had to use IdlingResource. Because espresso doesn't do well job with Coroutines yet 

# What feature would you like to add in the future to improve the project?

- Recommended Section
- Add/Remove to Favourites
- Search option