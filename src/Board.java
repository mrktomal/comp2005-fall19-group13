import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.*; //Get all of Swing

public class Board extends JFrame implements ActionListener {
	
	protected class GridSquare extends JButton implements ActionListener{
		
		protected boolean active = false;
		protected String colour = null;
		protected int x; // x-coordinate
		protected int y; // y-coordinate
		
		public GridSquare(int newRow, int newCol) {
			
			x = newRow;
			y = newCol;
			this.addActionListener(this);
			this.addMouseListener(new MouseAdapter() {

	            @Override
	            public void mouseEntered(MouseEvent me) {
	            	if (Board.this.pieceSelected()) {
	            		Board.this.hover(GridSquare.this);
	            	}
	            	//System.out.println(GridSquare.this.toString());
	            }
	            
	            public void mouseExited(MouseEvent me) {
	            	Board.this.unHover();
	            }
	            
	        });
		}
		
		public void actionPerformed(ActionEvent e) {
			if (!this.active && Board.this.pieceSelected()) {
				Board.this.placePiece(GridSquare.this);
				System.out.println("Square Selected: ("+x+" , "+y+")");
				System.out.println("Path:");
				Board.this.selectedPiece.piecePath().stream().forEach(point -> System.out.println(point.toString()));
				System.out.println();
			}
		}
		
		public void setActive() {
			if(!this.active) {
				active = true;
				colour = Board.this.selectedPiece.getColour();
				this.setBackground(Color.decode(colour));
			}
		}
		
		public void setActive(String colour) {
			if(!this.active) {
				active = true;
				this.colour = colour;
				this.setBackground(Color.decode(colour));
			}
		}
		
		public void setUnActive() {
			active = false;
			this.setBackground(null);
		}
		
		public void setHover() {
			if (!this.active) {
				this.setBackground(Color.decode(Board.this.selectedPiece.getColour()));
			}
		}
		
		public void setUnHover() {
			if (!this.active) {
				this.setBackground(null);
			}
		}
		
		public void setColour(String colour) {
			this.colour = colour;
			this.setBackground(Color.decode(colour));
		}
		
		public String toString() {
			return ("("+x+","+y+")" + "\nActive: " + active + "\nColour: " + colour);
		}
		
	}
	
	private JPanel gridPanel = new JPanel();
	
	private GridSquare [][] boardGrid;
	private Piece selectedPiece;
	
	private Queue<GridSquare> hoverQueue;
	
	private int row = 24;
	private int col = 24;
	
	public Board() {
		// 2D array of board pieces	 
		boardGrid = new GridSquare[row][col];
		hoverQueue = new LinkedList<GridSquare>();
		
		//selectedPiece = new Piece(18, "#25BE00"); // For testing
		
		
				
		for (int i = 0; i < row; i++) { 
			for (int j = 0; j < col; j++) { 
            	boardGrid[i][j] = new GridSquare(i, j);
            	boardGrid[i][j].addActionListener(this);
            	gridPanel.add(boardGrid[i][j]);
	        }   
		} 		 
	}
	
	
	public void drawBoard() {
		 gridPanel.setLayout(new GridLayout(row, col));
		 add(gridPanel);
		 //setSize(1920/2, 1080/2);
		 setSize(750,750);
		 setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		 setBounds();
		 setVisible(true);		 
	}
	
	private void setBounds() {
		for (int i = 0; i < row; i++) { 
			for (int j = 0; j < col; j++) { 
		    	if(i==0||i==1||i==22||i==23||j==0||j==1||j==22||j==23) {
		    		if(i==1 && j==1) {boardGrid[i][j].setActive("#167BFF");}
		    		else if(i==1 && j==22) {boardGrid[i][j].setActive("#25BE00");}
		    		else if(i==22 && j==22) {boardGrid[i][j].setActive("#BE0000");}
		    		else if(i==22 && j==1) {boardGrid[i][j].setActive("#F3DF13");}
		    		else {boardGrid[i][j].setActive("#3A352B");}
		    	}
			}
    	}
	}
	
