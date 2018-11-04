/**
 * A representation of a tile in a board.
 *
 * @author Edmund Wu
 */
public class Tile {
    private Position pos;
    private Entity entity;
    private char content;

    /**
     * Creates a Tile class with coordinates (x, y) and content.
     *
     * @param x       the x coordinate the tile is
     * @param y       the y coordinate the tile is
     * @param content what the tile should show
     */
    public Tile(int x, int y, char content) {
        this.pos = new Position(x, y);
        this.content = content;
    }

    /**
     * Creates a Tile class at position pos with content.
     *
     * @param pos     the position of the tile
     * @param content what the tile should show
     */
    public Tile(Position pos, char content) {
        this.pos = pos;
        this.content = content;
    }

    /**
     * Marks the Tile as occupied.
     *
     * @param entity the Entity that is occupying the Tile
     */
    public void markOccupied(Entity entity) {
        this.entity = entity;
    }

    /**
     * Returns whether or not the Tile has an Entity on it.
     *
     * @return true if Entity exists on Tile
     */
    public boolean isOccupied() {
        return this.entity != null;
    }

    /**
     * Gets the Position of the Tile.
     *
     * @return the Tile's Position
     */
    public Position getPosition() {
        return this.pos;
    }

    /**
     * Returns the Tile's content.
     *
     * @return the content of the Tile
     */
    public char getContent() {
        return this.content;
    }

    /**
     * Sets the content of the Tile.
     *
     * @param content what the Tile should show
     */
    public void setContent(char content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.valueOf(this.content);
    }
}
