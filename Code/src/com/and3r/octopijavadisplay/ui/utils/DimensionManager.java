package com.and3r.octopijavadisplay.ui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DimensionManager implements ComponentListener {


    public static Font defaultFont = new Font("Arial", Font.PLAIN, 22);

    public static int widowWidth = 300;
    public static int windowHeight = 200;

    private static DimensionManager dimensionManager;

    private DimensionManager(){
        InputStream stream = this.getClass().getResourceAsStream("Oxygen-Regular.ttf");
        try {
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenTo(JFrame jFrame){
        jFrame.addComponentListener(this);
        componentResized(new ComponentEvent(jFrame, 0));
    }

    public synchronized static DimensionManager get(){
        if (dimensionManager == null){
            dimensionManager = new DimensionManager();
        }
        return dimensionManager;
    }

    public void init(String fontName, int width, int height){
        widowWidth = width;
        windowHeight = height;
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts();
        for (Font f : fonts) {
            //System.out.println(f.getFontName());
        }
    }


    @Override
    public void componentResized(ComponentEvent e) {
        widowWidth = e.getComponent().getWidth();
        windowHeight = e.getComponent().getHeight();

        defaultFont = defaultFont.deriveFont(Font.PLAIN, e.getComponent().getWidth() / 30);
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
