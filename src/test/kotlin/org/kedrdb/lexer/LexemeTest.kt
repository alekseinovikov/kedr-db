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
        assertContentEquals(
            sequenceOf(
                NumInt("2", 2),
                OpPlus("+"),
                NumInt("2", 2)
            ), lexer.parse(provider.get("2+2"))
        )
    }

    @Test
    @DisplayName("Empty query empty lexemes")
    fun emptyQuery() {
        assertContentEquals(sequenceOf(), lexer.parse(provider.get("")))
    }

}