package com.br.syntactic;

import com.br.lexicon.AnalyzerLexicon;
import com.br.token.LexemeToken;
import com.br.token.OperatorToken;
import com.br.util.FunctionUtil;

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
            /* TODO: Está dando erro: verificar factor e ponto e virgula */
            if(index != lexemeTokens.size()){
                messages.append("* Erro no analisador sintático").append("\n");
            }
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
            }else if(nextToken().getOperatorToken() == OperatorToken.SEMICOLON){
                assignment();
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
                messages.append("* Parênteses a direita esperado").append("\n");
            }
        }else{
            messages.append("* Erro: identificador, contante inteira, constante flutuante, parênteses esquerdo esperados").append("\n");
        }
    }

    private void lexeme(){
        if(index == lexemeTokens.size()) return;
        index++;
    }

    @Override
    public String toString() {
        return messages.toString();
    }
}
