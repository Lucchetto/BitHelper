//
//  AddApiKeySheet.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct AddApiKeySheet: View {
    
    @StateObject var viewModel = AddApiKeySheetViewModel()
    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        NavigationView {
            Form {
                TextField(MokoStrings.label_title.localized, text: $viewModel.label)
                TextField(MokoStrings.api_key_title.localized, text: $viewModel.apiKey)
            }
            .navigationTitle(MokoText(MokoStrings.add_api_key_sheet_title))
            .navigationBarItems(
                trailing: Button(action: {
                    viewModel.addApiKey()
                    presentationMode.wrappedValue.dismiss()
                }) {
                    MokoText(MokoStrings.add)
                }
            )
        }
    }
}

struct AddApiKeySheet_Previews: PreviewProvider {
    static var previews: some View {
        AddApiKeySheet()
    }
}
