package se.itu.game.cave;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
/**
 * Created by andreas on 18/04/17.
 */
public class Room {

    private String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private List<Thing> things;

    public Room(String description, Room north, Room east, Room south, Room west, List<Thing> things) {
        if (description == null || things == null) {
            throw new NullPointerException("Things or description can't be null!");
        }
        this.description = description;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.things = things;
    }

    /**
     * @param direction The direction to Room
     * @param room The Room in direction
     */
    public void setConnectingRoom(Direction direction, Room room) {
        switch (direction) {
            case NORTH:north = room;
            break;
            case EAST: east = room;
            break;
            case SOUTH: south = room;
            break;
            case WEST: west = room;
            break;
            default:
                throw new IllegalArgumentException("Direction not correct, can't happen");
        }
    }

    /**
     * Returns a reference to an unmodifiable version of the list og Things.
     * @return an umnmodifiable version of the list of THings in this room.
     */
    public List<Thing> things() {
        return Collections.unmodifiableList(this.things);
    }


    /**
     *
     * @param thing The Thing to remove
     * @return the Thing removed
     * @throws NullPointerException - if thing is null
     * @throws IllegalArgumentException - if the Thing to remove is not present in the Room
     */
    public Thing removeThing(Thing thing) {
        if (thing == null) {
            throw new NullPointerException("Thing can't be null biatch");
        } else if (things.contains(thing)) {
            things.remove(thing);
            return thing;
        } else {
            throw new IllegalArgumentException("Thing is not in this room");
        }
    }


    /**
     * Add a Thing to the Room. For convenience this method returns
     * @param thing The Thing to add
     */
    public void putThing(Thing thing) {
        if (thing == null) {
            throw new NullPointerException("Thing ca'nt be null goddammit");
        } else if (things.contains(thing)) {
            throw new IllegalArgumentException("The Thing passed as argument already exists in this Room!");
        } else {
            things.add(thing);
        }
    }

    /**
     * Returns the connceting Room in the given direction
     * @param direction The direction of the Room we want.
     * @return connecting Room in the given direction.
     */
    public Room getRoom(Direction direction) {
        switch (direction) {
            case NORTH: return north;
            case EAST: return east;
            case SOUTH: return south;
            case WEST: return west;
            default:
                throw new IllegalArgumentException("This is not a direction");
        }
    }

    public Room getConnectingRoom(Room.Direction direction) {
        switch(direction) {
            case NORTH: return north;
            case EAST: return east;
            case SOUTH: return south;
            case WEST: return west;
            default:
                throw new IllegalArgumentException("No valid direction");
        }
    }

    public String description() {
        return description;
    }

    @Override
    public String toString() {
        return "Description: " + description + "\nThings: " + things;
    }

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}
