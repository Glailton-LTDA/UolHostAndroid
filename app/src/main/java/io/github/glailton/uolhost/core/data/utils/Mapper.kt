package io.github.glailton.uolhost.core.data.utils

fun interface Mapper<I, O> {
    fun map(from: I): O
}

fun <I, O> I.mapWith(mapper: Mapper<I, O>) = mapper.map(this)

fun <I, O> List<I>.mapWith(mapper: Mapper<I, O>) = map { it.mapWith(mapper) }