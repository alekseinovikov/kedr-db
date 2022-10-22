package org.kedrdb.tokenizer

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.kedrdb.core.SourceProvider
import org.kedrdb.tokenizer.tokens.KeyWord
import org.kedrdb.tokenizer.tokens.Number
import org.kedrdb.tokenizer.tokens.Operation
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
                Number.NumInt("2"),
                Operation.OpPlus("+"),
                Number.NumInt("2")
            ), tokenizer.parse(provider.get("2+2"))
        )
    }

    @Test
    @DisplayName("Empty query empty tokens")
    fun emptyQuery() {
        assertContentEquals(sequenceOf(), tokenizer.parse(provider.get("")))
    }

    @Test
    @DisplayName("Create keyword token")
    fun createKeyWordToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordCreate("cReATe")
            ),
            tokenizer.parse(provider.get(" \n cReATe  \t"))
        )
    }

    @Test
    @DisplayName("Table keyword token")
    fun tableKeyWordToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordTable("TABlE")
            ),
            tokenizer.parse(provider.get(" \n\n\t\t\t\t    \n\tTABlE\n \n \t"))
        )
    }

    @Test
    @DisplayName("Select keyword token")
    fun selectKeyWordToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordSelect("seLect")
            ),
            tokenizer.parse(provider.get(" \n\n\t\t    \n\tseLect\n \n"))
        )
    }

    @Test
    @DisplayName("From keyword token")
    fun fromKeyWordToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordFrom("FROM")
            ),
            tokenizer.parse(provider.get(" \n\t\t    \n\tFROM\n "))
        )
    }

    @Test
    @DisplayName("Where keyword token")
    fun whereKeyWordToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordWhere("WhERe")
            ),
            tokenizer.parse(provider.get(" \n\t\t  \t  \n\tWhERe \n    "))
        )
    }

    @Test
    @DisplayName("And keyword token")
    fun andKeyWordToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordAnd("and")
            ),
            tokenizer.parse(provider.get("\n\t  \tand    "))
        )
    }

    @Test
    @DisplayName("Delete keyword token")
    fun deleteKeyWordToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordDelete("Delete")
            ),
            tokenizer.parse(provider.get("\n\t  \tDelete\t\n\t    "))
        )
    }

    @Test
    @DisplayName("All keywords token")
    fun allKeyWordsToken() {
        assertContentEquals(
            sequenceOf(
                KeyWord.KeyWordSelect("select"),
                KeyWord.KeyWordAnd("and"),
                KeyWord.KeyWordFrom("from"),
                KeyWord.KeyWordCreate("create"),
                KeyWord.KeyWordWhere("where"),
                KeyWord.KeyWordTable("table"),
                KeyWord.KeyWordDelete("delete"),
            ),
            tokenizer.parse(provider.get(" select and from create where table delete "))
        )
    }

}