import javax.swing.SwingUtilities;

public class Game {

	public Player[] players;
	public Board board;
	public Boolean hints;
	public Boolean visImpaired;

	public void startGame(){
		board = new Board();
		board.drawBoard();
	}

	public void endGame(){
		board.dispose();
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

	public static void main(String[] args) {

		Game test = new Game();
		test.startGame();

	}
}
