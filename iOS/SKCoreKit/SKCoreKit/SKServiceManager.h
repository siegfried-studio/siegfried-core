//
//  SKServiceManager.h
//  SKCoreKit
//
//  Created by chao-chih lin on 11/1/14.
//  Copyright (c) 2014 chao-chih lin. All rights reserved.
//

#import "AFHTTPSessionManager.h"

@interface SKServiceManager : AFHTTPSessionManager
+ (instancetype)sharedInstance;

- (void)signUp:(NSString*)account
      password:(NSString*)password
          meta:(NSDictionary*)meta
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure;

- (void)signIn:(NSString*)account
      password:(NSString*)password
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure;



@end
