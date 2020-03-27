
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

### Web Images, DynamicFiles, or Base64
Alternatively if you have a dynamic url, temp file, or gallery path use the following syntax
```javascript
import ImagesCombineLibrary from 'react-native-images-combine';

const image1 = base64Asset // any base64 asset
const image2 = fileUriAsset // any file url asset example file:///data/user/0/com.myapp//cache/tempitem.png
      ImagesCombineLibrary.combineImages([
        { uri: image1 },
        { uri: image2 },
      ]).then(base64 => {
        // do something here
      })
```

  
