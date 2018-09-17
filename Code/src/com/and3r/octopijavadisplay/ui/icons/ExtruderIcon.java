package com.and3r.octopijavadisplay.ui.icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ExtruderIcon extends JPanel implements ComponentListener {

    private double temp;
    private int tempInt;

    private static final int[] xPoints =  {50, 70, 70, 120, 120, 70, 70, 90, 60, 40, 30, 40, 40,0, 0, 50};
    private static final int[] yPoints = {0, 0, 30, 30, 60, 60, 65, 65, 80, 80, 65, 65, 60, 60, 30, 30};

    private int baseWidth;
    private int baseHeight;
    private double aspectRatio;

    private int margin = 20;

    private int[] xPointsCalculated;
    private int[] yPointsCalculated;

    private int[] targetColor;
    private int[] currentColor;

    public ExtruderIcon(){
        setOpaque(false);
        this.temp = 0;
        this.tempInt = 0;
        this.targetColor = new int[3];
        this.currentColor = new int[3];
        xPointsCalculated = new int[xPoints.length];
        System.arraycopy( xPoints, 0, xPointsCalculated, 0, xPoints.length );
        yPointsCalculated = new int[yPoints.length];
        System.arraycopy(yPoints, 0, yPointsCalculated, 0, yPoints.length );

        // Calculate sizes
        for (int current: xPoints){
            if (current > baseWidth){
                baseWidth = current + margin*2;
            }
        }

        for (int current: yPoints){
            if (current > baseHeight){
                baseHeight = current + margin*2;
            }
        }
        setTemp(0);

        aspectRatio = baseWidth / (double) baseHeight;

        addComponentListener(this);
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
        boolean colorChanged = false;
        for (int i=0; i<3; i++){
            if (currentColor[i] < targetColor[i]){
                currentColor[i]++;
                colorChanged = true;
            }else if (currentColor[i] > targetColor[i]){
                currentColor[i]--;
                colorChanged = true;
            }
        }

        Color color = new Color(currentColor[0], currentColor[1], currentColor[2]);

        g.setColor(Color.BLACK);
        //g.drawRect(0, 0, 100, 100);

        g.drawPolygon(xPointsCalculated, yPointsCalculated, xPointsCalculated.length);
        g.setColor(color);
        g.fillPolygon(xPointsCalculated, yPointsCalculated, xPointsCalculated.length);

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
    public void componentResized(ComponentEvent e) {
        double newAspectRatio = getWidth() / (double) getHeight();
        double multiplicator;
        if (aspectRatio > newAspectRatio){
            multiplicator = getWidth() / (double) baseWidth;
        }else{
            multiplicator = getHeight() / (double) baseHeight;
        }
        int i = 0;
        while (i < xPoints.length){
            xPointsCalculated[i] = (int) (xPoints[i] * multiplicator);
            yPointsCalculated[i] = (int) (yPoints[i] * multiplicator);
            i++;
        }

        // Center image on the middle
            int movePixel = (int) (getWidth() - (baseWidth * multiplicator)) / 2;
            i = 0;
            while (i<xPointsCalculated.length){
                xPointsCalculated[i] = xPointsCalculated[i] + movePixel + (int)(margin*multiplicator);
                i++;
            }
            movePixel = (int) (getHeight() - (baseHeight * multiplicator)) / 2;
            i = 0;
            while (i<yPointsCalculated.length){
                yPointsCalculated[i] = yPointsCalculated[i] + movePixel + (int)(margin*multiplicator);
                i++;
            }


        repaint();
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

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
