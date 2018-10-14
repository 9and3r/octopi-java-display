package com.and3r.octopijavadisplay.ui.components;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.OctoprintStatus;
import com.and3r.octopijavadisplay.connection.OctoprintStatusListener;

import javax.swing.*;

public abstract class BasePanel extends JPanel implements OctoprintStatusListener {

    protected OctoprintConnectionManager octoprint;

    public BasePanel(OctoprintConnectionManager octoprintConnectionManager, Object... args){
        octoprint = octoprintConnectionManager;
        init(args);
        octoprint.addStatusListener(this);
    }

    protected abstract void init(Object... args);

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {

    }

}
