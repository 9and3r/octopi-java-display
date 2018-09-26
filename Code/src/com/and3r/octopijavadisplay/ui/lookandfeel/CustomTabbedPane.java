package com.and3r.octopijavadisplay.ui.lookandfeel;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class CustomTabbedPane extends BasicTabbedPaneUI {

    public static ComponentUI createUI (JComponent c) {
        return new CustomTabbedPane ();
    }

    @Override
    public void installUI (JComponent c) {
        super.installUI (c);

        JTabbedPane tabbedPane = (JTabbedPane) c;
        tabbedPane.setOpaque (false);
        tabbedPane.setFont (UIManager.getFont ("TabbedPane.font"));
        tabbedPane.setBackground (UIManager.getColor ("TabbedPane.background"));
        tabbedPane.setForeground (UIManager.getColor ("TabbedPane.foreground"));
        tabbedPane.setBorder (UIManager.getBorder ("TabbedPane.border"));

        darkShadow = UIManager.getColor ("TabbedPane.darkShadow");
        shadow = UIManager.getColor ("TabbedPane.shadow");
        lightHighlight = UIManager.getColor ("TabbedPane.highlight");
    }


    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return (tabPane.getWidth() / tabPane.getTabCount()) - 5;
    }

    @Override
    protected void paintTabBackground (Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        g.setColor (isSelected ? lightHighlight : tabPane.getBackground ());
        g.fillRect (x, y, w, h);
    }

    @Override
    protected void paintTabBorder (Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        g.setColor (UIManager.getColor ("TabbedPane.borderHighlightColor"));
        g.drawRect (x, y, w, h);
    }

    @Override
    protected void paintFocusIndicator (Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        // do nothing
    }

}
