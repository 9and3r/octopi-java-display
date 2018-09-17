package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;
import com.and3r.octopijavadisplay.OctoprintStatusListener;
import com.and3r.octopijavadisplay.ui.icons.ExtruderIcon;
import com.and3r.octopijavadisplay.ui.icons.TemperatureIcon;
import com.and3r.octopijavadisplay.ui.icons.TemperaturesPanel;

import javax.swing.*;
import java.awt.*;

public class MainJframe extends JFrame implements OctoprintStatusListener {



    public MainJframe(OctoprintConnectionManager octoprintConnectionManager){
        setLayout(new BorderLayout());
        add(new ControlPanel(octoprintConnectionManager), BorderLayout.CENTER);
        setSize(800, 480);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        //temperatureIcon.setTemp(status.printerStatus.temperature.bed.actual);
    }
}
