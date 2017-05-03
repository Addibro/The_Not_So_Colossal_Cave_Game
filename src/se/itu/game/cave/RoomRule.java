package se.itu.game.cave;

public abstract class RoomRule {
    protected String creatureDescription;
    protected Room room;

    public RoomRule(Room room, String creatureDescription) {
        this.creatureDescription = creatureDescription;
        this.room = room;
    }

    public abstract void apply();

    public String getCreatureDescription() {
        return creatureDescription;
    }

    public void changeCreatureDescription(String description) {
        this.creatureDescription = description;
    }
}
