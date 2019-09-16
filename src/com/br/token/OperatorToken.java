package com.br.token;

public enum  OperatorToken {

    SUM("+"),
    MULTIPLICATION("*"),
    SQUARED("**"),
    DIVISION("/"),
    SUBTRACTION("-"),
    ASSIGNMENT("="),
    SEMICOLON(";"),

    RIGHT_PARENTHESIS(")"),
    LEFT_PARENTHESIS("("),

    IDENTIFIER("id"),

    CONSTANT_INTEGER("Integer"),
    CONSTANT_FLOAT("float"),

    FINISH("fim"),

    ERROR("Error");

    private String value;

    OperatorToken(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}