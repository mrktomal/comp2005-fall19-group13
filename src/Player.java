import java.util.*;
import java.io.Serializable;


public class Player implements Serializable{
	
	private ArrayList<Piece> pieces;
	private String colour;
	private String name;
	private int score;
	private boolean bot;
	private boolean active;

	public Player(String colour, String name, boolean isBot) {
		
		this.score = 0;
		this.colour = colour;
		this.name = name;
		this.bot = isBot;
		this.pieces = new ArrayList<Piece>(21);
		
		for (int p=0; p<21; p++) {
			this.pieces.add(new Piece(p, colour));
		}
		
		active = false;
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
		score = pieces.stream().mapToInt(p -> p.getSize()*(-1)).sum();
		if(!this.hasPieces()) {score += 15;}
	}
	
	public void bonusPoints() {
		score += 5;
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	public Piece getPiece(int ID) {
		return pieces.stream().filter(pc -> pc.getID() == ID).findFirst().get();
	}
	
	public int piecesRemaining() {
		return pieces.size();
	}
	
	public boolean hasPieces() {
		return this.piecesRemaining() > 0;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean val) {
		active = val;
	}
	
	public boolean isBot() {
		return bot;
	}
}
