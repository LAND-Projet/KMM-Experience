package com.dardev.yanotekmm.android.yanote_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dardev.yanotekmm.domain.yanote.SearchYanotes
import com.dardev.yanotekmm.domain.yanote.Yanote
import com.dardev.yanotekmm.domain.yanote.YanoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YanoteListViewModel @Inject constructor(
    private val yanoteDataSource: YanoteDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val searchYanotes = SearchYanotes()

    val yanotes = savedStateHandle.getStateFlow("yanotes", emptyList<Yanote>())
    val searchText = savedStateHandle.getStateFlow("searchText","")
    val isSearchActive = savedStateHandle.getStateFlow("isSearchActive",false)

    val state = combine(yanotes,searchText,isSearchActive) { yanotes, searchText, isSearchActive ->
        YanoteListState(
            yanotes = searchYanotes.execute(yanotes,searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), YanoteListState())

    fun loadYanotes() {
        viewModelScope.launch {
            savedStateHandle["yanotes"] = yanoteDataSource.getAllYanotes()
        }
    }

    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] =  text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteYanoteById(id: Long) {
        viewModelScope.launch {
            yanoteDataSource.deleteYanoteById(id)
            loadYanotes()
        }
    }

}