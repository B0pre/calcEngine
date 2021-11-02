package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator
import org.bopre.samples.calcEngine.service.interpreter.impl.ExprLexerImpl
import org.bopre.samples.calcEngine.service.interpreter.impl.ExpressionCalculatorImpl
import org.bopre.samples.calcEngine.service.interpreter.impl.PostfixCalculatorImpl
import org.bopre.samples.calcEngine.service.interpreter.impl.PostfixCreatorImpl
import org.bopre.samples.calcEngine.service.interpreter.support.VariableStorage
import org.springframework.stereotype.Component

@Component
class SingletonExpressionCalculatorFactoryImpl(val instance: ExpressionCalculator = createInstance()) :
    ExpressionCalculatorFactory {

    override fun getCalculator(): ExpressionCalculator {
        return instance
    }

    companion object {
        private fun createInstance(): ExpressionCalculator {
            return ExpressionCalculatorImpl(
                ExprLexerImpl(),
                PostfixCreatorImpl(object : VariableStorage {
                    val memory = HashMap<String, Double>()
                    override fun getByName(name: String): VariableStorage.GetResult {
                        if (memory.contains(name))
                            return VariableStorage.GetResult.Success(memory[name]!!)
                        return VariableStorage.GetResult.Fail("no such variable")
                    }

                    override fun assignVariable(name: String, value: Double) {
                        memory[name] = value
                    }
                }),
                PostfixCalculatorImpl()
            )
        }
    }
}