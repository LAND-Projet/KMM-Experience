//
//  YanoteItem.swift
//  iosYanoteApp
//
//  Created by Darren on 2022-11-11.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct YanoteItem: View {
    var yanote: Yanote
    var onDeleteClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
                    HStack {
                        Text(yanote.title)
                            .font(.title3)
                            .fontWeight(.semibold)
                        Spacer()
                        Button(action: onDeleteClick) {
                            Image(systemName: "xmark").foregroundColor(.black)
                        }
                    }.padding(.bottom, 3)
                    
                    Text(yanote.content)
                        .fontWeight(.light)
                        .padding(.bottom, 3)
                    
                    HStack {
                        Spacer()
                        Text(DateTimeUtil().formatYanoteDate(dateTime: yanote.created))
                            .font(.footnote)
                            .fontWeight(.light)
                    }
                }
                .padding()
                .background(Color(hex: yanote.colorHex))
                .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

struct YanoteItem_Previews: PreviewProvider {
    static var previews: some View {
        YanoteItem(
            yanote: Yanote(id: nil, title: "My note", content: "text text", colorHex: 0xFF2341, created: DateTimeUtil().now()),
            onDeleteClick: {}
        )
    }
}
