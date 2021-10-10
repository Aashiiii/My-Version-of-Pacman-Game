package ghost;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class MapTest {

    // It is testing the constructor of Map class.
    @Test 
    public void constructor () {
        Config config = new Config ("configTest.json");
        assertNotNull (new Map (null, null, null, null, null, null, config) );
    }

    // The only function of Map class is to create the map grid.
    // It has been tested in App class that it is drawing the grid correctly
    // or not. And all the map attributes have been tested in Config class.

}