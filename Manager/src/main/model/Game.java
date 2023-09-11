package model;

import org.json.JSONObject;
import persistence.Writable;

public class Game implements Writable {
    private String name;
    private String dateInitialized;
    private String timePlayed;


    //EFFECTS: Initializes all the task fields
    public Game(String name, String dateInitialized, String timePlayed) {
        this.name = name;
        this.dateInitialized = dateInitialized;
        this.timePlayed = timePlayed;

    }

    //EFFECT: returns the name of the task
    public String getName() {
        return name;
    }

    //EFFECT: returns the date of the task
    public String getDateInitialized() {

        return dateInitialized;
    }

    //EFFECT: returns the time of the task
    public String getTimePlayed() {

        return timePlayed;
    }

    //MODIFIES: this
    //EFFECT: sets a new name for the task
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECT: sets a new date for the task
    public void setDateInitialized(String dateInitialized) {
        this.dateInitialized = dateInitialized;
    }

    //MODIFIES: this
    //EFFECT: sets a new time for the task
    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("time played", timePlayed);
        json.put("date initialized", dateInitialized);
        json.put("name", name);

        return json;
    }




}

