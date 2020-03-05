//
//  ImageFramework.m
//  ImageFramework
//
//  Created by Yuriy Danilchenko on 05.03.2020.
//  Copyright © 2020 Yuriy Danilchenko. All rights reserved.
//

#import "ImageFramework.h"

@implementation ImageFramework

+ (NSString *_Nonnull)combineImages:(NSArray<UIImage *> * _Nonnull)images {
    if (images.count == 0) {
        return @"empty array";
    }
    UIImage *firstImage = [images firstObject];
    CGSize newImageSize = CGSizeMake(firstImage.size.width, firstImage.size.height);
    
    UIGraphicsBeginImageContextWithOptions(newImageSize, false, [UIScreen mainScreen].scale);
    
    for (UIImage *image in images) {
        [image drawAtPoint:CGPointZero];
    }
    
    UIImage *finalImage = UIGraphicsGetImageFromCurrentImageContext();
    
    UIGraphicsEndImageContext();
    
    if (finalImage == nil) {
        return @"empty context";
    }
    
    return [UIImagePNGRepresentation(finalImage) base64EncodedStringWithOptions:NSDataBase64Encoding64CharacterLineLength];
}

@end
