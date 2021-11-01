package org.bopre.samples.calcEngine.service.interpreter.support

enum class TokenType(val regex: String) {
    VALUE("[0-9]*(\\.?[0-9]+)"),
    PLUS("[+]"),
    MINUS("[-]"),
    MUL("[*]"),
    DIV("[/]"),
    BRACKET_LEFT("[(]"),
    BRACKET_RIGHT("[)]");

    override fun toString(): String {
        return name;
    }
}