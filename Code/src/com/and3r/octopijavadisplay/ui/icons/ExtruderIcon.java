package com.and3r.octopijavadisplay.ui.icons;


import com.and3r.octopijavadisplay.ui.ColorManager;

import javax.swing.*;
import java.awt.*;

public class ExtruderIcon extends TemperatureIcon {



    private static final int[][] xPoints =  {
            {30, 70, 60, 40},
            {0, 100, 100, 0},
            {20, 80, 80, 20}
    };
    private static final int[][] yPoints = {
            {80, 80, 95, 95},
            {65, 65, 77, 77},
            {0, 0, 65, 65}
    };

    private static final int margin = 15;

    @Override
    protected int getMargin() {
        return margin;
    }

    @Override
    protected int[][] getInitialXPoints() {
        return xPoints;
    }

    @Override
    protected int[][] getInitialYPoints() {
        return yPoints;
    }

    @Override
    protected int getStrokeWidth(int i) {
        return 3;
    }

    @Override
    protected boolean isStrokeDependentOnSize() {
        return true;
    }

    @Override
    protected Color getStrokeColor(int i) {
        return Color.BLACK;
    }

    @Override
    protected Color getFillColor(int i) {
        return getTemperatureColor();
    }

}
