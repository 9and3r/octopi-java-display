package com.and3r.octopijavadisplay.ui.icons;

import com.and3r.octopijavadisplay.datamodels.TemperatureDevice;
import com.and3r.octopijavadisplay.ui.DigitInputDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TemperatureDisplayPanel extends JPanel implements ComponentListener {

    private JLabel label;
    private TemperatureIcon icon;

    public TemperatureDisplayPanel(boolean extruder){
        setLayout(new BorderLayout(10, 10));
        label = new JLabel("", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);

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


            extrudePanel.add(new JComboBox<>(new String[]{"1mm", "2mm", "3mm", "4mm", "5mm", "8mm"}));


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

        addComponentListener(this);
        setOpaque(false);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DigitInputDialog digitInputDialog = new DigitInputDialog((Frame) SwingUtilities.getWindowAncestor(TemperatureDisplayPanel.this), "Set temperature", " ºC", 3);
                digitInputDialog.setVisible(true);
            }
        });
    }

    public void setTemp(TemperatureDevice temp) {
        if (temp != null){
            icon.setTemp(temp.actual);
            label.setText(String.valueOf((int)temp.actual) + "/" + String.valueOf((int)temp.target) + "ºC");
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        label.setFont(new Font("Arial", Font.PLAIN, getWidth()/10));
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
