/**
 * Tester class for the application.
 * 
 * @author C. Wren 
 * @version 03/12/2018
 */
public class MyTester 
{
    private ResortManager testResort = new ResortManager("Test resort");
    /**
     * Runs option one on the menu
     */
    private void doTest1()
    {
        System.out.println("\n****** doTest1() ******\n");
        System.out.println("****** All world details, including cards ******");
        System.out.println(testResort.toString());
    }

    /**
     * Runs option two on the menu
     */
    private void doTest2()
    {
        System.out.println("\n****** doTest2() ******\n");
        System.out.println("****** All card details ******");
        System.out.println(testResort.getAllCardsOnAllWorlds());
    }

    /**
     * Runs option three on the menu with valid and then invalid input
     */
    private void doTest3()
    {
        System.out.println("\n****** doTest3() ******\n");
        System.out.println("****** All cards on 'Home' world ******");
        System.out.println(testResort.getAllCardsOnWorld("Home"));
        System.out.println("****** All cards on 'madeUp' world ******");
        System.out.println(testResort.getAllCardsOnWorld("madeUp"));
    }

    /**
     * Runs option four on the menu with valid and then invalid input
     */
    private void doTest4()
    {
        System.out.println("\n****** doTest4() ******\n");
        System.out.println("****** 'findCard' with input '1001' ******");
        System.out.println(testResort.findCard(1001));
        System.out.println("****** 'findCard' with input '1' ******");
        System.out.println(testResort.findCard(1));
    }

    /**
     * Runs option five on the menu with various options
     */
    private void doTest5()
    {
        int olekCredits;
        
        // Testing valid inputs
        System.out.println("\n****** doTest5() ******\n");
        System.out.println("****** 'canTravel' with input '1001' (real) and 'ABC1' (real) ******");
        System.out.println(testResort.canTravel(1001, "ABC1"));
        System.out.println("****** 'canTravel' with input '1001' (real) and 'AAAA' (not real) ******");
        System.out.println(testResort.canTravel(1001, "AAAA"));
        System.out.println("****** 'canTravel' with input '1' (not real) and 'ABC1' (real) ******");
        System.out.println(testResort.canTravel(1, "ABC1"));
        System.out.println("****** 'canTravel' with input '1' (not real) and 'AAAA' (not real) ******");
        System.out.println(testResort.canTravel(1, "AAAA"));
        System.out.println("****** 'canTravel' with input '1' (not real) and 'AAAA' (not real) ******");
        System.out.println(testResort.canTravel(1, "AAAA"));
        System.out.println("****** Now moving 1003 to world 1... ******");
        System.out.println(testResort.travel(1003, "ABC1"));
        System.out.println("****** All cards on 'Sprite' world ******");
        System.out.println(testResort.getAllCardsOnWorld("Sprite"));
        System.out.println("'Olek' card is now on Sprite. It has a rating of 1 so should be able to travel on GHJ6 but not CDE3 ");
        
        // Testing rating functionality
        System.out.println("****** 'canTravel' with input '1003' and 'GHJ6' ******");
        System.out.println(testResort.canTravel(1003, "GHJ6"));
        System.out.println("****** 'canTravel' with input '1003' and 'CDE3' ******");
        System.out.println(testResort.canTravel(1003, "CDE3"));
        
        // Testing 'card not in source world' functionality
        System.out.println("****** 'canTravel' with input '1003' and 'JKL8' (card not in the source world) ******");
        System.out.println(testResort.canTravel(1003, "JKL8"));
        
        // Testing 'world full' functionality
        System.out.println("****** 'canTravel' with input '1003' and 'GHJ6' (both valid) ******");
        System.out.println(testResort.canTravel(1003, "GHJ6")); // can travel to Solo at this point
        System.out.println(testResort.travel(1001, "ABC1"));
        System.out.println(testResort.travel(1001, "GHJ6"));
        System.out.println("'Nils' card travelled to Sprite, then to Solo, so 'Olek' should not be able to travel CHJ6 now because it is full.");
        System.out.println("****** 'canTravel' with input '1003' and 'GHJ6' ******");
        System.out.println(testResort.canTravel(1003, "GHJ6")); // now can't travel to Solo
        
        // Testing 'not enough credits' functionality
        System.out.println("****** 'canTravel' with input '1003' and 'BCD2' with 8 credits ******");
        System.out.println(testResort.canTravel(1003, "BCD2")); // no longer possible due to not having enough credits
        testResort.getCard(1003).deductCredits(7); // reduces Olek's credits to 1
        olekCredits = testResort.getCard(1003).getCredits();
        System.out.println(olekCredits);
        System.out.println("****** 'canTravel' with input '1003' and 'BCD2' with 1 credit ******");
        System.out.println(testResort.canTravel(1003, "BCD2")); // no longer possible due to not having enough credits
    }
    
