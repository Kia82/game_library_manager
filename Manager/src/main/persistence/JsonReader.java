package persistence;

import model.Game;
import model.GameLibrary;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public GameLibrary read() throws IOException {
        String jsonData = readFile(source); //read file and returns a readable file
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameLibrary(jsonObject); //analyses that readable file?

    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private GameLibrary parseGameLibrary(JSONObject jsonObject) {
        GameLibrary gameLibrary = new GameLibrary();
        addGames(gameLibrary, jsonObject);
        return gameLibrary;

    }

    private void addGames(GameLibrary gameLibrary, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(gameLibrary, nextGame);
        }
    }

    private void addGame(GameLibrary gameLibrary, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String dateInitialized = jsonObject.getString("date initialized");
        String timePlayed = jsonObject.getString("time played");

        gameLibrary.addGame(name, dateInitialized, timePlayed);

    }


}
