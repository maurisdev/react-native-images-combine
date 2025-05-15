
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
Alternatively if you have http url, local file uri **android and iOS** or base64 **iOS only** use the following syntax
```javascript
import ImagesCombineLibrary from 'react-native-images-combine';

const image1 = httpUrlAsset // any http url https://examplesite.com/example-image.png
const image2 = fileUriAsset // any file uri asset example file:///data/user/0/com.myapp//cache/tempitem.png
const image3 = base64Asset // any base64 asset (iOS only)
      ImagesCombineLibrary.combineImages([
        { uri: image1 },
        { uri: image2 },
        { uri: image3 }
      ]).then(base64 => {
        // do something here
      })
```

  
