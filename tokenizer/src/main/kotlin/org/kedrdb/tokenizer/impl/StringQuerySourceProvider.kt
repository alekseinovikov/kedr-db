package org.kedrdb.tokenizer.impl

import org.kedrdb.core.Source
import org.kedrdb.core.SourceProvider
import org.kedrdb.tokenizer.Query
import org.kedrdb.tokenizer.QueryChar
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
internal class StringQuerySourceProvider : SourceProvider<String, Query> {
    override fun get(arg: String): Source<Query> = CharQuerySource(arg)
}

internal class CharQuerySource(private val str: String) : Source<Query> {
    override fun get(): Query = CharQuery(str)
}

internal class CharQuery(lines: String) : Query {
    private val iterator = lines.asSequence().iterator()
    override fun getNextChar(): QueryChar? {
        return if (iterator.hasNext()) QueryChar(iterator.next()) else null
    }
}
