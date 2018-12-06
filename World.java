/**
 * Contains all of the relevant information about a world
 *
 * @author C. Wren
 * @version 03/12/2018
 */
import java.util.*;
public class World 
{
    /**
     * The number of the world
     */
    public int number;
    
    /**
     * The name of the world
     */
    private String name;
    
    /**
     * A luxury rating, to be tested against the luxury rating of the card
     */
    private int rating;
    
    /**
     * Capacity of the world
     */
    private int capacity;
    
    /**
     * Collection of cards currently on the world, referenced by their ID
     */
    private ArrayList<Card> cardsOnWorld;

    public World(int number, String name, int rating, int capacity)
    {
        this.number = number;
        this.name = name;
        this.rating = rating;
        this.capacity = capacity;
        this.cardsOnWorld = new ArrayList<Card>();
    }
    
    /**
     * Returns an integer of the number of the world
     * @return an integer of the number of the world
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * Returns a string of the name of the world
     * @return a string of the name of the world
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns an integer of the rating of the world
     * @return an integer of the rating of the world
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * Returns an integer of the capacity of the world
     * @return an integer of the capacity of the world
     */
    public int getCapacity()
    {
        return capacity;
    }
    
    /**
     * Prints the name of the owner of all cards currently on the world
     */
    public String getAllCards()
    {
        String message;
        if (cardsOnWorld.size() > 0)
        {
            message = "\nCards currently on world:";
            for(Card temp: cardsOnWorld) {
                message += "\n" + temp.getName();
            }
        }
        else 
        {
            message = "\nNo cards";
        }
        return message;
    }
    
    /**
     * Returns true if there is a card with the specified ID on this world
     * @param id - the ID of the specified card
     * @return true if there is a card with the specified ID on this world
     */
    public boolean cardOnWorld(int id)
    {
        for(Card temp: cardsOnWorld) {
            if(temp.getId() == id)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns true if the world is currently at capacity
     * @return true if the world is currently at capacity
     */
    public boolean isFull()
    {
        if(cardsOnWorld.size() >= capacity)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Adds a card to the world.
     * @param cardObj - the card object to add
     */
    public void addCard(Card cardObj)
    {
        if (cardOnWorld(cardObj.getId()) == false)
        {
            cardsOnWorld.add(0, cardObj); // adds the card to the list of cards on the world
        }
        else
        {
            System.out.println("Card is already on this world, so cannot add it.");
        }
            
    }

    /**
     * Removes a card from the world
     * @param cardObj - the card object to remove
     */
    public void removeCard(Card cardObj)
    {
        if (cardOnWorld(cardObj.getId()) == true)
        {
            cardsOnWorld.remove(cardObj); // removes the card from the list of cards on the world
        }
        else
        {
            System.out.println("Card is not on this world, so cannot remove it.");
        }
            
    }
        
        
    /**
     * Prints details of the world to the screen 
     * @return details of the world to the screen as a string
     */
    public String toString()
    {
        return "World No: " + getNumber() + "\nName: " + getName() 
            + "\nRating: " + getRating() + "\nCapacity: " + getCapacity()
            + getAllCards();
    } 
    
}
