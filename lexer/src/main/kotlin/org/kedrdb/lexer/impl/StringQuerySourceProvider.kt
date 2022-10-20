package org.kedrdb.lexer.impl

import org.kedrdb.core.Source
import org.kedrdb.core.SourceProvider
import org.kedrdb.lexer.Query
import org.kedrdb.lexer.QueryLine
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
internal class StringQuerySourceProvider : SourceProvider<String, Query> {
    override fun get(arg: String): Source<Query> = StringQuerySource(arg)
}

internal class StringQuerySource(str: String) : Source<Query> {
    private val lines = str.lines()
    override fun get(): Query = StringQuery(lines)
}

internal class StringQuery(lines: List<String>) : Query {
    private val iterator = lines.iterator()
    override fun getNextLine(): QueryLine? = if (iterator.hasNext()) QueryLine(iterator.next()) else null
}
