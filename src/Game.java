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

	public ArrayList<Player> players;
	public Board board;
	public boolean hints;
	public boolean visImpaired;
	public boolean playing;
	
	public File boardSaveFile;
	public File playersSaveFile;
	
	public TestingInterface testPanel;
	
	//Colours: Color.decode(String)
	//Blue: #167BFF
	//Green: #25BE00
	//Red: #BE0000
	//Yellow: #F3DF13
	
	public Game(int numOfPlayers, int difficulty) {
		players = new ArrayList<Player>(numOfPlayers);
		players.add(new Player("#167BFF", "Player1"));
		players.add(new Player("#25BE00", "Player2"));
		players.add(new Player("#BE0000", "Player3"));
		players.add(new Player("#F3DF13", "Player4"));
	}
	
	public Game() {
		// For loading
	}
	
	
	public void startGame(){
		board = new Board();
		board.drawBoard(false);
		
		testPanel = new TestingInterface(board, players, this);
		testPanel.init();
		
		playing = true;
		play();
	}

	public void endGame(){
		//board.dispose();
	}
	
	public void play() {
		
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
		
		testPanel = new TestingInterface(board, players, this);
		testPanel.init();

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

}
