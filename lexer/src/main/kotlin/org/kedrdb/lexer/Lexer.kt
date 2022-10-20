package org.kedrdb.lexer

import org.kedrdb.core.Source

interface Lexer {
    fun parse(querySource: Source<Query>): Sequence<Lexeme>
}

interface Query {
    fun getNextLine(): QueryLine?
}

@JvmInline
value class QueryLine(val line: String)
