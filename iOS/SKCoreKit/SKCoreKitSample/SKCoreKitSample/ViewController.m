//
//  ViewController.m
//  SKCoreKitSample
//
//  Created by chao-chih lin on 11/2/14.
//  Copyright (c) 2014 chao-chih lin. All rights reserved.
//

#import "ViewController.h"
#import "SKServiceManager.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [[SKServiceManager sharedInstance] signUp:@""
                                     password:@""
                                         meta:nil
    completion:^{
        
    } success:^{
        NSLog(@"Sign Up!");
        
    } failure:^(NSError *error) {
        NSLog(@"%@", error);
        
    }];
    
    [[SKServiceManager sharedInstance] signIn:@"" password:@"" completion:^{
        
    } success:^{
        NSLog(@"Sign In!");
        
    } failure:^(NSError *error) {
        NSLog(@"%@", error);
        
    }];
    
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
