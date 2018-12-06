
/**
 * Write a description of class Tourist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tourist extends Card
{
    private String citizenship;

    public Tourist(String name, int rating, int credits, String citizenship)
    {
        super(name, rating, credits);
        this.citizenship = citizenship;
    }
    
    public String getCitizenship()
    {
        return citizenship;
    }
    
    public void enterShuttle() // This has to be unique to the class because the number of credits deducted can vary
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
  
    public String toString()
    {
        return "***Tourist***" + "\nCitizenship: " + getCitizenship() + "\n"
            + super.toString();
    } 
   
}
