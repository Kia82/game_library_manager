package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.Scanner;

public class GameLibraryApp {

    private static final String JSON_STORE = "./data/gamelibrary.json";
    private GameLibrary gameLibrary;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECT: Runs the To Do List Application
    public GameLibraryApp() {


        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        gameLibrary = new GameLibrary();
        input = new Scanner(System.in);

        runGameLibraryApp();

    }

    //MODIFIES: this
    //EFFECTS: functions the inputs from the user
    public void runGameLibraryApp() {

        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        do {

            gameLibraryMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                takeInput(command);
            }



        } while (keepGoing);

    }





    //EFFECT: Shows the options the user has
    public void gameLibraryMenu() {
        System.out.println("\nOptions:");
        System.out.println("\t0 -> Add a Game");
        System.out.println("\t1 -> Remove a Game");
        System.out.println("\t2 -> Display all Games");
        System.out.println("\t3 -> Display Specific Game");
        //System.out.println("\t4 -> Edit a Game");
        System.out.println("\t5 -> Save my game library");
        System.out.println("\t6 -> Load my game library");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: takes in command input from user
    private void takeInput(String command) {
        if (command.equals("0")) {
            addGame();
        } else if (command.equals("1")) {
            removeGame();
        } else if (command.equals("2")) {
            displayGames();
        } else if (command.equals("3")) {
            displaySpecificGame();

        }  else if (command.equals("5")) {
            saveGameLibrary();
        } else if (command.equals("6")) {
            loadGameLibrary();
        } else {
            System.out.println("Input not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: adds a task to do the list
    //TODO: BUG, it will take two inputs instead of one (skip next input) if there is a space in between
    // in between the first String
    public void addGame() {



        System.out.println("Enter the name of the game "
                +
                "(remember that there is a max number games you can add to your game library)");
        String name = input.next();

        System.out.println("Enter the date you first purchased the game"
                  +  " FORMAT: MM/DD/YY i.e 10/17/22");
        String date = input.next();


        System.out.println("Enter the time that you have played it so far"
                + " FORMAT: HH:MM (24 hour format) i.e 15:20");

        String time = input.next();


        gameLibrary.addGame(name, date, time);

    }

    // MODIFIES: this
    // EFFECTS: removes a task from the to do list
    public void removeGame() {
        displayGames();
        System.out.println("Enter the game you want to remove"
                +
                " (FORMAT: enter the index of the game you want to remove)");

        int index = input.nextInt();
        int listSize =  gameLibrary.getGames().size();

        if ((0 <= index) && (index < listSize)) {
            gameLibrary.removeGame(index);
        } else {
            System.out.print("Invalid input");
        }

    }

    // MODIFIES: this
    // EFFECTS: displays all tasks
    public void displayGames() {
        System.out.print(gameLibrary);
    }



    // MODIFIES: this
    // EFFECTS: displays a specific task
    public void displaySpecificGame() {
        System.out.println("Enter the index of the game:");

        int index = input.nextInt();
        int listSize =  gameLibrary.getGames().size();

        if ((0 <= index) && (index < listSize)) {
            System.out.println(gameLibrary.getSpecificGame(index));
        } else {
            System.out.println("Invalid input");
        }

    }


    private void saveGameLibrary() {
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
    // EFFECTS: loads workroom from file
    private void loadGameLibrary() {
        try {
            gameLibrary = jsonReader.read();
            System.out.println("Loaded " + "Game Library" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
