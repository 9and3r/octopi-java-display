package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;
import com.and3r.octopijavadisplay.OctoprintStatusListener;

import javax.swing.*;
import java.awt.*;

public class MainJframe extends JFrame implements OctoprintStatusListener {

    private JPanel currentPanel;
    private OctoprintStatus lastStatus;
    private OctoprintConnectionManager octoprintConnectionManager;

    public MainJframe(OctoprintConnectionManager octoprintConnectionManager){
        this.octoprintConnectionManager = octoprintConnectionManager;
        setLayout(new BorderLayout());
        setSize(800, 480);
        //getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        octoprintConnectionManager.addStatusListener(this);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        if (lastStatus == null || lastStatus.connectedToOctopi != status.connectedToOctopi || !lastStatus.connection.current.state.equals(status.connection.current.state)) {
            // Should change panel
            if (currentPanel != null) {
                remove(currentPanel);
            }
            JPanel newPanel;
            if (!status.connectedToOctopi) {
                newPanel = new OctoprintNotConnectedPanel(octoprintConnectionManager);
            } else {
                if (!status.isPrinterConnected()) {
                    newPanel = new PrinterNotConnectedPanel(octoprintConnectionManager);
                } else {
                    newPanel = new ControlPanel(octoprintConnectionManager);
                }
            }
            currentPanel = newPanel;
            add(newPanel, BorderLayout.CENTER);
            revalidate();
        }
        lastStatus = status;
    }
}
