package org.kedrdb

import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain

@Suppress("unused")
@QuarkusMain
class Main: QuarkusApplication {
    override fun run(vararg args: String?): Int {
        println("HELLO!")

        return 0
    }
}