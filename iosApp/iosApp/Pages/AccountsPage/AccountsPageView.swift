//
//  AccountsPageView.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AccountsPageView: View {
    
    @State private var showSheet = false
    @StateObject var viewModel = AccountsPageViewModel()
    
    var body: some View {
        NavigationView {
            List(viewModel.accountList) { item in
                AccountView(apiKey: item.value)
            }
            .navigationTitle(
                MokoText(MokoStrings.accounts_page_title)
            )
            .navigationBarItems(trailing:
                Button(action: {
                    showSheet = true
                }) {
                    Image(systemName: "plus").imageScale(.large)
                }
            )
            .sheet(isPresented: $showSheet) {
                AddAccountSheetView()
            }
        }
    }
}

private struct AccountView: View {
    
    let apiKey: ApiKey
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(apiKey.label)
            Text(apiKey.apiKey)
            MokoText(apiKey.exchange.labelRes)
        }
    }
}

struct AccountsPageView_Previews: PreviewProvider {
    static var previews: some View {
        AccountsPageView()
    }
}
