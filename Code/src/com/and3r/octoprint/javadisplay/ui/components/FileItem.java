package com.and3r.octoprint.javadisplay.ui.components;

import com.and3r.octoprint.javadisplay.datamodels.files.OctoprintFile;
import com.and3r.octoprint.javadisplay.ui.utils.ColorManager;
import org.ocpsoft.prettytime.PrettyTime;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Date;

public class FileItem extends JButton {

    private OctoprintFile file;

    public FileItem(OctoprintFile file) {
        super();
        this.file = file;
        setLayout(new GridLayout(0, 1));
        setBorder(BorderFactory.createEmptyBorder(20,20, 20, 20));
        setBackground(ColorManager.primaryColor);
        add(new JLabel(file.name));

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(1);

        String sizeString;
        if (file.size < 1024){
            sizeString = file.size + " bytes";
        }else if (file.size < 1048576){
            sizeString = decimalFormat.format(file.size / 1024.0) + " KB";
        }else{
            sizeString = decimalFormat.format(file.size / 1048576.0) + " MB";
        }

        add(new JLabel(sizeString));

        PrettyTime p = new PrettyTime();
        add(new JLabel("Uploaded " + p.format(new Date(file.date * 1000))));
    }

    public OctoprintFile getFile() {
        return file;
    }
}
