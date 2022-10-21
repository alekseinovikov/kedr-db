package org.kedrdb.tokenizer

import kotlin.reflect.KClass

interface TokenGroup {
    fun getMemberClasses(): Set<KClass<*>>
}

sealed interface Token {
    val stringRepresentation: String

    abstract class Operation(override val stringRepresentation: String) : Token, TokenGroup {
        data class OpPlus(override val stringRepresentation: String) : Token

        override fun getMemberClasses() = setOf(OpPlus::class)
    }

    sealed class Number : Token, TokenGroup {
        data class NumInt(override val stringRepresentation: String, val value: Int) : Token

        override fun getMemberClasses() = setOf(NumInt::class);
    }
}

