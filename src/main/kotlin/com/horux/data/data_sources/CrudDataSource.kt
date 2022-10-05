package com.horux.data.data_sources

interface CrudDataSource<T> {

    suspend fun add(entity: T): Boolean
    suspend fun update(entity: T): Boolean
    suspend fun delete(entity: T): Boolean

    suspend fun get(id: Int): T?
    suspend fun getAll(): List<T>?

}