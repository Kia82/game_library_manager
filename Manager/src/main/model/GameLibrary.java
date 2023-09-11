package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;

public class GameLibrary implements Writable {

    private ArrayList<Game> games;
    public static final int MAX_GAMES = 20;


    //Effect: constructs a game library
    public GameLibrary() {

        games = new ArrayList<Game>();

    }

    //Requires: dateInitialized has to be in MM:DD:YY format i.e 10/28/22
    //          timePlayed has to be in HH:MM i.e 13:50
    //Modifies: this
    //Effect: Adds a game to the list if the list size is less than MAX_GAMES
    public boolean addGame(String name, String dateInitialized, String timePlayed)  {
        if (getSize() >= MAX_GAMES) {
            return false;
        }
        games.add(new Game(name, dateInitialized, timePlayed));
        EventLog.getInstance().logEvent(new Event("Added a Game to the Library"));

        return true;
    }

    //Requires:  i >= 0, i < tasks.size()
    //Modifies: this
    //Effect: removes a task from the list
    public void removeGame(int i) {
        games.remove(i);
        EventLog.getInstance().logEvent(new Event("Removed the first Game From the Library"));

    }

    public void clearGames() {
        games.clear();
    }

    //Require: i >= 0, i < tasks.size()
    //Modifies: this
    //Effects: edits a task in the tasks
    public void editGameName(int i, String name) {

        games.get(i).setName(name);
    }

    //Require: i >= 0, i < tasks.size()
    //         date has to be in MM:DD:YY format i.e 10/28/22
    //Modifies: this
    //Effects: edits a task in the tasks
    public void editGameDate(int i, String date) {
        games.get(i).setDateInitialized(date);


    }

    //Require: i >= 0, i < tasks.size()
    //         time has to be in HH:MM (24 hour format) i.e 13:50
    //Modifies: this
    //Effects: edits a game in the game library
    public void editGameTime(int i, String time) {

        games.get(i).setTimePlayed(time);

    }


    //Effects: returns the list of tasks
    public  ArrayList<Game> getGames() {

        return games;
    }


    //Effects: returns the size of tasks
    public int getSize() {

        return games.size();
    }


    //Require: i >= 0, i < tasks.size()
    //Effects: returns a specific task
    public Game getGame(int i) {

        return games.get(i);
    }

    public String getSpecificGame(int i) {
        String specificGameName = games.get(i).getName();
        String specificGameDateInitialized = games.get(i).getDateInitialized();
        String specificGameTimePlayed = games.get(i).getTimePlayed();


        String specificGame = ("[Name: " + specificGameName + "\n"
                             + " Date Initialized: " + specificGameDateInitialized + "\n"
                             + " Time Played: " + specificGameTimePlayed + "]");


        return specificGame;
    }

    public String toString() {

        String gameLibrary = "";
        for (Game g : games) {
            gameLibrary +=    ("[Name: " + g.getName() + "\n"
                              + " Date Initialized: " + g.getDateInitialized() + "\n"
                              + " Time Played: " + g.getTimePlayed() + "]" + "\n");
        }

        return gameLibrary;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("games", gamesToJson());

        return json;
    }

    // EFFECTS: returns games in this GameLibrary as a JSON array
    public JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : games) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }



}
