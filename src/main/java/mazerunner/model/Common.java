package main.java.mazerunner.model;

import java.io.File;

public class Common {

    public static final int MAZE_WIDTH = 10;
    public static final int MAZE_HEIGHT = 10;
    public static final int NUMBER_OF_GOLD = 3;
    public static final int INITIAL_HEALTH_LEVEL = 90;
    public static File file = new File("");
    public static String absolutePath = file.getAbsolutePath();
    public static String baseUrl = "file:\\" + absolutePath + "\\resource\\img\\";
    public static int cellSize = 23;
    public static int statusBarWidth = 140;
    public static int gameResultSize = 140;
    public static int arrowSize = 30;
    //page size
    public static int pageWidth = cellSize*41 + statusBarWidth;
    public static int pageHeight = cellSize*22;

}
