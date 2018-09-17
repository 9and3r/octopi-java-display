package com.and3r.octopijavadisplay.ui.icons;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;
import com.and3r.octopijavadisplay.ui.BasePanel;

import javax.swing.*;
import java.awt.*;

public class TemperaturesPanel extends BasePanel {

    private TemperatureIcon extruder0;
    private TemperatureIcon extruder1;
    private TemperatureIcon bed;

    public TemperaturesPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }


    @Override
    protected void init() {
        setOpaque(false);
        setLayout(new GridLayout(1,0));
        extruder0 = new TemperatureIcon();
        extruder1 = new TemperatureIcon();
        bed = new TemperatureIcon();
        add(bed);
        add(extruder0);
        add(extruder1);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        super.onOctoprintStatusChanged(status);
        bed.setTemp(status.printerStatus.temperature.bed);
        extruder0.setTemp(status.printerStatus.temperature.tool0);
        //extruder1.setTemp(status.printerStatus.temperature.tool1);
    }
}
