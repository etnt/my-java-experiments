package org.kruskakli

import java.util.Stack

// kotlin CalculatorExample.kt "1 + 2 * 3 - 4 / 5"

fun main(args: Array<String>) {
    val input = "100 + 12.2 * 3.14 - 40 / 13.33"
    val answer = calculate(input)
    println("The answer is: $answer")
}

fun calculate(expression: String): Double {
    val numbers = Stack<Double>()
    val operations = Stack<Char>()

    var numberBuffer = StringBuilder()

    for (c in expression) {
        when {
            c.isDigit() || c == '.' -> {
                numberBuffer.append(c)
            }
            c == '(' -> {
                operations.push(c)
            }
            c == ')' -> {
                if (numberBuffer.isNotEmpty()) {
                    numbers.push(numberBuffer.toString().toDouble())
                    numberBuffer.clear()
                }
                while (operations.peek() != '(') {
                    processOperation(numbers, operations)
                }
                operations.pop()
            }
            c == '+' || c == '-' || c == '*' || c == '/' -> {
                if (numberBuffer.isNotEmpty()) {
                    numbers.push(numberBuffer.toString().toDouble())
                    numberBuffer.clear()
                }
                while (!operations.isEmpty() && hasPrecedence(c, operations.peek())) {
                    processOperation(numbers, operations)
                }
                operations.push(c)
            }
            else -> throw IllegalArgumentException("Invalid character in expression: '$c'")
        }
    }

    if (numberBuffer.isNotEmpty()) {
        numbers.push(numberBuffer.toString().toDouble())
    }

    while (!operations.isEmpty()) {
        processOperation(numbers, operations)
    }

    return numbers.pop()
}

fun processOperation(numbers: Stack<Double>, operations: Stack<Char>) {
    val operator = operations.pop()
    val right = numbers.pop()
    val left = numbers.pop()

    val result = when (operator) {
        '+' -> left + right
        '-' -> left - right
        '*' -> left * right
        '/' -> {
            if (right == 0.0) throw ArithmeticException("Division by zero")
            left / right
        }
        else -> throw IllegalArgumentException("Invalid operator: '$operator'")
    }

    numbers.push(result)
}

fun hasPrecedence(op1: Char, op2: Char): Boolean {
    if (op2 == '(' || op2 == ')')
        return false
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
        return false
    return true
}
