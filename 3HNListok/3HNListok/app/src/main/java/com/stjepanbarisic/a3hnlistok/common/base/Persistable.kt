package com.stjepanbarisic.a3hnlistok.common.base

interface Persistable<ListType> {
    fun asListData(): ListType
}

fun <T> List<Persistable<T>>.asListData(): List<T> {
    val domainEntities = arrayListOf<T>()
    this.forEach { domainEntities.add(it.asListData()) }
    return domainEntities
}