	public void hover(GridSquare origin) {
		int xCoor = origin.x;
		int yCoor = origin.y;
		for(Point c : selectedPiece.piecePath()) {
			if(xCoor+c.getX() >= 0 && xCoor+c.getX() < row && yCoor+c.getY() >= 0 && yCoor+c.getY() < row) {
				GridSquare square = boardGrid[(int)c.getX()+xCoor][(int)c.getY()+yCoor];
				if(!square.active) {
					hoverQueue.add(square);
					square.setHover();		
				}
			}
		}
	}
	
	public void unHover() {
		while(hoverQueue.size() >0) {
			GridSquare square = hoverQueue.remove();
			square.setUnHover();
		}
	}
	
	public void setSelectedPiece(Piece p) {
		selectedPiece = p;
	}
	
	public Piece getSelectedPiece() {
		return selectedPiece;
	}
	
	public boolean pieceSelected() {
		return !(this.selectedPiece == null);
	}
	
	public void placePiece(GridSquare origin) {
		if(legalMove(selectedPiece, origin)) {	
			for(Point c : selectedPiece.piecePath()) {
				boardGrid[origin.x + (int)c.getX()][origin.y + (int)c.getY()].setActive();
			}
		}
	}
	
	// Determines if position is legal
	// Need error checking for out of bounds
	public boolean legalMove(Piece p, GridSquare origin) {
		boolean valid = true;
		boolean allChecked = false;
		boolean toCorner = false;
		// Check conditions for each square in Piece
		if(!pieceSelected()) {return false;} // Bail if there's no piece selected
		while(valid && !allChecked) {
			for(Point c : p.piecePath()) {
				// Check if there are any adjacent active squares - Not valid if there are
				if(boardGrid[(int) (origin.x+c.getX())][(int) (origin.y+c.getY())].active) {valid=false;}
				if(this.squareAdjacent(boardGrid[(int) (origin.x+c.getX())][(int) (origin.y+c.getY())], p.getColour())) {valid=false;}
				// Check that there is an active piece touching a corner - Needs to be at least one			
				if(!toCorner) {
					if(this.touchingCorner(boardGrid[(int) (origin.x+c.getX())][(int) (origin.y+c.getY())], p.getColour())) {toCorner = true;}
				}
			}
			allChecked = true;
		}
		return (valid && toCorner);
	}
	
	private boolean squareAdjacent(GridSquare centre, String colour) {
		if(boardGrid[centre.x+1][centre.y].active && boardGrid[centre.x+1][centre.y].colour.equals(colour)) {return true;}
		else if(boardGrid[centre.x][centre.y+1].active && boardGrid[centre.x][centre.y+1].colour.equals(colour)) {return true;}
		else if(boardGrid[centre.x-1][centre.y].active && boardGrid[centre.x-1][centre.y].colour.equals(colour)) {return true;}
		else if(boardGrid[centre.x][centre.y-1].active && boardGrid[centre.x][centre.y-1].colour.equals(colour)) {return true;}
		return false;
	}
	
	private boolean touchingCorner(GridSquare centre, String colour) {
		if(boardGrid[centre.x+1][centre.y+1].active && boardGrid[centre.x+1][centre.y+1].colour.equals(colour)) {return true;}
		else if(boardGrid[centre.x+1][centre.y-1].active && boardGrid[centre.x+1][centre.y-1].colour.equals(colour)) {return true;}
		else if(boardGrid[centre.x-1][centre.y-1].active && boardGrid[centre.x-1][centre.y-1].colour.equals(colour)) {return true;}
		else if(boardGrid[centre.x-1][centre.y+1].active && boardGrid[centre.x-1][centre.y+1].colour.equals(colour)) {return true;}
		return false;
	}
	
	public GridSquare[][] getBoard() {
		return boardGrid;
	}
	
	public void actionPerformed(ActionEvent e){
		//Probably not needed
	}
	
	public String toString() {
		String grid = "";
		for (int i=0; i<row; i++) {
			grid = grid + "[" + Arrays.stream(boardGrid[i]).map(gs -> gs.colour).reduce((str1, str2) -> str1 + "," + str2) + "]\n";
		}
		return grid;
	}
}

