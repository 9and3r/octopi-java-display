package com.and3r.octopijavadisplay.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitInputDialog extends JDialog implements ActionListener {

    private final String rightStaticValue;
    private final JLabel numberLabel;
    private final int maxDigits;
    private Font font;
    private String temperatureString;

    public DigitInputDialog(Frame owner, String title,  String rightStaticValue, int maxDigits) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        this.rightStaticValue = rightStaticValue;
        this.maxDigits = maxDigits;

        setLayout(new BorderLayout());

        temperatureString = "";

        font = new Font("Arial", Font.PLAIN, 42);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        numberLabel = new JLabel("", SwingConstants.RIGHT);
        topPanel.add(numberLabel, BorderLayout.CENTER);
        numberLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        numberLabel.setForeground(Color.WHITE);
        numberLabel.setFont(font);
        numberLabel.setOpaque(true);
        numberLabel.setBackground(ColorManager.backgroundColorTabs);

        JPanel mainButtonsPanel = new JPanel();
        mainButtonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainButtonsPanel.setLayout(new GridLayout(4, 3, 10, 10));

        mainButtonsPanel.add(generateButton("7"));
        mainButtonsPanel.add(generateButton("8"));
        mainButtonsPanel.add(generateButton("9"));

        mainButtonsPanel.add(generateButton("4"));
        mainButtonsPanel.add(generateButton("5"));
        mainButtonsPanel.add(generateButton("6"));

        mainButtonsPanel.add(generateButton("1"));
        mainButtonsPanel.add(generateButton("2"));
        mainButtonsPanel.add(generateButton("3"));

        JButton deleteButton = new JButton("<-");
        deleteButton.setFont(font);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (temperatureString.length() > 0){
                    temperatureString = temperatureString.substring(0, temperatureString.length() - 1);
                    updateTemperatureString();
                }
            }
        });
        mainButtonsPanel.add(deleteButton);

        mainButtonsPanel.add(generateButton("0"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0, 10, 10));
        JButton buttonSet = new JButton("Set");
        buttonSet.setFont(font);
        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setFont(font);
        bottomPanel.add(buttonCancel);
        bottomPanel.add(buttonSet);

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(mainButtonsPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        updateTemperatureString();
        pack();
        setLocationRelativeTo(owner);
    }

    private void updateTemperatureString(){
        numberLabel.setText(temperatureString + rightStaticValue);
    }

    private JButton generateButton(String text){
        JButton button = new JButton(text);
        button.setName(text);
        button.addActionListener(this);
        button.setFont(font);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (temperatureString.length() == maxDigits && temperatureString.substring(0, 1).equals("0")){
            temperatureString = temperatureString.substring(1);
        }
        if (temperatureString.length() < maxDigits){
            JButton source = (JButton) e.getSource();
            temperatureString += source.getName();
            updateTemperatureString();
        }
    }
}
