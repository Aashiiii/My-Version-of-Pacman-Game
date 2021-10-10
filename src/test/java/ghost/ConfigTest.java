package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class ConfigTest {
    
    // Testing the constructor of the Fruit class.
    @Test 
    public void constructor () {
        assertNotNull (new Config ("configTest.json") );
    }
    
    // Testing if it is assigning the correct value to waka's start
    // x-axix position or not.
    @Test 
    public void wakastartXPosition () {
        Config config = new Config ("configTest.json");
        assertEquals (16, config.getWakaStartX ());
    }
    
    // Testing if it is assigning the correct value to waka's start
    // y-axix position or not.
    @Test 
    public void wakastartYPosition () {
        Config config = new Config ("configTest.json");
        assertEquals (411, config.getWakaStartY ());
    }
    
    // Testing if it is assigning the correct value to the ghosts's
    // mode lengths or not.
    @Test 
    public void durationOfModeLength () {
        Config config = new Config ("configTest.json");
        assertEquals (1084, config.getDuration ());
    }
    
    // Testing if it is assigning the correct value to waka's start
    // x-axix position or not.
    @Test 
    public void downLeftPositions () {
        Config config = new Config ("configTest.json");
        ArrayList<Integer> expected = new ArrayList<Integer> ();
        expected.add (432);
        expected.add (400);
        assertEquals (expected, config.getDownL ());
    }
    
    // Testing if it is assigning the correct positions to up left grids.
    @Test 
    public void upLeftPositions () {
        Config config = new Config ("configTest.json");
        ArrayList<Integer> expected = new ArrayList<Integer> ();
        expected.add (432);
        expected.add (464);
        assertEquals (expected, config.getUpL ());
    }

    // Testing if it is assigning the correct positions to down right grids.
    @Test 
    public void downRightPositions () {
        Config config = new Config ("configTest.json");
        ArrayList<Integer> expected = new ArrayList<Integer> ();
        expected.add (0);
        expected.add (400);
        assertEquals (expected, config.getDownR ());
    }

    // Testing if it is assigning the correct positions to up right grids.
    @Test 
    public void upRightPositions () {
        Config config = new Config ("configTest.json");
        ArrayList<Integer> expected = new ArrayList<Integer> ();
        expected.add (0);
        expected.add (464);
        assertEquals (expected, config.getUpR ());
    }

    // Testing if it is assigning the correct positions to horizontal grids.
    @Test 
    public void horizontalPositions () {
        Config config = new Config ("configTest.json");
        assertEquals (16, config.getHor ().get (0));
    }

    // Testing if it is assigning the correct positions to vertical grids.
    @Test 
    public void verticalPositions () {
        Config config = new Config ("configTest.json");
        assertEquals (0, config.getVer ().get (0));
    }

    // Testing if it is correctly finding the name of map file.
    @Test 
    public void mapFileUsed () {
        Config config = new Config ("configTest.json");
        assertEquals ("map1.txt", config.getMapFile () );
    }

    // Testing if it is assigning the correct number of lives to waka, and ghost.
    @Test 
    public void numberOfLives () {
        Config config = new Config ("configTest.json");
        assertEquals (3, config.getLives () );
    }

    // Testing if it is assigning the correct speed to waka, and ghost.
    @Test 
    public void speed () {
        Config config = new Config ("configTest.json");
        assertEquals (2, config.getSpeed () );
    }

    // Testing if it is assigning the correct mode lengths for ghost mode
    @Test 
    public void modeLengths () {
        Config config = new Config ("configTest.json");
        assertEquals (7, config.getModeLength ().get (0) );
    }

    // Testing if it is assigning the correct frightened mode length to the ghost.
    @Test 
    public void frightenedLength () {
        Config config = new Config ("configTest.json");
        assertEquals (20, config.getFrightenedLength () );
    }

    // Testing if it is assigning correct fruit x-axis, and y-axis positions.
    @Test 
    public void fruits () {
        Config config = new Config ("configTest.json");
        assertEquals (336, config.getFruits ().get (0));
    }

    // Testing if it is assigning correct super fruit x-axis, and y-axis positions.
    @Test 
    public void superFruits () {
        Config config = new Config ("configTest.json");
        assertEquals (388, config.getSuperFruits ().get (0));
    }

    // Testing if it is assigning correct ambusher ghost x-axis, and y-axis positions.
    @Test 
    public void ghostsAmbusher () {
        Config config = new Config ("configTest.json");
        ArrayList<Long> expected = new ArrayList<Long> ();
        expected.add ( (long) 48);
        expected.add ( (long) 443);
        assertEquals (expected, config.getGhostsAmbusher ());
    }

    // Testing if it is assigning correct ignorant ghost x-axis, and y-axis positions.
    @Test 
    public void ghostsIgnorant () {
        Config config = new Config ("configTest.json");
        ArrayList<Long> expected = new ArrayList<Long> ();
        expected.add ( (long) 96);
        expected.add ( (long) 443);
        assertEquals (expected, config.getGhostsIgnorant ());
    }

    // Testing if it is assigning correct chaser ghost x-axis, and y-axis positions.
    @Test 
    public void ghostsChaser () {
        Config config = new Config ("configTest.json");
        ArrayList<Long> expected = new ArrayList<Long> ();
        expected.add ( (long) 80);
        expected.add ( (long) 443);
        assertEquals (expected, config.getGhostsChaser ());
    }
 
    // Testing if it is assigning correct whim ghost x-axis, and y-axis positions.
    @Test 
    public void ghostsWhim () {
        Config config = new Config ("configTest.json");
        ArrayList<Long> expected = new ArrayList<Long> ();
        expected.add ( (long) 64);
        expected.add ( (long) 443);
        assertEquals (expected, config.getGhostsWhim ());
    }

}