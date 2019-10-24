import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; //Get all of Swing

public class Board extends JFrame implements ActionListener {
	
	private JPanel gridPanel = new JPanel();
	
	private BoardPiece [][] boardGrid;
	private Piece [] pieceList;
	
	private int row = 10;
	private int col = 10;
	
	
	public void drawBoard() {
		 
		 gridPanel.setLayout(new GridLayout(row, col));
		 
		 //Array of pieces, each with their own unique ID
		 pieceList = new Piece[21];
		 
		 for (int i = 0; i <= 20; i++) {
			 pieceList[i] = new Piece(i);
			 //Selection demo, change i to the corresponding shape ID you wish to play
			 if (i == 20) {
				 pieceList[i].selected = true;
			 }
		 }
	
		 // 2D array of board pieces	 
		 boardGrid = new BoardPiece[row][col];
		 
		 for (int i = 0; i < row; i++) { 
	            for (int j = 0; j < col; j++) { 
	            	boardGrid[i][j] = new BoardPiece(i, j, this);
	            	boardGrid[i][j].addActionListener(this);
	            	gridPanel.add(boardGrid[i][j]);          	                
	            }   
	        } 
		 add(gridPanel);
		 setVisible(true);			 
	}
		
	public void actionPerformed(ActionEvent e){
				//Probably not needed
	}

	public BoardPiece[][] getBoard() {
		return boardGrid;
	}
	
	public void printPieces() {
		for (int i = 0; i <= 20; i++) {
			System.out.println(pieceList[i].id);
		}
	}
	
	public void findSelectedPiece(BoardPiece moveLocation) { //Upon click of a grid square when attempting to place a object, this method quickly scans through the array of shapes to determine which is active
		for (int i = 0; i <= 20; i++) {
			if (pieceList[i].selected) {
				isValid(moveLocation, pieceList[i]);
			}
		}
	}
	
