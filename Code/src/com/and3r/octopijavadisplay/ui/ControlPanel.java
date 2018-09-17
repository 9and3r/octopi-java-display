package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends BasePanel {


    public ControlPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init() {
        setLayout(new GridLayout(0, 5));
        add(Box.createGlue());
        add(new JLabel("Up"));
        add(Box.createGlue());
        add(Box.createGlue());
        add(new JLabel("Up (Z)"));
        add(new JLabel("Left"));
        add(new JLabel("Home"));
        add(new JLabel("Right"));
        add(Box.createGlue());
        add(new JLabel("Home (Z)"));
        add(Box.createGlue());
        add(new JLabel("Down"));
        add(Box.createGlue());
        add(Box.createGlue());
        add(new JLabel("Down (Z)"));
    }
}
