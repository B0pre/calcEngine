package org.bopre.samples.calcEngine.config

import org.bopre.samples.calcEngine.data.dao.StoredVariableRepository
import org.bopre.samples.calcEngine.service.ExpressionCalculatorFactory
import org.bopre.samples.calcEngine.service.SingletonExpressionCalculatorFactoryImpl
import org.bopre.samples.calcEngine.service.interpreter.ExpressionCalculator
import org.bopre.samples.calcEngine.service.interpreter.impl.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExpressionCalculatorConfiguration(
    val variableRepository: StoredVariableRepository
) {

    @Bean
    fun expressionCalculator(): ExpressionCalculator {
        return ExpressionCalculatorImpl(
            ExprLexerImpl(),
            PostfixCreatorImpl(VariableStorageDatabaseImpl(variableRepository)),
            PostfixCalculatorImpl()
        )
    }

    @Bean
    fun expressionCalculatorFactory(): ExpressionCalculatorFactory {
        return SingletonExpressionCalculatorFactoryImpl(expressionCalculator())
    }

}