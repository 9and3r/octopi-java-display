package com.and3r.octopijavadisplay.ui.mainpanels;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.OctoprintStatus;
import com.and3r.octopijavadisplay.ui.components.BasePanel;

import javax.swing.*;
import java.awt.*;

public class PrinterNotConnectedPanel extends BasePanel {

    public PrinterNotConnectedPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init(Object... args) {
        setLayout(new BorderLayout());
        setOpaque(false);
        add(new JLabel("Printer not connected", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
