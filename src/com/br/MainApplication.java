package com.br;

import com.br.lexicon.AnalyzerLexicon;
import com.br.util.Action;
import com.br.util.FunctionUtil;
import com.br.util.Menu;

import java.awt.*;

public class MainApplication {

    public static void main(String[] args) {
        String projectName = "PW1 LIP - Luís Fernando & Letícia Farias";
        String[] buttonsName = {
                "ANALYZER LEXICON",
                "ANALYZER SYNTACTIC",
        };

        Dimension sizeScreen = new Dimension(400, 400);

        new Menu(
                projectName,
                buttonsName,
                new Action().getActions(),
                sizeScreen
        ).execute();
    }
}