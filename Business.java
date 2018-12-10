/**
 * Business class, extending the 'Card' class.
 * 
 * @author C. Wren
 * @version 03/12/2018
 */
public class Business extends Card
{
    /**
     * Loyalty points, which are exclusive to business cards,
     * can be exchanged for credits at a rate of 5 to 1.
     */
    private int loyaltyPoints;

    /**
     * Constructor for 'Business' class. Always sets the 
     * number of initial credits to 30 and the number of 
     * loyalty points to 20.
     * @param name - Name of the owner of the card
     * @param rating - the luxury rating of the card
     */
    public Business(String name, int rating)
    { 
        super(name, rating, 30);
        loyaltyPoints = 20;
    }

    /**
     * Returns the current number of loyalty points
     * @return the current number of loyalty points
     */
    public int getLoyaltyPoints()
    {
        return loyaltyPoints;
    }
    
    /**
     * Converts loyalty points to credits at a rate of five loyalty points
     * to one credit. 
     */
    public void convertLoyalty()
    {
        addCredits(loyaltyPoints / 5); // adds 1 credit per 5 loyalty points
        loyaltyPoints = loyaltyPoints % 5; // sets loyalty as the remaining number of points       
    }
    
    /**
     * Adds two loyalty points to the card (to be called when 
     * entering shuttle).
     */
    public void addLoyalty()
    {
        loyaltyPoints = loyaltyPoints + 2;
    }
    
    /**
     * Returns true if the card has enough credits to make a journey.
     * @return true if the card has enough credits to make a journey.
     */
    public boolean hasEnoughCredits()
    {
        return getCredits() >= 3;
    }
    
    /**
     * Deducts three credits and adds two loyalty points.
     * To be called when entering shuttle.
     */
    public void enterShuttle()
    {
        deductCredits(3);
        addLoyalty();
    }

    /**
     * Returns a string containing details of the business card.
     * @return a string containing details of the business card.
     */
    public String toString()
    {
        return "***Business***" + "\nLoyalty points: " + getLoyaltyPoints() + "\n"
            + super.toString();
    } 
}
