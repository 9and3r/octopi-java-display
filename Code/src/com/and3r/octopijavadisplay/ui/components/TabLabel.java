package com.and3r.octopijavadisplay.ui.components;

import com.and3r.octopijavadisplay.ui.utils.ColorManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TabLabel extends JPanel {

    private static final Font titleFonts = new Font("Arial", Font.PLAIN, 22);

    public TabLabel(String title, int position){
        setOpaque(false);
        setLayout(new BorderLayout());
        JLabel label = new JLabel(title.toUpperCase(), SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        label.setFont(titleFonts);
        setBackground(ColorManager.primaryColor);
        setName(String.valueOf(position));
        add(label, BorderLayout.CENTER);
    }

}
