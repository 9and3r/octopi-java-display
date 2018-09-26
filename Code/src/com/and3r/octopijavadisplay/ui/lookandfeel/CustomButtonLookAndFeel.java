package com.and3r.octopijavadisplay.ui.lookandfeel;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
