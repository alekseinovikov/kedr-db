package org.kedrdb.lexer

import kotlin.reflect.KClass

interface LexemeGroup {
    fun getMemberClasses(): Set<KClass<*>>
}

sealed interface Lexeme {
    val stringRepresentation: String

    abstract class Operation(override val stringRepresentation: String) : Lexeme, LexemeGroup {
        data class OpPlus(override val stringRepresentation: String) : Lexeme

        override fun getMemberClasses() = setOf(OpPlus::class)
    }

    sealed class Number : Lexeme, LexemeGroup {
        data class NumInt(override val stringRepresentation: String, val value: Int) : Lexeme

        override fun getMemberClasses() = setOf(NumInt::class);
    }
}

