package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.OctoprintStatus;
import com.and3r.octopijavadisplay.datamodels.OctoprintFile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FileListPanel extends BasePanel {

    private ListAdapter<OctoprintFile> listAdapter;

    public FileListPanel(OctoprintConnectionManager octoprintConnectionManager) {
        super(octoprintConnectionManager);
    }


    @Override
    protected void init(Object... args) {
        setLayout(new BorderLayout());

        setOpaque(false);


        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DragScrollListener dragScrollListener = new DragScrollListener(mainPanel);
        JScrollPane jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setOpaque(false);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getViewport().setOpaque(false);
        mainPanel.addMouseListener(dragScrollListener);
        mainPanel.addMouseMotionListener(dragScrollListener);

        add(jScrollPane, BorderLayout.CENTER);

        listAdapter = new ListAdapter<OctoprintFile>(mainPanel, OctoprintFile.class) {
            @Override
            protected JComponent generateView(OctoprintFile item) {
                return new FileItem(octoprint, item);
            }
        };
    }

    @Override
    public void onOctoprintStatusChanged(OctoprintStatus status) {
        super.onOctoprintStatusChanged(status);
        if (status.files != null){
            listAdapter.updateItems(new ArrayList<>(status.files.getAllFilesRecursive()));
        }
    }





}
