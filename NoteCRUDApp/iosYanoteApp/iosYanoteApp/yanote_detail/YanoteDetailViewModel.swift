//
//  YanoteDetailViewModel.swift
//  iosYanoteApp
//
//  Created by Darren on 2022-11-11.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension YanoteDetailScreen {
    @MainActor class YanoteDetailViewModel: ObservableObject {
        private var yanoteDataSource: YanoteDataSource?
        
        private var noteId: Int64? = nil
        @Published var noteTitle = ""
        @Published var noteContent = ""
        @Published private(set) var noteColor = Yanote.Companion().generateRandomColor()
        
        init(yanoteDataSource: YanoteDataSource? = nil) {
            self.yanoteDataSource = yanoteDataSource
        }
        
        func loadNoteIfExists(id: Int64?) {
            if id != nil {
                self.noteId = id
                yanoteDataSource?.getYanoteById(id: id!, completionHandler: { note, error in
                    self.noteTitle = note?.title ?? ""
                    self.noteContent = note?.content ?? ""
                    self.noteColor = note?.colorHex ?? Yanote.Companion().generateRandomColor()
                })
            }
        }
        
        func saveNote(onSaved: @escaping () -> Void) {
            yanoteDataSource?.insertYanote(
                yanote: Yanote(id: noteId == nil ? nil : KotlinLong(value: noteId!), title: self.noteTitle, content: self.noteContent, colorHex: self.noteColor, created: DateTimeUtil().now()), completionHandler: { error in
                    onSaved()
                })
        }
        
        func setParamsAndLoadNote(yanoteDataSource: YanoteDataSource, noteId: Int64?) {
            self.yanoteDataSource = yanoteDataSource
            loadNoteIfExists(id: noteId)
        }
    }
}
