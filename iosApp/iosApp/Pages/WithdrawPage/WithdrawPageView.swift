//
//  WithdrawPageView.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 26/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct WithdrawPageView: View {
    
    @StateObject var viewModel: WithdrawPageViewModel
    
    var body: some View {
        Form {
            TextField(MokoStrings.recipient_address_title.localized, text: $viewModel.recipientAddress)
            TextField(MokoStrings.memo_title.localized, text: $viewModel.recipientMemo)
            
            let methods = ((viewModel.withdrawMethods as? ResultWrapperSuccess<NSArray, ExchangeApiError>)?.data as! [WithdrawMethod]?) ?? []
            
            SheetPicker(
                data: methods,
                id: \.self.exchangeInternalId,
                label: MokoText(MokoStrings.withdraw_method_title),
                busy: viewModel.withdrawMethods is ResultWrapperLoading,
                selection: $viewModel.withdrawMethodId,
                valueMapper: { value in
                    if let it = value?.name {
                        return Text(it)
                    } else {
                        return MokoText(MokoStrings.select_method_hint)
                    }
                },
                sheetItem: { item in
                    WithdrawMethodView(method: item, assetTicker: viewModel.assetTicker)
                },
                sheetItemDisabled: { !$0.available }
            ).disabled(methods.isEmpty)
            
            if let it = viewModel.selectedWithdrawMethod?.fee {
                Text(MokoStrings.withdrawal_fee_value.format(args_: [it.toStringExpanded(), viewModel.assetTicker]).localized()).font(.headline)
            }
        }
        .navigationTitle(MokoStrings.withdraw_page_title.format(args_: [viewModel.assetTicker]).localized())
        .navigationBarItems(
            trailing: Button(
                action: { viewModel.withdraw() }
            ) {
                MokoText(MokoStrings.withdraw)
            }.disabled(viewModel.formInvalid)
        )
        .onDisappear {
            viewModel.onDestroy()
        }
    }
}

struct WithdrawMethodView: View {
    
    let method: WithdrawMethod
    let assetTicker: String
    
    var body: some View {
        VStack(
            alignment: .leading
        ) {
            Text(method.name).font(.headline)
            if let it = method.fee {
                if (it.isZero()) {
                    MokoText(MokoStrings.free).font(.subheadline)
                } else {
                    Text(
                        MokoStrings.fee_value_and_unit.format(args_: [it.toStringExpanded(), assetTicker]).localized()
                    ).font(.subheadline)
                }
            }
            if let it = method.description_ {
                Text(it).font(.caption)
            }
        }
    }
}

struct WithdrawPageView_Previews: PreviewProvider {
    static var previews: some View {
        WithdrawPageView(
            viewModel: WithdrawPageViewModel(apiKeyId: 0, assetTicker: "ETH")
        )
    }
}
