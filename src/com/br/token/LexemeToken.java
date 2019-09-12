package com.br.token;

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
        return "\n\tToken: " + getOperatorToken() + "\n\t" + "Lexeme: " + getLexeme() + "\n";
    }
}