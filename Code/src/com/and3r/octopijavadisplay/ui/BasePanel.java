package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;
import com.and3r.octopijavadisplay.OctoprintStatusListener;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class BasePanel extends JPanel implements OctoprintStatusListener {

    protected OctoprintConnectionManager octoprint;

    public BasePanel(OctoprintConnectionManager octoprintConnectionManager){
        octoprint = octoprintConnectionManager;
        init();
        octoprint.addStatusListener(this);
    }

    protected abstract void init();

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {

    }

}
