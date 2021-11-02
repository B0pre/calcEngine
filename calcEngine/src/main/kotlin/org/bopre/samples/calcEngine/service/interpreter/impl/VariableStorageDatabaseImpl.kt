package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.data.dao.StoredVariableRepository
import org.bopre.samples.calcEngine.data.entity.StoredVariable
import org.bopre.samples.calcEngine.service.interpreter.VariableStorage
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

class VariableStorageDatabaseImpl(private val repo: StoredVariableRepository) : VariableStorage.VariableStorageManager {

    @Transactional
    override fun listVariables(): Map<String, Double> =
        repo.listPageable(Pageable.unpaged()).associate { v -> v.name to v.condition }

    @Transactional
    override fun clean() = repo.deleteAll()

    @Transactional
    override fun getByName(name: String): VariableStorage.GetResult {
        return with(repo.byName(name)) {
            if (this == null)
                VariableStorage.GetResult.Fail("no variable $name")
            else
                VariableStorage.GetResult.Success(this.condition)
        }
    }

    @Transactional
    override fun assignVariable(name: String, value: Double) {
        val storedVariable: StoredVariable = with(repo.byName(name)) {
            this
                ?: StoredVariable(
                    id = null,
                    name = name,
                    condition = value
                )
        }
        storedVariable.condition = value
        repo.save(storedVariable)
    }

}