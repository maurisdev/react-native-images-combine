
# react-native-images-combine-library

## Getting started

`$ npm install react-native-images-combine-library --save`

### Mostly automatic installation

`$ react-native link react-native-images-combine-library`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-images-combine-library` and add `RNImagesCombineLibrary.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNImagesCombineLibrary.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNImagesCombineLibraryPackage;` to the imports at the top of the file
  - Add `new RNImagesCombineLibraryPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-images-combine-library'
  	project(':react-native-images-combine-library').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-images-combine-library/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-images-combine-library')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNImagesCombineLibrary.sln` in `node_modules/react-native-images-combine-library/windows/RNImagesCombineLibrary.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Images.Combine.Library.RNImagesCombineLibrary;` to the usings at the top of the file
  - Add `new RNImagesCombineLibraryPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNImagesCombineLibrary from 'react-native-images-combine-library';

// TODO: What to do with the module?
RNImagesCombineLibrary;
```
  