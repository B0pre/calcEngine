package org.bopre.samples.calcEngine.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class InputExpression(@JsonProperty("expression") val expr: String) {
}