	public boolean isValid(BoardPiece moveLocation, Piece selectedPiece) {
		
		boolean valid = false;
		
		//debugging logs
		System.out.println("Board's row Position: " + moveLocation.row);
		System.out.println("Board's col Position: " + moveLocation.col);
		System.out.println("Selected Piece ID: " + selectedPiece.id);
		System.out.println("Is this board location active?: " + moveLocation.active);
		
		
		//Below is a series of IF statements, which will check if a shape is about to be placed in a valid location based on the selected shapes ID.
		//Does not account for rotations at the moment. 
		//This is obviously hard coded, due to time constraints. 
		
		if (selectedPiece.id == 0 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col+1].active) && (!boardGrid[moveLocation.row][moveLocation.col-1].active) &&
						(!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row-1][moveLocation.col].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
						System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//3 square rectangle
		else if (selectedPiece.id == 1 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row-1][moveLocation.col].active)){
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);			
					}
					else {  
							//Unsuccessful Placement
						System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//w shape
		else if (selectedPiece.id == 2 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active) && (!boardGrid[moveLocation.row-1][moveLocation.col-1].active) &&
						(!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row+1][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);			
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//5 blocks
		else if (selectedPiece.id == 3 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col-1].active) && (!boardGrid[moveLocation.row][moveLocation.col-1].active) &&
						(!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row+1][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//2 block rectangle
		else if (selectedPiece.id == 4 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);			
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//L shape
		else if (selectedPiece.id == 5 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active) && (!boardGrid[moveLocation.row+1][moveLocation.col].active) &&
						(!boardGrid[moveLocation.row+2][moveLocation.col].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//U shape
		else if (selectedPiece.id == 6 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col].active) && (!boardGrid[moveLocation.row-1][moveLocation.col+1].active) &&
						(!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row+1][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		// 4 straight, one off
		else if (selectedPiece.id == 7 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col].active) && (!boardGrid[moveLocation.row-2][moveLocation.col].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active) && (!boardGrid[moveLocation.row+1][moveLocation.col].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//single piece
		else if (selectedPiece.id == 8) {
			if (!moveLocation.active) {
				//Successful Placement
				System.out.println("Successful placement");
				valid = true;
				placePiece(moveLocation, selectedPiece);	
			}
			else {
				//Unsuccessful Placement
				System.out.println("Unsuccessful placement");
				valid = false;;
			}		 
		}
		// double L shape
		else if (selectedPiece.id == 9 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active) && (!boardGrid[moveLocation.row-1][moveLocation.col-1].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active) && (!boardGrid[moveLocation.row+1][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//large arrow shape
		else if (selectedPiece.id == 10 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active) && (!boardGrid[moveLocation.row-1][moveLocation.col].active) &&
						(!boardGrid[moveLocation.row-2][moveLocation.col].active) && (!boardGrid[moveLocation.row][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//small corner shape
		else if (selectedPiece.id == 11 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col].active) && (!boardGrid[moveLocation.row][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//square shape
		else if (selectedPiece.id == 12 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row+1][moveLocation.col+1].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//Full L shape
		else if (selectedPiece.id == 13 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col].active) && (!boardGrid[moveLocation.row-1][moveLocation.col+1].active) &&
						(!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row+1][moveLocation.col].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//5 square rectangle
		else if (selectedPiece.id == 14 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active) && (!boardGrid[moveLocation.row][moveLocation.col-2].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active) && (!boardGrid[moveLocation.row][moveLocation.col+2].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//Big corner shape
		else if (selectedPiece.id == 15 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col].active) && (!boardGrid[moveLocation.row-2][moveLocation.col].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active) && (!boardGrid[moveLocation.row][moveLocation.col+2].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//up-down shape
		else if (selectedPiece.id == 16 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col].active) && (!boardGrid[moveLocation.row][moveLocation.col+1].active) &&
						(!boardGrid[moveLocation.row+1][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//odd up-down shape
		else if (selectedPiece.id == 17 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row-1][moveLocation.col].active) && (!boardGrid[moveLocation.row][moveLocation.col+1].active) &&
						(!boardGrid[moveLocation.row+1][moveLocation.col+1].active) && (!boardGrid[moveLocation.row+2][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//square shape + 1
		else if (selectedPiece.id == 18 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row+1][moveLocation.col].active) && (!boardGrid[moveLocation.row+1][moveLocation.col+1].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active)  && (!boardGrid[moveLocation.row+1][moveLocation.col+2].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//4 square rectangle
		else if (selectedPiece.id == 19 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active) && (!boardGrid[moveLocation.row][moveLocation.col-2].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		//Small arrow shape
		else if (selectedPiece.id == 20 && !moveLocation.active) {
			//Is it normal rotation?
			if (selectedPiece.rotation == 0) {
				//Are the needed relative board locations available?
				if ((!boardGrid[moveLocation.row][moveLocation.col-1].active) && (!boardGrid[moveLocation.row-1][moveLocation.col].active) &&
						(!boardGrid[moveLocation.row][moveLocation.col+1].active)) {
							//Successful Placement
							System.out.println("Successful placement");
							valid = true;
							placePiece(moveLocation, selectedPiece);		
					}
					else {  
							//Unsuccessful Placement
							System.out.println("Unsuccessful placement");
							valid = false;;
					}	
			}		 
		}
		
		return valid;	
	}
	
	
	
	public void placePiece(BoardPiece moveLocation, Piece selectedPiece) {
		
		//Below is an IF statement for each type of shape, sorted by their ID.
		
		//Cross piece
		if (selectedPiece.id == 0) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
		}
		//3 square rectangle
		else if (selectedPiece.id == 1) {
			moveLocation.setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
		}
		//w shape
		else if (selectedPiece.id == 2) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
		}
		//5 blocks
		else if (selectedPiece.id == 3) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
		}
		//2 block rectangle
		else if (selectedPiece.id == 4) {
			moveLocation.setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
		}
		//odd L-Shape
		else if (selectedPiece.id == 5) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-2][moveLocation.col].setActive();
		}
		//U-Shape
		else if (selectedPiece.id == 6) {
			moveLocation.setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
		}
		//4 straight, one off
		else if (selectedPiece.id == 7) {
			moveLocation.setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-2][moveLocation.col].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
		}
		//Single piece
		else if (selectedPiece.id == 8) {
			moveLocation.setActive();
		}
		//Double L shape
		else if (selectedPiece.id == 9) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
		}
		//Large arrow shape
		else if (selectedPiece.id == 10) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-2][moveLocation.col].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
		}
		//Small corner shape
		else if (selectedPiece.id == 11) {
			moveLocation.setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
		}
		//Square shape
		else if (selectedPiece.id == 12) {
			moveLocation.setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
		}
		//L shape
		else if (selectedPiece.id == 13) {
			moveLocation.setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
		}
		//5 square rectangle
		else if (selectedPiece.id == 14) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row][moveLocation.col-2].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row][moveLocation.col+2].setActive();
		}
		//Big corner shape
		else if (selectedPiece.id == 15) {
			moveLocation.setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row-2][moveLocation.col].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row][moveLocation.col+2].setActive();
		}
		//up-down shape
		else if (selectedPiece.id == 16) {
			moveLocation.setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
		}
		//odd up-down shape
		else if (selectedPiece.id == 17) {
			moveLocation.setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+2][moveLocation.col+1].setActive();
		}
		//Square shape + 1
		else if (selectedPiece.id == 18) {
			moveLocation.setActive();
			boardGrid[moveLocation.row+1][moveLocation.col].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
			boardGrid[moveLocation.row+1][moveLocation.col+2].setActive();
		}
		//4 square rectangle
		else if (selectedPiece.id == 19) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row][moveLocation.col-2].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
		}
		//Small arrow shape
		else if (selectedPiece.id == 20) {
			moveLocation.setActive();
			boardGrid[moveLocation.row][moveLocation.col-1].setActive();
			boardGrid[moveLocation.row-1][moveLocation.col].setActive();
			boardGrid[moveLocation.row][moveLocation.col+1].setActive();
		}
		
	}
}
