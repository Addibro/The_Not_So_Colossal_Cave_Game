package se.itu.game.gui;

import se.itu.game.cave.interfaces.IPopup;

import javax.swing.*;

public class MessagePopup implements IPopup<Object> {

    private String message;

    public MessagePopup(String message) {
        this.message = message;
    }

    @Override
    public Object popup() {
        JOptionPane.showMessageDialog(MainMenu.getInstance(), message);
        return null;
    }
}
