import javax.swing.SwingUtilities;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable{

	private ArrayList<Player> players;
	private Player currentPlayer;
	private Board board;
	private GameFrame window;
	private int numOfPlayers;
	private boolean hints;
	private boolean visImpaired;
	private boolean playing;
	
	private File boardSaveFile;
	private File playersSaveFile;
	
	//Colours: Color.decode(String)
	//Blue: #167BFF
	//Green: #25BE00
	//Red: #BE0000
	//Yellow: #F3DF13
	
	public Game(int numOfPlayers, int difficulty) {
		this.numOfPlayers = numOfPlayers;
		players = new ArrayList<Player>(numOfPlayers);
		players.add(new Player("#167BFF", "Player1"));
		players.add(new Player("#F3DF13", "Player2"));
		players.add(new Player("#BE0000", "Player3"));
		players.add(new Player("#25BE00", "Player4"));
	}
	
	public Game() {
		currentPlayer = null;
	}
	
	
	public void startGame(){
		board = new Board(this);
		board.drawBoard(false);
		currentPlayer = players.get(0);	
		currentPlayer.setActive(true);
		window = new GameFrame(board, players, this);
		play();
	}

	public void endGame(){
		//switch(numOfPlayers) {
		//	case 2: 
		//}
	}
	
	public void play() {
		playing = true;
		while(playing) {
			board.setSelectedPiece(currentPlayer.getPieces().get(0));
			playing = false;
		}
	}
	
	public void piecePlayed(Piece currPiece) {
		currentPlayer.setActive(false);
		currentPlayer.getPieces().removeIf(pc -> pc.getID()==currPiece.getID());
		if(currPiece.getID()==0 && !currentPlayer.hasPieces()) {currentPlayer.bonusPoints();}
		nextPlayer();
		board.setSelectedPiece(currentPlayer.getPieces().get(0));
		currentPlayer.setActive(false);
		window.drawPieceBench();
	}
	
	public void nextPlayer() {
		boolean gameOver = false;
		Player prevPlayer = currentPlayer;
		currentPlayer = players.get((players.indexOf(currentPlayer)+1)%players.size());
		
		while(!currentPlayer.hasPieces() && board.legalMovesRemain(currentPlayer) && !gameOver) {
			currentPlayer = players.get((players.indexOf(currentPlayer)+1)%players.size());
			if(currentPlayer.equals(prevPlayer)) {
				gameOver = true;
			}
		}
		if(gameOver) {
			this.endGame();
		}
	}
	
	public void saveGame() throws IOException{
		//save board to a textfile called prevBoard.txt
		boardSaveFile = new File ("prevBoard.txt");
		FileOutputStream boardFileOutStream = new FileOutputStream(boardSaveFile);
		ObjectOutputStream boardObjectOutStream = new ObjectOutputStream(boardFileOutStream);
		boardObjectOutStream.writeObject(board);
		boardObjectOutStream.close();

		//save players linked list to text file called prevPlayers.txt
		playersSaveFile = new File ("prevPlayers.txt");
		FileOutputStream playersFileOutStream = new FileOutputStream(playersSaveFile);
		ObjectOutputStream playersObjectOutStream = new ObjectOutputStream(playersFileOutStream);
		playersObjectOutStream.writeObject(players);
		playersObjectOutStream.close();
	}
	
	public void loadGame() throws IOException, ClassNotFoundException{
		//board = prevBoard.txt
		FileInputStream boardFileInStream = new FileInputStream ("prevBoard.txt");
		ObjectInputStream boardObjectInStream = new ObjectInputStream (boardFileInStream);
		board = (Board) boardObjectInStream.readObject();
		boardObjectInStream.close();

		//players = prevPlayers.txt
		FileInputStream playersFileInStream = new FileInputStream ("prevPlayers.txt");
		ObjectInputStream playersObjectInStream = new ObjectInputStream (playersFileInStream);
		players = (ArrayList<Player>) playersObjectInStream.readObject();
		playersObjectInStream.close();
		
		currentPlayer = players.stream().filter(pl -> pl.isActive()).findFirst().get();
		
		window = new GameFrame(board, players, this);

	}
	
	public void loadBoard() {
		board.drawBoard(true);
	}

	public void toggleHints(){
		if(hints == true){
			hints = false;
		}
		else{
			hints = true;
		}
	}

	public void toggleVis(){
		if(visImpaired == true){
			visImpaired = false;
		}
		else{
			visImpaired = true;
		}
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public boolean saveExists() {
		
		boolean exists = false;
		
		File test1 = new File("prevBoard.txt");
		File test2 = new File("prevPlayers.txt");

		
		if ((test1.exists() & !test1.isDirectory()) && (test2.exists() && !test2.isDirectory())){
			exists = true;
			System.out.println("Save Exists");			
		}
		else {
			exists  = false;
		}
		
		return exists;
				
	}
	

	
	
	
	
	
	
}
