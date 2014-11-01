//
//  SKServiceManager.h
//  SKCoreKit
//
//  Created by chao-chih lin on 11/1/14.
//  Copyright (c) 2014 chao-chih lin. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SKServiceManager : NSObject
+ (instancetype)sharedInstance;

- (void)signup:(NSString*)account
      password:(NSString*)password
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure;

- (void)signin:(NSString*)account
      password:(NSString*)password
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure;



@end
