package main.java.mazerunner.engine;

import main.java.mazerunner.model.Gold;
import main.java.mazerunner.model.Player;

import java.io.IOException;
import java.util.ArrayList;

import static main.java.mazerunner.model.Common.*;

import static main.java.mazerunner.mazegenerator.MazeGenerator.EMPTY_SPACE;

/**
 * A class to display stuffs on the console.
 */
public class DisplayGameGrid {



    private final char[][] map;
    private final Player player;
    private final int height;
    private final int width;


    private ArrayList<Gold> golds;
    /**
     * @param map    the 2D array of characters representing the game map with maze and game objects
     * @param player the {@link Player} object
     */
    public DisplayGameGrid(char[][] map, Player player) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
        this.player = player;
        this.golds = new ArrayList<>();
    }

    /**
     * Updates the game map and game stats to be printed on the console.
     */
    public int update() {
        clearScreen();
        int mapSize = 0;
        for (char[] chars : map) {
            System.out.print(new String(chars));
            System.out.println();
            mapSize++;
        }
        System.out.printf("\tGold(s) remained: %d/%d\n",this.golds.size() , NUMBER_OF_GOLD);
        System.out.printf("\tHealth: %d/%d\n", (player.isDead()) ? 0 : player.getHealthLevel(), INITIAL_HEALTH_LEVEL);
        return mapSize;
    }

    /**
     * Prints the game intro on the console.
     *
     * @return int : will be only used for testing
     */
    public int gameIntroMessage() {
        clearScreen();
        int ret = 0;
        String[] intros = {"Player, 'P', the maze runner, is trapped in a maze.\n\n",
                "To escape from the maze, he must find all the golds, 'G', that are scattered in the maze.\n",
                "After collecting all the golds, the exit, 'E', will appear at the corner of the maze.\n\n",
                "And of course, Player is just a normal guy, he couldn't see or walk through the maze walls.\n",
                "Every movement will cost Player a drop of blood. So make your move wisely!.\n\n"};
        for (String intro : intros) {
            printWithDelay(intro, 1);
            ret++;
        }
        return ret;
    }

    /**
     * Prints a message indicating victory on the console when the player has completed both levels.
     *
     * @return int : will be only used for testing
     */
    public int winMessage() {
        int mid_H = height / 2;
        int mid_W = width / 2;

        clear(mid_H, mid_W);

        map[mid_H][mid_W - 3] = 'V';
        map[mid_H][mid_W - 2] = 'I';
        map[mid_H][mid_W - 1] = 'C';
        map[mid_H][mid_W] = 'T';
        map[mid_H][mid_W + 1] = 'O';
        map[mid_H][mid_W + 2] = 'R';
        map[mid_H][mid_W + 3] = 'Y';
        map[mid_H][mid_W + 4] = '!';
        return update();
    }

    /**
     * Prints a message indicating game over on the console when the player has lost the game.
     *
     * @return int : will be only used for testing
     */
    public int loseMessage() {
        int mid_H = height / 2;
        int mid_W = width / 2;

        clear(mid_H, mid_W);

        map[mid_H][mid_W - 4] = 'G';
        map[mid_H][mid_W - 3] = 'A';
        map[mid_H][mid_W - 2] = 'M';
        map[mid_H][mid_W - 1] = 'E';
        map[mid_H][mid_W] = ' ';
        map[mid_H][mid_W + 1] = 'O';
        map[mid_H][mid_W + 2] = 'V';
        map[mid_H][mid_W + 3] = 'E';
        map[mid_H][mid_W + 4] = 'R';
        map[mid_H][mid_W + 5] = '!';
        return update();
    }

    /**
     * Prints a message indicating next level on the console, invoked when the player has completed level one.
     *
     * @return boolean : will be only used for testing
     */
    public boolean nextLevelMessage() {
        printWithDelay("\nALL GOLDS WAS FOUND! ", 0);
        printWithDelay("BUT...\nWE'RE NOT DONE YET...", 0);
        printWithDelay("\nFIND THE EXIT BEFORE YOUR HP BECOMES ZERO!\n", 0);
        return true;
    }

    /**
     * Prints a message indicating invalid movement on the console, when the player's movement is not valid.
     */
    void invalidMovementMessage() {
        System.out.println("You can't walk through walls.");
    }

    /**
     * Prints the specified String message character by character,
     * followed by a delay for the specified number of milliseconds after each character is printed.
     * This will give a 'typing' effect.
     *
     * @param message the String to be printed
     * @param millis  the length of time to delay in milliseconds
     */
    private void printWithDelay(String message, int millis) {
        if (message.length() > 0) {
            for (int i = 0; i < message.length(); i++) {
                System.out.print(message.charAt(i));
                delay(millis);
            }
        }
    }

    /**
     * Delays for the specified number of milliseconds.
     *
     * @param millis the length of time to delay in milliseconds
     */
    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the console output.
     */
    private void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the middle of the game map based on the specified middle vertical and horizontal index.
     *
     * @param mid_H the middle vertical index
     * @param mid_W the middle horizontal index
     */
    private void clear(int mid_H, int mid_W) {
        for (int i = mid_H - 1; i <= mid_H + 1; i++) {
            for (int j = mid_W - 7; j <= mid_W + 7; j++) {
                map[i][j] = EMPTY_SPACE;
            }
        }
    }

    /**
     * getter setter
     */
    public char[][] getMap() {
        return map;
    }


    public ArrayList<Gold> getGolds() {
        return golds;
    }

    public void setGolds(ArrayList<Gold> golds) {
        this.golds = golds;
    }
}
