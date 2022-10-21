package org.kedrdb.lexer.impl

import org.kedrdb.core.Source
import org.kedrdb.lexer.Lexeme
import org.kedrdb.lexer.Lexer
import org.kedrdb.lexer.Query
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
internal class QueryLexer : Lexer {
    override fun parse(querySource: Source<Query>): Sequence<Lexeme> =
        querySource.get()?.run { QueryLexerMachine(this).parse() } ?: sequenceOf()
}