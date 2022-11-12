//
//  MainNavigationView.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 12/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainNavigationView: View {
    
    @State private var selectedTab = 0
    var body: some View {
        TabView(selection: $selectedTab) {
            AccountsPageView()
                .tabItem({
                    Label(MokoStrings.accounts_page_title.localized, systemImage: "person.circle")
                    MokoText(MokoStrings.accounts_page_title)
                })
                .tag(0)
        }
    }
}

struct MainNavigationView_Previews: PreviewProvider {
    static var previews: some View {
        MainNavigationView()
    }
}
