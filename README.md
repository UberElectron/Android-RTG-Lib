# Android-RTG-Lib
Small lib for drawing Real Time Graphics on Android using canvas.

## About
I developed this lib so I don't have to rewrite this every time I need Real Time Graphics on an Android Studio Project (Mostly games, but I have try to do it "abstract" so it can be used for any app).

I have been developing some "games" using Android Studio. I know it's not ideal, but it's doable. I will create a lib including my stuff for "game dev on Android Studio" sometime.

I will create a Wiki or some docs sometime.


## How to use!
I'm trying Jitpack for releases so it's easy to add this library as a dependency.

If you want to know how to use it... Sory! I have not written the docs yet, so try to figure it out for now.

### How to add this library as a dependency in Android Studio:

1. Open your settings.gradle file and add this line to your repositories:
```
maven { url 'https://jitpack.io' }
```
It should look something like this:
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

2. Open build.gradle of your app and add the implementation (You should set the last version release):
```
implementation 'com.github.UberElectron:Android-RTG-Lib:1.0.0'
```


You can follow this guide from Jitpack, but it's not Android related (se follow this if you are not using Android Studio): https://docs.jitpack.io/

