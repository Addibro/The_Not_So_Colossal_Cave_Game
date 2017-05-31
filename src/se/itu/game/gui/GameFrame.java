package se.itu.game.gui;

import se.itu.game.cave.Player;
import se.itu.game.cave.Room;
import se.itu.game.cave.Thing;
import se.itu.game.cave.exceptions.IllegalMoveException;
import se.itu.game.cave.exceptions.RuleViolationException;
import se.itu.game.cave.init.CaveInitializer;
import se.itu.game.cave.interfaces.IPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

/**
 * Functions as the interctive frame for the player. Has
 * all the nessecary components for the game to function, such as navigation buttons,
 * pick up and drop buttons, text fields for room description and also lists
 * for things in the current room and in the player's inventory
 */
public class GameFrame extends javax.swing.JFrame implements KeyListener {

    static {
        try {
            // Ignore this - it's a fix for Rikard's computer. Hell Dell!
            UIManager.setLookAndFeel((LookAndFeel)Class
                    .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                    .newInstance());
        } catch (Exception ignore) {}
    }

    /**
     * Not used.
     * @param e the key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    /**
     * Listens to any key pressed on keyboard
     * and processes the input given, though it only reacts to the arrow keys on the keyboard.
     * All other key input is simply consumed.
     *
     * @param e the KeyEvent object which is to be processed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP: handleKeyInput(Room.Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:handleKeyInput(Room.Direction.SOUTH);
                break;
            case KeyEvent.VK_RIGHT:handleKeyInput(Room.Direction.EAST);
                break;
            case KeyEvent.VK_LEFT:handleKeyInput(Room.Direction.WEST);
                break;
            default:
                e.consume();
        }
    }

    /**
     * Not used.
     * @param e the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();
    }

    private void handleKeyInput(Room.Direction dir) {
        try {
            player.go(dir);
            updateGui();
        } catch (IllegalMoveException e) {
            System.out.println("Bad direction - shouldn't happen.");
        }
    }

    /**
     * Creates a new GameFram that initializes its components
     * and lays them out. This is the frame with which the
     * user will interact.
     */
    public GameFrame() {
        initComponents();
        layoutComponents();
    }

    private void layoutComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Not So Colossal Cave");
        setBounds(new java.awt.Rectangle(0, 23, 710, 550));
        setPreferredSize(new java.awt.Dimension(710, 550));
        setSize(new java.awt.Dimension(710, 550));
        getContentPane().setLayout(null);

        inventoryLabel.setFont(new java.awt.Font("Luminari", 0, 14)); // NOI18N
        inventoryLabel.setForeground(new java.awt.Color(255, 255, 255));
        inventoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inventoryLabel.setText("Inventory");
        getContentPane().add(inventoryLabel);
        inventoryLabel.setBounds(500, 20, 90, 40);

        thingsLabel.setFont(new java.awt.Font("Luminari", 0, 14)); // NOI18N
        thingsLabel.setForeground(new java.awt.Color(255, 255, 255));
        thingsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        thingsLabel.setText("Things in this room");
        getContentPane().add(thingsLabel);
        thingsLabel.setBounds(70, 20, 160, 40);

        inventoryScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        inventoryScrollPane.setOpaque(false);

        inventoryList.setBackground(new java.awt.Color(0, 0, 0));
        inventoryList.setForeground(new java.awt.Color(255, 255, 255));
        inventoryList.setOpaque(false);
        inventoryScrollPane.setViewportView(inventoryList);

        getContentPane().add(inventoryScrollPane);
        inventoryScrollPane.setBounds(480, 60, 140, 120);
        inventoryScrollPane.getViewport().setOpaque(false);

        thingScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        thingScrollPane.getViewport().setOpaque(false);
        thingScrollPane.getViewport().setBorder(null);
        thingScrollPane.setOpaque(false);

        thingList.setBackground(new java.awt.Color(0, 0, 0));
        thingList.setForeground(new java.awt.Color(255, 255, 255));
        thingList.setOpaque(false);
        thingScrollPane.setViewportView(thingList);

        getContentPane().add(thingScrollPane);
        thingScrollPane.setBounds(78, 60, 140, 120);

        descriptionScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        descriptionScrollPane.getViewport().setOpaque(false);
        descriptionScrollPane.setOpaque(false);

        descriptionTextArea.setEditable(false);
        descriptionTextArea.setBackground(new java.awt.Color(0, 0, 0));
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setForeground(new java.awt.Color(255, 255, 255));
        descriptionTextArea.setRows(5);
        descriptionTextArea.setText("Description");
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setBorder(null);
        descriptionTextArea.setOpaque(false);
        descriptionScrollPane.setViewportView(descriptionTextArea);

        getContentPane().add(descriptionScrollPane);
        descriptionScrollPane.setBounds(224, 20, 250, 220);

