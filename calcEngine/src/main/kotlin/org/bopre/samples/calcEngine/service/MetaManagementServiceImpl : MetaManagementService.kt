package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dao.StoredVariableRepository
import org.bopre.samples.calcEngine.data.dto.Variable
import org.bopre.samples.calcEngine.data.mapping.StoredVarMapperImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MetaManagementServiceImpl(
    @Autowired private val variableRepository: StoredVariableRepository,
    @Autowired private val variableMapper: StoredVarMapperImpl
) : MetaManagementService {

    @Transactional
    override fun removeAllVariables() = variableRepository.deleteAll()

    @Transactional
    override fun getAllVariables(): List<Variable> =
        variableRepository.listPageable(Pageable.unpaged())
            .map(variableMapper::mapToDTO)
            .sortedBy(Variable::name)
            .toList()

}