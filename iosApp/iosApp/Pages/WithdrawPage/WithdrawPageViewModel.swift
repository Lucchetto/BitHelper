//
//  WithdrawPageViewModel.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 26/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class WithdrawPageViewModel: WithdrawPageBaseViewModel, ObservableObject {
    
    @Published var recipientAddress: String = ""
    @Published var recipientMemo: String = ""
    @Published var withdrawMethodId: String = ""
    
    @Published var withdrawMethods: ResultWrapper<NSArray, ExchangeApiError> = ResultWrapperLoading()
    
    override init(apiKeyId: Int64, assetTicker: String) {
        super.init(apiKeyId: apiKeyId, assetTicker: assetTicker)
        
        asPublisher(withdrawMethodsFlow)
            .receive(on: DispatchQueue.main)
            .assign(to: &$withdrawMethods)
            
    }
    
    private var selectedWithdrawMethod: WithdrawMethod? {
        let it = withdrawMethodId
        
        if (it.isBlank) {
            return nil
        } else {
            let withdrawMethodsList = (withdrawMethods as? ResultWrapperSuccess)?.data as? [WithdrawMethod]
            return withdrawMethodsList?.first(where: { $0.exchangeInternalId == it })
        }
    }
    
    var formInvalid: Bool {
        recipientAddress.isBlank || selectedWithdrawMethod == nil
    }
}
