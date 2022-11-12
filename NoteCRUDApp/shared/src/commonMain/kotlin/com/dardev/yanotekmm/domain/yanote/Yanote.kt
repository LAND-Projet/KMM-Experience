package com.dardev.yanotekmm.domain.yanote

import com.dardev.yanotekmm.presentation.*
import kotlinx.datetime.LocalDateTime

data class Yanote(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
){
    companion object{
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}
