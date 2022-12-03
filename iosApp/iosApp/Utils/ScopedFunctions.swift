//
//  ScopedFunctions.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 05/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//


extension Optional {
    func `let`(do: (Wrapped)->()) {
        guard let v = self else { return }
        `do`(v)
    }
}
