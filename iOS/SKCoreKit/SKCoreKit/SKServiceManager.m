//
//  SKServiceManager.m
//  SKCoreKit
//
//  Created by chao-chih lin on 11/1/14.
//  Copyright (c) 2014 chao-chih lin. All rights reserved.
//

#import "SKServiceManager.h"

static NSString * const HOST_STAGING = @"http://private-cf6fc-siegfried.apiary-mock.com";
static NSString * const HOST_PRODUCTION = @"http://private-cf6fc-siegfried.apiary-mock.com";

static NSString * const API_SIGNUP = @"/users/signup";
static NSString * const API_SIGNIN = @"/users/signin";

@implementation SKServiceManager

+ (instancetype)sharedInstance {
    static SKServiceManager * _instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        
        NSString * host = HOST_STAGING;
        
        _instance = [[SKServiceManager alloc] initWithBaseURL:[NSURL URLWithString:host]];
        [_instance setSecurityPolicy:[AFSecurityPolicy policyWithPinningMode:AFSSLPinningModePublicKey]];
        _instance.responseSerializer = [AFJSONResponseSerializer serializer];
        _instance.requestSerializer = [AFJSONRequestSerializer serializer];
        
    });
    
    return _instance;
}

- (void)signUp:(NSString*)account
      password:(NSString*)password
          meta:(NSDictionary *)meta
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure {
    
    NSDictionary * params = @{@"account":account,
                              @"password":password};
    
    [self POST:API_SIGNUP parameters:params success:^(NSURLSessionDataTask *task, id responseObject) {
        if ([SKServiceManager handleSuccessResponse:responseObject completion:completion failure:failure]) {
            if (success) {
                success();
            }
        }
        
    } failure:^(NSURLSessionDataTask *task, NSError *error) {
        [SKServiceManager handleFailureResponse:error completion:completion failure:failure];
    }];
    
}

- (void)signIn:(NSString*)account
      password:(NSString*)password
    completion:(void(^)())completion
       success:(void(^)())success
       failure:(void(^)(NSError* error))failure {
    
    NSDictionary * params = @{@"account":account,
                              @"password":password};
    
    [self POST:API_SIGNIN parameters:params success:^(NSURLSessionDataTask *task, id responseObject) {
        if ([SKServiceManager handleSuccessResponse:responseObject completion:completion failure:failure]) {
            if (success) {
                success();
            }
        }
        
    } failure:^(NSURLSessionDataTask *task, NSError *error) {
        [SKServiceManager handleFailureResponse:error completion:completion failure:failure];
    }];
    
    
}

#pragma mark - Privates

+ (BOOL)handleSuccessResponse:(NSDictionary*)response
                   completion:(void(^)())completion
                      failure:(void(^)(NSError*))failure {
    
    if (completion) {
        completion();
    }
    
    int errorCode = [[response objectForKey:@"status"] intValue];
    if (200!=errorCode) {
        if (failure) {
            // FIXME!
            //NSError * error = [APIManager createNSError:errorCode];
            failure(nil);
        }
        return NO;
    } else {
        return YES;
    }
}

+ (void)handleFailureResponse:(NSError*)error
                   completion:(void(^)())completion
                      failure:(void(^)(NSError*))failure {
    if (completion) {
        completion();
    }
    
    if (failure) {
        failure(error);
    }
}

@end
