//
//  ApiKeysListPageView.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ApiKeysListPageView: View {
    
    @State private var showSheet = false
    @StateObject var viewModel = ApiKeysListPageViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                List(viewModel.apiKeysList) { item in
                    ApiKeyView(apiKey: item.value)
                }
            }
            .navigationTitle(
                Text(LocalizedStringKey(SharedRes.strings().api_keys_list_page_title.resourceId), bundle: SharedRes.strings().nsBundle)
            )
            .navigationBarItems(trailing:
                Button(action: {
                    showSheet = true
                }) {
                    Image(systemName: "plus").imageScale(.large)
                }
            )
            .sheet(isPresented: $showSheet) {
                AddApiKeySheet()
            }
        }
    }
}

private struct ApiKeyView: View {
    
    let apiKey: ApiKey
    
    var body: some View {
        Text(apiKey.label)
    }
}

struct ApiKeysListPageView_Previews: PreviewProvider {
    static var previews: some View {
        ApiKeysListPageView()
    }
}
