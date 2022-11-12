package com.dardev.yanotekmm.android.yanote_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dardev.yanotekmm.domain.time.DateTimeUtil
import com.dardev.yanotekmm.domain.yanote.Yanote
import com.dardev.yanotekmm.domain.yanote.YanoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YanoteDetailViewModel @Inject constructor(
    private val yanoteDataSource: YanoteDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val noteTitle = savedStateHandle.getStateFlow("yanoteTitle", "")
    private val isNoteTitleFocused = savedStateHandle.getStateFlow("isYanoteTitleFocused", false)
    private val noteContent = savedStateHandle.getStateFlow("yanoteContent", "")
    private val isNoteContentFocused = savedStateHandle.getStateFlow("isYanoteContentFocused", false)
    private val noteColor = savedStateHandle.getStateFlow(
        "yanoteColor",
        Yanote.generateRandomColor()
    )

    val state = combine(
        noteTitle,
        isNoteTitleFocused,
        noteContent,
        isNoteContentFocused,
        noteColor
    ) { title, isTitleFocused, content, isContentFocused, color ->
        YanoteDetailState(
            yanoteTitle = title,
            isYanoteTitleHintVisible = title.isEmpty() && !isTitleFocused,
            yanoteContent = content,
            isYanoteContentHintVisible = content.isEmpty() && !isContentFocused,
            yanoteColor = color
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), YanoteDetailState())

    private val _hasYanoteBeenSaved = MutableStateFlow(false)
    val hasYanoteBeenSaved = _hasYanoteBeenSaved.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
            if(existingNoteId == -1L) {
                return@let
            }
            this.existingNoteId = existingNoteId
            viewModelScope.launch {
                yanoteDataSource.getYanoteById(existingNoteId)?.let { yanote ->
                    savedStateHandle["yanoteTitle"] = yanote.title
                    savedStateHandle["yanoteContent"] = yanote.content
                    savedStateHandle["yanoteColor"] = yanote.colorHex
                }
            }
        }
    }

    fun onYanoteTitleChanged(text: String) {
        savedStateHandle["yanoteTitle"] = text
    }

    fun onYanoteContentChanged(text: String) {
        savedStateHandle["yanoteContent"] = text
    }

    fun onYanoteTitleFocusChanged(isFocused: Boolean) {
        savedStateHandle["isYanoteTitleFocused"] = isFocused
    }

    fun onYanoteContentFocusChanged(isFocused: Boolean) {
        savedStateHandle["isYanoteContentFocused"] = isFocused
    }

    fun saveYanote() {
        viewModelScope.launch {
            yanoteDataSource.insertYanote(
                Yanote(
                    id = existingNoteId,
                    title = noteTitle.value,
                    content = noteContent.value,
                    colorHex = noteColor.value,
                    created = DateTimeUtil.now()
                )
            )
            _hasYanoteBeenSaved.value = true
        }
    }
}