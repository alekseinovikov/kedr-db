package org.kedrdb.tokenizer.tokens

abstract class Number<T : kotlin.Number> : Token, TokenGroup {
    abstract fun getValue(): T

    data class NumInt(private var stringRepresentation: String = "") : Number<Int>() {
        private var value: Int? = null
        override fun getValue(): Int = value!!

        override fun getStringRepresentation() = stringRepresentation

        override fun tryAcceptChar(ch: Char): Boolean {
            if (ch.isDigit()) {
                addCharAsANumber(ch)
                return true
            }

            return false
        }

        private fun addCharAsANumber(ch: Char) {
            stringRepresentation += ch

            val intRepresentation = ch.digitToInt()
            value = when (value) {
                null -> intRepresentation
                else -> value!! * 10 + intRepresentation
            }
        }

        override fun canBeCompletedNow(): Boolean = value != null

        override fun canTakeMore(): Boolean = true

        override fun mustBeCompleted(): Boolean = false
    }
}