package com.and3r.octopijavadisplay.ui.components;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.printer.TemperatureDevice;
import com.and3r.octopijavadisplay.ui.components.icons.BedIcon;
import com.and3r.octopijavadisplay.ui.components.icons.ExtruderIcon;
import com.and3r.octopijavadisplay.ui.components.icons.TemperatureIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureDisplayPanel extends BasePanel {

    private JLabel extrudeDistanceLabel;
    private JLabel label;
    private TemperatureIcon icon;
    private int extrudeLength;
    private boolean extruder;

    public TemperatureDisplayPanel(OctoprintConnectionManager octoprintConnectionManager, boolean extruder){
        super(octoprintConnectionManager, extruder);
    }

    private void setExtrudeLength(int extrudeLength){
        this.extrudeLength = extrudeLength;
        extrudeDistanceLabel.setText(extrudeLength + " mm");
    }

    public void setTemp(TemperatureDevice temp) {
        if (temp != null){
            icon.setTemp(temp.actual);
            label.setText(String.valueOf((int)temp.actual) + "/" + String.valueOf((int)temp.target) + "ºC");
        }
    }

    @Override
    protected void init(Object... args) {
        this.extruder = (boolean) args[0];
        setLayout(new BorderLayout(10, 10));
        label = new JLabel("", SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new GridLayout(1, 0, 10, 10));

        if (extruder){
            icon = new ExtruderIcon();

            JPanel extrudePanel = new JPanel();
            extrudePanel.setLayout(new GridLayout(0, 1, 10, 10));
            extrudePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
            extrudePanel.setOpaque(false);

            JButton retractButton = new JButton("Retract");
            extrudePanel.add(retractButton);


            extrudeDistanceLabel = new JLabel("", SwingConstants.CENTER);
            extrudeDistanceLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    DigitInputDialog digitInputDialog = new DigitInputDialog((Frame) SwingUtilities.getWindowAncestor(TemperatureDisplayPanel.this), "Set distance", " mm", 3, new DigitInputDialog.Listener() {
                        @Override
                        public void onInputSet(int value) {
                            setExtrudeLength(value);
                        }
                    });
                    digitInputDialog.setVisible(true);
                }
            });
            setExtrudeLength(1);
            extrudePanel.add(extrudeDistanceLabel);


            JButton extrudeButton = new JButton("Extrude");
            extrudePanel.add(extrudeButton);

            add(extrudePanel, BorderLayout.EAST);

        }else{
            icon = new BedIcon();
        }


        add(icon, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(bottomPanel, BorderLayout.SOUTH);

        setOpaque(false);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DigitInputDialog digitInputDialog = new DigitInputDialog((Frame) SwingUtilities.getWindowAncestor(TemperatureDisplayPanel.this), "Set temperature", " ºC", 3, new DigitInputDialog.Listener() {
                    @Override
                    public void onInputSet(int value) {
                        if (!extruder){
                            octoprint.setBedTargetTemperature(value);
                        }else{
                            octoprint.setToolTargetTemperature(0, value);
                        }
                    }
                });
                digitInputDialog.setVisible(true);
            }
        });
    }
}
