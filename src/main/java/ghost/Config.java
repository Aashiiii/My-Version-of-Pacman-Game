

package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 *  Reads in the json file, and manipulates the map file to create different map attributes.
 */

public class Config {

private String mapfile;

// Variable to store the number of lives.
private long lives;

// Variable to store the speed of waka, and ghost.
private long speed;

// Variable to store the duration of one round of ghosts's mode lengths.
private long duration;

// Variable to store the length of the frightened mode.
private long frightenedLength;

// Variable to store the starting x-axis position of waka.
private long wakaStartX;

// Variable to store the starting y-axis position of waka.
private long wakaStartY;

// Variable to store the ghost mode lengths.
private ArrayList<Long> modeLength;

// Variable to store the x-axis position, followed by y-axis position of ambusher 
// ghosts in the form of a list.
private ArrayList<Long> ghostsAmbusher;

// Variable to store the x-axis position, followed by y-axis position of chaser 
// ghosts in the form of a list.
private ArrayList<Long> ghostsChaser;

// Variable to store the x-axis position, followed by y-axis position of ignorant 
// ghosts in the form of a list.
private ArrayList<Long> ghostsIgnorant;

// Variable to store the x-axis position, followed by y-axis position of whim 
// ghosts in the form of a list.
private ArrayList<Long> ghostsWhim;

// Variable to store the x-axis position, followed by y-axis position of 
// down left grid blocks.
private ArrayList<Integer> downL;

// Variable to store the x-axis position, followed by y-axis position of 
// down right grid blocks.
private ArrayList<Integer> downR;

// Variable to store the x-axis position, followed by y-axis position of 
// up left grid blocks.
private ArrayList<Integer> upL;

// Variable to store the x-axis position, followed by y-axis position of 
// up right grid blocks.
private ArrayList<Integer> upR;

// Variable to store the x-axis position, followed by y-axis position of 
// horizontal grid blocks.
private ArrayList<Integer> hor;

// Variable to store the x-axis position, followed by y-axis position of 
// vertical grid blocks.
private ArrayList<Integer> ver;

// Variable to store the x-axis position, followed by y-axis position of 
// fruits on map.
private ArrayList<Integer> fruits;

// Variable to store the x-axis position, followed by y-axis position of 
// super fruits on map.
private ArrayList<Integer> superFruits;


/**
 *  Constructs a new Config object.
 * 
 *  @param filename of the json file.
 */

// Constructor.
public Config (String filename) {

        fruits = new ArrayList<Integer> ();
        superFruits = new ArrayList<Integer> ();
        modeLength = new ArrayList<Long> ();
        ghostsAmbusher = new ArrayList<Long> ();
        ghostsIgnorant = new ArrayList<Long> ();
        ghostsChaser = new ArrayList<Long> ();
        ghostsWhim = new ArrayList<Long> ();

        this.hor = new ArrayList<Integer> ();
        this.ver = new ArrayList<Integer> ();
        this.downL = new ArrayList<Integer> ();
        this.downR = new ArrayList<Integer> ();
        this.upL = new ArrayList<Integer> ();
        this.upR = new ArrayList<Integer> ();

        JSONParser parser = new JSONParser ();
        
        // Reading different attributes from the json file.
        try {
                Object obj = parser.parse (new FileReader (filename) );
                JSONObject jsonObject = (JSONObject) obj;

                // Reading map filename from json file.
                this.mapfile = (String) jsonObject.get ("map");

                // Reading number of lives from json file.
                this.lives = (long) jsonObject.get ("lives");

                // Reading speed of waka, and ghost from json file.
                this.speed = (long) jsonObject.get ("speed");

                // Reading length of frightened ghost mode from json file.
                this.frightenedLength = (long) jsonObject.get ("frightenedLength");

                // Reading mode lengths for the ghost from json file.
                JSONArray jsonArray = (JSONArray) jsonObject.get ("modeLengths");

                
                // Storing the mode lengths in the form of an ArrayList.
                for (int i = 0; i < jsonArray.size(); i++) {
                        modeLength.add ( (long) jsonArray.get (i) );
                        
                        // Calculating the duration by adding all the values of 
                        // mode lengths.
                        duration += (long) jsonArray.get (i);
                }
        
        } catch (Exception e) {
                System.out.println ("Error when reading file");
        }

        BufferedReader reader;
        try {

                reader = new BufferedReader (new FileReader (this.mapfile) );

                // Storing the first line of the file.
                String line = reader.readLine ();
                int lineNumber = 0;
                

                // Reading the contents of the map file.
                while (line != null) {

                        for(int i = 0; i < line.length (); i++) {

                                // Extracting every character of each line of the file.
                                char c = line.charAt (i);
                                

                                // Comparing different characters of the file, 
                                // and adding their x-axix, and y-axix position to their 
                                // particular variables.

                                if (c == '7') {

                                        fruits.add ( (Integer) i*16);
                                        fruits.add ( (Integer) lineNumber*16);

                                } else if (c == '8') {

                                        superFruits.add ( (Integer) i*16 + 4);
                                        superFruits.add ( (Integer) lineNumber*16 + 4);

                                } else if(c == 'p') {

                                        wakaStartX = (long)i*16;
                                        wakaStartY = (long)lineNumber*16-5;

                                } else if(c == 'a') {

                                        ghostsAmbusher.add ( (long) i*16);
                                        ghostsAmbusher.add ( (long) (lineNumber*16 - 5) );

                                } else if(c == 'w') {

                                        ghostsWhim.add ( (long) i*16);
                                        ghostsWhim.add ( (long) (lineNumber*16 - 5) );

                                } else if(c == 'i') {

                                        ghostsIgnorant.add ( (long) i*16);
                                        ghostsIgnorant.add ( (long) (lineNumber*16 - 5) );

                                } else if(c == 'c') {

                                        ghostsChaser.add ( (long) i*16);
                                        ghostsChaser.add ( (long) (lineNumber*16 - 5) );

                                } else if(c == '5') {

                                        downL.add ( (Integer) i*16 );
                                        downL.add ( (Integer) lineNumber*16);

                                } else if(c == '2') {

                                        ver.add ( (Integer) i*16);
                                        ver.add ( (Integer) lineNumber*16);

                                } else if(c == '1') {

                                        hor.add ( (Integer) i*16);
                                        hor.add ( (Integer) lineNumber*16);

                                } else if(c == '3') {

                                        upL.add ( (Integer) i*16);
                                        upL.add ( (Integer) lineNumber*16);

                                } else if(c == '4') {

                                        upR.add ( (Integer) i*16);
                                        upR.add ( (Integer) lineNumber*16);

                                } else if(c == '6') {

                                        downR.add ( (Integer) i*16);
                                        downR.add ( (Integer) lineNumber*16);

                                }
                        }
 
                        // Reading next line of the file.
                        line = reader.readLine (); 

                        // Stores the line number of the line of the file being read.
                        lineNumber += 1; 
                }
                reader.close ();
        
            } catch (IOException e) {
                e.printStackTrace ();
        }

}

/**
 *  Returns the name of the map text file.
 * 
 *  @return Map file name.
 */

// Getter method.
public String getMapFile () {
        return mapfile;
}

/**
 *  Returns the number of lives that waka has.
 * 
 *  @return Number of lives.
 */

// Getter method.
public long getLives () {
        return lives;
}

/**
 *  Returns the speed of waka, and ghost in pixels.
 * 
 *  @return Speed of waka, and ghost.
 */

// Getter method.
public long getSpeed () {
        return speed;
}

/**
 *  Returns the mode lengths for the ghost.
 * 
 *  @return Mode lengths of ghost.
 */

// Getter method.
public ArrayList<Long> getModeLength () {
        return modeLength;
}

/**
 *  Returns the length of frightened mode for the ghost.
 * 
 *  @return Frightened mode length.
 */

// Getter method.
public long getFrightenedLength () {
        return frightenedLength;
}

/**
 *  Returns the array list of the x-axis position, followed by y-axis position of 
 *  fruits on map.
 * 
 *  @return Positions of fruits.
 */

// Getter method.
public ArrayList<Integer> getFruits () {
        return fruits;
}

/**
 *  Returns the array list of the x-axis position, followed by y-axis position of 
 *  super fruits on map.
 * 
 *  @return Positions of super fruits.
 */

// Getter method.
public ArrayList<Integer> getSuperFruits () {
        return superFruits;
}

/**
 *  Returns the x-axis position, followed by y-axis position of ambusher ghosts 
 *  in the form of a list.
 * 
 *  @return Position of ambusher ghosts.
 */

// Getter method.
public ArrayList<Long> getGhostsAmbusher () {
        return ghostsAmbusher;
}

/**
 *  Returns the x-axis position, followed by y-axis position of chaser ghosts 
 *  in the form of a list.
 * 
 *  @return Position of chaser ghosts.
 */

// Getter method.
public ArrayList<Long> getGhostsChaser () {
        return ghostsChaser;
}

/**
 *  Returns the x-axis position, followed by y-axis position of ignorant ghosts 
 *  in the form of a list.
 * 
 *  @return Position of ignorant ghosts.
 */

// Getter method.
public ArrayList<Long> getGhostsIgnorant () {
        return ghostsIgnorant;
}

/**
 *  Returns the x-axis position, followed by y-axis position of whim ghosts 
 *  in the form of a list.
 * 
 *  @return Position of whim ghosts.
 */

// Getter method.
public ArrayList<Long> getGhostsWhim () {
        return ghostsWhim;
}

/**
 *  Returns the current x-axis position of waka.
 * 
 *  @return x-axis position of waka.
 */

// Getter method.
public long getWakaStartX () {
    return wakaStartX;
}

/**
*  Returns the current y-axis position of waka.
*
*  @return y-axis position of waka.
*/

// Getter method.
public long getWakaStartY () {
    return wakaStartY;
}

/**
*  Returns the time it takes for the ghost to complete one round of mode lengths.
*
*  @return Duration of one mode length round.
*/

// Getter method.
public long getDuration () {
    return duration;
}

/**
*  Returns the array list of the x-axis position, followed by y-axis position of 
*  down left grid blocks.   
*
*  @return Positions of down left grid space.
*/

// Getter method.
public ArrayList<Integer> getDownL () {
    return downL;
}

/**
*  Returns the array list of the x-axis position, followed by y-axis position of 
*  up left grid blocks.
* 
*  @return Positions of up left grid space.
*/

// Getter method.
public ArrayList<Integer> getUpL () {
    return upL;
}

/**
*  Returns the array list of the x-axis position, followed by y-axis position of
*  down right grid blocks.
*
*  @return Positions of down right grid space.
*/

// Getter method.
public ArrayList<Integer> getDownR () {
    return downR;
}

/**
*  Returns the array list of the x-axis position, followed by y-axis position of 
*  up right grid blocks.
*
*  @return Positions of up right grid space.
*/

// Getter method.
public ArrayList<Integer> getUpR () {
    return upR;
}

/**
*  Returns the array list of the x-axis position, followed by y-axis position of 
*  horizontal grid blocks.
*
*  @return Positions of horizontal grid space.
*/

// Getter method.
public ArrayList<Integer> getHor () {
    return hor;
}

/**
*  Returns the array list of the x-axis position, followed by y-axis position of 
*  vertical grid blocks.
*
*  @return Positions of vertical grid space.
*/

// Getter method.
public ArrayList<Integer> getVer () {
    return ver;
}


}