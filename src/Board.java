import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.*; //Get all of Swing

public class Board extends JFrame implements ActionListener {
	
	public class GridSquare extends JButton implements ActionListener{
		
		protected boolean active = false;
		protected int x; // x-coordinate
		protected int y; // y-coordinate
		
		public GridSquare(int newRow, int newCol) {
			
			x = newRow;
			y = newCol;
			this.addActionListener(this);
			this.addMouseListener(new MouseAdapter() {

	            @Override
	            public void mouseEntered(MouseEvent me) {
	            	Board.this.hover(GridSquare.this);
	            }
	            
	            public void mouseExited(MouseEvent me) {
	            	Board.this.unHover();
	            }
	            
	        });
		}
		
		public void actionPerformed(ActionEvent e) {
			Board.this.placePiece(GridSquare.this);
		}
		
		public void setActive() {
			active = true;
			this.setBackground(Color.decode(Board.this.selectedPiece.getColour()));
		}
		
		public void setUnActive() {
			active = false;
			this.setBackground(null);
		}
		
		public void setHover() {
			this.setBackground(Color.decode(Board.this.selectedPiece.getColour()));		
		}
		
		public void setUnHover() {
			if (!this.active) {
				this.setBackground(null);
			}
		}
		
	}
	
	private JPanel gridPanel = new JPanel();
	
	private GridSquare [][] boardGrid;
	private Piece selectedPiece;
	
	private Queue<GridSquare> hoverQueue;
	
	private int row = 20;
	private int col = 20;
	
	public Board() {
		// 2D array of board pieces	 
		boardGrid = new GridSquare[row][col];
		hoverQueue = new LinkedList<GridSquare>();
		
		selectedPiece = new Piece(19, "#2874A6");
				
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
		 setSize(1920, 1080);
		 setVisible(true);			 
	}
	
	public void hover(GridSquare origin) {
		int xCoor = origin.x;
		int yCoor = origin.y;
		for(Point c : selectedPiece.piecePath()) {
			if(xCoor+c.getX() >= 0 && xCoor+c.getX() < 20 && yCoor+c.getY() >= 0 && yCoor+c.getY() < 20) {
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
	
	public void placePiece(GridSquare origin) {
		if(legalMove(selectedPiece, origin)) {	
			for(Point c : selectedPiece.piecePath()) {
				boardGrid[origin.x + (int)c.getX()][origin.y + (int)c.getY()].setActive();
			}
		}
		
	}
	
	// Determines if position is legal
	// STILL NEED TO ADD COLOUR CHECK
	// STILL NEED TO CHECK OPENING MOVE -- boardGrid[0][0] boardGrid[19][0] boardGrid[19][19] boardGrid[0][19]
	// Need error checking for out of bounds
	public boolean legalMove(Piece p, GridSquare origin) {
		boolean valid = true;
		boolean allChecked = false;
		boolean toCorner = false;
		// Check conditions for each square in Piece
		while(valid && !allChecked) {
			for(Point c : p.piecePath()) {
				// Check if there are any adjacent active squares - Not valid if there are
				if(boardGrid[(int) (origin.x+c.getX()+1)][(int) (origin.y+c.getY())].active) {valid = false;}
				else if(boardGrid[(int) (origin.x+c.getX())][(int) (origin.y+c.getY()+1)].active){valid = false;}
				else if(boardGrid[(int) (origin.x+c.getX()-1)][(int) (origin.y+c.getY())].active) {valid = false;}
				else if(boardGrid[(int) (origin.x+c.getX())][(int) (origin.y+c.getY()-1)].active) {valid = false;}
				// Check that there is an active piece touching a corner
				//if(boardGrid[(int) (origin.x+c.getX()+1)][(int) (origin.y+c.getY()+1)].active) {toCorner = true;}
				//else if(boardGrid[(int) (origin.x+c.getX()+1)][(int) (origin.y+c.getY()-1)].active) {toCorner = true;}
				//else if(boardGrid[(int) (origin.x+c.getX()-1)][(int) (origin.y+c.getY()-1)].active) {toCorner = true;}
				//else if(boardGrid[(int) (origin.x+c.getX()-1)][(int) (origin.y+c.getY()+1)].active) {toCorner = true;}
			}
			allChecked = true;
		}
		return (valid); // && toCorner);
	}
	
	public GridSquare[][] getBoard() {
		return boardGrid;
	}
	
	public void actionPerformed(ActionEvent e){
		//Probably not needed
}
}

