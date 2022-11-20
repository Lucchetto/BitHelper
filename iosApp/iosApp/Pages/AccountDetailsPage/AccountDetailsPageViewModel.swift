//
//  AccountDetailsPageViewModel.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 19/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class AccountDetailsPageViewModel: AccountDetailsPageBaseViewModel, ObservableObject {
    
    @Published var accountApiKey: ResultWrapper<ApiKey, KotlinThrowable> = ResultWrapperLoading<ApiKey, KotlinThrowable>()
    @Published var accountBalances: ResultWrapper<NSArray, ExchangeApiError> = ResultWrapperLoading<NSArray, ExchangeApiError>()
    
    override init(apiKeyId: Int64) {
        super.init(apiKeyId: apiKeyId)
        
        asPublisher(accountBalancesFlow)
            .receive(on: DispatchQueue.main)
            .assign(to: &$accountBalances)
        
        asPublisher(accountApiKeyFlow)
            .receive(on: DispatchQueue.main)
            .assign(to: &$accountApiKey)
    }
}
