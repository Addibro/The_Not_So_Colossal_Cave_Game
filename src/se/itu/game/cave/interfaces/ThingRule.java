package se.itu.game.cave.interfaces;

import se.itu.game.cave.exceptions.RuleViolationException;

/**
 * An abstract idea of a rule for a Thing in the game.
 * Will be defined in initiation of the game and executed
 * during the game play
 * @see se.itu.game.cave.RuleBook
 */
public interface ThingRule {
    /**
     * Returns true if no rule if present to the given thing,
     * otherwise it will throw an exception
     * @return true or throws an exception
     * @throws RuleViolationException if the condition(s) of the rule isn't met
     */
    boolean apply() throws RuleViolationException;
}
