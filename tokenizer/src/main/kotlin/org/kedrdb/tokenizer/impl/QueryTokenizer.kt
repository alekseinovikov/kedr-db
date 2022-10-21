package org.kedrdb.tokenizer.impl

import org.kedrdb.core.Source
import org.kedrdb.tokenizer.Token
import org.kedrdb.tokenizer.Tokenizer
import org.kedrdb.tokenizer.Query
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
internal class QueryTokenizer : Tokenizer {
    override fun parse(querySource: Source<Query>): Sequence<Token> =
        querySource.get()?.run { QueryTokenMachine(this).parse() } ?: sequenceOf()
}