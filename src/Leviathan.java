/**
 * Representation of the Leviathan monster.
 *
 * @author Edmund Wu
 */
public class Leviathan extends Entity implements Monster {
    private int stamina;

    /**
     * Creates a Leviathan with id at (x, y).
     *
     * @param id the "unique" id of the Entity
     * @param x  the x coordinate where the Leviathan spawns
     * @param y  the y coordinate where the Leviathan spawns
     */
    public Leviathan(int id, int x, int y) {
        super(id, new Position(x, y));
        this.stamina = Constants.MAX_STAMINA;
    }

    /**
     * Creates a Leviathan with id at pos.
     *
     * @param id  the "unique" id of the Entity
     * @param pos the position where the Leviathan spawns
     */
    public Leviathan(int id, Position pos) {
        super(id, pos);
        this.stamina = Constants.MAX_STAMINA;
    }

    /**
     * {@inheritDoc}
     */
    public void rest() {
        this.stamina = Constants.MAX_STAMINA;
    }

    /**
     * Returns the Leviathan's current stamina.
     *
     * @return the stamina represented as an integer
     */
    public int getStamina() {
        return this.stamina;
    }
}
