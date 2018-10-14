package com.and3r.octopijavadisplay.ui.lookandfeel;


import com.and3r.octopijavadisplay.ui.utils.ColorManager;

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
        table.put ("LabelUI", CustomJLabelLookAndFeel.class.getCanonicalName());
    }

    @Override
    protected void initComponentDefaults (UIDefaults table) {
        super.initComponentDefaults(table);

        table.put("Button.highlight", ColorManager.accentColor);
        table.put("Button.opaque", true);
        table.put("Button.border", BorderFactory.createEmptyBorder(7, 17, 7, 17));
        table.put("Button.foreground", ColorManager.textColor);
        table.put("Button.background", ColorManager.primaryColor);
        table.put("Button.backgroundPressed", ColorManager.accentColor);

        UIManager.put("ProgressBar.selectionBackground", ColorManager.textColor);//Red

        table.put("Label.foreground", ColorManager.textColor);

    }


    }
