package org.bopre.samples.calcEngine.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculationServiceImplTest {

    companion object {
        const val DOUBLE_DELTA = 0.00000001;
    }

    lateinit var calculationService: CalculationService

    @BeforeEach
    fun beforeEach() {
        calculationService = CalculationServiceImpl()
    }

    @Test
    fun sumOperation() {
        val a = 5.0
        val b = 10.0
        val expected = 15.0

        val actual = calculationService.sumOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

    @Test
    fun diffOperation() {
        val a = 5.0
        val b = 10.0
        val expected = -5.0;

        val actual = calculationService.diffOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

    @Test
    fun divOperation() {
        val a = 5.0
        val b = 10.0
        val expected = 0.5

        val actual = calculationService.divOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

    @Test
    fun mulOperation() {
        val a = 5.0
        val b = 10.0
        val expected = 50.0

        val actual = calculationService.mulOperation(a, b)

        assertEquals(expected, actual.result, DOUBLE_DELTA, "wrong result")
    }

}