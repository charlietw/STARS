
/**
 * Write a description of class Business here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Business extends Card
{
    private int loyaltyPoints;

    public Business(String name, int rating)
    { 
        super(name, rating, 30);
        loyaltyPoints = 20;
    }

    public int getLoyaltyPoints()
    {
        return loyaltyPoints;
    }
      
    public void convertLoyalty()
    {
        addCredits(loyaltyPoints / 5); // adds 1 credit per 5 loyalty points
        loyaltyPoints = loyaltyPoints % 5; // sets loyalty as the remaining number of points       
    }
    
    /**
     * Returns true if the card has enough credits to make a journey.
     * @return true if the card has enough credits to make a journey.
     */
    public boolean hasEnoughCredits()
    {
        return getCredits() >= 3; // returns true if 'getCredits' returns at least 4
    }
    
    public void addLoyalty()
    {
        loyaltyPoints = loyaltyPoints + 2;
    }
    
    public void enterShuttle()
    {
        deductCredits(3);
        addLoyalty();
    }

    public String toString()
    {
        return "***Business***" + "\nLoyalty points: " + getLoyaltyPoints() + "\n"
            + super.toString();
    } 
}
