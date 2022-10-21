package org.kedrdb.tokenizer

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.kedrdb.core.SourceProvider
import org.kedrdb.tokenizer.Token.Number.*
import org.kedrdb.tokenizer.Token.Operation.*
import javax.inject.Inject
import kotlin.test.assertContentEquals

@QuarkusTest
class TokenTest {

    @Inject
    lateinit var provider: SourceProvider<String, Query>

    @Inject
    lateinit var tokenizer: Tokenizer


    @Test
    @DisplayName("Simple 2+2 tokens = '2' '+' '2'")
    fun simpleThreeTokens() {
        assertContentEquals(
            sequenceOf(
                NumInt("2"),
                OpPlus("+"),
                NumInt("2")
            ), tokenizer.parse(provider.get("2+2"))
        )
    }

    @Test
    @DisplayName("Empty query empty tokens")
    fun emptyQuery() {
        assertContentEquals(sequenceOf(), tokenizer.parse(provider.get("")))
    }

}