package enginetest;

import main.java.mazerunner.engine.DisplayGameGrid;
import main.java.mazerunner.engine.GameGrid;
import main.java.mazerunner.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayGameGridTest {
    GameGrid gameGrid;
    Player player;
    DisplayGameGrid displayGameGrid;
    @Before
    public void setUp(){
        gameGrid = new GameGrid(10, 10);
        player = new Player(gameGrid.getRandomPosition());
        displayGameGrid = new DisplayGameGrid(gameGrid.getGrids(), player);
    }
    @Test
    public void update() {
        int size = displayGameGrid.update();
        assertEquals(displayGameGrid.getMap().length, size);
    }

    @Test
    public void winMessage() {
        int len = displayGameGrid.winMessage();
        assertEquals(displayGameGrid.getMap().length, len);
    }

    @Test
    public void loseMessage() {
        int len = displayGameGrid.loseMessage();
        assertEquals(displayGameGrid.getMap().length, len);
    }

    @Test
    public void nextLevelMessage() {
        assertTrue(displayGameGrid.nextLevelMessage());
    }
}