        dropButton.setText("Drop!");
        dropButton.setToolTipText("Click on a thing and press drop");
        dropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropButtonActionPerformed(evt);
            }
        });
        getContentPane().add(dropButton);
        dropButton.setBounds(515, 190, 70, 40);

        pickButton.setText("Pick Up!");
        pickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickButtonActionPerformed(evt);
            }
        });
        getContentPane().add(pickButton);
        pickButton.setBounds(105, 190, 90, 40);

        westButton.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        westButton.setText("WEST");
        westButton.setToolTipText("Walk to the room in the west direction");
        getContentPane().add(westButton);
        westButton.setBounds(210, 300, 90, 60);

        northButton.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        northButton.setText("NORTH");
        northButton.setToolTipText("Walk to the room in the northern direction");
        getContentPane().add(northButton);
        northButton.setBounds(300, 250, 100, 60);

        southButton.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        southButton.setText("SOUTH");
        southButton.setToolTipText("Walk to the room in the south direction");
        getContentPane().add(southButton);
        southButton.setBounds(300, 350, 100, 60);

        eastButton.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        eastButton.setText("EAST");
        eastButton.setToolTipText("Walk to the room in the east direction");
        getContentPane().add(eastButton);
        eastButton.setBounds(400, 300, 90, 60);

        buttonMap = new HashMap<>();
        buttonMap.put(Room.Direction.NORTH, northButton);
        buttonMap.put(Room.Direction.SOUTH, southButton);
        buttonMap.put(Room.Direction.WEST, westButton);
        buttonMap.put(Room.Direction.EAST, eastButton);

        caveGameLabel.setFont(new java.awt.Font("Luminari", 0, 24)); // NOI18N
        caveGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        caveGameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caveGameLabel.setText("THE NOT SO COLOSSAL CAVE");
        getContentPane().add(caveGameLabel);
        caveGameLabel.setBounds(160, 430, 380, 40);

        backgroundImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundImage.setIcon(new javax.swing.ImageIcon("src/media/CaveGame.png")); // NOI18N
        backgroundImage.setLabelFor(backgroundImage);
        backgroundImage.setToolTipText("");
        backgroundImage.setPreferredSize(new java.awt.Dimension(710, 550));
        backgroundImage.setSize(new java.awt.Dimension(710, 550));
        getContentPane().add(backgroundImage);
        backgroundImage.setBounds(0, 0, 710, 550);


        // Add menu bar and items
        menuBar.setText("Menu");
        helpBar.setText("Help");
        mainMenuBar.add(menuBar);
        mainMenuBar.add(helpBar);
        menuBar.add(quit);
        helpBar.add(help);

        // Add ActrionListeners to menu items
        quit.addActionListener((event) -> {
            if (popUp(new ConfirmMessagePopup("Sure you want to quit?")) == 0) System.exit(0);
        });
