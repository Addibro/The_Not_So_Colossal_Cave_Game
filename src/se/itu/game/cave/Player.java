package se.itu.game.cave;

import se.itu.game.cave.init.CaveInitializer;

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


    public void takeThing(Thing thing) {
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

    public Room currentRoom() {
        return currentRoom;
    }

    public void go(Room.Direction direction) {
        Room connectingRoom = currentRoom().getRoom(direction);
        if (connectingRoom == null) {
            throw new IllegalArgumentException("No room in that direction");
        }
        currentRoom = connectingRoom;
    }

    @Override
    public String toString() {
        return currentRoom() + "\nPlayer's inventory: " + inventory();
    }

}
