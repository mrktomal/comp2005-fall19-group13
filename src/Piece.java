import java.awt.Point;
import java.util.*;
import java.io.Serializable;

public final class Piece  implements Serializable{
	
	public final int[][] squares; // 5x5 matrix to represent piece
	private static int[][][] pieceBank;   // Stores each possible piece.
	private final String colour;   
	private final int pieceID;
	private final int size;
	
	//Contains a template for each piece.
	static {
		// pieceBank[ID][row][col]
		Piece.pieceBank = new int[][][]{
				{{0,0,0,0,0},{0,0,0,0,0},{0,0,1,0,0},{0,0,0,0,0},{0,0,0,0,0}}, // 0: 1
				{{0,0,0,0,0},{0,0,0,0,0},{0,0,1,1,0},{0,0,0,0,0},{0,0,0,0,0}},  // 1: 2
				{{0,0,0,0,0},{0,0,0,0,0},{0,0,1,1,1},{0,0,0,0,0},{0,0,0,0,0}},  // 2: 3
				{{0,0,0,0,0},{0,0,0,0,0},{0,0,1,1,0},{0,0,0,1,0},{0,0,0,0,0}},  // 3: Crooked 3
				{{0,0,0,0,0},{0,0,0,0,0},{0,0,1,1,0},{0,0,1,1,0},{0,0,0,0,0}},  // 4: Square
				{{0,0,0,0,0},{0,0,1,0,0},{0,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}},  // 5: T
				{{0,0,0,0,0},{0,0,0,0,0},{0,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}},  // 6: 4 Long
				{{0,0,0,0,0},{0,0,0,1,0},{0,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}},  // 7: L
				{{0,0,0,0,0},{0,0,1,1,0},{0,1,1,0,0},{0,0,0,0,0},{0,0,0,0,0}},  // 8: Z
				{{0,0,0,0,0},{1,0,0,0,0},{1,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}},  // 9: Tall L
				{{0,0,0,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,1,1,1,0},{0,0,0,0,0}},  // 10: Big T
				{{0,0,1,0,0},{0,0,1,0,0},{0,0,1,1,1},{0,0,0,0,0},{0,0,0,0,0}},  // 11: Big V
				{{0,0,0,0,0},{0,0,0,0,0},{0,0,1,1,1},{0,1,1,0,0},{0,0,0,0,0}},  // 12: Long Z
				{{0,0,0,0,0},{0,0,0,1,0},{0,1,1,1,0},{0,1,0,0,0},{0,0,0,0,0}},  // 13: Big Z
				{{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0}},  // 14: 5 Long
				{{0,0,0,0,0},{0,0,1,0,0},{0,0,1,1,0},{0,0,1,1,0},{0,0,0,0,0}},  // 15: b
				{{0,0,0,0,0},{0,0,1,1,0},{0,1,1,0,0},{0,1,0,0,0},{0,0,0,0,0}},  // 16: w
				{{0,0,0,0,0},{0,0,1,1,0},{0,0,1,0,0},{0,0,1,1,0},{0,0,0,0,0}},  // 17: U
				{{0,0,0,0,0},{0,0,1,1,0},{0,1,1,0,0},{0,0,1,0,0},{0,0,0,0,0}},  // 18: JankyPiece
				{{0,0,0,0,0},{0,0,1,0,0},{0,1,1,1,0},{0,0,1,0,0},{0,0,0,0,0}},  // 19: Cross
				{{0,0,0,0,0},{0,0,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{0,0,0,0,0}}   // 20: f
			}; 
	}
	
	/**
	 * @param colour
	 * @param ID
	 */
	public Piece(int ID, String colour) {
		
		this.pieceID = ID;
		this.colour = colour;
		this.squares = new int[5][5];
		int count = 0;
		
		//Copy piece from static block
		for (int row=0; row<5; row++) {
			for (int col=0; col<5; col++) {
				int sq = pieceBank[ID][row][col];
				this.squares[row][col] = sq;
				count += sq;
				}
			}
		
		this.size = count;
	}
	

	public int getSize() {
		return size;
	}
	
	public String getColour() {
		return colour;		
	}
	
	public int getID() {
		return pieceID;
	}
	
	public void rotate() {
		// Start on outside square
		for (int x=0; x < 5/2; x++) {
			//Go through each
			for(int y=x; y < 4-x; y++) {
				//Store Element
				int temp = squares[x][y];
				//Do the dosido
				squares[x][y] = squares[y][4-x];
				squares[y][4-x] = squares[4-x][4-y];
				squares[4-x][4-y] = squares[4-y][x];
				squares[4-y][x] = temp;				
			}
		}
	}
	
	public void flipV() {
		int temp;
		for (int row=0; row < 5/2; row++) {
			for(int col=0; col < 5; col++) {
				temp = squares[row][col];
				squares[row][col] = squares[4-row][col];
				squares[4-row][col] = temp;
			}
		}
		
	}
	
	public void flipH() {
		int temp;
		for (int row=0; row < 5; row++) {
			for(int col=0; col < 2; col++) {
				temp = squares[row][col];
				squares[row][col] = squares[row][4-col];
				squares[row][4-col] = temp;
			}
		}
	}
	
	public ArrayList<Point> piecePath(){
		ArrayList<Point> path = new ArrayList<Point>();
		for (int row=0; row<5; row++) {
			for(int col=0; col<5; col++) {
				if(squares[row][col]==1) {
					path.add(new Point(row-2,col-2));
				}
			}
		}
		return path;
	}

	public String toString() {
		return "\nID: " + pieceID +"\n"+ Arrays.toString(squares[0]) + "\n" +
				Arrays.toString(squares[1]) +"\n"+
				Arrays.toString(squares[2]) +"\n"+
				Arrays.toString(squares[3]) +"\n"+
				Arrays.toString(squares[4]) +"\n";
	}
	
}


