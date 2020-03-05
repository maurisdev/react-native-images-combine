
#import "RNImagesCombineLibrary.h"
#import <ImageFramework/ImageFramework.h>

@implementation RNImagesCombineLibrary

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show:(NSString *)text)
{
  //[ImageLibrary combineImagesWithImages:(NSArray<UIImage *> * _Nonnull)]

}

@end
  
