package com.br.lexicon;

import com.br.token.LexemeToken;
import com.br.token.OperatorToken;
import com.br.util.FunctionUtil;
import sun.tools.jstat.Token;

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


    public void analyze(){
        if(program == null) FunctionUtil.print("Nenhuma cadeia de caracters apresentado");

        programArray = program.toCharArray();

        if(!Character.isLetter(programArray[0])){
            FunctionUtil.print("Identificado obrigatório");
            return;
        }

        for(i = 0; i < programArray.length; i++){
            if(!isIdentifier(programArray[i]))
            if(!isOperator(programArray[i]))
            if(!isInteger(programArray[i]))
            if(!isSemicolon(programArray[i]))
            if(!Character.isWhitespace(programArray[i])){
                FunctionUtil.print("Cadeia não reconhecida");
                return;
            }
        }
    }

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

    private boolean isOperator(char character) {

        if (character == OperatorToken.ASSIGNMENT.getValue().charAt(0)) {
            lexemeTokens.add(new LexemeToken(OperatorToken.ASSIGNMENT,  OperatorToken.ASSIGNMENT.getValue()));
            return true;
        }else if(character == OperatorToken.MULTIPLICATION.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.MULTIPLICATION,  OperatorToken.MULTIPLICATION.getValue()));
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

    private boolean isInteger(char character){
        if (Character.isDigit(character)) {
            StringBuilder digit = new StringBuilder();
            do {
                digit.append(programArray[i]).append("");
                i += 1;
            } while (i != programArray.length && (Character.isDigit(programArray[i]) || programArray[i] == '.'));

            if(!isFloat(digit.toString())){
                lexemeTokens.add(new LexemeToken(OperatorToken.CONSTANT_INTEGER, digit.toString()));
            }

            return true;
        }
        return false;
    }

    private boolean isFloat(String digits){
        if(digits.contains(".")){
            lexemeTokens.add(new LexemeToken(OperatorToken.CONSTANT_FLOAT, digits));
            return true;
        }
        return false;
    }

    private boolean isSemicolon(char character){
        if(character == OperatorToken.SEMICOLON.getValue().charAt(0)){
            lexemeTokens.add(new LexemeToken(OperatorToken.SEMICOLON, OperatorToken.SEMICOLON.getValue()));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return lexemeTokens.toString().replace(",", "");
    }
}