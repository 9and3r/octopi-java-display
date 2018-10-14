package com.and3r.octoprint.javadisplay.ui.mainpanels.connected;

import com.and3r.octoprint.javadisplay.connection.OctoprintConnectionManager;
import com.and3r.octoprint.javadisplay.datamodels.OctoprintStatus;
import com.and3r.octoprint.javadisplay.datamodels.files.OctoprintFile;
import com.and3r.octoprint.javadisplay.ui.components.BasePanel;
import com.and3r.octoprint.javadisplay.ui.components.FileItem;
import com.and3r.octoprint.javadisplay.ui.components.ListAdapter;
import com.and3r.octoprint.javadisplay.ui.utils.TouchScroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FileListPanel extends BasePanel {

    private ListAdapter<OctoprintFile> listAdapter;
    private TouchScroll scroller;

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

        scroller = TouchScroll.generateSroll(mainPanel);
        add(scroller, BorderLayout.CENTER);

        listAdapter = new ListAdapter<OctoprintFile>(mainPanel, OctoprintFile.class) {
            @Override
            protected JComponent generateView(OctoprintFile item) {
                FileItem fileItem = new FileItem(item);
                fileItem.addMouseListener(scroller);
                fileItem.addMouseMotionListener(scroller);
                fileItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!scroller.wasDragged()){
                            octoprint.printFile(item);
                        }
                    }
                });
                return fileItem;
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
