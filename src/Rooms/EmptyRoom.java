package Rooms;

import People.Person;

public class EmptyRoom extends Room {
    public EmptyRoom(int x, int y) {
        super(x, y);
    }

    /**
     * The outcome after the room is entered.
     * @param p the player who entered the room
     */
    @Override
    public void enterRoom(Person p) {
        this.occupant = p;
        p.setxLoc(this.xLoc);
        p.setyLoc(this.yLoc);
        System.out.println("You entered the empty room.");
    }
}