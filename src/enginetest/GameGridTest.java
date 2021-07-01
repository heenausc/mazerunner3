package enginetest;

import main.java.mazerunner.engine.GameGrid;
import main.java.mazerunner.model.Gold;
import main.java.mazerunner.model.Player;
import main.java.mazerunner.model.Position;
import org.junit.Before;
import org.junit.Test;

import static main.java.mazerunner.mazegenerator.MazeGenerator.EMPTY_SPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameGridTest {

    GameGrid grid;
    @Before
    public void setUp(){
        grid = new GameGrid(10, 10);
    }
    @Test
    public void addPlayer2Grids() {
        Player player = new Player(grid.getRandomPosition());
        Position pos = grid.add2Grids(player);
        char character = grid.getGrids()[pos.getY()][pos.getX()];
        assertTrue(pos.isValid());
        assertEquals(character, player.getCharacter());
        assertEquals(character, 'P');
    }
    @Test
    public void addGold2Grids() {
        Gold gold = new Gold(grid.getRandomPosition());
        Position pos = grid.add2Grids(gold);
        char character = grid.getGrids()[pos.getY()][pos.getX()];
        assertTrue(pos.isValid());
        assertEquals(character, gold.getCharacter());
        assertEquals(character, 'G');
    }

    @Test
    public void removePlayerFromGrids() {
        Player player = new Player(grid.getRandomPosition());
        Position pos = grid.removeFromGrids(player);
        assertTrue(pos.isValid());
        assertEquals(grid.getGrids()[pos.getY()][pos.getX()], EMPTY_SPACE);
    }

    @Test
    public void removeGoldFromGrids() {
        Gold gold = new Gold(grid.getRandomPosition());
        Position pos = grid.removeFromGrids(gold);
        assertTrue(pos.isValid());
        assertEquals(grid.getGrids()[pos.getY()][pos.getX()], EMPTY_SPACE);
    }

    @Test
    public void getRandomPosition() {
        Position randPosition = grid.getRandomPosition();
        assertTrue(randPosition.isValid());
    }
}