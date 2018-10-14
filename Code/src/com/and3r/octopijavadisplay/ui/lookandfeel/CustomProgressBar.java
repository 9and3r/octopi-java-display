package com.and3r.octopijavadisplay.ui.lookandfeel;

import com.and3r.octopijavadisplay.ui.utils.ColorManager;
import com.and3r.octopijavadisplay.ui.utils.DimensionManager;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CustomProgressBar extends BasicProgressBarUI {

    public static ComponentUI createUI (JComponent c) {
        return new CustomProgressBar ();
    }

    @Override
    public void installUI (JComponent c) {
        super.installUI (c);
        JProgressBar progressBar = (JProgressBar) c;
        progressBar.setBorder(BorderFactory.createLineBorder (new Color (238, 238, 238), 1));
        progressBar.setBackground(ColorManager.backgroundColor2);
        progressBar.setForeground(ColorManager.primaryColor);
        c.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                c.setFont(DimensionManager.defaultFont);
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
    }

}
