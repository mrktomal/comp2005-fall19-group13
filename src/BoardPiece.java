import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoardPiece extends JButton implements ActionListener{
	
	public boolean active = false;
	
	public int row;
	public int col;
	
	public Board gameBoard;
	public BoardPiece [][] boardGrid;
	
	public BoardPiece (int newRow, int newCol, Board newBoard){
		
		this.addActionListener(this);
		row = newRow;
		col = newCol;
		gameBoard = newBoard;
	}
	
	public void actionPerformed(ActionEvent e){
		gameBoard.findSelectedPiece(this); //find what shape is selected, the board will handle the rest.
	}
	
	public void setActive() {
		this.setBackground(Color.DARK_GRAY);
		this.setOpaque(true);
		active = true;
	}
}
