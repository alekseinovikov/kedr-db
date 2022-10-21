package org.kedrdb.lexer

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.kedrdb.core.SourceProvider
import org.kedrdb.lexer.Lexeme.Number.*
import org.kedrdb.lexer.Lexeme.Operation.*
import javax.inject.Inject
import kotlin.test.assertContentEquals

@QuarkusTest
class LexemeTest {

    @Inject
    lateinit var provider: SourceProvider<String, Query>

    @Inject
    lateinit var lexer: Lexer


    @Test
    @DisplayName("Simple 2+2 lexemes = '2' '+' '2'")
    fun simpleThreeLexemes() {
        val source = provider.get("2+2")

        val lexemes = lexer.parse(source)
        assertContentEquals(
            sequenceOf(
                NumInt("2", 2),
                OpPlus("+"),
                NumInt("2", 2)
            ), lexemes
        )
    }

}