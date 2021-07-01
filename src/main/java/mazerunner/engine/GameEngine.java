package main.java.mazerunner.engine;

import main.java.mazerunner.model.Exit;
import main.java.mazerunner.model.Gold;
import main.java.mazerunner.model.Player;
import main.java.mazerunner.model.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static main.java.mazerunner.model.Common.*;

public class GameEngine {

    public static void main(String[] args) {
        // clear screen first
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("***********Welcome to the Maze Runner Game***********");
        GameEngine gameEngine = new GameEngine();
        gameEngine.init();
        gameEngine.startGame();
    }


    private GameGrid gameGrid;
    private  Player player;
    private Exit exit;
    private DisplayGameGrid displayGameGrid;

    private boolean hasFoundExit;
    private boolean hasFoundAllGolds;
    public GameEngine(){
        hasFoundExit = false;
        hasFoundAllGolds = false;
        exit = null;
        gameGrid = new GameGrid(MAZE_HEIGHT, MAZE_WIDTH);
        player = new Player(gameGrid.getRandomPosition());
        displayGameGrid = new DisplayGameGrid(gameGrid.getGrids(), player);
        // init game objects
        gameGrid.add2Grids(player);

        for (int i = 0; i < NUMBER_OF_GOLD; i++) {
            Gold gold = new Gold(gameGrid.getRandomPosition());
            displayGameGrid.getGolds().add(gold);
            gameGrid.add2Grids(gold);
        }
    }

    /**
     * init game pad with string according to maze
     */
    public int init() {

        displayGameGrid.gameIntroMessage();
        return displayGameGrid.update();
    }


    /**
     * start game
     */
    void startGame() {
        while (true) {
            move2Direction();
            if (player.isDead()) {
                gameFailed();
                break;
            }
            // if all keys are collected
            if (displayGameGrid.getGolds().size() == 0) {
                foundAllGolds();
                displayGameGrid.update();
                if(hasFoundExit){
                    gameExit();
                }
                break;
            }
        }
    }

    /**
     * show exit message when player find exit.
     */
    public int gameExit() {
        return displayGameGrid.winMessage();
    }


    /**
     * move player to input direction
     */
    private void move2Direction() {
        String direction = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter direction to be moved (right:a, left:d, up:w, down:s): ");
        while (!scanner.hasNext()) {
            System.out.print("Please input character! Enter again: ");
            scanner.next();
        }
        direction = scanner.next();
        switch (direction) {
            case "w":
            case "s":
            case "a":
            case "d":
                move(direction);
                break;
            default:
                break;
        }
        displayGameGrid.update();
    }

    /**
     * move player with direction
     * @param direction : to be moved direction
     * @return Position : is only used for test
     */
    public Position move(String direction){
        Position nextPosition = player.getPosition();
        if (gameGrid.validateMovement(player, direction)) {
            gameGrid.removeFromGrids(player);
            player.move(direction);
            nextPosition = player.getPosition();
            gameGrid.add2Grids(player);
            checkIfPlayerHasReachedItem();
            player.reduceHealthLevelBy(1);

        } else {
            displayGameGrid.invalidMovementMessage();
        }
        return nextPosition;
    }

    /**
     * Updates the game stats if the player has reached specified items in the game.
     */
    public String checkIfPlayerHasReachedItem() {
        String type = "";
        for (Gold gold : displayGameGrid.getGolds()) {
            if (player.getPosition().equals(gold.getPosition())) {
                displayGameGrid.getGolds().remove(gold);
                displayGameGrid.getGolds().trimToSize();
                type = String.valueOf(gold.getCharacter());
                return type;
            }
        }
        if((exit != null) && player.getPosition().equals(exit.getPosition())){
            hasFoundExit = true;
            type = String.valueOf(exit.getCharacter());
        }
        return type;
    }

    public void foundAllGolds() {
        if(!hasFoundAllGolds){
            displayGameGrid.nextLevelMessage();
            if(exit == null){
                exit = new Exit(generateExitPosition());
                gameGrid.add2Grids(exit);
            }
            hasFoundAllGolds = true;
        }
    }
    /**
     * Returns a {@link Position} object which is in a quadrant that is opposite to the
     * specified Player's quadrant in the maze.
     * @return a {@link Position} in an opposite quadrant
     */
    public Position generateExitPosition() {
        int x, y;
        if (player.getX() > gameGrid.getWidth() / 2) {
            if (player.getY() > gameGrid.getHeight() / 2) {
                // player is in quadrant 4, set exit to quadrant 2
                x = 2;
                y = 1;
            } else {
                // player is in quadrant 1, set exit to quadrant 3
                x = 2;
                y = gameGrid.getHeight() - 2;
            }
        } else {
            if (player.getY() > gameGrid.getHeight() / 2) {
                // player is in quadrant 3, set exit to quadrant 1
                x = gameGrid.getWidth() - 3;
                y = 1;
            } else {
                // player is in quadrant 2, set exit to quadrant 4
                x = gameGrid.getWidth() - 3;
                y = gameGrid.getHeight() - 2;
            }
        }
        return new Position(x, y);
    }

    /**
     * when game failed, will be called.
     * @return int : only used for testing
     */
    public int gameFailed() {
        return displayGameGrid.loseMessage();
    }


    public Exit getExit() {
        return exit;
    }

    public ArrayList<Gold> getGolds() {
        return displayGameGrid.getGolds();
    }

    public Player getPlayer() {
        return player;
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public boolean isHasFoundExit() {
        return hasFoundExit;
    }
}
