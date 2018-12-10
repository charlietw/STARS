/**
 * Staff class, extending the 'Card' class.
 * 
 * @author C. Wren
 * @version 03/12/2018
 */
public class Staff extends Card
{
    /**
     * Integer containing the employee number
     */
    private int employeeNumber;

    /**
     * String containing the department the employee works in
     */
    private String department;
    
    /**
     * Integer containing number of journeys made with this card
     */
    private int journeyScore;

    /**
     * Constructor for 'Staff' class. 'Rating' is always set to 10
     * and 'credits' to 0.
     * @param name - Name of the owner of the card
     * @param employeeNumber - the employee number of the owner
     * @param department - the department the employee works in
     */
    public Staff(String name, int employeeNumber, String department)
    { 
        super(name, 10, 0);
        this.employeeNumber = employeeNumber;
        this.department = department;
        this.journeyScore = 0; // Journey score always starts at 0
    }
    
    /**
     * Returns the employee number of the owner of the card
     * @return the employee number of the owner of the card
     */
    public int getEmployeeNumber()
    {
        return employeeNumber;
    }
    
    /**
     * Returns the department of the owner of the card
     * @return the department of the owner of the card
     */
    public String getDepartment()
    {
        return department;
    }
    
    /**
     * Returns the current journey score for this card
     * @return the current journey score for this card
     */
    public int getJourneyScore()
    {
        return journeyScore;
    }

    /**
     * Increments the journey score. To be called when entering shuttle.
     */
    public void enterShuttle()
    {
        journeyScore++;
    }

    /**
     * Returns a string containing details of the staff card.
     * @return a string containing details of the staff card.
     */
    public String toString()
    {
        return "***Staff***" + "\nEmployee number: " + getEmployeeNumber()
            + "\nDepartment: " + getDepartment() + "\nJourney score: " + getJourneyScore()
            + "\n" + super.toString();
    } 
}
