package org.bopre.samples.calcEngine.service

import org.bopre.samples.calcEngine.data.dao.StoredVariableRepository
import org.bopre.samples.calcEngine.data.dto.Variable
import org.bopre.samples.calcEngine.data.mapping.StoredVarMapperImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import javax.transaction.Transactional

@DataJpaTest
@Import(
    value = [
        MetaManagementServiceImpl::class,
        StoredVarMapperImpl::class
    ]
)
internal class MetaManagementServiceImplTest(
    @Autowired private val variableRepository: StoredVariableRepository,
    @Autowired private val managementService: MetaManagementService
) {

    @Test
    @Transactional
    @Sql(
        statements = [
            "insert into variable (variable_id, name, value) VALUES ('0fddd611-50c7-4745-a789-5a7952eb0544', 'a', 1);",
            "insert into variable (variable_id, name, value) VALUES ('e13407b6-0a55-4879-b981-bd78e4516aa9', 'b', -1);",
            "insert into variable (variable_id, name, value) VALUES ('25f3d826-5ad2-45d3-a8cd-26d97d98a8b9', 'c', 0.5);"
        ]
    )
    fun getAllVariables() {
        val expected = listOf(
            Variable(name = "a", value = 1.0),
            Variable(name = "b", value = -1.0),
            Variable(name = "c", value = 0.5)
        )

        val actual = managementService.getAllVariables();

        assertEquals(expected, actual, "wrong varialbes list")
    }

    @Test
    @Transactional
    @Sql(
        statements = [
            "insert into variable (variable_id, name, value) VALUES ('0fddd611-50c7-4745-a789-5a7952eb0544', 'a', 1);",
            "insert into variable (variable_id, name, value) VALUES ('e13407b6-0a55-4879-b981-bd78e4516aa9', 'b', -1);",
            "insert into variable (variable_id, name, value) VALUES ('25f3d826-5ad2-45d3-a8cd-26d97d98a8b9', 'c', 0.5);"
        ]
    )
    fun removeAllVariables() {
        assertTrue(variableRepository.findAll().any(), "expected variables in repository before operation")

        managementService.removeAllVariables()
        assertTrue(variableRepository.findAll().none(), "expected no variables in repository after operation")
    }

}