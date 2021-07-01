package enginetest;

import main.java.mazerunner.engine.GameEngine;
import main.java.mazerunner.model.Position;
import org.junit.Before;
import org.junit.Test;

import static main.java.mazerunner.model.Common.NUMBER_OF_GOLD;
import static org.junit.Assert.*;

public class GameEngineTest {
    GameEngine gameEngine;
    @Before
    public void setUp(){
        gameEngine = new GameEngine();
        gameEngine.init();
    }
    @Test
    public void init() {
        //check gold numbers
        assertEquals(NUMBER_OF_GOLD, gameEngine.getGolds().size());
        //check whether exit is null
        assertNull(gameEngine.getExit());
    }

    @Test
    public void generateExitPosition() {
        Position position = gameEngine.generateExitPosition();
        assertTrue(position.isValid());
    }

    @Test
    public void moveUp() {
        Position current = gameEngine.getPlayer().getPosition();

        //check whether input direction is correct, if it is correct and then
        if(gameEngine.getGameGrid().validateMovement(gameEngine.getPlayer(), "w")){
            Position next = gameEngine.move("w");
            assertEquals(current.getY() - 2, next.getY());
        }else{//if it is not correct
            Position next = gameEngine.move("w");
            assertTrue(current.equals(next));
        }

    }
    @Test
    public void moveDown() {
        Position current = gameEngine.getPlayer().getPosition();

        //check whether input direction is correct, if it is correct and then
        if(gameEngine.getGameGrid().validateMovement(gameEngine.getPlayer(), "s")){
            Position next = gameEngine.move("s");
            assertEquals(current.getY() + 2, next.getY());
        }else{//if it is not correct
            Position next = gameEngine.move("s");
            assertTrue(current.equals(next));
        }
    }
    @Test
    public void moveLeft() {
        Position current = gameEngine.getPlayer().getPosition();

        //check whether input direction is correct, if it is correct and then
        if(gameEngine.getGameGrid().validateMovement(gameEngine.getPlayer(), "a")){
            Position next = gameEngine.move("a");
            assertEquals(current.getX() - 4, next.getX());
        }else{//if it is not correct
            Position next = gameEngine.move("a");
            assertTrue( current.equals(next));
        }
    }
    @Test
    public void moveRight() {
        Position current = gameEngine.getPlayer().getPosition();

        //check whether input direction is correct, if it is correct and then
        if(gameEngine.getGameGrid().validateMovement(gameEngine.getPlayer(), "d")){
            Position next = gameEngine.move("d");
            assertEquals(current.getX() + 4, next.getX());
        }else{//if it is not correct
            Position next = gameEngine.move("d");
            assertTrue(current.equals(next));
        }
    }

    @Test
    public void checkIfPlayerHasReachedItemGold() {
        //set player's position as any gold's position
        gameEngine.getPlayer().setPosition(gameEngine.getGolds().get(0).getPosition());
        String type = gameEngine.checkIfPlayerHasReachedItem();
        assertEquals("G", type);
    }
    @Test
    public void checkIfPlayerHasReachedItemExit() {
        //call some method for initialization
        gameEngine.foundAllGolds();
        //set player's position as an exit's position
        gameEngine.getPlayer().setPosition(gameEngine.getExit().getPosition());
        String type = gameEngine.checkIfPlayerHasReachedItem();
        assertEquals("E", type);
    }
}