//
//  LabelValueItem.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 04/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LabelValueItem: View {
    
    let label: Text
    let value: Text?
    
    var body: some View {
        HStack {
            label
            Spacer()
            if (value == nil) {
                ProgressView()
            } else {
                value.foregroundColor(.secondary)
            }
        }
    }
}

struct LabelValueItem_Previews: PreviewProvider {
    static var previews: some View {
        LabelValueItem(
            label: Text(verbatim: "Label"),
            value: Text("Value")
        )
        LabelValueItem(
            label: Text(verbatim: "Label"),
            value: nil
        )
    }
}
