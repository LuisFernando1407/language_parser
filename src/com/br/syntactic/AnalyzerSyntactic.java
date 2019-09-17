package com.br.syntactic;

import com.br.lexicon.AnalyzerLexicon;
import com.br.token.LexemeToken;
import com.br.token.OperatorToken;

import java.util.List;

public class AnalyzerSyntactic {

    private List<LexemeToken> lexemeTokens;
    private StringBuilder messages;
    private int index;

    public AnalyzerSyntactic(String program){
        AnalyzerLexicon analyzerLexicon = new AnalyzerLexicon(program);
        this.lexemeTokens = analyzerLexicon.analyze();
        this.messages = new StringBuilder();
        this.index = 0;
    }

    public void analyze(){
        if(lexemeTokens != null){
            assignment();
        }else{
            messages.append("* Erro no analisador léxico").append("\n");
        }
    }

    private void assignment(){
        if(OperatorToken.IDENTIFIER == nextToken().getOperatorToken()){
            lexeme();
            if(nextToken().getOperatorToken() == OperatorToken.ASSIGNMENT){
                lexeme();
                expression();
            }else{
                messages.append("* Erro no analisador sintático!\nO programa deve conter uma atribuição após o identificador").append("\n\n");
            }

            if(nextToken().getOperatorToken() == OperatorToken.SEMICOLON){
                assignment();
            }else{
                messages.append("* Erro no analisador sintático!\nO programa deve terminar com ponto e virgula (;)").append("\n\n");
            }
        }else{
            if(lexemeTokens.size() == 1){
                messages.append("* Erro no analisador sintático!\nO programa deve começar com um identificador").append("\n\n");
            }
        }
    }

    private LexemeToken nextToken(){
        if(index == lexemeTokens.size()) return new LexemeToken(OperatorToken.FINISH, OperatorToken.FINISH.getValue());
        return lexemeTokens.get(index);
    }

    private void expression(){
        term();
        while(
                nextToken().getOperatorToken() == OperatorToken.SUM||
                nextToken().getOperatorToken() == OperatorToken.SUBTRACTION
        ) {
            lexeme();
            term();
        }
    }

    private void term(){
        factor();
        while (
                nextToken().getOperatorToken() == OperatorToken.MULTIPLICATION ||
                nextToken().getOperatorToken() == OperatorToken.DIVISION
        ){
            lexeme();
            factor();
        }
    }

    private void factor(){
        exp();
        if(nextToken().getOperatorToken() == OperatorToken.SQUARED){
            lexeme();
            exp();
        }
    }

    private void exp(){
        if(nextToken().getOperatorToken() == OperatorToken.IDENTIFIER){
            lexeme();
        }else if(nextToken().getOperatorToken() == OperatorToken.CONSTANT_INTEGER){
            lexeme();
        }else if(nextToken().getOperatorToken() == OperatorToken.CONSTANT_FLOAT){
            lexeme();
        }else if(nextToken().getOperatorToken() == OperatorToken.LEFT_PARENTHESIS){
            lexeme();
            expression();

            if(nextToken().getOperatorToken() == OperatorToken.RIGHT_PARENTHESIS){
                lexeme();
            }else{
                messages.append("*Erro no analisador sintático!\nParênteses a direita esperado").append("\n\n");
            }
        }else{
            messages.append("* Erro no analisador sintático!\nidentificador, contante inteira, constante flutuante, parênteses esquerdo esperados").append("\n\n");
        }
    }

    private void lexeme(){
        if(index == lexemeTokens.size()) return;
        index++;
    }

    @Override
    public String toString() {
        return messages.length() == 0 ?
                "@_@ Programa processado com sucesso!\n\n" + lexemeTokens.toString().replace(",", "") :
                messages.toString().replace(",", "");
    }
}