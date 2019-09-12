package com.br;

import com.br.lexicon.AnalyzerLexicon;
import com.br.util.FunctionUtil;

public class MainApplication {

    public static void main(String[] args) {
        String program = "1";
        AnalyzerLexicon analyzerLexicon = new AnalyzerLexicon(program);

        analyzerLexicon.analyze();

        FunctionUtil.print(analyzerLexicon.toString());
    }
}