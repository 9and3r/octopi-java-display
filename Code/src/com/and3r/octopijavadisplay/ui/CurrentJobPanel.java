package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;
import com.and3r.octopijavadisplay.ui.icons.OperationIcon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CurrentJobPanel extends BasePanel{

    private JProgressBar jProgressBar;
    private JPanel mainPanel;

    private ArrayList<JLabel> labels;


    public CurrentJobPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }


    @Override
    protected void init(Object... args) {
        setOpaque(false);
        setLayout(new BorderLayout());
        labels = new ArrayList<>();
        jProgressBar = new JProgressBar();
        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(100);
        jProgressBar.setStringPainted(true);
        jProgressBar.setFont(new Font("Arial", Font.PLAIN, 23));

        JPanel panelOperation = new JPanel();
        panelOperation.setPreferredSize(new Dimension(0, 100));
        panelOperation.setOpaque(false);
        panelOperation.setLayout(new GridLayout(1, 0));
        panelOperation.add(new OperationIcon(OperationIcon.PLAY_ICON));
        panelOperation.add(new OperationIcon(OperationIcon.PAUSE_ICON));
        panelOperation.add(new OperationIcon(OperationIcon.STOP_ICON));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(ColorManager.backgroundColorTabs);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(jProgressBar, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottomPanel.add(panelOperation, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new SpringLayout());
        mainPanel.setOpaque(false);

        DragScrollListener dragScrollListener = new DragScrollListener(mainPanel);
        JScrollPane jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getViewport().setOpaque(false);
        mainPanel.addMouseListener(dragScrollListener);
        mainPanel.addMouseMotionListener(dragScrollListener);


        addRow("Status:");
        addRow("File:");
        addRow("Filament:");
        addRow("Status:");
        addRow("Print time:");
        addRow("Print time left:");
        addRow("Printed:");

        add(jScrollPane, BorderLayout.CENTER);

        SpringUtilities.makeCompactGrid(mainPanel,
                labels.size(), 2, //rows, cols
                20, 20,        //initX, initY
                15, 5);       //xPad, yPad
    }

    private void addRow(String label){
        mainPanel.add(new LabelCurrentJob(label));
        JLabel currentLabel = new LabelCurrentJob("-");
        mainPanel.add(currentLabel);
        labels.add(currentLabel);
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        super.onOctoprintStatusChanged(status);

        if (status.isPrinterConnected()){
            //jProgressBar.setValue((int)status.jobStatus.progress.completion);
            jProgressBar.setValue(60);

            labels.get(0).setText(status.jobStatus.state);
            labels.get(1).setText(status.jobStatus.job.file.name);
            //labels.get(2).setText(String.valueOf(status.jobStatus.progress.completion));
        }


    }
}
