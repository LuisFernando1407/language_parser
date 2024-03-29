package com.br.util;

import com.br.lexicon.AnalyzerLexicon;
import com.br.syntactic.AnalyzerSyntactic;
import com.br.token.LexemeToken;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Action {
    private String message;

    public List<ActionListener> getActions(){
        /* List of Actions */
        List<ActionListener> actionListeners = new ArrayList<>();

        /* Action button "Analyzer Lexicon" */
        actionListeners.add(e -> {
            String program = inputProgram();

            if(program != null && !program.equals("")) {
                AnalyzerLexicon analyzerLexicon = new AnalyzerLexicon(program);
                analyzerLexicon.analyze();
                showMessage(analyzerLexicon.toString());
            }
        });

        /* Action button "Analyzer Syntactic" */
        actionListeners.add(e -> {
            String program = inputProgram();

            if(program != null && !program.equals("")){
                AnalyzerSyntactic analyzerSyntactic = new AnalyzerSyntactic(program);
                analyzerSyntactic.analyze();
                showMessage(analyzerSyntactic.toString());
            }
        });

        return actionListeners;
    }

    private void alert(String message){
        JWindow jWindow = new JWindow();

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2, 1));

        JLabel jLabel = new JLabel(message, SwingConstants.CENTER);

        jPanel.add(jLabel);

        jWindow.getContentPane().add(jPanel);
        jWindow.setSize(new Dimension(350, 70));
        jWindow.setMinimumSize(new Dimension(350, 70));

        jWindow.setLocationRelativeTo(null);

        try {
            // 1.6+
            jWindow.setLocationByPlatform(true);
            jWindow.setMinimumSize(jWindow.getSize());
        } catch(Throwable ignoreAndContinue) {
            ignoreAndContinue.printStackTrace();
        }

        jWindow.setVisible(true);

    }

    private String inputProgram(){
        return JOptionPane.showInputDialog(null, "Enter the program", "Analyzer Lexicon", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMessage(String message){

        JTextArea textArea = new JTextArea(5, 10);
        textArea.setText(message);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 260));

        JOptionPane.showOptionDialog(
                null,
                scrollPane,
                "Response" ,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
    }
}