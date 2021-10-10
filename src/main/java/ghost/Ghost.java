package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.lang.System.*;
import java.lang.Math;


/**
 *  Represents ghost object in game.
 */

public class Ghost {

// Variable to store the PImage of the ambusher ghost.
private PImage ambusher;

// Variable to store the PImage of the chaser ghost.
private PImage chaser;

// Variable to store the PImage of the ignorant ghost.
private PImage ignorant;

// Variable to store the PImage of the whim ghost.
private PImage whim;

// Variable to store the PImage of the frightened ghost.
private PImage fright;

// Variable to store the x-axis position, followed by y-axis position of the ambusher 
// on map in the form of an array list.
private ArrayList<Long> ghostsAmbusher;

// Variable to store the x-axis position, followed by y-axis position of the chaser 
// on map in the form of an array list.
private ArrayList<Long> ghostsChaser;

// Variable to store the x-axis position, followed by y-axis position of the ignorant 
// on map in the form of an array list.
private ArrayList<Long> ghostsIgnorant;

// Variable to store the x-axis position, followed by y-axis position of the whim on 
// map in the form of an array list.
private ArrayList<Long> ghostsWhim;

// Variable to store the x-axis position, followed by y-axis position of the ambusher 
// when chasing the target.
private ArrayList<Long> targetsAmbusher;

// Variable to store the x-axis position, followed by y-axis position of the chaser 
// when chasing the target.
private ArrayList<Long> targetsChaser;

// Variable to store the x-axis position, followed by y-axis position of the ignorant 
// when chasing the target.
private ArrayList<Long> targetsIgnorant;

// Variable to store the x-axis position, followed by y-axis position of the whim 
// when chasing the target .
private ArrayList<Long> targetsWhim;

// Variable to store the mode lengths of the ghost.
private ArrayList<Long> modeLength;

// Variable to store the time at which the frightened mode started, and ended.
private ArrayList<Long> frightTimes;

// Variable to store which round of mode lengths is the ghost on.
private int roundNumber;

// Variable to store the name of the  map text file.
private String mapfile;

// Variable to store the length of each round of mode lengths.
private long length;

// Variable to store the current time.
private long time;

// Variable to store the difference between the current time, and the time at which 
// the game started.
private long timeDifference;

// Variable to store the speed of the ghost.
private long speed;

// Variable to store the length of frightened ghost mode.
private long frightenedLength;

// Variable to store the number of times the ghost has entered frightened mode.
private long timesFrightened;

// Variable to store the x-axis position of the target of ambusher.
private long targetxAmbusher;

// Variable to store the y-axis position of the target of ambusher.
private long targetyAmbusher;

// Variable to store the x-axis position of the target of chaser.
private long targetxChaser;

// Variable to store the y-axis position of the target of chaser.
private long targetyChaser;

// Variable to store the x-axis position of the target of ignorant.
private long targetxIgnorant;

// Variable to store the y-axis position of the target of ignorant.
private long targetyIgnorant;

// Variable to store the x-axis position of the target of whim.
private long targetxWhim;

// Variable to store the y-axis position of the target of whim.
private long targetyWhim;

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

// Variable to store the list of moves the ambusher has taken.
private ArrayList<Integer> movesListAmbusher;

// Variable to store the list of moves the chaser has taken.
private ArrayList<Integer> movesListChaser;

// Variable to store the list of moves the ignorant has taken.
private ArrayList<Integer> movesListIgnorant;

// Variable to store the list of moves the whim has taken.
private ArrayList<Integer> movesListWhim;

// Variable to store if the ghost is in frightened mode or not.
private boolean frightened;

// Variable that stores whether the ambusher is alive or not.
private boolean ambusherAlive;

// Variable that stores whether the chaser is alive or not.
private boolean chaserAlive;

// Variable that stores whether the ignorant is alive or not.
private boolean ignorantAlive;

// Variable that stores whether the whim is alive or not.
private boolean whimAlive;

// Variable of type Config.
private Config config;

// Variable of type Waka.
private Waka waka;



/**
 *  Constructs a new Ghost object.
 * 
 * @param ambusher PImage of ambusher ghost.
 * @param ignorant PImage of ignorant ghost.
 * @param chaser PImage of chaser ghost.
 * @param whim PImage of whim ghost.
 * @param waka Object of type Waka.
 * @param fright PImage of ghost in frightened mode.
 * @param config Object of type Config.
 */

// Constructor.
public Ghost ( PImage ambusher,PImage ignorant, PImage chaser, PImage whim, Waka waka, 
                    PImage fright, Config config) {


        this.ambusher = ambusher;
        this.ignorant = ignorant;
        this.whim = whim;
        this.chaser = chaser;
        this.waka = waka;
        this.fright = fright;
        this.config = config;

        this.hor = config.getHor ();
        this.ver = config.getVer ();
        this.downL = config.getDownL ();
        this.downR = config.getDownR ();
        this.upL = config.getUpL ();
        this.upR = config.getUpR ();

        this.movesListAmbusher = new ArrayList<Integer> ();
        this.movesListChaser = new ArrayList<Integer> ();
        this.movesListIgnorant = new ArrayList<Integer> ();
        this.movesListWhim = new ArrayList<Integer> ();

        this.ambusherAlive = true;
        this.chaserAlive = true;
        this.ignorantAlive = true;
        this.whimAlive = true;

        this.length = config.getDuration ();

        ghostsAmbusher = config.getGhostsAmbusher ();
        ghostsIgnorant = config.getGhostsIgnorant ();
        ghostsChaser = config.getGhostsChaser ();
        ghostsWhim = config.getGhostsWhim ();
        modeLength = config.getModeLength ();
        frightTimes = new ArrayList<Long> ();
        roundNumber = 0;

        frightened = false;
        timesFrightened = 0;

        targetxAmbusher = 0;
        targetyAmbusher = 0;
        targetxChaser = 0;
        targetyChaser = 0;
        targetxIgnorant = 0;
        targetyIgnorant = 0;
        targetxWhim = 0;
        targetyWhim = 0;

        this.frightenedLength = config.getFrightenedLength ();
        this.speed = config.getSpeed ();

        targetsAmbusher = new ArrayList<Long> (ghostsAmbusher);
        targetsIgnorant = new ArrayList<Long> (ghostsIgnorant);
        targetsChaser =new ArrayList<Long> (ghostsChaser);
        targetsWhim = new ArrayList<Long> (ghostsWhim);

}


/**
 *  Returns the number of rounds of the mode lengths that the ghost has completed. 
 *  That is it tells how many times the ghost has traversed through the whole mode 
 *  lengths list switching between scatter, and chase mode accordingly.
 * 
 *  @return Mode length round number.
 */

// Getter method.
public int getRoundNumber () {
    return roundNumber;
}

/**
 *  Returns the list of times at which the frightened modes of the ghost started, 
 *  and ended. 
 *  The list would be empty if the ghost has not entered any frightened mode yet.
 * 
 *  @return List of frightened mode start, and end times.
 */

// Getter method.
public ArrayList<Long> getFrightTimes () {
    return frightTimes;
}


/**
 *  Returns true if the ghost is in frightened mode, else it returns false.
 * 
 *  @return Whether ghost is in frightened mode or not.
 */

// Getter method.
public boolean getFrightened () {
    return frightened;
}


/**
 *  Returns the x-axis target position of ambusher.
 *  @return Target x-axis position.
 */


// Getter method.
public long getTargetxAmbusher () {
    return targetxAmbusher;
}

/**
 *  Returns the y-axis target position of ambusher.
 *  @return Target y-axis position.
 */

// Getter method.
public long getTargetyAmbusher () {
    return targetyAmbusher;
}

/**
 *  Returns the x-axis target position of chaser.
 *  @return Target x-axis position.
 */

// Getter method.
public long getTargetxChaser () {
    return targetxChaser;
}

/**
 *  Returns the y-axis target position of chaser.
 *  @return Target y-axis position.
 */

// Getter method.
public long getTargetyChaser () {
    return targetyChaser;
}

/**
 *  Returns the x-axis target position of ignorant.
 *  @return Target x-axis position.
 */

// Getter method.
public long getTargetxIgnorant () {
    return targetxIgnorant;
}

/**
 *  Returns the y-axis target position of ignorant.
 *  @return Target y-axis position.
 */

// Getter method.
public long getTargetyIgnorant () {
    return targetyIgnorant;
}

/**
 *  Returns the x-axis target position of whim.
 *  @return Target x-axis position.
 */

// Getter method.
public long getTargetxWhim () {
    return targetxWhim;
}

/**
 *  Returns the y-axis target position of whim.
 *  @return Target y-axis position.
 */

// Getter method.
public long getTargetyWhim () {
    return targetyWhim;
}

/**
 *  Returns the x-axis position, followed by y-axis position of ambusher ghosts 
 *  in the form of a list based on the target in either scatter or chase mode.
 * 
 *  @return Position of ambusher ghosts.
 */

// Getter method.
public ArrayList<Long> getTargetsAmbusher () {
        return targetsAmbusher;
}

/**
 *  Returns the x-axis position, followed by y-axis position of chaser ghosts 
 *  in the form of a list based on the target in either scatter or chase mode.
 * 
 *  @return Position of chaser ghosts.
 */

// Getter method.
public ArrayList<Long> getTargetsChaser () {
    return targetsChaser;
}

/**
 *  Returns the x-axis position, followed by y-axis position of ignorant ghosts 
 *  in the form of a list based on the target in either scatter or chase mode.
 * 
 *  @return Position of ignorant ghosts.
 */

// Getter method.
public ArrayList<Long> getTargetsIgnorant () {
    return targetsIgnorant;
}

/**
 *  Returns the x-axis position, followed by y-axis position of whim ghosts 
 *  in the form of a list based on the target in either scatter or chase mode.
 * 
 *  @return Position of whim ghosts.
 */

// Getter method.
public ArrayList<Long> getTargetsWhim () {
    return targetsWhim;
}

/**
 *  Returns the length of frightened mode for the ghost.
 *  @return Frightened mode length.
 */

// Getter method.
public long getFrightenedLength () {
    return frightenedLength;
}

/**
 *  Sets the parameter as the ambushers's alive status. 
 *  If the parameter passed is true then it sets that ambusher is alive, 
 *  else if parameter is false then it sets that the ambusher is not alive.
 * 
 *  @param alivee The status telling if ambusher is alive or not.
 */

// Setter method.
public void setAmbusherAlive (Boolean alivee) {
    this.ambusherAlive = alivee;
}

/**
 *  Sets the parameter as the chaser's alive status. 
 *  If the parameter passed is true then it sets that chaser is alive, 
 *  else if parameter is false then it sets that the chaser is not alive.
 * 
 *  @param alivee The status telling if chaser is alive or not.
 */

// Setter method.
public void setChaserAlive (Boolean alivee) {
    this.chaserAlive = alivee;
}

/**
 *  Sets the parameter as the ignorant's alive status. 
 *  If the parameter passed is true then it sets that ignorant is alive, 
 *  else if parameter is false then it sets that the ignorant is not alive.
 * 
 *  @param alivee The status telling if ignorant is alive or not.
 */

// Setter method.
public void setIgnorantAlive (Boolean alivee) {
    this.ignorantAlive = alivee;
}

/**
 *  Sets the parameter as the whim's alive status. 
 *  If the parameter passed is true then it sets that whim is alive, 
 *  else if parameter is false then it sets that the whim is not alive.
 * 
 *  @param alivee The status telling if whim is alive or not.
 */

// Setter method.
public void setWhimAlive (Boolean alivee) {
    this.whimAlive = alivee;
}

/**
 *  Sets the parameter as the list of times at which the frightened modes of 
 *  the ghost started, and ended. 
 * 
 *  @param times List of frightened mode start, and end times.
 */

// Setter method.
public void setFrightTimes (ArrayList<Long> times) {
    this.frightTimes = times;
}

/**
 *  Returns true if the ambusher is alive, else it returns false.
 * 
 *  @return Alive status of ambusher.
 */

// Method that tells whether ambusher is alive or not.
public boolean isAmbusherAlive () {
        return ambusherAlive;
}

/**
 *  Returns true if the ignorant is alive, else it returns false.
 * 
 *  @return Alive status of ignorant.
 */

// Method that tells whether ignorant is alive or not.
public boolean isIgnorantAlive () {
        return ignorantAlive;
}

/**
 *  Returns true if the chaser is alive, else it returns false.
 * 
 *  @return Alive status of chaser.
 */

// Method that tells whether chaser is alive or not.
public boolean isChaserAlive () {
        return chaserAlive;
}

/**
 *  Returns true if the whim is alive, else it returns false.
 * 
 *  @return Alive status of whim.
 */

// Method that tells whether whim is alive or not.
public boolean isWhimAlive () {
        return whimAlive;
}



/**
 *  Returns true if the ghost is in frightened mode, else it returns false;
 * 
 *  @param frightened Frightened status of ghost.
 */

// Setter method.
public void setFrightened (boolean frightened) {
        this.frightened = frightened;
}

/**
 *  Method to add the frightened mode start, and end time to the fright times list.  
 *  The parameter time is the frightened mode start time, or the time when a 
 *  super fruit was eaten.
 * 
 *  @param time Frightened mode start time.
 */


public void addFrightTime (long time) {
        
        // If the length of fright times is 0, that is there are no fright times in
        // the list, then we simply add the time passed as the parameter.
        if (frightTimes.size () == 0) {
                
                // Adding the frightened mode start time passed in as a parameter.
                frightTimes.add (time);

                // Adding the frightened mode end time which is equal to frightened mode 
                // start time + frightened mode length.
                frightTimes.add (time + frightenedLength);
        
        } else {
                
                int length = frightTimes.size ();
                
                // If the frightened start time is greater than the last frightened 
                // end time, then we add the values to the list as done above.
                if (time > frightTimes.get (length - 1) ) {

                        // Adding the frightened mode start time passed in as a 
                        // parameter.
                        frightTimes.add (time);

                        // Adding the frightened mode end time which is equal to 
                        // frightened mode start time + frightened mode length.
                        frightTimes.add (time + frightenedLength);
                
                } else {
                        
                        // If time is graeter than the last frightened mode start time, 
                        // but is less than the frightened mode end time then that denotes 
                        // that the player ate another super fruit while the ghost was 
                        // already in frightened mode.
                        // In that case we just update the previous frightened mode end
                        // time. We replace it with the new frightened mode end time.
                        if (time > frightTimes.get (length - 2) ) {
                                
                                // Replacing the frightened mode end time, with the new
                                // end time.
                                frightTimes.remove (length-1);
                                frightTimes.add (time + frightenedLength);

                        }
                }
        }

}

/**
 * Method that returns the amount of time spent in frightened mode already, 
 * which needs to be adjusted when finding the current ghost mode.
 * After frightened mode the ghost needs to continue from the previous mode, 
 * the time elapsed in the frightened mode should not be taken into account when 
 * calculating the next ghost mode.
 * This gives the amount of time spent in frightened mode already.
 * 
 * @return Time spent in frightened mode.
 */

 
public long getTimeOffset () {
        long timeOffset = 0;
        
        // Adding the times spent in the frightened mode to time offset.
        for (int i = 0; i < frightTimes.size () - 1; i += 2) {
                timeOffset += (frightTimes.get (i + 1) - frightTimes.get (i));
        }
        
        // Stores the current time.
        long timeNow = System.currentTimeMillis() / 1000;

        if (frightTimes.size () != 0) {
                
                // If the current time is less than the last frightened mode end time, 
                // then that means that the ghost is still in frightened mode.
                if (frightTimes.get (frightTimes.size () - 1 ) > timeNow) {

                        // When ghost is still in frightened mode, we reduce the time 
                        // between now and frightened mode end time from the time offset.
                        // This is done beacuse that amount of time is yet to be elapsed.
                        timeOffset -= frightTimes.get (frightTimes.size () - 1) - timeNow;
                }
        }
        return timeOffset;
}



/**
*  This method returns the mode lengths list in a cumulative format.
* 
*  @param modeLength List of mode lengths.
*  @return Cumulative mode lengths.
*/

public ArrayList<Long> modeLengthAdjuster (ArrayList<Long> modeLength) {
        
        // Creating a new list to store cumulative mode lengths.
        ArrayList<Long> modeLengths = new ArrayList<Long> ();

        // The first element needs to be 0.
        modeLengths.add ( (long) 0);
        long sum = 0;
        
        // Finding cumulative mode lengths.
        for (int i= 0; i < modeLength.size (); i++) {
                sum += modeLength.get (i);
                modeLengths.add (sum);
        }
        
        // Returning cumulative mode lengths.
        return modeLengths;
}

/**
 * This method selects and calls the required ghost mode based on the time.
 * The parameter that is passed is the time at which the game started.
 * 
 * 
 * @param startTime Time at which game started.
 */


public void move (long startTime) {
        
        // This variable stores the total time spent in frightened mode.
        long timeOffset = getTimeOffset ();

        // We subtract the offset from the current time, because the time spent in 
        // frightened mode is not counted when selecting the ghost mode- scatter, 
        // or chase.
        time = System.currentTimeMillis () / 1000 - timeOffset;

        // It tells how much time has passed since game started, or the game has been 
        // running since how long.
        timeDifference = time - startTime;
        int n = 0;

        while (true) {
                
                // Finding which round of mode lengths are we on. Here round means 
                // how many times have we traversed the whole mode lengths list, 
                // and have restarted.
                if (timeDifference >= n * length && timeDifference < (n + 1) * length) {
                        roundNumber = n;
                        break;
                }
                n += 1;

        }

        ArrayList<Long> modeLengths = modeLengthAdjuster (modeLength);
        
        // Finding the ghost mode, and calling those particular methods.
        for (int i = 0; i < modeLengths.size () - 1; i++) {
            
                if (timeDifference > (roundNumber) * length + modeLengths.get (i) 
                        && timeDifference <= (roundNumber) * length + modeLengths.get (i + 1)) {
                        
                        if (i % 2 == 0 ) {

                                // Caliing scatter method for all ghosts to find their 
                                // target.
                                scatterAmbusher ();
                                scatterChaser ();
                                scatterWhim ();
                                scatterIgnorant ();

                        } else {

                                // Caliing chase method for all ghosts to find their target.
                                chaseAmbusher ();
                                chaseIgnorant (targetsIgnorant);
                                chaseWhim ();
                                chaseChaser ();

                        } 

                }

                if (timeDifference == 0) {

                        // Caliing scatter method for all ghosts to find their target.
                        scatterAmbusher ();
                        scatterChaser ();
                        scatterWhim ();
                        scatterIgnorant ();
                }

        }
        
        // If the ambusher is alive, then we call the isAlive() method to see if the 
        // ambusher is colliding with waka or not.
        if (isAmbusherAlive() == true) {
            isAlive (targetsAmbusher, ambusher, "ambusher");
        }
        
        // If the chaser is alive, then we call the isAlive() method to see if the 
        // chaser is colliding with waka or not.
        if (isChaserAlive() == true) {
            isAlive (targetsChaser, chaser, "chaser");
        }
        
        // If the ignorant is alive, then we call the isAlive() method to see if the 
        // ignorant is colliding with waka or not.
        if (isIgnorantAlive() == true) {
            isAlive (targetsIgnorant, ignorant, "ignorant");
        }
        
        // If the whim is alive, then we call the isAlive() method to see if the 
        // whim is colliding with waka or not.
        if (isWhimAlive() == true) {
            isAlive (targetsWhim, whim, "whim");
        }

}

/**
 * This method checks if the waka, and the ghost are colliding or not.
 * If the ghost and waka are colliding, and ghost is in frightened mode then the ghost dies.
 * If the collison happens and ghost is not in frightened mode, then waka or player dies.
 * This method calls the functions to kill waka, and ghost when needed.
 * 
 * @param targets Position of ghost whose type is passed as parameter.
 * @param spirit PImage of the ghost whose type is passed.
 * @param type  String telling type of ghost.
 */


public void isAlive (ArrayList<Long> targets, PImage spirit, String type) {

        // Variable that tells if waka is alive or not. Waka is alive if it does not 
        // collides with the ghost.
        Boolean alive = true;

        for (int i = 0; i < targets.size () - 1; i += 2) {
                
                // Finding top, bottom, left, and right coordinates of the ghost, 
                // and waka.
                long ghostleft = targets.get (i);
                long ghosttop = targets.get (i+1);
                long ghostbottom = ghosttop + spirit.height;
                long ghostright = ghostleft + spirit.width;
                long wakaleft = waka.getX ();
                long wakatop = waka.getY ();
                long wakabottom = wakatop + 16;
                long wakaright = wakaleft + 16;
 
                // Checking different conditions to check if waka, and ghost are 
                // colliding or not.
                if (ghostbottom >= wakatop && ghostbottom <= wakabottom 
                        && ghostright <= wakaright && ghostright >= wakaleft) {

                            alive = false;
                }

                if (ghostbottom >= wakatop && ghostbottom <= wakabottom 
                        && ghostleft <= wakaright && ghostleft >= wakaleft) {

                            alive = false;
                }

                if (ghosttop >= wakatop && ghosttop <= wakabottom 
                        && ghostright <= wakaright && ghostright >= wakaleft) {

                            alive = false;
                }

                if (ghosttop >= wakatop && ghostbottom <= wakabottom 
                        && ghostright <= wakaright && ghostleft >= wakaleft) {
                        
                            alive = false;
                }

                if (wakatop <= ghosttop && ghosttop <= wakabottom 
                        && ghostleft <= wakaright && ghostleft >= wakaleft) {
                            
                            alive = false;
                }

                if (wakatop >= ghosttop && wakabottom <= ghostbottom 
                        && wakaright <= ghostright && wakaleft >= ghostleft) {
                        
                            alive = false;
                }

        }
        
        // Waka can die only if it collides with a ghost that is not in frightened mode.
        if (alive == false && frightened == false) {

                waka.die ();

                // After waka dies by colliding with a not frightened ghost, 
                // all the ghosts that died, or dissapeared previously become alive 
                // again.
                this.ambusherAlive = true;
                this.chaserAlive = true;
                this.ignorantAlive = true;
                this.whimAlive = true;
                
                // Ghosts restart from their starting position.
                targetsAmbusher = new ArrayList<Long> (ghostsAmbusher);
                targetsIgnorant = new ArrayList<Long> (ghostsIgnorant);
                targetsWhim = new ArrayList<Long> (ghostsWhim);
                targetsChaser = new ArrayList<Long> (ghostsChaser);
        }
        
        // If waka, and ghost collide, and ghost is in frightened mode, then the 
        // ghost dies.
        if (alive == false && frightened == true) {
                dieGhost (type);
        }
}

/**
 * This method changes the status of the ghost referred to by the ghost type parameter 
 * from alive to not alive. That is it kills the ghost. The ghost's alive status 
 * becomes false.
 * The parameter string "ambusher" refers to ambusher ghost, "chaser" refers to chaser 
 * ghost, "ignorant" refers to ignorant ghost, and "whim" refers to whim ghost.
 * 
 * @param ghostType Type of ghost to be killed.
 */


public void dieGhost (String ghostType) { 

        if (ghostType == "ambusher") {
                ambusherAlive = false;
        }

        if (ghostType == "chaser") {
                chaserAlive = false;
        }

        if (ghostType == "ignorant") {
                ignorantAlive = false;
        }

        if (ghostType == "whim") {
                whimAlive = false;
        }
}

/**
 *  This method finds the target location for ambusher when in chase mode, and 
 *  then calls the ghost move function to move the ghost accordingly.
 */

public void chaseAmbusher () {
        
        // Finding the current waka position.
        long targetx = waka.getX (); 
        long targety = waka.getY ();

        // Finding the direction in which waka is moving.
        String direction = waka.getMovingDirection ();
        
        // Finding the grid position which is four grid spaces ahead of waka based 
        // on the direction of waka.
        if (direction == "right") {
                targetx += 128;

                if (targetx > 432) {
                        targetx = 432;
                }

        } else if (direction == "left") {
                targetx -= 128;

                if (targetx < 0) {
                        targetx = 0;
                }

        } else if (direction == "up") {
                targety -= 128;

                if (targety < 0) {
                        targety = 0;
                }

        } else if (direction == "down") {
                targety += 128;

                if (targety > 428) {
                        targety = 428;
                }

        } else {
        }
        
        // Updating the target position of ambusher based on the value calculated above.
        targetxAmbusher = targetx;
        targetyAmbusher = targety;

        // Calling the ghost move function to move the ambusher according to its target position.
        ghostMove (targetx, targety, targetsAmbusher, ambusher, movesListAmbusher);

}

/**
 *  This method finds the target location for ambusher when in scatter mode, 
 *  and then calls the ghost move function to move the ghost accordingly.
 */

public void scatterAmbusher () {
        
        // The target position for ambusher for scatter mode is top right corner.
        long targetx = 410;
        long targety = 58;

        targetxAmbusher = targetx;
        targetyAmbusher = targety;

        // Calling the ghost move function to move the ambusher according to its target position.
        ghostMove (targetx, targety, targetsAmbusher, ambusher, movesListAmbusher);

}

/**
 *  This method finds the target location for chaser when in chase mode, and 
 *  then calls the ghost move function to move the ghost accordingly.
 */

public void chaseChaser () {
        
        // Finding the current waka position.
        long targetx = waka.getX ();
        long targety = waka.getY ();
        
        // Target of chaser for chase mode is the current waka position.
        targetxChaser = targetx;
        targetyChaser = targety;
        
        // Calling the ghost move function to move the chaser according to 
        // its target position.
        ghostMove (targetx, targety, targetsChaser, chaser, movesListChaser);
}

/**
 *  This method finds the target location for chaser when in scatter mode, and 
 *  then calls the ghost move function to move the ghost accordingly.
 */

public void scatterChaser () {
        
        // The target position for chaser for scatter mode is top left corner.
        long targetx = 10;
        long targety = 59;

        targetxChaser = targetx;
        targetyChaser = targety;
        
        // Calling the ghost move function to move the chaser according to its 
        // target position.
        ghostMove (targetx, targety, targetsChaser, chaser, movesListChaser);
}

/**
 *  This method finds the target location for ignorant when in chase mode, and 
 *  then calls the ghost move function to move the ghost accordingly.
 * 
 *  @param targets Array list of positions of ignorant ghost.
 */

public void chaseIgnorant (ArrayList<Long> targets) {
        
        // Finding the current position of waka.
        long wakax = waka.getX();
        long wakay = waka.getY();
        long currentx = 0;
        long currenty = 0;
        
        // Finding the current position of ignorant.
        for (int i = 0; i < targets.size () - 1; i += 2) {
                currentx = targets.get (i);
                currenty = targets.get (i + 1);
        }
        
        // Calculating the distance between waka, and ghost.
        long diffx = currentx - wakax;
        long diffy = currenty - wakay;
        long distance = (long) Math.sqrt (diffx * diffx + diffy * diffy);
        long targetx;
        long targety;
        
        // If distance is less than 8 units, then target location is bottom left corner. 
        if (distance <= 8) {
                targetx = 10;
                targety = 59;
        }
        else { 
                // In this case the target location is the current position of waka.
                targetx = wakax;
                targety = wakay;
        }

        targetxIgnorant = targetx;
        targetyIgnorant = targety;
        
        // Calling the ghost move function to move the ignorant according to its 
        // target position.
        ghostMove (targetx, targety, targetsIgnorant, ignorant, movesListIgnorant);
}

/**
 *  This method finds the target location for ignorant when in scatter mode, and 
 *  then calls the ghost move function to move the ghost accordingly.
 */

public void scatterIgnorant () {
        
        // The target position for chaser for scatter mode is bottom left corner.
        long targetx = 10;
        long targety = 506;

        targetxIgnorant = targetx;
        targetyIgnorant = targety;
        
        // Calling the ghost move function to move the ignorant according to its 
        // target position.
        ghostMove (targetx, targety, targetsIgnorant, ignorant, movesListIgnorant);
}

/**
 *  This method finds the target location for whim when in chase mode, and then 
 *  calls the ghost move function to move the ghost accordingly.
 */

public void chaseWhim () {

        if (targetsChaser.size () != 0) {
                
                // Finding the current waka position.
                long wakax = waka.getX ();
                long wakay = waka.getY ();

                long targetx = 0;
                long targety = 0;

                // Finding the current chaser position.
                long chaserx = targetsChaser.get (0);
                long chasery = targetsChaser.get (1);
                String direction = waka.getMovingDirection ();
                
                // Finding the position two grid spaces ahead of waka based on waka's 
                // current direction.
                if (direction == "right") {
                        wakax += 2;

                        if (wakax > 432)
                        {
                                wakax = 432;
                        }

                } else if (direction == "left") {
                        wakax -= 2;

                        if (wakax < 0) {
                                wakax = 0;
                        }

                } else if (direction == "up") {
                        wakay -= 2;

                        if (wakay < 0) {
                                wakay = 0;
                        }

                } else if (direction == "down") {
                        wakay += 2;

                        if (wakay > 428) {
                                wakay = 428;
                        }

                } else {
                }
                
                // Finding the target based on the position of chaser, and position 
                // of grid two spaces ahead of waka in its direction of moving.

                if ( wakax < chaserx && wakay == chasery ) {
             
                    targetx = chaserx - 2 * (chaserx - wakax);

                    if(targetx < 0){
                        targetx = 0;
                    }

                    targety = wakay;

                } else if ( wakax < chaserx && wakay < chasery ) {
                
                    targetx = chaserx - 2 * (chaserx - wakax);

                    if(targetx < 0){
                        targetx = 0;
                    }

                    targety = chasery - 2 * (chasery - wakay);

                    if (targety < 0){
                        targety = 0;
                    }

                } else if ( wakax < chaserx && wakay > chasery ) {
            
                    targetx = chaserx - 2 * (chaserx - wakax);

                    if(targetx < 0){
                        targetx = 0;
                    }

                    targety = chasery + 2 * (wakay - chasery);

                    if (targety > 576){
                        targety = 576;
                    }

                }   else if ( wakax == chaserx && wakay < chasery ) {
                
                    targetx = wakax;

                    targety = chasery - 2 * (wakay - chasery);

                    if (targety < 0){
                        targety = 0;
                    }

                }    else if ( wakax == chaserx && wakay > chasery ) {
                    
                    targetx = wakax;

                    targety = chasery + 2 * (wakay - chasery);

                    if (targety > 576){
                        targety = 576;
                    }

                }    else if ( wakax == chaserx && wakay == chasery ) {
                
                    targetx = wakax;
                    targety = wakay;

                }   else if ( wakax > chaserx && wakay == chasery ) {
              
                    targetx = chaserx + 2 * (chaserx - wakax);

                    if(targetx > 448){
                        targetx = 448;
                    }

                    targety = wakay;

                } else if ( wakax > chaserx && wakay < chasery ) {
                  
                    targetx = chaserx + 2 * (chaserx - wakax);

                    if(targetx > 448){
                        targetx = 448;
                    }

                    targety = chasery - 2 * (chasery - wakay);

                    if (targety < 0){
                        targety = 0;
                    }

                } else if ( wakax > chaserx && wakay > chasery ) {
                
                    targetx = chaserx + 2 * (chaserx - wakax);

                    if(targetx > 448){
                        targetx = 448;
                    }

                    targety = chasery + 2 * (wakay - chasery);

                    if (targety > 576){
                        targety = 576;
                    } 

                }            
           
                targetxWhim = targetx;
                targetyWhim = targety;
                
                // Calling the ghost move function to move the whim according to its 
                // target position.
                ghostMove (targetx, targety, targetsWhim, whim, movesListWhim);
        }
}

/**
 *  This method finds the target location for whim when in scatter mode, and 
 *  then calls the ghost move function to move the ghost accordingly.
 */

public void scatterWhim () {

        long targetx = 410;
        long targety = 506;

        targetxWhim = targetx;
        targetyWhim = targety;
        
        // Calling the ghost move function to move the whim according to its 
        // target position.
        ghostMove (targetx, targety, targetsWhim, whim, movesListWhim);
}



/**
 *  This method is responsible for moving the ghost based on its target location. 
 * 
 *  @param targetx     x-axis target position.
 *  @param targety     y-axis target position.
 *  @param targets     List of ghost positions.
 *  @param spirit      PImage of ghost which has to move.
 *  @param movesList   List of moves made by ghost previously.
 */

public void ghostMove (long targetx, long targety, ArrayList<Long> targets, 
                                PImage spirit, ArrayList<Integer> movesList) {


        for (int i = 0; i < targets.size () - 1; i += 2) {
                
                // Stores the x, and y position of ghost.
                long ghostx = targets.get (i);
                long ghosty = targets.get (i + 1);
                
                // Direction array stores all the directions which are possible.
                String[] directions = {"up", "down", "left", "right"};

                // Array to store whether it is possible for the ghost to move in 
                // the direction corresponding to direction array or not.
                String[] possible = {" ", " ", " ", " "};

                Boolean result;

                for (int j = 0; j < 4; j++) {
                        
                        if (j == 0) {
                                
                                // If it is possible for the ghost to move upwards, 
                                // then store yes in the possible array, else store no.
                                result = isCollidingUp (ghostx, ghosty - this.speed, 
                                                            targets, spirit);

                                if (result == true) {
                                        possible [j] = "no";
                                } else {
                                        possible [j] = "yes";
                                }

                        }
                        if (j == 1) {
                                
                                // If it is possible for the ghost to move downwards, 
                                // then store yes in the possible array, else store no.
                                result = isCollidingDown (ghostx, ghosty + this.speed, 
                                                            targets, spirit);

                                if (result == true) {
                                        possible [j] = "no";
                                } else {
                                        possible [j] = "yes";
                                }

                        }
                        if (j == 2) {

                                // If it is possible for the ghost to move left, 
                                // then store yes in the possible array, else store no.
                                result = isCollidingLeft (ghostx - this.speed, ghosty, 
                                                            targets, spirit);

                                if (result == true) {
                                        possible [j] = "no";
                                } else {
                                        possible [j] = "yes";
                                }

                        }
                        if (j == 3) {

                                // If it is possible for the ghost to move right, 
                                // then store yes in the possible array, else store no.
                                result = isCollidingRight (ghostx + this.speed, ghosty, 
                                                                targets, spirit);

                                if (result == true) {
                                        possible [j] = "no";
                                } else { 
                                        possible [j] = "yes";
                                }

                        }
                }
                
                // It stores the number of possible directions in which the ghost can move.
                int possibilities = 0;

                int movePos = 9;
                for (int j = 0; j < 4; j++) {
                        if (possible [j] == "yes") {
                                possibilities += 1;
                        }
                }
                
                // Variable option1 stores the first priority direction of the ghost.
                String option1 = "";

                // Variable option2 stores the first priority direction of the ghost.
                String option2 = "";

                // Based on the ghost position, and its target location, we decide the 
                // first, and second priority of the ghost.
                
                if (ghostx > targetx && ghosty > targety) {
                        long diffx = ghostx -  targetx;
                        long diffy = ghosty -  targety;

                        if (diffx > diffy){
                                option1 = "left";
                                option2 = "up";
                        } else{
                                option2 = "left";
                                option1 = "up";
                        }
                }

                if (ghostx > targetx && ghosty < targety) {
                        long diffx = ghostx -  targetx;
                        long diffy = targety -  ghosty;

                        if (diffx > diffy) {
                                option1 = "left";
                                option2 = "down";
                        } else{
                                option2 = "left";
                                option1 = "down";
                        }
                }

                if (ghostx < targetx && ghosty < targety) {
                        long diffx = targetx - ghostx;
                        long diffy = targety -  ghosty;

                        if (diffx > diffy) {
                                option1 = "right";
                                option2 = "down";
                        } else{
                                option2 = "right";
                                option1 = "down";
                        }
                }

                if (ghostx < targetx && ghosty > targety) {
                        long diffx = targetx - ghostx;
                        long diffy = ghosty -  targety;

                        if (diffx > diffy) {
                                option1 = "right";
                                option2 = "up";
                        } else{
                                option2 = "right";
                                option1 = "up";
                        }
                }

                if (ghostx > targetx && ghosty == targety) {
                        option1 = "right";
                }

                if (ghostx == targetx && ghosty < targety) {
                        option1 = "up";
                }

                if (ghostx < targetx && ghosty == targety) {
                        option1 = "left";
                }

                if (ghostx == targetx && ghosty > targety) {
                        option1 = "down";
                }

                if (ghostx == targetx && ghostx == targety) {
                        option1 = "reached";
                        option2 = "reached";
                }

                // Stores if we could find the first priority option or not.
                Boolean first = false;

                // Stores if we could find the second priority option or not.
                Boolean second = false;
                
                // If ghost hasnt moved even once then we calculate the next possible move.
                if (movesList.size () == 0) {
                        
                        // Checking if the first option move is possible or not.
                        for (int k = 0; k < 4; k++) {
                                if (possible [k].equals ("yes")) {
                                        if (directions [k].equals (option1) ) {

                                                // If first priority move is possible 
                                                // then we change the move pos to the 
                                                // index corresponding to that direction 
                                                // in direction array.
                                                movePos = k;
                                                first =  true;
                                                break;
                                        }
                                }
                        }

                        // Checking if the second option move is possible or not.
                        for (int k = 0; k < 4; k++) {
                                if (possible [k].equals ("yes") && first == false) {
                                        if (directions [k].equals (option2) ) {

                                                // If second priority move is possible then 
                                                // we change the move pos to the index 
                                                // corresponding to that direction in 
                                                // direction array.
                                                movePos = k;
                                                second = true;
                                                break;
                                        }
                                }
                        }

                        // Finding any possible move for the ghost, if both first, 
                        // and second priorities are not possible.
                        if (first == false && second == false) {

                                for (int k = 0; k < 4; k++) {
                                        if (possible [k].equals ("yes") ) {
                                                movePos = k;
                                                break;

                                        }
                                }
                        }
                        
                        // Adding the move pos to the list of moves.
                        movesList.add (movePos);
                }

                // If the ghost has already moved a several times, we only change direction
                // at an intersection.
                else if (movesList.size () != 0) { 

                        // Stores the last move made by waka.
                        int lastMove = movesList.get (movesList.size () - 1);

                        // If the number of possibilities for the ghost is one, then 
                        // ghost moves in that direction.
                        if (possibilities == 1) {
                                for (int k = 0; k < 4; k++) {
                                        if (possible [k].equals ("yes") ) {
                                                movePos = k;
                                                break;
                                        }
                                }
                        }

                        if (possibilities == 2) {

                                if (possible [lastMove].equals ("yes") ) {

                                        // If the number of possibilities is two, and 
                                        // the last move made by ghost is possible, 
                                        // then the ghost would keep on moving in that 
                                        // direction.
                                        movePos = lastMove;
                                } else {

                                        // If the last made move is not possible, then 
                                        // the ghost changes its direction to the other 
                                        // possible move.
                                        for (int k = 0; k < 4; k++) {

                                                if (possible [k].equals ("yes") 
                                                        && k != movePos) {
                                                            movePos = k;
                                                            break;
                                                }
                                        }
                                }
                        }

                        // If the number of possibilities is greater than 2, that is 
                        // ghost is at an intersection, we change ghosts direction.
                        if (possibilities > 2 && frightened == false) {
                                Boolean repeat = false;

                                // Checking if the first priority option is possible.
                                for (int k = 0; k < 4; k++) {

                                        if (possible [k].equals ("yes") ) {

                                                if (directions [k].equals (option1) ) {
                                                        repeat = false;

                                                        // Checking if the ghost does not 
                                                        // turns back by making that possible move. 
                                                        // It is allowed to do that only when it has 
                                                        // no other choice.
                                                        if ( (lastMove == 0 && k == 1) 
                                                                || (lastMove == 1 && k == 0) 
                                                                || (lastMove == 2 && k == 3) 
                                                            || (lastMove == 3 && k == 2) ) {
                                                                    
                                                                    repeat = true;
                                                        }

                                                        if (repeat == false) {
                                                                movePos = k;
                                                                first =  true;
                                                                break;
                                                        }
                                                }
                                        }
                                }

                                // Checking if the second priority option is possible.
                                for (int k = 0; k < 4; k++) {

                                        if (possible [k].equals ("yes") && first == false) {

                                                if (directions [k].equals (option2) ) {
                                                        repeat = false;


                                                        // Checking if the ghost does not 
                                                        // turns back by making that possible move. 
                                                        // It is allowed to do that only when it has 
                                                        // no other choice.
                                                        if ( (lastMove == 0 && k == 1) 
                                                                || (lastMove == 1 && k == 0) 
                                                                || (lastMove == 2 && k == 3) 
                                                            || (lastMove == 3 && k == 2) ) {

                                                                        repeat = true;
                                                        }

                                                        if (repeat == false) {
                                                                movePos = k;
                                                                second =  true;
                                                                break;
                                                        }
                                                }
                                        }
                                }

                                // Finding any possible move for the ghost, if both first, 
                                // and second priorities are not possible.
                                if (first == false && second == false) {

                                        for (int k = 0; k < 4; k++) {

                                                if (possible [k].equals ("yes") ) {
                                                        repeat =  false;


                                                        // Checking if the ghost does not 
                                                        // turns back by making that possible move. 
                                                        // It is allowed to do that only when it has 
                                                        // no other choice.
                                                        if ( (lastMove == 0 && k == 1) 
                                                                || (lastMove == 1 && k==0) 
                                                                || (lastMove == 2 && k == 3) 
                                                            || (lastMove == 3 && k == 2) ) {
                                                                
                                                                        repeat = true;
                                                        }

                                                        if (repeat == false) {
                                                                movePos = k;
                                                                break;
                                                        }
                                                }
                                        }
                                }
                        }

                        // If the number of possibilities is greater than 2, and the 
                        // ghost is frightened that is ghost is at an intersection, 
                        // the ghost decides next move randomly.
                        if (possibilities > 2 && frightened == true) {

                                ArrayList<Integer> moves = new ArrayList<Integer> ();
                                Boolean repeat = false;

                                for (int k = 0; k < 4; k++) {

                                        if (possible [k].equals ("yes") ) {
                                             

                                                // Checking if the ghost does not 
                                                // turns back by making that possible 
                                                // move. It is allowed to do that only 
                                                // when it has no other choice.
                                                if ( (lastMove == 0 && k == 1) 
                                                        || (lastMove == 1 && k == 0) 
                                                        || (lastMove == 2 && k == 3) 
                                                    || (lastMove == 3 && k == 2) ) {
                                                                
                                                            repeat = true;
                                                }

                                                // Finding the list of possible moves 
                                                // for which ghost would not turn back 
                                                // unless that being the last option.
                                                if (repeat == false) {
                                                        moves.add (k);
                                                }
                                        }

                                }

                                int length = moves.size ();
                                Random random = new Random ();

                                // Choosing a random integer to randomly decide the 
                                // next move of ghost out of all the possible moves.
                                if (length != 0) {
                                        int pos = random.ints (0, length).findFirst ().getAsInt ();
                                        movePos = moves.get (pos);
                                }

                                if (length == 0) {

                                        // If no move is possible, and only option 
                                        // available is turning back, then the ghost 
                                        // would turn back.
                                        for (int k = 0; k < 4; k++) {
                                                if (possible [k].equals ("yes") ) {
                                                        movePos = k;
                                                }
                                        }

                                }
                        }

                        // Adding the move to the moves list.
                        if (movePos != lastMove && movePos != 9) {
                                movesList.add (movePos);
                        }
                }

                // If the ghost has reached, then it does not has to move anywhere else.
                // Value 9 indicates no move.
                if (option1.equals ("reached") ) {
                        movePos = 9;
                }

                // Moving the ghost based on its move position.

                if (movePos == 0) {
                        if (possible [movePos].equals ("yes") ) {
                                ghosty -= this.speed;
                        }
                }

                if (movePos == 1) {
                        if (possible [movePos].equals ("yes") ) {
                                ghosty += this.speed;
                        }
                }

                if (movePos == 2) {
                        if (possible [movePos].equals ("yes") ) {
                                ghostx -= this.speed;
                        }
                }

                if (movePos == 3) {
                        if (possible [movePos].equals ("yes") ) {
                                ghostx += this.speed;
                        }
                }

                // Changing the position of ghost based on target location.
                targets.set (i, ghostx);
                targets.set (i+1, ghosty);
        }
}



/**
 *  Checking for different top, bottom, left, and right values of ghost, and 
 *  wall that they are colliding or not when ghost is trying to move upwards.
 *  It returns true if the ghost collides with wall, when it tries to move upwards, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param ghostTop      Coordinates of the top of ghost.
 *  @param ghostBottom   Coordinates of the bottom of ghost.
 *  @param ghostLeft     Coordinates of the left side of ghost.
 *  @param ghostRight    Coordinates of the right side of ghost.
 * 
 *  @return              Outcome of ghost trying to move upwards.
 */

public boolean collisonUp (long wallTop, long wallBottom, long wallLeft, long wallRight, 
                                long ghostTop, long ghostBottom, long ghostLeft, long ghostRight) {
        
        if (ghostTop < wallBottom && ghostTop > wallTop && ghostRight < wallRight 
                    && ghostRight > wallLeft) {
                            
                        return true;
        }

        if (ghostTop < wallBottom && ghostTop > wallTop && ghostLeft < wallRight 
                    && ghostLeft > wallLeft) {
                
                        return true;
        }

        if (ghostTop < wallBottom && ghostTop > wallTop && wallRight < ghostRight 
                    && wallRight > ghostLeft) {
                
                        return true;
        }

        if (wallTop > ghostTop && wallTop < ghostBottom && ghostRight < wallRight 
                    && ghostRight > wallLeft) {
                
                        return true;
        }

        if (wallTop > ghostTop && wallTop < ghostBottom && ghostLeft < wallRight 
                    && ghostLeft > wallLeft) {
                
                        return true;
        }

        if (wallTop > ghostTop && wallTop < ghostBottom && wallRight < ghostRight 
                    && wallRight > ghostLeft) {
                
                        return true;
        }

        return false;
}




/**
 *  Checking for different top, bottom, left, and right values of ghost, and 
 *  wall that they are colliding or not when ghost is trying to move downwards.
 *  It returns true if the ghost collides with wall, when it tries to move downwards, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param ghostTop      Coordinates of the top of ghost.
 *  @param ghostBottom   Coordinates of the bottom of ghost.
 *  @param ghostLeft     Coordinates of the left side of ghost.
 *  @param ghostRight    Coordinates of the right side of ghost.
 * 
 *  @return              Outcome of ghost trying to move downwards.
 */

public boolean collisonDown (long wallTop, long wallBottom, long wallLeft, 
                                long wallRight, long ghostTop, long ghostBottom, 
                            long ghostLeft, long ghostRight) {
        
        if (wallTop > ghostTop && wallTop < ghostBottom && ghostRight < wallRight  
                    && ghostRight > wallLeft) {
                
                        return true;
        }

        if (wallTop > ghostTop && wallTop < ghostBottom && ghostLeft < wallRight 
                    && ghostLeft > wallLeft) {
               
                        return true;
        }

        if (wallTop > ghostTop && wallTop < ghostBottom && wallRight < ghostRight 
                    && wallRight > ghostLeft) {
                
                        return true;
        }

        if (ghostBottom < wallBottom && ghostBottom > wallTop && ghostRight < wallRight 
                    && ghostRight > wallLeft) {
                
                        return true;
        }

        if (ghostBottom < wallBottom && ghostBottom > wallTop && ghostLeft < wallRight 
                    && ghostLeft > wallLeft) {
                
                        return true;
        }

        return false;
}



/**
 *  Checking for different top, bottom, left, and right values of ghost, and 
 *  wall that they are colliding or not when ghost is trying to move right.
 *  It returns true if the ghost collides with wall, when it tries to move right, 
 *  else it returns false.
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param ghostTop      Coordinates of the top of ghost.
 *  @param ghostBottom   Coordinates of the bottom of ghost.
 *  @param ghostLeft     Coordinates of the left side of ghost.
 *  @param ghostRight    Coordinates of the right side of ghost.
 * 
 *  @return              Outcome of ghost trying to move right.
 */

public boolean collisonRight (long wallTop, long wallBottom, long wallLeft, 
                                    long wallRight, long ghostTop, long ghostBottom, 
                              long ghostLeft, long ghostRight) {

        if (ghostTop > wallTop && ghostTop < wallBottom && ghostRight < wallRight 
                    && ghostRight > wallLeft) {
                
                        return true;
        }

        if (ghostBottom > wallTop && ghostBottom < wallBottom && ghostRight < wallRight 
                    && ghostRight > wallLeft) {
                
                        return true;
        }

        if (ghostTop < wallTop && ghostBottom > wallTop && ghostRight > wallRight 
                    && wallRight > ghostLeft) {
                
                        return true;
        }

        if (ghostBottom > wallTop && ghostBottom < wallBottom && wallRight < ghostRight
                     && wallRight > ghostLeft) {
                
                        return true;
        }

        if (ghostTop > wallTop && ghostTop < wallBottom && wallLeft < ghostRight 
                    && wallLeft > ghostLeft) {
                
                        return true;
        }

        return false;
}



/**
 *  Checking for different top, bottom, left, and right values of ghost, and wall 
 *  that they are colliding or not when ghost is trying to move left.
 *  It returns true if the ghost collides with wall, when it tries to move left, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param ghostTop      Coordinates of the top of ghost.
 *  @param ghostBottom   Coordinates of the bottom of ghost.
 *  @param ghostLeft     Coordinates of the left side of ghost.
 *  @param ghostRight    Coordinates of the right side of ghost.
 * 
 *  @return              Outcome of ghost trying to move left.
 */

public boolean collisonLeft1 (long wallTop, long wallBottom, long wallLeft, 
                                    long wallRight, long ghostTop, long ghostBottom, 
                              long ghostLeft, long ghostRight) {

        if (ghostTop < wallTop && ghostBottom > wallTop && ghostRight > wallRight 
                && wallRight > ghostLeft) {
                
                    return true;
        }

        if (ghostBottom > wallTop && ghostBottom < wallBottom && wallRight < ghostRight
                 && wallRight > ghostLeft) {
                
                    return true;
        }

        if (ghostTop > wallTop && ghostTop < wallBottom && wallLeft < ghostRight 
                && wallLeft > ghostLeft) {
                
                    return true;
        }

        return false;
}




/**
 *  Checking for different top, bottom, left, and right values of ghost, and wall 
 *  that they are colliding or not when ghost is trying to move left.
 *  It returns true if the ghost collides with wall, when it tries to move left, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param ghostTop      Coordinates of the top of ghost.
 *  @param ghostBottom   Coordinates of the bottom of ghost.
 *  @param ghostLeft     Coordinates of the left side of ghost.
 *  @param ghostRight    Coordinates of the right side of ghost.
 * 
 *  @return              Outcome of ghost trying to move left.
 */

public boolean collisonLeft2 (long wallTop, long wallBottom, long wallLeft, 
                                        long wallRight, long ghostTop, long ghostBottom, 
                             long ghostLeft, long ghostRight) {

        if(ghostTop > wallTop && ghostTop < wallBottom && ghostLeft < wallRight 
                && ghostLeft > wallLeft) {
                
                    return true;
        }

        if (ghostBottom > wallTop && ghostBottom < wallBottom && ghostLeft < wallRight 
                && ghostLeft > wallLeft) {
                
                    return true;
        }

        return false;
}



/**
 * This method calculates the top, bottom, left, and right position of wall, and 
 * ghost, and checks if it is colliding when ghost tries to move upwards or not.
 * It returns true if the ghost is able to move upwards without colliding, else 
 * it returns false.
 * 
 * @param startx    x-axis position of ghost.
 * @param starty    y-axis position of ghost.
 * @param targets   List of positions of ghost.
 * @param spirit    PImange of the ghost which we are trying to move upwards.
 * 
 * @return    Outcome of ghost trying to move upwards.
 */


public boolean isCollidingUp (long startx, long starty, ArrayList<Long> targets, 
                                    PImage spirit) {

        // Checking if ghost is colliding with a horizontal grid space when trying to 
        // move upwards or not.
        for (int i = 0; i < hor.size () - 1; i += 2) {

                long wallTop = hor.get (i + 1);
                long wallBottom = hor.get (i + 1) + 8;
                long wallLeft = hor.get (i);
                long wallRight = hor.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonUp (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                    ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                        return true;
                }

        }

        // Checking if ghost is colliding with an up right grid space when trying to 
        // move upwards or not.
        for(int i = 0; i < upR.size () - 1; i += 2) {

                long wallTop = upR.get (i + 1);
                long wallBottom = upR.get (i + 1) + 8;
                long wallLeft = upR.get (i) + 8;
                long wallRight = upR.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonUp (wallTop, wallBottom, wallLeft, wallRight, ghostTop,
                                     ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                        return true;
                }
        }

        // Checking if ghost is colliding with an up left grid space when trying to 
        // move upwards or not.
        for (int i = 0; i < upL.size () - 1; i += 2) {

                long wallTop = upL.get (i + 1);
                long wallBottom = upL.get (i + 1) + 8;
                long wallLeft = upL.get (i);
                long wallRight = upL.get (i) + 8;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonUp (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                    ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                        return true;
                }
        }

        // Checking if ghost is colliding with a down right grid space when trying 
        // to move upwards or not.
        for (int i = 0; i < downR.size () - 1; i += 2) {

                long wallTop = downR.get (i + 1);
                long wallBottom = downR.get (i + 1) + 8;
                long wallLeft = downR.get (i) + 8;
                long wallRight = downR.get (i) + 16;

                long ghostTop = starty;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonUp (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        return false;

}




/**
 * This method calculates the top, bottom, left, and right position of wall, 
 * and ghost, and checks if it is colliding when ghost tries to move downwards or not.
 * It returns true if the ghost is able to move downwards without colliding, 
 * else it returns false.
 * 
 * 
 * @param startx    x-axis position of ghost.
 * @param starty    y-axis position of ghost.
 * @param targets   List of positions of ghost.
 * @param spirit    PImange of the ghost which we are trying to move downwards.
 * 
 * @return    Outcome of ghost trying to move downwards.
 */


public boolean isCollidingDown (long startx, long starty, ArrayList<Long> targets, 
                                    PImage spirit) {

         // Checking if ghost is colliding with a horizontal grid space when trying to 
         // move downwards or not.
        for (int i = 0; i < hor.size () - 1; i += 2) {

                long wallTop = hor.get (i + 1);
                long wallBottom = hor.get (i + 1) + 8;
                long wallLeft = hor.get (i);
                long wallRight = hor.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonDown (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }


        }
        
        // Checking if ghost is colliding with a down right grid space when trying to 
        // move downwards or not.
        for (int i = 0; i < downR.size () - 1; i += 2) {

                long wallTop = downR.get (i + 1) + 8;
                long wallBottom = downR.get (i + 1) + 16;
                long wallLeft = downR.get (i) + 8;
                long wallRight = downR.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonDown (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                    ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                        return true;
                }
        }

        // Checking if ghost is colliding with a down left grid space when trying to 
        // move downwards or not.
        for (int i = 0; i < downL.size () - 1; i += 2) {

                long wallTop = downL.get (i + 1) + 8;
                long wallBottom = downL.get (i + 1) + 16;
                long wallLeft = downL.get (i);
                long wallRight = downL.get (i) + 8;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonDown (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                    ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                        return true;
                }
        }

         // Checking if ghost is colliding with a up right grid space when trying to 
         // move downwards or not.
        for (int i = 0; i < upR.size () - 1; i += 2) {

                long wallTop = upR.get (i + 1) + 8;
                long wallBottom = upR.get (i + 1) + 16;
                long wallLeft = upR.get (i) + 8;
                long wallRight = upR.get (i) + 16;

                long ghostTop = starty;
                long ghostBottom = starty + spirit.height;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonDown (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                    ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                        return true;
                }
        }

        return false;
}




/**
 * This method calculates the top, bottom, left, and right position of wall, and 
 * ghost, and checks if it is colliding when ghost tries to move right or not.
 * It returns true if the ghost is able to move right without colliding, else it 
 * returns false.
 * 
 * 
 * @param startx    x-axis position of ghost.
 * @param starty    y-axis position of ghost.
 * @param targets   List of positions of ghost.
 * @param spirit    PImange of the ghost which we are trying to move right.
 * 
 * @return    Outcome of ghost trying to move right.
 */


public boolean isCollidingRight (long startx, long starty, ArrayList<Long> targets, 
                                        PImage spirit) {

         // Checking if ghost is colliding with a vertical grid space when trying to 
         // move right or not.
        for (int i = 0; i < ver.size () - 1; i += 2) {

                long wallTop = ver.get (i + 1);
                long wallBottom = ver.get (i + 1) + 16; 
                long wallLeft = ver.get (i) + 8;
                long wallRight = ver.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        // Checking if ghost is colliding with a down left grid space when trying to 
        // move right or not.
        for (int i = 0; i < downL.size () - 1; i += 2) {

                long wallTop = downL.get (i + 1) + 8;
                long wallBottom = downL.get (i + 1) + 16;
                long wallLeft = downL.get (i) + 8;
                long wallRight = downL.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        // Checking if ghost is colliding with a down right grid space when trying to 
        // move right or not.
        for (int i = 0; i < downR.size () - 1; i += 2) {

                long wallTop = downR.get (i + 1) + 8;
                long wallBottom = downR.get (i + 1) + 16;
                long wallLeft = downR.get (i) + 8;
                long wallRight = downR.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        // Checking if ghost is colliding with an up left grid space when trying to 
        // move right or not.
        for (int i = 0; i < upL.size () - 1; i += 2) {

                long wallTop = upL.get (i + 1);
                long wallBottom = upL.get (i + 1) + 8;
                long wallLeft = upL.get (i) + 8;
                long wallRight = upL.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        // Checking if ghost is colliding with an up right grid space when trying to 
        //move right or not.
        for (int i = 0; i < upR.size () - 1; i += 2) {

                long wallTop = upR.get (i + 1);
                long wallBottom = upR.get (i + 1) + 8;
                long wallLeft = upR.get (i) + 8;
                long wallRight = upR.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        return false;
}




/**
 * This method calculates the top, bottom, left, and right position of wall, and 
 * ghost, and checks if it is colliding when ghost tries to move left or not.
 * It returns true if the ghost is able to move left without colliding, else it 
 * returns false.
 * 
 * 
 * @param startx    x-axis position of ghost.
 * @param starty    y-axis position of ghost.
 * @param targets   List of positions of ghost.
 * @param spirit    PImange of the ghost which we are trying to move left.
 * 
 * @return    Outcome of ghost trying to move left.
 */



public boolean isCollidingLeft (long startx, long starty, ArrayList<Long> targets, 
                                        PImage spirit) {

         // Checking if ghost is colliding with a vertical grid space when trying 
         // to move left or not.
        for (int i = 0; i < ver.size () - 1; i += 2) {

                long wallTop = ver.get (i + 1);
                long wallBottom = ver.get (i + 1) + 16;
                long wallLeft = ver.get (i) + 8;
                long wallRight = ver.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }

                wallLeft = ver.get (i);
                wallRight = ver.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }

        }

        // Checking if ghost is colliding with an up left grid space when trying 
        // to move left or not.
        for (int i = 0; i < upL.size () - 1; i += 2) {
                long wallTop = upL.get (i + 1);
                long wallBottom = upL.get (i + 1) + 8;
                long wallLeft = upL.get (i) + 8;
                long wallRight = upL.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }

                wallTop = upL.get (i + 1);
                wallBottom = upL.get (i + 1) + 8;
                wallLeft = upL.get (i);
                wallRight = upL.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        // Checking if ghost is colliding with an up right grid space when trying to 
        // move left or not.
        for (int i = 0; i < upR.size () - 1; i += 2) {
                long wallTop = upR.get (i + 1);
                long wallBottom = upR.get (i + 1) + 8;
                long wallLeft = upR.get (i) + 8;
                long wallRight = upR.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }

                wallTop = upR.get (i + 1);
                wallBottom = upR.get (i + 1) + 8;
                wallLeft = upR.get (i);
                wallRight = upR.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        // Checking if ghost is colliding with a down left grid space when trying to 
        // move left or not.
        for (int i = 0; i < downL.size () - 1; i += 2) {
                long wallTop = downL.get (i + 1) + 8;
                long wallBottom = downL.get (i + 1) + 16;
                long wallLeft = downL.get (i) + 8;
                long wallRight = downL.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }

                wallTop = downL.get (i + 1) + 8;
                wallBottom = downL.get (i + 1) + 16;
                wallLeft = downL.get (i);
                wallRight = downL.get (i) + 8;

                if (collisonLeft2(wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }
        }

        // Checking if ghost is colliding with a down right grid space when trying to 
        // move left or not.
        for (int i = 0; i < downR.size () - 1; i += 2) {
                long wallTop = downR.get (i + 1) + 8;
                long wallBottom = downR.get (i + 1) + 16;
                long wallLeft = downR.get (i) + 8;
                long wallRight = downR.get (i) + 16;

                long ghostTop = starty + 3;
                long ghostBottom = starty + spirit.height - 3;
                long ghostLeft = startx + 2;
                long ghostRight = startx + spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                        ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                            return true;
                }

                wallTop = downR.get (i + 1) + 8;
                wallBottom = downR.get (i + 1) + 16;
                wallLeft = downR.get (i);
                wallRight = downR.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, ghostTop, 
                                            ghostBottom, ghostLeft, ghostRight) == true) {
                        
                                                return true;
                }
        }

        return false;
}

/**
*  It is called sixty (frame rate) times per second. 
*/

public void tick () {

}

/**
 *  It is the draw method which is called sixty (frame rate) times per second. 
 *  It draws the image of different ghosts at their respective position using the 
 *  required PImage.
 * 
 * @param app PApplet app object.
 */

// Draw method.
public void draw (PApplet app) {

        PImage spirit;

        // If ambusher is alive then we draw it to the screen.
        if (ambusherAlive == true) {
                for (int i = 0; i < targetsAmbusher.size () - 1; i += 2) {

                        // If ghost is in frightened mode, then we draw the frightened 
                        // image to screen instead of ambusher image.

                        if (frightened == true) {
                                spirit = this.fright;
                        } else {
                                spirit = this.ambusher;
                        }
                        app.image (spirit, (long) targetsAmbusher.get (i),
                                        (long) targetsAmbusher.get (i + 1) );

                }
        }

        // If ignorant is alive then we draw it to the screen.
        if (ignorantAlive == true) {
                for (int i = 0; i < targetsIgnorant.size () - 1; i += 2) {

                        // If ghost is in frightened mode, then we draw the frightened 
                        // image to screen instead of ignorant image.

                        if (frightened == true) {
                                spirit = this.fright;
                        } else {
                                spirit = this.ignorant;
                        }
                        app.image (spirit, (long) targetsIgnorant.get (i), 
                                        (long) targetsIgnorant.get (i + 1) );

                }
        }

        // If whim is alive then we draw it to the screen.
        if (whimAlive == true) {
                for (int i = 0; i < targetsWhim.size () - 1; i += 2) {

                        // If ghost is in frightened mode, then we draw the frightened 
                        // image to screen instead of whim image.

                        if (frightened == true) {
                                spirit = this.fright;
                        } else{
                                spirit = this.whim;
                        }
                        app.image (spirit, (long) targetsWhim.get (i), 
                                        (long) targetsWhim.get (i + 1) );

                }
        }

        // If chaser is alive then we draw it to the screen.
        if (chaserAlive == true) {
                for (int i = 0; i < targetsChaser.size () - 1; i += 2) {

                        // If ghost is in frightened mode, then we draw the frightened 
                        // image to screen instead of chaser image.

                        if (frightened == true) {
                                spirit = this.fright;
                        } else{
                                spirit = this.chaser;
                        }
                        app.image (spirit, (long) targetsChaser.get (i), 
                                        (long) targetsChaser.get (i + 1) );

                }
        }
}
}


