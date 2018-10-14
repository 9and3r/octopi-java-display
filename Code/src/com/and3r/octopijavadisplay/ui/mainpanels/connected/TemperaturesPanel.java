package com.and3r.octopijavadisplay.ui.mainpanels.connected;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.OctoprintStatus;
import com.and3r.octopijavadisplay.ui.components.BasePanel;
import com.and3r.octopijavadisplay.ui.components.TemperatureDisplayPanel;

import java.awt.*;

public class TemperaturesPanel extends BasePanel {

    private TemperatureDisplayPanel extruder0;
    private TemperatureDisplayPanel extruder1;
    private TemperatureDisplayPanel bed;

    public TemperaturesPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }


    @Override
    protected void init(Object... args) {
        setOpaque(false);
        setLayout(new GridLayout(1,0));
        extruder0 = new TemperatureDisplayPanel(octoprint, true);
        //extruder1 = new TemperatureDisplayPanel(true);
        bed = new TemperatureDisplayPanel(octoprint,false);
        add(bed);
        add(extruder0);
        //add(extruder1);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        super.onOctoprintStatusChanged(status);
        if (status.isPrinterConnected()){
            bed.setTemp(status.printerStatus.temperature.bed);
            extruder0.setTemp(status.printerStatus.temperature.tool0);
            //extruder1.setTemp(status.printerStatus.temperature.tool1);
        }

    }
}
