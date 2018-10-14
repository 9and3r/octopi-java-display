package com.and3r.octopijavadisplay.connection;

import com.and3r.octopijavadisplay.datamodels.OctoprintStatus;

public interface OctoprintStatusListener {

    void onOctoprintStatusChanged(OctoprintStatus octoprintStatus);
}
