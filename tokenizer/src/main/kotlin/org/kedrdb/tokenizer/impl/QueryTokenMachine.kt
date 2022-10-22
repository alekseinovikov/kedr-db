package org.kedrdb.tokenizer.impl

import org.kedrdb.core.WrongCharacterException
import org.kedrdb.tokenizer.Query
import org.kedrdb.tokenizer.tokens.KeyWord.*
import org.kedrdb.tokenizer.tokens.Number.NumInt
import org.kedrdb.tokenizer.tokens.Operation.OpPlus
import org.kedrdb.tokenizer.tokens.Token

internal class QueryTokenMachine(private val query: Query) {
    private val delimiters = setOf(' ', '\n', '\t')


    private var activeTokens: MutableList<Token> = mutableListOf()

    init {
        resetCurrentTokens()
    }

    fun parse(): Sequence<Token> = sequence {
        var currentChar = query.getNextChar()
        while (currentChar != null) {
            processChar(currentChar.char)
                .filterNotNull()
                .forEach { yield(it) }

            currentChar = query.getNextChar()
        }

        //Finish active tokens before termination
        activeTokens.firstOrNull { it.canBeCompletedNow() }?.let { yield(it) }
    }

    private fun processChar(char: Char): List<Token?> {
        return when {
            delimiters.contains(char) -> listOf(processDelimiter())
            else -> {
                listOf(
                    processNotDelimiter(char),
                    processMustBeCompletedTokens()
                )
            }
        }
    }

    private fun processNotDelimiter(char: Char): Token? {
        var acceptedTokens = activeTokens
            .filter { it.canTakeMore() }
            .filter { it.tryAcceptChar(char) }

        var mayBeFinished: Token? = null
        if (acceptedTokens.isEmpty()) {
            //we probably may try to finish previous and try the character again
            mayBeFinished = activeTokens.firstOrNull { it.canBeCompletedNow() }
            resetCurrentTokens()

            acceptedTokens = activeTokens
                .filter { it.canTakeMore() }
                .filter { it.tryAcceptChar(char) }
        }

        when {
            acceptedTokens.isEmpty() -> throw WrongCharacterException(char)
            else -> activeTokens = acceptedTokens.toMutableList()
        }

        return mayBeFinished
    }

    private fun processDelimiter(): Token? {
        return activeTokens.firstOrNull { it.canBeCompletedNow() }?.let {
            resetCurrentTokens()
            return@let it
        }
    }

    private fun processMustBeCompletedTokens(): Token? {
        return activeTokens.firstOrNull { it.mustBeCompleted() }?.let {
            resetCurrentTokens()
            return@let it
        }
    }

    private fun resetCurrentTokens() {
        activeTokens.clear()

        activeTokens.add(KeyWordFrom())
        activeTokens.add(KeyWordAnd())
        activeTokens.add(KeyWordWhere())
        activeTokens.add(KeyWordSelect())
        activeTokens.add(KeyWordCreate())
        activeTokens.add(KeyWordTable())
        activeTokens.add(KeyWordDelete())
        activeTokens.add(OpPlus())
        activeTokens.add(NumInt())
    }
}