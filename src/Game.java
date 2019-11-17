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
	private boolean hints;
	private boolean visImpaired;
	private boolean playing;
	
	private File boardSaveFile;
	private File playersSaveFile;
	
	//public TestingInterface testPanel;
	
	//Colours: Color.decode(String)
	//Blue: #167BFF
	//Green: #25BE00
	//Red: #BE0000
	//Yellow: #F3DF13
	
	public Game(int numOfPlayers, int difficulty) {
		players = new ArrayList<Player>(numOfPlayers);
		players.add(new Player("#167BFF", "Player1"));
		players.add(new Player("#F3DF13", "Player2"));
		players.add(new Player("#BE0000", "Player3"));
		players.add(new Player("#25BE00", "Player4"));
	}
	
	public Game() {
		// For loading
	}
	
	
	public void startGame(){
		board = new Board(this);
		board.drawBoard(false);
		currentPlayer = players.get(0);		
		window = new GameFrame(board, players, this);
		
		play();
	}

	public void endGame(){
		//board.dispose();
	}
	
	public void play() {
		playing = true;
		while(playing) {
			board.setSelectedPiece(currentPlayer.getPieces().get(0));
			playing = false;
		}
	}
	
	public void piecePlayed(Piece currPiece) {
		currentPlayer.getPieces().removeIf(pc -> pc.getID()==currPiece.getID());
		currentPlayer = players.get((players.indexOf(currentPlayer)+1)%players.size());
		board.setSelectedPiece(currentPlayer.getPieces().get(0));
		window.drawPieceBench();
	}
	
	public void saveGame() throws IOException{
		//save board to a textfile called prevBoard.txt
		boardSaveFile = new File ("prevBoard.txt");
		FileOutputStream boardFileOutStream = new FileOutputStream(boardSaveFile);
		ObjectOutputStream boardObjectOutStream = new ObjectOutputStream(boardFileOutStream);
		boardObjectOutStream.writeObject(board);

		//save players linked list to text file called prevPlayers.txt
		playersSaveFile = new File ("prevPlayers.txt");
		FileOutputStream playersFileOutStream = new FileOutputStream(playersSaveFile);
		ObjectOutputStream playersObjectOutStream = new ObjectOutputStream(playersFileOutStream);
		playersObjectOutStream.writeObject(players);
	}
	
	public void loadGame() throws IOException, ClassNotFoundException{
		//board = prevBoard.txt
		FileInputStream boardFileInStream = new FileInputStream ("prevBoard.txt");
		ObjectInputStream boardObjectInStream = new ObjectInputStream (boardFileInStream);
		board = (Board) boardObjectInStream.readObject();

		//players = prevPlayers.txt
		FileInputStream playersFileInStream = new FileInputStream ("prevPlayers.txt");
		ObjectInputStream playersObjectInStream = new ObjectInputStream (playersFileInStream);
		players = (ArrayList<Player>) playersObjectInStream.readObject();
		
		//testPanel = new TestingInterface(board, players, this);
		//testPanel.init();

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
}
