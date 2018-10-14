package com.and3r.octopijavadisplay;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.ui.MainJframe;
import com.and3r.octopijavadisplay.ui.lookandfeel.CustomLookAndFeel;
import com.and3r.octopijavadisplay.ui.utils.ColorManager;
import com.and3r.octopijavadisplay.ui.utils.DimensionManager;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

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

    @Parameter(names = "-updateTime", description = "Update time in milliseconds")
    public int updateTime = 2000;

    @Parameter(names = "-width", description = "Width of the screen")
    public int width = -1;

    @Parameter(names = "-height", description = "Height of the screen")
    public int height = -1;

    // UI customization
    @Parameter(names = "-backgroundColor", description = "Color to be used as backgrounds in hex format. Example #222222")
    public String backgroundColor = "#0A0A0A";

    @Parameter(names = "-backgroundColor2", description = "Color to be used as secondary backgrounds in hex format. Example #222222")
    public String backgroundColor2 = "#0A0A0A";

    @Parameter(names = "-textColor", description = "Color to be used as secondary backgrounds in hex format. Example #222222")
    public String textColor = "#FAFAFA";

    @Parameter(names = "-primaryColor", description = "Color to be used as secondary backgrounds in hex format. Example #222222")
    public String primaryColor = "#B21000";

    @Parameter(names = "-accentColor", description = "Color to be used as secondary backgrounds in hex format. Example #222222")
    public String accentColor = "#E63946";

    @Parameter(names = "-temperatureStrokeColor", description = "Color to be used as secondary backgrounds in hex format. Example #222222")
    public String temperatureStrokeColor = "#0A0A0A";



    // https://raspberrypi.stackexchange.com/questions/43847/startx-command-not-found
    // sudo apt-get install oracle-java8-jdk
    // sudo apt-get install xserver-xorg-legacy
    // https://www.raspberrypi.org/forums/viewtopic.php?t=5851

    public static void main(String[] args) {

        Main main = new Main();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(main)
                .build();

        jCommander.parse(args);

        if (main.help){
            jCommander.usage();
            System.exit(0);
        }

        // Check apiKey
        if (main.apiKey == null){
            System.out.println("apiKey not provided");
            if (main.host.equals("localhost") || main.host.equals("127.0.0.1")){

                // Find config file to get the key automatically
                String homeDir = System.getProperty("user.home");
                File file = new File(homeDir, "/.octoprint/config.yaml");
                System.out.println("Searching octoprint config file to get apiKey: " + file.getAbsolutePath());
                if (file.exists()){
                    System.out.println("File found: " + file.getAbsolutePath());
                    boolean found = false;
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while (!found && (line = br.readLine()) != null) {
                            if (line.trim().startsWith("key:")){
                                main.apiKey = line.substring(line.indexOf("key:") + "key:".length());
                                System.out.println("apiKey found: " + main.apiKey);
                                found = true;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!found){
                        System.out.println("Could not find key in config file");
                    }
                }else{
                    System.out.println("Config file not found. Could not get apiKey automatically");
                }
            }else{
                System.exit(-1);
            }
        }

        // Set screen size
        if (main.width < 1 || main.height < 1){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            if (main.width < 1){
                main.width = screenSize.width;
            }
            if (main.height < 1){
                main.height = screenSize.height;
            }
        }
        DimensionManager.get().init(null, main.width, main.height);


        // Set ui customization
        ColorManager.backgroundColor = Color.decode(main.backgroundColor);
        ColorManager.backgroundColor2 = Color.decode(main.backgroundColor2);
        ColorManager.textColor = Color.decode(main.textColor);
        ColorManager.primaryColor = Color.decode(main.primaryColor);
        ColorManager.accentColor = Color.decode(main.accentColor);
        ColorManager.temperatureStrokeColor = Color.decode(main.temperatureStrokeColor);

        // Set look and feel
        try {
            UIManager.setLookAndFeel (new CustomLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }

        main.start();
    }

    private void start(){
        //OctoprintConnectionManager manager = new OctoprintConnectionManager("localhost", 5000, "780CC77D0E364E2E816C903EB3A4ED32");
        //OctoprintConnectionManager manager = new OctoprintConnectionManager("192.168.1.126", 80, "F758ADB326CA4282B3F2944E566396AA");
        OctoprintConnectionManager manager = new OctoprintConnectionManager(host, port, apiKey, updateTime);
        MainJframe mainJframe = new MainJframe(manager, width, height);

        //mainJframe.setSize(800, 480);
        //mainJframe.setSize(300, 120);
        DimensionManager.get().listenTo(mainJframe);
        mainJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (!cursor){
            mainJframe.setCursor(mainJframe.getToolkit().createCustomCursor(
                    new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
                    "null"));
        }
    }

}
