package org.kedrdb.lexer

import org.kedrdb.core.Source

interface Lexer {
    fun parse(querySource: Source<Query>): Sequence<Lexeme>
}

interface Query {
    fun getNextChar(): QueryChar?
}

@JvmInline
value class QueryChar(val char: Char)
