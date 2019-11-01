import javax.swing.SwingUtilities;
import java.util.*;

public class Game {

	public ArrayList<Player> players;
	public Board board;
	public boolean hints;
	public boolean visImpaired;
	public boolean playing;
	
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
