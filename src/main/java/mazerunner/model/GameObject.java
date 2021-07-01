package main.java.mazerunner.model;

/**
 * GameObject is the base class for all game objects.
 */
public abstract class GameObject {

    private final char character;
    private Position position;

    /**
     * @param character     the icon to represent this game object
     * @param position the {@link Position} of this game object in the map
     */
    GameObject(char character, Position position) {
        this.character = character;
        this.position = position;
    }

    /**
     * Returns the {@link #character} of this game object.
     *
     * @return the game object's {@link #character}
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Returns the {@link #position} of this game object.
     *
     * @return the game object's {@link #position}
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the x-coordinate of this game object's {@link Position}
     *
     * @return the game object's x-coordinate
     */
    public int getX() {
        return position.getX();
    }

    /**
     * Sets the x-coordinate of this game object to the specified x-coordinate.
     *
     * @param x the game object's x-coordinate
     */
    void setX(int x) {
        position = new Position(x, position.getY());
    }

    /**
     * Returns the y-coordinate of this game object's {@link Position}
     *
     * @return the game object's y-coordinate
     */
    public int getY() {
        return position.getY();
    }

    /**
     * Sets the y-coordinate of this game object to the specified y-coordinate.
     *
     * @param y the game object's y-coordinate
     */
    void setY(int y) {
        position = new Position(position.getX(), y);
    }

    public void setPosition(Position position){this.position = position;}
}
