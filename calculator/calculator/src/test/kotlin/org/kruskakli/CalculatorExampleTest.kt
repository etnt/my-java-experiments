package org.kruskakli

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorExampleTest {

    @Test
    fun testCalculate() {
        assertEquals(135.3072, calculate("100 + 12.2 * 3.14 - 40 / 13.33"), 0.0001)
        assertEquals(1.0, calculate("1"), 0.1)
        assertEquals(3.0, calculate("1 + 2"), 0.1)
        assertEquals(6.2, calculate("1 + 2 * 3 - 4 / 5"), 0.1)
    }
}