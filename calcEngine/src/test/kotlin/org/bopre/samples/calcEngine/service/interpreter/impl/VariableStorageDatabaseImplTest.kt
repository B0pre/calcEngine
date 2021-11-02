package org.bopre.samples.calcEngine.service.interpreter.impl

import org.bopre.samples.calcEngine.data.dao.StoredVariableRepository
import org.bopre.samples.calcEngine.service.interpreter.VariableStorage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
internal class VariableStorageDatabaseImplTest
@Autowired constructor(
    val repo: StoredVariableRepository
) {

    lateinit var variableStorage: VariableStorageDatabaseImpl

    @BeforeEach
    fun beforeEach() {
        variableStorage = VariableStorageDatabaseImpl(repo)
    }

    @Test
    @Transactional
    @Sql(
        statements = [
            "insert into variable (variable_id, name, value) values ('8b63419ab592467c9f29545fcfb4e67e', 'a', 4.0)",
            "insert into variable (variable_id, name, value) values ('2a907c83cd434e4ab0ef985f2eb76ea7', 'b', 10.0)"
        ]
    )
    fun listVariables() {

        val expected = mapOf("a" to 4.0, "b" to 10.0)
        val actual = variableStorage.listVariables()

        assertEquals(expected, actual, "got wrong variables list")
    }

    @Test
    @Transactional
    @Sql(
        statements = [
            "insert into variable (variable_id, name, value) values ('8b63419ab592467c9f29545fcfb4e67e', 'a', 4.0)",
            "insert into variable (variable_id, name, value) values ('2a907c83cd434e4ab0ef985f2eb76ea7', 'b', 10.0)"
        ]
    )
    fun clean() {
        val expected = emptyMap<String, Double>()
        variableStorage.clean()
        val actual = variableStorage.listVariables()

        assertEquals(expected, actual, "got wrong variables list")
    }

    @Test
    @Transactional
    @Sql(
        statements = [
            "insert into variable (variable_id, name, value) values ('8b63419ab592467c9f29545fcfb4e67e', 'a', 4.0)"
        ]
    )
    fun `getByName success`() {
        val expected = 4.0
        val actual = variableStorage.getByName("a")

        if (actual is VariableStorage.GetResult.Success)
            assertEquals(expected, actual.value, "got wrong variable value")
        else
            fail("expected success but was $actual")
    }

    @Test
    @Transactional
    fun `getByName no such variable`() {
        val actual = variableStorage.getByName("no_such_var")

        assertTrue(actual is VariableStorage.GetResult.Fail, "expected fail but was $actual")
    }

    @Test
    @Transactional
    @Sql(
        statements = [
            "insert into variable (variable_id, name, value) values ('8b63419ab592467c9f29545fcfb4e67e', 'a', 4.0)"
        ]
    )
    fun `assignVariable already existed`() {

        val expected = 10.0
        variableStorage.assignVariable("a", expected)

        val storedVariable = repo.byName("a")
        assertEquals(expected, storedVariable?.condition, "wrong variable value")
    }

    @Test
    @Transactional
    fun `assignVariable new variable`() {
        val expected = 10.0
        variableStorage.assignVariable("new_variable_name", expected)

        val storedVariable = repo.byName("new_variable_name")
        assertEquals(expected, storedVariable?.condition, "wrong variable value")
    }

}