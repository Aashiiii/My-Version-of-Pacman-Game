package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

class FruitTest {

    // Testing the constructor of fruit class.
    @Test 
    public void constructor () {
        Config config = new Config ("configTest.json");
        assertNotNull (new Fruit (null, null, config) );
    }

    // Testing if it is correctly identifying if the fruit has been eaten
    // by the waka or not.
    @Test 
    public void eaten () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        assertEquals (false, fruit.getEaten ());
    }

    // Testing if it is assigning the correct value to the start time of
    // frightened mode, that is when a super fruit has been eaten, or not.
    @Test 
    public void frightenedStartTime () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        assertEquals (0, fruit.getFrightenedStartTime ());
    }

    // Testing if it is storing the position of fruits correctly in an
    // arraylist or not.
    @Test 
    public void fruits () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        assertEquals (336, fruit.getFruits ().get (0));
    }

    // Testing if it is storing the position of super fruits correctly in
    // an arraylist or not.
    @Test 
    public void superFruits () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        assertEquals (388, fruit.getSuperFruits ().get (0));
    }

    // Testing if it is correctly identifying when the game has been won
    // that is when no more fruits, and super fruits are left to be
    // eaten. This tests the case when game is not won.
    @Test 
    public void gameWonWhenNot () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        assertEquals (false, fruit.hasWon () );
    }

    // Testing if it is correctly identifying when the game has been won
    // that is when no more fruits, and super fruits are left to be
    // eaten. This tests the case when game is won.
    @Test 
    public void gameWonWhenWon () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        ArrayList<Integer> fruits = new ArrayList<Integer> ();
        ArrayList<Integer> superFruit = new ArrayList<Integer> ();
        fruit.setFruits (fruits);
        fruit.setSuperFruits (superFruit);
        assertEquals (true, fruit.hasWon ());
    }

    // This is testing different scenarios in which game could be won.
    @Test
    public void gameWon2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        ArrayList<Integer> empty = new ArrayList<Integer> ();
        app.fruits.setFruits (empty);
        app.fruits.setSuperFruits (empty);
        app.draw();
        assertEquals (true, app.fruits.hasWon());
    }

    // This is testing different scenarios in which game could be won.
    @Test
    public void gameWon3 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        ArrayList<Integer> empty = new ArrayList<Integer> ();
        app.fruits.setSuperFruits (empty);
        app.draw();
        assertEquals (false, app.fruits.hasWon());
    }

    // This is testing different scenarios in which game could be won.
    @Test
    public void gameWon4 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        ArrayList<Integer> empty = new ArrayList<Integer> ();
        app.fruits.setFruits (empty);
        app.draw();
        assertEquals (false, app.fruits.hasWon());
    }

    // Testing if it is correctly removing the eaten fruits or not.
    // In this case the asked fruits are not present.
    @Test 
    public void removeFruit () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        fruit.removeFruits (336, 450, "simple");
        assertEquals (336, fruit.getFruits ().get (0));
    }

    // Testing if it is correctly removing the eaten super fruits or not.
    // In this case the asked super fruits are not present.
    @Test 
    public void removeSuperFruits () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        fruit.removeFruits (388, 450, "super");
        assertEquals (388, fruit.getSuperFruits ().get (0));
    }

    // Testing if it is correctly removing the eaten fruits or not.
    // In this case the asked fruits are present.
    @Test 
    public void checkRemoveFruits () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        ArrayList<Integer> fruits = new ArrayList<Integer> ();
        ArrayList<Integer> superFruit = new ArrayList<Integer> ();
        fruit.removeFruits (336, 416, "simple");
        assertEquals (2, fruit.getFruits ().size ());
    }

    // Testing if it is correctly removing the eaten super fruits or not.
    // In this case the asked super fruits are present.
    @Test 
    public void checkRemoveSuperFruits () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        ArrayList<Integer> fruits = new ArrayList<Integer> ();
        ArrayList<Integer> superFruit = new ArrayList<Integer> ();
        fruit.removeFruits (388, 420, "super");
        assertEquals (4, fruit.getSuperFruits ().size ());
    }

    // It is testing the time at which the frightened mode starts, that is 
    // the time at which a super fruit has been eaten.
    @Test 
    public void startingFrightenedMode () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        long time = System.currentTimeMillis() / 1000;
        fruit.frightenedModeStart ();
        assertEquals (time, fruit.getFrightenedStartTime ());
    }

}