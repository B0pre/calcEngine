package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator
import org.bopre.samples.calcEngine.service.interpreter.support.VariableStorage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ExpressionCalculatorImplIntegrationTest {

    companion object {

        const val DOUBLE_DELTA = 0.00001

        @JvmStatic
        fun sources(): List<Arguments> =
            listOf(
                Arguments.of("0", 0.0),
                Arguments.of("2 + 2*4 - (4/2)", 8.0),
                Arguments.of("(1 + 1 + 1) * 2", 6.0),
                Arguments.of("(1 + 2 * 3) * 2", 14.0),
                Arguments.of("(1 * 2 + 3) * 2", 10.0)
            )

        @JvmStatic
        fun sourcesWithVariables(): List<Arguments> =
            listOf(
                Arguments.of("a + b", 5.0, mapOf("a" to 2, "b" to 3)),
                Arguments.of("a + b*3 - c/d", 8.5, mapOf("a" to 2, "b" to 3, "c" to 10, "d" to 4))
            )

        private class StaticStorage : VariableStorage {
            val memory: MutableMap<String, Double> = HashMap()
            override fun getByName(name: String): VariableStorage.GetResult {
                if (memory.containsKey(name)) {
                    return VariableStorage.GetResult.Success(memory[name]!!)
                }
                return VariableStorage.GetResult.Fail("no such variable `$name`")
            }

            override fun assignVariable(name: String, value: Double) {
                memory[name] = value
            }

            fun clear() = memory.clear()

            fun rewrite(variables: Map<String, Double>) {
                clear()
                variables.forEach { (k, v) -> memory[k] = v }
            }
        }
    }

    private lateinit var variableStorage: StaticStorage
    private lateinit var expressionCalculatorImpl: ExpressionCalculator

    @BeforeEach
    fun beforeEach() {
        variableStorage = StaticStorage();
        expressionCalculatorImpl = ExpressionCalculatorImpl(
            ExprLexerImpl(),
            PostfixCreatorImpl(variableStorage),
            PostfixCalculatorImpl()
        )
    }

    @ParameterizedTest
    @MethodSource("sources")
    fun `test expression calculation`(expr: String, expected: Double) {
        val actual = expressionCalculatorImpl.calculate(expr)

        assertEquals(expected, actual, DOUBLE_DELTA, "wrong result")
    }

    @ParameterizedTest
    @MethodSource("sourcesWithVariables")
    fun `test expression calculation with variables`(expr: String, expected: Double, variables: Map<String, Double>) {
        variableStorage.rewrite(variables)

        val actual = expressionCalculatorImpl.calculate(expr)

        assertEquals(expected, actual, DOUBLE_DELTA, "wrong result")
    }


}