import java.util.*;

/**This class implements the ResortControl interface
 *
 * @author A.A.Marczyk 
 * @version 09/11/18
 * 
 * @author C. Wren
 * @version 04/12/2018
 * 
 **/
public class ResortManager implements ResortControl
{
    private ArrayList<World> worlds;
    private ArrayList<Card> cards;
    private ArrayList<Shuttle> shuttles; // creating the ArrayLists to store the data
    private String location;

    public ResortManager(String location)
    {
        worlds = new ArrayList<World>();
        cards = new ArrayList<Card>();
        shuttles = new ArrayList<Shuttle>();
        this.location = location;
        loadWorlds();
        setUpShuttles();
        loadCards();
    }
    
    /**
     * Returns all of the details of all worlds including the cards
     * currently on each world, or "No cards"
     * @return all of the details of all worlds including location 
     * and all cards currently on each world, or "No cards" 
     */
    public String toString()
    {
        String details = "";
        for(World temp: worlds) {
            details += "\n" + temp.toString() + "\n";
        }
        return details;
    }

    /**Returns a String representation of all the cards on all worlds
     * @return a String representation of all cards on all worlds
     **/
    public String getAllCardsOnAllWorlds()
    {
        String details = "";
        for(Card temp: cards) 
        {
            details += "\n" + temp.toString();
        }
        return details;
    }
    
    
    /**Returns the name of the world which contains the specified card or null
     * @param tr - the specified card
     * @return the name of the World which contains the card, or null
     **/
    public String findCard(int tr)
    {
        String returnWorld = null;
        for(World tempWorld: worlds) // for each world...
        {
            if (tempWorld.cardOnWorld(tr) == true) // if the card is there
            {
                returnWorld = tempWorld.getName(); // return the name
                break; // exit the loop
            }
        }
        return returnWorld; // return either the name or null
    }
    
    
    /** Given the name of a world, returns the world id number
     * or -1 if world does not exist
     * @param name of world
     * @return id number of world
     */
    public int getWorldNumber(String ww)
    {
        int worldNumber = -1;
        for(World tempWorld: worlds)
        {
            if (tempWorld.getName() == ww) 
            {
                worldNumber = tempWorld.getNumber(); // return the number
                break; // exit the loop
            }
        }
        return worldNumber; // return either the number or -1
    }
    
                
    /**Returns a String representation of all the cards on specified world
     * @param world - name of world
     * @return a String representation of all cards on specified world
     **/
    public String getAllCardsOnWorld(String world)
    {
        String message;
        World worldObj = getWorldFromName(world); // Takes the string of the name and returns the world object
        if (worldObj == null)
        {
            message = "\nWorld does not exist. Please enter a valid world name.";
        }
        else 
        {
            message = worldObj.getAllCards();
        }
        return message;
    }
    
    /**Returns true if a Card is allowed to move using the shuttle, false otherwise
     * A move can be made if:  
     * the rating of the card  >= the rating of the destination world
     * AND the destination world is not full
     * AND the card has sufficient credits
     * AND the card is currently in the source world
     * AND the card id is for a card on the system
     * AND the shuttle code is the code for a shuttle on the system
     * @param trId is the id of the card requesting the move
     * @param shtlCode is the code of the shuttle journey by which the card wants to pCardel
     * @return true if the card is allowed on the shuttle journey, false otherwise 
     **/
    public boolean canTravel(int trId, String shtlCode)
    {
        
        if (!assertValidCardAndWorld(trId, shtlCode)) // early checks to avoid null pointer when getting objects
        {
            return false;
        }
        Card cardObj = getCard(trId); // translate the 'trId' integer to a card object
        Shuttle shuttleObj = getShuttle(shtlCode); // translate the 'shtlCode' string to a shuttle object
        World sourceWorld = getWorldFromId(shuttleObj.getOrigin()); // ...as otherwise could not do this processing
        World destWorld = getWorldFromId(shuttleObj.getDestination()); // translate the integers in the shuttle class to World objects
        if (
            cardObj.getRating() >= destWorld.getRating() 
            && destWorld.isFull() == false
            && cardObj.hasEnoughCredits() == true
            && sourceWorld.cardOnWorld(trId) == true
        ) 
        {
            return true;
        }
        return false;
    }
    

