package com.dardev.yanotekmm.domain.yanote

interface YanoteDataSource {
    suspend fun insertYanote(yanote: Yanote)
    suspend fun getYanoteById(id: Long): Yanote?
    suspend fun getAllYanotes(): List<Yanote>
    suspend fun deleteYanoteById(id: Long)
}