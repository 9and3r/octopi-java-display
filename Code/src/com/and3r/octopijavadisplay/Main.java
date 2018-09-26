package com.and3r.octopijavadisplay;

import com.and3r.octopijavadisplay.ui.MainJframe;
import com.and3r.octopijavadisplay.ui.lookandfeel.CustomLookAndFeel;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {


    @Parameter(names = "-host", description = "IP or hostname where Octoprint is listening")
    public String host = "localhost";

    @Parameter(names = "-port", description = "Port where Octoprint is listening")
    public int port = 80;

    @Parameter(names = "-key", description = "Octoprint API key")
    public String apiKey = null;

    @Parameter(names = "-help", description = "Use to show help")
    public boolean help = false;

    @Parameter(names = "-cursor", description = "Show cursor", arity=1)
    public boolean cursor = false;


    // https://raspberrypi.stackexchange.com/questions/43847/startx-command-not-found
    // sudo apt-get install oracle-java8-jdk
    // sudo apt-get install xserver-xorg-legacy
    // https://www.raspberrypi.org/forums/viewtopic.php?t=5851

    public static void main(String[] args) {


        // Set look and feel
        try {
            UIManager.setLookAndFeel (new CustomLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }

        Main main = new Main();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(main)
                .build();

        jCommander.parse(args);

        if (main.help){
            jCommander.usage();
        }else{
            main.start();
        }

    }

    private void start(){
        //OctoprintConnectionManager manager = new OctoprintConnectionManager("localhost", 5000, "780CC77D0E364E2E816C903EB3A4ED32");
        //OctoprintConnectionManager manager = new OctoprintConnectionManager("192.168.1.114", 80, "F758ADB326CA4282B3F2944E566396AA");
        OctoprintConnectionManager manager = new OctoprintConnectionManager(host, port, apiKey);
        MainJframe mainJframe = new MainJframe(manager);
        mainJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (!cursor){
            mainJframe.setCursor(mainJframe.getToolkit().createCustomCursor(
                    new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
                    "null"));
        }
    }

}
