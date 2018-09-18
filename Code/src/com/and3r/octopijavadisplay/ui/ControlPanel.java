package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.StatusCodeException;
import com.and3r.octopijavadisplay.ui.icons.ArrowIcon;
import com.and3r.octopijavadisplay.ui.icons.HomeIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlPanel extends BasePanel {


    public ControlPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init() {
        setLayout(new GridLayout(0, 5));

        ArrowIcon arrowIconUp = new ArrowIcon(ArrowIcon.UP);
        arrowIconUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.moveHead("y", 10);
            }
        });

        ArrowIcon arrowIconDown = new ArrowIcon(ArrowIcon.DOWN);
        arrowIconDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.moveHead("y", -10);
            }
        });

        ArrowIcon arrowIconLeft = new ArrowIcon(ArrowIcon.LEFT);
        arrowIconLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.moveHead("x", 10);
            }
        });

        ArrowIcon arrowIconRight = new ArrowIcon(ArrowIcon.RIGHT);
        arrowIconRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.moveHead("x", -10);
            }
        });

        HomeIcon homeIcon = new HomeIcon();
        homeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.homeXY();
            }
        });



        add(Box.createGlue());
        add(arrowIconUp);
        add(Box.createGlue());
        add(Box.createGlue());
        add(new JLabel("Up (Z)"));
        add(arrowIconLeft);
        add(homeIcon);
        add(arrowIconRight);
        add(Box.createGlue());
        add(new JLabel("Home (Z)"));
        add(Box.createGlue());
        add(arrowIconDown);
        add(Box.createGlue());
        add(Box.createGlue());
        add(new JLabel("Down (Z)"));
    }
}
