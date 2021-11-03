package org.bopre.samples.calcEngine.controllers

import org.bopre.samples.calcEngine.data.exceptions.CalculationException
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestComponent
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

typealias Producer<T> = () -> T

@WebMvcTest(controllers = [RestProcessingExceptionThrowingController::class])
@Import(
    value = [
        ExceptionControllerAdvice::class,
        RestProcessingExceptionThrowingController::class
    ]
)
class ExceptionControllerAdviceTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var advice: ExceptionControllerAdvice

    @Autowired
    lateinit var controller: RestProcessingExceptionThrowingController

    @MockBean
    lateinit var errorProvier: Producer<Exception>


    @Test
    fun calculationException() {
        val expectedJson = "{\n" +
                "\"message\":\"calculation exception invalid expr\"\n" +
                "}\n";

        Mockito.`when`(errorProvier.invoke())
            .thenReturn(CalculationException("invalid expr"))

        mockMvc.perform(get("/tests/exception"))
            .andExpect(status().is4xxClientError)
            .andExpect(content().json(expectedJson))
    }

    @Test
    fun handleGeneralException() {
        val expectedJson = "{\n" +
                "\"message\":\"undefined server error\"\n" +
                "}\n";

        Mockito.`when`(errorProvier.invoke())
            .thenReturn(Exception("something went wrong"))

        mockMvc.perform(get("/tests/exception"))
            .andExpect(status().is5xxServerError)
            .andExpect(content().json(expectedJson))
    }

}

@TestComponent
@RestController
@RequestMapping("/tests")
class RestProcessingExceptionThrowingController {

    @Autowired
    lateinit var errorProvier: Producer<Exception>

    @GetMapping("/exception")
    fun exception(): String {
        throw errorProvier.invoke()
    }
}
