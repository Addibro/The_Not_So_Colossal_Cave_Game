package se.itu.game.cave.interfaces;

import se.itu.game.cave.exceptions.RuleViolationException;

public interface ThingRule {
    boolean apply() throws RuleViolationException;
}
