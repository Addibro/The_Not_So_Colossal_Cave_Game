package se.itu.game.cave;

public interface ThingRule {
    boolean apply() throws RuleViolationException;
}
