package com.and3r.octoprint.javadisplay.ui.mainpanels;

import com.and3r.octoprint.javadisplay.connection.OctoprintConnectionManager;
import com.and3r.octoprint.javadisplay.ui.components.BasePanel;

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