    /**Returns the result of a card requesting to move by Shuttle.
     * A move will be successful if:  
     * the luxury rating of the card  >= the luxury rating of the destination world
     * AND the destination world is not full
     * AND the card has sufficient credits
     * AND the card is currently in the source world
     * AND the card id is for a card on the system
     * AND the shuttle code is the code for a shuttle on the system
     * If the shuttle journey can be made, the card information is removed from the source
     * world, added to the destination world and a suitable message returned.
     * If shuttle journey cannot be made, the state of the system remains unchanged
     * and a message specifying the reason is returned.
     * @param pCardId is the id of the card requesting the move
     * @param shtlCode is the code of the shuttle journey by which the card wants to pCardel
     * @return a String giving the result of the request 
     **/
    public String travel(int pCardId, String shtlCode )
    {
        if (!assertValidCardAndWorld(pCardId, shtlCode)) // early checks to avoid null pointer when getting objects
        {
            return "Invalid card or world";
        }
        Card cardObj = getCard(pCardId); // translate the 'trId' integer to a card object
        Shuttle shuttleObj = getShuttle(shtlCode); // translate the 'shtlCode' string to a shuttle object
        World sourceWorld = getWorldFromId(shuttleObj.getOrigin()); 
        World destWorld = getWorldFromId(shuttleObj.getDestination()); // translate the integers in the shuttle class to World objects
        String reasons = "";
        if (!canTravel(pCardId, shtlCode)) // if this is false, then need to do determine what caused it
        {
            if (cardObj.getRating() < destWorld.getRating()) // tests the rating of the destination world
            {
                reasons += "\nCard rating lower than destination world rating.";
            }
            if (destWorld.isFull() == true)
            {
                reasons += "\nDestination world is full.";
            }
            if (cardObj.hasEnoughCredits() == false)
            {
                reasons += "\nCard does not have enough credits.";
            }

            if (sourceWorld.cardOnWorld(pCardId) == false) // in case the card is not present
            {
                reasons += "\nCard not on source world.";
            }
        }
        else
        {
            cardObj.enterShuttle(); // handles the logic in the relevant class
            sourceWorld.removeCard(cardObj);
            destWorld.addCard(cardObj);
            return "Journey completed successfully.";
        }
        return reasons;

    }
    
     
    // These methods are for Task 6 only and not required for the Demonstration 
    // If you choose to implement them, uncomment the following code    
    /** Allows a card to top up their credits.This method is not concerned with 
     *  the cost of a credit as currency and prices may vary between resorts.
     *  
     *  N.B. because this, 'moveHome' and 'convertPoints' are all mutators 
     *  which return 'void', error checking has been completed on this side
     *  and the client side; no error message can be returned from the functions, and 
     *  it cannot be assumed that the user will be using a CLI to access these and
     *  therefore see any prints to screen.
     *  
     *  @param id the id of the card toping up their credits
     *  @param creds the number of credits purchased to be added to cards information
     */
    public void topUpCredits(int id, int creds)
    {
        if (assertValidCard(id)) // only do this if it is valid, otherwise do nothing
        {
            Card cardObj = getCard(id);
            cardObj.addCredits(creds);
        }
    }
    
    
    /** Moves a card directly back to the home world without affecting credits
     *  and not using existing shuttles
     */
    public void moveHome(int id)
    {
        if (assertValidCard(id)) // only do this if it is valid, otherwise do nothing
        {
            Card cardObj = getCard(id);
            String worldStr = findCard(id); // gets the name of the world the card is currently on
            World worldObj = getWorldFromName(worldStr); // gets the world object from that string
            // could alternatively be:
            // World worldObj = getWorldFromName(findCard(id));
            worldObj.removeCard(cardObj); // removes the card from the ArrayList
            World homeWorld = getWorldFromId(0); // gets the home world
            homeWorld.addCard(cardObj); // adds the card to the home world
        }
    }
  
    /** Converts a business card's loyalty points into credits
     * @param tr the id of the card whose points are to be converted
     */
    public void convertPoints(int id)
    {
        if (assertValidCard(id)) // only do this if it is valid, otherwise do nothing
        {
            Card cardObj = getCard(id);
            if (assertBusinessCard(cardObj)) // to avoid crashes at runtime
            {
                ((Business) cardObj).convertLoyalty(); // safely downcast the 'Card' object to a 'Business' object
                // N.B. to actually get the object rather than just call the method:
                // Business businessCardObj = (Business) cardObj;
            }
        }
    }
    
    /** In an emergency, evacuates all cards directly back to the home world without 
     * affecting credits or other information and not using existing shuttles
     */
    public void evacuateAll()
    {
        clearAllCards();
        allCardsHome();
    }
   
    /**
     * Returns 'true' if the card is on the system, otherwise return 'false'
     * @param cardId - an integer representing a card
     * @return 'true' if the card is on the system, otherwise return 'false'
     */
    public boolean assertValidCard(int cardId)
    {
        Card cardObj = getCard(cardId);
        if (cardObj == null)
        {
            return false;
        }
        return true;
    }

