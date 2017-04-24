package se.itu.game.cave;

import java.util.Map;

public class RuleBook {
    private static Map<Thing, ThingRule> rules;

    public static void addThingRule(Thing thing, ThingRule rule) {
        rules.put(thing, rule); // The rule if of ThingRule type i.e an interface and will implement the apply(): boolean for each Thing
    }

    public static ThingRule getRuleFor(Thing thing) {
        ThingRule rule = rules.get(thing); // Give us the rule for the Thing
        if (rule == null) { // If the Thing didn't have a ThingRule (null)
            rule = () -> true; // We set the rule to true (i.e no you're good to go and pick it up)
        }
        return rule;
    }
}
