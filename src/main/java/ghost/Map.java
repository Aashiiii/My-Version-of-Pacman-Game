package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 *  Represents map (game grid) object in game.
 */

public class Map {

// Variable to store the PImage of down left grid space.
private PImage downLeft;

// Variable to store the PImage of down right grid space.
private PImage downRight;

// Variable to store the PImage of up left grid space.
private PImage upLeft;

// Variable to store the PImage of up right grid space.
private PImage upRight;

// Variable to store the PImage of horizontal grid space.
private PImage horizontal;

// Variable to store the PImage of vertical grid space.
private PImage vertical;

// Variable to store the name of the map text file.
private String mapfile;

// Variable to store the x-axis position, followed by y-axis position of down left 
// grid blocks.
private ArrayList<Integer> downL;

// Variable to store the x-axis position, followed by y-axis position of down right 
// grid blocks.
private ArrayList<Integer> downR;

// Variable to store the x-axis position, followed by y-axis position of up left 
// grid blocks.
private ArrayList<Integer> upL;

// Variable to store the x-axis position, followed by y-axis position of up right 
// grid blocks.
private ArrayList<Integer> upR;

// Variable to store the x-axis position, followed by y-axis position of horizontal 
// grid blocks.
private ArrayList<Integer> hor;

// Variable to store the x-axis position, followed by y-axis position of vertical 
// grid blocks.
private ArrayList<Integer> ver;

// Variable of type Config.
private Config config;

/**
     *  Constructs a new Map object.
     * 
     *  @param downLeft         PImage of down left grid spaces.
     *  @param downRight        PImage of down right grid spaces.
     *  @param upLeft           PImage of up left grid spaces.
     *  @param upRight          PImage of up right grid spaces.
     *  @param horizontal       PImage of horizontal grid spaces.
     *  @param vertical         PImage of vertical grid spaces.
     *  @param config           Object of type Config.
     * 
     */

// Constructor.
public Map (PImage downLeft, PImage downRight, PImage upLeft, PImage upRight, 
                    PImage horizontal, PImage vertical, Config config) {

        this.downLeft = downLeft;
        this.downRight = downRight;
        this.upLeft = upLeft;
        this.upRight = upRight;
        this.horizontal = horizontal;
        this.vertical = vertical;

        this.config = config;

        downL = new ArrayList<Integer> ();
        downR = new ArrayList<Integer> ();
        upL = new ArrayList<Integer> ();
        upR = new ArrayList<Integer> ();
        hor = new ArrayList<Integer> ();
        ver = new ArrayList<Integer> ();

}


/**
*  It is called sixty (frame rate) times per second. It performs all the necessary 
*  calculations for Map objects.
*/

public void tick () {
       
        // Gets the different grid spaces array list using the Config object.
        this.hor = config.getHor ();
        this.ver = config.getVer ();
        this.downL = config.getDownL ();
        this.downR = config.getDownR ();
        this.upL = config.getUpL ();
        this.upR = config.getUpR ();

}


/**
 *  It is the draw method which is called sixty (frame rate) times per second. 
 *  It draws the image of all the grid spaces on the screen, setting up the map for 
 *  the game.
 * 
 *  @param app PApplet app object.
 */

// Draw meethod.
public void draw (PApplet app) {
    
        // Drawing all the down left grid spaces on the map at the required position.
        for (int i = 0; i < downL.size () - 1; i += 2) {
                int row = (int) downL.get (i);
                int col = (int) downL.get (i + 1);
                app.image (this.downLeft, row, col);
                
        }

         // Drawing all the down right grid spaces on the map at the required position.
        for (int i = 0; i < downR.size () - 1; i += 2) {
                int row = (int) downR.get (i);
                int col = (int) downR.get (i +1 );
                app.image (this.downRight, row, col);
        }

         // Drawing all the up left grid spaces on the map at the required position.
        for (int i = 0; i < upL.size () - 1; i += 2) {
                int row = (int) upL.get (i);
                int col = (int) upL.get (i + 1);
                app.image (this.upLeft, row, col);
        }

         // Drawing all the up right grid spaces on the map at the required position.
        for (int i = 0; i < upR.size () - 1; i += 2) {
                int row = (int) upR.get (i);
                int col = (int) upR.get (i + 1);
                app.image (this.upRight, row, col);
        }

         // Drawing all the horizontal grid spaces on the map at the required position.
        for (int i = 0; i < hor.size () - 1; i += 2) {
                int row = (int) hor.get (i);
                int col = (int) hor.get (i + 1);
                app.image (this.horizontal, row, col);
        }

         // Drawing all the vertical grid spaces on the map at the required position.
        for (int i = 0; i < ver.size () - 1; i += 2) {
                int row = (int) ver.get (i);
                int col = (int) ver.get (i + 1);
                app.image (this.vertical, row, col);
        }
}

}