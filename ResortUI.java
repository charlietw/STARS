import java.util.*;
/**
 * Write a description of class ResortUI here.
 * 
 * @author C. Wren 
 * @version 03/12/2018
 */
public class ResortUI
{
    
    private Scanner reader = new Scanner(System.in);
    private ResortManager wayward = new ResortManager("Wayward asteroids");

    private void runUI()
    {   
        int choice = getOption();        
        while (choice != 0)
        {            
            // process choice
            if      (choice == 1){listAllWorlds();}
            else if (choice == 2){listAllCards();}
            else if (choice == 3){listOneWorld();}
            else if (choice == 4){findACard();}
            else if (choice == 5){tryTravel();}
            else if (choice == 6){travelNow();}
//             else if (choice == 7){updateCredits();}
//             else if (choice == 8){goHome();}
//             else if (choice == 9){convertPts();}
//             else if (choice == 10){evacuate();}
            // output menu & get choice
            choice = getOption();
        }
        System.out.println("\nThank-you");
    }
    
    
    private int getOption()
    {
       System.out.println("What would you like to do ?");
       System.out.println("0. Quit");
       System.out.println("1. List all world details");
       System.out.println("2. List all cards on all worlds");
       System.out.println("3. List all cards on one world");
       System.out.println("4. Find a card");
       System.out.println("5. Say if card can move by shuttle");
       System.out.println("6. Move a card by shuttle");
//        System.out.println("7. Top up credits");
//        System.out.println("8. Move a card to home world");
//        System.out.println("9. Convert points to credits - Business only");
//        System.out.println("10. Evacuate all");

       System.out.println("Enter your choice");
       // read choice
       int option = reader.nextInt();
       reader.nextLine();
       return option;
    }
    
    /**
     * Prints a string of all of the worlds
     */
    private void listAllWorlds()
    {
        System.out.println(wayward.toString());
    }
    
    /**
     * Prints a string of all of the cards on all of the worlds
     */
    private void listAllCards()
    {
        System.out.println(wayward.getAllCardsOnAllWorlds());
    }
   
    /**
     * Prints one specified world
     */
    private void listOneWorld()
    {
        System.out.println("Enter name of world");
        String ww = reader.nextLine();
        System.out.println(wayward.getAllCardsOnWorld(ww));
    }
    
   
    /**
     * Prints one card
     */
    private void findACard()
    {
        System.out.println("Enter card id");
        int trav = reader.nextInt();
        String foundCard = wayward.findCard(trav);
        System.out.println(foundCard);
    }
       
    /**
     * Displays whether or not an card/shuttle combination is valid
     */
    private void tryTravel()
    {
        System.out.println("Enter card id");
        int trav = reader.nextInt();
        reader.nextLine();
        System.out.println("Enter shuttle code");
        String shuttle = reader.nextLine();
        System.out.println(wayward.canTravel(trav,shuttle));
    }
    
    /**
     * Makes a journey if the card/shuttle combination is valid
     */
    private void travelNow()
    {
        System.out.println("Enter card id");
        int trav = reader.nextInt();
        reader.nextLine();
        System.out.println("Enter shuttle code");
        String shuttle = reader.nextLine();
        wayward.travel(trav,shuttle);
    }
    
//     // provide the code here
//     private void updateCredits()
//     {
//     }
//     
//     // provide the code here
//     private void goHome()
//     {
//     }
//     
//     // provide the code here
//     private void convertPts()
//     {
//     }
//     
//     // provide the code here
//     private void evacuate()
//     {
//     }
    
    public static void main(String[] args)
    {
        ResortUI xx = new ResortUI();
        xx.runUI();
    }
}
