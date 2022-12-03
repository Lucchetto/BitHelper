//
//  SheetPicker.swift
//  iosApp
//
//  Created by Zhenxiang Chen on 26/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SheetPicker<Data, ID, Content>: View where Data : RandomAccessCollection, ID : Hashable, Content : View {
    
    @State private var showSheet = false
    @State private var fakeFalseBiding = false
    
    let data: Data
    let id: KeyPath<Data.Element, ID>
    let label: Text
    var busy = false
    @Binding var selection: ID
    let valueMapper: (Data.Element?) -> Text
    let sheetItem: (Data.Element) -> Content
    let sheetItemDisabled: (Data.Element) -> Bool
    
    var body: some View {
        Button(
            action: {
                if (!busy) {
                    showSheet = true
                }
        }, label: {
            let content = LabelValueItem(
                label: label,
                value: busy ? nil : valueMapper(data.first { $0[keyPath: id] == selection })
            )
            
            if (busy) {
                content
            } else {
                NavigationLink(destination: EmptyView(), isActive: $fakeFalseBiding) {
                    content
                }
            }
        })
        .foregroundColor(.primary)
        .sheet(isPresented: $showSheet) {
            SelectionSheet<Data, ID, Content>(label: label, data: data, id: id, selection: $selection, sheetItem: sheetItem, sheetItemDisabled: sheetItemDisabled)
        }
    }
}

private struct SelectionSheet<Data, ID, Content>: View where Data : RandomAccessCollection, ID : Hashable, Content : View {
    
    @Environment(\.presentationMode) var presentationMode
    
    let label: Text
    let data: Data
    let id: KeyPath<Data.Element, ID>
    @Binding var selection: ID
    let sheetItem: (Data.Element) -> Content
    let sheetItemDisabled: (Data.Element) -> Bool
    
    var body: some View {
        NavigationView {
            List(data, id: id) { item in
                
                let button = Button(
                    action: {
                        presentationMode.wrappedValue.dismiss()
                        selection = item[keyPath: id]
                    }
                ) {
                    HStack {
                        sheetItem(item)
                        Spacer()
                        let checkmark = Image(systemName: "checkmark")
                            .foregroundColor(.accentColor)
                        if (selection == item[keyPath: id]) {
                            checkmark
                        } else {
                            checkmark.hidden()
                        }
                    }
                }
                
                if (sheetItemDisabled(item)) {
                    button.disabled(true)
                } else {
                    button.foregroundColor(.primary)
                }
            }
            .navigationTitle(label)
            .navigationBarItems(
                leading: Button(
                    action: { presentationMode.wrappedValue.dismiss() }
                ) {
                    MokoText(MokoStrings.cancel)
                }
            )
        }
    }
}

/*struct SheetPicker_Previews: PreviewProvider {
    static var previews: some View {
        SheetPicker()
    }
}*/
