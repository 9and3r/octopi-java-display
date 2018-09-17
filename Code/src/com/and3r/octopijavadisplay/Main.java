package com.and3r.octopijavadisplay;

import com.and3r.octopijavadisplay.ui.MainJframe;

public class Main {

    public static void main(String[] args) {
        OctoprintConnectionManager manager = new OctoprintConnectionManager("localhost", 5000, "780CC77D0E364E2E816C903EB3A4ED32");
        MainJframe mainJframe = new MainJframe(manager);
    }

}
