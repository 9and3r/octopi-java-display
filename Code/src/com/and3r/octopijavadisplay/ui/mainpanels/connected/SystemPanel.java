package com.and3r.octopijavadisplay.ui.mainpanels.connected;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.OctoprintStatus;
import com.and3r.octopijavadisplay.datamodels.actions.OctoprintAction;
import com.and3r.octopijavadisplay.ui.components.BasePanel;
import com.and3r.octopijavadisplay.ui.utils.ColorManager;
import com.and3r.octopijavadisplay.ui.components.ListAdapter;
import com.and3r.octopijavadisplay.ui.utils.TouchScroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SystemPanel extends BasePanel {

    private ListAdapter<OctoprintAction> listAdapter;
    private JLabel label;
    private TouchScroll scroller;

    public SystemPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }

    @Override
    protected void init(Object... args) {
        setOpaque(false);


        setLayout(new BorderLayout());

        label = new JLabel("", SwingConstants.CENTER);
        label.setBackground(ColorManager.backgroundColor2);
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



        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        scroller = TouchScroll.generateSroll(mainPanel);

        add(scroller, BorderLayout.CENTER);


        listAdapter = new ListAdapter<OctoprintAction>(mainPanel, OctoprintAction.class) {
            @Override
            protected JComponent generateView(OctoprintAction item) {
                JButton jButton = new JButton(item.name);
                jButton.addMouseMotionListener(scroller);
                jButton.addMouseListener(scroller);
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!scroller.wasDragged()){
                            String message = item.confirm;
                            if (!message.startsWith("<html>")){

                                message = "<html><body style=\"width: " + SwingUtilities.getWindowAncestor(SystemPanel.this).getWidth()/1.3 + ";\">" + message + "</body></html>";
                            }
                            JOptionPane.showOptionDialog((Frame) SwingUtilities.getWindowAncestor(SystemPanel.this), message, item.name, JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, new String[]{"Cancel", item.name}, "Cancel");
                        }

                    }
                });
                return jButton;
            }
        };
        updateIP();
    }


    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        super.onOctoprintStatusChanged(status);
        if (status.actions != null && status.actions.core != null){
            listAdapter.updateItems(status.actions.core);
        }
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
