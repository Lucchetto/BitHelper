//
//  StringExtensions.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 06/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

extension String {
    
    /// A Boolean value indicating whether a string has no characters or only whitespaces.
    var isBlank: Bool {
        return allSatisfy({ $0.isWhitespace })
    }
}
