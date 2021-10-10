
package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 *  Represents Fruit object within the system.
 */

public class Fruit {
    
    // Variable to store whether the particular fruit has been eaten, or not.
    private boolean eaten;

    // Variable to store the PImage of fruit.
    private PImage fruit;

    // Variable to store the PImage of super fruit.
    private PImage superFruit;
    
    // Variable to store the name of the map text file.
    private String mapfile; 

    // Variable to store the x-axis position, followed by y-axis position of fruits 
    // on map.
    private ArrayList<Integer> fruits;

    // Variable to store the x-axis position, followed by y-axis position of super fruits
    // on map.
    private ArrayList<Integer> superFruits;
    
    // Variable to store the time at which the frightened mode of ghost starts, 
    // that is when a super fruit has been eaten.
    private long frightenedStartTime;

    // Variable of type Config.
    private Config config;
    
    
    /**
     *  Constructs a new Fruit object.
     * 
     *  @param fruit PImage of fruit.
     *  @param superFruit PImage of superFruit.
     *  @param config Object of type Config.
     */

    // Constructor
    public Fruit (PImage fruit, PImage superFruit, Config config) {

        this.fruit = fruit;
        this.superFruit = superFruit;
        this.eaten = false;
        this.config = config;
        frightenedStartTime = 0;
        fruits = config.getFruits ();
        superFruits =  config.getSuperFruits ();

    }
   

   /**
    *  Returns the boolean value telling whenther the fruit has been eaten or not.
    *
    *  @return Outcome of whether fruit has been eaten or not.
    */
   
   // Getter method.
   public boolean getEaten () {
       return eaten;
   }
   

   /**
    *  Returns the time at which the last frightened mode of ghost started. 
    *  It returns 0 if no frightened mode has been started yet.
    *
    *  @return Frightened mode start time.
    */

   // Getter method.
   public long getFrightenedStartTime () {
       return frightenedStartTime;
   }
   

   /**
    *  Returns the array list of the x-axis position, followed by y-axis position 
    *  of fruits on map.
    *
    *  @return Positions of fruits.
    */

   // Getter method.
   public ArrayList<Integer> getFruits () {
       return fruits;
   }
   

   /**
    *  Returns the array list of the x-axis position, followed by y-axis position 
    *  of super fruits on map.
    *
    *  @return Positions of super fruits.
    */

   // Getter method.
   public ArrayList<Integer> getSuperFruits () {
       return superFruits;
   }
    


   /**
    *  Sets the parameter as the list that stores the x-axis position, followed 
    *  by y-axis position of fruits on map.
    *
    *  @param fruit Positions of fruit.
    */

    // Setter method.
    public void setFruits (ArrayList<Integer> fruit) {
        this.fruits = fruit;
   }
   

   /**
   *  Sets the parameter as the list that stores the x-axis position, followed 
   *  by y-axis position of super fruits on map.
   * 
   *  @param superFruit Positions of super fruits.
   */
   
   // Setter method.
   public void setSuperFruits (ArrayList<Integer> superFruit) {
       this.superFruits = superFruit;
  }


   
  /**
   *  Sets the parameter as the time at which the frightened mode of ghost starts,
   *  that is the time when a super fruit was eaten.
   * 
   *  @param time Frightened mode start time.
   */ 

   // Setter method.
   public void setFrightenedStartTime (long time) {
       frightenedStartTime = time;
   }

    

   /**
     *  This function starts the frightened mode of ghost. It sets the start time 
     *  of frightened mode based on the time when it is called.
     */
    
    // It is called when the frightened mode starts.
    // It returns the time at which the frightened mode starts.
    public void frightenedModeStart () {
        frightenedStartTime = System.currentTimeMillis() / 1000;     
    }



   /**
     *  It returns true if the player has won, that is there are no fruits, and 
     *  super fruits left to be eaten by the player. 
     *  It returns false if some fruits, or super fruits are still left.
     * 
     *  @return The win status of the game.
     */

    // Method that tells whether the player has won or not.
    public boolean hasWon () {
        

        // If all the fruits, and super fruits have been eaten then the player has won.
        // This is the case when no fruits, and super fruits positions are left in 
        // their array list.
        if (this.fruits.size () == 0 && this.superFruits.size () == 0) {
            return true;
        }

        return false;
    }



   /**
     *  It removes the fruits, or super fruits with the x, and y position from the list. 
     *  If the type is "super" then it removes it from super fruits list, if present.
     *  If it is "simple" then it removes them from the normal fruits list, if present.
     * 
     *  @param x       x-axis position of fruit
     *  @param y       y-axis position of fruit
     *  @param type    Type of fruit - simple or super.
     */ 

    // Method to remove particular fruits from the map.
    // This method is called when the particular fruit, or super fruit has been eaten.
    public void removeFruits (int x, int y, String type) {
            

        // This is the case to handle when a fruit has been eaten.
        if (type.equals ("simple") ) {
            
            // Looping through all the fruit positions.
            for (int i = 0; i < fruits.size () - 1; i += 2) { 
                int a = 0, b = 0;

                // Variable 'a' stores the x position of the fruit.
                // Variable 'b' stores the y position of the fruit.
                a = fruits.get (i);
                b = fruits.get (i+1);
                
                // If both the x, and y position of a fruit matches, then we remove 
                // those positions from the list.
                if (a == x && b == y) {
                    fruits.remove (i);
                    fruits.remove (i);
                    return;
                }

             }

        }

        // This is the case to handle when a super fruit has been eaten.
        if (type.equals ("super") ) {
            
            // Looping through all the fruit positions.
            for (int i = 0; i < superFruits.size () - 1; i += 2) { 

                int a = 0, b = 0;

                // Variable 'a' stores the x position of the fruit.
                // Variable 'b' stores the y position of the fruit.
                a = superFruits.get (i);
                b = superFruits.get (i+1);
                
                // If both the x, and y position of a fruit matches, then we remove 
                // those positions from the list.
                if (a == x && b == y) {
                    superFruits.remove (i);
                    superFruits.remove (i);
                    frightenedModeStart ();
                    return;
                }

            }

       }
        
   } 



   /**
     *  It is called sixty (frame rate) times per second. It performs all the 
     *  necessary calculations for Fruit objects.
     */

    public void tick () {
        
    }



    /**
     *  It is the draw method which is called sixty (frame rate) times per second. 
     *  It draws the image of fruits, and super fruits on the screen.
     * 
     *  @param app PApplet app object.
     */

    public void draw (PApplet app) {
       
        // Drawing all the fruits on the map.
        for (int i = 0; i < fruits.size () - 1; i += 2) {
             app.image (this.fruit, (int) fruits.get (i), (int) fruits.get (i+1) );
        }
        
        // Drawing all the super fruits on the map.
        for (int i = 0; i < superFruits.size () - 1; i += 2) {
             app.image (this.superFruit, (int) superFruits.get (i), (int) superFruits.get (i+1) );
        } 
 
     } 

}