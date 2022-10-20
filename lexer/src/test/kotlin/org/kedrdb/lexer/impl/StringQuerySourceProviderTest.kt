package org.kedrdb.lexer.impl

import org.junit.jupiter.api.Test
import org.kedrdb.lexer.QueryLine

class StringQuerySourceProviderTest {

    private val provider = StringQuerySourceProvider()


    @Test
    fun provide_lines_returns_one_by_one() {
        val lines = """
            first
            second
            third
        """.trimIndent()

        val source = provider.get(lines)
        val query = source.get()!!

        lines.lines().forEach {
            assert(QueryLine(it) == query.getNextLine())
        }
    }

}