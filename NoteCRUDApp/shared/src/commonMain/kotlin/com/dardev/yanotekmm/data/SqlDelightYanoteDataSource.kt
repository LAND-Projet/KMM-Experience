package com.dardev.yanotekmm.data

import com.dardev.yanotekmm.database.YanoteDatabase
import com.dardev.yanotekmm.domain.time.DateTimeUtil
import com.dardev.yanotekmm.domain.yanote.Yanote
import com.dardev.yanotekmm.domain.yanote.YanoteDataSource

class SqlDelightYanoteDataSource(db: YanoteDatabase): YanoteDataSource {

    private val queries = db.yanoteQueries

    override suspend fun insertYanote(yanote: Yanote) {
        queries.insertYanote(
            id = yanote.id,
            title = yanote.title,
            content = yanote.content,
            colorHex = yanote.colorHex,
            created = DateTimeUtil.toEpochMillis(yanote.created)
        )
    }

    override suspend fun getYanoteById(id: Long): Yanote? {
        return queries.getYanoteById(id)
            .executeAsOneOrNull()
            ?.toYanote()
    }

    override suspend fun getAllYanotes(): List<Yanote> {
        return queries
            .getAllYanotes()
            .executeAsList()
            .map { it.toYanote() }
    }

    override suspend fun deleteYanoteById(id: Long) {
        queries.deleteYanoteById(id)
    }

}