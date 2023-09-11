package model;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class GameLibraryTest {

    private GameLibrary gameLibrary;



    @BeforeEach
    public void runBefore() {

        gameLibrary = new GameLibrary();
    }

    @Test
    void testConstructor() {

        assertEquals(0, gameLibrary.getGames().size());
    }

    @Test
    void testAddGame() {

        for (int i = 0; i < gameLibrary.MAX_GAMES; i++) {
            gameLibrary.addGame("Watch Dogs 2", "10/18/2022", "50:32");

        }
        assertFalse(gameLibrary.addGame("COD", "11/28/2021", "00:00"));

        gameLibrary.clearGames();

        for (int i = 0; i < gameLibrary.MAX_GAMES - 1; i++) {
            gameLibrary.addGame("Watch Dogs 2", "10/18/2022", "50:32");

        }
        assertTrue(gameLibrary.addGame("COD", "11/28/2021", "00:00"));

        gameLibrary.clearGames();

        for (int i = 0; i <= gameLibrary.MAX_GAMES; i++) {
            gameLibrary.addGame("Watch Dogs 2", "10/18/2022", "50:32");

        }
        assertFalse(gameLibrary.addGame("COD", "11/28/2021", "00:00"));

        gameLibrary.clearGames();


        assertTrue(gameLibrary.addGame("Math", "10/18/2022", "23:59"));

        assertEquals("Math" , gameLibrary.getGame(0).getName());


    }

    @Test
    void testRemoveGame() {
        gameLibrary.addGame("Math HW", "10/18/2022", "23:59");
        gameLibrary.removeGame(0);
        assertEquals(0 , gameLibrary.getGames().size());
    }

    @Test
    void TestEditGameName() {
        gameLibrary.addGame("Math HW", "10/18/2022", "23:59");
        gameLibrary.addGame("Chemistry HW", "10/20/2022", "11:59");

        gameLibrary.editGameName(1, "Physics HW");
        assertEquals("Physics HW", gameLibrary.getGame(1).getName());
    }

    @Test
    void TestEditGameDate() {
        gameLibrary.addGame("Math HW", "10/18/2022", "23:59");
        gameLibrary.addGame("CPSC HW", "10/20/2022", "11:59");

        gameLibrary.editGameDate(1, "10/22/2022");
        assertEquals("10/22/2022", gameLibrary.getGame(1).getDateInitialized());
    }

    @Test
    void TestEditGameTime() {
        gameLibrary.addGame("Physics HW", "10/15/2022", "13:20");
        gameLibrary.addGame("Biology HW", "10/20/2022", "19:25");

        gameLibrary.editGameTime(0, "23:00");
        assertEquals("23:00", gameLibrary.getGame(0).getTimePlayed());
    }

    @Test
    void TestGetGames() {
        gameLibrary.addGame("Physics HW", "10/15/2022", "13:20");
        gameLibrary.addGame("Biology HW", "10/20/2022", "19:25");


        assertEquals(2 , gameLibrary.getGames().size());
    }

    @Test
    void TestGetGame() {
        gameLibrary.addGame("Physics HW", "10/15/2022", "13:20");
        gameLibrary.addGame("Biology HW", "10/20/2022", "19:25");


        assertEquals("Physics HW", gameLibrary.getGame(0).getName());
    }


    @Test
    void TestClearGames() {
        gameLibrary.addGame("Physics HW", "10/15/2022", "13:20");
        gameLibrary.addGame("Biology HW", "10/20/2022", "19:25");


        gameLibrary.clearGames();
        assertEquals(0, gameLibrary.getSize());
    }

    @Test
    void TestGetSpecificGame() {
        assertEquals("5", gameLibrary.getSpecificGame(0));

    }

    @Test
    void TesttoString()  {
        assertEquals("5", gameLibrary);

    }

    @Test
    void TestToJSON()  {
        assertEquals("5", gameLibrary.toJson());

    }
    @Test
    void TestGamesToJson()  {
        assertEquals("5", gameLibrary.gamesToJson());

    }







}
