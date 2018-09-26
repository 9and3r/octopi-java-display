package com.and3r.octopijavadisplay.ui.lookandfeel;


import com.and3r.octopijavadisplay.ui.ColorManager;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class CustomLookAndFeel extends MetalLookAndFeel {

    @Override
    public String getName () {
        return "Custom style for Octoprint Java Display";
    }

    @Override
    public String getID () {
        return "CustomStyleOctoprintJavaDisplay";
    }

    @Override
    public String getDescription () {
        return "Custom style for Octoprint Java Display";
    }

    @Override
    public boolean isNativeLookAndFeel () {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel () {
        return true;
    }

    @Override
    protected void initClassDefaults (UIDefaults table) {
        super.initClassDefaults (table);
        table.put ("ButtonUI", CustomButtonLookAndFeel.class.getCanonicalName());
        table.put ("ProgressBarUI", CustomProgressBar.class.getCanonicalName());
        table.put ("TabbedPaneUI", CustomTabbedPane.class.getCanonicalName());
    }

    @Override
    protected void initComponentDefaults (UIDefaults table) {
        super.initComponentDefaults(table);

        table.put("Button.highlight", ColorManager.buttonNormalStateStrokeColorPressed);
        table.put("Button.opaque", true);
        table.put("Button.border", BorderFactory.createEmptyBorder(7, 17, 7, 17));
        table.put("Button.foreground", Color.WHITE);
        table.put("Button.background", ColorManager.buttonNormalStateStrokeColorPressed);
        table.put("Button.backgroundPressed", ColorManager.buttonNormalStateStrokeColor);

        table.put ("TabbedPane.font", new Font("Arial", Font.PLAIN, 30));
        table.put ("TabbedPane.background", Color.WHITE);
        table.put ("TabbedPane.foreground", Color.BLACK);
        table.put ("TabbedPane.border", BorderFactory.createEmptyBorder ());
        table.put ("TabbedPane.shadow", null);
        table.put ("TabbedPane.darkShadow", null);
        table.put ("TabbedPane.highlight", new Color (238, 238, 238));
        table.put ("TabbedPane.borderHighlightColor", new Color (224, 224, 224));
    }


    }
