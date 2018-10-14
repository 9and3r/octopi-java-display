package com.and3r.octoprint.javadisplay.connection;

import com.and3r.octoprint.javadisplay.datamodels.OctoprintStatus;

public interface OctoprintStatusListener {

    void onOctoprintStatusChanged(OctoprintStatus octoprintStatus);
}
