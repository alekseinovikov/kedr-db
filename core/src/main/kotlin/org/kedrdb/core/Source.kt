package org.kedrdb.core

interface Source<T> {
    fun get(): T?
}

interface SourceProvider<S,T>{
    fun get(arg: S): Source<T>
}