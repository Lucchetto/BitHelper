//
//  AddApiKeySheetViewModel.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class AddApiKeySheetViewModel: AddApiKeySheetBaseViewModel, ObservableObject {
    
    @Published var apiKey: String = ""
    @Published var label: String = ""
    
    func addApiKey() {
        addApiKey(
            apiKey: ApiKey(
                id: 0,
                apiKey: apiKey,
                exchange: Exchange.ftx,
                label: label,
                secretKey: nil,
                creationTimestamp: Int64(NSDate().timeIntervalSince1970) * 1000,
                readOnly: nil
            )
        )
    }
}
