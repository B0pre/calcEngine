package org.bopre.samples.calcEngine.service.interpreter.support.calc

interface BinaryOperation : CalcPart {
    fun calc(leftOperand: CalcValue, rightOperand: CalcValue): CalcValue;
}