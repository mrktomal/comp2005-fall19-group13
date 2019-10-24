import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Node extends JButton implements ActionListener{
	
	public boolean active = true;
	
	public int row;
	public int col;
	
	public Node(int newRow, int newCol){
		
		this.addActionListener(this);
		row = newRow;
		col = newCol;	
	}
	
	public void actionPerformed(ActionEvent e){
		
		System.out.println(row + " " + col);
	}
}
