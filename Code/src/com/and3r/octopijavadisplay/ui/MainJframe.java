package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;
import com.and3r.octopijavadisplay.OctoprintStatusListener;
import com.and3r.octopijavadisplay.ui.icons.TemperatureIcon;
import com.and3r.octopijavadisplay.ui.icons.TemperaturesPanel;

import javax.swing.*;
import java.awt.*;

public class MainJframe extends JFrame implements OctoprintStatusListener {

    private int lastStatusInt;
    private JComponent currentPanel;
    private OctoprintStatus lastStatus;
    private OctoprintConnectionManager octoprintConnectionManager;

    public MainJframe(OctoprintConnectionManager octoprintConnectionManager){
        this.lastStatusInt = Integer.MIN_VALUE;
        this.octoprintConnectionManager = octoprintConnectionManager;
        setLayout(new BorderLayout());
        setSize(800, 480);
        getContentPane().setBackground(ColorManager.backgroundColor);
        setVisible(true);
        octoprintConnectionManager.addStatusListener(this);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        if (lastStatusInt != status.getStatus()) {
            lastStatusInt = status.getStatus();
            // Should change panel
            if (currentPanel != null) {
                remove(currentPanel);
            }
            JComponent newPanel;

            switch (status.getStatus()){
                case OctoprintStatus.STATUS_OCTOPRINT_NOT_CONNECTED_BAD_KEY:
                    newPanel = new LoadingPanel(octoprintConnectionManager, "Bad configuration. API KEY is not correct");
                    break;
                case OctoprintStatus.STATUS_OCTOPRINT_NOT_CONNECTED:
                    newPanel = new LoadingPanel(octoprintConnectionManager, "Connecting to octoprint...", 3000, "Could not connect ot octoprint on " + octoprintConnectionManager.host + ":" + octoprintConnectionManager.port);
                    break;
                case OctoprintStatus.STATUS_OCTOPRINT_CONNECTED:
                    newPanel = new PrinterNotConnectedPanel(octoprintConnectionManager);
                    break;
                case OctoprintStatus.STATUS_OCTOPRINT_CONNECTED_DETECTING_BAUD_RATE:
                    newPanel = new LoadingPanel(octoprintConnectionManager, "Detecting baudrate");
                    break;
                case OctoprintStatus.STATUS_OCTOPRINT_CONNECTED_OPERATIONAL:
                    TabbedPanel tabbedPane = new TabbedPanel();
                    newPanel = tabbedPane;

                    tabbedPane.add("Job", new CurrentJobPanel(octoprintConnectionManager));
                    tabbedPane.add("Temperature", new TemperaturesPanel(octoprintConnectionManager));
                    tabbedPane.add("Head", new ControlPanel(octoprintConnectionManager));
                    tabbedPane.add("Files", new FileListPanel(octoprintConnectionManager));
                    tabbedPane.add("System", new SystemPanel(octoprintConnectionManager));
                    break;
                default:
                    newPanel = new LoadingPanel(octoprintConnectionManager);
                    break;
            }
            currentPanel = newPanel;
            add(newPanel, BorderLayout.CENTER);
            revalidate();
        }
        lastStatus = status;
    }


}
