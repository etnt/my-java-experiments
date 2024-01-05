package org.kruskakli

import java.util.Stack

// kotlin CalculatorExample.kt "1 + 2 * 3 - 4 / 5"

fun main(args: Array<String>) {
    val input = "1 + 2 * 3 - 4 / 5"
    val answer = calculate(input)
    println("The answer is: $answer")
}

fun calculate(expression: String): Double {
    val numbers = Stack<Double>()
    val operations = Stack<Char>()

    for (c in expression) {
        when {
            c.isWhitespace() -> continue
            c in '0'..'9' -> numbers.push((c - '0').toDouble())
            c == '(' -> operations.push(c)
            c == ')' -> {
                while (operations.peek() != '(') {
                    processOperation(numbers, operations)
                }
                operations.pop()
            }
            c == '+' || c == '-' || c == '*' || c == '/' -> operations.push(c)
        }
    }

    while (!operations.isEmpty()) {
        processOperation(numbers, operations)
    }

    return numbers.pop()
}

fun processOperation(numbers: Stack<Double>, operations: Stack<Char>) {
    val operation = operations.pop()
    val number2 = numbers.pop()
    val number1 = numbers.pop()

    val result = when (operation) {
        '+' -> number1 + number2
        '-' -> number1 - number2
        '*' -> number1 * number2
        '/' -> number1 / number2
        else -> throw IllegalArgumentException("Unknown operation: $operation")
    }

    numbers.push(result)
}