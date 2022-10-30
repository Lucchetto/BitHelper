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
                TextField("Label", text: $viewModel.label)
                TextField("API key", text: $viewModel.apiKey)
            }
            .navigationTitle("Add new API key")
            .navigationBarItems(
                trailing: Button("Save") {
                    viewModel.addApiKey()
                    presentationMode.wrappedValue.dismiss()
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
