import java.util.*;
/**
 * A class to test and demonstrate polymorphism and inheritance
 * 
 * @author C. Wren 
 * @version 03/12/2018
 */
public class InheritanceTest
{   
    /**
     * Sets up and runs the relevant tests to display inheritance.
     */
    private void doTests()
    { 
        // Set up some test objects (as per appendix A)
        Tourist lynn = new Tourist("Lynn", 5, 10, "Earth");
        Business may = new Business("May", 3);
        Staff nils = new Staff("Nils", 12345, "Cook");
        
        ArrayList<Card> myArrayList = new ArrayList<Card>();
        
        myArrayList.add(lynn);
        myArrayList.add(may);
        myArrayList.add(nils);
        
        // For task 2 - testing inheritance
        
        for(Card temp: myArrayList) {
            System.out.println(temp.toString()); // calling an overwridden method, which in turn calls 'super' method
        }
        
        for(Card temp: myArrayList) {
            System.out.println(temp.getName()); // calling a method defined only in the parent class
        }
        
        System.out.println(nils.getEmployeeNumber()); // calling a method unique to the subclass 'staff'

    }
     
    public static void main(String[] args)
    {
        InheritanceTest xx = new InheritanceTest();
        xx.doTests();
    }
}

