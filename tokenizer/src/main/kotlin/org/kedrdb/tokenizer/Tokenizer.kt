package org.kedrdb.tokenizer

import org.kedrdb.core.Source

interface Tokenizer {
    fun parse(querySource: Source<Query>): Sequence<Token>
}

interface Query {
    fun getNextChar(): QueryChar?
}

@JvmInline
value class QueryChar(val char: Char)
