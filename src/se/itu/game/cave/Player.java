package se.itu.game.cave;

import se.itu.game.cave.exceptions.IllegalMoveException;
import se.itu.game.cave.exceptions.RuleViolationException;
import se.itu.game.cave.init.CaveInitializer;
import se.itu.game.cave.init.Things;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by andreas on 18/04/17.
 */
public class Player {

    private List<Thing> inventory;
    private Room currentRoom;

    private static Player player;

    private Player(Room room) {
        inventory = new ArrayList<>();
        currentRoom = room;
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player(CaveInitializer.getInstance().getFirstRoom());
        }
        return player;
    }


    public void takeThing(Thing thing) throws RuleViolationException {
        RuleBook.getRuleFor(thing).apply(); // Can throw RuleViolationException
        Thing takenThing = currentRoom.removeThing(thing);
        inventory.add(takenThing);
    }

    public void dropThing(Thing thing) {
        if (!inventory.contains(thing)|| thing == null) {
            throw new IllegalArgumentException("Thing is not present in the inventory");
        }
        currentRoom.putThing(thing);
        inventory.remove(thing);
    }

    public List<Thing> inventory() {
        return Collections.unmodifiableList(inventory);
    }

    public boolean hasAllKeys() {
        return     inventory.contains(Things.get(RuleBook.GLASS_KEY))
                && inventory.contains(Things.get(RuleBook.RUSTY_KEY))
                && inventory.contains(Things.get(RuleBook.BRASS_KEY))
                && inventory.contains(Things.get(RuleBook.SKELETON_KEY));
    }

    public Room currentRoom() {
        return currentRoom;
    }

    public String describeCurrentRoom() {
        return currentRoom.description();
    }

    public List<Thing> thingsInCurrentRoom() {
        return currentRoom.things();
    }

    public boolean canSeeDoorIn(Room.Direction direction) {
        return currentRoom.getConnectingRoom(direction) != null;
    }

    /**
     * Moves the player in given direction
     * @param direction The direction in which to move
     * @throws IllegalMoveException - if there is not Room in the given direction
     */
    public void go(Room.Direction direction) throws IllegalMoveException {
        Room connectingRoom = currentRoom().getConnectingRoom(direction);
        if (connectingRoom == null) {
            throw new IllegalMoveException("No room in " + direction + " direction");
        }
        currentRoom = connectingRoom;
    }

    @Override
    public String toString() {
        return currentRoom() + "\nPlayer's inventory: " + inventory();
    }

}
