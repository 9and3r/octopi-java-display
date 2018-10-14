package com.and3r.octoprint.javadisplay.ui.lookandfeel;

import com.and3r.octoprint.javadisplay.ui.utils.DimensionManager;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CustomJLabelLookAndFeel extends BasicLabelUI {

    public static ComponentUI createUI (final JComponent c) {
        return new CustomJLabelLookAndFeel();
    }

    private int size;

    @Override
    public void installUI (JComponent c) {
        super.installUI (c);
        c.setForeground(UIManager.getColor("Label.foreground"));
        c.setFont(DimensionManager.defaultFont);
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
