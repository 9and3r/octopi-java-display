package com.and3r.octopijavadisplay.ui.icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class TemperatureIcon extends BaseIcon{

    private int minTemp;
    private int maxTemp;


    private int tempInt;

    private int[] targetColor;
    private int[] currentColor;
    private boolean colorChanged;
    private Timer timer;


    @Override
    protected void init(Object... params) {
        minTemp = 0;
        maxTemp = 100;

        this.tempInt = Integer.MIN_VALUE;
        setOpaque(false);

        this.targetColor = new int[3];
        this.currentColor = new int[3];
        setTemp(0);
    }

    public void setTemp(double temp) {
        int newTemp = (int) temp;
        if (newTemp != this.tempInt){

            this.tempInt = newTemp;

            int progress = getProgress();

            targetColor[0] = normalizeColorValue((progress * 255 / 100) - 30);
            if (targetColor[0] < 55){
                targetColor[0] = 55;
            }
            targetColor[1] = 55;
            targetColor[2] = normalizeColorValue(255 - (progress*255/100));
            if (targetColor[2] < 55){
                targetColor[2] = 55;
            }

            if (timer != null){
                timer.stop();
                timer = null;
            }

            timer = new Timer(10, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
        }
    }

    protected int getProgress(){
        int progress = 0;
        if (tempInt > 0){
            progress =  tempInt * 100 / (maxTemp - minTemp);
        }
        if (progress > 100){
            progress = 100;
        }else if (progress < 0){
            progress = 0;
        }
        return progress;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!colorChanged){
            if (timer != null){
                timer.stop();
                timer = null;
            }
        }
    }


    protected static int normalizeColorValue(int value){
        if (value > 255){
            return 255;
        }else if (value < 0){
            return 0;
        }else{
            return value;
        }
    }

    protected Color getTemperatureColor(){
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

}
