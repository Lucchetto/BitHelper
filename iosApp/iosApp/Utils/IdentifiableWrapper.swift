//
//  KotlinUtils.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

struct IdentifiableWrapper<T, ID: Hashable>: Identifiable {
    let value: T
    let id: ID
}
