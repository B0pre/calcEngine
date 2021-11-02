package org.bopre.samples.calcEngine.data.dao

import org.bopre.samples.calcEngine.data.entity.StoredVariable
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StoredVariableRepository : CrudRepository<StoredVariable, UUID> {
    @Query("SELECT V FROM StoredVariable V ORDER BY V.id")
    fun listPageable(page: Pageable): List<StoredVariable>

    @Query("SELECT V FROM StoredVariable V WHERE V.name = :name")
    fun byName(name: String): StoredVariable?
}