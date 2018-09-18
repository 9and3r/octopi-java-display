package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;

import javax.swing.*;
import java.awt.*;

public class OctoprintNotConnectedPanel extends BasePanel{


    public OctoprintNotConnectedPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Could not connect with octoprint. Check configuration", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
