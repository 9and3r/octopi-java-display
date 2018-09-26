package com.and3r.octopijavadisplay.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IndefiniteLoadingIndicator extends JPanel {

    private int currentSize;
    private int maxSize;
    private int minSize;
    private boolean directionBigger;

    private int currentAngle;


    public IndefiniteLoadingIndicator(){
        maxSize = 100;
        minSize = 50;
        setOpaque(false);
        Timer timer = new Timer(10, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        //Set  anti-alias!
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int smallestSize = getHeight();
        if (smallestSize > getWidth()){
            smallestSize = getWidth();
        }

        maxSize = smallestSize / 2;
        minSize = smallestSize / 6;

        if (directionBigger){
            currentSize ++;
            if (currentSize > maxSize){
                directionBigger = false;
                currentSize = maxSize;
            }
        }else{
            currentSize--;
            if (currentSize < minSize){
                directionBigger = true;
                currentSize = minSize;
            }
        }

        int x = getWidth()/2 - currentSize/2;
        int y = getHeight()/2 - currentSize/2;




        currentAngle ++;
        if (currentAngle > 360){
            currentAngle = 0;
        }
        int secondAngle = currentAngle + 60;
        if (secondAngle > 360){
            secondAngle -= 360;
        }

        g.setColor(ColorManager.buttonNormalStateStrokeColor);
        g.fillArc(x, y, currentSize, currentSize, currentAngle, secondAngle);

        //g.setColor(ColorManager.buttonNormalStateStrokeColorPressed);
        //g.drawArc(x, y, currentSize, currentSize, currentAngle, secondAngle);

    }


}
