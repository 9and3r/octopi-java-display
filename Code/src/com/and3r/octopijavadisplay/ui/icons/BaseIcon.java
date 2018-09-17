package com.and3r.octopijavadisplay.ui.icons;

import com.and3r.octopijavadisplay.ui.BasePanel;

import javax.swing.*;

public abstract class BaseIcon extends JPanel {


    public BaseIcon(){

    }

    protected abstract int[][] getInitialXPoints();

    protected abstract int[][] getInitialYPoints();


}
