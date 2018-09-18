package com.and3r.octopijavadisplay.ui.icons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class ClickableIcon extends BaseIcon implements MouseListener {

    protected boolean pressed;
    protected boolean hover;

    public ClickableIcon(Object... params){
        super(params);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        hover = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;
        repaint();
    }

}
