package org.kruskakli

class Hello(val name: String) {
    fun greeting() = "Hello ${name}!"
}

fun main(args: Array<String>) {
    println(Hello("World").greeting())
}