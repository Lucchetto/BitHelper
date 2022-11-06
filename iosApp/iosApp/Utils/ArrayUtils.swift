//
//  ArrayUtils.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension NSArray {
    
    func toArray<T>() -> Array<T> {
        return compactMap({ $0 as? T })
    }
}

extension Array where Element: AnyObject {
    /// Allows conversion from `KotlinArray` to `Array`
    public init(_ array: KotlinArray<Element>) {
        self.init()
        self.reserveCapacity(Int(array.size))
        let iterator = array.iterator()
        while iterator.hasNext() {
            self.append(iterator.next() as! Element)
        }
    }
}
