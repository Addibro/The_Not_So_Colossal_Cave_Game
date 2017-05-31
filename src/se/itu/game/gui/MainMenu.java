package se.itu.game.gui;

//import se.itu.game.cave.init.HighScoreUtil;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


/**
 * This class represents the main menu of the game and is a singleton.
 * It extends JFrame and acts as a mediator between the gamer and the game.
 *
 */
public class MainMenu extends JFrame {

    // Buttons used
    private JButton start = new JButton("Start Game");
    private JButton quit = new JButton("Quit");

    // Background image
    private JLabel imageLabel = new JLabel(new ImageIcon("src/media/CaveGame.png"));

    // GridBagConstraint
    private GridBagConstraints gbc = new GridBagConstraints();

    // Reference variable for MainMenu
    private static MainMenu mainMenuFrame;

    //    ActionListener showHighScores = e -> JOptionPane.showMessageDialog(mainMenuFrame, HighScoreUtil.getHighScore(), "High Score", JOptionPane.INFORMATION_MESSAGE);


    /**
     * Returns the main menu frame. Instantiates the variable if null.
     * @return the frame for the main menu
     */
    public static MainMenu getInstance() {
        if (mainMenuFrame == null) {
            mainMenuFrame = new MainMenu();
        }
        return mainMenuFrame;
    }

    /**
     * Calls the getInstance() method on the MainMenu class
     * which initializes the game.
     */
    public static void run() {
        MainMenu.getInstance();
    }

    private MainMenu() {
        setTitle("The Not So Colossal Cave");
        setPreferredSize(new Dimension(710, 550));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // create and add ActionListeners to buttons
        ActionListener startGame = e -> {
            setVisible(false);
            new GameFrame();
            Timer.start();
        };
        ActionListener quitGame = e -> System.exit(0);

        start.addActionListener(startGame);
        quit.addActionListener(quitGame);
//        highScore.addActionListener(showHighScores);

        // Layout the buttons
        imageLabel.setLayout(new GridBagLayout());
        addButtonsToContainer(imageLabel);
        add(imageLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            File musicFile = new File("src/media/The Cave.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicFile);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Error obtaining sound: " + ex.getMessage());
        }
    }

    private void addButtonsToContainer(Container container) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.weightx = 1;
        container.add(start, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.weightx = 1;
//        container.add(highScore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 30;
        gbc.ipadx = 40;
        gbc.weightx = 1;
        container.add(quit, gbc);
    }
}

