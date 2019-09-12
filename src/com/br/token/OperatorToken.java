package com.br.token;

public enum OperatorToken {

    SUM("+"),
    MULTIPLICATION("*"),
    SQUARED("**"),
    DIVISION("/"),
    SUBTRACTION("-"),
    ASSIGNMENT("="),
    SEMICOLON(";"),

    IDENTIFIER("id"),

    CONSTANT_INTEGER("int"),
    CONSTANT_FLOAT("float");

    private String value;

    OperatorToken(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}