//
//  YanoteDetailScreen.swift
//  iosYanoteApp
//
//  Created by Darren on 2022-11-11.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct YanoteDetailScreen: View {
    private var yanoteDataSource: YanoteDataSource
    private var noteId: Int64? = nil
    
    @StateObject var viewModel = YanoteDetailViewModel(yanoteDataSource: nil)
    
    @Environment(\.presentationMode) var presentation
    
    init(yanoteDataSource: YanoteDataSource, noteId: Int64? = nil) {
        self.yanoteDataSource = yanoteDataSource
        self.noteId = noteId
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            TextField("Enter a title...", text: $viewModel.noteTitle)
                .font(.title)
            TextField("Enter some content...", text: $viewModel.noteContent)
            Spacer()
        }.toolbar(content: {
            Button(action: {
                viewModel.saveNote {
                    self.presentation.wrappedValue.dismiss()
                }
            }) {
                Image(systemName: "checkmark")
            }
        })
        .padding()
        .background(Color(hex: viewModel.noteColor))
        .onAppear {
            viewModel.setParamsAndLoadNote(yanoteDataSource: yanoteDataSource, noteId: noteId)
        }
    }
}

struct NoteDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
