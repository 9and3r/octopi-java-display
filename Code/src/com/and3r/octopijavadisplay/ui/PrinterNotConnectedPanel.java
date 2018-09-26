package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;

import javax.swing.*;
import java.awt.*;

public class PrinterNotConnectedPanel extends BasePanel {

    private JComboBox<String> ports;
    private boolean reload;

    public PrinterNotConnectedPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init(Object... args) {
        setLayout(new BorderLayout());
        reload = true;
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        ports = new JComboBox<>();

        mainPanel.add(ports);
        add(mainPanel, BorderLayout.NORTH);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        super.onOctoprintStatusChanged(status);

        if (reload){
            DefaultComboBoxModel model = new DefaultComboBoxModel( status.connection.options.ports );
            ports.setModel(model);
            reload = false;
        }

    }
}
