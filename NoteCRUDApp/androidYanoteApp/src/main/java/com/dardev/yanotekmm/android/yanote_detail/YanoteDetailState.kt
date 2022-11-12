package com.dardev.yanotekmm.android.yanote_detail

import androidx.compose.ui.graphics.Color

data class YanoteDetailState(
    val yanoteTitle:String = "",
    val isYanoteTitleHintVisible: Boolean = false,
    val yanoteContent: String = "",
    val isYanoteContentHintVisible: Boolean = false,
    val yanoteColor: Long = 0xFFFFFF
)
