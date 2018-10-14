package com.and3r.octoprint.javadisplay.ui.mainpanels;

import com.and3r.octoprint.javadisplay.connection.OctoprintConnectionManager;
import com.and3r.octoprint.javadisplay.ui.components.BasePanel;
import com.and3r.octoprint.javadisplay.ui.components.IndefiniteLoadingIndicator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoadingPanel extends BasePanel {

    private JLabel label;

    public LoadingPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    public LoadingPanel(OctoprintConnectionManager octoprintConnectionManager, String message) {
        super(octoprintConnectionManager, message);
    }

    public LoadingPanel(OctoprintConnectionManager octoprintConnectionManager, String message, int timeoutMS, String timeoutMessage) {
        super(octoprintConnectionManager, message, timeoutMS, timeoutMessage);
    }

    @Override
    protected void init(Object... args) {
        setOpaque(false);
        setLayout(new BorderLayout());

        add(new IndefiniteLoadingIndicator(), BorderLayout.CENTER);

        if (args.length >= 1){
            label = new JLabel(args[0].toString(), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 22));
            label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            add(label, BorderLayout.SOUTH);

            if (args.length == 3){
                Timer timer = new Timer((int) args[1], new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label.setText((String) args[2]);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }


        }

    }
}
