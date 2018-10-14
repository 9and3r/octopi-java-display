package com.and3r.octoprint.javadisplay.ui.mainpanels.connected;

import com.and3r.octoprint.javadisplay.connection.OctoprintConnectionManager;
import com.and3r.octoprint.javadisplay.ui.components.BasePanel;
import com.and3r.octoprint.javadisplay.ui.components.icons.ArrowIcon;
import com.and3r.octoprint.javadisplay.ui.components.icons.HomeIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlPanel extends BasePanel {


    public ControlPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init(Object... args) {
        setOpaque(false);
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

        ArrowIcon arrowUpZ = new ArrowIcon(ArrowIcon.UP, true);
        arrowUpZ.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.moveHead("z", 10);
            }
        });

        ArrowIcon arrowDownZ = new ArrowIcon(ArrowIcon.DOWN, true);
        arrowDownZ.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.moveHead("z", -10);
            }
        });

        HomeIcon homeIconZ = new HomeIcon();
        homeIconZ.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                octoprint.homeZ();
            }
        });



        add(Box.createGlue());
        add(arrowIconUp);
        add(Box.createGlue());
        add(Box.createGlue());
        add(arrowUpZ);
        add(arrowIconLeft);
        add(homeIcon);
        add(arrowIconRight);
        add(Box.createGlue());
        add(homeIconZ);
        add(Box.createGlue());
        add(arrowIconDown);
        add(Box.createGlue());
        add(Box.createGlue());
        add(arrowDownZ);
    }
}
