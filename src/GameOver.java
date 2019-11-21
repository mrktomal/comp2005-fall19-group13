import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class GameOver extends JFrame implements ActionListener {
	
	public JButton playBTN, menuBTN;
	public JTable table;
	public JLabel label;
	public JFrame frame;
	public JPanel panel;
	
	
    public GameOver(ArrayList<Player> players) {
    	
        frame = new JFrame("GAME OVER!");
        

        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setPreferredSize(new Dimension(800,800));
        panel.setOpaque(true);
        panel.setBackground(Color.YELLOW);

        
        int winnerIndex = 0;
        int lowestScore = 0;
        
        for (int i = 0; i < players.size(); i++) {
        	if (players.get(i).getScore() < lowestScore) {
        		lowestScore = players.get(i).getScore();
        		winnerIndex = i;   		
        	}
        }
        
        StringBuilder sb = new StringBuilder(64);
        sb.append("<html>GAME OVER <br>").
           append(" <br> </br>").
           append(players.get(winnerIndex).getName().toString()).
           append("</br> Wins!");

        label = new JLabel(sb.toString());
        //label.setOpaque(true);
        //label.setBackground(Color.YELLOW);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        Font f = new Font("Arial", Font.PLAIN, 30);
        label.setFont(f);
        panel.add(label);

        String[][] data = null;
        
	    // Data to be displayed in the JTable 
        
        if (players.size() == 2) {
        	String[][] newData = { 
    	    		{ players.get(0).getName(), Integer.toString(players.get(0).getScore())}, 
    	    		{ players.get(1).getName(), Integer.toString(players.get(1).getScore()), } 
    	    }; 
        	data = newData;
        	
        }
        else if (players.size() == 3) {
        	String[][] newData = { 
    	    		{ players.get(0).getName(), Integer.toString(players.get(0).getScore())}, 
    	    		{ players.get(1).getName(), Integer.toString(players.get(1).getScore())},
    	    		{ players.get(2).getName(), Integer.toString(players.get(3).getScore()), } 
    	    }; 
        	data = newData;
        }
        else if (players.size() == 4) {
        	String[][] newData = { 
    	    		{ players.get(0).getName(), Integer.toString(players.get(0).getScore())}, 
    	    		{ players.get(1).getName(), Integer.toString(players.get(1).getScore())},
    	    		{ players.get(2).getName(), Integer.toString(players.get(2).getScore())},
    	    		{ players.get(3).getName(), Integer.toString(players.get(3).getScore()), } 
    	    }; 
        	data = newData;
        }

        String[] columnNames = { "Player", "Score"}; 
		table = new JTable(data, columnNames);  
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);

         
        playBTN = new JButton("Play Again");
        playBTN.addActionListener(this);
        playBTN.setFont(f);
        panel.add(playBTN);

        
        menuBTN = new JButton("Main Menu");
        menuBTN.addActionListener(this);
        menuBTN.setFont(f);
        panel.add(menuBTN);
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);  

    }


	@Override
	public void actionPerformed(ActionEvent e) {
		 final Object source = e.getSource();
	        if(source == playBTN); {
	            frame.setVisible(false);
	        	NewGameMenu NMen = new NewGameMenu();
	            NMen.init();
	        }	
	        if (source == menuBTN) {
	        	frame.setVisible(false);
	            MainMenu men = new MainMenu();
	            men.init();
	        }
	}
}