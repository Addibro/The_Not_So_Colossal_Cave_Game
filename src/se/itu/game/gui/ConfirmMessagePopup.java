package se.itu.game.gui;

import se.itu.game.cave.interfaces.IPopup;

import javax.swing.*;

public class ConfirmMessagePopup implements IPopup<Integer> {
    private String message;
    public ConfirmMessagePopup(String message) {
        this.message = message;
    }

    @Override
    public Integer popup() {
        return JOptionPane.showConfirmDialog(MainMenu.getInstance(), message);
    }
}