    /**
     * Returns 'true' if the card is of the specified type, otherwise 'false'
     * @param cardObj - a string representing the type of card required
     * @return 'true' if the card is of the specified type, otherwise 'false'
     */
    public boolean assertBusinessCard(Card cardObj)
    {
        if (cardObj instanceof Business)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns 'true' if the shuttle is on the system, otherwise return 'false'
     * @param shtlCode - a string representing a shuttle code
     * @return 'true' if the shuttle is on the system, otherwise return 'false'
     */
    public boolean assertValidShuttle(String shtlCode)
    {
        Shuttle shuttleObj = getShuttle(shtlCode);
        if (shuttleObj == null)
        {
            return false;
        }
        return true;
    }

    
    /**
     * Combines 'assertValidCard' and 'assertValidShuttle' functions, returning 'true' if they are both true, otherwise 'false'.
     * @param cardId - an integer representing a card
     * @param shtlCode - a string representing a shuttle code
     * @return 'true' if they are both true, otherwise 'false'.
     */
    public boolean assertValidCardAndWorld(int cardId, String shtlCode)
    {
        if (assertValidCard(cardId) && assertValidShuttle(shtlCode)) // if either of these are true then return false.
        {
            return true;
        }
        return false;
    }
    
    private void loadWorlds()
    {
        World home = new World(0, "Home", 0, 1000);
        World sprite = new World(1, "Sprite", 1, 100);
        World tropicana = new World(2, "Tropicana", 3, 10);
        World fantasia = new World(3, "Fantasia", 5, 2);
        World solo = new World(4, "Solo", 1, 1);
        worlds.add(0, home);
        worlds.add(1, sprite);
        worlds.add(2, tropicana);
        worlds.add(3, fantasia);
        worlds.add(4, solo);
    }
    
    private void setUpShuttles()
    {
        Shuttle abc1 = new Shuttle("ABC1", 0, 1);
        Shuttle bcd2 = new Shuttle("BCD2", 1, 0);
        Shuttle cde3 = new Shuttle("CDE3", 1, 2);
        Shuttle def44 = new Shuttle("DEF44", 2, 1);
        Shuttle jkl8 = new Shuttle("JKL8", 2, 3);
        Shuttle efg5 = new Shuttle("EFG5", 3, 1);
        Shuttle ghj6 = new Shuttle("GHJ6", 1, 4);
        Shuttle hjk7 = new Shuttle("HJK7", 4, 1);
        shuttles.add(abc1);
        shuttles.add(bcd2);
        shuttles.add(cde3);
        shuttles.add(def44);
        shuttles.add(jkl8);
        shuttles.add(efg5);
        shuttles.add(ghj6);
        shuttles.add(hjk7);
    }
    
    private void loadCards()
    {
        Tourist lynn = new Tourist("Lynn", 5, 10, "Earth");
        Business may = new Business("May", 3);
        Staff nils = new Staff("Nils", 12345, "Cook");
        Tourist olek = new Tourist("Olek", 1, 12, "Earth"); 
        Tourist pan = new Tourist("Pan", 3, 3, "Mars");
        Business quin = new Business("Quin", 1);
        Staff raj = new Staff("Raj", 23456, "Cook");
        Tourist sol = new Tourist("Sol", 7, 20, "Moon");
        Business tel = new Business("Tel", 6);
        cards.add(lynn);
        cards.add(may);
        cards.add(nils);
        cards.add(olek);
        cards.add(pan);
        cards.add(quin);
        cards.add(raj);
        cards.add(sol);
        cards.add(tel);
        allCardsHome(); // Sends all the cards to the home world to start with
    }
    
    /**
     * Sends all cards to the 'home' world.
     */
    private void allCardsHome()
    {
        World homeWorld = getWorldFromId(0);
        for(Card temp: cards)
        {
            homeWorld.addCard(temp);
        }
    }

    /**
     * Clears all cards from all worlds.
     */
    private void clearAllCards()
    {
        for(World temp: worlds)
        {
            temp.removeAllCards();
        }
    }

    /** Returns the card with the card id specified by the parameter
     * @return the card with the specified name
     **/
    public Card getCard(int id)
    {
        Card returnCard = null;
        for(Card temp: cards) 
        {
            if(temp.getId() == id)
            {
                returnCard = temp; // save the Card to the variable
                break; // break out of the for loop
            }
        }
        return returnCard;
    }
    
    /** Returns the world with the world id specified by the parameter
     * @return the world with the specified name
     **/
    public World getWorldFromId(int id)
    {
        World returnWorld = null;
        for(World temp: worlds) 
        {
            if(temp.getNumber() == id)
            {
                returnWorld = temp;
                break;
            }
        }
        return returnWorld;
    }
    
    /** Returns the world with the name specified by the parameter
     * @return the world with the specified name
     **/
    private World getWorldFromName(String worldName)
    {
        World returnWorld = null;
        for(World temp: worlds) 
        {
            if(temp.getName().equals(worldName))
            {
                returnWorld = temp;
                break;
            }
        }
        return returnWorld;
    }
    
    /** Returns the shuttle with the name specified by the parameter
     * @return the shuttle with the specified name
     **/
    private Shuttle getShuttle(String shut)
    {
        Shuttle returnShuttle = null;
        for(Shuttle temp: shuttles) 
        {
            if(temp.getCode().equals(shut))
            {
                returnShuttle = temp;
                break;
            }
        }
        return returnShuttle;
    }
}