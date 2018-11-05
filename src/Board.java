import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A representation of a game board.
 *
 * @author Edmund Wu
 */
public class Board {
    private int width;
    private int height;
    private Tile[][] internals;

    /**
     * Creates a Board class with a row x col board.
     *
     * @param rows the amount of rows the board should have
     * @param cols the amount of columns the board should have
     */
    public Board(int rows, int cols) {
        this.width = cols;
        this.height = rows;
        this.internals = new Tile[rows][cols];
        this.fill('#');
    }

    /**
     * Creates a Board class with a row x col board with the Tiles' content as letter.
     *
     * @param rows   the amount of rows the board should have
     * @param cols   the amount of columns the board should have
     * @param letter the default character each Tile should have
     */
    public Board(int rows, int cols, char letter) {
        this.width = cols;
        this.height = rows;
        this.internals = new Tile[rows][cols];
        this.fill(letter);
    }

    /**
     * Creates a Board class with a n x n board.
     *
     * @param n the length of one side of the board
     */
    public Board(int n) {
        this.width = n;
        this.height = n;
        this.internals = new Tile[n][n];
        this.fill('#');
    }

    /**
     * Creates a Board class with a n x n board with the Tiles' content as letter.
     *
     * @param n      the length of one side of the board
     * @param letter the content to put into the Tile
     */
    public Board(int n, char letter) {
        this.width = n;
        this.height = n;
        this.internals = new Tile[n][n];
        this.fill(letter);
    }

    /**
     * Fills the Board Tiles with a certain letter.
     *
     * @param letter the letter to put on each Tile
     */
    private void fill(char letter) {
        IntStream.range(0, this.internals.length).forEach(row -> {
            IntStream.range(0, this.internals[row].length).forEach(col -> {
                this.internals[row][col] = new Tile(row, col, letter);
            });
        });
    }

    /**
     * Returns the width of the board.
     *
     * @return an integer representing the width of the board
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the board.
     *
     * @return an integer representing the height of the board
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the Tile given the coordinates.
     *
     * @param x the x coordinate the tile is located
     * @param y the y coordinate the tile is located
     * @return the tile located at (x, y)
     */
    public Tile tileAt(int x, int y) {
        return this.internals[y][x];
    }

    /**
     * Returns the Tile given its position.
     *
     * @param pos the position the tile is located
     * @return the tile located at pos
     */
    public Tile tileAt(Position pos) {
        return this.internals[pos.getY()][pos.getX()];
    }

    /**
     * Normalizes Cartesian coordinates based on board dimensions.
     *
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     * @return the normalized Position
     */
    public static Position normalize(int x, int y) {
        int normalizedX = x < Constants.WIDTH && x > -1 ? x : x > Constants.WIDTH ? x - Constants.WIDTH : Constants.WIDTH + x;
        int normalizedY = y < Constants.HEIGHT && y > -1 ? y : y > Constants.HEIGHT ? y - Constants.HEIGHT : Constants.HEIGHT + y;

        return new Position(normalizedX, normalizedY);
    }

    public Tile[] tilesAround(int x, int y) {
        return this.tilesAround(this.tileAt(y, x));
    }

    /**
     * Calculates distance between two Cartesian coordinates using its Manhattan distance.
     *
     * @param a the source position
     * @param b the destination position
     * @return the Manhattan distance of the two coordinates
     */
    public static double calculateDistance(Position a, Position b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());

