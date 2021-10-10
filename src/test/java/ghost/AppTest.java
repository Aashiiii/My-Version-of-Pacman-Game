package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

class AppTest {

// Checking the constructor of App class.
@Test
public void simpleTest () {
        App classUnderTest = new App();
        assertNotNull (classUnderTest);
}

// Checking that the App class is assigning the correct start time or not.
// It also sees if it is drawing the start stage correctly on the screen or not.
@Test
    public void checkFunctionality () {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        long timeNow = System.currentTimeMillis() / 1000;
        long startTime = app.time;
        assertEquals (startTime, timeNow);
    }

// The rest of the App class cases are covered in other test cases, 
// as we need to change the values related to ghost, waka, and fruit
// to see if the app is drawing them to the screen correctly or not.
    
}
