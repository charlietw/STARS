
/**
 * Abstract class for the Tourist, Business and Staff cards
 * which contains the data which is common to all of them
 * 
 * @author C. Wren
 * @version 03/12/2018
 */
abstract class Card
{
    /**
     * The ID of the card starts at this variable and increments
     */
    public static int nextCard = 1000;
    
    /**
     * A unique identifier for each card
     */
    private int cardId;
    
    /**
     * A luxury rating, determing the worlds which this card allows access to
     */
    private int rating;
    
    /**
     * Number of credits on this card
     */
    private int credits;
    
    /**
     * Name of the owner of the card
     */
    private String name;

    public Card(String name, int rating, int credits)
    {
        this.name = name;
        this.rating = rating;
        this.credits = credits;
        cardId = nextCard++;

    }
    
    /**
     * Returns the name of the owner of the card
     * @return a string of the owner of the card
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns an integer of the ID of the card
     * @return an integer of the ID of the card
     */
    public int getId()
    {
        return cardId;
    }

    /**
     * Returns an integer of the rating of the card
     * @return an integer of the rating of the card
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * Returns an integer of the number of credits on the card
     * @return an integer of the number of credits on the card
     */
    public int getCredits()
    {
        return credits;
    }
    
    /**
     * Adds a specified number of credits to the card
     * @param cr an integer of the number of credits to add to the card
     */
    public void addCredits(int cr)
    {
        credits = credits + cr;
    }
    
    /**
     * Removes a specified number of credits from the card
     * @param cr an integer of the number of credits to reduce from the card
     */
    public void deductCredits(int cr)
    {
        credits = credits - cr;
    }
    
    /**
     * Returns true if the card has enough credits to make a journey.
     * If not defined in the subclass then the default behaviour should be
     * to allow the journey, for example in the case of staff.
     * @return true if the card has enough credits to make a journey.
     */
    public boolean hasEnoughCredits()
    {
        return true;
    }
    
   
    abstract void enterShuttle(); // need this even though we cannot specify what a 'card' should do as it is abstract.
    
    /**
     * Prints details of the card to the screen 
     * @return details of the card to the screen as a string
     */
    public String toString()
    {
        return "Card No: " + getId() + "\nName: " + getName() 
            + "\nRating: " + getRating() + "\nCredits: " + getCredits()
            + "\n";
    } 
    
}
