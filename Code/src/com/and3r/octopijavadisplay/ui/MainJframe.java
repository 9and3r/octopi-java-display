package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.OctoprintStatus;
import com.and3r.octopijavadisplay.connection.OctoprintStatusListener;
import com.and3r.octopijavadisplay.ui.components.TabbedPanel;
import com.and3r.octopijavadisplay.ui.mainpanels.connected.TemperaturesPanel;
import com.and3r.octopijavadisplay.ui.mainpanels.*;
import com.and3r.octopijavadisplay.ui.mainpanels.connected.ControlPanel;
import com.and3r.octopijavadisplay.ui.mainpanels.connected.CurrentJobPanel;
import com.and3r.octopijavadisplay.ui.mainpanels.connected.FileListPanel;
import com.and3r.octopijavadisplay.ui.mainpanels.connected.SystemPanel;
import com.and3r.octopijavadisplay.ui.utils.ColorManager;

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
