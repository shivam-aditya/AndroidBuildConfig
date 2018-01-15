
# react-native-android-build-config

This is an under development library

## Getting started

`$ npm install react-native-android-build-config --save`

### Mostly automatic installation

`$ react-native link react-native-android-build-config`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.androidbuildconfig.RNAndroidBuildConfigPackage;` to the imports at the top of the file
  - Add `new RNAndroidBuildConfigPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-build-config'
  	project(':react-native-android-build-config').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-build-config/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-build-config')
  	```


## Usage
```javascript
import RNAndroidBuildConfig from 'react-native-android-build-config';

// TODO: What to do with the module?
RNAndroidBuildConfig;
```
  