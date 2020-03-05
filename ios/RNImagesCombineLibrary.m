
#import "RNImagesCombineLibrary.h"
#import "ImageFramework.h"

@implementation RNImagesCombineLibrary

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE();

RCT_REMAP_METHOD(combineImages,
                 inputImages: (NSArray<NSDictionary *> *)inputImages
                 resolver: (RCTPromiseResolveBlock)resolve
                rejecter:(RCTPromiseRejectBlock)reject)
{
    NSMutableArray * imagesArray = [[NSMutableArray alloc] init];
    
    int i;
    for (i = 0; i < [inputImages count]; i++) {
      id myArrayElement = [inputImages objectAtIndex:i];
      [imagesArray addObject:myArrayElement];
    }
    
    NSArray *myarray = [imagesArray copy];

    
    NSString *base = [ImageFramework combineImages:myarray];
      if(YES) {
        resolve(base);
      } else {
        
      }
}

@end
  
