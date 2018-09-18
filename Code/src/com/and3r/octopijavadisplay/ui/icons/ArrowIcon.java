package com.and3r.octopijavadisplay.ui.icons;

import java.awt.*;

public class ArrowIcon extends ClickableIcon {


    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    private int[][] xPoints;
    private int[][] yPoints;

    private int direction;

    public ArrowIcon(int direction){
        super(direction);
    }

    @Override
    protected void init(Object... params) {
        direction = (int) params[0];
        int[] defaultX = {0, 20, 20, 40, 20, 20, 0};
        int[] defaultY = {10, 10, 0, 15, 30, 20, 20};
        xPoints = new int[1][];
        yPoints = new int[1][];

        setBackground(Color.RED);


        // Change axes if necesary
        if (direction == DOWN){
            int[] aux = defaultX;
            defaultX = defaultY;
            defaultY = aux;
        }

        if (direction == LEFT){
            for (int i=0; i<defaultX.length; i++){
                defaultX[i] = 40 - defaultX[i];
            }
        }


        xPoints[0] = defaultX;
        yPoints[0] = defaultY;
    }

    @Override
    protected int getMargin() {
        return 20;
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
        return 10;
    }

    @Override
    protected Color getStrokeColor(int i) {
        return Color.BLACK;
    }

    @Override
    protected Color getFillColor(int i) {
        if (pressed){
            return Color.BLUE;
        }else if (hover){
            return Color.GREEN;
        }else{
            return null;
        }

    }


}
