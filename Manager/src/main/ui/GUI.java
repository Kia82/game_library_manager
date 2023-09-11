package ui;

import model.Event;
import model.EventLog;
import model.GameLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GUI extends JFrame implements ActionListener {


    private static final String JSON_STORE = "./data/gamelibrary.json";
    private GameLibrary gameLibrary;


    private JPanel mainMenuPanel;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;


    private JPanel gameListPanel;
    private JLabel gameList;

    private JPanel addAGamePanel;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;

    private JPanel specificGamePanel;
    private JLabel specificGame;
    private JTextField f1;
    private JButton b1;


    private JLabel name;
    private JLabel dateInitialized;
    private JLabel timePlayed;







    //EFFECTS: Sets up the frame, initializes/sets up the main menu, makes all the panels and adds to the frame
    public GUI() {
        super("Game Library");
        gameLibrary = new GameLibrary();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        initializeMenu();
        makeGameListPanel();
        makeAddAGamePanel();
        makeViewSpecificGamePanel();


        JLabel welcomeLabel = new JLabel("Your Game Library!");
        JLabel mainMenuImage = new JLabel();

        addImageToLabel(mainMenuImage);
        addLabel(welcomeLabel);



        initializeMenuButtons();

        addButtons(button1, button2, button3, button4, button5, button6, button7);
        addActionToButton();


        mainMenuPanel.setVisible(true);




    }

    //MODIFIES: this
    //EFFECTS: sets up an image icon and sets it to the game Library image
    // in the main menu panel and scales it properly
    public void addImageToLabel(JLabel mainMenuImage) {
        ImageIcon ic = new ImageIcon("./data/gameLibrary.png");
        Image image = ic.getImage();
        Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        ic = new ImageIcon(newimg);

        mainMenuImage.setIcon(ic);
        mainMenuPanel.add(mainMenuImage);

    }


    //EFFECTS: This is the method that is called when any button is clicked
    public void actionPerformed(ActionEvent ae) {


        if (ae.getActionCommand().equals("View Games")) {
            initializeGameListPanel();
        } else if (ae.getActionCommand().equals("List your Game")) {
            initializeAddAGamePanel();
        } else if (ae.getActionCommand().equals("Add a Game")) {
            addAGame();
        } else if (ae.getActionCommand().equals("Remove a Game")) {
            removeGame();
        } else if (ae.getActionCommand().equals("View Specific Game")) {
            initializeViewSpecificGame();
        } else if (ae.getActionCommand().equals("Change")) {
            changeSpecificGameInput();
        } else if (ae.getActionCommand().equals("Save Game Library")) {
            saveGameList();
        } else if (ae.getActionCommand().equals("Load Game Library")) {
            loadGameList();
        } else if (ae.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        } else if (ae.getActionCommand().equals("Exit")) {
            printLog();
            System.exit(0);

        }
    }


    public void printLog() {
        EventLog eventLog = EventLog.getInstance();

        for (Event next: eventLog) {
            System.out.println(next.toString() + "\n\n");
        }
    }

    //MODIFIES: this
    // EFFECTS: Initializes the main menu panel with
    private void initializeMenu() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(Color.gray);
        add(mainMenuPanel);

        gameList = new JLabel();
        gameList.setText("No Games in Library :(");
        gameList.setFont(new Font("ComicSans", Font.BOLD, 12));



    }

    // EFFECTS: Initializes main menu buttons with given labels
    public void initializeMenuButtons() {
        button1 = new JButton("View Games");
        button2 = new JButton("List your Game");
        button3 = new JButton("Remove a Game");
        button4 = new JButton("View Specific Game");
        button5 = new JButton("Save Game Library");
        button6 = new JButton("Load Game Library");
        button7 = new JButton("Exit");

    }


    //EFFECTS: initializes the game list panel
    public void initializeGameListPanel() {

        add(gameListPanel);
        gameListPanel.setVisible(true);
        mainMenuPanel.setVisible(false);
        addAGamePanel.setVisible(false);
        specificGamePanel.setVisible(false);

    }

    //EFFECTS: initializes the Add A Game panel so that it is visible
    public void initializeAddAGamePanel() {
        add(addAGamePanel);
        addAGamePanel.setVisible(true);
        mainMenuPanel.setVisible(false);
        gameListPanel.setVisible(false);
        specificGamePanel.setVisible(false);
    }



    //EFFECTS: initializes the view specific game panel so that it is visible
    public void initializeViewSpecificGame() {

        add(specificGamePanel);
        specificGamePanel.setVisible(true);

        mainMenuPanel.setVisible(false);
        gameListPanel.setVisible(false);
        addAGamePanel.setVisible(false);


    }

    //MODIFIES: this
    //EFFECTS: makes the game list panel
    public void makeGameListPanel() {

        gameListPanel = new JPanel();

        addMenuButton(gameListPanel);

        JScrollPane scroll = new JScrollPane(gameList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gameList.setFont(new Font("ComicSans", Font.CENTER_BASELINE,13));
        gameListPanel.add(scroll);


    }

    //MODIFIES: this
    // EFFECTS: makes the add a game panel
    public void makeAddAGamePanel() {

        addAGamePanel = new JPanel(new GridLayout(0, 2));
        addMenuButton(addAGamePanel);

        JButton addAGameButton = new JButton("Add a Game");
        addAGameButton.setActionCommand("Add a Game");
        addAGameButton.addActionListener(this);


        addAddAGameButton(addAGameButton);

        createGameFields();



    }

    // MODIFIES: this
    // EFFECTS: makes the View Specific Game Panel
    public void makeViewSpecificGamePanel() {

        specificGamePanel = new JPanel();
        specificGamePanel.setBackground(Color.lightGray);

        addMenuButton(specificGamePanel);

        f1 = new JTextField(2);

        b1 = new JButton("Change");
        b1.setActionCommand("Change");
        b1.addActionListener(this);

        specificGame = new JLabel("No Inputs Received");
        specificGame.setFont(new Font("ComicSans",Font.ITALIC, 15));
        specificGame.setBackground(Color.ORANGE);

        specificGamePanel.add(f1);
        specificGamePanel.add(b1);
        specificGamePanel.add(specificGame);


    }


    //EFFECTS: parse the JTextField into an int that is used to get a specific element in the game library list
    //         which is graphically formatted and given to the specificGame label
    public void changeSpecificGameInput() {
        try {
            int parseInt = Integer.parseInt(f1.getText());
            String inputText = gameLibrary.getSpecificGame(parseInt);
            String formattedInputText = ("<html><pre>Selected Game: \n" + inputText + "\n</pre></html>");
            specificGame.setText(formattedInputText);
        } catch (Exception e) {
            System.out.println("Some Error occurred, make sure inputs are right and within bounds");

        }
    }


    //MODIFIES: this
    //EFFECTS: formats/adds the "add a game button" in the "add a game panel"
    private void addAddAGameButton(JButton addAGameButton) {

        addAGameButton.setFont(new Font("ComicSans", Font.ITALIC, 12));
        addAGameButton.setBackground(Color.YELLOW);
        addAGamePanel.add(addAGameButton);

    }


    //EFFECTS: adds a game to the game library which is formatted/visually added to the gameList panel
    public void addAGame() {

        gameLibrary.addGame(t1.getText(), t2.getText(), t3.getText());
        gameList.setText("<html><pre>Current Games: \n" + gameLibrary + "\n</pre></html>");
    }

    //EFFECTS: removes the top game from the gameLibrary list
    public void removeGame() {

        try {
            gameLibrary.removeGame(0);
            gameList.setText("<html><pre>Current Games: \n" + gameLibrary + "\n</pre></html>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your game library is empty");
        }

    }

    //EFFECTS: Creates the Game fields and formats them
    private void createGameFields() {


        name = new JLabel("Name of the Game:");
        name.setFont(new Font("ComicSans", Font.ITALIC, 12));
        name.setBorder(new LineBorder(Color.DARK_GRAY, 3, true));

        t1 = new JTextField(6);

        dateInitialized = new JLabel("The Date Initialized:");
        dateInitialized.setFont(new Font("ComicSans", Font.ITALIC, 12));
        dateInitialized.setBorder(new LineBorder(Color.DARK_GRAY, 3, true));

        t2 = new JTextField(6);


        timePlayed = new JLabel("Time Played:");
        timePlayed.setFont(new Font("ComicSans", Font.ITALIC, 12));
        timePlayed.setBorder(new LineBorder(Color.DARK_GRAY, 3, true));

        t3 = new JTextField(6);

        addAGamePanel.add(name);
        addAGamePanel.add(t1);
        addAGamePanel.add(dateInitialized);
        addAGamePanel.add(t2);
        addAGamePanel.add(timePlayed);
        addAGamePanel.add(t3);




    }

    //MODIFIES: this
    //EFFECTS: adds the return to main menu button to each panel that calls it
    private void addMenuButton(JPanel panel) {

        JButton mainMenuButton = new JButton("Return to main menu");
        mainMenuButton.setActionCommand("Return to main menu");
        mainMenuButton.addActionListener(this);

        mainMenuButton.setFont(new Font("ComicSans", Font.ITALIC, 11));
        mainMenuButton.setBackground(Color.orange);
        panel.add(mainMenuButton);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);




    }

    // MODIFIES: this
    // EFFECTS: Creates the welcome text label and adds it to the main menu panel
    public void addLabel(JLabel j1) {
        j1.setFont(new Font("ComicSans", 1, 45));
        j1.setBorder(new EmptyBorder(0,0,0,0));
        mainMenuPanel.add(j1);
    }


    // MODIFIES: this
    // EFFECT: sets up action listener to each button so that it can perform an action when it is clicked
    public void addActionToButton() {

        button1.addActionListener(this);
        button1.setActionCommand("View Games");
        button2.addActionListener(this);
        button2.setActionCommand("List your Game");
        button3.addActionListener(this);
        button3.setActionCommand("Remove a Game");
        button4.addActionListener(this);
        button4.setActionCommand("View Specific Game");
        button5.addActionListener(this);
        button5.setActionCommand("Save Game Library");
        button6.addActionListener(this);
        button6.setActionCommand("Load Game Library");
        button7.addActionListener(this);
        button7.setActionCommand("Exit");

        addWindowListener(new CloseWindowActions());
    }


    //MODIFIES: this
    //EFFECTS: adds main menu buttons to a method that formats each button
    public void addButtons(JButton button1, JButton button2, JButton button3, JButton button4,
                           JButton button5, JButton button6, JButton button7) {

        addButton(button1, mainMenuPanel);
        addButton(button2, mainMenuPanel);
        addButton(button3, mainMenuPanel);
        addButton(button4, mainMenuPanel);
        addButton(button5, mainMenuPanel);
        addButton(button6, mainMenuPanel);
        addButton(button7, mainMenuPanel);

    }



    // MODIFIES this
    // EFFECTS: sets up the buttons for the main menu
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBackground(Color.lightGray);
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: saves game from the data file
    public void saveGameList() {

        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(gameLibrary);
            jsonWriter.close();
            System.out.println("Saved " + "Game Library" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads game from the data file
    public void loadGameList() {

        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            gameLibrary = jsonReader.read();
            gameList.setText("<html><pre>Current Listings: \n" + gameLibrary + "\n</pre></html>");
            System.out.println("Loaded " + "Game Library" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    // EFFECTS: returns to the main menu panel
    public void returnToMainMenu() {
        mainMenuPanel.setVisible(true);
        gameListPanel.setVisible(false);
        addAGamePanel.setVisible(false);
        specificGamePanel.setVisible(false);
    }





}
