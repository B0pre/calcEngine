package org.bopre.samples.calcEngine.service.interpreter.support.calc

import org.bopre.samples.calcEngine.service.interpreter.support.VariableStorage

class VariableValue(val name: String, val storage: VariableStorage) : CalcValue.MutableValue {

    override fun setValue(value: Double) =
        storage.assignVariable(name, value)


    override fun getValue(): CalcValue.CalcResult {
        val rr = storage.getByName(name)
        if (rr is VariableStorage.GetResult.Success)
            return CalcValue.CalcResult.Success(rr.value)

        return CalcValue.CalcResult.Fail("failed get $name $rr")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VariableValue

        if (name != other.name) return false
        if (storage != other.storage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + storage.hashCode()
        return result
    }

    override fun toString(): String {
        return "$name"
    }

}