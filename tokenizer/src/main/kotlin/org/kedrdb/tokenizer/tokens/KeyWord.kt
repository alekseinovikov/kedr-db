package org.kedrdb.tokenizer.tokens

abstract class KeyWord : Token, TokenGroup {

    protected abstract val expectedChars: List<Char>
    private var currExpectedCharPointer = 0
    protected abstract fun addCharToRepresentation(ch: Char)

    override fun tryAcceptChar(ch: Char): Boolean {
        if (mustBeCompleted() || !canTakeMore()) {
            return false
        }

        if (!ch.equals(expectedChars[currExpectedCharPointer], ignoreCase = true)) {
            return false
        }

        currExpectedCharPointer++
        addCharToRepresentation(ch)
        return true
    }

    override fun canBeCompletedNow() = currExpectedCharPointer == expectedChars.size
    override fun mustBeCompleted() = currExpectedCharPointer == expectedChars.size
    override fun canTakeMore() = currExpectedCharPointer < expectedChars.size

    data class KeyWordCreate(private var stringRepresentation: String = "") : KeyWord() {
        override val expectedChars = listOf('c', 'r', 'e', 'a', 't', 'e')
        override fun addCharToRepresentation(ch: Char) {
            stringRepresentation += ch
        }

        override fun getStringRepresentation(): String = stringRepresentation
    }

    data class KeyWordTable(private var stringRepresentation: String = "") : KeyWord() {
        override val expectedChars = listOf('t', 'a', 'b', 'l', 'e')

        override fun addCharToRepresentation(ch: Char) {
            stringRepresentation += ch
        }

        override fun getStringRepresentation(): String = stringRepresentation
    }

    data class KeyWordSelect(private var stringRepresentation: String = "") : KeyWord() {
        override val expectedChars = listOf('s', 'e', 'l', 'e', 'c', 't')

        override fun addCharToRepresentation(ch: Char) {
            stringRepresentation+=ch
        }

        override fun getStringRepresentation() = stringRepresentation
    }

    data class KeyWordFrom(private var stringRepresentation: String = "") : KeyWord() {
        override val expectedChars = listOf('f', 'r', 'o', 'm')

        override fun addCharToRepresentation(ch: Char) {
            stringRepresentation+=ch
        }

        override fun getStringRepresentation() = stringRepresentation
    }

    data class KeyWordWhere(private var stringRepresentation: String = "") : KeyWord() {
        override val expectedChars = listOf('w', 'h', 'e', 'r', 'e')

        override fun addCharToRepresentation(ch: Char) {
            stringRepresentation+=ch
        }

        override fun getStringRepresentation() = stringRepresentation
    }

    data class KeyWordAnd(private var stringRepresentation: String = "") : KeyWord() {
        override val expectedChars = listOf('a', 'n', 'd')

        override fun addCharToRepresentation(ch: Char) {
            stringRepresentation+=ch
        }

        override fun getStringRepresentation() = stringRepresentation
    }

    data class KeyWordDelete(private var stringRepresentation: String = "") : KeyWord() {
        override val expectedChars = listOf('d', 'e', 'l', 'e', 't', 'e')

        override fun addCharToRepresentation(ch: Char) {
            stringRepresentation+=ch
        }

        override fun getStringRepresentation() = stringRepresentation
    }

}