package org.kedrdb.tokenizer.impl

import org.junit.jupiter.api.Test
import org.kedrdb.tokenizer.QueryChar
import org.kedrdb.tokenizer.impl.StringQuerySourceProvider

class CharQuerySourceProviderTest {

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

        lines.toCharArray().forEach {
            assert(QueryChar(it) == query.getNextChar())
        }
    }

}