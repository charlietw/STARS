
/**
 * Tourist class, extending the 'Card' class.
 * 
 * @author C. Wren
 * @version 03/12/2018
 */
public class Tourist extends Card
{
    /**
     * The citizenship of the owner of the card
     */
    private String citizenship;

    /**
     * Constructor for 'Tourist' class. 
     * @param name - Name of the owner of the card
     * @param rating - the luxury rating of the card
     * @param credits - the number of credits to initially set the card up with
     * @param citizenship - the citizenship of the owner of the card
     */
    public Tourist(String name, int rating, int credits, String citizenship)
    {
        super(name, rating, credits);
        this.citizenship = citizenship;
    }
    
    /**
     * Returns the citizenship of the owner of the card
     * @return the citizenship of the owner of the card
     */
    public String getCitizenship()
    {
        return citizenship;
    }
    
    /**
     * Reduces the credits by four. To be called when entering shuttle.
     */
    public void enterShuttle()
    {
        deductCredits(4);
    }
    
    /**
     * Returns true if the card has enough credits to make a journey.
     * @return true if the card has enough credits to make a journey.
     */
    public boolean hasEnoughCredits()
    {
        return getCredits() >= 4; // returns true if 'getCredits' returns at least 4
    }

    /**
     * Returns a string containing details of the tourist card.
     * @return a string containing details of the tourist card.
     */
    public String toString()
    {
        return "***Tourist***" + "\nCitizenship: " + getCitizenship() + "\n"
            + super.toString();
    } 
   
}
