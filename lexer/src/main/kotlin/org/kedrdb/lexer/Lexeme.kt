package org.kedrdb.lexer

sealed class Lexeme {
    abstract val stringRepresentation: String

    sealed class Operation : Lexeme() {
        data class OpPlus(override val stringRepresentation: String) : Operation()
    }

    sealed class Number : Lexeme() {
        data class NumInt(override val stringRepresentation: String, val value: Int) : Number()
    }
}