package se.itu.game.gui;

import se.itu.game.cave.interfaces.IPopup;

import javax.swing.*;

public class InputMessagePopup implements IPopup<String> {
    private String message;

    public InputMessagePopup(String message) {
        this.message = message;
    }

    @Override
    public String popup() {
        return JOptionPane.showInputDialog(MainMenu.getInstance(), message);
    }
}
