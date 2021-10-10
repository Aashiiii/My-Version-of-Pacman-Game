package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

class GhostTest {
    
    // Testing the constructor for Ghost class.
    @Test 
    public void constructor () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        assertNotNull (new Ghost (null, null, null, null, waka, null, config) );
    }
    
    // Testing the x-axix target position of ambusher.
    @Test 
    public void checkTargetXAmbusher () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetxAmbusher ());
    }

    // Testing the y-axix target position of ambusher.
    @Test 
    public void checkTargetYAmbusher () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetyAmbusher ());
    }

    // Testing the x-axix target position of chaser.
    @Test 
    public void checkTargetXChaser () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetxChaser ());
    }

    // Testing the y-axix target position of chaser.
    @Test 
    public void checkTargetYChaser () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetyChaser ());
    }

    // Testing the x-axix target position of ignorant.
    @Test 
    public void checkTargetXIgnorant () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetxIgnorant ());
    }

    // Testing the y-axix target position of ignorant.
    @Test 
    public void checkTargetYIgnorant () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetyIgnorant ());
    }

    // Testing the x-axix target position of whim.
    @Test 
    public void checkTargetXWhim () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetxWhim ());
    }

    // Testing the y-axix target position of whim.
    @Test 
    public void checkTargetYWhim () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTargetyWhim ());
    }

    // Testing the round of mode lengths, the ghost is on.
    @Test 
    public void getRoundNumber () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getRoundNumber ());
    }

    // Testing the positions ambusher goes to trying to chase the target 
    // in both scatter, and chase mode.
    @Test 
    public void getTargetsAmbusher () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (48, ghost.getTargetsAmbusher ().get (0));
    }

    // Testing the positions chaser goes to trying to chase the target 
    // in both scatter, and chase mode.
    @Test 
    public void getTargetsChaser () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (80, ghost.getTargetsChaser ().get (0));
    }

    // Testing the positions ignorant goes to trying to chase the target 
    // in both scatter, and chase mode.
    @Test 
    public void getTargetsIgnorant (){
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (96, ghost.getTargetsIgnorant ().get (0));
    }
    
    // Testing the positions whim goes to trying to chase the target 
    // in both scatter, and chase mode.
    @Test 
    public void getTargetsWhim () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (64, ghost.getTargetsWhim ().get (0));
    }

    // Testing if it is correctly identifying when ambusher is alive or not.
    // That is when it collides with a waka, but is not in frightened mode.
    @Test 
    public void isAmbusherAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (true, ghost.isAmbusherAlive ());
    }

    // Testing if it is correctly identifying when chaser is alive or not.
    // That is when it collides with a waka, but is not in frightened mode.
    @Test 
    public void isChaserAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (true, ghost.isChaserAlive ());
    }

    // Testing if it is correctly identifying when ignorant is alive or not.
    // That is when it collides with a waka, but is not in frightened mode.
    @Test 
    public void isIgnorantAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (true, ghost.isIgnorantAlive ());
    }

    // Testing if it is correctly identifying when whim is alive or not.
    // That is when it collides with a waka, but is not in frightened mode.
    @Test 
    public void isWhimAlive () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (true, ghost.isWhimAlive ());
    }

    // Testing if it is correctly calculating the time spent in frightened mode or not.
    // This test checks when no frightened mode has happened yet.
    @Test 
    public void getTimeOffset () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.getTimeOffset ());
    }


    // Testing if it is correctly calculating the time spent in frightened mode or not.
    // This case is testing the situation when ghost has entered, and completed
    // a frightened mode.
    @Test 
    public void checkTimeOffset2 () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        ghost.addFrightTime ((long) 100);
        ghost.addFrightTime ((long) 200);
        assertEquals (40, ghost.getTimeOffset () );
    }

    // Testing if it is correctly calculating the time spent in frightened mode or not.
    // This case is testing the situation when ghost enters another frightened mode
    // when it is already in one.
    @Test 
    public void checkTimeOffset3 () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        long timeNow = System.currentTimeMillis() / 1000;
        ghost.addFrightTime (timeNow - 5);
        assertEquals (5, ghost.getTimeOffset () );
    }

    // Testing if it is correctly modifying the list of mode lengths, and returning
    // the correct new one or not.
    @Test 
    public void modeLengthAdjuster () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (0, ghost.modeLengthAdjuster (config.getModeLength () ).get (0));
    }

    // Testing if it is returning the correct frightened length or not.
    @Test 
    public void checkFrightenedLength () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        assertEquals (20, ghost.getFrightenedLength () );
    }

    // Testing if it is correctly updating the frightened length or not.
    @Test 
    public void updatingFrightenedLength () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        ghost.setFrightened ( true);
        assertEquals (true, ghost.getFrightened () );
    }

    // It tests that the list of times at which frightened mode starts, or ends is 
    // correct or not.
    @Test 
    public void checkFrightTimes () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        ArrayList<Long> expected =  new ArrayList<Long> ();
        expected.add ((long) 200);
        ghost.setFrightTimes (expected);
        assertEquals (expected, ghost.getFrightTimes () );
    }

    //  It tests whether it is correctly adding the frightened mode start, and end time or not.
    @Test 
    public void checkAddFrightTimes () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        ghost.addFrightTime ((long) 100);
        assertEquals (2, ghost.getFrightTimes ().size () );
    }

    // It tests whether it is correctly adding the frightene mode start, end time or not
    // when some times are already present.
    @Test 
    public void checkAddFrightTimes2 () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        ghost.addFrightTime ((long) 100);
        ghost.addFrightTime ((long) 200);
        assertEquals (4, ghost.getFrightTimes ().size () );
    }

    // It tests whether it is correctly adding the frightene mode start, end time or not
    // when ghost is already in frightened mode, and waka eats another super fruit.
    @Test 
    public void checkAddFrightTimes3 () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        ghost.addFrightTime ((long) 100);
        ghost.addFrightTime ((long) 200);
        ghost.addFrightTime ((long) 150);
        assertEquals (4, ghost.getFrightTimes ().size () );
    }

    // It tests whether it is correctly adding the frightene mode start, end time or not
    // when waka eats two super fruits within a very short time.
    @Test 
    public void checkAddFrightTimes4 () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        Waka waka = new Waka (null, null, null, null, null, fruit, config);
        Ghost ghost = new Ghost (null, null, null, null, waka, null, config);
        ghost.addFrightTime ((long) 100);
        ghost.addFrightTime ((long) 110);
        assertEquals (2, ghost.getFrightTimes ().size () );
    }

    // It is testing the general movement of ghost.
    @Test
    public void checkMove () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        app.ghost.move (timeNow - 2000);
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing the movement of ghost when waka, and ghost are aligned so that
    // the ghosts targets change.
    @Test
    public void checkMove1 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        app.ghost.move (timeNow - 2000);
        app.waka.move(37, 60);
        app.ghost.move (timeNow - 2000);
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing the movement of ghost when waka, and ghost are aligned so that
    // the ghosts targets change.
    @Test
    public void checkMove2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        app.ghost.move (timeNow - 2000);
        app.waka.move(38, 60);
        app.ghost.move (timeNow - 2000);
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing the movement of ghost when waka, and ghost are aligned so that
    // the ghosts targets change.
    @Test
    public void checkMove3 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        app.ghost.move (timeNow - 2000);
        app.waka.move(39, 60);
        app.ghost.move (timeNow - 2000);
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing that the ambusher does not dies when it is not in frightened mode.
    @Test
    public void dieGhost1 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.ghost.dieGhost ("ambusher");
        assertEquals (false, app.ghost.isAmbusherAlive () );
    }

    // It is testing that the chaser does not dies when it is not in frightened mode.
    @Test
    public void dieGhost2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.ghost.dieGhost ("chaser");
        assertEquals (false, app.ghost.isChaserAlive () );
    }

    // It is testing that the ignorant does not dies when it is not in frightened mode.
    @Test
    public void dieGhost3 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        app.ghost.dieGhost ("ignorant");
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing that the whim does not dies when it is not in frightened mode.
    @Test
    public void dieGhost4 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        app.ghost.dieGhost ("whim");
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing the case when ghost, and waka can collide in different cases.
    @Test
    public void moveGhostAndWaka () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        for (int i=0; i<4; i++){
           app.waka.move(39, 60);
        }
        app.ghost.move (timeNow - 2000);
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing the case when ghost, and waka can collide in different cases.
    @Test
    public void moveGhostAndWaka2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        for (int i=0; i<4; i++){
            app.waka.move(37, 60);
         }
        app.ghost.move (timeNow - 2000);
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // It is testing the case when ghost, and waka can collide in different cases.
    @Test
    public void moveGhostAndWaka3 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        for (int i=0; i<9; i++){
            app.waka.move(39, 60);
         }
        assertEquals (0, app.ghost.getTimeOffset () );
    }

    // Testing the case when ghost moves in frightened mode selecting its direction 
    // of movement at intersection at random.
    @Test
    public void moveFrightenedGhost () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        for (int i=0; i<4; i++){
            app.waka.move(37, 60);
         }
        app.ghost.setFrightened(true);
        app.ghost.move (timeNow - 2000);
        assertEquals (true, app.ghost.isChaserAlive () );
    }

    // It is testing the ghost debug mode.
    @Test
    public void checkDebugMode () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.debug = 11;
        app.draw();
        assertEquals (true, app.ghost.isChaserAlive () );
    }

    
}

