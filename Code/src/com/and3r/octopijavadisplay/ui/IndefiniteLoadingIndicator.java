package com.and3r.octopijavadisplay.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class IndefiniteLoadingIndicator extends JPanel implements ComponentListener {

    private int currentSize;

    private int angles[];
    private int anglesSize[];
    private int speeds[];
    private Color[] colors;
    private int x;
    private int y;


    public IndefiniteLoadingIndicator(){


        angles = new int[]{0, 120, 240, 60, 180, 300};
        anglesSize = new int[]{60, 60, 60, 60, 60, 60};
        speeds = new int[]{1, 1, 1, 1, 1, 1};
        colors = new Color[]{
                ColorManager.buttonNormalStateStrokeColorPressed,
                ColorManager.buttonNormalStateStrokeColorPressed,
                ColorManager.buttonNormalStateStrokeColorPressed,
                ColorManager.buttonNormalStateStrokeColor,
                ColorManager.buttonNormalStateStrokeColor,
                ColorManager.buttonNormalStateStrokeColor,
        };

        setOpaque(false);
        addComponentListener(this);
        Timer timer = new Timer(10, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int smallestSize = getHeight();
        if (smallestSize > getWidth()){
            smallestSize = getWidth();
        }

        currentSize = smallestSize / 2;

        x = getWidth()/2 - currentSize/2;
        y = getHeight()/2 - currentSize/2;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        //Set  anti-alias!
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        for (int i=0; i<angles.length; i++){

            angles[i] = angles[i] + speeds[i];
            if (angles[i] > 360){
                angles[i] -= 360;
            }else if (angles[i] < 0){
                angles[i] += 360;
            }
            g.setColor(colors[i]);
            g.fillArc(x, y, currentSize, currentSize, angles[i], anglesSize[i]);
        }
        g.setColor(ColorManager.backgroundColor);
        g.fillArc(x+currentSize/4, y+currentSize/4, currentSize/2, currentSize/2, 0, 360);
    }




}
