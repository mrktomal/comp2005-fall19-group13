import javax.swing.SwingUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Game implements Serializable{

	public ArrayList<Player> players;
	public Board board;
	public boolean hints;
	public boolean visImpaired;
	public boolean playing;
	
	public File boardSaveFile;
	public File playersSaveFile;
	
	//Colours: Color.decode(String)
	//Blue: 167bffff
	//Green: 25be00ff
	//Red: be0000ff
	//Yellow: f3df13ff
	
	public Game(int numOfPlayers, int difficulty) {
		players = new ArrayList<Player>(numOfPlayers);
		players.add(new Player("167bffff", "Player1"));
		players.add(new Player("25be00ff", "Player2"));
		players.add(new Player("be0000ff", "Player3"));
		players.add(new Player("f3df13ff", "Player4"));
	}
	public Game () {
		//empty constructor for resuming so we can assign the board and player lists via loadGame
	}
	
	
	public void startGame(){
		board = new Board(this);
		board.drawBoard(false);
		playing = true;
		play();
	}
	
	public void resumeGame() {
		board.drawBoard(true);
	}

	public void endGame(){
		board.dispose();
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
