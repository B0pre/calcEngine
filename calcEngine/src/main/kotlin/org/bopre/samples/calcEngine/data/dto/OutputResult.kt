package org.bopre.samples.calcEngine.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OutputResult(@JsonProperty("result") val result: Double) {

}