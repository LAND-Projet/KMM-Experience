package com.dardev.yanotekmm.domain.yanote

import com.dardev.yanotekmm.domain.time.DateTimeUtil

class SearchYanotes {

    fun execute(yanotes: List<Yanote>, query: String): List<Yanote> {
        if (query.isBlank()){
            return yanotes
        }
        return yanotes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }
}