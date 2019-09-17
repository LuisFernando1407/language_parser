package com.br.util;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Menu {
    private String projectName;
    private String[] buttonsNames;
    private Dimension sizeScreen;
    private java.util.List<ActionListener> actionListeners;

    public Menu(String projectName,
                String[] buttonsNames, List<ActionListener> actionListeners, Dimension sizeScreen){
        this.projectName = projectName;
        this.buttonsNames = buttonsNames;
        this.sizeScreen = sizeScreen;
        this.actionListeners = actionListeners;
    }

    private JPanel createButtons(JPanel container){
        for(int i = 0; i < buttonsNames.length; i++){
            JButton btnCreateXML = new JButton(buttonsNames[i]);
            btnCreateXML.addActionListener(actionListeners.get(i));
            if(i == 0){
                container.add(btnCreateXML, BorderLayout.NORTH);
            }else{
                container.add(btnCreateXML, BorderLayout.SOUTH);
            }
        }
        return container;
    }

    public void execute(){
        JFrame frame = new JFrame(projectName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.setBorder(new TitledBorder("Menu"));

        JPanel container = new JPanel();

        container.setLayout(new GridLayout(buttonsNames.length, buttonsNames.length));

        gui.add(createButtons(container));

        frame.setContentPane(gui);
        frame.setSize(sizeScreen);
        frame.setResizable(false);
        frame.setMinimumSize(sizeScreen);
        frame.pack();

        frame.setLocationRelativeTo(null);

        try {
            // 1.6+
            frame.setLocationByPlatform(true);
            frame.setMinimumSize(frame.getSize());
        } catch(Throwable ignoreAndContinue) {
            ignoreAndContinue.printStackTrace();
        }

        frame.setVisible(true);
    }
}