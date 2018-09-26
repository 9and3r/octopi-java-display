package com.and3r.octopijavadisplay.ui.icons;

import com.and3r.octopijavadisplay.datamodels.TemperatureDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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

        // Off button
        JButton currentButton = new JButton("Off");
        currentButton.setActionCommand("off");
        bottomPanel.add(currentButton);

        if (extruder){
            icon = new ExtruderIcon();

            currentButton = new JButton("180ºC");
            bottomPanel.add(currentButton);

            currentButton = new JButton("200ºC");
            bottomPanel.add(currentButton);

            currentButton = new JButton("210ºC");
            bottomPanel.add(currentButton);

            currentButton = new JButton("220ºC");
            bottomPanel.add(currentButton);



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
            currentButton = new JButton("60ºC (PLA)");
            bottomPanel.add(currentButton);

            currentButton = new JButton("ºC (PLA)");
            bottomPanel.add(currentButton);
        }


        add(icon, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(bottomPanel, BorderLayout.SOUTH);

        addComponentListener(this);
        setOpaque(false);
    }

    public void setTemp(TemperatureDevice temp) {
        if (temp != null){
            icon.setTemp(temp.actual);
            label.setText(String.valueOf(temp.actual) + "/" + String.valueOf(temp.target) + "ºC");
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
