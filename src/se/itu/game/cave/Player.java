package se.itu.game.cave;

import se.itu.game.cave.exceptions.IllegalMoveException;
import se.itu.game.cave.exceptions.RuleViolationException;
import se.itu.game.cave.init.CaveInitializer;
import se.itu.game.cave.init.Things;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Singleton class for the player functionality of the game.
 * Can navigate through the cave, pick up and drop items.
 */
public class Player {

    private List<Thing> inventory;
    private Room currentRoom;

    private static Player player;

    private Player(Room room) {
        inventory = new ArrayList<>();
        currentRoom = room;
    }

    /**
     * Returns the only instance of Player. Initializes the Player
     * with the first room in the cave as argument.
     * @return the only instance of Player
     */
    public static Player getInstance() {
        if (player == null) {
            player = new Player(CaveInitializer.getInstance().getFirstRoom());
        }
        return player;
    }


    /**
     * Takes a thing from a room. Checks rules for the given thing, removes the thing from the current room
     * and adds it to Player's inventory. Also checks for any room rule when
     * a thing is taken
     * @param thing the thing Player is about to take
     * @throws RuleViolationException if the Thing can't be picked up
     */
    public void takeThing(Thing thing) throws RuleViolationException {
        RuleBook.getRuleFor(thing).apply(); // Can throw RuleViolationException
        Thing takenThing = currentRoom.removeThing(thing);
        inventory.add(takenThing);
        RuleBook.getRuleFor(currentRoom()).apply();
    }

    /**
     * Drops a thing to the current room and removing it from Player's inventory.
     * Also checks for any room rule when a thing is dropped.
     * @param thing the thing to be dropped
     * @throws IllegalArgumentException if thing is not present in inventory or null
     */
    public void dropThing(Thing thing) throws IllegalArgumentException {
        if (!inventory.contains(thing)|| thing == null) {
            throw new IllegalArgumentException("Thing is not present in inventory");
        }
        currentRoom.putThing(thing);
        inventory.remove(thing);
        RuleBook.getRuleFor(currentRoom()).apply();
    }

    /**
     * Returns a list of Player's things
     * @return an unmodifiable list of things Player's things
     */
    public List<Thing> inventory() {
        return Collections.unmodifiableList(inventory);
    }

    /**
     * Returns true if Player's inventory contains all the required keys
     * needed to pick up the Pirate Chest.
     * @return true if Player has all the necessary keys for the pirate chest
     */
    public boolean hasAllKeys() {
        return     inventory.contains(Things.get(RuleBook.GLASS_KEY))
                && inventory.contains(Things.get(RuleBook.RUSTY_KEY))
                && inventory.contains(Things.get(RuleBook.BRASS_KEY))
                && inventory.contains(Things.get(RuleBook.SKELETON_KEY));
    }

    /**
     * Returns the current room in which the Player is present
     * @return the current room
     */
    public Room currentRoom() {
        return currentRoom;
    }

    /**
     * Returns the current room description. Also checks for rule text
     * to be concatenated, if one is present.
     * @return a description of the current room
     */
    public String describeCurrentRoom() {
        RoomRule roomRule = RuleBook.getRuleFor(currentRoom);
        String creatureDescription = roomRule.getCreatureDescription();
        return currentRoom.description() + "\n\n" + creatureDescription;
    }

    /**
     * Returns the things in the current room
     * @return the things in the current room
     */
    public List<Thing> thingsInCurrentRoom() {
        return currentRoom.things();
    }

    /**
     * Returns true if the connecting room in the given direction is not null.
     * @param direction the direction to look at
     * @return true if the direction is not null
     */
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

    /**
     * Returns a string representation of Player
     * @return a string representation of Player
     */
    @Override
    public String toString() {
        return currentRoom() + "\nPlayer's inventory: " + inventory();
    }

}
