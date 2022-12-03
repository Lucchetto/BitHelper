//
//  ResultWrapperExtensions.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 05/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared

final class ResultWrapperUtils {
    
    private init() {}
    
    static func successDataOrNull<T, E>(_ result: ResultWrapper<T, E>) -> T? {
        return (result as? ResultWrapperSuccess<T, E>)?.data
    }
}