//        menu.addActionListener(e -> {
//            if (JOptionPane.showConfirmDialog(this, "Sure you want to go back to main menu?") == 0) {
//                CaveInitializer.resetGame();
//                // dispose this Game Frame
//                dispose();
//                // Show the Main Menu again
//                MainMenu.getInstance().setVisible(true);
//            }
//        });
//        menuBar.add(menu);
        help.addActionListener((event) -> {
            popUp(new MessagePopup(helpText()));
        });

        // Set mainMenuBar to this Frame
        setJMenuBar(mainMenuBar);

        updateGui();
        addListeners();
        pack();
        setButtonFocus();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initComponents() {

        inventoryScrollPane = new javax.swing.JScrollPane();
        inventoryModel = new javax.swing.DefaultListModel<Thing>();
        roomThingsModel = new javax.swing.DefaultListModel<Thing>();
        thingScrollPane = new javax.swing.JScrollPane();
        inventoryList = new javax.swing.JList<>(inventoryModel);
        thingList = new javax.swing.JList<>(roomThingsModel);
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        dropButton = new javax.swing.JButton();
        pickButton = new javax.swing.JButton();
        westButton = new javax.swing.JButton();
        northButton = new javax.swing.JButton();
        southButton = new javax.swing.JButton();
        eastButton = new javax.swing.JButton();
        caveGameLabel = new javax.swing.JLabel();
        backgroundImage = new javax.swing.JLabel();
        mainMenuBar = new javax.swing.JMenuBar();
        menuBar = new javax.swing.JMenu();
        helpBar = new javax.swing.JMenu();
        quit = new JMenuItem("Quit Game");
        help = new JMenuItem("Show how this works");
        menu = new JMenuItem("Main Menu");
        inventoryLabel = new javax.swing.JLabel();
        thingsLabel = new javax.swing.JLabel();

        caveInit = CaveInitializer.getInstance();
        caveInit.initAll();
        player = Player.getInstance();
        messages = new JLabel();
    }// </editor-fold>

    private String helpText() {
        return "Navigate through the cave with the navigation buttons\nand pick up things to your inventory.\nYou can later drop items into a room if you want.\nThere will be challenges, so you need to figure out\nwhat items need to be dropped/used in order to solve them!\nRemember to select the item you want to drop/pick up.\n\nButtons in the game: \n\n North: Walk north \n South: Walk south \n"
                + " East: Walk east \n West: Walk west \n Drop: Drop items from your inventory \n Pick Up: Pick up items in the room";
    }

    private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {
        java.util.List<Thing> thingList = (List<Thing>) inventoryList.getSelectedValuesList();
        if (thingList != null) {
            for (Thing thing : thingList) { // traverse through the list of Thing(s) and call takeThing() on each Thing
                try {
                    player.dropThing(thing);
                    updateGui();
                } catch (Exception ex) {
                    popUp(new MessagePopup("Couldn't drop " + thing + ": " + ex.getMessage()));
                }
            }
        }
    }

    private void pickButtonActionPerformed(java.awt.event.ActionEvent evt) {
        List<Thing> thingList = (List<Thing>) this.thingList.getSelectedValuesList();
        if (thingList != null) {
            for (Thing thing : thingList) { // traverse through the list of Things and call takeThing() on each Thing
                try {
                    player.takeThing(thing);
                    updateGui();
                } catch (RuleViolationException ex) {
                    popUp(new MessagePopup("Couldn't take " + thing + ": " + ex.getMessage()));
                }
            }
        }
    }

    /**
     * Creates a pop up window of different types depending on argument
     * and returns a string representation of the returning value
     *
     * @param popUpType the type of popup to be displayed
     * @return the string representation of the return value from the JOptionPane
     * @throws IllegalArgumentException if the given pop up type is not identified
     */
    public static <T> T popUp(IPopup<T> popUpType) {
        return popUpType.popup();
    }

    private void updateGui() {
        descriptionTextArea.setText(player.describeCurrentRoom());
        updateModels();
        updateButtons();
        setButtonFocus();
    }

    // Requests focus on the navgational buttons. Looking
    // for any enabled buttons.
    private void setButtonFocus() {
        for (Room.Direction dir : Room.Direction.values()) {
            JButton button = buttonMap.get(dir);
            if (button.isEnabled()) button.requestFocus();
        }
    }

    private void updateModels() {
        // First clear the models for the two lists
        inventoryModel.clear();
        roomThingsModel.clear();

        for (Thing thing : player.thingsInCurrentRoom()) {
            roomThingsModel.addElement(thing);
        }

        for (Thing thing : player.inventory()) {
            inventoryModel.addElement(thing);
        }
    }

    private void updateButtons() {
        for (Room.Direction dir : Room.Direction.values()) {
            buttonMap.get(dir).setEnabled(player.canSeeDoorIn(dir));
        }
    }

    private void addListeners() {
        for (Room.Direction dir : Room.Direction.values()) {
            buttonMap.get(dir).addActionListener( (event) -> {
                try {
                    player.go(dir);
                    updateGui();
                } catch (IllegalMoveException e) {
                    messages.setText("Bad direction - shouldn't happen.");
                }
            });

            // add this class (implements KeyListener) to the buttonMap's different directions
            buttonMap.get(dir).addKeyListener(this);
        }
    }

    /**
     * Sets the message type for the different pop up windows.
     * MESSAGE_TYP is a regular window with text and OK button.
     */
    public static final int MESSAGE_TYPE = 1;
    /**
     * CONFIRM_TYPE is a window with text and "Yes, No and Cancel".
     */
    public static final int CONFIRM_TYPE = 2;
    /**
     * INPUT_TYPE is with text, input textfield and OK button.
     */
    public static final int INPUT_TYPE = 3;

    // other gui variables
    private javax.swing.JLabel backgroundImage;
    private javax.swing.JLabel caveGameLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JMenu helpBar;
    private javax.swing.JList<Thing> inventoryList;
    private javax.swing.JList<Thing> thingList;
    private javax.swing.JScrollPane inventoryScrollPane;
    private javax.swing.JScrollPane thingScrollPane;
    private DefaultListModel<Thing> inventoryModel;
    private DefaultListModel<Thing> roomThingsModel;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu menuBar;
    private JMenuItem quit;
    private JMenuItem help;
    private JMenuItem menu;
    private javax.swing.JButton dropButton;
    private javax.swing.JButton pickButton;
    private javax.swing.JButton northButton;
    private javax.swing.JButton southButton;
    private javax.swing.JButton westButton;
    private javax.swing.JButton eastButton;
    private Map<Room.Direction, JButton> buttonMap;
    private CaveInitializer caveInit;
    private Player player;
    private JLabel messages;
    private javax.swing.JLabel thingsLabel;
    private javax.swing.JLabel inventoryLabel;
    // End of variables declaration

    private class ThingRenderer<Thing> implements ListCellRenderer<Thing> {

        protected DefaultListCellRenderer defaultLCR = new DefaultListCellRenderer();

        public Component getListCellRendererComponent(JList<? extends Thing> list,
                                                      Thing thing,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            defaultLCR.setText(thing.toString());
            if (isSelected) {
                defaultLCR.setBackground(list.getSelectionBackground());
                defaultLCR.setForeground(list.getSelectionForeground());
            } else {
                defaultLCR.setBackground(list.getBackground());
                defaultLCR.setForeground(list.getForeground());
            }
            return defaultLCR;
        }
    }

}
