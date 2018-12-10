import java.util.*;
/**
 * CLI access to the application.
 * 
 * @author C. Wren 
 * @version 03/12/2018
 */
public class ResortUI
{
    /**
     * A 'Scanner' object for interacting with the application via CLI
     */
    private Scanner reader = new Scanner(System.in);
    /**
     * A ResortManager object
     */
    private ResortManager wayward = new ResortManager("Wayward asteroids");
    /**
     * Runs the UI
     */
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
            else if (choice == 7){updateCredits();}
            else if (choice == 8){goHome();}
            else if (choice == 9){convertPts();}
            else if (choice == 10){evacuate();}
            // output menu & get choice
            choice = getOption();
        }
        System.out.println("\nThank-you");
    }
    
    /**
     * Outputs the relevant options to the screen
     */
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
       System.out.println("7. Top up credits");
       System.out.println("8. Move a card to home world");
       System.out.println("9. Convert points to credits - Business only");
       System.out.println("10. Evacuate all");

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
        if (wayward.assertValidCard(trav))
        {
            if (wayward.assertValidShuttle(shuttle))
            {
                System.out.println(wayward.travel(trav,shuttle));
            }
            else
            {
                System.out.println(String.format("%s is not valid shuttle.", shuttle));
            }
        }
        else 
        {
            System.out.println(String.format("Card %s is not valid card ID.", trav));
        }
            
    }
    
    /**
     * Tops up credits if it is a valid card ID
     */
    private void updateCredits()
    {
        System.out.println("Enter card id");
        int cardId = reader.nextInt();
        reader.nextLine();
        if (wayward.assertValidCard(cardId))
        {
            Card cardObj = wayward.getCard(cardId);
            System.out.println(String.format("This card currently has %s " +
            " credits. How many credits would you like to add?", cardObj.getCredits()));
            int credits = reader.nextInt();
            reader.nextLine();
            wayward.topUpCredits(cardId, credits);
            System.out.println(String.format("Top up successful. This card now has %s " +
            " credits.", cardObj.getCredits()));
        }
        else
        {
            System.out.println(String.format("Card %s is not valid card ID.", cardId));
        }
    }
    
    /**
     * Sends a card to the home world without using shuttles or 
     * calling 'enterShuttle' method
     */
    private void goHome()
    {
        System.out.println("Enter card id");
        int cardId = reader.nextInt();
        reader.nextLine();
        if (wayward.assertValidCard(cardId))
        {
            wayward.moveHome(cardId);
            System.out.println("Card successfully moved to the 'Home' world.");
        }
        else
        {
            System.out.println(String.format("Card %s is not valid card ID.", cardId));
        }
    }
   
    /**
     * Converts the loyalty points of a business card to credits
     * (after first checking if it is a business card)
     */
    private void convertPts()
    {
        System.out.println("Enter card id");
        int cardId = reader.nextInt();
        reader.nextLine();
        if (wayward.assertValidCard(cardId))
        {
            Card cardObj = wayward.getCard(cardId);
            if (wayward.assertBusinessCard(cardObj))
            {
                wayward.convertPoints(cardId);
                System.out.println(String.format("Converted loyalty points to credits. New balance is %s credit(s) "+
                " %s loyalty point(s).", cardObj.getCredits(), ((Business) cardObj).getLoyaltyPoints()));
            }
            else
            {
                System.out.println(String.format("Card %s is not a Business card.", cardId));
            }
        }
        else
        {
            System.out.println(String.format("Card %s is not valid card ID.", cardId));
        }
    }

    /**
     * Moves all cards to the home world without using shuttles
     */
    private void evacuate()
    {
        wayward.evacuateAll();
        System.out.println("All cards evacuated and now on the 'Home' world.");
    }
    
    public static void main(String[] args)
    {
        ResortUI xx = new ResortUI();
        xx.runUI();
    }
}
