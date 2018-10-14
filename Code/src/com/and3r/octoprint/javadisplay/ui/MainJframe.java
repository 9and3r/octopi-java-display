package com.and3r.octoprint.javadisplay.ui;

import com.and3r.octoprint.javadisplay.connection.OctoprintConnectionManager;
import com.and3r.octoprint.javadisplay.datamodels.OctoprintStatus;
import com.and3r.octoprint.javadisplay.connection.OctoprintStatusListener;
import com.and3r.octoprint.javadisplay.ui.components.TabbedPanel;
import com.and3r.octoprint.javadisplay.ui.mainpanels.connected.TemperaturesPanel;
import com.and3r.octoprint.javadisplay.ui.mainpanels.connected.ControlPanel;
import com.and3r.octoprint.javadisplay.ui.mainpanels.connected.CurrentJobPanel;
import com.and3r.octoprint.javadisplay.ui.mainpanels.connected.FileListPanel;
import com.and3r.octoprint.javadisplay.ui.mainpanels.connected.SystemPanel;
import com.and3r.octoprint.javadisplay.ui.utils.ColorManager;
import com.and3r.octoprint.javadisplay.ui.mainpanels.LoadingPanel;
import com.and3r.octoprint.javadisplay.ui.mainpanels.PrinterNotConnectedPanel;

import javax.swing.*;
import java.awt.*;

public class MainJframe extends JFrame implements OctoprintStatusListener {

    private int lastStatusInt;
    private JComponent currentPanel;
    private OctoprintStatus lastStatus;
    private OctoprintConnectionManager octoprintConnectionManager;

    public MainJframe(OctoprintConnectionManager octoprintConnectionManager, int width, int height){
        this.setSize(width, height);
        this.lastStatusInt = Integer.MIN_VALUE;
        this.octoprintConnectionManager = octoprintConnectionManager;
        setLayout(new BorderLayout());

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
                default:
                    TabbedPanel tabbedPane = new TabbedPanel();
                    newPanel = tabbedPane;

                    tabbedPane.add("Job", new CurrentJobPanel(octoprintConnectionManager));
                    tabbedPane.add("Tools", new TemperaturesPanel(octoprintConnectionManager));
                    tabbedPane.add("Control", new ControlPanel(octoprintConnectionManager));
                    tabbedPane.add("Files", new FileListPanel(octoprintConnectionManager));
                    tabbedPane.add("System", new SystemPanel(octoprintConnectionManager));
                    break;
            }
            currentPanel = newPanel;
            add(newPanel, BorderLayout.CENTER);
            revalidate();
        }
        lastStatus = status;
    }


}
