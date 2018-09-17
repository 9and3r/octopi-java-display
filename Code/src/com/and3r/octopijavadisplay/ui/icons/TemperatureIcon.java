package com.and3r.octopijavadisplay.ui.icons;

import com.and3r.octopijavadisplay.datamodels.TemperatureDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class TemperatureIcon extends JPanel implements ComponentListener {

    private JLabel label;
    private ExtruderIcon icon;

    public TemperatureIcon(){
        setLayout(new BorderLayout());
        label = new JLabel("", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        icon = new ExtruderIcon();
        add(icon, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        addComponentListener(this);
        setOpaque(false);
    }

    public void setTemp(TemperatureDevice temp) {
        icon.setTemp(temp.actual);
        label.setText(String.valueOf(temp.actual) + "/" + String.valueOf(temp.target) + "ºC");
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
