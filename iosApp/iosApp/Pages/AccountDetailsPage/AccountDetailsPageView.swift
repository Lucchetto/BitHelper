//
//  AccountDetailsPageView.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 19/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import Shimmer

struct AccountDetailsPageView: View {
    
    static let tabs: [PickerItem] = [
        PickerItem(id: 0, labelRes: MokoStrings.overview_title),
        PickerItem(id: 1, labelRes: MokoStrings.assets_title),
    ]
    
    @State var selectedTabId = 0
    @StateObject var viewModel: AccountDetailsPageViewModel
    
    init(apiKeyId: Int64) {
        self._viewModel = StateObject(
            wrappedValue: AccountDetailsPageViewModel(apiKeyId: apiKeyId)
        )
    }
    
    var body: some View {
        VStack {
            Picker("", selection: $selectedTabId) {
                ForEach(AccountDetailsPageView.tabs) { option in
                    MokoText(option.labelRes)
                }
            }.pickerStyle(SegmentedPickerStyle())
            TabView(selection: $selectedTabId) {
                Overview(viewModel.accountApiKey).tag(AccountDetailsPageView.tabs[0].id)
                Assets(accountBalances: viewModel.accountBalances).tag(AccountDetailsPageView.tabs[1].id)
            }.tabViewStyle(.page(indexDisplayMode: .never))
        }.navigationTitle(MokoText(MokoStrings.account_details_page_title)).onDisappear {
            viewModel.onDestroy()
        }
    }
}

private struct Overview: View {
    
    let apiKey: ApiKey?
    
    init(_ apiKeyWrapper: ResultWrapper<ApiKey, KotlinThrowable>) {
        apiKey = (apiKeyWrapper as? ResultWrapperSuccess<ApiKey, KotlinThrowable>)?.data
    }
    
    var body: some View {
        if let it = apiKey {
            Text(verbatim: it.label)
        } else {
            MokoText(MokoStrings.shimmer_placeholder_title)
                .redacted(reason: .placeholder)
                .shimmering()
        }
    }
}

private struct Assets: View {
    
    let accountBalances: ResultWrapper<NSArray, ExchangeApiError>
    
    var body: some View {
        switch accountBalances {
        case let it as ResultWrapperSuccess<NSArray, ExchangeApiError>:
            let assetBalances = it.data as! [AssetBalance]
            List(assetBalances, id: \.self.ticker) { asset in
                HStack {
                    Text(verbatim: asset.ticker)
                    Spacer()
                    Text(verbatim: String(format: "%f", asset.availableBalance))
                }
            }
        default:
            Text("IDK")
        }
    }
}

struct PickerItem: Identifiable {
    let id: Int
    let labelRes: StringResource
}

struct AccountDetailsPageView_Previews: PreviewProvider {
    static var previews: some View {
        AccountDetailsPageView(apiKeyId: 0)
    }
}
