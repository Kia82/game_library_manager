package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {

        private Game game;

    @BeforeEach
    public void runBefore() {

        game = new Game("CPSC 210 Project", "10/17/2022", "22:00");
    }

    @Test
    public void testConstructor() {
        assertEquals("CPSC 210 Project", game.getName());
        assertEquals("10/17/2022", game.getDateInitialized());
        assertEquals("22:00", game.getTimePlayed());

    }

    @Test
    void testGetName() {
        assertEquals("CPSC 210 Project", game.getName());

    }

    @Test
    void testGetDate() {

        assertEquals("10/17/2022", game.getDateInitialized());

    }

    @Test
    void TestGetTime() {
        assertEquals("22:00", game.getTimePlayed());

    }



    @Test
    public void testSetName() {
        game.setName("CPSC 121 HW");
        assertEquals("CPSC 121 HW", game.getName());

    }
    @Test
    public void testSetDate() {
        game.setDateInitialized("10/17/2022");
        assertEquals("10/17/2022", game.getDateInitialized());

    }
    @Test
    public void testSetTime() {
        game.setTimePlayed("11:00");
        assertEquals("11:00", game.getTimePlayed());
    }

    @Test
    public void toJson() {
        game.toJson();
        assertEquals("11:00", game.getTimePlayed());
    }






}