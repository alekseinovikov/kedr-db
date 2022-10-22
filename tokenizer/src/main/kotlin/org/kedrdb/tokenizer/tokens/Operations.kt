package org.kedrdb.tokenizer.tokens

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