
# react-native-images-combine
React Native Library for combine images

## Getting started

`$ npm install react-native-images-combine --save`


#### iOS
1. cd ios
2. pod install


## Usage
```javascript
import ImagesCombineLibrary from 'react-native-images-combine';

ImagesCombineLibrary.combineImages([
  Image.resolveAssetSource(require('../images/test1.png')),
  Image.resolveAssetSource(require('../images/test2.png'))
]).then((base64) => {
              
});
```
  
