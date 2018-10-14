package com.and3r.octopijavadisplay.ui.mainpanels.connected;

import com.and3r.octopijavadisplay.connection.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.OctoprintStatus;
import com.and3r.octopijavadisplay.datamodels.job.JobStatus;
import com.and3r.octopijavadisplay.ui.components.BasePanel;
import com.and3r.octopijavadisplay.ui.components.icons.OperationIcon;
import com.and3r.octopijavadisplay.ui.utils.ColorManager;
import com.and3r.octopijavadisplay.ui.utils.DimensionManager;
import com.and3r.octopijavadisplay.ui.utils.SpringUtilities;
import com.and3r.octopijavadisplay.ui.utils.TouchScroll;
import org.ocpsoft.prettytime.PrettyTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Date;

public class CurrentJobPanel extends BasePanel {

    private JProgressBar jProgressBar;
    private JPanel mainPanel;

    private ArrayList<JLabel> labels;

    private PrettyTime prettyTime;


    public CurrentJobPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }


    @Override
    protected void init(Object... args) {
        setOpaque(false);
        setLayout(new BorderLayout());
        prettyTime = new PrettyTime();
        labels = new ArrayList<>();
        jProgressBar = new JProgressBar();
        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(100);
        jProgressBar.setStringPainted(true);

        JPanel panelOperation = new JPanel();
        //panelOperation.setPreferredSize(new Dimension(0, 100));
        panelOperation.setOpaque(false);
        panelOperation.setLayout(new GridLayout(1, 0));
        panelOperation.add(new OperationIcon(OperationIcon.PLAY_ICON));
        panelOperation.add(new OperationIcon(OperationIcon.PAUSE_ICON));
        panelOperation.add(new OperationIcon(OperationIcon.STOP_ICON));

        panelOperation.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                panelOperation.setPreferredSize(new Dimension(0, DimensionManager.windowHeight / 8));
                //revalidate();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });



        JPanel topPanel = new JPanel();
        topPanel.setBackground(ColorManager.backgroundColor2);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(jProgressBar, BorderLayout.SOUTH);
        topPanel.add(panelOperation, BorderLayout.NORTH);
        add(topPanel, BorderLayout.SOUTH);


        mainPanel = new JPanel();
        mainPanel.setLayout(new SpringLayout());
        mainPanel.setOpaque(false);

        addRow("Status:");
        addRow("File:");
        addRow("Total print time:");
        addRow("Print time:");
        addRow("Print time left:");

        add(TouchScroll.generateSroll(mainPanel), BorderLayout.CENTER);

        SpringUtilities.makeCompactGrid(mainPanel,
                labels.size(), 2, //rows, cols
                20, 20,        //initX, initY
                15, 5);       //xPad, yPad
    }

    private void addRow(String label){
        mainPanel.add(new JLabel(label));
        JLabel currentLabel = new JLabel("-");
        mainPanel.add(currentLabel);
        labels.add(currentLabel);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        super.onOctoprintStatusChanged(status);

        if (status.isPrinterConnected()){
            jProgressBar.setValue((int)status.jobStatus.progress.completion);
            labels.get(0).setText(status.jobStatus.state);
            labels.get(1).setText(status.jobStatus.job.file.name);
            labels.get(2).setText(prettyTime.formatDuration(new Date((long) (System.currentTimeMillis() + status.jobStatus.job.estimatedPrintTime * 1000))));
            labels.get(3).setText(prettyTime.formatDuration(new Date((long) (System.currentTimeMillis() + status.jobStatus.progress.printTime * 1000))));
            if (status.jobStatus.progress.printTimeLeft != null){
                labels.get(4).setText(prettyTime.formatDuration(new Date((long) (System.currentTimeMillis() + status.jobStatus.progress.printTimeLeft * 1000))));
            }else{
                labels.get(4).setText("-");
            }

        }else{
            // TODO
        }


    }
}
