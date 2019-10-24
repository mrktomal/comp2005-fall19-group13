import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; //Get all of Swing

public class Piece extends JFrame implements ActionListener {

	private JPanel gridPanel = new JPanel();

	private Node [][] gridList;

	private int row = 5;
	private int col = 5;
	int id;

	public int rotation = 0;

	public boolean selected = false;

	public Piece (int newID) {
		id = newID;
	}


	public void drawPiece() {

		 gridPanel.setLayout(new GridLayout(row, col));


		 gridList = new Node[row][col];

		 for (int i = 0; i < row; i++) {
	            for (int j = 0; j < col; j++) {
	            	gridList[i][j] = new Node(i, j);
	            	gridList[i][j].addActionListener(this);
	            	gridPanel.add(gridList[i][j]);
	            }

	        }

		 add(gridPanel);
		 setVisible(true);

		 //test to see if unsucessful attempts work
		 gridList[0][1].setVisible(false);

	}

	public void actionPerformed(ActionEvent e){

	}




}
