import javax.swing.SwingUtilities;
import java.util.*;

public class Game {

	public ArrayList<Player> players;
	public Board board;
	public boolean hints;
	public boolean visImpaired;
	public boolean playing;
	
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
	
	
	public void startGame(){
		board = new Board();
		board.drawBoard();
		playing = true;
		play();
	}

	public void endGame(){
		board.dispose();
	}
	
	public void play() {

	}
	
	public void saveGame(){}

	public void resumeGame(){}

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
