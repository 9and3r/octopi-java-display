package com.and3r.octopijavadisplay.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TabbedPanel extends JPanel implements MouseListener {

    private boolean initiated;

    private ArrayList<TabLabel> panelTitles;
    private ArrayList<JPanel> panels;

    private JPanel currentPanel;
    private TabLabel currentTitle;

    private JPanel topPanel;

    public TabbedPanel(){
        panels = new ArrayList<>();
        panelTitles = new ArrayList<>();
        setOpaque(false);
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(ColorManager.backgroundColorTabs);
        topPanel.setLayout(new GridLayout(1, 0));
        add(topPanel, BorderLayout.NORTH);
    }

    public void add(String title, JPanel panel) {
        TabLabel tabLabel = new TabLabel(title, panelTitles.size());
        tabLabel.addMouseListener(this);
        this.panelTitles.add(tabLabel);
        this.panels.add(panel);
        this.topPanel.add(tabLabel);

        if (!initiated) {
            initiated = true;
            selectPanel(0);
        }
    }

    public void selectPanel(int selectedIndex){
        if (currentPanel != null){
            remove(currentPanel);
        }
        if (currentTitle != null){
            currentTitle.setOpaque(false);
        }
        currentPanel = panels.get(selectedIndex);
        currentTitle = panelTitles.get(selectedIndex);
        currentTitle.setOpaque(true);

        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int index = Integer.parseInt(e.getComponent().getName());
        selectPanel(index);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
