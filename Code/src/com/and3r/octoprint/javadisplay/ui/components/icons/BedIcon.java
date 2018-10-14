package com.and3r.octoprint.javadisplay.ui.components.icons;

import com.and3r.octoprint.javadisplay.ui.utils.ColorManager;

import java.awt.*;

public class BedIcon extends TemperatureIcon {


    private static final int num_of_lines = 3;
    private static final int line_separation = 20;

    private int[][] initialXPoints;
    private int[][] initialYPoints;

    private static final int[] base_x_line_points = new int[] {5, 20, 5, 20, 5, 20, 5, 20};

    @Override
    protected void init(Object... params) {
        super.init(params);
        initialXPoints = new int[num_of_lines+1][];
        initialYPoints = new int[num_of_lines+1][];

        initialXPoints[0] = new int[]{0, 5, 95, 100, 100, 95, 5, 0};
        initialYPoints[0] = new int[]{90, 85, 85, 90, 95, 100, 100, 95};

        for (int i=1; i<=num_of_lines; i++){
            initialYPoints[i] = new int[]{0, 20, 40, 60, 78, 60, 40, 20};
            int[] xCurrentPoints = new int[base_x_line_points.length];
            for (int z=0; z<base_x_line_points.length; z++){
                xCurrentPoints[z] = base_x_line_points[z] + line_separation*i;
            }
            initialXPoints[i] = xCurrentPoints;
        }
    }

    @Override
    protected int getMargin() {
        return 10;
    }

    @Override
    protected int[][] getInitialXPoints() {
        return initialXPoints;
    }

    @Override
    protected int[][] getInitialYPoints() {
        return initialYPoints;
    }

    @Override
    protected Color getStrokeColor(int i) {
        if (i == 0){
            return ColorManager.temperatureStrokeColor;
        }else{
            Color color = getTemperatureColor();
            int alpha = normalizeColorValue((getProgress()-30) * 255 / 45);
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
        }

    }

    @Override
    protected Color getFillColor(int i) {
        if (i == 0){
            return getTemperatureColor();
        }else{
            return null;
        }
    }
}
