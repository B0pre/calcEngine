package org.bopre.samples.calcEngine.service.interpreter

import org.bopre.samples.calcEngine.service.interpreter.support.Token
import org.bopre.samples.calcEngine.service.interpreter.support.calc.CalcPart

interface PostfixCreator {

    interface Result {
        data class Success(val parts: List<CalcPart>) : Result
        data class Fail(val message: String) : Result
    }

    fun createFromTokens(tokens: List<Token>): Result
}