//
//  YanoteListViewModel.swift
//  iosYanoteApp
//
//  Created by Darren on 2022-11-11.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension YanoteListScreen {
    @MainActor class YanoteListViewModel: ObservableObject {
        private var yanoteDataSource: YanoteDataSource? = nil
        
        private let searchYanotes = SearchYanotes()
        
        private var yanotes = [Yanote]()
        @Published private(set) var filteredYanotes = [Yanote]()
        @Published var searchText = ""{
            didSet {
                self.filteredYanotes = searchYanotes.execute(yanotes: self.yanotes, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false
        
        init(yanoteDataSource: YanoteDataSource? = nil){
            self.yanoteDataSource = yanoteDataSource
        }
        
        func loadYanotes(){
            yanoteDataSource?.getAllYanotes(completionHandler: {yanotes,error in
                self.yanotes = yanotes ?? []
                self.filteredYanotes = self.yanotes
            })
        }
        
        func deleteYanoteById(id: Int64?) {
            if id != nil {
                yanoteDataSource?.deleteYanoteById(id: id!, completionHandler: { error in
                    self.loadYanotes()
                })
            }
        }
        
        func toggleIsSearchActive(){
            isSearchActive = !isSearchActive
            if !isSearchActive {
                searchText = ""
            }
        }
        
        func setYanoteDataSource(yanoteDataSource: YanoteDataSource){
            self.yanoteDataSource = yanoteDataSource
        }
    }
}
