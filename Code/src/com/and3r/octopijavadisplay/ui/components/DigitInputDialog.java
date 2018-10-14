package com.and3r.octopijavadisplay.ui.components;

import com.and3r.octopijavadisplay.ui.utils.ColorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitInputDialog extends JDialog implements ActionListener {

    private final String rightStaticValue;
    private final JLabel numberLabel;
    private final int maxDigits;
    private final JButton buttonSet;
    private Font font;
    private String digitString;

    public DigitInputDialog(Frame owner, String title,  String rightStaticValue, int maxDigits, DigitInputDialog.Listener listener) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        this.rightStaticValue = rightStaticValue;
        this.maxDigits = maxDigits;

        setLayout(new BorderLayout());

        digitString = "";

        font = new Font("Arial", Font.PLAIN, 42);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        numberLabel = new JLabel("", SwingConstants.RIGHT);
        topPanel.add(numberLabel, BorderLayout.CENTER);
        numberLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        numberLabel.setFont(font);
        numberLabel.setOpaque(true);
        numberLabel.setBackground(ColorManager.backgroundColor2);

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
                if (digitString.length() > 0){
                    digitString = digitString.substring(0, digitString.length() - 1);
                    updateDigitString();
                }
            }
        });
        mainButtonsPanel.add(deleteButton);

        mainButtonsPanel.add(generateButton("0"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0, 10, 10));
        buttonSet = new JButton("Set");
        buttonSet.setFont(font);
        buttonSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    listener.onInputSet(Integer.parseInt(digitString));
                    dispose();
                }catch (NumberFormatException exception){
                    // Nothing to do. User should set a correct input
                }
            }
        });

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
        updateDigitString();
        pack();
        setLocationRelativeTo(owner);
    }

    private void updateDigitString(){
        numberLabel.setText(digitString + rightStaticValue);

        // Check if the value is valid
        try{
            Integer.parseInt(digitString);
            buttonSet.setEnabled(true);
        }catch (NumberFormatException e){
            buttonSet.setEnabled(false);
        }
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
        if (digitString.length() == maxDigits && digitString.substring(0, 1).equals("0")){
            digitString = digitString.substring(1);
        }
        if (digitString.length() < maxDigits){
            JButton source = (JButton) e.getSource();
            digitString += source.getName();
            updateDigitString();
        }
    }

    public static interface Listener{
        void onInputSet(int value);
    }
}
