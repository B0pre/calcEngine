package org.bopre.samples.calcEngine.service.interpreter.support

enum class TokenType(val regex: String) {
    VALUE("[0-9]*(\\.?[0-9]+)"),
    VARIABLE("[A-Za-z_][A-Za-z0-9_]*"),
    ASSIGN("[=]"),
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