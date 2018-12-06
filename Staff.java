
/**
 * Write a description of class Staff here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Staff extends Card
{
    private int employeeNumber;
    private String department;
    private int journeyScore;

    public Staff(String name, int employeeNumber, String department)
    /* 
     * We already know several facts about the Staff cards, notably:
     * - The 'deduct shuttle price' is always 0
     * - The rating is always 10
     */
    { 
        super(name, 10, 0);
        this.employeeNumber = employeeNumber;
        this.department = department;
        journeyScore = 0; // No option to set the initial journey score.
    }
    
    public int getEmployeeNumber()
    {
        return employeeNumber;
    }
    
    public String getDepartment()
    {
        return department;
    }
    
    public int getJourneyScore()
    {
        return journeyScore;
    }
       
    public void enterShuttle()
    {
        journeyScore++;
    }

    public String toString()
    {
        return "***Staff***" + "\nEmployee number: " + getEmployeeNumber()
            + "\nDepartment: " + getDepartment() + "\nJourney score: " + getJourneyScore()
            + "\n" + super.toString();
    } 
}
