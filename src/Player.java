/**
 * A representation of a player in the game.
 *
 * @author Edmund Wu
 */
public class Player extends Entity {
    private String name;

    /**
     * Generates a Player with id at (x, y) called name.
     *
     * @param id   the "unique" Entity id
     * @param x    the x coordinate where the player is spawning
     * @param y    the y coordinate where the player is spawning
     * @param name the name of the Player
     */
    public Player(int id, int x, int y, String name) {
        super(id, new Position(x, y));
        this.name = name;
    }

    /**
     * Generates a Player with id at pos called name.
     *
     * @param id   the "unique" Entity id
     * @param pos  the position the player spawns at
     * @param name the name of the Player
     */
    public Player(int id, Position pos, String name) {
        super(id, pos);
        this.name = name;
    }

    /**
     * Returns the Player's name.
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
