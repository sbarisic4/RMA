package com.stjepanbarisic.a3hnlistok.common.base

interface ListData<DatabaseType> {
    fun asDatabase(): DatabaseType
}

fun <T> List<ListData<T>>.asDatabase(): List<T> {
    val dbEntities = arrayListOf<T>()
    this.forEach { dbEntities.add(it.asDatabase()) }
    return dbEntities
}

