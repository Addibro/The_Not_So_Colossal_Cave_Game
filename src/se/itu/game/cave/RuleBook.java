package se.itu.game.cave;

import se.itu.game.cave.interfaces.ThingRule;

import java.util.HashMap;
import java.util.Map;

public class RuleBook {

    public static final String BIRD = "Bird";
    public static final String PIRATE_CHEST = "Pirate Chest";
    public static final String CAGE = "Cage";
    public static final String ROD = "Rod";
    public static final String GLASS_KEY = "Glass Key";
    public static final String RUSTY_KEY = "Rusty Key";
    public static final String BRASS_KEY = "BRASS_KEY";
    public static final String SKELETON_KEY = "Skeleton Key";

    private static Map<Thing, ThingRule> rules = new HashMap<>();

    public static void addThingRule(Thing thing, ThingRule rule) {
        rules.put(thing, rule); // The rule is of ThingRule type
                                // i.e an interface and will implement
                                // the apply(): boolean for the given Thing
                                // (either true or throw new RuleViolationException)
    }

    public static ThingRule getRuleFor(Thing thing) {
        ThingRule rule = rules.get(thing); // Give us the rule for the Thing (if any)
        if (rule == null) { // If the Thing didn't have a ThingRule (null)
            rule = () -> true; // We set the rule to true (i.e you're good to go and pick it up)
        }
        return rule;
    }
}
