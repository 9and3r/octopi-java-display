package com.and3r.octopijavadisplay.ui.components.icons;


import com.and3r.octopijavadisplay.ui.utils.ColorManager;

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
    protected Color getStrokeColor(int i) {
        return ColorManager.temperatureStrokeColor;
    }

    @Override
    protected Color getFillColor(int i) {
        return getTemperatureColor();
    }

}
