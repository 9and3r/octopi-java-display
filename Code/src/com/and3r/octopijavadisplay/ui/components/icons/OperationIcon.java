package com.and3r.octopijavadisplay.ui.components.icons;

public class OperationIcon extends ClickableIcon {

    public static final int PAUSE_ICON = 0;
    public static final int PLAY_ICON = 1;
    public static final int STOP_ICON = 2;

    private int type;

    private int[][] xPoints;
    private int[][] yPoints;

    public OperationIcon(int type){
        super(type);
        this.type = type;
    }

    @Override
    protected void init(Object... params) {
        setIconType((Integer) params[0]);
    }

    public void setIconType(int type){
        if (type == PAUSE_ICON){
            xPoints = new int[][]{
                    {0, 10, 10, 0},
                    {20, 30, 30, 20}};
            yPoints = new int[][]{
                    {0, 0, 40, 40},
                    {0, 0, 40, 40}
            };
        }else if (type == PLAY_ICON){
            xPoints = new int[][]{
                    {0, 35, 0}};
            yPoints = new int[][]{
                    {0, 25, 50}
            };
        }else if (type == STOP_ICON){
            xPoints = new int[][]{
                    {0, 40, 40, 0}};
            yPoints = new int[][]{
                    {0, 0, 40, 40}
            };
        }
        repaint();
    }

    @Override
    protected int getMargin() {
        return 20;
    }

    @Override
    protected int[][] getInitialXPoints() {
        return xPoints;
    }

    @Override
    protected int[][] getInitialYPoints() {
        return yPoints;
    }

}
