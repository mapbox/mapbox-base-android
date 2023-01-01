## Mapbox Base Android
This repository hosts annotation processors and module providers for Mapbox modular SDKs.

For documentation see [MODULARIZATION.md](https://github.com/mapbox/mapbox-base-android/blob/master/MODULARIZATION.md).

## Configure credentials
Before installing the SDK, you will need to gather sensitive information from your Mapbox account. 
If you don't have a Mapbox account: [sign up](https://account.mapbox.com/auth/signup/) and navigate to your [Account page](https://account.mapbox.com/). 
You'll need a secret access token with the `Downloads:Read` scope:
1. From your account's [tokens page](https://account.mapbox.com/access-tokens/), click the **Create a token** button.
2. From the token creation page, give your token a name and make sure the box next to the `Downloads:Read` scope is checked.
3. Click the **Create token** button at the bottom of the page to create your token.
4. The token you've created is a *secret token*, which means you will only have one opportunity to copy it somewhere secure.

You should not expose these access tokens in publicly-accessible source code where unauthorized users might find them. Instead, you should store them somewhere safe on your computer and take advantage of [Gradle properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties) to make sure they're only added when your app is compiled. Once this configuration step has been completed, you will be able to reference your credentials in other parts of your app.

test
