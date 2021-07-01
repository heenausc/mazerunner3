package main.java.mazerunner.model;

/**
 * A class representing the 'exit' of the maze.
 *
 * @author kphilemon
 */
public class Exit extends GameObject {

    private static final char DEFAULT_ICON = 'E';

    /**
     * @param position the {@link Position} of this game object
     */
    public Exit(Position position) {
        super(DEFAULT_ICON, position);
    }

}