    /**
     * Runs option six on the menu with various options
     */
    public void doTest6()
    {
        System.out.println("\n****** doTest6() ******\n");
        System.out.println("Testing with invalid input:");
        System.out.println("****** 'travel' with input '1002' and 'ABC1' (real) ******");
        System.out.println(testResort.travel(1002, "ABC1"));
        System.out.println("****** 'travel' with input '12' (not real) and 'ABC1' (real) ******");
        System.out.println(testResort.travel(12, "ABC1"));
        System.out.println("****** 'travel' with input '1002' (real) and 'AAAA' (not real) ******");
        System.out.println(testResort.travel(1002, "AAAA"));
        
        System.out.println("Testing rating functionality:");
        System.out.println("****** 'travel' with input '1003' and 'ABC1' ******");
        System.out.println(testResort.travel(1003, "ABC1"));
        System.out.println("****** 'travel' with input '1003' and 'CDE3' ******");
        System.out.println(testResort.travel(1003, "CDE3"));
        
        System.out.println("Testing 'world full' functionality :");
        System.out.println("****** 'travel' with input '1003' and 'GHJ6' ******");
        System.out.println(testResort.travel(1003, "GHJ6"));
        System.out.println("****** 'travel' with input '1002' and 'GHJ6' ******");
        System.out.println(testResort.travel(1002, "GHJ6"));

        System.out.println("Testing 'not enough credits' functionality (and multiple messaging):");
        System.out.println("****** 'travel' with input '1003' and 'HJK7'(now has 4 credits) ******");
        System.out.println(testResort.travel(1003, "HJK7"));
        System.out.println(testResort.travel(1002, "GHJ6"));
        System.out.println("****** 'travel' with input '1003' and 'GHJ6'(now has 0 credits, and source world is full) ******");
        System.out.println(testResort.travel(1003, "GHJ6"));

        System.out.println("Testing 'not on source world' functionality:");
        System.out.println("****** 'travel' with input '1002' and 'EFG5' ******");
        System.out.println(testResort.travel(1002, "EFG5"));

    }
    
    /**
     * Tests option seven on the menu (top up credits)
     */
    public void doTest7()
    {
        System.out.println("\n****** doTest7() ******\n");
        Card olek = testResort.getCard(1003);
        System.out.println("Credits on card ID 1003: " + olek.getCredits());
        System.out.println("Adding 3 credits...");
        testResort.topUpCredits(1003, 3);
        System.out.println("Credits on card ID 1003: " + olek.getCredits());
        System.out.println("Testing with invalid ID to ensure it doesn't crash...");
        testResort.topUpCredits(10, 3);
        System.out.println("... still running.");
    }

    /**
     * Tests option eight on the menu (move home)
     */
    public void doTest8()
    {
        System.out.println("\n****** doTest8() ******\n");
        System.out.println("Card ID 1003 location: " + testResort.findCard(1003));
        testResort.travel(1003, "ABC1");
        System.out.println("Card ID 1003 location: " + testResort.findCard(1003));
        System.out.println("Calling 'moveHome(1003)'...");
        testResort.moveHome(1003);
        System.out.println("Card ID 1003 location: " + testResort.findCard(1003));
    }

    /**
     * Tests option nine on the menu (convert loyalty to credits)
     */
    public void doTest9()
    {
        System.out.println("\n****** doTest9() ******\n");
        Card cardObj = testResort.getCard(1001);
        System.out.println(String.format("Current loyalty points/credits: %s / %s",((Business) cardObj).getLoyaltyPoints(), cardObj.getCredits()));
        System.out.println("Converting loyalty points...");
        testResort.convertPoints(1002); // should do nothing (but not crash)
        testResort.convertPoints(1000); // should do nothing (but not crash)
        testResort.convertPoints(1001);
        System.out.println(String.format("New loyalty points/credits: %s / %s",((Business) cardObj).getLoyaltyPoints(), cardObj.getCredits()));
    }
   
    
    public static void main(String[] args)
    {
        MyTester xx = new MyTester();
        xx.doTest1(); // can comment and uncomment these as required
        xx.doTest2();
        xx.doTest3();
        xx.doTest4();
        xx.doTest5();
        xx.doTest6();
        xx.doTest7();
        xx.doTest8();
        xx.doTest9();
        
    }
}
