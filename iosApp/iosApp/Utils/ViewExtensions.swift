//
//  ViewExtensions.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 06/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

extension TextField {
    
    func apiKeyField() -> some View {
        return disableAutocorrection(true).autocapitalization(.none)
    }
}

extension Binding<String> {
    func trimWhitespaces() -> Binding<String> {
        return Binding(
            get: { self.wrappedValue },
            set: { self.wrappedValue = $0.trimmingCharacters(in: .whitespacesAndNewlines) }
        )
    }
}
