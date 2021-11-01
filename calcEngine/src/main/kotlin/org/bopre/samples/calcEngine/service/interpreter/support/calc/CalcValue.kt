package org.bopre.samples.calcEngine.service.interpreter.support.calc

interface CalcValue : CalcPart {
    interface MutableValue : CalcValue {
        override var value: Double
    }

    interface ImmutableValue : CalcValue

    val value: Double
}