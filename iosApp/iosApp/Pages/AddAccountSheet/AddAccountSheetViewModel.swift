//
//  AddAccountSheetViewModel.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 30/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class AddAccountSheetViewModel: AddAccountSheetBaseViewModel, ObservableObject {
    
    let exchanges = Array(Exchange.values())
    
    @Published var apiKey: String = ""
    @Published var label: String = ""
    @Published var secretKey: String = ""
    @Published var password: String = ""
    @Published var selectedExchangeIndex: Int = -1
    
    var formInvalid: Bool {
        apiKey.isBlank || label.isBlank || secretKey.isBlank || selectedExchangeIndex < 0
    }
    
    func addAccount() {
        addAccount(
            apiKey: ApiKey(
                id: 0,
                apiKey: apiKey,
                exchange: exchanges[selectedExchangeIndex],
                label: label,
                secretKey: secretKey,
                password: password,
                creationTimestamp: Int64(NSDate().timeIntervalSince1970) * 1000,
                readOnly: nil
            )
        )
    }
}
