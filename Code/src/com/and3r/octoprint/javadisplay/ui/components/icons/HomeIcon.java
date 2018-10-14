package com.and3r.octoprint.javadisplay.ui.components.icons;

public class HomeIcon extends ClickableIcon {

    @Override
    protected void init(Object... params) {

    }

    @Override
    protected int getMargin() {
        return 20;
    }

    @Override
    protected int[][] getInitialXPoints() {
        return new int[][]{{0, 50, 100, 80, 80, 20, 20}};
    }

    @Override
    protected int[][] getInitialYPoints() {
        return new int[][]{{30, 0, 30, 30, 80, 80, 30}};
    }

    @Override
    protected int getStrokeWidth(int i) {
        return super.getStrokeWidth(i) * 2;
    }

}
