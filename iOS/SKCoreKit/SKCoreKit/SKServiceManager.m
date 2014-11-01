//
//  SKServiceManager.m
//  SKCoreKit
//
//  Created by chao-chih lin on 11/1/14.
//  Copyright (c) 2014 chao-chih lin. All rights reserved.
//

#import "SKServiceManager.h"

@implementation SKServiceManager

+ (instancetype)sharedInstance {
    static SKServiceManager * _instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[SKServiceManager alloc] init];
    });
    
    return _instance;
}

- (void)signup:(NSString*)account
      password:(NSString*)password
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure {
    
}

- (void)signin:(NSString*)account
      password:(NSString*)password
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure {
    
}

@end
