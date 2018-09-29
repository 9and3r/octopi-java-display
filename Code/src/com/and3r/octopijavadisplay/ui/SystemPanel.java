package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SystemPanel extends BasePanel {

    private JLabel label;

    public SystemPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init(Object... args) {
        setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 20, 20));

        setLayout(new BorderLayout());

        label = new JLabel("", SwingConstants.CENTER);
        label.setBackground(ColorManager.backgroundColorTabs);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.PLAIN, 43));

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateIP();
            }
        });
        timer.start();

        add(label, BorderLayout.SOUTH);

        updateIP();

    }

    private void updateIP(){
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            label.setText("IP: " + ip);
        } catch (SocketException e1) {
            e1.printStackTrace();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
    }


}
