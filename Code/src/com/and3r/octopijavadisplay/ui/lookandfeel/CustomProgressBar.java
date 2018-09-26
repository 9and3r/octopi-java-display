package com.and3r.octopijavadisplay.ui.lookandfeel;

import com.and3r.octopijavadisplay.ui.ColorManager;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class CustomProgressBar extends BasicProgressBarUI {

    public static ComponentUI createUI (JComponent c) {
        return new CustomProgressBar ();
    }

    @Override
    public void installUI (JComponent c) {
        super.installUI (c);
        JProgressBar progressBar = (JProgressBar) c;
        progressBar.setBorder(BorderFactory.createLineBorder (new Color (238, 238, 238), 1));
        progressBar.setBackground(ColorManager.backgroundColorTabs);
        progressBar.setForeground(ColorManager.buttonNormalStateStrokeColor);
    }

}
