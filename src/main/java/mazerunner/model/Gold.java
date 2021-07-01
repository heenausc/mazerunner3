package main.java.mazerunner.model;

/**
 * A class representing the 'key' game object.
 *
 * @author kphilemon
 */
public class Gold extends GameObject {

    private static final char DEFAULT_CHARACTER = 'G';

    /**
     * @param position the {@link Position} of this game object
     */
    public Gold(Position position) {
        super(DEFAULT_CHARACTER, position);
    }

}

