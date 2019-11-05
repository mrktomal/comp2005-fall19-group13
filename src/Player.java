import java.io.Serializable;
import java.util.*;


public class Player implements Serializable{
	
	private ArrayList<Piece> pieces;
	private String colour;
	private String name;
	private int score;

	public Player(String colour, String name) {
		
		this.score = 0;
		this.colour = colour;
		this.name = name;
		this.pieces = new ArrayList<Piece>(21);
		
		for (int p=0; p<21; p++) {
			this.pieces.add(new Piece(p, colour));
		}
	}
	
	public void takeTurn(Piece p) {
		this.removePiece(p.getID());
	}
	
	protected void removePiece(int ID) {
		pieces.removeIf(p -> p.getID()==ID);
	}

	public String getColour() {
		return colour;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		this.addScore();
		return score;
	}
	
	public void addScore() {
		score = pieces.stream().mapToInt(p -> p.getSize()).sum();
	}
	
}
