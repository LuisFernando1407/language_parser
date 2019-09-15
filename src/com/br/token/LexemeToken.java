package com.br.token;

import com.br.util.FunctionUtil;

public class LexemeToken {

    private OperatorToken operatorToken;
    private String lexeme;

    public LexemeToken(OperatorToken operatorToken, String lexeme) {
        this.operatorToken = operatorToken;
        this.lexeme = lexeme;
    }

    public OperatorToken getOperatorToken() {
        return operatorToken;
    }

    public void setOperatorToken(OperatorToken operatorToken) {
        this.operatorToken = operatorToken;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {

        String value;

        value = getOperatorToken().getValue().contains("float") || getOperatorToken().getValue().contains("Integer") ?
                     "Value: " + getLexeme() + "\n" :
                getOperatorToken().getValue().contains("Cadeia") ?
                        "Error: " + getLexeme() + "\n" :
                "Lexeme: " + getLexeme() + "\n";

        return "\n\tToken: " + getOperatorToken() + "\n\t" + value;
    }
}