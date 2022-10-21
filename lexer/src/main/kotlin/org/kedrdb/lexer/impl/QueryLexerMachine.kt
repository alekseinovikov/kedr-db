package org.kedrdb.lexer.impl

import org.kedrdb.lexer.Lexeme
import org.kedrdb.lexer.Query

class QueryLexerMachine(private val query: Query) {
    private val currentProcessingLexeme: Lexeme? = null

    fun parse(): Sequence<Lexeme> = sequence {
        var currentChar = query.getNextChar()
        while (currentChar != null) {
            processChar(currentChar.char)

            currentChar = query.getNextChar()
        }
    }

    private fun processChar(char: Char) {

    }
}