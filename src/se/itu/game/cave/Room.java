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

    /**
     * Constructor specifying the description of this room and all the connecting rooms of this room
     * and the list of things in this room.
     * @param description the room description
     * @param north the room to the north
     * @param east the room to the east
     * @param south the room to the south
     * @param west the room to the west
     * @param things the list of things in the room
     * @throws NullPointerException if description or things is null
     */
    public Room(String description, Room north, Room east, Room south, Room west, List<Thing> things) {
        if (description == null || things == null) {
            throw new NullPointerException("Things or description can't be null!");
        }
        this.description = description;
        this.north       = north;
        this.east        = east;
        this.south       = south;
        this.west        = west;
        this.things      = things;
    }

    /**
     * Sets a given Room to a given direction.
     * @param direction The direction to Room
     * @param room The Room in direction
     * @throws IllegalArgumentException if direction is incorrect
     */
    public void setConnectingRoom(Direction direction, Room room) throws IllegalArgumentException {
        switch (direction) {
            case NORTH: north = room; break;
            case EAST:  east  = room; break;
            case SOUTH: south = room; break;
            case WEST:  west  = room; break;
            default:
                throw new IllegalArgumentException("Direction not correct, can't happen");
        }
    }

    /**
     * Returns a reference to an unmodifiable version of the list og Things.
     * @return an unmodifiable version of the list of THings in this room.
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
            throw new IllegalArgumentException(thing + " is not in this room");
        }
    }


    /**
     * Add a Thing to the Room's list of things.
     * @param thing The Thing to add
     * @throws IllegalArgumentException if Player inventory already contains Thing
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
     * Returns the connecting Room in the given direction
     * @param direction The direction of the Room we want.
     * @return connecting Room in the given direction.
     * @throws IllegalArgumentException if the given direction doesn't match any direction
     */
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

    /**
     * Returns the room description
     * @return the description of the room
     */
    public String description() {
        return description;
    }

    /**
     * Returns a string representation of the room
     * @return a string with the room information
     */
    @Override
    public String toString() {
        return "Description: " + description + "\nThings: " + things;
    }

    /**
     * Enum class of the different directions that are used in the game
     */
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}
