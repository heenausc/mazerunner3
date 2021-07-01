package main.java.mazerunner.model;

import static main.java.mazerunner.model.Common.INITIAL_HEALTH_LEVEL;
import static main.java.mazerunner.model.Directions.*;
/**
 * A class to stand for the 'player' of this game.
 */
public class Player extends GameObject{

    private static final char DEFAULT_ICON = 'P';
    private int healthLevel;

    /**
     * @param position the {@link Position} of this game object
     */
    public Player(Position position) {
        super(DEFAULT_ICON, position);
        this.healthLevel = INITIAL_HEALTH_LEVEL;
    }


    /**
     * Returns the {@link #healthLevel} of this Player object.
     *
     * @return the {@link #healthLevel} of this Player object
     */
    public int getHealthLevel() {
        return healthLevel;
    }

    /**
     * Reduces the {@link #healthLevel} of this Player object.
     *
     * @param value the value to be reduced
     */
    public void reduceHealthLevelBy(int value) {
        healthLevel -= value;
    }

    /**
     * Returns <code>true</code> if this Player object's {@link #healthLevel} is less than or equals to zero.
     *
     * @return <code>true</code> if {@link #healthLevel} is less than or equals to zero, <code>false</code> otherwise
     */
    public boolean isDead() {
        return healthLevel <= 0;
    }


    /**
     * Changes this Player object's Position based on the specified direction
     *
     * @param direction the direction to move in integer
     */
    public void move(String direction) {
        switch (direction) {
            case DIRECTION_UP:
                this.setY(this.getY() - 2);
                break;
            case DIRECTION_DOWN:
                this.setY(this.getY() + 2);
                break;
            case DIRECTION_LEFT:
                this.setX(this.getX() - 4);
                break;
            case DIRECTION_RIGHT:
                this.setX(this.getX() + 4);
                break;
            default:
                break;
        }
    }
}
