package se.itu.game.cave;

public abstract class RoomRule {

    /**
     * A room rule has a creatureDescription which describe the event
     * and a room which is the room the rule applies to
     */
    protected String creatureDescription;
    protected Room room;

    /**
     * Class constructor specifying the room
     * and the description of it. The description describes
     * the event of the rule.
     *
     * @param room the room in which the rule will happen
     * @param creatureDescription the description of the event
     */
    public RoomRule(Room room, String creatureDescription) {
        this.creatureDescription = creatureDescription;
        this.room = room;
    }

    /**
     * Abstract method apply for apply the given room rule. This method is called whenever
     * a rule is to be applied to a room
     */
    public abstract void apply();

    /**
     * Returns a string with text about the rule and the event describing it
     * @return the description of the event of the rule
     */
    public String getCreatureDescription() {
//        apply(); // works without this (?!)
        return creatureDescription;
    }

    /**
     * Sets the current rule description to a new
     * description.
     * @param description the new description of the rule
     */
    public void changeCreatureDescription(String description) {
        this.creatureDescription = description;
    }
}
