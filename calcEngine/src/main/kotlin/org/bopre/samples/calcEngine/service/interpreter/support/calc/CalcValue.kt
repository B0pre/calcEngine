package org.bopre.samples.calcEngine.service.interpreter.support.calc

interface CalcValue : CalcPart {
    interface MutableValue : CalcValue {
        fun setValue(value: Double);
    }

    interface ImmutableValue : CalcValue

    interface CalcResult {
        data class Success(val value: Double) : CalcResult
        data class Fail(val message: String) : CalcResult
    }

    fun getValue(): CalcResult;
}