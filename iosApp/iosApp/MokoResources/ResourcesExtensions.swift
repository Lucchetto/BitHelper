//
//  ResourcesExtensions.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 04/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

var MokoStrings: SharedRes.strings {
    get {
        return SharedRes.strings()
    }
}

func MokoText(resource: StringResource) -> Text {
    Text(LocalizedStringKey(resource.resourceId), bundle: SharedRes.strings().nsBundle)
}
