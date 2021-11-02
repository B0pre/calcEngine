package org.bopre.samples.calcEngine.service.interpreter

interface VariableStorage {

    interface VariableStorageManager : VariableStorage {
        fun listVariables(): Map<String, Double>
        fun clean()
    }

    interface GetResult {
        data class Success(val value: Double) : GetResult
        data class Fail(val message: String) : GetResult
    }

    fun getByName(name: String): GetResult

    fun assignVariable(name: String, value: Double)
}