        return dx + dy;
    }

    /**
     * Returns the immediate tiles in the cardinal directions of the source tile.
     *
     * @param source the source node/tile to get the neighbors of
     * @return the immediate neighbors of the tile
     */
    public Tile[] tilesAround(Tile source) {
        int x = source.getPosition().getX();
        int y = source.getPosition().getY();

        Tile[] tiles = {
                this.tileAt(Board.normalize(x - 1, y)), this.tileAt(Board.normalize(x + 1, y)),
                this.tileAt(Board.normalize(x, y - 1)), this.tileAt(Board.normalize(x, y + 1))
        };

        return tiles;
    }

    /**
     * A simple pathfinding approach using Manhattan distance.
     *
     * @param source      the start node
     * @param destination the end node
     * @return the projected next tile
     */
    public Tile naiveNavigate(Tile source, Tile destination) {
        Tile[] neighbors = this.tilesAround(source);

        Arrays.sort(neighbors, (Tile a, Tile b) -> (int) (Board.calculateDistance(a.getPosition(), destination.getPosition()) -
                Board.calculateDistance(b.getPosition(), destination.getPosition())));

        return neighbors[0];
    }

    /**
     * Performs the same as naiveNavigate(Tile, Tile), except Tiles are found through Positions.
     *
     * @param source      the start position
     * @param destination the end position
     * @return the projected next tile
     */
    public Tile naiveNavigate(Position source, Position destination) {
        return this.naiveNavigate(this.tileAt(source), this.tileAt(destination));
    }

    /**
     * Performs the same as naiveNavigate(Tile, Tile) except positions are given through explicit coordinates.
     *
     * @param x0 the start node's x value
     * @param y0 the start node's y value
     * @param x1 the end node's x value
     * @param y1 the end node's y value
     */
    public Tile naiveNavigate(int x0, int y0, int x1, int y1) {
        return this.naiveNavigate(this.tileAt(x0, y0), this.tileAt(x1, y1));
    }

    /**
     * Moves an Entity to (x, y).
     *
     * @param row     the x coordinate of the target Position
     * @param col     the y coordinate of the target Position
     * @param content the symbol that represents the Entity
     * @param entity  the Entity
     */
    public void move(int row, int col, char content, Entity entity) {
        Position currentPos = entity.getPosition();
        Position newPos = Board.normalize(row, col);

        entity.setPosition(newPos);
        this.tileAt(currentPos).setContent(' ');
        this.tileAt(currentPos).markOccupied(null);
        this.tileAt(newPos).setContent(content);
        this.tileAt(newPos).markOccupied(entity);
    }

    /**
     * Moves an Entity to pos.
     *
     * @param pos     the target Position
     * @param content the symbol that represents the Entity
     * @param entity  the Entity
     */
    public void move(Position pos, char content, Entity entity) {
        this.move(pos.getX(), pos.getY(), content, entity);
    }

    /**
     * Moves an Entity given a direction.
     *
     * @param direction the direction to move the Entity
     * @param content   the symbol that represents the Entity
     * @param entity    the Entity
     */
    public void move(String direction, char content, Entity entity) {
        int dx = 0;
        int dy = 0;

        Position currentPos = entity.getPosition();

        switch (direction) {
            case "N":
                dy = -1;
                break;
            case "S":
                dy = 1;
                break;
            case "E":
                dx = 1;
                break;
            default:
                dx = -1;
        }

        this.move(currentPos.getX() + dx, currentPos.getY() + dy, content, entity);
    }

    /**
     * Edits the character on a Tile in the Board given its coordinates.
     *
     * @param row     the x position of the Tile
     * @param col     the y position of the Tile
     * @param content the content the Tile should have
     */
    public void edit(int row, int col, char content) {
        this.internals[row][col].setContent(content);
    }

    /**
     * Edits the character on a Tile in the board given its position.
     *
     * @param pos     the position of the Tile
     * @param content the content the Tile should have
     */
    public void edit(Position pos, char content) {
        this.internals[pos.getX()][pos.getY()].setContent(content);
    }

    /**
     * Prints out a "flattened" board generated from internals.
     */
    public void print() {
        System.out.println(Arrays.stream(this.internals)
                .map(row -> Arrays.stream(row).map(Tile::toString).collect(Collectors.joining()))
                .collect(Collectors.joining("\n")));
    }
}
