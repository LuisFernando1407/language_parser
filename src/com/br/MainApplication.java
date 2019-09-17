package com.br;

import com.br.lexicon.AnalyzerLexicon;
import com.br.util.Action;
import com.br.util.FunctionUtil;
import com.br.util.Menu;

import java.awt.*;

public class MainApplication {

    public static void main(String[] args) {
        /*
            Exemplos de programas testes

            1) x1 = 2 * 3 + ( 1 ** 2 ) - 10 ;
            2) x1 = 2 + ( 1 //falha
            3) z = 2 * 3 - ( 3 ** 2 ) / 3 ;
         */

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