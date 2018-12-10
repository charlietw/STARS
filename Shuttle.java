/**
 * Contains all of the relevant information about a shuttle
 *
 * @author C. Wren
 * @version 03/12/2018
 */
import java.util.*;
public class Shuttle 
{
    /**
     * The code of the shuttle
     */
    public String code;
    
    /**
     * The origin of the journey
     */
    private int origin;
    
    /**
     * The destination of the journey
     */
    private int destination;

    /**
     * Constructor for 'Shuttle' class
     * @param code - The code that this shuttle is referenced by
     * @param origin - An integer representing the number of the world
     * that this shuttle starts at
     * @param destination - An integer representing the number of the world
     * that this shuttle ends at
     */
    public Shuttle(String code, int origin, int destination)
    {
        this.code = code;
        this.origin = origin;
        this.destination = destination;
    }
    
    /**
     * Returns a string of the code of the shuttle
     * @return a string of the code of the shuttle
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Returns an int representing the origin world
     * @return an int representing the origin world
     */
    public int getOrigin()
    {
        return origin;
    }

    /**
     * Returns an int representing the destination world
     * @return an int representing the destination world
     */
    public int getDestination()
    {
        return destination;
    }
            
    /**
     * Prints details of the shuttle to the screen 
     * @return details of the shuttle to the screen as a string
     */
    public String toString()
    {
        return "Shuttle code: " + getCode() + "\nOrigin: " + getOrigin() 
            + "\nDestination: " + getDestination()
            + "\n";
    } 
    
}
