import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Runner for the game.
 *
 * @author Edmund Wu
 */
public class Runner {
    public static void main(String[] args) {
        int spawnX = (int) (Math.random() * Constants.WIDTH);
        int spawnY = (int) (Math.random() * Constants.HEIGHT);
        int mSpawnX = spawnX;
        int mSpawnY = spawnY;
        int endX = spawnX;
        int endY = spawnY;

        while (endX == spawnX && endY == spawnY) {
            endX = (int) (Math.random() * Constants.WIDTH);
            endY = (int) (Math.random() * Constants.HEIGHT);
        }

        while (mSpawnX == spawnX && mSpawnY == spawnY) {
            mSpawnX = (int) (Math.random() * Constants.WIDTH);
            mSpawnY = (int) (Math.random() * Constants.HEIGHT);
        }

        // TODO: Might be better to abstract stdin communication
        Scanner stream = new Scanner(System.in);

        System.out.println("What is your name?");
        String name = stream.nextLine().trim();

        Position spawnPos = new Position(spawnX, spawnY);
        Position endPos = new Position(endX, endY);
        Player p = new Player(0, spawnPos, name);
        Leviathan levi = new Leviathan(1, mSpawnX, mSpawnY);
        Board board = new Board(Constants.WIDTH, Constants.HEIGHT);

        System.out.println(spawnPos.toString());
        board.move(spawnPos, 'o', p);
        board.move(mSpawnX, mSpawnY, '|', levi);

        System.out.printf("Hi there, %s, your goal is to reach %s without getting caught by the Leviathan.\n%s\n",
                p.toString(),
                endPos.toString(),
                "Coordinates are representing from the top left (0,0) to bottom right, good luck!");

        while (p.getPosition().getX() != endPos.getX() || p.getPosition().getY() != endPos.getY()) {
            board.print();

            System.out.printf("%s\n%s\n%s\n",
                    "Your current location is " + p.getPosition().toString() + ".",
                    "Which direction do you wish to move in?",
                    "N, S, E, or W are the possible choices, case insensitive.");
            String direction = stream.nextLine().trim().toUpperCase();

            try {
                if (!direction.equals("N") &&
                        !direction.equals("S") &&
                        !direction.equals("E") &&
                        !direction.equals("W"))
                    throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println("Failed to get a valid cardinal direction, exiting...");
                System.exit(1);
            }

            board.move(direction, 'o', p);
            if (levi.getStamina() <= 0) {
                levi.rest();
            } else {
                Tile targetTile = board.naiveNavigate(levi.getPosition(), p.getPosition());
                levi.setStamina(-1);
                board.move(targetTile.getPosition(), '|', levi);
            }

            // TODO: Probably better somewhere else
            if (p.getPosition().getX() == levi.getPosition().getX() && p.getPosition().getY() == levi.getPosition().getY())
                break;
        }

        if (p.getPosition().getX() == endPos.getX() && p.getPosition().getY() == endPos.getY()) {
            System.out.println("Congrats, you have won.");
        } else {
            System.out.println("You have been caught by the Leviathan.");
        }
        stream.close();
    }
}
