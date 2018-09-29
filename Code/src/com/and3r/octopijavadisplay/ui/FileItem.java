package com.and3r.octopijavadisplay.ui;

import com.and3r.octopijavadisplay.OctoprintConnectionManager;
import com.and3r.octopijavadisplay.datamodels.OctoprintFile;
import org.ocpsoft.prettytime.PrettyTime;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Date;

public class FileItem extends BasePanel {

    private OctoprintFile file;

    public FileItem(OctoprintConnectionManager octoprintConnectionManager, OctoprintFile file) {
        super(octoprintConnectionManager, file);
    }

    @Override
    protected void init(Object... args) {
        this.file = (OctoprintFile) args[0];
        setLayout(new GridLayout(0, 1));
        setBorder(BorderFactory.createEmptyBorder(20,20, 20, 20));
        setBackground(ColorManager.buttonNormalStateStrokeColor);
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
