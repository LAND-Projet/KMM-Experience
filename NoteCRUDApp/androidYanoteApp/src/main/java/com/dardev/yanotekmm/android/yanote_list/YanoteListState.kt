package com.dardev.yanotekmm.android.yanote_list

import com.dardev.yanotekmm.domain.yanote.Yanote

data class YanoteListState(
    val yanotes: List<Yanote> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)
