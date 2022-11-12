//
//  AccountsPageViewModel.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class AccountsPageViewModel: AccountsPageBaseViewModel, ObservableObject {
    
    @Published var accountList: [IdentifiableWrapper<ApiKey, String>] = []
    
    override init() {
        super.init()
        
        asPublisher(accountListFlow)
            .compactMap {
                ($0 as! [ApiKey]).compactMap({ item in IdentifiableWrapper(value: item, id: item.apiKey) })
            }
            .receive(on: DispatchQueue.main)
            .assign(to: &$accountList)
    }
}
