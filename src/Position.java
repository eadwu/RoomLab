/**
 * Represents a position on the board in the form of coordinates.
 *
 * @author Edmund Wu
 */
public class Position {
    private int x;
    private int y;

    /**
     * Creates a position at coordinates (x, y).
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the position's x coordinate.
     *
     * @return x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the position's y coordinate.
     *
     * @return y coordinate
     */
    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return '[' + String.valueOf(this.x) + ',' + String.valueOf(this.y) + ']';
    }
}
