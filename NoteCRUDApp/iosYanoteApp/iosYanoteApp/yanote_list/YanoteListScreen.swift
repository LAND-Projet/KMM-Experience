//
//  YanoteListScreen.swift
//  iosYanoteApp
//
//  Created by Darren on 2022-11-11.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct YanoteListScreen: View {
    private var yanoteDataSource: YanoteDataSource
     @StateObject var viewModel = YanoteListViewModel(yanoteDataSource: nil)
     
     @State private var isNoteSelected = false
     @State private var selectedNoteId: Int64? = nil
     
     init(yanoteDataSource: YanoteDataSource) {
         self.yanoteDataSource = yanoteDataSource
     }
     
     var body: some View {
         VStack {
             ZStack {
                 NavigationLink(destination: YanoteDetailScreen(yanoteDataSource: self.yanoteDataSource, noteId: selectedNoteId), isActive: $isNoteSelected) {
                     EmptyView()
                 }.hidden()
                 HideableSearchTextField<YanoteDetailScreen>(onSearchToggled: {
                     viewModel.toggleIsSearchActive()
                 }, destinationProvider: {
                     YanoteDetailScreen(
                         yanoteDataSource: yanoteDataSource,
                         noteId: selectedNoteId
                     )
                 }, isSearchActive: viewModel.isSearchActive, searchText: $viewModel.searchText)
                 .frame(maxWidth: .infinity, minHeight: 40)
                 .padding()
                 
                 if !viewModel.isSearchActive {
                     Text("All notes")
                         .font(.title2)
                 }
             }
             
             List {
                 ForEach(viewModel.filteredYanotes, id: \.self.id) { note in
                     Button(action: {
                         isNoteSelected = true
                         selectedNoteId = note.id?.int64Value
                     }) {
                         YanoteItem(yanote: note, onDeleteClick: {
                             viewModel.deleteYanoteById(id: note.id?.int64Value)
                         })
                     }
                 }
             }
             .onAppear {
                 viewModel.loadYanotes()
             }
             .listStyle(.plain)
             // .listRowSeparator(.hidden)
         }
         .onAppear {
             viewModel.setYanoteDataSource(yanoteDataSource: yanoteDataSource)
         }
     }
 }

 struct YanoteListScreen_Previews: PreviewProvider {
     static var previews: some View {
         EmptyView()
     }
 }
