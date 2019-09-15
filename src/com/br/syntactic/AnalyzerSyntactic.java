package com.br.syntactic;

import com.br.lexicon.AnalyzerLexicon;
import com.br.token.LexemeToken;

import java.util.List;

public class AnalyzerSyntactic {

    private List<LexemeToken> lexemeTokens;

    public AnalyzerSyntactic(String program){
        AnalyzerLexicon analyzerLexicon = new AnalyzerLexicon(program);
        this.lexemeTokens = analyzerLexicon.analyze();
    }

    /*
       TODO: Verificar se existe identificador e ponto e virgula no programa
       if(!Character.isLetter(programArray[0])){
            messageErrors.append("* Identificado obrigatório").append("\n");
            return;
        }

        if(!isSemicolon(programArray[programArray.length-1])){
            messageErrors.append("* Ponto e virgula(;) ao final do programa é obrigatório").append("\n");
            return;
        }
     */
}
