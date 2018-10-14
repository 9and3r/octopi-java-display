package com.and3r.octoprint.javadisplay.ui.components.icons;

public class ArrowIcon extends ClickableIcon {


    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    private int[][] xPoints;
    private int[][] yPoints;

    private int direction;

    public ArrowIcon(int direction){
        super(direction);
    }

    public ArrowIcon(int direction, boolean showBed){
        super(direction, showBed);
    }

    @Override
    protected void init(Object... params) {
        direction = (int) params[0];
        int[] defaultX = {0, 20, 20, 40, 20, 20, 0};
        int[] defaultY = {10, 10, 0, 15, 30, 20, 20};

        boolean showPlatform = false;
        if (params.length >= 2){
            showPlatform = (boolean) params[1];
        }

        if (showPlatform){
            xPoints = new int[2][];
            yPoints = new int[2][];
        }else{
            xPoints = new int[1][];
            yPoints = new int[1][];
        }

        // Change axes if necesary
        if (direction == DOWN || direction == UP){
            int[] aux = defaultX;
            defaultX = defaultY;
            defaultY = aux;
        }

        if (direction == UP){
            // Calculate max
            int max = 0;
            for (int i=0; i<defaultY.length; i++ ){
                if (max < defaultY[i]){
                    max = defaultY[i];
                }
            }
            for (int i=0; i<defaultY.length; i++){
                defaultY[i] = max - defaultY[i];
            }
        }

        if (direction == LEFT){
            // Calculate max
            int max = 0;
            for (int i=0; i<defaultX.length; i++ ){
                if (max < defaultX[i]){
                    max = defaultX[i];
                }
            }
            for (int i=0; i<defaultX.length; i++){
                defaultX[i] = max - defaultX[i];
            }
        }

        xPoints[0] = defaultX;
        yPoints[0] = defaultY;

        if (showPlatform){
            xPoints[1] = new int[]{0, 30};
            yPoints[1] = new int[]{50, 50};
        }

    }

    @Override
    protected int getMargin() {
        return 10;
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
