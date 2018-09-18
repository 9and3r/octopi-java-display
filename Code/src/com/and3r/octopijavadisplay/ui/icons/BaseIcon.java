package com.and3r.octopijavadisplay.ui.icons;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class BaseIcon extends JPanel implements ComponentListener {

    private int baseWidth;
    private int baseHeight;
    private double aspectRatio;

    private int[][] xPointsCalculated;
    private int[][] yPointsCalculated;




    public BaseIcon(Object... params){

        init(params);

        int[][] initialXPoints = getInitialXPoints();
        int[][] initialYPoints = getInitialYPoints();

        // Check if the points numbers make sense
        if (initialXPoints.length != initialYPoints.length){
            throw new IllegalArgumentException();
        }
        for (int i=0; i<initialXPoints.length; i++){
            if (initialXPoints[i].length != initialYPoints[i].length){
                throw new IllegalArgumentException();
            }
        }

        // Generate initial X points
        xPointsCalculated = new int[initialXPoints.length][];
        for (int i = 0; i< initialXPoints.length; i++){
            xPointsCalculated[i] = new int[initialXPoints[i].length];
            System.arraycopy( initialXPoints[i], 0, xPointsCalculated[i], 0, initialXPoints[i].length );
        }

        // Generate initial Y points
        yPointsCalculated = new int[initialYPoints.length][];
        for (int i = 0; i< initialYPoints.length; i++){
            yPointsCalculated[i] = new int[initialYPoints[i].length];
            System.arraycopy( initialYPoints[i], 0, yPointsCalculated[i], 0, initialYPoints[i].length );
        }

        int margin = getMargin();

        // Calculate width
        for (int i=0; i<initialXPoints.length; i++){
            for (int z=0; z<initialXPoints[i].length; z++){
                if (initialXPoints[i][z] > baseWidth){
                    baseWidth = initialXPoints[i][z] + margin*2;
                }
            }
        }

        // Calculate height
        for (int i=0; i<initialYPoints.length; i++){
            for (int z=0; z<initialYPoints[i].length; z++){
                if (initialYPoints[i][z] > baseHeight){
                    baseHeight = initialYPoints[i][z] + margin*2;
                }
            }
        }
        aspectRatio = baseWidth / (double) baseHeight;

        addComponentListener(this);
    }

    protected abstract void init(Object... params);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        //Set  anti-alias!
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i=0; i<xPointsCalculated.length; i++){
            Color fillColor = getFillColor(i);
            if (fillColor != null){
                g.setColor(fillColor);
                g.fillPolygon(xPointsCalculated[i], yPointsCalculated[i], xPointsCalculated[i].length);
            }
            Color strokeColor = getStrokeColor(i);
            graphics2D.setStroke(new BasicStroke(getStrokeWidth(i)));
            if (strokeColor != null){
                g.setColor(strokeColor);
                g.drawPolygon(xPointsCalculated[i], yPointsCalculated[i], xPointsCalculated[i].length);
            }
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
        int[][] xPoints = getInitialXPoints();
        int[][] yPoints = getInitialYPoints();
        for (int z=0; z<xPoints.length; z++){
            int i = 0;
            while (i < xPoints[z].length){
                xPointsCalculated[z][i] = (int) (xPoints[z][i] * multiplicator);
                yPointsCalculated[z][i] = (int) (yPoints[z][i] * multiplicator);
                i++;
            }
        }


        int margin = getMargin();


        // Center image on the middle
        int movePixelX = (int) (getWidth() - (baseWidth * multiplicator)) / 2;
        int movePixelY = (int) (getHeight() - (baseHeight * multiplicator)) / 2;

        for (int z=0; z<xPoints.length; z++){
            int i = 0;
            while (i<xPointsCalculated[z].length){
                xPointsCalculated[z][i] = xPointsCalculated[z][i] + movePixelX + (int)(margin*multiplicator);
                i++;
            }

            i = 0;
            while (i<yPointsCalculated[z].length){
                yPointsCalculated[z][i]  = yPointsCalculated[z][i]  + movePixelY + (int)(margin*multiplicator);
                i++;
            }
        }


        repaint();
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

    protected abstract int getMargin();

    protected abstract int[][] getInitialXPoints();

    protected abstract int[][] getInitialYPoints();

    protected abstract int getStrokeWidth(int i);
    protected abstract Color getStrokeColor(int i);
    protected abstract Color getFillColor(int i);


}
