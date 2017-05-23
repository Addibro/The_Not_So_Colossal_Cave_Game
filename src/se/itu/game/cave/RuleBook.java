package se.itu.game.cave;

import se.itu.game.cave.interfaces.ThingRule;

import java.util.HashMap;
import java.util.Map;

public class RuleBook {

    // Thing constants
    public static final String BIRD = "Bird";
    public static final String PIRATE_CHEST = "Pirate Chest";
    public static final String CAGE = "Cage";
    public static final String ROD = "Rod";
    public static final String GLASS_KEY = "Glass Key";
    public static final String RUSTY_KEY = "Rusty Key";
    public static final String BRASS_KEY = "Brass Key";
    public static final String SKELETON_KEY = "Skeleton Key";
    public static final String GOLD = "Gold";
    public static final String SILVER = "Silver";
    public static final String DIAMONDS = "Diamonds";
    public static final String JEWELRY = "Jewelry";

    // Room constants
    public static final int DRAGON_ROOM = 120;
    public static final int SNAKE_ROOM = 19;
    public static final int SNAKE_SOUTH_ROOM = 29;
    public static final int PIRATE_CHEST_ROOM = 250;

    private static Map<Thing, ThingRule> rules = new HashMap<>();
    private static Map<Room, RoomRule> roomRules = new HashMap<>();

    /**
     * Maps rules to certain Things. The rule is an interface
     * which can be applied anonymously.
     * @param thing the object which the rule is to be applied to
     * @param rule the thing rule which is to be applied
     * @see ThingRule
     * @see RuleBook
     */
    public static void addThingRule(Thing thing, ThingRule rule) {
        rules.put(thing, rule); // The rule is of ThingRule type
                                // i.e an interface and will implement
                                // the apply(): boolean for the given Thing
                                // (either true or throw new RuleViolationException)
    }

    /**
     * Maps rules to certain Rooms. The rule is abstract, so can be
     * applied anonymously.
     * @param room the room object which the rule is to be applied to
     * @param roomRule the room rule which is to be applied
     * @see RoomRule
     * @see RuleBook
     */
    public static void addRoomRule(Room room, RoomRule roomRule) {
        roomRules.put(room, roomRule);
    }

    /**
     * Returns a rule for a given thing. If no rule is found
     * it returns a rule set to true.
     * @param thing the thing to checked for rules
     * @return the rule for the given thing
     * @see ThingRule
     */
    public static ThingRule getRuleFor(Thing thing) {
        ThingRule rule = rules.get(thing); // Give us the rule for the Thing (if any)
        if (rule == null) {     // If the Thing didn't have a ThingRule (null)
            rule = () -> true;  // We set the rule to true (i.e you're good to go and pick it up)
        }
        return rule;
    }

    /**
     * Returns a rule for a given room. If no rule is found
     * it returns an "empty" rule, meaning it doesn't contain
     * any room or description.
     * @param room the room to be checked for rules
     * @return the room rule for the given room
     * @see RoomRule
     */
    public static RoomRule getRuleFor(Room room) {
        RoomRule roomRule = roomRules.get(room);
        if (roomRule == null) {
            return new RoomRule(null, "") {
                @Override
                public void apply() {
                    // No rule
                }
            };
        }
        return roomRule;
    }
}