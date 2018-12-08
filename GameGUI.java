 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the resort
 * 
 * @author A.A.Marczyk
 * @version 09/11/18
 */
public class GameGUI 
{
    private ResortManager waywd = new ResortManager("Wayward Asteroids");
    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton btnAll = new JButton("Show Resort");
    private JButton btnCards = new JButton("Show Cards");
    private JButton btnFind = new JButton("Find Card");
    private JButton btnClear = new JButton("Clear");
    private JButton btnQuit = new JButton("Quit");
    private JPanel panelEast = new JPanel();

    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        contentPane.setLayout(new BorderLayout());
        contentPane.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        contentPane.add(panelEast, BorderLayout.EAST);
        // set panel layout and add components
        panelEast.setLayout(new GridLayout(5,1));
        
        panelEast.add(btnAll);
        btnAll.addActionListener(new AllBtnHandler());
        
        panelEast.add(btnCards);
        btnCards.addActionListener(new CardsBtnHandler());
        
        panelEast.add(btnFind);
        btnFind.addActionListener(new FindItemHandler());
        
        panelEast.add(btnClear);
        btnClear.addActionListener(new ClearBtnHandler());
        
        panelEast.add(btnQuit);
        btnQuit.addActionListener(new QuitBtnHandler());
        
        btnAll.setVisible(true);
        btnCards.setVisible(true);
        btnFind.setVisible(true);
        btnClear.setVisible(true);
        btnQuit.setVisible(true);
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu menuFile = new JMenu("File");
        menubar.add(menuFile);
        
        JMenuItem itmShowAll = new JMenuItem("Show Resort");
        itmShowAll.addActionListener(new ShowAllItemHandler());
        menuFile.add(itmShowAll);

        JMenuItem itmListCards = new JMenuItem("List all cards");
        itmListCards.addActionListener(new ListCardsItemHandler());
        menuFile.add(itmListCards);
        
        JMenuItem itmFind = new JMenuItem("Find a card");
        itmFind.addActionListener(new FindItemHandler());
        menuFile.add(itmFind);

        JMenuItem moveCard = new JMenuItem("Move a card");
        moveCard.addActionListener(new MoveItemHandler());
        menuFile.add(moveCard);
                
    }

    
    private class ShowAllItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = waywd.toString();
            listing.setText(xx);
            
        }
    }
    
    private class ListCardsItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = waywd.getAllCardsOnAllWorlds();
            listing.setText(xx);
        }
    }
    
    private class FindItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String crd = JOptionPane.showInputDialog("Card ?: ");
            int card = Integer.parseInt(crd);
            result = waywd.findCard(card);
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }

    private class MoveItemHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String crd = JOptionPane.showInputDialog("Card ?: ");
            int trav = Integer.parseInt(crd);
            String shuttle = JOptionPane.showInputDialog("Shuttle ?: ");
            int card = Integer.parseInt(crd);
            if (waywd.assertValidCard(trav))
            {
                if (waywd.assertValidShuttle(shuttle))
                {
                    result = waywd.travel(trav,shuttle);
                }
                else
                {
                    result = String.format("%s is not valid shuttle.", shuttle);
                }
            }
            else 
            {
                result = String.format("Card %s is not valid card ID.", trav);
            }
            JOptionPane.showMessageDialog(myFrame,result);
        }
    }
    
    private class AllBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(true);
            String xx = waywd.toString();
            listing.setText(xx);

        }
    }
    
    private class CardsBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(true);
            String xx = waywd.getAllCardsOnAllWorlds();
            listing.setText(xx);

        }
    }
    
    private class ClearBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(false);
        }
    }
    
    private class QuitBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            System.exit(0);
        }
    }
}
   
