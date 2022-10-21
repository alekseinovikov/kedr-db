package org.kedrdb.tokenizer.impl

import org.kedrdb.tokenizer.Token
import org.kedrdb.tokenizer.Query

class QueryTokenMachine(private val query: Query) {
    private val currentProcessingToken: Token? = null

    fun parse(): Sequence<Token> = sequence {
        var currentChar = query.getNextChar()
        while (currentChar != null) {
            processChar(currentChar.char)

            currentChar = query.getNextChar()
        }
    }

    private fun processChar(char: Char) {

    }
}