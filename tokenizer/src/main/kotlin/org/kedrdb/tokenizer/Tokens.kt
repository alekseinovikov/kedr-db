package org.kedrdb.tokenizer

interface TokenGroup

sealed interface Token {
    fun getStringRepresentation(): String
    fun tryAcceptChar(ch: Char): Boolean
    fun canBeCompletedNow(): Boolean
    fun canTakeMore(): Boolean
    fun mustBeCompleted(): Boolean

    abstract class Operation : Token, TokenGroup {
        data class OpPlus(private var stringRepresentation: String = "") : Operation() {
            private val plus = '+'
            private var finished = false
            override fun getStringRepresentation() = stringRepresentation
            override fun tryAcceptChar(ch: Char): Boolean {
                if (!finished && ch == plus) {
                    stringRepresentation = "+"
                    finished = true
                    return true
                }

                return false
            }

            override fun canBeCompletedNow(): Boolean = finished
            override fun canTakeMore(): Boolean = !finished
            override fun mustBeCompleted(): Boolean = finished
        }
    }

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
}

