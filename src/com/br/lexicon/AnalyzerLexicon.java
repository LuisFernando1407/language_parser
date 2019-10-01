package com.br.lexicon;

import com.br.token.LexemeToken;
import com.br.token.OperatorToken;
import com.br.util.FunctionUtil;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

public class AnalyzerLexicon {
    private List<LexemeToken> lexemeTokens;
    private String program;
    private char[] programArray;
    private int i;

    public AnalyzerLexicon(String program){
        this.program = program;
        this.lexemeTokens = new ArrayList<>();
    }

    /* Método de execução que retorna os lexemas [ANALISADOR] */
    public List<LexemeToken> analyze(){
        if(program == null) return null;

        programArray = program.toCharArray();

        for(i = 0; i < programArray.length; i++){
            if(!isIdentifier(programArray[i]))
            if(!isOperator(programArray[i]))
            if(!isParenthesis(programArray[i]))
            if(!isInteger(programArray[i]))
            if(!isSemicolon(programArray[i]))
            if(!Character.isWhitespace(programArray[i])){
                lexemeTokens.add(new LexemeToken(OperatorToken.ERROR,
                        String.valueOf(programArray[i])));
                return null;
            }
        }
        return lexemeTokens;
    }

    /* Identifica se existe um ID no começo do programa */
    private boolean isIdentifier(char character){
        if(Character.isLetter(character)){
            StringBuilder identifier = new StringBuilder();
            do{

                identifier.append(programArray[i]);
                i = i + 1;

            }while (i != programArray.length &&
                    (
                            Character.isLetter(programArray[i]) ||
                            Character.isDigit(programArray[i])
                    )
            );

            lexemeTokens.add(new LexemeToken(OperatorToken.IDENTIFIER, identifier.toString()));

            if(i != programArray.length){
                i = i - 1;
            }
            return true;
        }
        return false;
    }

    /* Identifica se existe um operador válido (Lista de operadores em OperatorToken) */
    private boolean isOperator(char character) {

        if (character == OperatorToken.ASSIGNMENT.getValue().charAt(0)) {
            lexemeTokens.add(new LexemeToken(OperatorToken.ASSIGNMENT,  OperatorToken.ASSIGNMENT.getValue()));
            return true;
        }else if(character == OperatorToken.MULTIPLICATION.getValue().charAt(0)){
            StringBuilder sequence = new StringBuilder();
            do {
                sequence.append(programArray[i]).append("");
                i += 1;
            } while (i != programArray.length &&
                    programArray[i] == OperatorToken.MULTIPLICATION.getValue().charAt(0)
            );

            if(!isPow(sequence.toString()) && sequence.toString().length() == 1){
                lexemeTokens.add(new LexemeToken(OperatorToken.MULTIPLICATION,  OperatorToken.MULTIPLICATION.getValue()));
            }else{
                if(sequence.toString().length() > 2){
                    lexemeTokens.add(new LexemeToken(OperatorToken.ERROR,
                            sequence.toString()));
                }
            }

            return true;
        }else if(character == OperatorToken.SUM.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.SUM,  OperatorToken.SUM.getValue()));
            return true;
        } else if(character == OperatorToken.SUBTRACTION.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.SUBTRACTION,  OperatorToken.SUBTRACTION.getValue()));
            return true;
        }else if(character == OperatorToken.DIVISION.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.DIVISION,  OperatorToken.DIVISION.getValue()));
            return true;
        }
        return false;
    }

    /* Verificar se tem parêntese */
    private boolean isParenthesis(char character){
        if(character == OperatorToken.LEFT_PARENTHESIS.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.LEFT_PARENTHESIS,  OperatorToken.LEFT_PARENTHESIS.getValue()));
            return true;
        }else if(character == OperatorToken.RIGHT_PARENTHESIS.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.RIGHT_PARENTHESIS,  OperatorToken.RIGHT_PARENTHESIS.getValue()));
            return true;
        }
        return false;
    }

    /* Verifica se é um inteiro e ao final testa se é um ponto flutuante */
    private boolean isInteger(char character){
        if (Character.isDigit(character)) {
            StringBuilder digit = new StringBuilder();
            do {
                digit.append(programArray[i]).append("");
                i += 1;
            } while (i != programArray.length && (Character.isDigit(programArray[i]) || programArray[i] == '.'));

            int occurrencesPoint = digit.toString().length() - digit.toString().replace(".", "").length();

            if(occurrencesPoint == 0){
                lexemeTokens.add(new LexemeToken(OperatorToken.CONSTANT_INTEGER, digit.toString()));
                return true;
            }else{
                return !isFloat(digit.toString(), occurrencesPoint);
            }
        }
        return false;
    }

    /* Verifica se contém um ponto(.) em um cadeia de digitos */
    private boolean isFloat(String digits, int amountPoints){
        if(amountPoints == 1){
            lexemeTokens.add(new LexemeToken(OperatorToken.CONSTANT_FLOAT, digits));
            return true;
        }
        lexemeTokens.add(new LexemeToken(OperatorToken.ERROR,
                digits.toString()));
        return false;
    }

    /* Verifica se contém um Math pow (ELEVADO) [**] em um sequência de caracteres */
    private boolean isPow(String sequence){
        if(sequence.toString().length() == 2){
            lexemeTokens.add(new LexemeToken(OperatorToken.SQUARED, sequence));
            return true;
        }

        return false;
    }

    /* Verifica se contém um ponto e virgula(;) */
    private boolean isSemicolon(char character){
        if(character == OperatorToken.SEMICOLON.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.SEMICOLON, OperatorToken.SEMICOLON.getValue()));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if(lexemeTokens.size() > 0) return lexemeTokens.toString().replace(",", "");
        return null;
    }
}