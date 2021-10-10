package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class GameManagerTest {

    // Testing the constructor of the game manager class.
    @Test 
    public void constructor () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertNotNull (new GameManager (waka, ghost, fruit) );
    }

    // Testing the debug mode for ambusher when it is alive, and present.
    @Test 
    public void getCoordinatesForDebugAmbusher () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (62, gameManager.getCoordinatesForDebug (0).get (0) );
    }

    // Testing the debug mode for ignorant when it is alive, and present.
    @Test 
    public void getCoordinatesForDebugIgnorant () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (110, gameManager.getCoordinatesForDebug (1).get (0) );
    }

    // Testing the debug mode for chaser when it is alive, and present.
    @Test 
    public void getCoordinatesForDebugChaser () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (94, gameManager.getCoordinatesForDebug (2).get (0) );
    }

    // Testing the debug mode for whim when it is alive, and present.
    @Test 
    public void getCoordinatesForDebugWhim () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (78, gameManager.getCoordinatesForDebug (3).get (0) );
    }

    // Testing the debug mode for ambusher when it does not esists in game.
    @Test 
    public void getCoordinatesForDebugAmbusherAbsent () {
        Config config = new Config ("configTest2.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (0, gameManager.getCoordinatesForDebug (0).size () );
    }

    // Testing the debug mode for ignorant when it does not esists in game.
    @Test 
    public void getCoordinatesForDebugIgnorantAbsent () {
        Config config = new Config ("configTest2.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (0, gameManager.getCoordinatesForDebug (1).size () );
    }

    // Testing the debug mode for chaser when it does not esists in game.
    @Test 
    public void getCoordinatesForDebugChaserAbsent () {
        Config config = new Config ("configTest2.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (0, gameManager.getCoordinatesForDebug (2).size () );
    }

    // Testing the debug mode for whim when it does not esists in game.
    @Test 
    public void getCoordinatesForDebugWhimAbsent () {
        Config config = new Config ("configTest2.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (0, gameManager.getCoordinatesForDebug (3).size () );
    }

    // Testing the debug mode for ambusher when it is not alive.
    @Test 
    public void getCoordinatesForDebugAmbushertNotAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        ghost.setAmbusherAlive (false);
        assertEquals (0, gameManager.getCoordinatesForDebug (0).size () );
    }

    // Testing the debug mode for ignorant when it is not alive.
    @Test 
    public void getCoordinatesForDebugIgnorantNotAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        ghost.setIgnorantAlive (false);
        assertEquals (0, gameManager.getCoordinatesForDebug (1).size () );
    }

    // Testing the debug mode for chaser when it is not alive.
    @Test 
    public void getCoordinatesForDebugChaserNotAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        ghost.setChaserAlive (false);
        assertEquals (0, gameManager.getCoordinatesForDebug (2).size () );
    }

    // Testing the debug mode for whim when it is not alive.
    @Test 
    public void getCoordinatesForDebugWhimNotAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        ghost.setWhimAlive (false);
        assertEquals (0, gameManager.getCoordinatesForDebug (3).size () );
    }

    // Checking the tick function for GameManager class for different scenarios.
    @Test 
    public void checkTick () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        gameManager.tick ();
        assertEquals (20, gameManager.getFrightenedLength () );
    }

    // Checking the tick function for GameManager class for different scenarios.
    @Test 
    public void checkTick2 () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        long time = System.currentTimeMillis() / 1000;
        fruit.setFrightenedStartTime ( (long) time + 200);
        gameManager.tick ();
        assertEquals (20, gameManager.getFrightenedLength () );
    }

    // Checking the tick function for GameManager class for different scenarios.
    @Test 
    public void checkTick3 () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        fruit.setFrightenedStartTime ( (long) 900);
        gameManager.tick ();
        assertEquals (20, gameManager.getFrightenedLength () );
    }

    // Checking if the GameManager class is using the correct frightened length to 
    // perform the necessary operations or not.
    @Test 
    public void checkGetFrightenedLength () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        GameManager gameManager = new GameManager (waka, ghost, fruit);
        assertEquals (0, gameManager.getFrightenedLength () );
    }

}