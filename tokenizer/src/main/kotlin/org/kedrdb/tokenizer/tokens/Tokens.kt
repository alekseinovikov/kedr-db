package org.kedrdb.tokenizer.tokens

interface TokenGroup

sealed interface Token {
    fun getStringRepresentation(): String
    fun tryAcceptChar(ch: Char): Boolean
    fun canBeCompletedNow(): Boolean
    fun canTakeMore(): Boolean
    fun mustBeCompleted(): Boolean
}

