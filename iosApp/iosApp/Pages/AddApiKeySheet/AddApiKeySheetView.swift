//
//  AddApiKeySheet.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AddApiKeySheet: View {
    
    @StateObject var viewModel = AddApiKeySheetViewModel()
    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        NavigationView {
            Form {
                TextField(MokoStrings.label_title.localized, text: $viewModel.label)
                TextField(MokoStrings.api_key_title.localized, text: $viewModel.apiKey)
                TextField(MokoStrings.secret_key_title.localized, text: $viewModel.secretKey)
                Picker(MokoStrings.exchange_title.localized, selection: $viewModel.selectedExchangeIndex) {
                    MokoText(MokoStrings.select_an_exchange_hint).tag(-1).id(-1)
                    ForEach(0 ..< viewModel.exchanges.count, id: \.self) { index in
                        MokoText(viewModel.exchanges[index].labelRes)
                    }
                }
            }
            .navigationTitle(MokoText(MokoStrings.add_api_key_sheet_title))
            .navigationBarItems(
                trailing: Button(action: {
                    viewModel.addApiKey()
                    presentationMode.wrappedValue.dismiss()
                }) {
                    MokoText(MokoStrings.add)
                }.disabled(viewModel.formInvalid)
            )
        }
    }
}

struct AddApiKeySheet_Previews: PreviewProvider {
    static var previews: some View {
        AddApiKeySheet()
    }
}
