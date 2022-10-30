//
//  ArrayUtils.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

extension NSArray {
    
    func toArray<T>() -> Array<T> {
        return compactMap({ $0 as? T })
    }
}
