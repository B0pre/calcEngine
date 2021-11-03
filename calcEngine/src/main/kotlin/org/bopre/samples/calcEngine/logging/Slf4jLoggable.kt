package org.bopre.samples.calcEngine.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class Slf4jLoggable {
    val log: Logger = LoggerFactory.getLogger(this::class.java)
}