package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

class WakaTest {

    // Testing the constructor of Waka class.
    @Test 
    public void constructor () {
        Config config = new Config ("configTest.json");
        Fruit fruit = new Fruit (null, null, config);
        assertNotNull (new Waka (null, null, null, null, null, fruit, config) );
    }

    // This test checks the waka's movement when it moves left, and does not collides.    
    @Test
    public void checkMove () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.waka.move(37, 60);
        assertEquals ("left", app.waka.getMovingDirection () );
    }

    // This test checks the waka's movement when it moves upwards, and does not collides.
    @Test
    public void checkMove2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.waka.move(38, 60);
        assertEquals ("up", app.waka.getMovingDirection () );
    }

    // This test checks the waka's movement when it moves right, and does not collides.
    @Test
    public void checkMove3 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.waka.move(39, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // This test checks the waka's movement when it tries to move down, but it collides
    // so it stays in it's starting position.
    @Test
    public void checkMove4 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.waka.move(40, 60);
        assertEquals ("start", app.waka.getMovingDirection () );
    }

    // It is testing different cases of waka's movement where it moves, collides, stops, and moves.
    @Test
    public void checkMove5 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.waka.move(40, 60);
        app.waka.move(37, 60);
        app.waka.move(40, 60);
        app.waka.move(37, 60);
        app.waka.move(40, 60);
        assertEquals ("left", app.waka.getMovingDirection () );
    }

    // It is testing different variations of waka's moves.
    @Test
    public void checkMove6 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.waka.move(38, 60);
        app.waka.move(38, 60);
        app.waka.move(38, 60);
        app.waka.move(38, 60);
        app.waka.move(38, 60);
        assertEquals ("up", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison after it has already moved a few steps.
    // This way it is testing collison in different cases, making sure waka does
    // not collides with wall in any part of the game.
    @Test
    public void checkCollison () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int i=0; i<2; i++){
            app.waka.move(39, 60);
        }
        app.waka.move(40, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison after it has already moved a few steps.
    // This way it is testing collison in different cases, making sure waka does
    // not collides with wall in any part of the game.
    @Test
    public void checkCollison2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int i=0; i<3; i++){
            app.waka.move(39, 60);
        }
        app.waka.move(40, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison after it has already moved a few steps.
    // This way it is testing collison in different cases, making sure waka does
    // not collides with wall in any part of the game.
    @Test
    public void checkCollison3 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int i=0; i<4; i++){
            app.waka.move(39, 60);
        }
        app.waka.move(40, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison after it has already moved a few steps.
    // This way it is testing collison in different cases, making sure waka does
    // not collides with wall in any part of the game.
    @Test
    public void checkCollison4 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int i=0; i<5; i++){
            app.waka.move(39, 60);
        }
        app.waka.move(40, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison after it has already moved a few steps.
    // This way it is testing collison in different cases, making sure waka does
    // not collides with wall in any part of the game.
    @Test
    public void checkCollison5 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int i=0; i<6; i++){
            app.waka.move(39, 60);
        }
        app.waka.move(40, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison after it has already moved a few steps.
    // This way it is testing collison in different cases, making sure waka does
    // not collides with wall in any part of the game.
    @Test
    public void checkCollison6 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int i=0; i<7; i++){
            app.waka.move(39, 60);
        }
        app.waka.move(40, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison after it has already moved a few steps.
    // This way it is testing collison in different cases, making sure waka does
    // not collides with wall in any part of the game.
    @Test
    public void checkCollison7 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int i=0; i<8; i++){
            app.waka.move(39, 60);
        }
        app.waka.move(40, 60);
        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is testing waka's collison when it has moved, and reached different palces
    // of the grid, in different directions.
    @Test
    public void checkCollisonMixed () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        for (int j =9; j<35; j++) {
            app.setup();
            for (int i=0; i< j; i++) {
                app.waka.move(39, 60);
            }
            app.waka.move(40, 60);
        }
        assertEquals ("down", app.waka.getMovingDirection () );
    }

    // It is specifically testing waka's collision upwards in different situations.
    @Test
    public void checkCollisonUp () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        for (int j =2; j<36; j++) {
            app.setup();
            for (int i=0; i< j; i++) {
                app.waka.move(39, 60);
                app.waka.move(38, 60);
            }
            app.waka.move(38, 60);
            app.waka.move(38, 60);
            app.waka.move(38, 60);
        }
        assertEquals ("up", app.waka.getMovingDirection () );
    }

    // It is specifically testing waka's collision towards right in different situations.
    @Test
    public void checkCollisonRight () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int j =2; j<36; j++) {
            app.waka.move(39, 60);
        }
            
        for (int i=0; i< 15; i++) {
            app.waka.move(40, 60);
            app.waka.move(39, 60);
        }

        app.waka.move(40, 60);

        assertEquals ("right", app.waka.getMovingDirection () );
    }

    // It is specifically testing waka's collision towards right in different situations.
    @Test
    public void checkCollisonRight2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.noLoop();
        for (int j =2; j<36; j++) {
            app.waka.move(39, 60);
        }
            
        for (int i=0; i< 15; i++) {
            app.waka.move(38, 60);
            app.waka.move(39, 60);
        }

        app.waka.move(38, 60);

        assertEquals ("up", app.waka.getMovingDirection () );
    }

    // It is specifically testing waka's collision towards left in different situations.
    @Test
    public void checkCollisonLeft () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        for (int j =2; j<36; j++) {
            app.waka.move(37, 60);
        }
            
        for (int i=0; i< 15; i++) {
            app.waka.move(40, 60);
            app.waka.move(37, 60);
        }

        app.waka.move(40, 60);

        assertEquals ("left", app.waka.getMovingDirection () );
    }

    // It is specifically testing waka's collision towards left in different situations.
    @Test
    public void checkCollisonLeft2 () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.noLoop();
        for (int j =2; j<36; j++) {
            app.waka.move(37, 60);
        }
            
        for (int i=0; i< 15; i++) {
            app.waka.move(38, 60);
            app.waka.move(37, 60);
        }

        app.waka.move(38, 60);

        assertEquals ("left", app.waka.getMovingDirection () );
    }

}