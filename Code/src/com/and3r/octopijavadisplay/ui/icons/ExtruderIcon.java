package com.and3r.octopijavadisplay.ui.icons;


import javax.swing.*;
import java.awt.*;

public class ExtruderIcon extends BaseIcon {

    private double temp;
    private int tempInt;

    private static final int[][] xPoints =  {{50, 70, 70, 120, 120, 70, 70, 90, 60, 40, 30, 40, 40,0, 0, 50}};
    private static final int[][] yPoints = {{0, 0, 30, 30, 60, 60, 65, 65, 80, 80, 65, 65, 60, 60, 30, 30}};

    private static final int margin = 20;


    private int[] targetColor;
    private int[] currentColor;
    private boolean colorChanged;

    @Override
    protected void init(Object... params) {
        setOpaque(false);
        this.temp = 0;
        this.tempInt = 0;
        this.targetColor = new int[3];
        this.currentColor = new int[3];
        setTemp(0);
    }

    public void setTemp(double temp) {
        this.temp = temp;
        this.tempInt = (int) temp;

        targetColor[0] = normalizeColorValue(tempInt * 4);
        targetColor[1] = normalizeColorValue(100 - tempInt);
        targetColor[2] = normalizeColorValue(100 - tempInt);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (colorChanged){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            repaint();
                        }
                    });
                }
            });
            thread.start();
        }
    }

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
        return 10;
    }

    @Override
    protected Color getStrokeColor(int i) {
        return Color.BLACK;
    }

    @Override
    protected Color getFillColor(int pos) {
        colorChanged = false;
        for (int i=0; i<3; i++){
            if (currentColor[i] < targetColor[i]){
                currentColor[i]++;
                colorChanged = true;
            }else if (currentColor[i] > targetColor[i]){
                currentColor[i]--;
                colorChanged = true;
            }
        }
        return new Color(currentColor[0], currentColor[1], currentColor[2]);
    }


    private static int normalizeColorValue(int value){
        if (value > 255){
            return 255;
        }else if (value < 0){
            return 0;
        }else{
            return value;
        }
    }


}
