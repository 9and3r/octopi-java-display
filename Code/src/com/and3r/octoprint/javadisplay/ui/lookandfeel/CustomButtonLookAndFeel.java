package com.and3r.octoprint.javadisplay.ui.lookandfeel;

import com.and3r.octoprint.javadisplay.ui.utils.DimensionManager;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

public class CustomButtonLookAndFeel extends BasicButtonUI {

    public static ComponentUI createUI (final JComponent c) {
        return new CustomButtonLookAndFeel ();
    }

    @Override
    public void installUI (JComponent c) {
        super.installUI (c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(UIManager.getBoolean("Button.opaque"));
        button.setForeground(UIManager.getColor("Button.foreground"));
        button.setBorder(UIManager.getBorder("Button.border"));
        button.setBackground(UIManager.getColor("Button.background"));
        button.setForeground(UIManager.getColor("Button.foreground"));
        c.setFont(DimensionManager.defaultFont);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground (UIManager.getColor ("Button.backgroundPressed"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground (UIManager.getColor ("Button.background"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground (UIManager.getColor ("Button.background"));
            }
        });
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

    @Override
    public void paint (Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        if (b.isContentAreaFilled ()) {
            paintBackground (g, b);
        }
        super.paint (g, c);
    }

    private void paintBackground (Graphics g, JComponent c) {
        g.setColor (c.getBackground ());
        g.fillRoundRect (0, 0, c.getWidth (), c.getHeight (), 7, 7);
    }
}
