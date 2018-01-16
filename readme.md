[![Build Status](https://travis-ci.org/amvnetworks/amv-access-sdk-android.svg?branch=master)](https://travis-ci.org/amvnetworks/amv-access-sdk-android)
[![Jitpack](https://jitpack.io/v/amvnetworks/amv-access-sdk-android.svg)](https://jitpack.io/#amvnetworks/amv-access-sdk-android)


amv-access-sdk-android
======================

# getting started
## setup
```bash
git clone https://github.com/amvnetworks/amv-access-sdk-android.git
```

## build
```bash
./gradlew clean build
```

### ide
* Open the project in Android Studio
* minSdkVersion for android is 23
* gradle version 4.3

As this project uses [Project Lombok](https://projectlombok.org/) make sure you have the
[IntelliJ Lombok Plugin](https://github.com/mplushnikov/lombok-intellij-plugin) installed and
annotation processing enabled.


### integration
To start integrating the sdk you must choose an implementation of the `amv-access-sdk-spi` module.
Currently there is only one such implementation which is `amv-hm-access-sdk`.

**Step 1.** Add the following lines to your root `build.gradle` at the end of repositories:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the `libs/` folder to your apps repositories:
```groovy
    repositories {
        ...
        flatDir {
            dirs 'libs'
        }
    }
```

**Step 3.** Copy the `hmkit-android-*.aar` file from `amv-hm-access-sdk/libs` to your own `libs/` directory and
and the following lines to your repositories:
```groovy
    repositories {
        ...
        flatDir {
            dirs 'libs'
        }
    }
```

**Step 4.** Add the dependency:

```groovy
dependencies {
    ...
    implementation(name: 'hmkit-android', version: '1.0.11', ext: 'aar')
    implementation 'com.github.amvnetworks.amv-access-sdk-android:amv-hm-access-sdk:v0.0.1'
}
```

    
