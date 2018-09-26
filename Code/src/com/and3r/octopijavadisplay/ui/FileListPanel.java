package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;

import javax.swing.*;
import java.awt.*;

public class FileListPanel extends BasePanel {

    public FileListPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init(Object... args) {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));

        DragScrollListener dragScrollListener = new DragScrollListener(mainPanel);
        JScrollPane jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getViewport().setOpaque(false);
        mainPanel.addMouseListener(dragScrollListener);
        mainPanel.addMouseMotionListener(dragScrollListener);

        for (int i=0; i<200; i++){
            mainPanel.add(new JLabel("Prueba"));
        }

        add(jScrollPane, BorderLayout.CENTER);


    }



}
