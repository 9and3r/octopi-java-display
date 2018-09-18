package com.and3r.octopijavadisplay;

import com.and3r.octopijavadisplay.ui.MainJframe;

public class Main {

    public static void main(String[] args) {
        OctoprintConnectionManager manager = new OctoprintConnectionManager("localhost", 5000, "780CC77D0E364E2E816C903EB3A4ED32");
        //OctoprintConnectionManager manager = new OctoprintConnectionManager("192.168.1.114", 80, "F758ADB326CA4282B3F2944E566396AA");
        MainJframe mainJframe = new MainJframe(manager);
    }

}
