/**
 * Representation of an object/entity in the game.
 *
 * @author Edmund Wu
 */
public abstract class Entity {
    private int id;
    private Position pos;

    /**
     * Creates an Entity with id at (x, y).
     *
     * @param id the "unique" Entity id
     * @param x  the spawn Tile's x coordinate
     * @param y  the spawn Tile's y coordinate
     */
    public Entity(int id, int x, int y) {
        this.id = id;
        this.pos = new Position(x, y);
    }

    /**
     * Creates an Entity with id at pos.
     *
     * @param id  the "unique" Entity id
     * @param pos the spawn Tile's Position
     */
    public Entity(int id, Position pos) {
        this.id = id;
        this.pos = pos;
    }

    /**
     * Updates the Entity's Position.
     *
     * @param pos the new position
     */
    public void setPosition(Position pos) {
        this.pos = pos;
    }

    /**
     * Gets the Entity's id.
     *
     * @return the id
     */
    public int getID() {
        return this.id;
    }

    /**
     * Returns the Entity's Position.
     *
     * @return the position
     */
    public Position getPosition() {
        return this.pos;
    }
}
